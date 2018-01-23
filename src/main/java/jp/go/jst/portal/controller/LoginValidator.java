package jp.go.jst.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import jp.go.jst.portal.dto.login.LoginDto;
import jp.go.jst.portal.form.LoginForm;
import jp.go.jst.portal.service.LoginService;

@Component
public class LoginValidator implements Validator {

	@Autowired
	private LoginService loginServer;

	@Override
	public boolean supports(Class<?> clazz) {
		return LoginForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		LoginForm form = (LoginForm) target;
		String pw = form.getPassword();
		// --------- パラメータありチェック ---------
		if (pw.isEmpty()) {
			String[] par = { "password" };
			errors.rejectValue("password", "password.empty", par, "パスワードが未入力された。");
		}
		// --------- プロパティファイルに存在しないエラーコードチェック ---------
		if (pw.length() < 6 || pw.length() > 10) {
			errors.rejectValue("password", "password.size", "パスワード欄に6桁以上10桁以内の英数字を入力したください。");
		}
		// --------- validator DB内容チェック例 ---------
		// 画面返す用DTOを作成
		LoginDto dto = new LoginDto();
		// DBで該当ユーザを検索する
		dto = loginServer.getLogin(form.getId());
		if (dto == null) {
			errors.rejectValue("password", "", "パスワードが違います。");
		}

	}

}
