<%@page import="in.co.rays.util.DataUtility"%>
<%@page import="in.co.rays.util.ServletUtility"%>
<%@page import="in.co.rays.ctl.DoctorCtl"%>
<%@page import="in.co.rays.util.HTMLUtility"%>
<%@page import="java.util.HashMap"%>
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

	<form action="<%=ORSView.DOCTOR_CTL%>" method="post">
		<jsp:useBean id="bean" class="in.co.rays.bean.DoctorBean"
			scope="request" />


		<div align="center">

			<%
				if (bean != null && bean.getId() > 0) {
			%>
			<h1>Update Doctor</h1>

			<%
				} else {
			%>

			<h1>Add Doctor</h1>
			<%
				}
			%>

			<h3>
				<font color="green"><%=ServletUtility.getSuccessMessage(request)%></font>
			</h3>
			<h3>
				<font color="red"><%=ServletUtility.getErrorMessage(request)%></font>
			</h3>

			<input type="hidden" name="id" value="<%=bean.getId()%>" />


			<table>

				<tr>


					<th align="left">Name<span style="color: red">*</span></th>
					<td><input type="text" name="name" placeholder="Enter Name"
						value="<%=DataUtility.getStringData(bean.getName())%>" /></td>

					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("name", request)%></font></td>


				</tr>

				<tr>


					<th align="left">DOB<span style="color: red">*</span></th>
					<td><input type="text" name="dob" id="update"
						placeholder="Enter DOB"
						value="<%=DataUtility.getStringData(bean.getDob())%>" /></td>

					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("dob", request)%></font></td>


				</tr>

				<tr>


					<th align="left">MobileNo<span style="color: red">*</span></th>
					<td><input type="text" name="mobile"
						placeholder="Enter MobileNo"
						value="<%=DataUtility.getStringData(bean.getMobileNo())%>" /></td>

					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("mobile", request)%></font></td>


				</tr>


				<tr>


					<th align="left">OrderType<span style="color: red">*</span></th>

					<td>
						<%
							HashMap<String, String> expertiesMap = new HashMap<>();
							expertiesMap.put("Heart", "Heart");

							expertiesMap.put("Paralies", "Paralies");
							expertiesMap.put("Bon", "bon");
						%> <%=HTMLUtility.getList("experties", bean.getExperties(), expertiesMap)%>



					</td>

					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("experties", request)%></font></td>


				</tr>

			</table>

			<table>


				<tr>

					<%
						if (bean != null && bean.getId() > 0) {
					%>

					<td align="left" colspan="2"><input type="submit"
						name="operation" value="<%=DoctorCtl.OP_UPDATE%>"> <input
						type="submit" name="operation" value="<%=DoctorCtl.OP_CANCEL%>">
						<%
							} else {
						%>
					<td><input type="submit" name="operation"
						value="<%=DoctorCtl.OP_SAVE%>"></td>
					<td><input type="submit" name="operation"
						value="<%=DoctorCtl.OP_RESET%>"></td>
					<%
						}
					%>

				</tr>


			</table>

		</div>






	</form>

</body>
</html>