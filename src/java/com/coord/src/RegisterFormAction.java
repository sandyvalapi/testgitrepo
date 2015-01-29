/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coord.src;

import com.coord.bo.RegisterBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
public class RegisterFormAction extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS_USER_REGISTER = "successregistration";
    private static final String FAILED_USER_REGISTER = "failregistration";
    private static final String DEFAULT = "samepage";

    Connection con = null;
    PreparedStatement pst = null;
    DataSource ds = null;
    ResultSet rs = null;
    HttpSession session = null;

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        //UUID.randomUUID().toString().replace("-", "")
        RegisterBean bean = (RegisterBean) form;
        ds = (DataSource) InitialContext.doLookup("java:/comp/env/MSDB");
        con = ds.getConnection();
        pst = con.prepareStatement("INSERT INTO USER_DETAILS VALUES(?,?,?,?,?,?,?)");
        pst.setString(1, UUID.randomUUID().toString().replace("-", ""));
        pst.setString(2, bean.getUsername());
        pst.setString(3, bean.getPassword());
        pst.setString(4, bean.getUsertype());
        pst.setString(5, bean.getFirstname());
        pst.setString(6, bean.getLastname());
        pst.setString(7, bean.getTeamname());
        pst.execute();
        request.setAttribute("msg", "User registered successfully");
        return mapping.findForward(SUCCESS_USER_REGISTER);
    }
}
