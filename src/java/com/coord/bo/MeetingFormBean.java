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
public class MeetingFormBean extends org.apache.struts.action.ActionForm {

    private String userId;
    private String meetinghead;
    private String meetingdate;
    private String timeDetails;

    public String getMeetinghead() {
        return meetinghead;
    }

    public void setMeetinghead(String meetinghead) {
        this.meetinghead = meetinghead;
    }

    public String getMeetingdate() {
        return meetingdate;
    }

    public void setMeetingdate(String meetingdate) {
        this.meetingdate = meetingdate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTimeDetails() {
        return timeDetails;
    }

    public void setTimeDetails(String timeDetails) {
        this.timeDetails = timeDetails;
    }

    @Override
    public String toString() {
        return userId+"==="+meetinghead+"==="+timeDetails+"==="+meetingdate; //To change body of generated methods, choose Tools | Templates.
    }
    
    
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();

        return errors;
    }
}
