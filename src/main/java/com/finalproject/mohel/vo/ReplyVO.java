package com.finalproject.mohel.vo;

public class ReplyVO {
	private int boardNo;
	private int no;
	private String nickname;
	private String contents;
	private String writedate;
	
	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardno) {
		this.boardNo = boardno;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getWritedate() {
		return writedate;
	}

	public void setWritedate(String writedate) {
		this.writedate = writedate;
	}
}
