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
import java.util.ArrayList;
import java.util.Arrays;
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
public class ProceedToMeeting extends org.apache.struts.action.Action {

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
        System.out.println(".." + request.getParameter("myTeam"));
        System.out.println("Team name:" + request.getParameter("usrtimeend"));
        List<String> myList = new ArrayList<String>(Arrays.asList(request.getParameter("myTeam").replace("[", "").replace("]", "").split(",")));

        for (String str : myList) {
            pst = con.prepareStatement("insert into user_calender values(?,?,?,?,?,?)");
            pst.setString(1, UUID.randomUUID().toString().replace("-", ""));
            pst.setString(2, str);
            pst.setString(3, request.getParameter("purposeofmeeting"));
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String dateInString = request.getParameter("meetingdate");
            java.util.Date date = formatter.parse(dateInString);
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            pst.setDate(4, sqlDate);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            java.util.Date d1 = sdf.parse(request.getParameter("meetingdate") + " " + request.getParameter("d1") + ":00");
            java.util.Date d2 = sdf.parse(request.getParameter("meetingdate") + " " + request.getParameter("d2") + ":00");
         
            pst.setTimestamp(5, new Timestamp(d1.getTime()));
            pst.setTimestamp(6, new Timestamp(d2.getTime()));
            pst.execute();
        }
       
        request.setAttribute("msg", "scheduled successfully");
        return mapping.findForward(SUCCESS);
    }
}
