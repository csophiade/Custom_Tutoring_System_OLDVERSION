<title> Students </title>
<%@ page import="javax.servlet.jsp.jstl.core.LoopTagStatus" %>
<style><%@include file="/resources/css/studentInfo.css"%></style>
<style><%@include file="/resources/css/home.css"%></style>
<style><%@include file="/resources/js/home.js"%></style>
<style><%@include file="/resources/js/studentInfo.js"%></style>


<body>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<body id="background" style="background-color:lavender" class="body1"></body>

<body>
<header id="header" class="header" style="background-color:#c9b9e6">
    <p id="home" >Students</p>
    <div>
    <div id="dropdown" style="background-color: transparent">
        <button class="dropdownbutton" onclick="myFunction1()">
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

    function myFunction1() {
          document.getElementById("myDropdown").classList.toggle("show");
        }

        window.addEventListener('click', function(event){
          if (!event.target.matches('.dropdownbutton') && !event.target.matches('.dropdown-content')) {
            var dropdowns = document.getElementsByClassName("dropdown-content");
            for (var i = 0; i < dropdowns.length; i++) {
              var openDropdown = dropdowns[i];
              if (openDropdown.classList.contains('show')) {
                openDropdown.classList.remove('show');
              }
            }
          }
        })
        function redirect(page){
            window.location.href = page;
        }
    </script>

</body>


<body class="body1">
<%
String message = (String) request.getAttribute("message");
if (message != null && message != "") {
%>
  <script>
  alert("${message}")</script>
<%
}
%>


<button id="mainbtn" onclick="document.getElementById('createNewStudentButton').style.display='block'" style="width:auto;">Create New Student</button>
<div id="createNewStudentButton" class="modal">
  <form class="modal-content" action="createStudent">
    <div class="container">
     <span onclick="document.getElementById('createNewStudentButton').style.display='none'" class="close" title="Close Modal">&times;</span>
      <h1>Create New Student</h1>
      <hr>

      <label for="firstName"><b>First Name</b></label>
      <input type="text" placeholder="First Name" name="firstName" required>

      <label for="lastName"><b>Last Name</b></label>
      <input type="text" placeholder="Last Name" name="lastName" required>

      <label for="username"><b>Username</b></label>
      <input type="text" placeholder="Username" name="username" required>

      <label for="password"><b>Password</b></label>
      <input type="password" placeholder="Password" name="password" required>

      <label for="address"><b>Address</b></label>
      <input type="text" placeholder="Address" name="address" required>

      <label for="phoneNumber"><b>Phone Number</b></label>
      <input type="tel" id="phoneNumber" name="phoneNumber" placeholder="12345555" pattern="[0-9]{4}[0-9]{4}" required>

      <label for="studentGrade"><b>Grade</b></label>
      <input type="text" placeholder="Grade" name="studentGrade" required>

      <label for="studentDoB"><b>DOB</b></label>
      <input type="date"  value="2023-01-01" name="studentDoB" required>

      <div id="tution">
      <label for="tuitionFee"><b>Tuition Fee</b></label><br>
      <input type="number" min="100" name="tuitionFee" placeholder="200" required>
      </div>
      <div id="balacne">
      <label for="balance"><b>Balance</b></label><br>
      <input type="number" min="0" name="balance" placeholder="0" required>
      </div><br>


      <label for="hours"><b>Lesson Duration</b></label><br>
        <select id="hours" name="hours" required>
          <option value="0">Hours</option> <option value="0">0</option> <option value="1">1</option> <option value="2">2</option> <option value="3">3</option> <option value="4">4</option> <option value="5">5</option> <option value="6">6</option> <option value="7">7</option> <option value="8">8</option> <option value="9">9</option> <option value="10">10</option>
        </select>
        <label for="minutes"></label>
        <select id="minutes" name="minutes" value="0">
          <option value="0">Minutes</option> <option value="0">0</option> <option value="5">5</option> <option value="10">10</option> <option value="15">15</option> <option value="20">20</option> <option value="25">25</option> <option value="30">30</option> <option value="35">35</option> <option value="40">40</option> <option value="45">45</option> <option value="50">50</option> <option value="55">55</option>
        </select>

      <br><label for="emailAddress"><b>Email Address</b></label>
      <input type="email" id="emailAddress" name="emailAddress" placeholder="name@website.com">

      <div class="clearfix">
        <button type="button" onclick="document.getElementById('createNewStudentButton').style.display='none'" class="cancelbtn">Cancel</button>
        <button type="submit" class="createbtn">Create</button>
      </div>
    </div>
  </form>
