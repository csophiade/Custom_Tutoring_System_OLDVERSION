<title> Calendar </title>
<head>
<%@ page import="javax.servlet.jsp.jstl.core.LoopTagStatus" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<link rel="stylesheet" href="https://netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css">
<style src="https://netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css"></style>
<script src="resources/js/jquery-3.6.3.js"></script>
<script src="resources/js/cal.js"></script>
<style><%@include file="/resources/css/cal.css"%></style>
<style><%@include file="/resources/js/home.js"%></style>
<style><%@include file="/resources/css/home.css"%></style>

  <link href='https://fonts.googleapis.com/css?family=Lato' rel='stylesheet' type='text/css'>
</head>

<body id="background" style="background-color:lavender"></body>
<body>
<header id="header" class="header" style="background-color:#c9b9e6">
    <p id="home">Calendar</p>
    <div>
    <div id="dropdown" style="background-color: transparent">
        <button class="dropdownbutton" onclick="myFunction()">
            <div id="bar"></div>
            <div id="bar2"></div>
            <div id="bar"></div>
            <div id="bar2"></div>
            <div id="bar"></div>
        </button>
        <div id="myDropdown" class="dropdown-content" style="z-index:200;">
            <a href="homepage-student">Home</a>
            <a href="calendar-student">Calendar</a>
            <a href="songsheet-student">Song Sheet</a>
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

</body>

<body>

<button id="mainbtn" onclick="document.getElementById('createNewLessonButton').style.display='block';" style="width:auto;">Request Lesson</button>

<div id="createNewLessonButton" class="modal">
  <form class="modal-content" action="requestLesson">
    <div class="container">
     <span onclick="document.getElementById('createNewLessonButton').style.display='none'" class="close" title="Close Modal">&times;</span>
      <h1>Request Lesson</h1>
      <hr>

      <div>
      <label for="username"><b>Username</b></label><br>
      <select type="text" id="username" placeholder="Username" name="username" required>
          <option value="${sessionUser.username}">${sessionUser.username}</option>
      </select>
      </div>

      <p></p><br><br><br>

      <label for="lessonDate"><b>Lesson Date</b></label>
      <input type="date" id="lessonDate" value="${today}" name="lessonDate" required>

      <label for="lessonTime"><b>Lesson Time</b></label>
      <input type="time" id="lessonTime" value="${time}" name="lessonTime" required>

      <label for="hours"><b>Lesson Duration</b> (not required, only select if longer or shorter lesson is needed this week)</label><br>
        <select id="hours" name="hours">
          <option value="0">Hours</option> <option value="0">0</option> <option value="1">1</option> <option value="2">2</option> <option value="3">3</option> <option value="4">4</option> <option value="5">5</option> <option value="6">6</option> <option value="7">7</option> <option value="8">8</option> <option value="9">9</option> <option value="10">10</option>
        </select>
        <label for="minutes"></label>
        <select id="minutes" name="minutes">
          <option value="0">Minutes</option> <option value="0">0</option> <option value="5">5</option> <option value="10">10</option> <option value="15">15</option> <option value="20">20</option> <option value="25">25</option> <option value="30">30</option> <option value="35">35</option> <option value="40">40</option> <option value="45">45</option> <option value="50">50</option> <option value="55">55</option>
        </select>

      <div class="clearfix">
        <button type="button" onclick="document.getElementById('createNewLessonButton').style.display='none'" class="cancelbtn">Cancel</button>
        <button type="submit" class="createbtn">Create</button>
      </div>
    </div>
  </form>
</div>
<script>
          window.addEventListener('click', function(event){
            if (!event.target.matches('.modal-content') && !event.target.matches("#mainbtn") && event.target.matches(".modal")) {
              document.getElementById("createNewLessonButton").style.display = 'none';
            }
          });

</script>

<br> <br>
  <div id="calendar">
    <div id="calendar_header"><i class="icon-chevron-left"></i>          <h1></h1><i class="icon-chevron-right"></i>         </div>
    <div id="calendar_weekdays"></div>
    <div id="calendar_content"></div>
  </div>
  <form id="sendDate" style="display:none;" action="submitDate">
  <input type="text" id="dateinput" name="date-string" value="">
  </form>

</body>