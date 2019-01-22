package model;

import java.time.LocalDate;

public class ContractInfoVO {

	private int c_no;
	private String p_name;
	private String f_name;
	private String c_division;
	private String c_period;
	private double c_wage;
	private String c_wagecondition;
	private String c_wicondition;
	private String c_wirate;
	private String c_scorecondition;
	private double c_scoreprofit;
	private String c_assistscondition;
	private double c_assistsprofit;
	private String c_appscondition;
	private double c_appsprofit;
	private String c_notappscondition;
	private double c_notappsprofit;
	private String c_carcondition;
	private String c_cardivision;
	private String c_housecondition;
	private double c_housefee;
	private String c_periodcondition;
	private String c_picondition;
	private String c_piyear;
	private String c_buyoutcondition;
	private String c_bocondition;
	private double c_buyoutpay;
	private String c_releasecondition;
	private String c_rcondition;
	private double c_releasepenalty;
	private LocalDate c_condate;
	private LocalDate c_cexpiredate;
	private String c_negocs;
	private double c_loyalty;
	private double c_agentfee;
	private double c_contractTotal;
	public ContractInfoVO(int c_no, String p_name, String f_name, String c_division, String c_period, double c_wage,
			String c_wagecondition, String c_wicondition, String c_wirate, String c_scorecondition,
			double c_scoreprofit, String c_assistscondition, double c_assistsprofit, String c_appscondition,
			double c_appsprofit, String c_notappscondition, double c_notappsprofit, String c_carcondition,
			String c_cardivision, String c_housecondition, double c_housefee, String c_periodcondition,
			String c_picondition, String c_piyear, String c_buyoutcondition, String c_bocondition, double c_buyoutpay,
			String c_releasecondition, String c_rcondition, double c_releasepenalty, LocalDate c_condate,
			LocalDate c_cexpiredate, String c_negocs, double c_loyalty, double c_agentfee, double c_contractTotal) {
		super();
		this.c_no = c_no;
		this.p_name = p_name;
		this.f_name = f_name;
		this.c_division = c_division;
		this.c_period = c_period;
		this.c_wage = c_wage;
		this.c_wagecondition = c_wagecondition;
		this.c_wicondition = c_wicondition;
		this.c_wirate = c_wirate;
		this.c_scorecondition = c_scorecondition;
		this.c_scoreprofit = c_scoreprofit;
		this.c_assistscondition = c_assistscondition;
		this.c_assistsprofit = c_assistsprofit;
		this.c_appscondition = c_appscondition;
		this.c_appsprofit = c_appsprofit;
		this.c_notappscondition = c_notappscondition;
		this.c_notappsprofit = c_notappsprofit;
		this.c_carcondition = c_carcondition;
		this.c_cardivision = c_cardivision;
		this.c_housecondition = c_housecondition;
		this.c_housefee = c_housefee;
		this.c_periodcondition = c_periodcondition;
		this.c_picondition = c_picondition;
		this.c_piyear = c_piyear;
		this.c_buyoutcondition = c_buyoutcondition;
		this.c_bocondition = c_bocondition;
		this.c_buyoutpay = c_buyoutpay;
		this.c_releasecondition = c_releasecondition;
		this.c_rcondition = c_rcondition;
		this.c_releasepenalty = c_releasepenalty;
		this.c_condate = c_condate;
		this.c_cexpiredate = c_cexpiredate;
		this.c_negocs = c_negocs;
		this.c_loyalty = c_loyalty;
		this.c_agentfee = c_agentfee;
		this.c_contractTotal = c_contractTotal;
	}
	public ContractInfoVO(String p_name, String f_name, String c_division, String c_period, double c_wage,
			String c_wagecondition, String c_wicondition, String c_wirate, String c_scorecondition,
			double c_scoreprofit, String c_assistscondition, double c_assistsprofit, String c_appscondition,
			double c_appsprofit, String c_notappscondition, double c_notappsprofit, String c_carcondition,
			String c_cardivision, String c_housecondition, double c_housefee, String c_periodcondition,
			String c_picondition, String c_piyear, String c_buyoutcondition, String c_bocondition, double c_buyoutpay,
			String c_releasecondition, String c_rcondition, double c_releasepenalty, LocalDate c_condate,
			LocalDate c_cexpiredate, String c_negocs, double c_loyalty, double c_agentfee, double c_contractTotal) {
		super();
		this.p_name = p_name;
		this.f_name = f_name;
		this.c_division = c_division;
		this.c_period = c_period;
		this.c_wage = c_wage;
		this.c_wagecondition = c_wagecondition;
		this.c_wicondition = c_wicondition;
		this.c_wirate = c_wirate;
		this.c_scorecondition = c_scorecondition;
		this.c_scoreprofit = c_scoreprofit;
		this.c_assistscondition = c_assistscondition;
		this.c_assistsprofit = c_assistsprofit;
		this.c_appscondition = c_appscondition;
		this.c_appsprofit = c_appsprofit;
		this.c_notappscondition = c_notappscondition;
		this.c_notappsprofit = c_notappsprofit;
		this.c_carcondition = c_carcondition;
		this.c_cardivision = c_cardivision;
		this.c_housecondition = c_housecondition;
		this.c_housefee = c_housefee;
		this.c_periodcondition = c_periodcondition;
		this.c_picondition = c_picondition;
		this.c_piyear = c_piyear;
		this.c_buyoutcondition = c_buyoutcondition;
		this.c_bocondition = c_bocondition;
		this.c_buyoutpay = c_buyoutpay;
		this.c_releasecondition = c_releasecondition;
		this.c_rcondition = c_rcondition;
		this.c_releasepenalty = c_releasepenalty;
		this.c_condate = c_condate;
		this.c_cexpiredate = c_cexpiredate;
		this.c_negocs = c_negocs;
		this.c_loyalty = c_loyalty;
		this.c_agentfee = c_agentfee;
		this.c_contractTotal = c_contractTotal;
	}
	public ContractInfoVO() {
		super();
	}
	
