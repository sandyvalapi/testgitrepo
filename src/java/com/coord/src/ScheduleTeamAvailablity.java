/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coord.src;

import com.coord.bo.RegisterBean;
import com.coord.bo.ScheduleMeetingRequestBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author svalapi
 */
public class ScheduleTeamAvailablity extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";

    Connection con = null;
    PreparedStatement pst, pst1, pst2 = null;
    DataSource ds = null;
    ResultSet rs, rs1, rs2 = null;
    HttpSession session = null;

    /*
     Get Complete team  individual timing for requested time.
     If every one is available then we need to add.
     Else we need to show who are not available for meeting
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        session = request.getSession(false);
        ScheduleMeetingRequestBean requestBean = (ScheduleMeetingRequestBean) form;
        ds = (DataSource) InitialContext.doLookup("java:/comp/env/MSDB");
        con = ds.getConnection();
        String team = request.getParameter("teamname");
        pst = con.prepareStatement("select * from user_details where teamname = ?");
        pst.setString(1, team);
        System.out.println("select * from user_details where teamname = " + team);
        rs = pst.executeQuery();
        List<String> myTeam = new ArrayList<String>();
        while (rs.next()) {
            myTeam.add(rs.getString(1));
        }

        System.out.println("My Team object:" + myTeam);
        List<String> occupiedPPL = new ArrayList<String>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        java.util.Date d1 = sdf.parse(request.getParameter("meetingdate") + " " + request.getParameter("starttime") + ":00");
        java.util.Date d2 = sdf.parse(request.getParameter("meetingdate") + " " + request.getParameter("endtime") + ":00");
        pst1 = con.prepareStatement("select * from user_calender where userid = ? and starttime=? and endtime=?");
        for (String str : myTeam) {
            System.out.println("select * from user_calender where userid = " + str + " and starttime=" + new Timestamp(d1.getTime()) + " and endtime=" + new Timestamp(d2.getTime()));
            pst1.setString(1, str);
            pst1.setTimestamp(2, new Timestamp(d1.getTime()));
            pst1.setTimestamp(3, new Timestamp(d2.getTime()));
            rs1 = pst1.executeQuery();
            System.out.println("Checkin for team in d1.getTime():" + new Timestamp(d1.getTime()) + "  to " + new Timestamp(d2.getTime()));
            System.out.println(":::::" + rs1.next());
            while (rs1.next()) {
                System.out.println("_______" + rs1.getString(2));
                occupiedPPL.add(rs1.getString(2));
            }
        }
        System.out.println("occcupied size:::" + occupiedPPL.size());
        if (occupiedPPL.isEmpty()) {
            request.setAttribute("myTeam", myTeam);
            request.setAttribute("d1", request.getParameter("starttime"));
            request.setAttribute("d2", request.getParameter("endtime"));
            request.setAttribute("purposeofmeeting", request.getParameter("purposeofmeeting"));
            request.setAttribute("meetingdate", request.getParameter("meetingdate"));
            request.setAttribute("msg", "All Team is available at this timing");
        } else {
            List<RegisterBean> rb = new ArrayList<RegisterBean>();
            for (String uservalue : occupiedPPL) {
                pst2 = con.prepareStatement("select * from user_details where userid = ?");
                pst2.setString(1, uservalue);
                rs2 = pst2.executeQuery();
                while (rs2.next()) {
                    RegisterBean bean = new RegisterBean();
                    bean.setUid(rs2.getString(1));
                    bean.setUsername(rs2.getString(2));
                    bean.setUsertype(rs2.getString(4));
                    bean.setFirstname(rs2.getString(5));
                    bean.setLastname(rs2.getString(6));
                    bean.setTeamname(rs2.getString(7));
                    rb.add(bean);
                }
            }
            System.out.println("rb sixe:" + rb.size());
            request.setAttribute("beanList", rb);
        }
        System.out.println("Team occupied:" + occupiedPPL);
        return mapping.findForward(SUCCESS);
    }
}
