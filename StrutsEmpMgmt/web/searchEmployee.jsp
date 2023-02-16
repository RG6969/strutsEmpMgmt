
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
        <meta name="generator" content="Hugo 0.108.0">
        <title>Employee Management</title>

        <link rel="canonical" href="https://getbootstrap.com/docs/5.3/examples/product/">





        <link href="css/bootstrap.min.css" rel="stylesheet">

        <!-- Favicons -->
        <link rel="apple-touch-icon" href="/docs/5.3/assets/img/favicons/apple-touch-icon.png" sizes="180x180">
        <link rel="icon" href="/docs/5.3/assets/img/favicons/favicon-32x32.png" sizes="32x32" type="image/png">
        <link rel="icon" href="/docs/5.3/assets/img/favicons/favicon-16x16.png" sizes="16x16" type="image/png">
        <link rel="manifest" href="https://getbootstrap.com/docs/5.3/assets/img/favicons/manifest.json">
        <link rel="mask-icon" href="/docs/5.3/assets/img/favicons/safari-pinned-tab.svg" color="#712cf9">
        <link rel="icon" href="/docs/5.3/assets/img/favicons/favicon.ico">
        <meta name="theme-color" content="#712cf9">

        <link href="css/menu_css.css" rel="stylesheet">



        <!-- Custom styles for this template -->
        <link href="css/product.css" rel="stylesheet">
    </head>
    <body >
        <div id="body">


            <jsp:include page="menu.jsp"></jsp:include>

                <form class="form-inline" action="SearchEmployee" method="post" id="myForm">
                    <div class="container" >
                        <div class="row">
                            <div class="form-control col-sm">
                                <input value="" class="form-control" type="text" placeholder="First Name" name="firstName">
                            </div>
                            <div class="form-control col-sm">
                                <input value=""  class="form-control" type="text" placeholder="Last Name" name="lastName">
                            </div>
                            <div class="form-control col-sm">
                                <input value=""  class="form-control" type="text" placeholder="Gender" name="gender">
                            </div>


                            <!--                        <div class="form-control col-sm">
                                                        <input class="form-control" type="text" placeholder="Department" name="departmentName">
                                                    </div>-->

                            <div class="form-control col-sm" >
                                <select name="departmentId" class="form-control" id="departmentId" >
                                    <option value="" hidden>Select a Department</option>

                                <c:forEach items="${DeptList}" var="dept">
                                    <option value=${dept.getDepartmentId()}> ${dept.getDepartName()}  </option>
                                </c:forEach>
                            </select>
                        </div>


                        <div class="form-control col-sm">
                            <select name="roleId" class="form-control" id="roleId">
                                <option value="" hidden >Select a Role</option>

                                <c:forEach items="${RoleList}" var="roles">
                                    <option value=${roles.getRoleId()}> ${roles.getRoleName()}  </option>
                                </c:forEach>   
                            </select>
                        </div>


                        <div class="form-control col-sm">
                            <button type="button" class="form-control btn btn-primary mb-2" id="submitBtn" onclick="onSubmitButton(event)">Search</button>
                        </div>

                    </div>
                </div>
            </form>
            <c:if test="${EmpList!= null}">

                <table class="table table-striped" id="paginatingTable">

                    <thead>
                        <tr>
                            <th scope="col">Employee Id</th>
                            <th scope="col">First Name</th>
                            <th scope="col">Last Name</th>
                            <th scope="col">Phone Number</th>
                            <th scope="col">Gender</th>
                            <th scope="col">Age</th>
                            <th scope="col">Department Name</th>
                            <th scope="col">Role Name</th>
                            <th scope="col">Basic Salary</th>
                            <th scope="col">Car Allowance</th>
                            <th scope="col">Special Allowance</th>
                            <th scope="col">Action</th>
                        </tr>
                    </thead>
                    <c:forEach items="${searchEmployee}" var="emp">
                        <tr>
                            <th scope="row">${emp.getEmployeeId()}</th>
                            <td>${emp.getFirstName()}</td>
                            <td>${emp.getLastName()}</td>
                            <td>${emp.getPhone()}</td>
                            <td> ${emp.getGender()}</td>
                            <td>${emp.getAge()}</td>
                            <td>${emp.getDepartmentName()}</td>
                            <td>${emp.getRoleName()}</td>
                            <td>${emp.getBasicSalary()}</td>
                            <td>${emp.getCarAllowance()}</td>
                            <td>${emp.getSpecialAllowance()}</td>
                            <td> <a href=EditEmployee?employeeId=${emp.getEmployeeId()}>Edit</a></td>
                        </tr>
                    </c:forEach>



                </table>
            </c:if>
            <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
            <script src="https://cdn.datatables.net/1.13.1/js/jquery.dataTables.min.js"></script>
            <script src="https://cdn.datatables.net/1.13.1/js/dataTables.bootstrap5.min.js"></script>
            <script>
                                $(document).ready(function () {
                                    $('#paginatingTable').DataTable();
                                });
            </script>
        </div>


        <script src="https://getbootstrap.com/docs/5.3/dist/js/bootstrap.bundle.min.js" ></script>
        <script>
                                // Get the form and submit button elements
                                function onSubmitButton(event) {
                                    event.preventDefault();
                                    var form = document.getElementById("myForm");
                                    var submitBtn = document.getElementById("submitBtn");
                                    var responseDiv = document.getElementById("responseDiv");


                                    // Add a click event listener to the submit button
//                    submitBtn.addEventListener("click", function (event) {
                                    document.getElementById("body").style.display = 'none';
//                    submitBtn.disabled = true;
                                    // Prevent the default form submission behavior
                                    
//                        responseDiv.innerHTML = "";
                                    submitBtn.disabled = true;

                                    // Create a new XMLHttpRequest object
                                    var xhr = new XMLHttpRequest();

                                    // Set the request method and URL
                                    xhr.open("POST", "SearchEmployee", true);

                                    // Set the request headers (if needed)
//                xhr.setRequestHeader("Content-Type", "application/json");

                                    // Set the callback function to handle the response
                                    xhr.onreadystatechange = function () {
                                        if (xhr.readyState === 4 && xhr.status === 200) {
                                            // Do something with the response (if needed)
                                            console.log(xhr.responseText);
                                            responseDiv.innerHTML = xhr.responseText;
                                            submitBtn.disabled = false;
//                        document.body.innerHTML = xhr.responseText;
                                        }
                                    };

                                    // Get the form data and send the request
                                    var formData = new FormData(form);
                                    xhr.send(formData);
                                }
                                ;
//                }
        </script>


        <div id="responseDiv" ></div>
    </body>

</html>
