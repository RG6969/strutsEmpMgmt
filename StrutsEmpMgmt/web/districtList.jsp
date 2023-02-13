<%-- 
    Document   : districtList
    Created on : Feb 13, 2023, 2:08:44 PM
    Author     : hp
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<option >select a district</option>
<c:forEach items="${DistrictList}" var="district">
    <option value=${district.getDistrictCode() } <c:if test="${district.getDistrictCode().equalsIgnoreCase(User.getDistrictCode())}" > selected </c:if>> 
        ${district.getDistrictName()}  
    </option>
</c:forEach>