package in.co.rays.bean;

import java.util.Date;

public class DoctorBean extends BaseBean {

	private String name;
	private Date dob;
	private String mobileNo;
	private String experties;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getExperties() {
		return experties;
	}

	public void setExperties(String experties) {
		this.experties = experties;
	}

	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return id + "";
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return experties;
	}

}
