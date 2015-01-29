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
public class testser extends org.apache.struts.action.Action {

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
        System.out.println("Test servlet called");
        try{
            System.out.println("****");
            ds = (DataSource) InitialContext.doLookup("java:/comp/env/MSDB");
            con = ds.getConnection();
            session = request.getSession(false);
            pst = con.prepareStatement("update user_calender set meetingnotes= ? , time_details = ? where TIMEID = ?");
            pst.setString(1, request.getParameter("meetingheadline"));//
            pst.setString(2, request.getParameter("timedetails"));
            pst.setString(3, request.getParameter("timeid"));
            pst.execute();
            System.out.println("____________");
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            
        }
        return mapping.findForward(SUCCESS);
    }
}
