package egovframework.example.sample.service;

public class VetVO extends SampleDefaultVO {

	// hospital_geo 테이블
	private int hpt_id;
	private String adrs_old;
	private String adrs_new;
	private String province;
	private String city;
	private String x_axis;
	private String y_axis;

	// hospital 테이블
	private String hpt_name;
	private String hpt_phone;
	private String hpt_open;
	private int hpt_hit;

	public VetVO() {
		// TODO Auto-generated constructor stub
	}
	
	public VetVO(int hpt_id, int hpt_hit) {
		super();
		this.hpt_id = hpt_id;
		this.hpt_hit = hpt_hit;
	}

	public int getHpt_id() {
		return hpt_id;
	}

	public void setHpt_id(int hpt_id) {
		this.hpt_id = hpt_id;
	}

	public String getAdrs_old() {
		return adrs_old;
	}

	public void setAdrs_old(String adrs_old) {
		this.adrs_old = adrs_old;
	}

	public String getAdrs_new() {
		return adrs_new;
	}

	public void setAdrs_new(String adrs_new) {
		this.adrs_new = adrs_new;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getX_axis() {
		return x_axis;
	}

	public void setX_axis(String x_axis) {
		this.x_axis = x_axis;
	}

	public String getY_axis() {
		return y_axis;
	}

	public void setY_axis(String y_axis) {
		this.y_axis = y_axis;
	}

	public String getHpt_name() {
		return hpt_name;
	}

	public void setHpt_name(String hpt_name) {
		this.hpt_name = hpt_name;
	}

	public String getHpt_phone() {
		return hpt_phone;
	}

	public void setHpt_phone(String hpt_phone) {
		this.hpt_phone = hpt_phone;
	}

	public String getHpt_open() {
		return hpt_open;
	}

	public void setHpt_open(String hpt_open) {
		this.hpt_open = hpt_open;
	}

	public int getHpt_hit() {
		return hpt_hit;
	}

	public void setHpt_hit(int hpt_hit) {
		this.hpt_hit = hpt_hit;
	}

}