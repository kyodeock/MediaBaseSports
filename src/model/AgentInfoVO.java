package model;

import java.time.LocalDate;

public class AgentInfoVO {

	private String a_rNo;
	private String a_name;
	private String a_phone;
	private LocalDate a_birth;
	private String a_gender;
	private LocalDate a_qualifyDate;
	private LocalDate a_expireDate;
	private String a_univ;
	private String a_address;
	private String a_language;
	private int a_sal;
	private LocalDate a_hireDate;
	private String a_nationality;
	private String a_imgPath;
	
	public AgentInfoVO() {
		super();
	}

	public AgentInfoVO(String a_rNo, String a_name, String a_phone, LocalDate a_birth, String a_gender,
			LocalDate a_qualifyDate, LocalDate a_expireDate, String a_univ, String a_address, String a_language, int a_sal,
			LocalDate a_hireDate, String a_nationality, String a_imgPath) {
		super();
		this.a_rNo = a_rNo;
		this.a_name = a_name;
		this.a_phone = a_phone;
		this.a_birth = a_birth;
		this.a_gender = a_gender;
		this.a_qualifyDate = a_qualifyDate;
		this.a_expireDate = a_expireDate;
		this.a_univ = a_univ;
		this.a_address = a_address;
		this.a_language = a_language;
		this.a_sal = a_sal;
		this.a_hireDate = a_hireDate;
		this.a_nationality = a_nationality;
		this.a_imgPath = a_imgPath;
	}

	public AgentInfoVO(String a_name, String a_phone, LocalDate a_birth, String a_gender,
			LocalDate a_qualifyDate, LocalDate a_expireDate, String a_univ, String a_address, String a_language, int a_sal,
			LocalDate a_hireDate, String a_nationality, String a_imgPath) {
		super();
		this.a_name = a_name;
		this.a_phone = a_phone;
		this.a_birth = a_birth;
		this.a_gender = a_gender;
		this.a_qualifyDate = a_qualifyDate;
		this.a_expireDate = a_expireDate;
		this.a_univ = a_univ;
		this.a_address = a_address;
		this.a_language = a_language;
		this.a_sal = a_sal;
		this.a_hireDate = a_hireDate;
		this.a_nationality = a_nationality;
		this.a_imgPath = a_imgPath;
	}

	public String getA_imgPath() {
		
		return a_imgPath;
		
	}
	
	public void setA_imgPath(String a_imgPath) {
		
		this.a_imgPath = a_imgPath;
		
	}
	
	public String getA_address() {
		
		return a_address;
		
	}
	
	public void setA_address(String a_address) {
		
		this.a_address = a_address;
		
	}
	
	public String getA_rNo() {
		return a_rNo;
	}

	public void setA_rNo(String a_rNo) {
		this.a_rNo = a_rNo;
	}

	public String getA_name() {
		return a_name;
	}

	public void setA_name(String a_name) {
		this.a_name = a_name;
	}

	public String getA_phone() {
		return a_phone;
	}

	public void setA_phone(String a_phone) {
		this.a_phone = a_phone;
	}

	public LocalDate getA_birth() {
		return a_birth;
	}

	public void setA_birth(LocalDate a_birth) {
		this.a_birth = a_birth;
	}

	public String getA_gender() {
		return a_gender;
	}

	public void setA_gender(String a_gender) {
		this.a_gender = a_gender;
	}

	public LocalDate getA_qualifyDate() {
		return a_qualifyDate;
	}

	public void setA_qualifyDate(LocalDate a_qualifyDate) {
		this.a_qualifyDate = a_qualifyDate;
	}

	public LocalDate getA_expireDate() {
		return a_expireDate;
	}

	public void setA_expireDate(LocalDate a_expireDate) {
		this.a_expireDate = a_expireDate;
	}

	public String getA_univ() {
		return a_univ;
	}

	public void setA_univ(String a_univ) {
		this.a_univ = a_univ;
	}

	public String getA_language() {
		return a_language;
	}

	public void setA_language(String a_language) {
		this.a_language = a_language;
	}

	public int getA_sal() {
		return a_sal;
	}

	public void setA_sal(int a_sal) {
		this.a_sal = a_sal;
	}

	public LocalDate getA_hireDate() {
		return a_hireDate;
	}

	public void setA_hireDate(LocalDate a_hireDate) {
		this.a_hireDate = a_hireDate;
	}

	public String getA_nationality() {
		return a_nationality;
	}

	public void setA_nationality(String a_nationality) {
		this.a_nationality = a_nationality;
	}
	
}
