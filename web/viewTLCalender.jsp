<%-- 
    Document   : viewTLCalender
    Created on : Nov 30, 2014, 3:58:39 PM
    Author     : svalapi
--%>

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
                    <h3>See TL Schedule</h3>
                    <table border="1" cellpadding="10" cellspacing="2" align="center">

                        <tr style="font-size: 12px" align="center">
                            <td><b>Meeting Notes</b></td>
                            <td><b>Date</b></td>
                            <td><b>Time</b></td>
                            <td><b>Delete Action</b></td>
                            <td><b>Edit Action</b></td>
                        </tr>

                         
                        
                        <c:forEach var="list" items="${requestScope.totalbeans}">
                            <tr style="font-size: 10px" align="center">
                                <td>${list.meetingheadline}</td>
                                <td>${list.datevalue}</td>               
                                <td>${list.starttime}</td>
                                <td><a href="deleteUserSchedule.do?id=${list.secId}&grpid=${list.groupid}"><input type ="button" value="Delete" /></a></td>
                                <td><a href="editUserSchedule.do?id=${list.secId}&grpid=${list.groupid}"><input type ="button" value="Edit" /></a></td>
                            </tr>
                        </c:forEach><br>
                        
                    </table>

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