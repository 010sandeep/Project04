package in.co.rays.ctl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.bean.BaseBean;
import in.co.rays.bean.CollegeBean;
import in.co.rays.model.CollegeModel;
import in.co.rays.util.DataUtility;
import in.co.rays.util.ServletUtility;

@WebServlet(name = "CollegeListCtl", urlPatterns = { "/ctl/CollegeListCtl" })
public class CollegeListCtl extends BaseCtl {

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		CollegeBean bean = new CollegeBean();
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setName(DataUtility.getString(request.getParameter("Name")));

		populateDTO(bean, request);
		return bean;
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		CollegeModel model = new CollegeModel();
		CollegeBean bean = new CollegeBean();

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
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List list = null;

		String op = DataUtility.getString(request.getParameter("operation"));
		System.out.println("op = " + op);

		String[] ids = request.getParameterValues("ids");

		CollegeModel model = new CollegeModel();
		CollegeBean bean = (CollegeBean) populateBean(request);

		try {
			if (OP_DELETE.equalsIgnoreCase(op)) {

				for (String id : ids) {

					model.delete(DataUtility.getLong(id));
					list = model.search(bean, 0, 0);
					ServletUtility.setList(list, request);
					ServletUtility.forward(getView(), request, response);

				}
			}

			if (OP_SEARCH.equalsIgnoreCase(op)) {

				ServletUtility.setBean(bean, request);

			}

			if (OP_NEW.equalsIgnoreCase(op)) {

				ServletUtility.redirect(ORSView.COLLEGE_CTL, request, response);
				return;

			}

			list = model.search(bean, 0, 0);
			ServletUtility.setList(list, request);
			ServletUtility.forward(getView(), request, response);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ServletUtility.forward(getView(), request, response);

	}

	@Override
	protected String getView() {
		return ORSView.COLLEGE_LIST_VIEW;
	}

}
