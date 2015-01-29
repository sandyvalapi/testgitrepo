/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coord.bo;

/**
 *
 * @author svalapi
 */
public class ScheduleView {

    private String timeid;
    private String userid;
    private String meetingnotes;
    private String meetingdate;
    private String timedetails;

    public String getTimeid() {
        return timeid;
    }

    public void setTimeid(String timeid) {
        this.timeid = timeid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getMeetingnotes() {
        return meetingnotes;
    }

    public void setMeetingnotes(String meetingnotes) {
        this.meetingnotes = meetingnotes;
    }

    public String getMeetingdate() {
        return meetingdate;
    }

    public void setMeetingdate(String meetingdate) {
        this.meetingdate = meetingdate;
    }

    public String getTimedetails() {
        return timedetails;
    }

    public void setTimedetails(String timedetails) {
        this.timedetails = timedetails;
    }

}
