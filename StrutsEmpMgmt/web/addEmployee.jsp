<%-- 
    Document   : addEmployee
    Created on : Jan 27, 2023, 2:41:09 PM
    Author     : hp
--%>


<%@page import="java.util.Iterator"%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Employee</title>
        <!--<link href="css/sign_in.css" rel="stylesheet">-->
        <link href="css/backup_bootstrap.min.css" rel="stylesheet">
        <link href="css/backup_Sign_in.css" rel="stylesheet">
        <link href="css/backup_menu_css.css" rel="stylesheet">
        <link href="css/backup_product.css" rel="stylesheet">
    </head>
    <body class="text-center">
        <jsp:include page="menu.jsp"></jsp:include>



        <c:if test="${SuccessMsg != null}">

            <h1 style="color: lightgreen">${SuccessMsg}</h1> 

            <c:redirect url="home.jsp"/>
        </c:if>
        <c:if test="${not empty ErrorMsg}">
            <h1 style="color: red">${ErrorMsg}</h1>
        </c:if>

        <main class="form-signin w-100 m-auto">

            <form action="AddEmployee" method="post">

                <img class="mb-4" src="https://s3-us-west-2.amazonaws.com/cbi-image-service-prd/modified/d6b0e553-40e5-4fcc-aae9-46e950dcb071.png" alt="" width="300" height="100">
                <h1 class="h3 mb-3 fw-normal">Add employee data</h1>

                <div class="form-floating">
                    <input type="text"  class="form-control" id="floatingInput" placeholder="first name" name="firstName" oninvalid="this.setCustomValidity('Enter Letters only')" pattern="[A-Za-z]{1,}" required="required">
                    <label for="floatingInput">First Name</label>
                </div>
                <div class="form-floating">
                    <input type="text" class="form-control" id="floatingInput" placeholder="last name" name="lastName" oninvalid="this.setCustomValidity('Enter Letters only')" pattern="[A-Za-z]{1,}" required="required">
                    <label for="floatingInput">Last Name</label>
                </div>

                <div class="form-floating">
                    <input type="text" value ="0" class="form-control" id="floatingInput" placeholder="PhoneNo" name="phone" oninvalid="this.setCustomValidity('Enter valid 10 digit number')" pattern="[0-9]{10}" required="required" >
                    <label for="floatingInput">Phone</label>
                </div>
                <div class="form-floating">
                    <input type="text" class="form-control" id="floatingInput" placeholder="age" name="age" required>
                    <label for="floatingInput">Age</label>
                </div>
                <div class="form-floating">
                    <select name="gender" class="form-select" id="gender" required>
                        <option value="">Select Gender</option>
                        <option value="Male">Male</option>  
                        <option value="Female">Female</option> 
                        <option value="Other">Other</option> 
                    </select>
                    <label for="floatingInput">Gender</label>
                </div>


                <div class="form-floating" >
                    <select name="departmentId" class="form-select" id="departmentId" >
                        <option hidden>Select a Department</option>

                        <c:forEach items="${DeptList}" var="dept">
                            <option value=${dept.getDepartmentId()}> ${dept.getDepartName()}  </option>
                        </c:forEach>
                    </select>
                </div>


                <div class="form-floating">
                    <select name="roleId" class="form-select" id="roleId">
                        <option hidden  >Select a Role</option>

                        <c:forEach items="${RoleList}" var="roles">
                            <option value=${roles.getRoleId()}> ${roles.getRoleName()}  </option>
                        </c:forEach>   
                    </select>
                </div>


                <div class="form-floating">
                    <input type="number" step=0.001 class="form-control" id="floatingInput" placeholder="basicSalary" name="basicSalary" required>
                    <label for="floatingInput">Basic Salary</label>
                </div>
                <div class="form-floating">
                    <input type="number" value=0.0 step=0.001 class="form-control" id="floatingInput" placeholder="carAllowance" name="carAllowance">
                    <label for="floatingInput">Car Allowance</label>
                </div>
                <div class="form-floating">
                    <input type="text" value=0.0 step=0.001 class="form-control" id="floatingInput" placeholder="specialAllowance" name="specialAllowance" required>
                    <label for="floatingInput">Special Allowance</label>
                </div>

                <button class="w-100 btn btn-lg btn-primary" type="submit">Save</button>
                
                


            </form>
            

        </main>
        <script src="https://getbootstrap.com/docs/5.3/dist/js/bootstrap.bundle.min.js" ></script>

    </body>
</html>
