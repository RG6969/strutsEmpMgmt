<%-- 
    Document   : stateList
    Created on : Feb 13, 2023, 11:58:23 AM
    Author     : hp
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<option >select a province</option>
<c:forEach items="${ProvinceList}" var="province">
    <option value=${province.getProvinceCode() } <c:if test="${province.getProvinceCode().equalsIgnoreCase(User.getProvinceCode())}" > selected </c:if>> 
        ${province.getProvinceName()}  
    </option>
</c:forEach>