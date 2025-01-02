<%@page import="in.co.rays.util.DataUtility"%>
<%@page import="in.co.rays.util.HTMLUtility"%>
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


	<%@include file="Header.jsp"%>
	<form action="<%=ORSView.ROLE_LIST_CTL%>" method="post">
		<jsp:useBean id="bean" class="in.co.rays.bean.RoleBean"
			scope="request" />

		<%
			List list = (List) ServletUtility.getList(request);
		List roleList = (List) request.getAttribute("roleList");
		%>

		<h1 align="center">Role List</h1>

		<table>


			<th>Role :</th>
			<td><%=HTMLUtility.getList("roleId", DataUtility.getStringData(bean.getId()), roleList)%></td>
			&nbsp;
			<th>Name :</th>
			<td><input type="text" name="Name" placeholder="Enter Name"></td>
			&nbsp;
			<td><input type="submit" name="operation" value="Search"></td>
			<td><input type="submit" name="operation" value="Reset"></td>

		</table>
		<br><br>


		<table border="1px" width="100%">


			<tr>

				<th><input type="checkbox"></th>
				<th>S.NO</th>
				<th>Name</th>
				<th>Description</th>
				<th>Edit</th>




			</tr>


			<%
				Iterator it = list.iterator();

				while (it.hasNext()) {

					 bean = (RoleBean) it.next();
			%>


			<tr align="center">

				<td><input type="checkbox" name="ids" value="<%=bean.getId()%>"></td>
				<td><%=bean.getId()%></td>
				<td><%=bean.getName()%></td>
				<td><%=bean.getDescription()%></td>
				<td><a href="<%=ORSView.ROLE_CTL%>?id=<%=bean.getId()%>">edit</a></td>

			</tr>

			<%
				}
			%>
			<table>
				<td><input type="submit" name="operation" value="Delete"></td>

			</table>



		</table>
	</form>
</body>
</html>