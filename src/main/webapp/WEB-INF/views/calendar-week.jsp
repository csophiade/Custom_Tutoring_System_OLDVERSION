<title> Week View </title>
<%@ page import="javax.servlet.jsp.jstl.core.LoopTagStatus" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<link rel="stylesheet" href="https://netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css">
<style src="https://netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css"></style>
<script src="resources/js/jquery-3.6.3.js"></script>
<script src="resources/js/calendarWeek.js"></script>
<style><%@include file="/resources/js/home.js"%></style>
<style><%@include file="/resources/css/home.css"%></style>
<style><%@include file="/resources/css/calendarWeek.css"%></style>


<body id="background" style="background-color:lavender"></body>
<body>
<header id="header" class="header" style="background-color:#c9b9e6">
    <p id="home">Week</p>
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
            <a href="homepage-teacher">Home</a>
            <a href="calendar">Calendar</a>
            <a href="booking-info">Bookings</a>
            <a href="student-info">Students</a>
            <a href="songsheet">Song Sheet</a>
            <div style="height:100px;"></div>
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
<%
String errorMessage = (String) request.getAttribute("errorMessage");
if (errorMessage != null && errorMessage != "") {
%>
  <script>
  alert("${errorMessage}")</script>
<%
}
%>

<input type="hidden" id="dateinput" value="<%= request.getAttribute("dateInput") %>">
<button id="mainbtn" onclick="displayForm('<%= request.getAttribute("dateInput") %>', <%= request.getAttribute("studentList") %>, <%= request.getAttribute("studentListLength") %> )" style="width:auto;">Create New Lesson</button>

<div id="createNewLessonButton" class="modal">
  <form class="modal-content" action="createLesson">
    <div class="container">
     <span onclick="document.getElementById('createNewLessonButton').style.display='none'" class="close" title="Close Modal">&times;</span>
      <h1>Create New Lesson</h1>
      <hr>

      <div>
      <label for="username"><b>Username</b></label><br>
      <select type="text" id="username" placeholder="Username" name="username" required>
          <option value=""></option>
      </select>
      </div>

      <p></p><br><br><br>

      <label for="lessonDate"><b>Lesson Date</b></label>
      <input type="date" id="lessonDate" value="2023-01-01" name="lessonDate" required>

      <label for="lessonTime"><b>Lesson Time</b></label>
      <input type="time" id="lessonTime" value="09:00" name="lessonTime" required>

      <label for="hours"><b>Lesson Duration</b> (not required)</label><br>
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

    function displayForm(dateInput, studentList, studentListLength){
        var formDate = document.getElementById("lessonDate");
        console.log(dateInput);
        console.log(studentList);
        formDate.value = dateInput;
        const select = document.getElementById("username");
        select.innerHTML = "";
        const option = document.createElement("option");
        option.value = "";
        option.text = "Student";
        select.appendChild(option);
        for (let i = 0; i < studentListLength; i++){
            const option = document.createElement("option");
            option.value = studentList[i];
            option.text = studentList[i];
            select.appendChild(option);
        }
        console.log("check");
        document.getElementById('createNewLessonButton').style.display='block';
    }
</script>

<button id="mainbtn" onclick="displayForm1('<%= request.getAttribute("dateInput") %>')" style="width:auto;">Create Block Time</button>
<div id="createNewBlockButton" class="modal">
  <form class="modal-content" action="createLesson">
    <div class="container">
     <span onclick="document.getElementById('createNewBlockButton').style.display='none'" class="close" title="Close Modal">&times;</span>
      <h1>Create Block Time</h1>
      <hr>

      <input type="hidden" id="usernameBlock" value="Block" placeholder="Block" name="username" required>

      <label for="lessonDate"><b>Date</b></label>
      <input type="date" id="dateBlock" value="2023-01-01" name="lessonDate" required>

      <label for="lessonTime"><b>Time</b></label>
      <input type="time" id="timeBlock" value="09:00" name="lessonTime" required>

      <label for="hours"><b>Duration</b></label><br>
        <select id="hoursBlock" name="hours" required>
          <option value="">Hours</option> <option value="0">0</option> <option value="1">1</option> <option value="2">2</option> <option value="3">3</option> <option value="4">4</option> <option value="5">5</option> <option value="6">6</option> <option value="7">7</option> <option value="8">8</option> <option value="9">9</option> <option value="10">10</option>
        </select>
        <label for="minutes"></label>
        <select id="minutesBlock" name="minutes">
          <option value="0">Minutes</option> <option value="0">0</option>> <option value="5">5</option> <option value="10">10</option> <option value="15">15</option> <option value="20">20</option> <option value="25">25</option> <option value="30">30</option> <option value="35">35</option> <option value="40">40</option> <option value="45">45</option> <option value="50">50</option> <option value="55">55</option>
        </select>

      <div class="clearfix">
        <button type="button" onclick="document.getElementById('createNewBlockButton').style.display='none'" class="cancelbtn">Cancel</button>
        <button type="submit" class="createbtn">Create</button>
      </div>
    </div>
  </form>
