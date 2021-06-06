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
		int insertMarketPost= sqlSession.insert("insertMarketPost", marketDto);
		if(insertMarketPost>0) {
			sqlSession.commit();
		}else {
			sqlSession.rollback();
		}
		sqlSession.close();
		return insertMarketPost;
	}
	
	// 목록 출력
	public List<MarketPostDto> selectMarketPostList() throws Exception{
		
		SqlSession sqlSession= factory.openSession();
		
		List<MarketPostDto> marketPostList= sqlSession.selectList("marketPostList");
		sqlSession.close();
		return marketPostList;
	}
	
	// 상세보기
	public List<MarketPostDto> selectMarketPostOne() throws Exception{
		
		SqlSession sqlSession= factory.openSession();
		
		List<MarketPostDto> marketPostDetail= sqlSession.selectList("selectMarketPostOne");
		sqlSession.close();
		return marketPostDetail;
	}
	
	// 수정
	
	// 삭제
	
	// 검색
	

}
