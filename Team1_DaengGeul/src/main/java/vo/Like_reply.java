package vo;

public class Like_reply {
	private int board_idx;
	private int reply_idx;
	private String member_id;
	
	
	public int getBoard_idx() {
		return board_idx;
	}
	public void setBoard_idx(int board_idx) {
		this.board_idx = board_idx;
	}
	public int getReply_idx() {
		return reply_idx;
	}
	public void setReply_idx(int reply_idx) {
		this.reply_idx = reply_idx;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	
	@Override
	public String toString() {
		return "Like_reply [board_idx=" + board_idx + ", reply_idx=" + reply_idx + ", member_id=" + member_id
				+ ", reply_likecount=" + "]";
	}

}
