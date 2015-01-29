/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coord.bo;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author svalapi
 */
public class ScheduleMeetingBean extends org.apache.struts.action.ActionForm {

    private String secId;
    private String meetingheadline;
    private String datevalue;
    private String starttime;
    private String endtime;
    private String groupid;

    public String getGroupid() {
        return groupid;
    }

    public void setGroupid(String groupid) {
        this.groupid = groupid;
    }
    
    

    public String getMeetingheadline() {
        return meetingheadline;
    }

    public void setMeetingheadline(String meetingheadline) {
        this.meetingheadline = meetingheadline;
    }

    public String getSecId() {
        return secId;
    }

    public void setSecId(String secId) {
        this.secId = secId;
    }

    public String getDatevalue() {
        return datevalue;
    }

    public void setDatevalue(String datevalue) {
        this.datevalue = datevalue;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
//        if (getName() == null || getName().length() < 1) {
//            errors.add("name", new ActionMessage("error.name.required"));
//            // TODO: add 'error.name.required' key to your resources
//        }
        return errors;
    }
}
