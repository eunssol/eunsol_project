package junit5;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import board.BoardDao;
import board.BoardVo;
import user.UserVo;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:config/context-servlet.xml"})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) //테스트 순서 지정
@WebAppConfiguration //어노테이션 추가
public class Test3 {
	
	@Autowired
	BoardDao dao;
	// 가상 MVC객체
	MockMvc mockMvc;
	@Autowired
	WebApplicationContext ctx;  //주입받고1 (주입받기 위히션 어노테이션 추가 필수)
	MockHttpSession session;
	
	
	@BeforeEach //각각 메소드 실행될 때 먼저 저 mockmvc를 먼저 실행시키
	void init() {
		mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build(); //객체생성2
		
		// 세션
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		UserVo vo = new UserVo();
		vo.setNo(999);
		session = new MockHttpSession();
		session.setAttribute("userInfo", vo);
		request.setSession(session);  //여기까지 해야 세션객체가 리퀘스트에 셋이 되어짐
		
	}
	
	
	/*
	  솔직히 dao테스트 하려고 많이 씀 서비스랑 컨트롤러는 굳이
	 */
	@Test
	@Order(1)  //순서지정
	void selectAllTest() {
		BoardVo bv = new BoardVo();
		bv.setReqPage(2);
		List<BoardVo> list = dao.selectAll(bv); //빈객체 = new BoardVo
		for(BoardVo vo : list) {
			System.out.println(vo.getTitle());
		}
	}
		
		@Test
		@Order(2)
		void detailTest() {
			System.out.println("detail먼저먼저?");
			BoardVo bv = new BoardVo();
			bv.setNo(2);
			BoardVo vo = dao.detail(bv); //빈객체 = new BoardVo
			System.out.println(vo.getTitle());
		
		
	}
		
		// /board/index.do로 접속 테스트
		@Test
		@Order(3)
		void boardIndex() throws Exception {
			RequestBuilder req = MockMvcRequestBuilders.get("/board/index.do"); // 컨텍스트패스 안 넣어 프론트앞단에서만 넣음
			mockMvc.perform(req);
			
		}
		
		@Test
		@Order(4)
		void boardIndex2() throws Exception {
			RequestBuilder req = MockMvcRequestBuilders.get("/board/index.do").param("reqPage", "abc"); // 컨텍스트패스 안 넣어 프론트앞단에서만 넣음
			try {
				mockMvc.perform(req);
			} catch (Exception e) {
				System.out.println(1);
			}
			
		}
		
		@Test
		@Order(5)
		void mypage() throws Exception {
			RequestBuilder req = MockMvcRequestBuilders.get("/user/mypage.do").session(session); // 세션객체가 있어야만하대
			mockMvc.perform(req);
			
		}
		
		@Test
		@Order(4)
		void login() throws Exception {
			RequestBuilder req = MockMvcRequestBuilders.post("/user/login.do").param("id", "asas").param("pwd", "asas"); // 컨텍스트패스 안 넣어 프론트앞단에서만 넣음
			try {
				mockMvc.perform(req);
			} catch (Exception e) {
				System.out.println(1);
			}
			
		}

}
