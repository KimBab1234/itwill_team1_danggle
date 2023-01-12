package vo;

import java.sql.Date;

public class Reply {
	private int reply_idx;
	private int board_idx;
	private int board_type;
	private String member_id;
	private String reply_content;
	private Date date;
	private int reply_likecount;
	private int reply_likeduplicate;
	
	public int getReply_idx() {
		return reply_idx;
	}
	public void setReply_idx(int reply_idx) {
		this.reply_idx = reply_idx;
	}
	public int getBoard_idx() {
		return board_idx;
	}
	public void setBoard_idx(int board_idx) {
		this.board_idx = board_idx;
	}
	public int getBoard_type() {
		return board_type;
	}
	public void setBoard_type(int board_type) {
		this.board_type = board_type;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getReply_content() {
		return reply_content;
	}
	public void setReply_content(String reply_content) {
		this.reply_content = reply_content;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getReply_likecount() {
		return reply_likecount;
	}
	public void setReply_likecount(int reply_likecount) {
		this.reply_likecount = reply_likecount;
	}
	
	public int getReply_likeduplicate() {
		return reply_likeduplicate;
	}
	public void setReply_likeduplicate(int reply_likeduplicate) {
		this.reply_likeduplicate = reply_likeduplicate;
	}
	
	@Override
	public String toString() {
		return "Reply [reply_idx=" + reply_idx + ", board_idx=" + board_idx + ", board_type=" + board_type
				+ ", member_id=" + member_id + ", reply_content=" + reply_content + ", date=" + date
				+ ", reply_likecount=" + reply_likecount + ", reply_likeduplicate=" + reply_likeduplicate + "]";
	}

}
