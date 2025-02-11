<title> Bookings </title>
<%@ page import="javax.servlet.jsp.jstl.core.LoopTagStatus" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@page import="javax.imageio.ImageIO"%>
<style><%@include file="/resources/css/bookingInfo.css"%></style>
<style><%@include file="/resources/css/home.css"%></style>
<style><%@include file="/resources/js/home.js"%></style>
<body id="background" style="background-color:lavender"></body>

<body>
<header id="header" class="header" style="background-color:#c9b9e6">
    <p id="home">Bookings</p>
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
String message = (String) request.getAttribute("message");
if (message != null && message != "") {
%>
  <script>
  alert("${message}")</script>
<%
}
%>

<!--
<img src="resources/images/pfp.jpg" alt="pfp2"/>
<img src="https://raw.githubusercontent.com/mdn/learning-area/master/html/multimedia-and-embedding/images-in-html/dinosaur_small.jpg" alt="hotlink"/>
-->

<div id="approve">
<form action="searchLesson" id="form" role="search">
  <input type="search" id="query" name="q"
   placeholder="Search...">
  <button id="svg1"><svg viewBox="0 0 1024 1024"><path class="path1" d="M848.471 928l-263.059-263.059c-48.941 36.706-110.118 55.059-177.412 55.059-171.294 0-312-140.706-312-312s140.706-312 312-312c171.294 0 312 140.706 312 312 0 67.294-24.471 128.471-55.059 177.412l263.059 263.059-79.529 79.529zM189.623 408.078c0 121.364 97.091 218.455 218.455 218.455s218.455-97.091 218.455-218.455c0-121.364-103.159-218.455-218.455-218.455-121.364 0-218.455 97.091-218.455 218.455z"></path></svg></button>
