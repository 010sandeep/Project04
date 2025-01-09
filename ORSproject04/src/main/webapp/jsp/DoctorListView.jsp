<%@page import="in.co.rays.util.DataUtility"%>
<%@page import="in.co.rays.model.DoctorModel"%>
<%@page import="in.co.rays.ctl.DoctorListCtl"%>
<%@page import="java.util.Iterator"%>
<%@page import="in.co.rays.bean.DoctorBean"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<%@ include file="Header.jsp"%>

	<form action="<%=ORSView.DOCTOR_LIST_CTL%>" method="post">
		<jsp:useBean id="bean" class="in.co.rays.bean.DoctorBean"
			scope="request" />

		<div align="center">

			<h1>Doctor List</h1>




			<%
				List list = ServletUtility.getList(request);
				int pageNo = ServletUtility.getPageNo(request);
				int pageSize = ServletUtility.getPageSize(request);
				int index = ((pageNo - 1) * pageSize) + 1;
				int nextPageSize = DataUtility.getInt(request.getAttribute("nextListSize").toString());

				Iterator it = list.iterator();
			%>

			<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
				type="hidden" name="pageSize" value="<%=pageSize%>">

			<table>

				<tr>

					<th>Name :</th>
					<td><input type="text" name="name" placeholder="Enter Name"
						value="<%=ServletUtility.getParameter("firstName", request)%>"></td>
					<td><input type="submit" name="operation"
						value="<%=DoctorListCtl.OP_SEARCH%>"></td>



					<td><input type="submit" name="operation"
						value="<%=DoctorListCtl.OP_RESET%>"></td>


				</tr>


			</table>
			<br>

			<table border="1%" style="width: 100%">
				<tr style="background-color: lavender; color: black;">
					<th><input type="checkbox" id="selectall"></th>
					<th>S.No.</th>
					<th>Name</th>
					<th>DOB</th>
					<th>MobileNo</th>
					<th>Experties</th>
					<th>Edit</th>
				</tr>
				<%
					while (it.hasNext()) {
						bean = (DoctorBean) it.next();
						DoctorModel model = new DoctorModel();
						DoctorBean userbean = model.findByPk(bean.getId());
				%>
				<tr align="center">
					<td><input type="checkbox" class="case" name="ids"
						value="<%=bean.getId()%>"></td>
					<td><%=index++%></td>
					<td><%=bean.getName()%></td>
					<td><%=bean.getDob()%></td>
					<td><%=bean.getMobileNo()%></td>
					<td><%=bean.getExperties()%></td>

					<td><a href="<%=ORSView.DOCTOR_CTL%>?id=<%=bean.getId()%>">edit</a></td>
				</tr>
				<%
					}
				%>
			</table>

			<table width=100%>

				<td align="left"><input type="submit" name="operation"
					value="<%=DoctorListCtl.OP_PREVIOUS%>"
					<%=(pageNo == 1) ? "disabled" : ""%>></td>

				<td style="width: 30%"><input type="submit" name="operation"
					value="<%=DoctorListCtl.OP_NEW%>"></td>
				<td style="width: 25%"><input type="submit" name="operation"
					value="<%=DoctorListCtl.OP_DELETE%>"></td>

				<td align="right"><input type="submit" name="operation"
					value="<%=DoctorListCtl.OP_NEXT%>"
					<%=(nextPageSize != 0) ? "" : "disabled"%>></td>
			</table>




		</div>

	</form>

</body>
</html>