package login;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import util.MemberDto;
import util.Mybatis;

public class LoginDao {
	 private SqlSessionFactory factory = Mybatis.getSqlSession();

	    
	    public LoginDto loginCheck(String id) throws Exception {
	    	SqlSession sqlSession = factory.openSession();
	    	LoginDto logindto = sqlSession.selectOne("loginCheck",id);
	    	sqlSession.close();
	    	
	    	return logindto;
	    }
}
