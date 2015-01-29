/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coord.src;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
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
public class ScheduleMyCalender extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    Connection con = null;
    PreparedStatement pst = null;
    DataSource ds = null;
    ResultSet rs = null;
    HttpSession session = null;

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        ds = (DataSource) InitialContext.doLookup("java:/comp/env/MSDB");
        con = ds.getConnection();
        pst = con.prepareStatement("insert into user_calender values(?,?,?,?,?,?)");
        pst.setString(1, UUID.randomUUID().toString().replace("-", ""));
        pst.setString(2, request.getParameter("userid"));
        pst.setString(3, request.getParameter("meetingheadline"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateInString = request.getParameter("datevalue");
        java.util.Date date = formatter.parse(dateInString);
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        pst.setDate(4, sqlDate);

        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        //java.util.Date d1 = sdf.parse(request.getParameter("datevalue") + " " + request.getParameter("starttime") + ":00");
       // java.util.Date d2 = sdf.parse(request.getParameter("datevalue") + " " + request.getParameter("endtime") + ":00");
        //sdf.applyPattern("yyyy-MM-dd hh:mm:ss");
        //String mydt = sdf.format(d1);
        //System.out.println("mydate::;" + mydt);
        //pst.setTimestamp(5, new Timestamp(d1.getTime()));
        //pst.setTimestamp(6, new Timestamp(d2.getTime()));
        pst.setString(5, request.getParameter("timedetails"));
        pst.setString(6, null);
        pst.execute();
        request.setAttribute("msg", "Your time has scheduled successfully");
        return mapping.findForward(SUCCESS);
    }
}
