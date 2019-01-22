package model;

import java.time.LocalDate;

public class PlayerRecordVO {

	private int g_no;
	private String p_name;
	private String f_name;
	private String r_appstime;
	private double r_appsrate;
	private int r_balltouch;
	private int r_trypass;
	private int r_keypass;
	private int r_successpass;
	private double r_sucpassrate;
	private int r_assists;
	private int r_trydribble;
	private int r_sucdribble;
	private double r_sucdribblerate;
	private int r_tryshots;
	private int r_shotontarget;
	private int r_scores;
	private int r_arieldual;
	private int r_ariwons;
	private int r_trycut;
	private int r_succut;
	private int r_trytackle;
	private int r_suctackle;
	private double r_suctacklerate;
	private int r_save;
	private int r_gainsgoal;
	private LocalDate r_gamedate;
	private String r_haa;
	
	public PlayerRecordVO() {
		super();
	}

	public PlayerRecordVO(String p_name, String f_name, String r_appstime, double r_appsrate, int r_balltouch,
			int r_trypass, int r_keypass, int r_successpass, double r_sucpassrate, int r_assists, int r_trydribble,
			int r_sucdribble, double r_sucdribblerate, int r_tryshots, int r_shotontarget, int r_scores,
			int r_arieldual, int r_ariwons, int r_trycut, int r_succut, int r_trytackle, int r_suctackle,
			double r_suctacklerate, int r_save, int r_gainsgoal, LocalDate r_gamedate, String r_haa) {
		super();
		this.p_name = p_name;
		this.f_name = f_name;
		this.r_appstime = r_appstime;
		this.r_appsrate = r_appsrate;
		this.r_balltouch = r_balltouch;
		this.r_trypass = r_trypass;
		this.r_keypass = r_keypass;
		this.r_successpass = r_successpass;
		this.r_sucpassrate = r_sucpassrate;
		this.r_assists = r_assists;
		this.r_trydribble = r_trydribble;
		this.r_sucdribble = r_sucdribble;
		this.r_sucdribblerate = r_sucdribblerate;
		this.r_tryshots = r_tryshots;
		this.r_shotontarget = r_shotontarget;
		this.r_scores = r_scores;
		this.r_arieldual = r_arieldual;
		this.r_ariwons = r_ariwons;
		this.r_trycut = r_trycut;
		this.r_succut = r_succut;
		this.r_trytackle = r_trytackle;
		this.r_suctackle = r_suctackle;
		this.r_suctacklerate = r_suctacklerate;
		this.r_save = r_save;
		this.r_gainsgoal = r_gainsgoal;
		this.r_gamedate = r_gamedate;
		this.r_haa = r_haa;
	}

	public PlayerRecordVO(int g_no, String p_name, String f_name, String r_appstime, double r_appsrate, int r_balltouch,
			int r_trypass, int r_keypass, int r_successpass, double r_sucpassrate, int r_assists, int r_trydribble,
			int r_sucdribble, double r_sucdribblerate, int r_tryshots, int r_shotontarget, int r_scores,
			int r_arieldual, int r_ariwons, int r_trycut, int r_succut, int r_trytackle, int r_suctackle,
			double r_suctacklerate, int r_save, int r_gainsgoal, LocalDate r_gamedate, String r_haa) {
		super();
		this.g_no = g_no;
		this.p_name = p_name;
		this.f_name = f_name;
		this.r_appstime = r_appstime;
		this.r_appsrate = r_appsrate;
		this.r_balltouch = r_balltouch;
		this.r_trypass = r_trypass;
		this.r_keypass = r_keypass;
		this.r_successpass = r_successpass;
		this.r_sucpassrate = r_sucpassrate;
		this.r_assists = r_assists;
		this.r_trydribble = r_trydribble;
		this.r_sucdribble = r_sucdribble;
		this.r_sucdribblerate = r_sucdribblerate;
		this.r_tryshots = r_tryshots;
		this.r_shotontarget = r_shotontarget;
		this.r_scores = r_scores;
		this.r_arieldual = r_arieldual;
		this.r_ariwons = r_ariwons;
		this.r_trycut = r_trycut;
		this.r_succut = r_succut;
		this.r_trytackle = r_trytackle;
		this.r_suctackle = r_suctackle;
		this.r_suctacklerate = r_suctacklerate;
		this.r_save = r_save;
		this.r_gainsgoal = r_gainsgoal;
		this.r_gamedate = r_gamedate;
		this.r_haa = r_haa;
	}

