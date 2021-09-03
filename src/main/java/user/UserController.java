package user;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import board.BoardService;
import board.BoardVo;

@Controller
public class UserController {

	@Autowired
	UserService service;
	@Autowired
	BoardService boardService;
	
//	@RequestMapping("/admin/user/index.do")
//	public String index(Model model, UserVo vo) {
//		model.addAttribute("list", service.selectAll(vo));
//		return "admin/user/index";
//	}
	
//	@RequestMapping("/board/detail.do")
//	public String detail(Model model, UserVo vo) {
//		model.addAttribute("vo", service.deatil(vo));
//		return "board/detail";
//	}
	
	@RequestMapping("/user/join.do")
	public String write(Model model, UserVo vo) {
		return "user/join";
	}
	
	// form에서 submit용 (일단 남겨둠)
	@RequestMapping("/user/insert.do")
	public String insert(Model model, UserVo vo, HttpServletRequest req) {
		int r = service.insert(vo);
		// r > 0 : 정상 -> alert -> 목록으로 이동
		// r == 0 : 비정상 -> alert -> 이전페이지로 이동
		if (r > 0) {
			model.addAttribute("msg", "정상적으로 등록되었습니다.");
			model.addAttribute("url", "/pro/sample/index.do");
		} else {
			model.addAttribute("msg", "등록실패");
			model.addAttribute("url", "join.do");
		}
		return "include/alert";
	}
	
	//ajax용
	@RequestMapping("/user/insertAjax.do")
	public String insertAjax(Model model, UserVo vo, HttpServletRequest req) {
		int r = service.insert(vo);

		if (r > 0) {
			model.addAttribute("result", "true");
		} else {
			model.addAttribute("result", "false");
		}
		return "include/result";
	}
	
//	@RequestMapping("/board/edit.do")
//	public String edit(Model model, UserVo vo) {
//		model.addAttribute("vo", service.edit(vo));
//		return "board/edit";
//	}
	
//	@RequestMapping("/board/update.do")
//	public String update(Model model, UserVo vo, HttpServletRequest req) {
//		int r = service.update(vo);
//		// r > 0 : 정상 -> alert -> 목록으로 이동
//		// r == 0 : 비정상 -> alert -> 이전페이지로 이동
//		if (r > 0) {
//			model.addAttribute("msg", "정상적으로 수정되었습니다.");
//			model.addAttribute("url", "index.do");
//		} else {
//			model.addAttribute("msg", "수정실패");
//			model.addAttribute("url", "edit.do?no="+vo.getNo());
//		}
//		return "include/alert";
//	}
	
//	@RequestMapping("/board/delete.do")
//	public String delete(Model model, UserVo vo, HttpServletRequest req) {
//		int r = service.delete(vo);
//		if (r > 0) {
//			model.addAttribute("result", "true");
//		} else {
//			model.addAttribute("result", "false");
//		}
//		return "include/result";
//	}
	
	@RequestMapping("/user/isDuplicateId.do")
	public String isDuplcateId(Model model, @RequestParam String id) {
		if (service.isDuplicateId(id)==0) { //중복아님
			model.addAttribute("result", "false");
		} else {
			model.addAttribute("result", "true");
		}
		return "include/result";
	}
	
	@RequestMapping(value = "/user/login.do", method = RequestMethod.GET)
	public String loginForm(UserVo vo, @CookieValue(value="cookieId", required=false) Cookie cookie) {
		if(cookie != null) { //쿠키가 있다.
			vo.setId(cookie.getValue());
			
		}
		return "user/login";
	}
	
	@RequestMapping(value = "/user/login.do" , method = RequestMethod.POST)
	public String loginPro(Model model, UserVo vo, HttpSession sess, HttpServletResponse res, HttpServletRequest req) {
		UserVo uv = service.login(vo); //로그인 하면 여기에 담기겠지
		if(uv==null) {
			model.addAttribute("msg", "아이디 비밀번호가 올바르지 않습니다.");
			model.addAttribute("url", "login.do");
			return "include/alert";
		} else { //로그인이 되면
			
			sess.setAttribute("userInfo", uv);
			// 쿠키에 저장
			Cookie cookie = new Cookie("cookieId", vo.getId()); //가지고올 때 cookieId 로 . vo.getId() -> 
			cookie.setPath("/");
			if ("check".equals(vo.getCheckId())) {
				cookie.setMaxAge(60*60*24*365);
			} else {
				cookie.setMaxAge(0);
			}
			res.addCookie(cookie);
			return "redirect:/board/index.do";
		}
		
	}


	@RequestMapping(value = "/user/logout.do", method = RequestMethod.GET)
	public String logout(Model model,HttpSession sess ) {
		sess.invalidate(); //세션을 다 날려버리고 이동시켜야함
		model.addAttribute("msg", "로그아웃되었씁니다.");
		model.addAttribute("url", "/pro/board/index.do");
		return "include/alert";
	}
	
	@RequestMapping("user/mypage.do")
	public String mypage(Model model, BoardVo vo, HttpSession sess) {
		vo.setUser_no(((UserVo)sess.getAttribute("userInfo")).getNo());
		model.addAttribute("list", boardService.selectAll(vo));
		return "user/mypage";
	}
	
	@RequestMapping(value="user/searchId.do", method=RequestMethod.GET)
	public String searchId(Model model, UserVo vo) {
		return "user/searchId";
	}
	@RequestMapping(value="user/searchId.do", method=RequestMethod.POST)
	public String searchId2(Model model, UserVo vo) {//이름과 이메일을 전송 받을 거라서 커멘드객체로서 UserVo vo 반드시 있어야해 여기에 담겨있어~
		UserVo uv = service.searchId(vo); //이름과 이메일을 맞게 넣었으면 vo에 값이 담길 거고 틀렸으면 null이 담길 거야
		//사실 *로 조회했기 때문에 뭐든 다 들어있을 건데 우리가 필요한 건 뭐? 아이디.
		String id = "";
		if(uv != null) {
			id = uv.getId(); //내가 찾고자하는 아이디가 여기 들어갈 거야 잘 입력만 했으면
		}
		
		model.addAttribute("result", id); //result라는 이름으로 id를 담아두면 ..출력될 거임? 
		return "include/result";
	}
	
	@RequestMapping(value="user/searchPwd.do", method=RequestMethod.GET)
	public String searchPwd(Model model, UserVo vo) {
		return "user/searchPwd";
	}
	@RequestMapping(value="user/searchPwd.do", method=RequestMethod.POST)
	public String searchPwd2(Model model, UserVo vo) {
		UserVo uv = service.searchPwd(vo);
		if (uv != null) {
			model.addAttribute("result", "ok");
		} else {
			model.addAttribute("result", "no");
			
		}
		return "include/result";
	}
	
	


}
