/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coord.src;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
public class UpdateEditedUserSchedule extends org.apache.struts.action.Action {

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
            if (request.getParameter("grpid").length() > 2) {
                pst = con.prepareStatement("update user_calender set meetingnotes= ? , time_details = ? where Group_id = ?");
                pst.setString(1, request.getParameter("meetingheadline"));//
                pst.setString(2, request.getParameter("timedetails"));
                pst.setString(3, request.getParameter("grpid"));
            } else {
                pst = con.prepareStatement("update user_calender set meetingnotes= ? , time_details = ? where TIMEID = ?");
                pst.setString(1, request.getParameter("meetingheadline"));//
                pst.setString(2, request.getParameter("timedetails"));
                pst.setString(3, request.getParameter("timeid"));
            }

            pst.execute();

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
