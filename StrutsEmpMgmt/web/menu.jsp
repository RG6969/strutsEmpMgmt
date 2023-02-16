
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
    <head>
        <title>Employee Management</title>
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <meta name="theme-color" content="#712cf9">

        <!-- Custom styles for this template -->
        <link href="css/header.css" rel="stylesheet">
        <!--<link href="css/carousel.css" rel="stylesheet">-->
    </head>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

    <script type="text/javascript">
        function loadNewContent(url, id) {
            document.getElementById("MENU").style.display = 'none';
            $.ajax({
                
                url: url,
                success: function (response) {
                    $('#' + id).html(response);
                }
            });
        }
    </script>
    <header class="p-3 text-bg-dark" id="MENU">
        <div class="container">
            <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
                <a href="/" class="d-flex align-items-center mb-2 mb-lg-0 text-white text-decoration-none">
                    <img src="images/flower-logo.jpg" width="75" height="75"></img>
                </a>







                <form class="col-12 col-lg-auto mb-3 mb-lg-0 me-lg-3" role="search">
                    <input type="search" class="form-control form-control-dark text-bg-dark" placeholder="Search..." aria-label="Search">
                </form>

                <div class="text-end">
                    <% if (request.getSession().getAttribute("Loggedin") == null) {%>
                    <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                        <li><a class="nav-link px-2 text-white" href="login.jsp">Add Employee</a></li>
                        <li><a class="nav-link px-2 text-white" href="login.jsp">Search Employee</a></li>
                        <li><a class="nav-link px-2 text-white" href="login.jsp">Show All Employee</a></li>
                    </ul>

                    <a href="login.jsp">
                        <button type="button" class="btn btn-outline-light me-2" >Login</button>
                    </a>
                    <a href="Preprocessing">
                        <button type="button" class="btn btn-warning">Sign-up</button>
                    </a>
                    <%
                    } else {%>
                    <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                        <li><a class="nav-link px-2 text-white" onclick="loadNewContent('addEmployee.jsp', 'CRUD')">Add Employee</a></li>
                        <li><a class="nav-link px-2 text-white" onclick="loadNewContent('searchEmployee.jsp', 'CRUD')">Search Employee</a></li>
                        <li><a class="nav-link px-2 text-white" onclick="loadNewContent('employeeDetails.jsp', 'CRUD')">Show All Employee</a></li>
                        <li><a class="nav-link px-2 text-white" onclick="loadNewContent('fetchAPI.jsp', 'CRUD')">fetch API</a></li>
                        
                    </ul>

                    <a href="Logout">
                        <button type="button" class="btn btn-outline-light me-2" >Log Out</button>
                    </a>
                    <%
                        }
                    %>


                </div>
            </div>
        </div>

    </header>
    <div id="CRUD"></div>

