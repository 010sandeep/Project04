<%@page import="in.co.rays.util.ServletUtility"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
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
	<form action="<%=ORSView.USER_CTL%> ">

		<%
			List list = (List) ServletUtility.getList(request);
		%>

		<h1 align="center">User List</h1>

		<table border="1px" width="100%">

			<tr>

				<th>FirstName</th>
				<th>LastName</th>
				<th>Login Id</th>
				<th>DOB</th>
				<th>Mobile No</th>
				<th>Gender</th>
				<th>Role</th>
				<th>Created_By</th>
				<th>Modified_by</th>
				<th>Created_DateTime</th>
				<th>Modified_DateTime</th>


			</tr>

			<%
				Iterator it = list.iterator();
				while (it.hasNext()) {

					UserBean bean = (UserBean) it.next();
			%>


			<tr>

				<td><%=bean.getFirstName()%></td>
				<td><%=bean.getLastName()%></td>
				<td><%=bean.getLogin()%></td>
				<td><%=bean.getDob()%></td>
				<td><%=bean.getMobileNo()%></td>
				<td><%=bean.getGender()%></td>
				<td><%=bean.getRoleId()%></td>
				<td><%=bean.getCreatedBy()%></td>
				<td><%=bean.getModifiedBy()%></td>
				<td><%=bean.getCreatedDatetime()%></td>
				<td><%=bean.getModifiedDatetime()%></td>


			</tr>

			<%
				}
			%>






		</table>

	</form>
</body>
</html>