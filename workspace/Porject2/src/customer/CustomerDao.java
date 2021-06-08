package customer;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import util.Mybatis;

import java.util.List;

public class CustomerDao {
    private SqlSessionFactory factory = Mybatis.getSqlSession();


    public List<CustomerDto> selectAll() throws Exception {
        SqlSession sqlSession = factory.openSession();
        List<CustomerDto> list = sqlSession.selectList("");
        sqlSession.close();
        System.out.println("존잘신혁");
        return list;
    }

    public int update(CustomerDto dto) throws Exception{
        SqlSession sqlSession = factory.openSession();
        int result = -1;
        result = sqlSession.update("updateUser", dto);
        sqlSession.close();
        return result;
    }
    public List<CustomerDto> search(CustomerDto dto) throws Exception{
        SqlSession sqlSession = factory.openSession();

        List<CustomerDto> bikeDtoList=sqlSession.selectList("searchUser", dto);
        sqlSession.close();
        return bikeDtoList;
    }

}
