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
public class ScheduleMeetingRequestBean extends org.apache.struts.action.ActionForm {

    private String purposeofmeeting;
    private String meetingdate;
    private String starttime;
    private String endtime;

    public String getPurposeofmeeting() {
        return purposeofmeeting;
    }

    public void setPurposeofmeeting(String purposeofmeeting) {
        this.purposeofmeeting = purposeofmeeting;
    }

    public String getMeetingdate() {
        return meetingdate;
    }

    public void setMeetingdate(String meetingdate) {
        this.meetingdate = meetingdate;
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
