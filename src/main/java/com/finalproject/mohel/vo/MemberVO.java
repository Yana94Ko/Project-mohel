package com.finalproject.mohel.vo;

public class MemberVO {
	private int no;
	private String email;
	private String pwd;
	private String nickname;
	private String profile;
	private String birthdate;
	private int weight;
	private int height;
	private int age;
	private String gender;
	private float BRM;
	private float ARM;
	private int verify;
	private String recentvisit;
	private String joindate;
	
	@Override
	public String toString() {
		return "MemberVO [getNo()=" + getNo() + ", getEmail()=" + getEmail() + ", getPwd()=" + getPwd()
				+ ", getNickname()=" + getNickname() + ", getProfile()=" + getProfile() + ", getBirthdate()="
				+ getBirthdate() + ", getWeight()=" + getWeight() + ", getHeight()=" + getHeight() + ", getAge()="
				+ getAge() + ", getGender()=" + getGender() + ", getBRM()=" + getBRM() + ", getARM()=" + getARM()
				+ ", getVerify()=" + getVerify() + ", getRecentvisit()=" + getRecentvisit() + ", getJoindate()="
				+ getJoindate() + "]";
	}
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public String getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public float getBRM() {
		return BRM;
	}
	public void setBRM(float bRM) {
		BRM = bRM;
	}
	public float getARM() {
		return ARM;
	}
	public void setARM(float aRM) {
		ARM = aRM;
	}
	public int getVerify() {
		return verify;
	}
	public void setVerify(int verify) {
		this.verify = verify;
	}
	public String getRecentvisit() {
		return recentvisit;
	}
	public void setRecentvisit(String recentvisit) {
		this.recentvisit = recentvisit;
	}
	public String getJoindate() {
		return joindate;
	}
	public void setJoindate(String joindate) {
		this.joindate = joindate;
	}
}
