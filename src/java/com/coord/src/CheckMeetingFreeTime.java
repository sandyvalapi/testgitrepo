/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coord.src;

import com.coord.bo.DualBean;
import com.coord.bo.MeetingFormBean;
import com.coord.bo.RegisterBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
public class CheckMeetingFreeTime extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    Connection con = null;
    PreparedStatement pst, pst1 = null;
    DataSource ds = null;
    ResultSet rs, rs1 = null;
    HttpSession session = null;

    /**
     * Check for all team members including team leader Get their individual
     * time availability for the day Show the free time of all the users
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        try {
            ds = (DataSource) InitialContext.doLookup("java:/comp/env/MSDB");
            con = ds.getConnection();
            session = request.getSession(false);
            System.out.println("....:" + session.getAttribute("teamname"));
            /*
             Get team name from the session variable. Apply the same value and check for existing users
             */
            pst = con.prepareStatement("select * from user_details where teamname=?");
            pst.setString(1, session.getAttribute("teamname").toString());
            rs = pst.executeQuery();
            List<String> myTeam = new ArrayList<>();
            List<RegisterBean> registerBeans = new ArrayList<RegisterBean>();
            while (rs.next()) {
                myTeam.add(rs.getString(1));
                RegisterBean bean = new RegisterBean();
                bean.setFirstname(rs.getString(4));
                bean.setUsername(rs.getString(2));
                bean.setLastname(rs.getString(6));
                bean.setTeamname(rs.getString(7));
                bean.setUsertype(rs.getString(4));
                registerBeans.add(bean);
            }

            System.out.println("My Team::" + myTeam);
            List<String> overAllTime = new ArrayList<>();
            overAllTime.add("1");
            overAllTime.add("2");
            overAllTime.add("3");

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String dateInString = request.getParameter("meetingdate");
            Date date = formatter.parse(dateInString);
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());

            List<MeetingFormBean> formBeans = new ArrayList<>();
            Map<String, List<String>> map = new HashMap<String, List<String>>();
            for (String p : myTeam) {
                List<String> timeAvailable = new ArrayList<>();
                for (String ot : overAllTime) {
                    pst1 = con.prepareStatement("Select * from user_calender where meetingdate = ? and userid = ? and time_details = ?");
                    pst1.setDate(1, sqlDate);
                    pst1.setString(2, p);
                    pst1.setString(3, ot);
                    rs1 = pst1.executeQuery();
                    if (!rs1.next()) {
                        timeAvailable.add(ot);
                    }
                    while (rs1.next()) {
                        MeetingFormBean bean = new MeetingFormBean();
                        bean.setUserId(rs1.getString(2));
                        bean.setMeetinghead(rs1.getString(3));
                        bean.setMeetingdate(rs1.getDate(4).toString());
                        bean.setTimeDetails(rs1.getString(5));
                        formBeans.add(bean);
                    }
                }
                map.put(p, timeAvailable);
            }

            int totalStrength = myTeam.size();
            List<DualBean> teamAvailableAndFree = new ArrayList<DualBean>();

            int countVal = 0;
            List<String> uni = new ArrayList<>();
            for (String ss : overAllTime) {

                for (Map.Entry<String, List<String>> entry : map.entrySet()) {
                    if (entry.getValue().contains(ss)) {
                        countVal++;
                        System.out.println("entry.getValue():" + entry.getValue() + "==" + ss);
                        if (countVal == totalStrength) {
                            if (ss.equalsIgnoreCase("1")) {
                                DualBean db = new DualBean("1", "10:00 AM - 12:00 PM");
                                teamAvailableAndFree.add(db);
                            } else if (ss.equalsIgnoreCase("2")) {
                                DualBean db = new DualBean("2", "1:00 PM - 2:00 PM");
                                teamAvailableAndFree.add(db);
                            } else {
                                DualBean db = new DualBean("3", "2:00 PM - 4:00 PM");
                                teamAvailableAndFree.add(db);
                            }

                            System.out.println("team available at :" + ss);
                        }
                    }

                }
                countVal = 0;
            }
            request.setAttribute("purposeofmeeting", request.getParameter("purposeofmeeting"));
            request.setAttribute("meetingdate", request.getParameter("meetingdate"));
            request.setAttribute("teamObject", registerBeans);
            request.setAttribute("availableTime", teamAvailableAndFree);

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
        return mapping.findForward(SUCCESS);
    }
}
