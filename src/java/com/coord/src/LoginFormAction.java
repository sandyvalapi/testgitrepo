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
public class LoginFormAction extends org.apache.struts.action.Action {

    private static final String SUCCESS_ADMIN = "goodAdmin";
    private static final String SUCCESS_TL = "goodTL";
    private static final String SUCCESS_TM = "goodTM";
    private static final String SAME_PAGE = "login";
    Connection con = null;
    PreparedStatement pst = null;
    DataSource ds = null;
    ResultSet rs = null;
    HttpSession session = null;

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        com.coord.bo.LoginBean loginBo = (com.coord.bo.LoginBean) form;

        ds = (DataSource) InitialContext.doLookup("java:/comp/env/MSDB");
        con = ds.getConnection();
        pst = con.prepareStatement("select * from user_details where username=? and password=?");
        pst.setString(1, loginBo.getUsername());
        pst.setString(2, loginBo.getPassword());
        rs = pst.executeQuery();

        while (rs.next()) {
            if (rs.getString(4).equalsIgnoreCase("ad")) {
                session = request.getSession(true);
                session.setAttribute("username", rs.getString(2));
                session.setAttribute("password", rs.getString(3));
                session.setAttribute("id", rs.getString(1));
                session.setAttribute("type", rs.getString(4));
                return mapping.findForward(SUCCESS_ADMIN);
            } else if (rs.getString(4).equalsIgnoreCase("tl")) {
                session = request.getSession(true);
                session.setAttribute("username", rs.getString(2));
                session.setAttribute("password", rs.getString(3));
                session.setAttribute("id", rs.getString(1));
                session.setAttribute("type", rs.getString(4));
                session.setAttribute("firstname", rs.getString(5));
                session.setAttribute("lastname", rs.getString(6));
                session.setAttribute("teamname", rs.getString(7));
                return mapping.findForward(SUCCESS_TL);
            } else if (rs.getString(4).equalsIgnoreCase("tm")) {
                session = request.getSession(true);
                session.setAttribute("username", rs.getString(2));
                session.setAttribute("password", rs.getString(3));
                session.setAttribute("id", rs.getString(1));
                session.setAttribute("type", rs.getString(4));
                session.setAttribute("firstname", rs.getString(5));
                session.setAttribute("lastname", rs.getString(6));
                session.setAttribute("teamname", rs.getString(7));
                return mapping.findForward(SUCCESS_TM);
            }

        }

        return mapping.findForward(SAME_PAGE);

    }
}
