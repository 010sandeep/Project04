<%@page import="in.co.rays.util.ServletUtility"%>
<%@page import="in.co.rays.bean.RoleBean"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.ctl.ORSView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


			<%@include file="Header.jsp" %>
	<form action="<%=ORSView.ROLE_LIST_CTL%>" method="get">

		<%
			List list = (List) ServletUtility.getList(request);
		%>

		<h1 align="center">Role List</h1>


		<table border="1px" width="100%">


			<tr>

				<th><input type="checkbox"></th>
				<th>S.NO</th>
				<th>Name</th>
				<th>Desription</th>




			</tr>


			<%
				Iterator it = list.iterator();

				while (it.hasNext()) {

					RoleBean bean = (RoleBean) it.next();
			%>


			<tr align="center">

				<td><input type="checkbox" name="ids" value="<%=bean.getId()%>"></td>
				<td><%=bean.getId()%></td>
				<td><%=bean.getName()%></td>
				<td><%=bean.getDescription()%></td>

			</tr>


			<%
				}
			%>


		</table>
	</form>
</body>
</html>