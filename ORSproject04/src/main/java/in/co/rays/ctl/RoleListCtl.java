package in.co.rays.ctl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.bean.RoleBean;
import in.co.rays.model.RoleModel;
import in.co.rays.util.ServletUtility;

@WebServlet("/RoleListCtl")
public class RoleListCtl extends BaseCtl {

	@Override
	protected boolean validate(HttpServletRequest request) {
		return true;
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
