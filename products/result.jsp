<%@ page import="java.util.*"%>
<html>
<body>
<h1 align="center">New Product Added</h1>

<table align="center" border="2">
<tr>
<th>Barcode</th>
<th>Name</th>
<th>Color</th>
<th>Description</th>
</tr>
<%
	
	List details = (List)request.getAttribute("details"); %>
	
	<tr>
	<td><% out.print(details.get(0));%></td>
	<td><% out.print(details.get(1));%></td>
	<td><% out.print(details.get(2));%></td>
	<td><% out.print(details.get(3));%></td>
	</tr>

</table> 
</body>
</html>
