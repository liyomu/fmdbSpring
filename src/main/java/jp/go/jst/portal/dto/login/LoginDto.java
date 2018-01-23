package jp.go.jst.portal.dto.login;

/**
 * ログイン用DTO
 *
 * @author srin
 */
public class LoginDto {

	/** ログインユーザID */
	private String loginId;
	/** 名前 */
	private String firstName;

	/**
	 * @return loginId
	 */
	public String getLoginId() {
		return loginId;
	}

	/**
	 * @param loginId セットする loginId
	 */
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	/**
	 * @return firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName セットする firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

}
