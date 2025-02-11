function getDateStr(date) {
  var year = date.getFullYear();
  var month = date.getMonth() + 1;
  var day = date.getDate();
  return year + '-' + (month < 10 ? '0' : '') + month + '-' + (day < 10 ? '0' : '') + day;
}

function h(inputDate) {
  var day = new Date(inputDate);
  var todayYear = day.getFullYear();
  var todayMonth = day.getMonth();
  var todayDate = day.getDate();

  var week = [];

  for (var i = 0; i < 7; i++) {
    var date = new Date(todayYear, todayMonth, todayDate - day.getDay() + 1 + i); // add 1 to start from Monday
    week.push({ day: date.getDate(), weekday: [date.getDay()] });
  }

  return week;
}

$(function () {
  function c() {
    p();
    var inputDate = $('#dateinput').val();
    var e = h(inputDate); // Replace "2023-03-01" with the date you want to display
    var r = 0;
    var cal = false;
    l.empty();

    for (var c = 0; c < e.length; c++) {
      var v = e[c].day;
      var m = g(new Date(t, n - 1, v)) ? '<div class="today">' : "<div>";
      l.append("<div data-date='" + getDateStr(new Date(t, n - 1, v)) + "'>" + v + "</div>");
    }

    var lengthIndex = 0;
    var sessionUserID = $('#sessionUserID').val(); // get user id of session user for validation later
    var sessionUserTeacher = $('#sessionUserTeacher').val(); // check if user is a teacher for validation later
    const mondayLength = $('#mondayLength').val();
    const tuesdayLength = $('#tuesdayLength').val();
    const wednesdayLength = $('#wednesdayLength').val();
    const thursdayLength = $('#thursdayLength').val();
    const fridayLength = $('#fridayLength').val();
    const saturdayLength = $('#saturdayLength').val();
    const sundayLength = $('#sundayLength').val();
    while ((lengthIndex < mondayLength) || (lengthIndex < tuesdayLength) || (lengthIndex < wednesdayLength) || (lengthIndex < thursdayLength) ||
    (lengthIndex < fridayLength) || (lengthIndex < saturdayLength) || (lengthIndex < sundayLength)){ // iterate through the dayLessons until the last lesson (max length)
        var c = 0;
        for (var c = 0; c < e.length; c++){ // iterate through the days of the week
            var firstName = $('#firstName_'+c+lengthIndex).val();
            var lastName = $('#lastName_'+c+lengthIndex).val();
            var startTime = $('#time_'+c+lengthIndex).val();
            var lessonDate = $('#date_'+c+lengthIndex).val();
            var lessonUserID = $('#userID_'+c+lengthIndex).val();
            var lessonDuration = $('#duration_'+c+lengthIndex).val();
            var isApproved = $('#approved_'+c+lengthIndex).val();
            var endTime;
            var userAddress = $('#address_'+c+lengthIndex).val();
            var userContact = $('#contact_'+c+lengthIndex).val();
            if (sessionUserTeacher == 1){ // if user is a teacher
                if (firstName == null){ // non-existent lesson
                     l.append(`<div id="blank-box"></div>`);  // add transparent box in between lessons
                 } else if (isApproved == 1){ // approved student lesson
                     var start = new Date(`${lessonDate} ${startTime}`); // get the start time as a date
                     var end = new Date(start.getTime() + lessonDuration * 60000); // end time is the time + duration in minutes
                     var hours = end.getHours().toString().padStart(2, '0');
                     var minutes = end.getMinutes().toString().padStart(2, '0');
                     var endTime = `${hours}:${minutes}`; // display end time in HH:MM format
                     // add on purple lesson box showing lesson
                     l.append(`<div id="lesson-box"><b>${firstName} ${lastName}</b><br>${startTime} - ${endTime}<br>${userAddress}<br> ${userContact}</div>`);
                 } else if (isApproved == 0){ // unapproved student lesson
                     var start = new Date(`${lessonDate} ${startTime}`);
                     var end = new Date(start.getTime() + lessonDuration * 60000);
                     var hours = end.getHours().toString().padStart(2, '0');
                     var minutes = end.getMinutes().toString().padStart(2, '0');
                     var endTime = `${hours}:${minutes}`; // display end time in HH:MM format
                     // append pink lessons box showing lesson details
                     l.append(`<div id="lesson-box1"><b>${firstName} ${lastName}</b><br>${startTime} - ${endTime}<br>${userAddress}<br> ${userContact}</div>`);
                 } else if (isApproved == 2){ // if it is a blocked lessons
                     var start = new Date(`${lessonDate} ${startTime}`);
                     var end = new Date(start.getTime() + lessonDuration * 60000);
                     var hours = end.getHours().toString().padStart(2, '0');
                     var minutes = end.getMinutes().toString().padStart(2, '0');
                     var endTime = `${hours}:${minutes}`;
                     // append blue block with only time (name Block just for video Crit D)
                     l.append(`<div id="lesson-box2"><b></b><br>${startTime} - ${endTime}</div>`);
                 }
            } else if (sessionUserTeacher != 1){ // if the user is a student
                if (firstName == null ){ // if the lesson does not exist
                l.append(`<div id="blank-box"></div>`); // transparent lesson box
                } else if (isApproved == 1){ // for approved lessons
                    if (sessionUserID == lessonUserID){ // if the user on the site owns the lessons, show details
                        var start = new Date(`${lessonDate} ${startTime}`);
                        var end = new Date(start.getTime() + lessonDuration * 60000);
                        var hours = end.getHours().toString().padStart(2, '0');
                        var minutes = end.getMinutes().toString().padStart(2, '0');
                        var endTime = `${hours}:${minutes}`;
                        // append purple box with details
                        l.append(`<div id="lesson-box"><b>${firstName} ${lastName}</b><br>${startTime} - ${endTime}<br>${userAddress}<br> ${userContact}</div>`);
                    } else if (sessionUserID != lessonUserID){ // if the user does not match the lesson's user, hide details
                        var start = new Date(`${lessonDate} ${startTime}`);
                        var end = new Date(start.getTime() + lessonDuration * 60000);
                        var hours = end.getHours().toString().padStart(2, '0');
                        var minutes = end.getMinutes().toString().padStart(2, '0');
                        var endTime = `${hours}:${minutes}`;
                        // append purple box without details
                        l.append(`<div id="lesson-box">${startTime} - ${endTime}</div>`);
                    }
                } else if (isApproved != 1){ // unapproved or blocked lesson
                    if (sessionUserID == lessonUserID){ // if the user on the site owns the lessons, show details
                        var start = new Date(`${lessonDate} ${startTime}`);
                        var end = new Date(start.getTime() + lessonDuration * 60000);
                        var hours = end.getHours().toString().padStart(2, '0');
                        var minutes = end.getMinutes().toString().padStart(2, '0');
                        var endTime = `${hours}:${minutes}`;
                        // append pink box with details for unapproved
                        l.append(`<div id="lesson-box1"><b>${firstName} ${lastName}</b><br>${startTime} - ${endTime}<br>${userAddress}<br> ${userContact}</div>`);
                    } else if (sessionUserID != lessonUserID){ // if the user does not match the lesson's user, hide details
                        var start = new Date(`${lessonDate} ${startTime}`);
                        var end = new Date(start.getTime() + lessonDuration * 60000);
                        var hours = end.getHours().toString().padStart(2, '0');
                        var minutes = end.getMinutes().toString().padStart(2, '0');
                        var endTime = `${hours}:${minutes}`;
                        // append purple box without details for blocked and unapproved
                        l.append(`<div id="lesson-box">${startTime} - ${endTime}</div>`);
                    }
                }
            }
        }
        lengthIndex = lengthIndex + 1; // add to lengthIndex until while loop breaks
    }
 // calendar code taken from internet (codepen — https://codepen.io/B8bop)
    var y = o[n - 1];
    a.css("background-color", y)
      .find("h1")
      .text(i[n - 1] + " " + t);
    f.find("div").css("color", y);
    l.find(".today").css("background-color", y);
    d();
  }
    function p() {
      f.empty();
      for (var e = 0; e < 7; e++) {
        f.append("<div>" + s[e].substring(0, 3) + "</div>");
      }
    }
    function d() {
      var t;
      var n = $("#calendar").css("width", e + "px");
      n.find((t = "#calendar_weekdays, #calendar_content"))
        .css("width", e + "px")
        .find("div")
        .css({
          width: e/7 + "px",
          height: 2.5 + "em",
          "line-height": 40 + "px"
        });
      n.find("#calendar_header")
        .css({ height: e*(1/15) + "px" })
        .find('i[class^="icon-chevron"]')
        .css("line-height", e*(1/15) + "px");
    }
    function v(e, t) {
      return new Date(e, t, 0).getDate();
    }
    function m(e, t, n) {
      return new Date(e, t - 1, n).getDay();
    }
    function g(e) {
      return y(new Date()) == y(e);
    }
    function y(e) {
        return e.getFullYear() + "/" + (e.getMonth() + 1) + "/" + e.getDate();
      }

    function b() {
      var e = new Date();
      t = e.getFullYear();
      n = e.getMonth() + 1;
    }
    var e = $(window).width();
    var t = 2013;
    var n = 9;
    var r = [];
    var i = [
      "JANUARY",
      "FEBRUARY",
      "MARCH",
      "APRIL",
      "MAY",
      "JUNE",
      "JULY",
      "AUGUST",
      "SEPTEMBER",
      "OCTOBER",
      "NOVEMBER",
      "DECEMBER"
    ];
    var s = [
      "Monday",
      "Tuesday",
      "Wednesday",
      "Thursday",
      "Friday",
      "Saturday",
      "Sunday"
    ];
    var o = [
      "#FFFFFF"
    ];
    var cal = $("#calendar");
    var a = cal.find("#calendar_header");
    var f = cal.find("#calendar_weekdays");
    var l = cal.find("#calendar_content");
    b();
    c();

  });