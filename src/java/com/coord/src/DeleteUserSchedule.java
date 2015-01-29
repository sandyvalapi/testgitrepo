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
public class DeleteUserSchedule extends org.apache.struts.action.Action {

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
        System.out.println("****8" + request.getParameter("id"));
        
        try {
            ds = (DataSource) InitialContext.doLookup("java:/comp/env/MSDB");
            con = ds.getConnection();
            session = request.getSession(false);
            if (request.getParameter("grpid").length() > 2) {
                pst = con.prepareStatement("delete from user_calender where group_id = ?");
                pst.setString(1, request.getParameter("grpid"));
            } else {
                
                pst = con.prepareStatement("delete from user_calender where TIMEID = ?");
                pst.setString(1, request.getParameter("id"));
            }
            
            pst.executeUpdate();
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
        request.setAttribute("msg", "Row deleted successfully");
        return mapping.findForward(SUCCESS);
    }
}
