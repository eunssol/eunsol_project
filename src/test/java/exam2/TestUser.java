package exam2;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import user.UserDao;
import user.UserVo;


//JUnit4에서는 RunWith를 해야해
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:config/context-servlet.xml"})
@WebAppConfiguration //어노테이션 추가
public class TestUser {
	
	 @Autowired
	   UserDao dao;
	   MockMvc mockMvc;
	   @Autowired
	   WebApplicationContext ctx;
	   MockHttpSession session;
	   
	   @Test
	   public void countTest() {
	      UserVo uv = new UserVo();
	      int count = dao.count(uv);
	      System.out.println(count);
	      assumeTrue(count>0);
	   }
	   
	   @Test
	   public void selectAllTest() {
		   UserVo uv = new UserVo();
	      List<UserVo> list = dao.selectAll(uv);
	      for (UserVo vo : list) {
	         System.out.println(vo.getName());
	      }
	      assertNotNull(list);
	      
	   }

}
