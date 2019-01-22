package model;

import java.time.LocalDate;
import java.util.Date;

public class PlayerInfoVO {

	private int p_rNo;
	private String p_division;
	private String p_attached;
	private String p_name;
	private LocalDate p_birth;
	private double p_height;
	private double p_weight;
	private String p_position;
	private String p_nationality;
	private String p_visa;
	private String p_phone;
	private String p_address;
	private String p_email;
	private int p_totalscores;
	private int p_totalassists;
	private int p_totalapps;
	private double p_avgrate;
	private String p_pfoot;
	private String p_hta;
	private String p_gender;
	private String p_imgpath;
	
	public PlayerInfoVO() {
		super();
	}
	
	public PlayerInfoVO(int p_rNo, String p_division, String p_attached, String p_name, LocalDate p_birth,
			double p_height, double p_weight, String p_position, String p_nationality, String p_visa, String p_phone,
			String p_address, String p_email, int p_totalscores, int p_totalassists, int p_totalapps, double p_avgrate,
			String p_pfoot, String p_hta, String p_gender, String p_imgpath) {
		super();
		this.p_rNo = p_rNo;
		this.p_division = p_division;
		this.p_attached = p_attached;
		this.p_name = p_name;
		this.p_birth = p_birth;
		this.p_height = p_height;
		this.p_weight = p_weight;
		this.p_position = p_position;
		this.p_nationality = p_nationality;
		this.p_visa = p_visa;
		this.p_phone = p_phone;
		this.p_address = p_address;
		this.p_email = p_email;
		this.p_totalscores = p_totalscores;
		this.p_totalassists = p_totalassists;
		this.p_totalapps = p_totalapps;
		this.p_avgrate = p_avgrate;
		this.p_pfoot = p_pfoot;
		this.p_hta = p_hta;
		this.p_gender = p_gender;
		this.p_imgpath = p_imgpath;
	}


	public PlayerInfoVO(String p_division, String p_attached, String p_name, LocalDate p_birth, double p_height,
			double p_weight, String p_position, String p_nationality, String p_visa, String p_phone, String p_address,
			String p_email, int p_totalscores, int p_totalassists, int p_totalapps, double p_avgrate, String p_pfoot,
			String p_hta, String p_gender, String p_imgpath) {
		super();
		this.p_division = p_division;
		this.p_attached = p_attached;
		this.p_name = p_name;
		this.p_birth = p_birth;
		this.p_height = p_height;
		this.p_weight = p_weight;
		this.p_position = p_position;
		this.p_nationality = p_nationality;
		this.p_visa = p_visa;
		this.p_phone = p_phone;
		this.p_address = p_address;
		this.p_email = p_email;
		this.p_totalscores = p_totalscores;
		this.p_totalassists = p_totalassists;
		this.p_totalapps = p_totalapps;
		this.p_avgrate = p_avgrate;
		this.p_pfoot = p_pfoot;
		this.p_hta = p_hta;
		this.p_gender = p_gender;
		this.p_imgpath = p_imgpath;
	}


	public String getP_attached() {
		
		return p_attached;
		
	}
	
	public void setP_attached(String p_attached) {
		
		this.p_attached = p_attached;
		
	}
	
	public String getP_email() {
		
		return p_email;
		
	}
	
	public void setP_email(String p_email) {
		
		this.p_email = p_email;
		
	}
	
	public int getP_rNo() {
		return p_rNo;
	}

	public void setP_rNo(int p_rNo) {
		this.p_rNo = p_rNo;
	}

	public String getP_division() {
		return p_division;
	}

	public void setP_division(String p_division) {
		this.p_division = p_division;
	}

	public String getP_name() {
		return p_name;
	}

	public void setP_name(String p_name) {
		this.p_name = p_name;
	}

	public LocalDate getP_birth() {
		return p_birth;
	}

	public void setP_birth(LocalDate p_birth) {
		this.p_birth = p_birth;
	}

	public double getP_height() {
		return p_height;
	}

	public void setP_height(double p_height) {
		this.p_height = p_height;
	}

	public double getP_weight() {
		return p_weight;
	}

	public void setP_weight(double p_weight) {
		this.p_weight = p_weight;
	}

	public String getP_position() {
		return p_position;
	}

	public void setP_position(String p_position) {
		this.p_position = p_position;
	}

	public String getP_nationality() {
		return p_nationality;
	}

	public void setP_nationality(String p_nationality) {
		this.p_nationality = p_nationality;
	}

	public String getP_visa() {
		return p_visa;
	}

	public void setP_visa(String p_visa) {
		this.p_visa = p_visa;
	}

	public String getP_phone() {
		return p_phone;
	}

	public void setP_phone(String p_phone) {
		this.p_phone = p_phone;
	}

	public String getP_address() {
		return p_address;
	}

	public void setP_address(String p_address) {
		this.p_address = p_address;
	}

	public int getP_totalscores() {
		return p_totalscores;
	}

	public void setP_totalscores(int p_totalscores) {
		this.p_totalscores = p_totalscores;
	}

	public int getP_totalassists() {
		return p_totalassists;
	}

	public void setP_totalassists(int p_totalassists) {
		this.p_totalassists = p_totalassists;
	}

	public int getP_totalapps() {
		return p_totalapps;
	}

	public void setP_totalapps(int p_totalapps) {
		this.p_totalapps = p_totalapps;
	}

	public double getP_avgrate() {
		return p_avgrate;
	}

	public void setP_avgrate(double p_avgrate) {
		this.p_avgrate = p_avgrate;
	}

	public String getP_pfoot() {
		return p_pfoot;
	}

	public void setP_pfoot(String p_pfoot) {
		this.p_pfoot = p_pfoot;
	}

	public String getP_hta() {
		return p_hta;
	}

	public void setP_hta(String p_hta) {
		this.p_hta = p_hta;
	}

	public String getP_gender() {
		return p_gender;
	}

	public void setP_gender(String p_gender) {
		this.p_gender = p_gender;
	}

	public String getP_imgpath() {
		return p_imgpath;
	}

	public void setP_imgpath(String p_imgpath) {
		this.p_imgpath = p_imgpath;
	}
	
}
