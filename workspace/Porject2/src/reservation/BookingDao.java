package reservation;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import util.Mybatis;

public class BookingDao {
	private SqlSessionFactory factory = Mybatis.getSqlSession();
	
    public List<BookingDto> taeyungSelect() throws Exception {
        //세션(DB처리를 위한 작업단위) 열기
        SqlSession sqlSession = factory.openSession();

        //SQL문 사용
        List<BookingDto> list = sqlSession.selectList("taeyungSelect");
        sqlSession.close();
        return list;
    }
}