</div>

<form action="searchStudent" id="form" role="search">
  <input type="search" id="query" name="q"
   placeholder="Search...">
  <button id="svg1"><svg viewBox="0 0 1024 1024"><path class="path1" d="M848.471 928l-263.059-263.059c-48.941 36.706-110.118 55.059-177.412 55.059-171.294 0-312-140.706-312-312s140.706-312 312-312c171.294 0 312 140.706 312 312 0 67.294-24.471 128.471-55.059 177.412l263.059 263.059-79.529 79.529zM189.623 408.078c0 121.364 97.091 218.455 218.455 218.455s218.455-97.091 218.455-218.455c0-121.364-103.159-218.455-218.455-218.455-121.364 0-218.455 97.091-218.455 218.455z"></path></svg></button>
</form>

<script>
          window.addEventListener('click', function(event){
            if (!event.target.matches('.modal-content') && !event.target.matches("#mainbtn") && event.target.matches(".modal")) {
              document.getElementById("createNewStudentButton").style.display = 'none';
            }
          });
</script>
</body>

<body class="body1">

<table>
    <tbody>
        <c:forEach items="${users}" var="user">
            <tr>
                <td>
                    <div class="user-box">
                        <button id="editbtn" onclick="setFormValues(event, ${user.userID}, '${user.firstName}', '${user.lastName}', '${user.username}', '${user.password}', '${user.address}', '${user.contactNumber}', '${user.studentGrade}', '${user.studentDoB}', ${user.tuitionFee}, ${user.lessonDuration}, '${user.emailAddress}')">Edit</button>

                         <button id="viewbtn" onclick="setFormValues2(event, ${user.userID}, '${user.firstName}', '${user.lastName}', '${user.username}', '${user.password}', '${user.address}', '${user.contactNumber}', '${user.studentGrade}', '${user.studentDoB}', ${user.tuitionFee}, ${user.lessonDuration}, '${user.emailAddress}')">View</button>

                        <h2>${user.firstName} ${user.lastName}</h2>

                        <div id="editStudent" onclick="userID(${user.userID})">
                        <div id="editStudent_${user.userID}" class="modal2">
                                      <form class="modal-content2" action="editStudent">
                                        <div class="container2">
                                         <span onclick="document.getElementById('editStudent_${user.userID}').style.display='none'" class="close" title="Close Modal">&times;</span>
                                          <h1>Edit Student</h1>
                                          <hr>

                                          <input type="number" id="form-userID_${user.userID}" placeholder="" value="" name="form-userID" style="display:none;" required>

                                          <label for="form-firstName_${user.userID}"><b>First Name</b></label>
                                          <input type="text" id="form-firstName_${user.userID}" placeholder="" value="" name="form-firstName" required>

                                          <label for="form-lastName_${user.userID}"><b>Last Name</b></label>
                                          <input type="text" id="form-lastName_${user.userID}" value="" name="form-lastName" required>

                                          <label for="form-username_${user.userID}"><b>Username</b></label>
                                          <input type="text" id="form-username_${user.userID}" value="" name="form-username" required>

                                          <label for="form-password_${user.userID}"><b>Password</b></label>
                                          <input type="text" id="form-password_${user.userID}" value="" name="form-password" required>

                                          <label for="form-address_${user.userID}"><b>Address</b></label>
                                          <input type="text" id="form-address_${user.userID}" value="" name="form-address" required>

                                          <label for="form-phoneNumber_${user.userID}"><b>Phone Number</b></label>
                                          <input type="tel" id="form-phoneNumber_${user.userID}" value="" name="form-phoneNumber" pattern="[0-9]{4}[0-9]{4}" required>

                                          <label for="form-studentGrade_${user.userID}"><b>Grade</b></label>
                                          <input type="text" id="form-studentGrade_${user.userID}" value="" name="form-studentGrade" required>

                                          <label for="form-studentDoB_${user.userID}"><b>DOB</b></label>
                                          <input type="date" id="form-studentDoB_${user.userID}" value="" name="form-studentDoB" required>

                                          <label for="form-tuitionFee_${user.userID}"><b>Tuition Fee</b></label><br>
                                          <input type="number" min="100" id="form-tuitionFee_${user.userID}" value="" name="form-tuitionFee" required><br>

                                          <label for="form-hours_${user.userID}"><b>Lesson Duration</b></label><br>
                                          <select id="form-hours_${user.userID}" class="hours" value="" name="form-hours" required>
                                            <option value="0">Hours</option> <option value="0">0</option> <option value="1">1</option> <option value="2">2</option> <option value="3">3</option> <option value="4">4</option> <option value="5">5</option> <option value="6">6</option> <option value="7">7</option> <option value="8">8</option> <option value="9">9</option> <option value="10">10</option>
                                          </select>
                                          <label for="form-minutes_${user.userID}"></label>
                                          <select id="form-minutes_${user.userID}" class="minutes" value="" name="form-minutes">
                                            <option value="0">Minutes</option> <option value="0">0</option> <option value="5">5</option> <option value="10">10</option> <option value="15">15</option> <option value="20">20</option> <option value="25">25</option> <option value="30">30</option> <option value="35">35</option> <option value="40">40</option> <option value="45">45</option> <option value="50">50</option> <option value="55">55</option>
                                          </select>

                                          <br><label for="form-emailAddress_${user.userID}"><b>Email Address</b></label>
                                          <input type="email" id="form-emailAddress_${user.userID}" value="" name="form-emailAddress" >


                                          <div class="clearfix">
                                            <button type="button" onclick="document.getElementById('editStudent_'+${user.userID}).style.display='none'" class="cancelbtn">Cancel</button>
                                            <button type="submit" class="createbtn">Edit</button>
                                            <button type="button" id="deletebtn" onclick="deleteUser2(event, ${user.userID})">Delete</button>

                                          </div>
                                        </div>
                                      </form>
                                      <div id="confirm-delete_${user.userID}" class="modal4">

                                      <form id="deletefrm_${user.userID}" class="modal-content4" action="deleteStudent">
                                      <br><h2 style="color: black; position:relative; top:10%;">Are you sure?</h2><br>
                                        <input type="number" id="form2-userID_${user.userID}" value="" name="form2-userID" style="display:none;" required>
                                            <button id="popupdelete" class="deletepopup" onclick="submitDeleteForm(${user.userID})" style="float:right; width: 46%; margin: 2% 2%; bottom:-10%; ">Delete</button>
                                            <button type="button" class="canceldelete" id="popupcanceldelete_${user.userID}" style="float:left; width: 46%; margin:2% 2%; bottom: -10%;" onclick="removeDeleteChecker(event, ${user.userID})">Cancel</button>
                                      </form>
                                      </div>


                                    </div>
                                    </div>

                                    <div id="viewStudent_${user.userID}" onclick="userID1(${user.userID})" class="modal3">
                                          <div class="modal-content3" action="viewStudent">
                                            <div class="container3">
                                             <span onclick="document.getElementById('viewStudent_${user.userID}').style.display='none'" class="close" title="Close Modal">&times;</span>
                                              <h1>${user.firstName} ${user.lastName}</h1>
                                              <hr>

                                              <div id="viewText">

                                              <strong>Username:</strong> ${user.username} <br>
                                              <strong>Password:</strong> ${user.password}<br>
                                              <strong>Address:</strong> ${user.address}<br>
                                              <strong>Phone Number:</strong> ${user.contactNumber}<br>
                                              <strong>DOB:</strong> ${user.studentDoB}<br>
                                              <strong>Tuition Fee:</strong> ${user.tuitionFee}<br>
                                              <strong>Balance:</strong> ${user.balance}<br>
                                              <strong>Lesson Duration:</strong> ${user.lessonDuration}<br>
                                              <strong>Email:</strong> ${user.emailAddress}<br>
                                              <strong>Grade:</strong> ${user.studentGrade}<br>
                                              </div>

                                            </div>
                                          </div>
                                        </div>

                        <p><strong>Address:</strong> ${user.address} </p>
                        <p><strong>Phone Number:</strong> ${user.contactNumber}</p>
                        <p><strong>DOB:</strong> ${user.studentDoB}</p>

                    </div>
                    <div class="user-box" id="balancer">
                    <p style="float:left;"><strong>Balance:</strong> ${user.balance} </p>
                    <button id="editBalanceBtn" style="float:right;" onclick="balanceValues(event, ${user.userID}, ${user.balance})">Edit</button>
                    <div id="editBalance" onclick="userID2(${user.userID})">
                                            <div id="editBalance_${user.userID}" class="modal2">
                                                          <form class="modal-content2" action="editBalance">
                                                            <div class="container2">
                                                             <span onclick="document.getElementById('editBalance_${user.userID}').style.display='none'" class="close" title="Close Modal">&times;</span>
                                                              <h1>Edit Balance</h1>
                                                              <hr>

                                                              <input type="number" id="form-userID1_${user.userID}" placeholder="" value="" name="form-userID1" style="display:none;" required>

                                                              <input type="number" id="form-balance1_${user.userID}" placeholder="" value="" name="form-balance1" style="display:none;" required>

                                                              <b>Outstanding Balance: </b> $${user.balance} <br><br>

                                                              <label for="form-payment_${user.userID}"><b>Amount Paid</b></label>
                                                              <input type="text" id="form-payment_${user.userID}" placeholder="0" name="form-payment" required>

                                                              <label for="form-paymentadd_${user.userID}"><b>Add Balance </b><p id="smallText">(only if student needs to pay more than average monthly payment)</p></label>
                                                              <input type="text" id="form-paymentadd_${user.userID}" placeholder="0" value="0" name="form-paymentadd">


                                                              <div class="clearfix">
                                                                <button type="button" onclick="document.getElementById('editBalance_'+${user.userID}).style.display='none'" class="cancelbtn">Cancel</button>
                                                                <button type="submit" class="createbtn">Edit</button>

                                                              </div>
                                                            </div>
                                                          </form>

                    </div>

                    </div>


                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>


