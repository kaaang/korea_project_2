package company;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import util.Mybatis;

public class CompanyDao {
	private SqlSessionFactory factory = Mybatis.getSqlSession();
	
    public List<CompanyDto> taeyungSelect() throws Exception {
        //세션(DB처리를 위한 작업단위) 열기
        SqlSession sqlSession = factory.openSession();

        //SQL문 사용
        List<CompanyDto> list = sqlSession.selectList("taeyungSelect");
        sqlSession.close();
        return list;
    }
}
