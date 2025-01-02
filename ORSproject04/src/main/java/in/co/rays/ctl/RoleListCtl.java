package in.co.rays.ctl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.bean.BaseBean;
import in.co.rays.bean.RoleBean;
import in.co.rays.model.RoleModel;
import in.co.rays.util.DataUtility;
import in.co.rays.util.ServletUtility;

@WebServlet("/RoleListCtl")
public class RoleListCtl extends BaseCtl {

	@Override
	protected boolean validate(HttpServletRequest request) {
		return true;
	}

	@Override
	protected void preload(HttpServletRequest request) {

		RoleModel model = new RoleModel();

		List roleList;
		try {
			roleList = model.list();
			request.setAttribute("roleList", roleList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		RoleBean bean = new RoleBean();

		bean.setName(DataUtility.getString(request.getParameter("Name")));
		bean.setId(DataUtility.getLong(request.getParameter("roleId")));
		return bean;

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RoleBean bean = (RoleBean) populateBean(request);
		RoleModel model = new RoleModel();

		String op = DataUtility.getString(request.getParameter("operation"));
		System.out.println("op = " + op);

		String[] ids = request.getParameterValues("ids");

		try {
			if (OP_DELETE.equalsIgnoreCase(op)) {

				for (String id : ids) {

					model.delete(Integer.parseInt(id));

				}

			}

			if (OP_SEARCH.equalsIgnoreCase(op)) {

				model.search(bean, 0, 0);

			}

			List list = model.search(bean, 0, 0);
			ServletUtility.setBean(bean, request);
			ServletUtility.setList(list, request);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ServletUtility.forward(getView(), request, response);

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RoleModel model = new RoleModel();
		RoleBean bean = new RoleBean();

		try {
			List list = model.search(bean, 0, 0);
			ServletUtility.setList(list, request);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ServletUtility.forward(getView(), request, response);

	}

	@Override
	protected String getView() {
		return ORSView.ROLE_LIST_VIEW;
	}

}
