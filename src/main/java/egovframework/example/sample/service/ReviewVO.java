package egovframework.example.sample.service;

import java.sql.Timestamp;

public class ReviewVO {
	// review 테이블
	private int rv_id;
	private int hpt_id;
	private String hpt_rate;
	private String rv_title;
	private String rv_content;
	private Timestamp rv_reg_date;
	private String rv_image;
	private String pet_type;
	private String visit_date;
	private int visit_is_new;

	// review_comment 테이블
	private int cmt_id;
	private String cmt_content;
	private Timestamp cmt_reg_date;

	public int getRv_id() {
		return rv_id;
	}

	public void setRv_id(int rv_id) {
		this.rv_id = rv_id;
	}

	public int getHpt_id() {
		return hpt_id;
	}

	public void setHpt_id(int hpt_id) {
		this.hpt_id = hpt_id;
	}

	public String getHpt_rate() {
		return hpt_rate;
	}

	public void setHpt_rate(String hpt_rate) {
		this.hpt_rate = hpt_rate;
	}

	public String getRv_title() {
		return rv_title;
	}

	public void setRv_title(String rv_title) {
		this.rv_title = rv_title;
	}

	public String getRv_content() {
		return rv_content;
	}

	public void setRv_content(String rv_content) {
		this.rv_content = rv_content;
	}

	public Timestamp getRv_reg_date() {
		return rv_reg_date;
	}

	public void setRv_reg_date(Timestamp rv_reg_date) {
		this.rv_reg_date = rv_reg_date;
	}

	public String getRv_image() {
		return rv_image;
	}

	public void setRv_image(String rv_image) {
		this.rv_image = rv_image;
	}

	public String getPet_type() {
		return pet_type;
	}

	public void setPet_type(String pet_type) {
		this.pet_type = pet_type;
	}

	public String getVisit_date() {
		return visit_date;
	}

	public void setVisit_date(String visit_date) {
		this.visit_date = visit_date;
	}

	public int getVisit_is_new() {
		return visit_is_new;
	}

	public void setVisit_is_new(int visit_is_new) {
		this.visit_is_new = visit_is_new;
	}

	public int getCmt_id() {
		return cmt_id;
	}

	public void setCmt_id(int cmt_id) {
		this.cmt_id = cmt_id;
	}

	public String getCmt_content() {
		return cmt_content;
	}

	public void setCmt_content(String cmt_content) {
		this.cmt_content = cmt_content;
	}

	public Timestamp getCmt_reg_date() {
		return cmt_reg_date;
	}

	public void setCmt_reg_date(Timestamp cmt_reg_date) {
		this.cmt_reg_date = cmt_reg_date;
	}

}
