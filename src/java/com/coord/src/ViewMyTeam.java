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
import java.util.ArrayList;
import java.util.List;
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
public class ViewMyTeam extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "getTeamList";

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
        pst = con.prepareStatement("SELECT * from user_details where teamname=?");
        pst.setString(1, request.getParameter("teamname"));
        rs=pst.executeQuery();
        List<RegisterBean> beanList = new ArrayList<RegisterBean>();
        while(rs.next()){
            RegisterBean bean = new RegisterBean();
            bean.setUid(rs.getString(1));
            bean.setUsername(rs.getString(2));
            bean.setUsertype(rs.getString(4));
            bean.setFirstname(rs.getString(5));
            bean.setLastname(rs.getString(6));
            bean.setTeamname(rs.getString(7));
            beanList.add(bean);
        }
        request.setAttribute("beanList", beanList);
        return mapping.findForward(SUCCESS);
    }
}
