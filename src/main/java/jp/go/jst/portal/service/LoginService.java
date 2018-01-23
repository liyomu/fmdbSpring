package jp.go.jst.portal.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.go.jst.portal.dao.LoginDao;
import jp.go.jst.portal.dto.login.LoginDto;
import jp.go.jst.portal.entity.Login;

/**
 * ログインサービスを呼ぶ
 *
 * @author srin
 */
@Service
public class LoginService {

	// DB検索用DAOを呼ぶ
	@Autowired
	private LoginDao loginDao;

	/**
	 * ログインユーザを検索処理
	 *
	 * @param loginId ログインユーザID
	 * @return ログインユーザ情報
	 */
	public LoginDto getLogin(String loginId) {
		// コントロール用DTOを作成
		LoginDto dto = new LoginDto();
		// ログイン情報を取得
		Login entity = loginDao.getLogin(loginId);
		if (entity == null) {
			return null;
		}
		// エンティティからDTOに詰め込む
		BeanUtils.copyProperties(entity, dto);
		// DTOを返す
		return dto;
	}
}
