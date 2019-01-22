package model;

public class ClubInfoVO {

	private String f_name;
	private String f_ownername;
	private String f_directorname;
	private String f_coachname;
	private long f_wagebudget;
	private long f_transferbudget;
	private String f_address;
	private String f_imgpath;
	
	public ClubInfoVO(String f_name, String f_ownername, String f_directorname, String f_coachname, long f_wagebudget,
			long f_transferbudget, String f_address, String f_imgpath) {
		super();
		this.f_name = f_name;
		this.f_ownername = f_ownername;
		this.f_directorname = f_directorname;
		this.f_coachname = f_coachname;
		this.f_wagebudget = f_wagebudget;
		this.f_transferbudget = f_transferbudget;
		this.f_address = f_address;
		this.f_imgpath = f_imgpath;
	}

	public ClubInfoVO() {
		super();
	}

	public String getF_imgpath() {
		
		return f_imgpath;
		
	}
	
	public void setF_imgpath(String f_imgpath) {
		
		this.f_imgpath = f_imgpath;
		
	}
	
	public String getF_address() {
		
		return f_address;
		
	}
	
	public void setF_address(String f_address) {
		
		this.f_address = f_address;
		
	}
	
	public String getF_name() {
		return f_name;
	}

	public void setF_name(String f_name) {
		this.f_name = f_name;
	}

	public String getF_ownername() {
		return f_ownername;
	}

	public void setF_ownername(String f_ownername) {
		this.f_ownername = f_ownername;
	}

	public String getF_directorname() {
		return f_directorname;
	}

	public void setF_directorname(String f_directorname) {
		this.f_directorname = f_directorname;
	}

	public String getF_coachname() {
		return f_coachname;
	}

	public void setF_coachname(String f_coachname) {
		this.f_coachname = f_coachname;
	}

	public long getF_wagebudget() {
		return f_wagebudget;
	}

	public void setF_wagebudget(long f_wagebudget) {
		this.f_wagebudget = f_wagebudget;
	}

	public long getF_transferbudget() {
		return f_transferbudget;
	}

	public void setF_transferbudget(long f_transferbudget) {
		this.f_transferbudget = f_transferbudget;
	}
	
}
