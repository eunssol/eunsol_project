package comment;

import java.sql.Timestamp;

import util.CommonVo;

//페이징처리 위해서 상속받기.
public class CommentVo extends CommonVo {
	private int no;
	private String content;
	private Timestamp regdate;
	private int board_no;
	private int user_no;
	private String tablename;
	private String name; //comment.xml에 출력된 결과의 열이름이 매핑이 되기 때문에 아리아스 걸어놓은 게 반드시 vo에 있어야함!!!!
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getRegdate() {
		return regdate;
	}
	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}
	public int getBoard_no() {
		return board_no;
	}
	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}
	public int getUser_no() {
		return user_no;
	}
	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}
	public String getTablename() {
		return tablename;
	}
	public void setTablename(String tablename) {
		this.tablename = tablename;
	}
	
	
}
