<title> Welcome - Home </title>
<%@ page import="javax.servlet.jsp.jstl.core.LoopTagStatus" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<style><%@include file="/resources/css/homepageStudent.css"%></style>
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
<div id="homeinfo">
    <div id="todayclmn" >
        <h1 id="today" style="font-family:verdana; font-size: 35px; color:white;">Upcoming Lessons</h1>

        <table>
                    <tbody>
                        <c:forEach items="${unapprovedLessons}" var="lesson" varStatus="loop">
                            <tr>
                                <td>
                                    <div class="lesson-box1">

                                    <h3 class="text1" >Date: </h3>
                                    <p class="text1" id="lessonDate${loop.index}">${lesson.lessonDate}</p><br><br>
                                    <script>
                                      const lessonDate${loop.index} = new Date(document.querySelector("#lessonDate${loop.index}").innerHTML);
                                      const options0${loop.index} = { year: 'numeric', month: '2-digit', day: '2-digit' };
                                      document.querySelector("#lessonDate${loop.index}").innerHTML = lessonDate${loop.index}.toLocaleDateString('en-GB', options0${loop.index});
                                    </script>
                                    <h3 class="text1" >Time: </h3>
                                    <p class="text1" id="lessonTime${loop.index}">${lesson.lessonTime}</p>
                                    <h2 style="display: none;" id="lDuration${loop.index}">${lesson.lDuration}</h2>
                                    <p class="text1"> - </p><p class="text1" id="lessonEndTime${loop.index}"></p> <br>

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

                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>


        <table>
            <tbody>
                <c:forEach items="${approvedLessonsUpcoming}" var="lesson" varStatus="loop">
                    <tr>
                        <td>
                            <div class="lesson-box">
                            <h3 class="text1" >Date: </h3>
                            <p class="text1" id="lessonDate1${loop.index}">${lesson.lessonDate}</p><br><br>
                            <script>
                              const lessonDate1${loop.index} = new Date(document.querySelector("#lessonDate1${loop.index}").innerHTML);
                              const options2${loop.index} = { year: 'numeric', month: '2-digit', day: '2-digit' };
                              document.querySelector("#lessonDate1${loop.index}").innerHTML = lessonDate1${loop.index}.toLocaleDateString('en-GB', options2${loop.index});
                            </script>
                            <h3 class="text1" >Time: </h3>
                            <p class="text1" id="lessonTime1${loop.index}">${lesson.lessonTime}</p>
                            <h2 style="display: none;" id="lDuration1${loop.index}">${lesson.lDuration}</h2>
                            <p class="text1"> - </p><p class="text1" id="lessonEndTime1${loop.index}"></p> <br>

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

                            </div>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

</body>
        <body class="body1"></body>

    </div>


    <div id="tomorrowclmn">
        <h1 id="tomorrow" style="font-family:verdana; font-size: 35px; color:white;">Outstanding Balance</h1>
        <div class="user-box" id="balancer">
            <p class="text2" style="float:left;"><strong>$${balance}</strong> </p><br>
        </div><br><br><br><br>
        <h1 id="tomorrow" style="font-family:verdana; font-size: 35px; color:white;">Past Lessons</h1>
        <table id="pastlessons">
                  <tbody>
                      <c:forEach items="${approvedLessonsPast}" var="lesson" varStatus="loop">
                          <tr>
                              <td>
                                  <div class="lesson-box2">
                                  <h3 class="text1" >Date: </h3>
                                  <p class="text1" id="lessonDate2${loop.index}">${lesson.lessonDate}</p><br><br>
                                  <script>
                                    const lessonDate2${loop.index} = new Date(document.querySelector("#lessonDate2${loop.index}").innerHTML);
                                    const options4${loop.index} = { year: 'numeric', month: '2-digit', day: '2-digit' };
                                    document.querySelector("#lessonDate2${loop.index}").innerHTML = lessonDate2${loop.index}.toLocaleDateString('en-GB', options4${loop.index});
                                  </script>
                                  <h3 class="text1" >Time: </h3>
                                  <p class="text1" id="lessonTime2${loop.index}">${lesson.lessonTime}</p>
                                  <h2 style="display: none;" id="lDuration2${loop.index}">${lesson.lDuration}</h2>
                                  <p class="text1"> - </p><p class="text1" id="lessonEndTime2${loop.index}"></p> <br>

                                  <script>
                                    const startTime2${loop.index} = document.querySelector("#lessonTime2${loop.index}").innerHTML.split(':');
                                    console.log(startTime2${loop.index});
                                    const hours2${loop.index} = parseInt(startTime2${loop.index}[0], 10);
                                    console.log(hours2${loop.index});
                                    const minutes2${loop.index} = parseInt(startTime2${loop.index}[1], 10);
                                    console.log(minutes2${loop.index});
                                    const duration2${loop.index} = parseInt(document.querySelector("#lDuration2${loop.index}").innerHTML, 10);
                                    console.log(duration2${loop.index});

                                    let endTime2${loop.index} = new Date();
                                    endTime2${loop.index}.setHours(hours2${loop.index}, minutes2${loop.index} + duration2${loop.index}, 0, 0);
                                    console.log(endTime2${loop.index});

                                    if (endTime2${loop.index}.getMinutes() >= 60) {
                                      endTime2${loop.index}.setMinutes(endTime2${loop.index}.getMinutes() % 60);
                                      hours2${loop.index} = (hours2${loop.index} + Math.floor(duration2${loop.index} / 60)) % 24;
                                      endTime2${loop.index}.setHours(hours2${loop.index});
                                    }
                                    console.log(endTime2${loop.index});

                                    const options5${loop.index} = { hour: 'numeric', minute: '2-digit',  hour12: false };
                                    document.querySelector("#lessonEndTime2${loop.index}").innerHTML = endTime2${loop.index}.toLocaleTimeString('en-GB', options5${loop.index});
                                  </script>

                                  </div>
                              </td>
                          </tr>
                      </c:forEach>
                  </tbody>
            </table>

    </div>


</div>