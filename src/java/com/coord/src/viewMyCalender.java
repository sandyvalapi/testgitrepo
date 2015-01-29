/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coord.src;

import com.coord.bo.ScheduleView;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
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
public class viewMyCalender extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    Connection conn = null;
    DataSource ds = null;
    RequestDispatcher rDispatcher = null;
    HttpSession session = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        try {
            ds = (DataSource) InitialContext.doLookup("java:/comp/env/MSDB");
            conn = ds.getConnection();
            session = request.getSession(false);
            pst=conn.prepareStatement("select * from USER_CALENDER where userid=?");
            pst.setString(1, session.getAttribute("id").toString());
            rs=pst.executeQuery();
            List<ScheduleView> scheduleViews = new ArrayList<ScheduleView>();
            while(rs.next()){
                ScheduleView sv = new ScheduleView();
                sv.setTimeid(rs.getString(1));
                sv.setUserid(rs.getString(2));
                sv.setMeetingnotes(rs.getString(3));
                sv.setMeetingdate(rs.getDate(4)+"");
                sv.setTimedetails(rs.getString(5));
                scheduleViews.add(sv);
            }
            request.setAttribute("list", scheduleViews);
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            
        }
        return mapping.findForward(SUCCESS);
    }
}
