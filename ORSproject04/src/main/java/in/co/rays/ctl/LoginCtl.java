package in.co.rays.ctl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.bean.BaseBean;
import in.co.rays.bean.UserBean;
import in.co.rays.model.UserModel;
import in.co.rays.util.DataUtility;
import in.co.rays.util.DataValidator;
import in.co.rays.util.PropertyReader;
import in.co.rays.util.ServletUtility;

@WebServlet("/LoginCtl")
public class LoginCtl extends BaseCtl {

	public static final String OP_SIGN_IN = "SignIn";
	public static final String OP_SIGN_UP = "Sign Up";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ServletUtility.forward(getView(), request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String op = DataUtility.getString(request.getParameter("operation"));

		UserBean bean = (UserBean) populateBean(request);

		UserModel model = new UserModel();

		if (op.equalsIgnoreCase(op)) {

			try {
				model.authenticate(bean.getLogin(), bean.getPassword());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ServletUtility.redirect(ORSView.WELCOME_CTL, request, response);

		}

	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		UserBean bean = new UserBean();
		bean.setLogin(DataUtility.getString(request.getParameter("login")));
		bean.setPassword(DataUtility.getString(request.getParameter("password")));
		return bean;
	}

	@Override
	protected String getView() {
		return ORSView.LOGIN_VIEW;
	}

	@Override
	protected boolean validate(HttpServletRequest request) {
		String op = DataUtility.getString(request.getParameter("operation"));
		boolean isValid = true;

		// Skip validation if logging out or signing up
		if (OP_LOG_OUT.equalsIgnoreCase(op) || OP_SIGN_UP.equalsIgnoreCase(op)) {
			return isValid;
		}

		// Validate login
		if (DataValidator.isNull(request.getParameter("login"))) {
			request.setAttribute("login", PropertyReader.getValue("error.require", "Login Id"));
			isValid = false;
		} else if (!DataValidator.isEmail(request.getParameter("login"))) {
			request.setAttribute("login", PropertyReader.getValue("error.email", "Login"));
			isValid = false;
		}

		// Validate password
		if (DataValidator.isNull(request.getParameter("password"))) {
			request.setAttribute("password", PropertyReader.getValue("error.require", "Password"));
			isValid = false;
		}

		return isValid;
	}

}
