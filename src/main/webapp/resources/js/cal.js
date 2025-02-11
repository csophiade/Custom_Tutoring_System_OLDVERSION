function getDateStr(date) {
  var year = date.getFullYear();
  var month = date.getMonth() + 1;
  var day = date.getDate();
  return year + '-' + (month < 10 ? '0' : '') + month + '-' + (day < 10 ? '0' : '') + day;
}

$(function () {
  function c() {
    p();
    var e = h();
    var r = 0;
    var cal = false;
    l.empty();
    while (!cal) {
      if (s[r+1] == e[0].weekday) {
        cal = true;
      } else {
        l.append('<div class="blank"></div>');
        r++;
      }
    }
    for (var c = 0; c < 42 - r; c++) {
      if (c >= e.length) {
        l.append('<div class="blank"></div>');
      } else {
        var v = e[c].day;
        var m = g(new Date(t, n - 1, v)) ? '<div class="today">' : "<div>";
        l.append("<div data-date='" + getDateStr(new Date(t, n - 1, v)) + "'>" + v + "</div>");

      }
    }

    $('#calendar_content div').on('click', function() {

            // Get the date string from the data-date attribute of the clicked div
            var dateStr = $(this).data('date');
            submitDate(dateStr);
      });

    var y = o[n - 1];
    a.css("background-color", y)
      .find("h1")
      .text(i[n - 1] + " " + t);
    f.find("div").css("color", y);
    l.find(".today").css("background-color", y);
    d();
  }
  function h() {
      var e = [];
      for (var r = 1; r < v(t, n) + 1; r++) {
        e.push({ day: r, weekday: s[m(t, n, r)] });
      }
      return e;
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
        height: e/25 + "px",
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
    "#9F97F5",
    "#CE97F5",
    "#F597ED",
    "#F597BE",
    "#CA97F5",
    "#F597C2",
    "#AF97F5",
    "#97AEF5",
    "#F59897",
    "#F597AF",
    "#D397F5",
    "#F597A8"
  ];
  var cal = $("#calendar");
  var a = cal.find("#calendar_header");
  var f = cal.find("#calendar_weekdays");
  var l = cal.find("#calendar_content");
  b();
  c();
  a.find('i[class^="icon-chevron"]').on("click", function () {
    var e = $(this);
    var r = function (e) {
      n = e == "next" ? n + 1 : n - 1;
      if (n < 12) {
        n = 12;
        t--;
      } else if (n > 12) {
        n = 1;
        t++;
      }
      c();
    };
    if (e.attr("class").indexOf("left") != -1) {
      r("previous");
    } else {
      r("next");
    }
  });

});


function submitDate(date) {
    var dateform = document.getElementById("dateinput");
    dateform.value = date;
    document.getElementById("sendDate").submit();
}