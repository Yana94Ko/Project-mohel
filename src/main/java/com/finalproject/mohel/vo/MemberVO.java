package com.finalproject.mohel.vo;

public class MemberVO {
	private int no;
	private String email;
	private String pwd;
	private String nickname;
	private String tel;
	private String profile;
	private String birthdate;
	
	private String gender;
	private int age;
	private int height;
	private int weight;
	private float active;
	
	private float BMR;
	private float AMR;
	
	private int verify;
	private String recentvisit;
	private String joindate;
	
	@Override
	public String toString() {
		return "MemberVO [getNo()=" + getNo() + ", getEmail()=" + getEmail() + ", getPwd()=" + getPwd()
				+ ", getNickname()=" + getNickname() + ", getTel()=" + getTel() + ", getProfile()=" + getProfile()
				+ ", getBirthdate()=" + getBirthdate() + ", getGender()=" + getGender() + ", getAge()=" + getAge()
				+ ", getHeight()=" + getHeight() + ", getWeight()=" + getWeight() + ", getActive()=" + getActive()
				+ ", getBRM()=" + getBMR() + ", getARM()=" + getAMR() + ", getVerify()=" + getVerify()
				+ ", getRecentvisit()=" + getRecentvisit() + ", getJoindate()=" + getJoindate() + "]";
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
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
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
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public float getActive() {
		return active;
	}
	public void setActive(float active) {
		this.active = active;
	}
	public float getBMR() {
		if(getGender().equals("m")) {
			BMR = (float) (66.47+(13.75*(float) getWeight())+(5*(float) getHeight())-(6.76*(float) getAge()));
		}else {
			BMR = (float) (655.1+(9.56*(float) getWeight())+(1.85*(float) getHeight())-(4.68*(float) getAge()));
		}
		return BMR;
	}
	public void setBMR(float bRM) {
		BMR = bRM;
	}
	public float getAMR() {
		AMR = getBMR()*getActive();
		return AMR;
	}
	public void setAMR(float aRM) {
		AMR = aRM;
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
