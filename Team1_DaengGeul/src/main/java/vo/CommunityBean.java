package vo;

import java.sql.Date;

public class CommunityBean {
	private int board_idx;
	private int board_type;
	private int board_readcount;
	private String board_subject;
	private String member_id;
	private String board_content;
	private String board_file;
	private String board_real_file;
	private Date board_date;
	private int board_likecount;
	private int board_replycount;
	
	public int getBoard_likecount() {
		return board_likecount;
	}
	public void setBoard_likecount(int board_likecount) {
		this.board_likecount = board_likecount;
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
	public int getBoard_readcount() {
		return board_readcount;
	}
	public void setBoard_readcount(int board_readcount) {
		this.board_readcount = board_readcount;
	}
	public String getBoard_subject() {
		return board_subject;
	}
	public void setBoard_subject(String board_subject) {
		this.board_subject = board_subject;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getBoard_content() {
		return board_content;
	}
	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}
	public String getBoard_file() {
		return board_file;
	}
	public void setBoard_file(String board_file) {
		this.board_file = board_file;
	}
	public String getBoard_real_file() {
		return board_real_file;
	}
	public void setBoard_real_file(String board_real_file) {
		this.board_real_file = board_real_file;
	}
	public Date getBoard_date() {
		return board_date;
	}
	public void setBoard_date(Date board_date) {
		this.board_date = board_date;
	}
	
	public int getBoard_replycount() {
		return board_replycount;
	}
	public void setBoard_replycount(int board_replycount) {
		this.board_replycount = board_replycount;
	}
	
	@Override
	public String toString() {
		return "CommunityBean [board_idx=" + board_idx + ", board_type=" + board_type + ", board_readcount="
				+ board_readcount + ", board_subject=" + board_subject + ", member_id=" + member_id + ", board_content="
				+ board_content + ", board_file=" + board_file + ", board_real_file=" + board_real_file
				+ ", board_date=" + board_date + ", board_likecount=" + board_likecount + ", board_replycount="
				+ board_replycount + "]";
	}
}