</div>
<script>
          window.addEventListener('click', function(event){
            if (!event.target.matches('.modal-content') && !event.target.matches("#mainbtn1") && event.target.matches(".modal")) {
              document.getElementById("createNewBlockButton").style.display = 'none';
            }
          });

    function displayForm1(dateInput){
        var formDate1 = document.getElementById("dateBlock");
        formDate1.value = dateInput;
        document.getElementById('createNewBlockButton').style.display='block';
    }
</script>

<br><br>

  <div id="calendar">
    <div id="calendar_weekdays"></div>
    <div id="calendar_content"></div>
  </div>

  <input type="hidden" id="sessionUserID" value="${sessionUser.userID}">
  <input type="hidden" id="sessionUserTeacher" value="${sessionUser.isTeacher}">
  <input type="hidden" id="mondayLength" value="<%= request.getAttribute("mondayLength") %>">
  <input type="hidden" id="tuesdayLength" value="<%= request.getAttribute("tuesdayLength") %>">
  <input type="hidden" id="wednesdayLength" value="<%= request.getAttribute("wednesdayLength") %>">
  <input type="hidden" id="thursdayLength" value="<%= request.getAttribute("thursdayLength") %>">
  <input type="hidden" id="fridayLength" value="<%= request.getAttribute("fridayLength") %>">
  <input type="hidden" id="saturdayLength" value="<%= request.getAttribute("saturdayLength") %>">
  <input type="hidden" id="sundayLength" value="<%= request.getAttribute("sundayLength") %>">
  <table>
      <tbody>
          <c:forEach items="${mondayLessons}" var="lesson" varStatus="loop">
              <tr>
                  <td>
                      <input type="hidden" id="firstName_0${loop.index}" value="${lesson.userFirstName}">
                      <input type="hidden" id="lastName_0${loop.index}" value="${lesson.userLastName}">
                      <input type="hidden" id="time_0${loop.index}" value="${lesson.lessonTime}">
                      <input type="hidden" id="duration_0${loop.index}" value="${lesson.lDuration}">
                      <input type="hidden" id="approved_0${loop.index}" value="${lesson.isApproved}">
                      <input type="hidden" id="address_0${loop.index}" value="${lesson.userAddress}">
                      <input type="hidden" id="contact_0${loop.index}" value="${lesson.userContact}">
                      <input type="hidden" id="date_0${loop.index}" value="${lesson.lessonDate}">
                  </td>
              </tr>
          </c:forEach>
      </tbody>
  </table>
  <table>
      <tbody>
          <c:forEach items="${tuesdayLessons}" var="lesson" varStatus="loop">
              <tr>
                  <td>
                      <input type="hidden" id="firstName_1${loop.index}" value="${lesson.userFirstName}">
                      <input type="hidden" id="lastName_1${loop.index}" value="${lesson.userLastName}">
                      <input type="hidden" id="time_1${loop.index}" value="${lesson.lessonTime}">
                      <input type="hidden" id="duration_1${loop.index}" value="${lesson.lDuration}">
                      <input type="hidden" id="approved_1${loop.index}" value="${lesson.isApproved}">
                      <input type="hidden" id="address_1${loop.index}" value="${lesson.userAddress}">
                      <input type="hidden" id="contact_1${loop.index}" value="${lesson.userContact}">
                      <input type="hidden" id="date_1${loop.index}" value="${lesson.lessonDate}">
                  </td>
              </tr>
          </c:forEach>
      </tbody>
  </table>
  <table>
      <tbody>
          <c:forEach items="${wednesdayLessons}" var="lesson" varStatus="loop">
              <tr>
                  <td>
                        <input type="hidden" id="firstName_2${loop.index}" value="${lesson.userFirstName}">
                        <input type="hidden" id="lastName_2${loop.index}" value="${lesson.userLastName}">
                        <input type="hidden" id="time_2${loop.index}" value="${lesson.lessonTime}">
                        <input type="hidden" id="duration_2${loop.index}" value="${lesson.lDuration}">
                        <input type="hidden" id="approved_2${loop.index}" value="${lesson.isApproved}">
                        <input type="hidden" id="address_2${loop.index}" value="${lesson.userAddress}">
                        <input type="hidden" id="contact_2${loop.index}" value="${lesson.userContact}">
                        <input type="hidden" id="date_2${loop.index}" value="${lesson.lessonDate}">
                  </td>
              </tr>
          </c:forEach>
      </tbody>
  </table>
  <table>
      <tbody>
          <c:forEach items="${thursdayLessons}" var="lesson" varStatus="loop">
              <tr>
                  <td>
                        <input type="hidden" id="firstName_3${loop.index}" value="${lesson.userFirstName}">
                        <input type="hidden" id="lastName_3${loop.index}" value="${lesson.userLastName}">
                        <input type="hidden" id="time_3${loop.index}" value="${lesson.lessonTime}">
                        <input type="hidden" id="duration_3${loop.index}" value="${lesson.lDuration}">
                        <input type="hidden" id="approved_3${loop.index}" value="${lesson.isApproved}">
                        <input type="hidden" id="address_3${loop.index}" value="${lesson.userAddress}">
                        <input type="hidden" id="contact_3${loop.index}" value="${lesson.userContact}">
                        <input type="hidden" id="date_3${loop.index}" value="${lesson.lessonDate}">

                  </td>
              </tr>
          </c:forEach>
      </tbody>
  </table>
  <table>
      <tbody>
          <c:forEach items="${fridayLessons}" var="lesson" varStatus="loop">
              <tr>
                  <td>
                        <input type="hidden" id="firstName_4${loop.index}" value="${lesson.userFirstName}">
                        <input type="hidden" id="lastName_4${loop.index}" value="${lesson.userLastName}">
                        <input type="hidden" id="time_4${loop.index}" value="${lesson.lessonTime}">
                        <input type="hidden" id="duration_4${loop.index}" value="${lesson.lDuration}">
                        <input type="hidden" id="approved_4${loop.index}" value="${lesson.isApproved}">
                        <input type="hidden" id="address_4${loop.index}" value="${lesson.userAddress}">
                        <input type="hidden" id="contact_4${loop.index}" value="${lesson.userContact}">
                        <input type="hidden" id="date_4${loop.index}" value="${lesson.lessonDate}">
                  </td>
              </tr>
          </c:forEach>
      </tbody>
  </table>

  <table>
        <tbody>
            <c:forEach items="${saturdayLessons}" var="lesson" varStatus="loop">
                <tr>
                    <td>
                        <input type="hidden" id="firstName_5${loop.index}" value="${lesson.userFirstName}">
                        <input type="hidden" id="lastName_5${loop.index}" value="${lesson.userLastName}">
                        <input type="hidden" id="time_5${loop.index}" value="${lesson.lessonTime}">
                        <input type="hidden" id="duration_5${loop.index}" value="${lesson.lDuration}">
                        <input type="hidden" id="approved_5${loop.index}" value="${lesson.isApproved}">
                        <input type="hidden" id="address_5${loop.index}" value="${lesson.userAddress}">
                        <input type="hidden" id="contact_5${loop.index}" value="${lesson.userContact}">
                        <input type="hidden" id="date_5${loop.index}" value="${lesson.lessonDate}">
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <table>
        <tbody>
            <c:forEach items="${sundayLessons}" var="lesson" varStatus="loop">
                <tr>
                    <td>
                        <input type="hidden" id="firstName_6${loop.index}" value="${lesson.userFirstName}">
                        <input type="hidden" id="lastName_6${loop.index}" value="${lesson.userLastName}">
                        <input type="hidden" id="time_6${loop.index}" value="${lesson.lessonTime}">
                        <input type="hidden" id="duration_6${loop.index}" value="${lesson.lDuration}">
                        <input type="hidden" id="approved_6${loop.index}" value="${lesson.isApproved}">
                        <input type="hidden" id="address_6${loop.index}" value="${lesson.userAddress}">
                        <input type="hidden" id="contact_6${loop.index}" value="${lesson.userContact}">
                        <input type="hidden" id="date_6${loop.index}" value="${lesson.lessonDate}">
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    </body>