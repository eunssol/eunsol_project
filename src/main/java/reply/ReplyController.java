package reply;

import java.io.File;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import comment.CommentService;
import comment.CommentVo;

@Controller
public class ReplyController {

	@Autowired
	ReplyService service;
	@Autowired
	CommentService cService;
	
	static final String TABLENAME = "reply";
	
	@RequestMapping("/reply/index.do")
	public String index(Model model, ReplyVo vo) {
		model.addAttribute("list", service.selectAll(vo));//list란 이름으로 전체 데이터가 dao에 담겨서 모델에 담김(=request에 담김=스프링이 담아줌)
		return "reply/index";
	}
	
	@RequestMapping("/reply/detail.do")
	public String detail(Model model, ReplyVo vo, CommentVo cv) {
		model.addAttribute("vo", service.deatil(vo));

		return "reply/detail";
	}
	
	@RequestMapping("/reply/write.do")
	public String write(Model model, ReplyVo vo) {
		return "reply/write";
	}
	
	@RequestMapping("/reply/reply.do")
	public String reply(Model model, ReplyVo vo) {  //어떤 용도로 만들어야해 얘를 ? -> jsp는 당연히 만들어야하고, 컨트롤러에서 어떤 행위를 해야하는지
		//model.addAttribute("vo", service.deatil(vo));	//gno, ono, nested 이 세개의 값이 필요해 무조건
		
		ReplyVo rv = service.deatil(vo);
		model.addAttribute("gno", rv.getGno());
		model.addAttribute("ono", rv.getOno());
		model.addAttribute("nested", rv.getNested());
		return "reply/reply";						
													//컨트롤러에 해도 되고 안 해도 돼
	}
	
	@RequestMapping("/reply/insert.do")
	public String insert(Model model, ReplyVo vo, 
						@RequestParam MultipartFile file, HttpServletRequest req) {
		//service.insert(vo, filename, req)
		if (!file.isEmpty()) { // 첨부파일이 있으면
			try {
				String org = file.getOriginalFilename(); // 원본파일명
				String ext = ""; //확장자
				
				ext = org.substring(org.lastIndexOf("."));
				String real = new Date().getTime()+ext; // 서버에 저장할 파일명
	//			System.out.println("org:"+org);
	//			System.out.println("real:"+real);
				// 파일 저장
				String path = req.getRealPath("/upload/"); // 경로
				file.transferTo(new File(path+real)); // 경로+파일명 저장
				// vo에 set
				vo.setFilename_org(org);
				vo.setFilename_real(real);
			} catch (Exception e) {
				
			}
		}
		int r = service.insert(vo);
		// r > 0 : 정상 -> alert -> 목록으로 이동
		// r == 0 : 비정상 -> alert -> 이전페이지로 이동
		if (r > 0) {
			model.addAttribute("msg", "정상적으로 등록되었습니다.");
			model.addAttribute("url", "index.do");
		} else {
			model.addAttribute("msg", "등록실패");
			model.addAttribute("url", "write.do");
		}
		return "include/alert";
	}
	
	@RequestMapping("/reply/insertReply.do")
	public String insertReply(Model model, ReplyVo vo, 
			@RequestParam MultipartFile file, HttpServletRequest req) {
		if (!file.isEmpty()) { // 첨부파일이 있으면
			try {
				String org = file.getOriginalFilename(); // 원본파일명
				String ext = ""; //확장자
				ext = org.substring(org.lastIndexOf("."));
				String real = new Date().getTime()+ext; // 서버에 저장할 파일명
				// 파일 저장
				String path = req.getRealPath("/upload/"); // 경로
				file.transferTo(new File(path+real)); // 경로+파일명 저장
				// vo에 set
				vo.setFilename_org(org);
				vo.setFilename_real(real);
			} catch (Exception e) {
				
			}
		}
		int r = service.insertReply(vo);
		// r > 0 : 정상 -> alert -> 목록으로 이동
		// r == 0 : 비정상 -> alert -> 이전페이지로 이동
		if (r > 0) {
			model.addAttribute("msg", "정상적으로 등록되었습니다.");
			model.addAttribute("url", "index.do");
		} else {
			model.addAttribute("msg", "등록실패");
			model.addAttribute("url", "write.do");
		}
		return "include/alert";
	}
	
	@RequestMapping("/reply/edit.do")
	public String edit(Model model, ReplyVo vo) {
		model.addAttribute("vo", service.edit(vo));
		return "reply/edit";
	}
	
	@RequestMapping("/reply/update.do")
	public String update(Model model, ReplyVo vo, 
						@RequestParam MultipartFile file, HttpServletRequest req) {
		//service.insert(vo, filename, req)
		if (!file.isEmpty()) { // 첨부파일이 있으면
			try {
				String org = file.getOriginalFilename(); // 원본파일명
				String ext = ""; //확장자
				
				ext = org.substring(org.lastIndexOf("."));
				String real = new Date().getTime()+ext; // 서버에 저장할 파일명
	//			System.out.println("org:"+org);
	//			System.out.println("real:"+real);
				// 파일 저장
				String path = req.getRealPath("/upload/"); // 경로
				file.transferTo(new File(path+real)); // 경로+파일명 저장
				// vo에 set
				vo.setFilename_org(org);
				vo.setFilename_real(real);
			} catch (Exception e) {
				
			}
		}
		int r = service.update(vo);
		// r > 0 : 정상 -> alert -> 목록으로 이동
		// r == 0 : 비정상 -> alert -> 이전페이지로 이동
		if (r > 0) {
			model.addAttribute("msg", "정상적으로 수정되었습니다.");
			model.addAttribute("url", "index.do");
		} else {
			model.addAttribute("msg", "수정실패");
			model.addAttribute("url", "edit.do?no="+vo.getNo());
		}
		return "include/alert";
	}
	
	@RequestMapping("/reply/delete.do")
	public String delete(Model model, ReplyVo vo, HttpServletRequest req) {
		int r = service.delete(vo);
		if (r > 0) {
			model.addAttribute("result", "true");
		} else {
			model.addAttribute("result", "false");
		}
		return "include/result";
	}
	
	@RequestMapping("/reply/comment/insert.do")
	public String insert(Model model, CommentVo vo) {
		vo.setTablename(TABLENAME);

		int r = cService.insert(vo);
		// r > 0 : 정상 -> alert -> 목록으로 이동
		// r == 0 : 비정상 -> alert -> 이전페이지로 이동
		if (r > 0) {
			model.addAttribute("result", "true");
		} else {
			model.addAttribute("result", "false");
		}
		return "include/result";   //ajax로 호출할 거라서 응답만~ 잘 됐는지 안 됐는지만 확인하려고~result에~
	}
	
	@RequestMapping("/reply/comment/list.do")
	public String commentList(Model model, CommentVo cv) {
		cv.setTablename(TABLENAME);
		model.addAttribute("list", cService.selectAll(cv));
		return "include/comment";   //ajax로 호출할 거라서 응답만~ 잘 됐는지 안 됐는지만 확인하려고~result에~
	}
}
