package model;

public class AccountVO {

	private String division;
	private String name;
	private String userId;
	private String pw;
	private String email;
	
	public AccountVO() {
		super();
	}

	public AccountVO(String division, String name, String userId, String pw, String email) {
		super();
		this.division = division;
		this.name = name;
		this.userId = userId;
		this.pw = pw;
		this.email = email;
	}

	public String getEmail() {
		
		return email;
		
	}
	
	public void setEmail(String email) {
		
		this.email = email;
		
	}
	
	public String getName() {
		
		return name;
		
	}
	
	public void setName(String name) {
		
		this.name = name;
		
	}
	
	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}
	
}
