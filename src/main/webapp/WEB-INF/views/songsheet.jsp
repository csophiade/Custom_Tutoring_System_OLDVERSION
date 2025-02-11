<title> Song Sheet </title>
<%@ page import="javax.servlet.jsp.jstl.core.LoopTagStatus" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@page import="javax.imageio.ImageIO"%>
<style><%@include file="/resources/css/songsheet.css"%></style>
<style><%@include file="/resources/css/home.css"%></style>
<style><%@include file="/resources/js/home.js"%></style>
<body id="background" style="background-color:lavender"></body>

<body>
<header id="header" class="header" style="background-color:#c9b9e6">
    <p id="home">Song Sheet</p>
    <div>
    <div id="dropdown" style="background-color: transparent">
        <button class="dropdownbutton" onclick="myFunction()">
            <div id="bar"></div>
            <div id="bar2"></div>
            <div id="bar"></div>
            <div id="bar2"></div>
            <div id="bar"></div>
        </button>
        <div id="myDropdown" class="dropdown-content">
            <a href="homepage-teacher">Home</a>
            <a href="calendar">Calendar</a>
            <a href="booking-info">Bookings</a>
            <a href="student-info">Students</a>
            <a href="songsheet">Song Sheet</a>
            <div style="height: 100px"></div>
            <a href="logout">Logout</a>
        </div>
    </div>
    </div>
</header>
<script>
    function myFunction() {
      document.getElementById("myDropdown").classList.toggle("show");
    }
    window.onclick = function(event) {
      if (!event.target.matches('.dropdownbutton') && !event.target.matches('.dropdown-content')) {
        var dropdowns = document.getElementsByClassName("dropdown-content");
        var i;
        for (i = 0; i < dropdowns.length; i++) {
          var openDropdown = dropdowns[i];
          if (openDropdown.classList.contains('show')) {
            openDropdown.classList.remove('show');
          }
        }
      }
    }
    function redirect(page){
        window.location.href = page;
    }
    </script>

<a href="songsheet">Google Drive</a>


</body>