	public int getG_no() {
		return g_no;
	}

	public void setG_no(int g_no) {
		this.g_no = g_no;
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

	public String getR_appstime() {
		return r_appstime;
	}

	public void setR_appstime(String r_appstime) {
		this.r_appstime = r_appstime;
	}

	public double getR_appsrate() {
		return r_appsrate;
	}

	public void setR_appsrate(double r_appsrate) {
		this.r_appsrate = r_appsrate;
	}

	public int getR_balltouch() {
		return r_balltouch;
	}

	public void setR_balltouch(int r_balltouch) {
		this.r_balltouch = r_balltouch;
	}

	public int getR_trypass() {
		return r_trypass;
	}

	public void setR_trypass(int r_trypass) {
		this.r_trypass = r_trypass;
	}

	public int getR_keypass() {
		return r_keypass;
	}

	public void setR_keypass(int r_keypass) {
		this.r_keypass = r_keypass;
	}

	public int getR_successpass() {
		return r_successpass;
	}

	public void setR_successpass(int r_successpass) {
		this.r_successpass = r_successpass;
	}

	public double getR_sucpassrate() {
		return r_sucpassrate;
	}

	public void setR_sucpassrate(double r_sucpassrate) {
		this.r_sucpassrate = r_sucpassrate;
	}

	public int getR_assists() {
		return r_assists;
	}

	public void setR_assists(int r_assists) {
		this.r_assists = r_assists;
	}

	public int getR_trydribble() {
		return r_trydribble;
	}

	public void setR_trydribble(int r_trydribble) {
		this.r_trydribble = r_trydribble;
	}

	public int getR_sucdribble() {
		return r_sucdribble;
	}

	public void setR_sucdribble(int r_sucdribble) {
		this.r_sucdribble = r_sucdribble;
	}

	public double getR_sucdribblerate() {
		return r_sucdribblerate;
	}

	public void setR_sucdribblerate(double r_sucdribblerate) {
		this.r_sucdribblerate = r_sucdribblerate;
	}

	public int getR_tryshots() {
		return r_tryshots;
	}

	public void setR_tryshots(int r_tryshots) {
		this.r_tryshots = r_tryshots;
	}

	public int getR_shotontarget() {
		return r_shotontarget;
	}

	public void setR_shotontarget(int r_shotontarget) {
		this.r_shotontarget = r_shotontarget;
	}

	public int getR_scores() {
		return r_scores;
	}

	public void setR_scores(int r_scores) {
		this.r_scores = r_scores;
	}

	public int getR_arieldual() {
		return r_arieldual;
	}

	public void setR_arieldual(int r_arieldual) {
		this.r_arieldual = r_arieldual;
	}

	public int getR_ariwons() {
		return r_ariwons;
	}

	public void setR_ariwons(int r_ariwons) {
		this.r_ariwons = r_ariwons;
	}

	public int getR_trycut() {
		return r_trycut;
	}

	public void setR_trycut(int r_trycut) {
		this.r_trycut = r_trycut;
	}

	public int getR_succut() {
		return r_succut;
	}

	public void setR_succut(int r_succut) {
		this.r_succut = r_succut;
	}

	public int getR_trytackle() {
		return r_trytackle;
	}

	public void setR_trytackle(int r_trytackle) {
		this.r_trytackle = r_trytackle;
	}

	public int getR_suctackle() {
		return r_suctackle;
	}

	public void setR_suctackle(int r_suctackle) {
		this.r_suctackle = r_suctackle;
	}

	public double getR_suctacklerate() {
		return r_suctacklerate;
	}

	public void setR_suctacklerate(double r_suctacklerate) {
		this.r_suctacklerate = r_suctacklerate;
	}

	public int getR_save() {
		return r_save;
	}

	public void setR_save(int r_save) {
		this.r_save = r_save;
	}

	public int getR_gainsgoal() {
		return r_gainsgoal;
	}

	public void setR_gainsgoal(int r_gainsgoal) {
		this.r_gainsgoal = r_gainsgoal;
	}

	public LocalDate getR_gamedate() {
		return r_gamedate;
	}

	public void setR_gamedate(LocalDate r_gamedate) {
		this.r_gamedate = r_gamedate;
	}

	public String getR_haa() {
		return r_haa;
	}

	public void setR_haa(String r_haa) {
		this.r_haa = r_haa;
	}

}
