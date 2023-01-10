package vo;

public class Like_community {
	
	private int board_idx;
	private String member_id;
	
	public int getBoard_idx() {
		return board_idx;
	}
	public void setBoard_idx(int board_idx) {
		this.board_idx = board_idx;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	
	@Override
	public String toString() {
		return "Like_community [board_idx=" + board_idx + ", member_id=" + member_id + "]";
	}
	
}
