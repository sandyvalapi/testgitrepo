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
public class RegisterBean extends org.apache.struts.action.ActionForm {

    private String firstname;
    private String lastname;
    private String usertype;
    private String password;
    private String username;
    private String teamname;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
    private String uid;
    
    

    public String getTeamname() {
        return teamname;
    }

    public void setTeamname(String teamname) {
        this.teamname = teamname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
