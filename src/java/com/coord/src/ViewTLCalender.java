/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coord.src;

import com.coord.bo.ScheduleMeetingBean;
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
public class ViewTLCalender extends org.apache.struts.action.Action {

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
            pst = con.prepareStatement("select * from user_calender where userid = ?");
            pst.setString(1, session.getAttribute("id").toString());
            rs = pst.executeQuery();
            List<ScheduleMeetingBean> smbs = new ArrayList<ScheduleMeetingBean>();

            while (rs.next()) {
                ScheduleMeetingBean bean = new ScheduleMeetingBean();
                bean.setMeetingheadline(rs.getString(3));
                bean.setDatevalue(rs.getDate(4).toString());
                bean.setStarttime(rs.getString(5));
                bean.setSecId(rs.getString(1));
                smbs.add(bean);

            }
            request.setAttribute("totalbeans", smbs);
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
