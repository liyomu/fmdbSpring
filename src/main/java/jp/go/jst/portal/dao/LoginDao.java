package jp.go.jst.portal.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jp.go.jst.portal.entity.Login;

@Repository("loginDao")
public class LoginDao {

	@Autowired
	private SqlSessionTemplate sqlSession;

	/**
	 * ログインテーブルを呼ぶ、結果を返す。
	 * @param id
	 * @return
	 */
	public Login getLogin(String id) {
		// ログインMapper.xmlファイルを呼ぶ、xmlファイルの該当SQL文を実行し、実行結果を返す。
		Login login = sqlSession.selectOne("jp.go.jst.portal.dao.LoginDao.getLogin", id);
		// TODO: 取得できない場合は省略
		// 取得した場合、ログインユーザ情報を返す
		return login;
	}

}
