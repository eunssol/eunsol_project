package comment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service //이렇게 해야 빈에 등록이 되고 그래야 다른 곳에서 주입받을 수 있지
public class CommentServiceImpl implements CommentService {

	@Autowired  // CommentDao를 주입받아줘! --> 그래서 밑에 다 쓸 수 있음 페이지처리
	CommentDao dao;
	
	@Override
	public List<CommentVo> selectAll(CommentVo vo) {
		int totCount = dao.count(vo); // 총갯수
		// 총페이지수
		int totPage = totCount / vo.getPageRow();
		if (totCount % vo.getPageRow() > 0) totPage++;
		// 시작페이지
		int startPage = (vo.getReqPage()-1)/vo.getPageRange()
						*vo.getPageRange()+1;
		int endPage = startPage+vo.getPageRange()-1;
		if (endPage > totPage) endPage = totPage;
		
		vo.setStartPage(startPage);
		vo.setEndPage(endPage);
		vo.setTotCount(totCount);
		vo.setTotPage(totPage);
		return dao.selectAll(vo);
	}

	@Override
	public int insert(CommentVo vo) {
		return dao.insert(vo);
	}

	@Override
	public int delete(CommentVo vo) {
		return dao.delete(vo);
	}

}
