package egovframework.example.sample.service;

import java.sql.Timestamp;

public class NoticeVO extends SampleDefaultVO {

	private static final long serialVersionUID = 1L;

	private int id;
	private String title;
	private String content;
	private Timestamp reg_date;	
	//private String reg_date = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(n_reg_date);	// String으로 변환
	private String image;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getReg_date() {
		return reg_date;
	}

	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