	public void setC_contractTotal(double c_contractTotal) {
		
		this.c_contractTotal = c_contractTotal;
		
	}
	
	public double getC_contractTotal() {
		
		return c_contractTotal;
		
	}
	public int getC_no() {
		return c_no;
	}
	public void setC_no(int c_no) {
		this.c_no = c_no;
	}
	public String getP_name() {
		return p_name;
	}
	public void setP_name(String p_name) {
		this.p_name = p_name;
	}
	public String getF_name() {
		return f_name;
	}
	public void setF_name(String f_name) {
		this.f_name = f_name;
	}
	public String getC_division() {
		return c_division;
	}
	public void setC_division(String c_division) {
		this.c_division = c_division;
	}
	public String getC_period() {
		return c_period;
	}
	public void setC_period(String c_period) {
		this.c_period = c_period;
	}
	public double getC_wage() {
		return c_wage;
	}
	public void setC_wage(double c_wage) {
		this.c_wage = c_wage;
	}
	public String getC_wagecondition() {
		return c_wagecondition;
	}
	public void setC_wagecondition(String c_wagecondition) {
		this.c_wagecondition = c_wagecondition;
	}
	public String getC_wicondition() {
		return c_wicondition;
	}
	public void setC_wicondition(String c_wicondition) {
		this.c_wicondition = c_wicondition;
	}
	public String getC_wirate() {
		return c_wirate;
	}
	public void setC_wirate(String c_wirate) {
		this.c_wirate = c_wirate;
	}
	public String getC_scorecondition() {
		return c_scorecondition;
	}
	public void setC_scorecondition(String c_scorecondition) {
		this.c_scorecondition = c_scorecondition;
	}
	public double getC_scoreprofit() {
		return c_scoreprofit;
	}
	public void setC_scoreprofit(double c_scoreprofit) {
		this.c_scoreprofit = c_scoreprofit;
	}
	public String getC_assistscondition() {
		return c_assistscondition;
	}
	public void setC_assistscondition(String c_assistscondition) {
		this.c_assistscondition = c_assistscondition;
	}
	public double getC_assistsprofit() {
		return c_assistsprofit;
	}
	public void setC_assistsprofit(double c_assistsprofit) {
		this.c_assistsprofit = c_assistsprofit;
	}
	public String getC_appscondition() {
		return c_appscondition;
	}
	public void setC_appscondition(String c_appscondition) {
		this.c_appscondition = c_appscondition;
	}
	public double getC_appsprofit() {
		return c_appsprofit;
	}
	public void setC_appsprofit(double c_appsprofit) {
		this.c_appsprofit = c_appsprofit;
	}
	public String getC_notappscondition() {
		return c_notappscondition;
	}
	public void setC_notappscondition(String c_notappscondition) {
		this.c_notappscondition = c_notappscondition;
	}
	public double getC_notappsprofit() {
		return c_notappsprofit;
	}
	public void setC_notappsprofit(double c_notappsprofit) {
		this.c_notappsprofit = c_notappsprofit;
	}
	public String getC_carcondition() {
		return c_carcondition;
	}
	public void setC_carcondition(String c_carcondition) {
		this.c_carcondition = c_carcondition;
	}
	public String getC_cardivision() {
		return c_cardivision;
	}
	public void setC_cardivision(String c_cardivision) {
		this.c_cardivision = c_cardivision;
	}
	public String getC_housecondition() {
		return c_housecondition;
	}
	public void setC_housecondition(String c_housecondition) {
		this.c_housecondition = c_housecondition;
	}
	public double getC_housefee() {
		return c_housefee;
	}
	public void setC_housefee(double c_housefee) {
		this.c_housefee = c_housefee;
	}
	public String getC_periodcondition() {
		return c_periodcondition;
	}
	public void setC_periodcondition(String c_periodcondition) {
		this.c_periodcondition = c_periodcondition;
	}
	public String getC_picondition() {
		return c_picondition;
	}
	public void setC_picondition(String c_picondition) {
		this.c_picondition = c_picondition;
	}
	public String getC_piyear() {
		return c_piyear;
	}
	public void setC_piyear(String c_piyear) {
		this.c_piyear = c_piyear;
	}
	public String getC_buyoutcondition() {
		return c_buyoutcondition;
	}
	public void setC_buyoutcondition(String c_buyoutcondition) {
		this.c_buyoutcondition = c_buyoutcondition;
	}
	public String getC_bocondition() {
		return c_bocondition;
	}
	public void setC_bocondition(String c_bocondition) {
		this.c_bocondition = c_bocondition;
	}
	public double getC_buyoutpay() {
		return c_buyoutpay;
	}
	public void setC_buyoutpay(double c_buyoutpay) {
		this.c_buyoutpay = c_buyoutpay;
	}
	public String getC_releasecondition() {
		return c_releasecondition;
	}
	public void setC_releasecondition(String c_releasecondition) {
		this.c_releasecondition = c_releasecondition;
	}
	public String getC_rcondition() {
		return c_rcondition;
	}
	public void setC_rcondition(String c_rcondition) {
		this.c_rcondition = c_rcondition;
	}
	public double getC_releasepenalty() {
		return c_releasepenalty;
	}
	public void setC_releasepenalty(double c_releasepenalty) {
		this.c_releasepenalty = c_releasepenalty;
	}
	public LocalDate getC_condate() {
		return c_condate;
	}
	public void setC_condate(LocalDate c_condate) {
		this.c_condate = c_condate;
	}
	public LocalDate getC_cexpiredate() {
		return c_cexpiredate;
	}
	public void setC_cexpiredate(LocalDate c_cexpiredate) {
		this.c_cexpiredate = c_cexpiredate;
	}
	public String getC_negocs() {
		return c_negocs;
	}
	public void setC_negocs(String c_negocs) {
		this.c_negocs = c_negocs;
	}
	public double getC_loyalty() {
		return c_loyalty;
	}
	public void setC_loyalty(double c_loyalty) {
		this.c_loyalty = c_loyalty;
	}
	public double getC_agentfee() {
		return c_agentfee;
	}
	public void setC_agentfee(double c_agentfee) {
		this.c_agentfee = c_agentfee;
	}
}
