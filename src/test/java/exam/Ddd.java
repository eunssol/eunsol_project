package exam;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import board.BoardDao;
import board.BoardVo;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:config/context-servlet.xml"})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // 순서 지정
@WebAppConfiguration
public class Ddd {
   
   @Autowired
   BoardDao dao;
   // 가상MVC객체
   MockMvc mockMvc;
   @Autowired
   WebApplicationContext ctx; // 어노테이션 추가
   MockHttpSession session;
   
   @Test
   @Order(1)
   void countTest() {
      BoardVo bv = new BoardVo();
      int count = dao.count(bv);
      System.out.println(count);
      assumeTrue(count>0);
   }
   
   @Test
   @Order(2)
   void selectAllTest() {
      BoardVo bv = new BoardVo();
      List<BoardVo> list = dao.selectAll(bv);
      for (BoardVo vo : list) {
         System.out.println(vo.getTitle());
      }
      assertNotNull(list);
      
   }
}