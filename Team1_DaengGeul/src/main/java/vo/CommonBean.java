package vo;

public class CommonBean {
	
	private int common_idx;
	private String common_subject;
	private String common_content;
	
	
	public int getCommon_idx() {
		return common_idx;
	}
	public void setCommon_idx(int common_idx) {
		this.common_idx = common_idx;
	}
	public String getCommon_subject() {
		return common_subject;
	}
	public void setCommon_subject(String common_subject) {
		this.common_subject = common_subject;
	}
	public String getCommon_content() {
		return common_content;
	}
	public void setCommon_content(String comnon_content) {
		this.common_content = comnon_content;
	}
	@Override
	public String toString() {
		return "CommonBean [common_idx=" + common_idx + ", common_subject=" + common_subject + ", common_content="
				+ common_content + "]";
	}
	
	
	
	
}
