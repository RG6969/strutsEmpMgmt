<%-- 
    Document   : Signup
    Created on : Jan 19, 2023, 3:10:08 PM
    Author     : hp
--%>


<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
        <meta name="generator" content="Hugo 0.108.0">
        <title>Product example Â· Bootstrap v5.3</title>

        <link rel="canonical" href="https://getbootstrap.com/docs/5.3/examples/product/">







        <!-- Favicons -->
        <link rel="apple-touch-icon" href="/docs/5.3/assets/img/favicons/apple-touch-icon.png" sizes="180x180">
        <link rel="icon" href="/docs/5.3/assets/img/favicons/favicon-32x32.png" sizes="32x32" type="image/png">
        <link rel="icon" href="/docs/5.3/assets/img/favicons/favicon-16x16.png" sizes="16x16" type="image/png">
        <link rel="manifest" href="https://getbootstrap.com/docs/5.3/assets/img/favicons/manifest.json">
        <link rel="mask-icon" href="/docs/5.3/assets/img/favicons/safari-pinned-tab.svg" color="#712cf9">
        <link rel="icon" href="/docs/5.3/assets/img/favicons/favicon.ico">
        <meta name="theme-color" content="#712cf9">

        <link href="css/backup_bootstrap.min.css" rel="stylesheet">
        <link href="css/backup_Sign_in.css" rel="stylesheet">
        <link href="css/backup_menu_css.css" rel="stylesheet">
        <link href="css/backup_product.css" rel="stylesheet">



        <!-- Custom styles for this template -->


    </head>


    <body>
        <jsp:include page="menu.jsp"></jsp:include>





            <main class="form-signin w-100 m-auto">


            <c:set var="emp" value="${Emp}" />
            <form action="SaveEmployee" method="post" >




                <h1 class="h3 mb-3 fw-normal">Enter Your Details</h1>
                <div class="form-floating">
                    <input type="text" class="form-control" id="floatingInput" placeholder="first name" name="employeeId" value=${emp.getEmployeeId()} readonly>
                    <label for="floatingInput">Employee Id</label>
                </div>

                <div class="form-floating">
                    <input type="text" class="form-control" id="floatingInput" placeholder="first name" name="firstName" value=${emp.getFirstName()}>
                    <label for="floatingInput">First Name</label>
                </div>
                <div class="form-floating">
                    <input type="text" class="form-control" id="floatingInput" placeholder="last name" name="lastName" value=${emp.getLastName()}>
                    <label for="floatingInput">Last Name</label>
                </div>

                <div class="form-floating">
                    <input type="text" class="form-control" id="floatingInput" placeholder="phoneNo" name="phone" value=${emp.getPhone()}>
                    <label for="floatingInput">Phone</label>
                </div>
                <div class="form-floating">
                    <input type="text" class="form-control" id="floatingInput" placeholder="gender" name="gender" value=${emp.getGender()}>
                    <label for="floatingInput">Gender</label>
                </div>
                <div class="form-floating">
                    <input type="text" class="form-control" id="floatingInput" placeholder="age" name="age" value=${emp.getAge()}>
                    <label for="floatingInput">Age</label>
                </div>
                <!--                <div class="form-floating">
                                    <input type="text" class="form-control" id="floatingInput" placeholder="departName" name="departName" value=>
                                    <label for="floatingInput">Department Name</label>
                                </div>-->
                <!--<div class="form-control">Your Current Department is </div>-->
                <div class="form-floating" >
                    <select name="departmentId" class="form-select" id="departmentId" >
                        <option value=${emp.getDepartmentId()} hidden >${emp.getDepartmentName()}</option>
                        <c:forEach items="${DeptList}" var="dept">
                            <option value=${dept.getDepartmentId()}> ${dept.getDepartName()}  </option>
                        </c:forEach>
                    </select>
                </div>
                <!--                
                <!--<div class="form-control">Your Current Role is </div>-->
                <div class="form-floating">
                    <select name="roleId" class="form-select" id="roleId">
                        <option value=${emp.getRoleId()} hidden  >${emp.getRoleName()}</option>
                    <c:forEach items="${RoleList}" var="roles">
                        <option value=${roles.getRoleId()}> ${roles.getRoleName()}  </option>
                     </c:forEach>   
                    </select>
                </div>
                    
                <div class="form-floating">
                    <input type="text" class="form-control" id="floatingInput" placeholder="basicSalary" name="basicSalary" value=${emp.getBasicSalary()}>
                    <label for="floatingInput">Basic Salary</label>
                </div>
                <div class="form-floating">
                    <input type="text" class="form-control" id="floatingInput" placeholder="carAllowance" name="carAllowance" value=${emp.getCarAllowance()}>
                    <label for="floatingInput">Car Allowance</label>
                </div>
                <div class="form-floating">
                    <input type="text" class="form-control" id="floatingInput" placeholder="specialAllowance" name="specialAllowance" value=${emp.getSpecialAllowance()}>
                    <label for="floatingInput">Special Allowance</label>
                </div>

                <div class="checkbox mb-3">
                    <label>
                        <input type="checkbox" value="remember-me"> Remember me
                    </label>
                </div>

                <button class="w-100 btn btn-lg btn-primary" type="submit">Save</button>    


                <p class="mt-5 mb-3 text-muted">&copy; 2017â2022</p>
            </form>



        </main>
        <script src="https://getbootstrap.com/docs/5.3/dist/js/bootstrap.bundle.min.js" ></script>
    </body>
</html>
