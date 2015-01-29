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
                        <li class="first current_page_item"><a href="viewTLCreatedMeetings.do">View Meetings</a></li>
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
                    <h3>CHECK TEAM AVAILABLITY</h3>
                    <html:form  action="/checkMeetingFreeTime"  >
                        <input type="text" name="purposeofmeeting" value="" required="required" placeholder="Purpose of meeting"/> <br/>
                        <input type="date" name="meetingdate" value="" required="required" /> <br/>
                        <!--                        <input type="time" name="starttime" value="" required="required"/> <br/>
                                                <input type="time" name="endtime" value="" required="required"/> <br/>-->
                        <input type="hidden" name="teamname" value="${sessionScope.teamname}"/> <br/>
                        <input type="submit" value="Check availablity"/><br/>
                    </html:form>
                    ${requestScope.msg}
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