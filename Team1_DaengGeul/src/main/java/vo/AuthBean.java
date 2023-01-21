package vo;
/*
create table auth(
	auth_id varchar(50),
    authCode varchar(50)
);
 */
public class AuthBean {
	private String auth_id;
	private String authCode;
	
	public String getAuth_id() {
		return auth_id;
	}
	public void setAuth_id(String auth_id) {
		this.auth_id = auth_id;
	}
	public String getAuthCode() {
		return authCode;
	}
	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}
	
	@Override
	public String toString() {
		return "authBean [auth_id=" + auth_id + ", authCode=" + authCode + "]";
	}
	
}
