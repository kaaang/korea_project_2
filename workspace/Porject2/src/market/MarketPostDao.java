package market;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import util.Mybatis;


public class MarketPostDao {
	private SqlSessionFactory factory= Mybatis.getSqlSession();
	
	// 등록
	public int insertMarketPost(MarketPostDto marketDto) throws Exception{
		SqlSession sqlSession= factory.openSession();
		int insertMarketPost= sqlSession.insert("marketDto", marketDto);
		if(insertMarketPost>0) {
			sqlSession.commit();
		}else {
			sqlSession.rollback();
		}
		sqlSession.close();
		return insertMarketPost;
	}
	
	
	
	public List<MarketPostDto> selectMarketPost() throws Exception{
		
		SqlSession sqlSession= factory.openSession();
		
		List<MarketPostDto> marketPostList= sqlSession.selectList("selectMarketPostAll");
		sqlSession.close();
		return marketPostList;
	}
	

}
