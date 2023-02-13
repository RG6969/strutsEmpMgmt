<%-- 
    Document   : home
    Created on : 08-Feb-2023, 2:40:23 pm
    Author     : RISHAV DUTTA
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/menu_css.css" rel="stylesheet">
        <link href="css/product.css" rel="stylesheet">
        <link href="css/sign-in.css" rel="stylesheet">
        <style>
            #urlSearch{
                width: 50%;
                margin:25px 0px;

                padding-left: 100px;
            }
            #url{
                text-align: center;
                width: 50%;
            }

        </style>
    </head>
    <body>
        <script>
            function fetchUrl() {
                var api_link = document.getElementById("url").value;
                console.log(api_link);
                return api_link;
            }

            async function addRandomData() {
                let url = fetchUrl();
                const res = await fetch(url);
                r = await res.json();
                
                console.log(r);
                
                alert("id-->"+ r.id +" UserId--> "+r.userId+" Body--> "+r.body+" Titlet--> "+r.title);
                document.getElementById("id").value = r.id;
                document.getElementById("userId").value = r.userId;
                document.getElementById("body").value = r.body;
                document.getElementById("title").value = r.title;
                
          
            }
            function onSubmit(){
                formDetails.submit();
            }

        </script>


        <div id="urlSearch">
            <label>API END POINT: </label>
            <input type="text" id="url" name="url"  onchange="fetchUrl()"  value="https://jsonplaceholder.typicode.com/posts/1"> 
        </div>

        <button class="filed_style" onclick="addRandomData()" >
            show data
        </button>
        <button class="filed_style" onclick="onSubmit()" >
            Add Data
        </button>

        <c:set var="msg" value="${SuccessMsg}"/>                   
        <c:if test="${msg!=null}">
            <div class="alert alert-success msg_style" role="alert">
                <c:out value="${msg}"/>
            </div>
        </c:if>
        <c:set var="msg" value="${FailureMsg}"/>                   
        <c:if test="${msg!=null}">
            <div class="alert alert-danger msg_style" role="alert">
                <c:out value="${msg}"/>
            </div>
        </c:if>



        <form action="AddData" method="post" id="formDetails"   >
            <input type="text" id="id" name="id"  readonly >
            <input type="text" id="userId" name="userId"  >
            <input type="text" id="body" name="body"   >
            <input type="text" id="title" name="title"  >
        </form>

    </body>
</html>
