/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coord.src;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
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
public class ScheduleConfirm extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";

    Connection con = null;
    PreparedStatement pst, pst1 = null;
    DataSource ds = null;
    ResultSet rs, rs1 = null;
    HttpSession session = null;

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        try {
            ds = (DataSource) InitialContext.doLookup("java:/comp/env/MSDB");
            con = ds.getConnection();
            session = request.getSession(false);
            System.out.println(")))))))))::" + session.getAttribute("teamname").toString());
            pst = con.prepareStatement("select * from user_details where teamname = ?");
            pst.setString(1, session.getAttribute("teamname").toString());
            rs = pst.executeQuery();
            List<String> teamObj = new ArrayList<String>();

            while (rs.next()) {
                teamObj.add(rs.getString(1));
            }

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String dateInString = request.getParameter("meetingdate");

            java.util.Date date = formatter.parse(dateInString);
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            System.out.println("team objec:" + teamObj);
            String group_id=UUID.randomUUID().toString().replace("-", "");
            for (String s : teamObj) {
                pst1 = con.prepareStatement("insert into user_calender values(?,?,?,?,?,?)");
                pst1.setString(1, UUID.randomUUID().toString().replace("-", ""));
                pst1.setString(2, s);
                pst1.setString(3, request.getParameter("purposeofmeeting"));
                pst1.setDate(4, sqlDate);
                pst1.setString(6, group_id);
                pst1.setString(5, request.getParameter("time"));
                rs1 = pst1.executeQuery();
            }
            request.setAttribute("msg", "User details updated successfully");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                con.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (pst1 != null) {
                pst1.close();
            }
            if (rs != null) {
                rs.close();
            }
            if (rs1 != null) {
                rs1.close();
            }
        }
        System.out.println("....." + request.getParameter("teamobject") + " mmeeting notes:" + request.getParameter("purposeofmeeting") + request.getParameter("meetingdate") + ",," + request.getParameter("time"));
        return mapping.findForward(SUCCESS);
    }
}
