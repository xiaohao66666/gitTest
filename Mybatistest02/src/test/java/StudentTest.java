import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.softeem.bean.Student;
import com.softeem.bean.StudentExample;
import com.softeem.mapper.StudentMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import org.omg.CORBA.MARSHAL;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class StudentTest {
    SqlSession sqlSession = null ;

    @Before
    public void init() throws IOException {
        //读取MyBatis的核心配置文件
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        //创建SqlSessionFactoryBuilder对象
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        //通过核心配置文件所对应的字节输入流创建工厂类SqlSessionFactory，生产SqlSession对象
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(is);
        //创建SqlSession对象，此时通过SqlSession对象所操作的sql都必须手动提交或回滚事务
        // sqlSession = sqlSessionFactory.openSession();
        //创建SqlSession对象，此时通过SqlSession对象所操作的sql都会自动提交
        sqlSession = sqlSessionFactory.openSession(true);
    }
    @Test
    public void test() {
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        StudentExample example=new StudentExample();
        StudentExample.Criteria criteria = example.createCriteria();
        criteria.andNameLike("%小%");
        criteria.andGenderEqualTo("男");
        criteria.andBirthdateGreaterThanOrEqualTo(new Date(2000-1900,1,1));
        PageHelper.startPage(1,4);//分页
        List<Student> students = mapper.selectByExample(example);
        PageInfo pageInfo=new PageInfo(students);
        System.out.println(pageInfo.getList());
    }
    @Test
    public void test2(){
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        PageHelper.startPage(1,4);
        List<Student> students = mapper.selectStuAndClassAll();
        PageInfo pageInfo=new PageInfo(students);
        System.out.println(pageInfo.getList());
    }
}
