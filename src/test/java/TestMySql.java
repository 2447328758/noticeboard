
import com.noticeboard.NoticeBoardApp;
//import org.apache.ibatis.session.SqlSession;
//import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = NoticeBoardApp.class)
public class TestMySql {

//    @Autowired
//    SqlSessionFactory sqlSessionFactory;
    @Test
    public void testConnection(){
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//        sqlSession.insert("insert into admin values(1,'xiaoming','123456'),(2,'xiaohong','123456')");
//        sqlSession.commit();
    }
}
