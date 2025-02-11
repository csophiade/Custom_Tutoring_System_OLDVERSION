<title> Login </title>
<style><%@include file="/resources/css/index.css"%></style>
<html>

<body style="background-color:lavender">

<span class="dot"></span>
<form action="login" method="post">
    <div class="container">
        <label class="control-label" for="username"></label>
        <input type="text" name="username" placeholder="username" required>
        <label class="control-label" for="password"></label>
        <input type="password" name="password" placeholder="password" required><br>
        <button type="submit" style="text-color:white">Submit</button>
    </div>
</form>
</body>
</html>