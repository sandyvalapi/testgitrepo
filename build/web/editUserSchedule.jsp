<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@taglib prefix="tiles" uri="http://struts.apache.org/tags-tiles" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<html:html  lang="en"> 
    <head>
        <meta name="description" content="" />
        <meta name="keywords" content="" />
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <title>M Coordinator</title>
        <link rel="stylesheet" type="text/css" href="style.css" />
    </head>
    <body>
        <div id="wrapper">
            <div id="header">
                <div id="logo">
                    <h1><a href="#">Meeting Coord</a></h1>
                </div>
                <div id="menu">
                    <ul>
                         
                         <li class="first current_page_item"><a href="goodTL.jsp">Back</a></li>

                    </ul>
                    <br class="clearfix" />
                </div>
            </div>
            <div id="page">

                <div class="box">
                    <h2>Welcome to Meeting Coord</h2>
                    <p>
                        Please register a user in this application for further interaction with the application.
                        All the users will be given username and password for further interaction with the application.
                    </p>
                </div>

                <div class="box">
                    <h3>Schedule My Calender</h3>
                    <form  action="updateEditedUserSchedule.do"  >
                        <input type="text" name="meetingheadline" value="${requestScope.mn}" placeholder="Enter Meeting Notes"/> <br/>
                        <input type="date" name="datevalue" value="${requestScope.md}" /> <br/>
                        <input type="hidden" name="timeid" value="${requestScope.tid}"/>
                        <input type="hidden" name="grpid" value="${requestScope.grpid}"/>
                        <select name="timedetails">
                            <option value="1" ${requestScope.td == 1?'selected="selected"':''}>10:00 AM - 12:00 AM</option>
                            <option value="2" ${requestScope.td == 2?'selected="selected"':''}>01:00 PM - 02:00 PM</option>
                            <option value="3" ${requestScope.td == 3?'selected="selected"':''}>2:00 PM - 04:00 PM</option>
                        </select>
                        <!--<input type="time" name="starttime" value="" /> <br/>
                        <input type="time" name="endtime" value="" /> <br/>-->
                        
                        

                        <br/>
                        <input type="submit" value="Submit"/><br/>
                    </form>

                </div>
                <br class="clearfix" />


                <br class="clearfix" />
            </div>
        </div>
        <div id="footer">
            Copyright (c) 2014 Meeting Scheduler. All rights reserved. Design by Rohit.
        </div>
    </body>
</html:html>