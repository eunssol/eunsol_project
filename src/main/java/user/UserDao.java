package user;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public List<UserVo> selectAll(UserVo vo) {
		return sqlSession.selectList("user.selectAll", vo);
	}
	
	public int count(UserVo vo) {
		return sqlSession.selectOne("user.count", vo);
	}
	
	public UserVo detail(UserVo vo) {
		return sqlSession.selectOne("user.detail", vo);
	}
	
	public int insert(UserVo vo) {
		return sqlSession.insert("user.insert", vo);
	}
	
	public int update(UserVo vo) {
		return sqlSession.update("user.update", vo);
	}
	
	public int delete(UserVo vo) {
		return sqlSession.delete("user.delete", vo);
	}
	
	public int isDuplicateId(String id) {
		return sqlSession.selectOne("user.isDuplicateId", id); //네임스페이스는 동일하지만 아이디 다르지 매개변수를 id로 바꿔줌 꼭 아이디로 안 해도됨
	}
	
	public UserVo login(UserVo vo) {
		return sqlSession.selectOne("user.login", vo);
	}
	public UserVo searchId(UserVo vo) {
		return sqlSession.selectOne("user.searchId", vo);
	}
	
	//같은 로직임 밑에 2개
	public UserVo searchPwd(UserVo vo) {
		return sqlSession.selectOne("user.searchPwd", vo);
	}
	
	public int updateTempPwd(UserVo vo) {
		return sqlSession.update("user.updateTempPwd", vo);
	}
	
	
	
}