<script>

        function balanceValues(event, userID, balance){
             var userid1 = document.getElementById("form-userID1_"+userID);
             var balance1 = document.getElementById("form-balance1_"+userID);
             if (userID != null) {
                  userid1.value = userID;
                  balance1.value = balance;
             }
             document.getElementById('editBalance_'+userID).style.display='block';

        }

        function userID1(userID){
            window.addEventListener('click', function(event){
                    if (!event.target.matches('.modal-content3') && !event.target.matches("#viewbtn") && event.target.matches(".modal3")) {
                                  document.getElementById('viewStudent_'+userID).style.display='none';
                                }
                  });
        }

        function userID(userID){
        window.addEventListener('click', function(event){
                    if (!event.target.matches('.modal-content2') && !event.target.matches("#editbtn") && event.target.matches(".modal2")) {
                                  document.getElementById('editStudent_'+userID).style.display='none';
                                }
                  });
        }

        function userID2(userID){
                window.addEventListener('click', function(event){
                            if (!event.target.matches('.modal-content2') && !event.target.matches("#editBalanceBtn") && event.target.matches(".modal2")) {
                                          document.getElementById('editBalance_'+userID).style.display='none';
                                        }
                          });
                }

            function setFormValues(event, userID, firstName, lastName, username, password, address, contactNumber, studentGrade, studentDoB, tuitionFee, lessonDuration, emailAddress) {

                var userid = document.getElementById("form-userID_"+userID);
                var firstname = document.getElementById("form-firstName_"+userID);
                var lastname = document.getElementById("form-lastName_"+userID);
                var userName = document.getElementById("form-username_"+userID);
                var passWord = document.getElementById("form-password_"+userID);
                var Address = document.getElementById("form-address_"+userID);
                var phone = document.getElementById("form-phoneNumber_"+userID);
                var grade = document.getElementById("form-studentGrade_"+userID);
                var dob = document.getElementById("form-studentDoB_"+userID);
                var fee = document.getElementById("form-tuitionFee_"+userID);
                var minutes = document.getElementById("form-minutes_"+userID);
                var hours = document.getElementById("form-hours_"+userID);
                var email = document.getElementById("form-emailAddress_"+userID);

                <c:forEach var="user" items="${users}">
                    if (userID != null) {
                        userid.value = userID;
                        firstname.value = firstName;
                        lastname.value = lastName;
                        userName.value = username;
                        passWord.value = password;
                        Address.value = address;
                        phone.value = contactNumber;
                        grade.value = studentGrade;
                        dob.value = studentDoB;
                        fee.value = tuitionFee;
                        minutes.value = lessonDuration%60;
                        hours.value = (lessonDuration-(lessonDuration%60))/60;
                        email.value = emailAddress;
                    }
                </c:forEach>
                document.getElementById('editStudent_'+userID).style.display='block';
                }

                function setFormValues2(event, userID, firstName, lastName, username, password, address, contactNumber, studentGrade, studentDoB, tuitionFee, lessonDuration, emailAddress) {

                                var userid = document.getElementById("form-userID_"+userID);
                                var firstname = document.getElementById("form-firstName_"+userID);
                                var lastname = document.getElementById("form-lastName_"+userID);
                                var userName = document.getElementById("form-username_"+userID);
                                var passWord = document.getElementById("form-password_"+userID);
                                var Address = document.getElementById("form-address_"+userID);
                                var phone = document.getElementById("form-phoneNumber_"+userID);
                                var grade = document.getElementById("form-studentGrade_"+userID);
                                var dob = document.getElementById("form-studentDoB_"+userID);
                                var fee = document.getElementById("form-tuitionFee_"+userID);
                                var minutes = document.getElementById("form-minutes_"+userID);
                                var hours = document.getElementById("form-hours_"+userID);
                                var email = document.getElementById("form-emailAddress_"+userID);

                                <c:forEach var="user" items="${users}">
                                    if (userID != null) {
                                        userid.value = userID;
                                        firstname.value = firstName;
                                        lastname.value = lastName;
                                        userName.value = username;
                                        passWord.value = password;
                                        Address.value = address;
                                        phone.value = contactNumber;
                                        grade.value = studentGrade;
                                        dob.value = studentDoB;
                                        fee.value = tuitionFee;
                                        minutes.value = lessonDuration%60;
                                        hours.value = (lessonDuration-(lessonDuration%60))/60;
                                        email.value = emailAddress;
                                    }
                                </c:forEach>
                                document.getElementById('viewStudent_'+userID).style.display='block';
                                }

        function deleteUser2(event, userID){
        if (event.target.matches("#deletebtn")){
        var userid2 = document.getElementById("form2-userID_"+userID);
                       if (userID != null) {
                           userid2.value = userID;
                           userid2.placeholder = userID;
                       }
        }
           document.getElementById("confirm-delete_"+userID).style.display='block';
        }

        function removeDeleteChecker(event, userID){
            if (event.target.matches("#popupcanceldelete_"+userID)){
                document.getElementById("confirm-delete_"+userID).style.display="none";
            }
        }

        function submitDeleteForm(userID) {
                   document.getElementById("deletefrm_"+userID).submit();
        }


            </script>
 </body>
