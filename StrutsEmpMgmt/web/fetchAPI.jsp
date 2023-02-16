<%--
    Document   : savinginfofromurl
    Created on : 10-Feb-2023, 11:51:08 pm
    Author     : Lenovo
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:if test="${Loggedin==null}">
    <c:redirect url="landingPage.jsp"/>
</c:if>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/product.css" rel="stylesheet">
        <title>Fetch Info From API</title>
    </head>
    <body>
        <div id = "body">
        <jsp:include page="menu.jsp"></jsp:include>
            <div class="d-flex align-items-center justify-content-center mt-3">
                <form action="FetchUrl" method="get" id="myForm">
                    <div class="form-outline">
                        <select name="sizeOfData"class="form-select">
                            <option value="0">--select end point---</option>
                            <option value="1">single object</option>
                            <option value="2">multiple object(Array of json data)</option>
                        </select>
                    </div>
                    <div class="form-outline mt-4">
                        <input type="text" id="url" class="form-control" placeholder="...paste your url here..." name="url"/>
                    </div>
                    <div class="d-flex align-items-center justify-content-center mt-3">
                        <button type="submit" class="btn btn-outline-success" id="submitBtn">
                            submit
                        </button>
                    </div>
                </form>
            </div>
        <c:if test="${TextArea!=null}">
            <div class="form-group">
                <label for="exampleFormControlTextarea1">JSON DATA FETCHED FROM GIVEN URL</label>
                <textarea class="form-control" id="exampleFormControlTextarea1" rows="11" readonly>${TextArea}</textarea>
            </div>
        </c:if>
            </div>
            <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
            <script>
                // Get the form and submit button elements
                var form = document.getElementById("myForm");
                var submitBtn = document.getElementById("submitBtn");
                var responseDiv = document.getElementById("responseDiv");

                // Add a click event listener to the submit button
                submitBtn.addEventListener("click", function (event) {
                    document.getElementById("body").style.display = 'none';
                    // Prevent the default form submission behavior
                    event.preventDefault();

                    // Create a new XMLHttpRequest object
                    var xhr = new XMLHttpRequest();

                    // Set the request method and URL
                    xhr.open("POST", "FetchUrl", true);

                    // Set the request headers (if needed)
//                xhr.setRequestHeader("Content-Type", "application/json");

                    // Set the callback function to handle the response
                    xhr.onreadystatechange = function () {
                        if (xhr.readyState === 4 && xhr.status === 200) {
                            // Do something with the response (if needed)
                            console.log(xhr.responseText);
                            responseDiv.innerHTML = xhr.responseText;
//                        document.body.innerHTML = xhr.responseText;
                        }
                    };

                    // Get the form data and send the request
                    var formData = new FormData(form);
                    xhr.send(formData);
                });
        </script>
        <div id="responseDiv" ></div>
    </body>
</html>
