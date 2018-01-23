package jp.go.jst.portal.controller;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.go.jst.portal.dto.login.LoginDto;
import jp.go.jst.portal.form.LoginForm;
import jp.go.jst.portal.service.LoginService;

@Controller
public class LoginController {
	
	// --------- 自定義バリデーション追加 ---------
	@Inject
	LoginValidator loginValidator;

	// --------- DB呼ぶ ---------
	@Autowired
	private LoginService loginServer;

	// --------- 自定義バリデーション追加 ---------
	@ModelAttribute
	public LoginForm loginForm() {
		return new LoginForm();
	}
	
	// --------- 自定義バリデーション追加 ---------
	@InitBinder
	public void InitBinder(WebDataBinder binder) {
		binder.addValidators(loginValidator);
	}

	/**
	 * ログイン画面初期化
	 *
	 * @param model 画面へのモジュール
	 * @return 画面ID
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String showMessage(Model model) {
		LoginForm loginForm = new LoginForm();
		loginForm.setId("");
		loginForm.setPassword("");
		model.addAttribute("loginForm", loginForm);
		return "login";
	}

	/**
	 * ログインユーザIDとパスワードの確認処理、及びメニュー画面への遷移
	 *
	 * @param loginForm 画面情報
	 * @param result 自動バリデーションチェック結果
	 * @param model 画面へのモジュール
	 * @return 画面ID
	 */
	@RequestMapping(value = "/showMessage", method = RequestMethod.POST)
	public String showMessage(@Valid @ModelAttribute LoginForm loginForm, BindingResult result, Model model) {
		// 自動バリデーションエラーチェック
		if (result.hasErrors()) {
			// エラーがある場合、メッセージを設定し、ログイン画面へ遷移する。
			model.addAttribute("message", "以下のエラーを解消してください");
			return "login";
		}
		// 画面返す用DTOを作成
		LoginDto dto = new LoginDto();
		// DBで該当ユーザを検索する
		dto = loginServer.getLogin(loginForm.getId());
		// TODO: 問題がある場合は省略
		// ユーザが存在する場合、ユーザ名を設定し、メニュー画面へ遷移する
		model.addAttribute("message", "名前 : " + dto.getFirstName());
		loginForm.setId(dto.getLoginId());
		loginForm.setPassword(dto.getFirstName());
		return "showMessage";
	}

}
