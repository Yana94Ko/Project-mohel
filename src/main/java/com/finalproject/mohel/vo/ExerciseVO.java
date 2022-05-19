package com.finalproject.mohel.vo;

public class ExerciseVO {
	private int no;
	private String title;
	private String nickname;
	private String hashtag;
	private String contents;
	private String startdate;
	private String enddate;
	private String writedate;
	private String placeinfo; 
	private int applicantCnt;
	private int applicantMax;
	private int hit;
	private String img;
	
	@Override
	public String toString() {
		return "ExerciseVO [no=" + no + ", title=" + title + ", nickname=" + nickname + ", hashtag=" + hashtag
				+ ", contents=" + contents + ", startdate=" + startdate + ", enddate=" + enddate + ", writedate="
				+ writedate + ", placeinfo=" + placeinfo + ", applicantCnt=" + applicantCnt + ", applicantMax="
				+ applicantMax + ", hit=" + hit + ", img=" + img + "]";
	}
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getHashtag() {
		return hashtag;
	}
	public void setHashtag(String hashtag) {
		this.hashtag = hashtag;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	public String getWritedate() {
		return writedate;
	}
	public void setWritedate(String writedate) {
		this.writedate = writedate;
	}
	public String getPlaceinfo() {
		return placeinfo;
	}
	public void setPlaceinfo(String placeinfo) {
		this.placeinfo = placeinfo;
	}
	public int getApplicantCnt() {
		return applicantCnt;
	}
	public void setApplicantCnt(int applicantCnt) {
		this.applicantCnt = applicantCnt;
	}
	public int getApplicantMax() {
		return applicantMax;
	}
	public void setApplicantMax(int applicantMax) {
		this.applicantMax = applicantMax;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	
}
