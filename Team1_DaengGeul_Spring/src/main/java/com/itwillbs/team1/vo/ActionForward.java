package com.itwillbs.team1.vo;

public class ActionForward {
	private String path;
	private boolean isRedirect;
	// 두 개를 한묶음으로 관리
	// path는 경로
    // is~는 true면 리다이렉트 false면 디스패치방식 포워딩
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public boolean isRedirect() {
		return isRedirect;
	}
	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}
	
	
	
}