</form>
<button id="mainbtn" onclick="displayForm(<%= request.getAttribute("studentList") %>, <%= request.getAttribute("studentListLength") %> )" style="width:auto;">Create New Lesson</button>

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

    function displayForm(studentList, studentListLength){
    const date = new Date();
            month = '' + (date.getMonth() + 1),
            day = '' + date.getDate(),
            year = date.getFullYear();

        if (month.length < 2)
            month = '0' + month;
        if (day.length < 2)
            day = '0' + day;
        console.log(year + '-' + month + '-' + day);
        const date1 = document.getElementById("lessonDate");
        date1.value = year + '-' + month + '-' + day;
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

<button id="mainbtn" onclick="document.getElementById('createNewBlockButton').style.display='block';" style="width:auto;">Create Block Time</button>
<div id="createNewBlockButton" class="modal">
  <form class="modal-content" action="createLesson">
    <div class="container">
     <span onclick="document.getElementById('createNewBlockButton').style.display='none'" class="close" title="Close Modal">&times;</span>
      <h1>Create Block Time</h1>
      <hr>

      <input type="hidden" id="username" value="Block" placeholder="Block" name="username" required>

      <label for="lessonDate"><b>Date</b></label>
      <input type="date" id="dateBlock" value="" name="lessonDate" required>

      <label for="lessonTime"><b>Time</b></label>
      <input type="time" id="timeBlock" value="09:00" name="lessonTime" required>

      <label for="hours"><b>Duration</b></label><br>
        <select id="hoursBlock" name="hours" required>
          <option value="0">Hours</option> <option value="0">0</option> <option value="1">1</option> <option value="2">2</option> <option value="3">3</option> <option value="4">4</option> <option value="5">5</option> <option value="6">6</option> <option value="7">7</option> <option value="8">8</option> <option value="9">9</option> <option value="10">10</option>
        </select>
        <label for="minutes"></label>
        <select id="minutesBlock" name="minutes">
          <option value="0">Minutes</option> <option value="0">0</option> <option value="5">5</option> <option value="10">10</option> <option value="15">15</option> <option value="20">20</option> <option value="25">25</option> <option value="30">30</option> <option value="35">35</option> <option value="40">40</option> <option value="45">45</option> <option value="50">50</option> <option value="55">55</option>
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

</script>
<br><br>
        <table>
            <tbody>
                <c:forEach items="${approvedLessons}" var="lesson" varStatus="loop">
                    <tr>
                        <td>
                            <div class="lesson-box1">

                            <div style="float:right;">
                            <button id="editbtn2" onclick="setFormValues(&quot;${lesson.lessonID}&quot;,&quot;${lesson.lessonDate}&quot;, &quot;${lesson.lessonTime}&quot;, &quot;${lesson.lDuration}&quot;, &quot;${lesson.isApproved}&quot;)">&#8226;&#8226;&#8226;</button>
                            <button style="float:right;" id="deletebtn2" onclick="deleteLesson(${lesson.lessonID})">&times</button>
                            </div> <br>

                            <form id="deleteLesson_${lesson.lessonID}" style="display:none;" action="deleteLesson">
                            <input type="number" id="deleteLesson-lessonID_${lesson.lessonID}" value="${lesson.lessonID}" name="deleteLesson-lessonID">
                            </form>

                            <div id="editLessonApprove" onclick="displayEdit(${lesson.lessonID})">
                            <div id="editLessonApprove_${lesson.lessonID}" class="modal2">
                            <form class="modal-content2" action="editLesson">
                              <div class="container2">
                               <span onclick="document.getElementById('editLessonApprove_${lesson.lessonID}').style.display='none'" class="close" title="Close Modal">&times;</span>
                                <h1>Edit Lesson</h1>
                                <hr>

                                <input type="number" id="editLessonApprove-lessonID_${lesson.lessonID}"  value="${lesson.lessonID}" name="editLessonApprove-lessonID" style="display:none;" required>
                                <input type="number" id="editLessonApprove-isApproved_${lesson.lessonID}" value="${lesson.isApproved}" name="editLessonApprove-isApproved" style="display:none;" required>

                                <label for="editLessonApprove-date"><b>Date</b></label>
                                <input type="date" id="editLessonApprove-date_${lesson.lessonID}" value="" name="editLessonApprove-date" required>

                                <label for="editLessonApprove-startTime"><b>Time</b></label>
                                <input type="time" id="editLessonApprove-startTime_${lesson.lessonID}" value="" name="editLessonApprove-startTime" required>

                                <label for="editLessonApprove-hours"><b>Lesson Duration</b></label><br>
                                <select id="editLessonApprove-hours_${lesson.lessonID}" class="hours" value="" name="editLessonApprove-hours" required>
                                  <option value="0">Hours</option> <option value="0">0</option> <option value="1">1</option> <option value="2">2</option> <option value="3">3</option> <option value="4">4</option> <option value="5">5</option> <option value="6">6</option> <option value="7">7</option> <option value="8">8</option> <option value="9">9</option> <option value="10">10</option>
                                </select>
                                <label for="editLessonApprove-minutes"></label>
                                <select id="editLessonApprove-minutes_${lesson.lessonID}" class="minutes" value="" name="editLessonApprove-minutes">
                                  <option value="0">Minutes</option> <option value="0">0</option> <option value="5">5</option> <option value="10">10</option> <option value="15">15</option> <option value="20">20</option> <option value="25">25</option> <option value="30">30</option> <option value="35">35</option> <option value="40">40</option> <option value="45">45</option> <option value="50">50</option> <option value="55">55</option>
                                </select>

                                <div class="clearfix">
                                  <button type="button" onclick="document.getElementById('editLessonApprove_'+${lesson.lessonID}).style.display='none'" class="cancelbtn">Cancel</button>
                                  <button type="submit" class="createbtn">Edit</button>

                                </div>
                              </div>
                            </form>
                            </div>
                            </div>
                            <h2>${lesson.userFirstName} ${lesson.userLastName}</h2>
                            <h3 style="display: none;" class="text1" id="lessonDate1${loop.index}">${lesson.lessonDate}</h3> <br>


                            <h3 class="text1" >Time: </h3>
                            <p class="text1" id="lessonTime1${loop.index}">${lesson.lessonTime}</p>
                            <h2 style="display: none;" id="lDuration1${loop.index}">${lesson.lDuration}</h2>
                            <p class="text1"> - </p>
                            <p class="text1" id="lessonEndTime1${loop.index}"></p> <br>

                            <h3 class="text1" >Date: </h3>
                            <p class="text1" id="lessonDate1${loop.index}">${lesson.lessonDate}</p><br>

                            <h3 class="text1" >Duration: </h3>
                            <p class="text1" id="lDuration1${loop.index}">${lesson.lDuration} minutes</p>
                            <p class="text1" id="normalDuration${loop.index}">(${lesson.userDuration})</p><br>

                            <h3 class="text1" >Address: </h3>
                            <p class="text1">${lesson.userAddress}</p> <br>

                            <h3 class="text1" ><strong>Contact: </strong></h3>
                            <p class="text1">${lesson.userContact}</p>
                            </div>
                            </div>

                            <script>
                            const lessonDate1${loop.index} = new Date(document.querySelector("#lessonDate1${loop.index}").innerHTML);
                            const options00${loop.index} = { year: 'numeric', month: '2-digit', day: '2-digit' };
                            document.querySelector("#lessonDate1${loop.index}").innerHTML = lessonDate1${loop.index}.toLocaleDateString('en-GB', options00${loop.index});
                            const startTime1${loop.index} = document.querySelector("#lessonTime1${loop.index}").innerHTML.split(':');
                            console.log(startTime1${loop.index});
                            const hours1${loop.index} = parseInt(startTime1${loop.index}[0], 10);
                            console.log(hours1${loop.index});
                            const minutes1${loop.index} = parseInt(startTime1${loop.index}[1], 10);
                            console.log(minutes1${loop.index});
                            const duration1${loop.index} = parseInt(document.querySelector("#lDuration1${loop.index}").innerHTML, 10);
                            console.log(duration1${loop.index});

                            let endTime1${loop.index} = new Date();
                            endTime1${loop.index}.setHours(hours1${loop.index}, minutes1${loop.index} + duration1${loop.index}, 0, 0);
                            console.log(endTime1${loop.index});

                            if (endTime1${loop.index}.getMinutes() >= 60) {
                              endTime1${loop.index}.setMinutes(endTime1${loop.index}.getMinutes() % 60);
                              hours1${loop.index} = (hours1${loop.index} + Math.floor(duration1${loop.index} / 60)) % 24;
                              endTime1${loop.index}.setHours(hours1${loop.index});
                            }
                            console.log(endTime1${loop.index});

                            const options01${loop.index} = { hour: 'numeric', minute: '2-digit',  hour12: false };
                            document.querySelector("#lessonEndTime1${loop.index}").innerHTML = endTime1${loop.index}.toLocaleTimeString('en-GB', options01${loop.index});

                            </script>

                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

</div>


<div id="unapprove">
<div style="height: 50px;"></div>

        <table>
            <tbody>
                <c:forEach items="${unapprovedLessons}" var="lesson" varStatus="loop">
                    <tr>
                        <td>
                            <div class="lesson-box1" id="unapproved">
                            <div id="trbuttons">

                                <form id="approveLesson_${lesson.lessonID}" style="display:none;" action="approveLesson">
                                <input type="number" id="approveLesson-lessonID_${lesson.lessonID}" value="${lesson.lessonID}" name="approveLesson-lessonID">
                                </form>
                                <button id="acceptbtn" onclick="submitForm(${lesson.lessonID})">&#10004;</button>
                                <button id="editbtn" onclick="setFormValues1(&quot;${lesson.lessonID}&quot;, &quot;${lesson.lessonDate}&quot;, &quot;${lesson.lessonTime}&quot;, &quot;${lesson.lDuration}&quot;, &quot;${lesson.isApproved}&quot;)">&#8226;&#8226;&#8226;</button>
                                <button id="deletebtn" onclick="deleteLesson(${lesson.lessonID})">&times</button>


                                <div id="editLessonApprove1" onclick="displayEdit1(${lesson.lessonID})">
                                <div id="editLessonApprove1_${lesson.lessonID}" class="modal2">
                                <form class="modal-content2" action="editLesson">
                                  <div class="container2">
                                   <span onclick="document.getElementById('editLessonApprove1_${lesson.lessonID}').style.display='none'" class="close" title="Close Modal">&times;</span>
                                    <h1>Edit Lesson</h1>
                                    <hr>

                                    <input type="number" id="editLessonApprove1-lessonID_${lesson.lessonID}" value="${lesson.lessonID}" name="editLessonApprove-lessonID" style="display:none;" required>
                                    <input type="number" id="editLessonApprove1-isApproved_${lesson.lessonID}"  value="${lesson.isApproved}" name="editLessonApprove-isApproved" style="display:none;" required>

                                    <br>
                                    <label for="editLessonApprove-date"><b>Date</b></label>
                                    <input type="date" id="editLessonApprove1-date_${lesson.lessonID}" value="" name="editLessonApprove-date" required>

                                    <label for="editLessonApprove-startTime"><b>Time</b></label>
                                    <input type="time" id="editLessonApprove1-startTime_${lesson.lessonID}" value="" name="editLessonApprove-startTime" required>

                                    <label for="editLessonApprove-hours"><b>Lesson Duration</b></label><br>
                                    <select id="editLessonApprove1-hours_${lesson.lessonID}" class="hours" value="" name="editLessonApprove-hours" required>
                                      <option value="0">Hours</option> <option value="0">0</option> <option value="1">1</option> <option value="2">2</option> <option value="3">3</option> <option value="4">4</option> <option value="5">5</option> <option value="6">6</option> <option value="7">7</option> <option value="8">8</option> <option value="9">9</option> <option value="10">10</option>
                                    </select>
                                    <label for="editLessonApprove-minutes"></label>
                                    <select id="editLessonApprove1-minutes_${lesson.lessonID}" class="minutes" value="" name="editLessonApprove-minutes">
                                      <option value="0">Minutes</option> <option value="0">0</option> <option value="5">5</option> <option value="10">10</option> <option value="15">15</option> <option value="20">20</option> <option value="25">25</option> <option value="30">30</option> <option value="35">35</option> <option value="40">40</option> <option value="45">45</option> <option value="50">50</option> <option value="55">55</option>
                                    </select>

                                    <div class="clearfix">
                                      <button type="button" onclick="document.getElementById('editLessonApprove1_'+${lesson.lessonID}).style.display='none'" class="cancelbtn">Cancel</button>
                                      <button type="submit" class="createbtn">Edit</button>

                                    </div>
                                  </div>
                                </form>
                                </div>
                                </div>

                                <form id="deleteLesson_${lesson.lessonID}" style="display:none;" action="deleteLesson">
                                <input type="number" id="deleteLesson-lessonID_${lesson.lessonID}" value="${lesson.lessonID}" name="deleteLesson-lessonID">
                                </form>

                            </div><br>

                            <h2>${lesson.userFirstName} ${lesson.userLastName}</h2>

                            <h3 style="display: none;" class="text1" id="lessonDate${loop.index}">${lesson.lessonDate}</h3> <br>


                            <h3 class="text1" >Time: </h3><p class="text1" id="lessonTime${loop.index}">${lesson.lessonTime}</p>
                            <h2 style="display: none;" id="lDuration${loop.index}">${lesson.lDuration}</h2>
                            <p class="text1"> - </p><p class="text1" id="lessonEndTime${loop.index}"></p> <br>

                            <h3 class="text1" >Date: </h3>
                            <p class="text1" id="lessonDate${loop.index}">${lesson.lessonDate}</p><br>

                            <h3 class="text1" >Duration: </h3>
                            <p class="text1" id="lDuration1${loop.index}">${lesson.lDuration} minutes</p>
                            <p class="text1" id="normalDuration${loop.index}">(${lesson.userDuration})</p><br>

                            <h3 class="text1" >Address: </h3>
                            <p class="text1">${lesson.userAddress}</p> <br>

                            <h3 class="text1" ><strong>Contact: </strong></h3>
                            <p class="text1">${lesson.userContact}</p>

                            </div>

                            <script>
                             const lessonDate${loop.index} = new Date(document.querySelector("#lessonDate${loop.index}").innerHTML);
                             const options${loop.index} = { year: 'numeric', month: '2-digit', day: '2-digit' };
                             document.querySelector("#lessonDate${loop.index}").innerHTML = lessonDate${loop.index}.toLocaleDateString('en-GB', options${loop.index});

                             const startTime${loop.index} = document.querySelector("#lessonTime${loop.index}").innerHTML.split(':');
                             console.log(startTime${loop.index});
                             const hours${loop.index} = parseInt(startTime${loop.index}[0], 10);
                             console.log(hours${loop.index});
                             const minutes${loop.index} = parseInt(startTime${loop.index}[1], 10);
                             console.log(minutes${loop.index});
                             const duration${loop.index} = parseInt(document.querySelector("#lDuration${loop.index}").innerHTML, 10);
                             console.log(duration${loop.index});

                             let endTime${loop.index} = new Date();
                             endTime${loop.index}.setHours(hours${loop.index}, minutes${loop.index} + duration${loop.index}, 0, 0);
                             console.log(endTime${loop.index});

                             if (endTime${loop.index}.getMinutes() >= 60) {
                               endTime${loop.index}.setMinutes(endTime${loop.index}.getMinutes() % 60);
                               hours${loop.index} = (hours${loop.index} + Math.floor(duration${loop.index} / 60)) % 24;
                               endTime${loop.index}.setHours(hours${loop.index});
                             }
                             console.log(endTime${loop.index});

                             const options1${loop.index} = { hour: 'numeric', minute: '2-digit',  hour12: false };
                             document.querySelector("#lessonEndTime${loop.index}").innerHTML = endTime${loop.index}.toLocaleTimeString('en-GB', options1${loop.index});

                             </script>

                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
</div>

<script>
function submitForm(lessonID){
    document.getElementById("approveLesson_"+lessonID).submit();
}

function deleteLesson(lessonID){
    document.getElementById("deleteLesson_"+lessonID).submit();
}

function setFormValues(lessonID, date, time, duration, isApproved){
console.log("lessonID" + lessonID);
        const dateObj = new Date();
        const [hours1, minutes1] = time.split(':');
        dateObj.setHours(hours1, minutes1);
        const formattedTime = dateObj.toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' });

    var Lessonid = document.getElementById("editLessonApprove-lessonID_"+lessonID);
    var Date1 = document.getElementById("editLessonApprove-date_"+lessonID);
    var Time = document.getElementById("editLessonApprove-startTime_"+lessonID);
    var minutes = document.getElementById("editLessonApprove-minutes_"+lessonID);
    var hours = document.getElementById("editLessonApprove-hours_"+lessonID);
    var isapproved = document.getElementById("editLessonApprove-isApproved_"+lessonID);
    <c:forEach var="lesson" items="${approvedLessons}">
        Date1.value = date;
        Date1.placeholder = date;
        Time.value = time;
        Time.placeholder = time;
        minutes.value = duration%60;
        hours.value = (duration-(duration%60))/60;
        minutes.placeholder = duration%60;
        hours.placeholder = (duration-(duration%60))/60;
    </c:forEach>
    document.getElementById('editLessonApprove_'+lessonID).style.display='block';
    console.log("11");
}

function setFormValues1(lessonID, date, time, duration, isApproved){

        const dateObj = new Date();
        const [hours1, minutes1] = time.split(':');
        dateObj.setHours(hours1, minutes1);
        const formattedTime = dateObj.toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' });

    var Lessonid = document.getElementById("editLessonApprove1-lessonID_"+lessonID);
    var Date1 = document.getElementById("editLessonApprove1-date_"+lessonID);
    var Time = document.getElementById("editLessonApprove1-startTime_"+lessonID);
    var minutes = document.getElementById("editLessonApprove1-minutes_"+lessonID);
    var hours = document.getElementById("editLessonApprove1-hours_"+lessonID);
    var isapproved = document.getElementById("editLessonApprove1-isApproved_"+lessonID);
    <c:forEach var="lesson" items="${unapprovedLessons}">
        Date1.value = date;
        Date1.placeholder = date;
        Time.value = time;
        Time.placeholder = time;
        minutes.value = duration%60;
        hours.value = (duration-(duration%60))/60;
        minutes.placeholder = duration%60;
        hours.placeholder = (duration-(duration%60))/60;
    </c:forEach>
    document.getElementById('editLessonApprove1_'+lessonID).style.display='block';
    console.log("15");
}

function displayEdit(lessonID){
    window.addEventListener('click', function(event){
    console.log("2");
        if (!event.target.matches('.modal-content2') && !event.target.matches("#editbtn") && event.target.matches(".modal2")) {
            document.getElementById('editLessonApprove_'+lessonID).style.display='none';
            console.log("22");
        }
    });
}


</script>
</body>