package in.co.rays.ctl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.bean.BaseBean;
import in.co.rays.bean.DoctorBean;
import in.co.rays.bean.PurchaseBean;
import in.co.rays.model.DoctorModel;
import in.co.rays.model.PurchaseModel;
import in.co.rays.util.DataUtility;
import in.co.rays.util.DataValidator;
import in.co.rays.util.PropertyReader;
import in.co.rays.util.ServletUtility;

@WebServlet(name = "DoctorCtl", urlPatterns = { "/ctl/DoctorCtl" })
public class DoctorCtl extends BaseCtl {

	@Override
	protected boolean validate(HttpServletRequest request) {
		boolean isValid = true;

		String name = request.getParameter("name");
		if (DataValidator.isNull(name)) {
			request.setAttribute("name", PropertyReader.getValue("error.require", "Name"));
			isValid = false;
		} else if (!DataValidator.isName(name)) {
			request.setAttribute("name", "Invalid name");
			isValid = false;
		}

		String dob = request.getParameter("dob");
		if (DataValidator.isNull(dob)) {
			request.setAttribute("dob", PropertyReader.getValue("error.require", "dob"));
			isValid = false;
		}
		String mobile = request.getParameter("mobile");
		if (DataValidator.isNull(mobile)) {
			request.setAttribute("mobile", PropertyReader.getValue("error.require", "Mobile No"));
			isValid = false;
		} else if (!DataValidator.isPhoneLength(mobile)) {
			request.setAttribute("mobile", "Mobile No must have 10 digits");
			isValid = false;
		} else if (!DataValidator.isPhoneNo(mobile)) {
			request.setAttribute("mobileNo", "Invalid Mobile No");
			isValid = false;
		}

		if (DataValidator.isNull(request.getParameter("experties"))) {
			request.setAttribute("experties", PropertyReader.getValue("error.require", "experties"));
			isValid = false;
		}

		return isValid;

	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		DoctorBean bean = new DoctorBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setDob(DataUtility.getDate(request.getParameter("dob")));
		bean.setMobileNo(DataUtility.getString(request.getParameter("mobile")));
		bean.setExperties(DataUtility.getString(request.getParameter("experties")));

		populateDTO(bean, request);
		return bean;

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Long id = DataUtility.getLong(request.getParameter("id"));
		DoctorModel model = new DoctorModel();

		if (id > 0) {

			try {
				DoctorBean bean = model.findByPk(id);
				ServletUtility.setBean(bean, request);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			

		}
		
		ServletUtility.forward(getView(), request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String op = DataUtility.getString(request.getParameter("operation"));
		System.out.println("op = " + op);

		DoctorModel model = new DoctorModel();
		DoctorBean bean = (DoctorBean) populateBean(request);
		try {
			if (OP_SAVE.equalsIgnoreCase(op)) {

				model.add(bean);

				ServletUtility.setSuccessMessage("Data Added Successfully..", request);

			} else if (OP_RESET.equalsIgnoreCase(op)) {
				ServletUtility.redirect(ORSView.DOCTOR_CTL, request, response);
				return;
			}

			if (OP_UPDATE.equalsIgnoreCase(op)) {

				model.update(bean);
				
				ServletUtility.setSuccessMessage("Data Update Successfully..", request);
				
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ServletUtility.setBean(bean, request);
			ServletUtility.setErrorMessage("alerady exixts", request);
		}
		ServletUtility.forward(getView(), request, response);
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.DOCTOR_VIEW;
	}

}
