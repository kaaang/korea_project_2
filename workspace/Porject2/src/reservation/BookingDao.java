package reservation;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import util.Mybatis;


public class BookingDao {
	//세션(DB처리를 위한 작업단위) 열기
	private SqlSessionFactory factory= Mybatis.getSqlSession();
	
	// 등록
	public int insertBooking(BookingDto bookingDto) throws Exception{
		SqlSession sqlSession= factory.openSession();
		int insertBooking= sqlSession.insert("insertBooking", bookingDto);
		if(insertBooking>0) {
			sqlSession.commit();
		}else {
			sqlSession.rollback();
		}
		sqlSession.close();
		return insertBooking;
	}
	
	
	
	public List<BookingDto> selectBooking() throws Exception{
		
		SqlSession sqlSession= factory.openSession();
		//SQL문 사용
		List<BookingDto> selectBookingList= sqlSession.selectList("selectBookingList");
		sqlSession.close();
		return selectBookingList;
	}
	
	
	       
		public List<BookingDto> selectBookingOne() throws Exception{
			
			SqlSession sqlSession= factory.openSession();
			
			List<BookingDto> selectBookingOne= sqlSession.selectList("selectBookingOne");
			sqlSession.close();
			return selectBookingOne;
		}
		
		// 수정
		
		public int updateBooking(BookingDto bookingDto) throws Exception{
			
			SqlSession sqlSession= factory.openSession();
			int updateBooking= sqlSession.insert("updateBooking", bookingDto);
			if(updateBooking>0) {
				sqlSession.commit();
			}else {
				sqlSession.rollback();
			}
			return updateBooking;
		}
		// 삭제
		
		// 검색
		


    }


