<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Student Registration</title>
    <script src="js/script.js"></script>
    <link rel="stylesheet" href="css/style.css">
</head>

<body>

    <h2>Student Registration</h2>

    <form action="student/register" method="post" onsubmit="return validateForm();">

        <label>Name:</label>
        <input type="text" name="name" required>

        <br><br>

        <label>Username:</label>
        <input type="text" name="username" required>

        <br><br>

        <label>Password:</label>
        <input type="password" id="password" name="password" required>

        <br><br>

        <label>Email:</label>
        <input type="email" name="email" required>

        <br><br>

        <label>Phone:</label>
        <input type="text" name="phone">

        <br><br>

        <label>Address:</label>
        <textarea name="address"></textarea>

        <br><br>

        <label>Gender:</label>

        <input type="radio" name="gender" value="Male"> Male
        <input type="radio" name="gender" value="Female"> Female
        <input type="radio" name="gender" value="Other"> Other

        <br><br>

        <label>Qualification:</label>
        <select name="qualification">
            <option value="">Select</option>
            <option value="B.Tech">B.Tech</option>
            <option value="B.E">B.E</option>
            <option value="BCA">BCA</option>
            <option value="MCA">MCA</option>
            <option value="Other">Other</option>
        </select>

        <br><br>

        <label>Skills:</label>
        <textarea name="skills"></textarea>

        <br><br>

        <label>Resume:</label>
        <input type="text" name="resume" placeholder="Resume file name">

        <br><br>

        <input type="submit" value="Register">
        <input type="reset" value="Reset">

    </form>

    <br>

    <a href="login.jsp">Already registered? Login here</a>

</body>
</html>