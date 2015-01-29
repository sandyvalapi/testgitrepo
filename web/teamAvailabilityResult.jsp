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
                        This application is useful for scheduling online meetings
                    </p>
                </div>

                <div class="box">
                    <form action="scheduleConfirm.do">


                        <h3>Team Available<select name = "time">
                                <c:forEach var="availableTime" items="${requestScope.availableTime}">

                                    <option value="${availableTime.one}">${availableTime.two}</option>
                                </c:forEach></select></h3>
                                <c:if test="${requestScope.availableTime.size() > 0}">
                            <table border="1" cellpadding="10" cellspacing="2" align="center">

                                <tr style="font-size: 12px" align="center">
                                    <td><b>USER NAME</b></td>
                                    <td><b>FIRST NAME</b></td>
                                    <td><b>LAST NAME</b></td>
                                    <td><b>TEAM NAME</b></td>
                                    <td><b>USER ROLE</b></td>
                                </tr>

                                <input type="hidden" value="${requestScope.teamObject}" name="teamobject"/>
                                <input type="hidden" value="${requestScope.purposeofmeeting}" name="purposeofmeeting"/>
                                <input type="hidden" value="${requestScope.meetingdate}" name="meetingdate"/>
                                <c:forEach var="list" items="${requestScope.teamObject}">
                                    <tr style="font-size: 10px" align="center">
                                        <td>${list.username}</td>
                                        <td>${list.firstname}</td>               
                                        <td>${list.lastname}</td>
                                        <td>${list.teamname}</td>
                                        <td>${list.usertype}</td>
                                    </tr>
                                </c:forEach><br>

                            </table>
                            <input type="submit" value="Submit" name="submit" />
                        </c:if>
                            <c:if test="${requestScope.availableTime.size() < 1}">
                                Team is not available for meeting
                            </c:if>


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