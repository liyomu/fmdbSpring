package jp.go.jst.portal.form;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 画面表示用Form
 *
 * @author srin
 */
public class LoginForm {

	/** ログインユーザID NotEmptyチェック */
	@NotEmpty
	private String id;

	/** ログインパスワード NotEmptyチェック */
	private String password;

	/**
	 * @return id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id セットする id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password セットする password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}
