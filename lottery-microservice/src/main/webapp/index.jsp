<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Lottery Numbers</title>
</head>
<body>
     <form action="draw" method="post">
        <button>Draw</button>
     </form> 
     <c:if test="${not empty model.lotteryNumbers}">
     <table>
     	<thead>
     		<tr>
     			<th>Column #1</th>
     			<th>Column #2</th>
     			<th>Column #3</th>
     			<th>Column #4</th>
     			<th>Column #5</th>
     			<th>Column #6</th>
     		</tr>
     	</thead>
     	<tbody>
			 <c:forEach items="${model.lotteryNumbers}" var="numbers">
			 	<tr>
			 		<c:forEach items="${numbers}" var="number">
			 	   		<td>${number}</td>
			 		</c:forEach>	     
			 	</tr>
			 </c:forEach>	     
     	</tbody>
     </table> 
     </c:if>
</body>
</html>