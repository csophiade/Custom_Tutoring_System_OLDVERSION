<title> Welcome - Home </title>
<%@ page import="javax.servlet.jsp.jstl.core.LoopTagStatus" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<style><%@include file="/resources/css/homepageTeacher.css"%></style>
<style><%@include file="/resources/css/home.css"%></style>
<style><%@include file="/resources/js/home.js"%></style>
<body id="background" style="background-color:lavender"></body>

<body>
<header id="header" class="header" style="background-color:#c9b9e6">
    <p id="home">Home</p>
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
<div id="homeinfo">
    <div id="todayclmn" >
        <h1 id="today" style="font-family:verdana; font-size: 40px; color:white;">Today</h1>
        <a id="bookingreqbutton" href="booking-info">${unapprovedLessonLength} New Booking Request(s)</a>

        <table>
            <tbody>
                <c:forEach items="${approvedLessonsToday}" var="lesson" varStatus="loop">
                    <tr>
                        <td>
                            <div class="lesson-box">
                            <h2>${lesson.userFirstName} ${lesson.userLastName}</h2>
                            <h3 style="display: none;" class="text" id="lessonDate${loop.index}">${lesson.lessonDate}</h3> <br>
                            <script>
                              const lessonDate${loop.index} = new Date(document.querySelector("#lessonDate${loop.index}").innerHTML);
                              const options${loop.index} = { year: 'numeric', month: '2-digit', day: '2-digit' };
                              document.querySelector("#lessonDate${loop.index}").innerHTML = lessonDate${loop.index}.toLocaleDateString('en-GB', options${loop.index});
                            </script>

                            <h3 class="text" id="lessonTime${loop.index}">${lesson.lessonTime}</h3>
                            <h2 style="display: none;" id="lDuration${loop.index}">${lesson.lDuration}</h2>
                            <h3 class="text"> - </h1><h3 class="text" id="lessonEndTime${loop.index}"></h3> <br>

                            <script>
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

                            <h3 class="text">${lesson.userAddress}</h3> <br>
                            <h3 class="text">${lesson.userContact}</h3>

                            </div>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

</body>


        <body class="body1"></body>

    </div>
    <div id="tomorrowclmn" >
        <h1 id="tomorrow" style="font-family:verdana; font-size: 40px; color:white;">Tomorrow</h1>
        <table>
                    <tbody>
                        <c:forEach items="${approvedLessonsTomorrow}" var="lesson" varStatus="loop">
                            <tr>
                                <td>
                                    <div class="lesson-box">
                                    <h2>${lesson.userFirstName} ${lesson.userLastName}</h2>
                                    <h3 style="display: none;" class="text" id="lessonDate1${loop.index}">${lesson.lessonDate}</h3> <br>
                                    <script>
                                      const lessonDate1${loop.index} = new Date(document.querySelector("#lessonDate1${loop.index}").innerHTML);
                                      const options2${loop.index} = { year: 'numeric', month: '2-digit', day: '2-digit' };
                                      document.querySelector("#lessonDate1${loop.index}").innerHTML = lessonDate1${loop.index}.toLocaleDateString('en-GB', options2${loop.index});
                                    </script>

                                    <h3 class="text" id="lessonTime1${loop.index}">${lesson.lessonTime}</h3>
                                    <h2 style="display: none;" id="lDuration1${loop.index}">${lesson.lDuration}</h2>
                                    <h3 class="text"> - </h1><h3 class="text" id="lessonEndTime1${loop.index}"></h3> <br>

                                    <script>
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

                                      const options3${loop.index} = { hour: 'numeric', minute: '2-digit',  hour12: false };
                                      document.querySelector("#lessonEndTime1${loop.index}").innerHTML = endTime1${loop.index}.toLocaleTimeString('en-GB', options3${loop.index});
                                    </script>

                                    <h3 class="text">${lesson.userAddress}</h3> <br>
                                    <h3 class="text">${lesson.userContact}</h3>

                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
    </div>
</div>