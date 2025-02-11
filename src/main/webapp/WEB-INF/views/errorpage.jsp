<body class="body1">
<%
String message = (String) request.getAttribute("message");
if (message != null) {
%>
  <script>alert("${message}")</script>
<%
}
%>

<p> error page </p>

