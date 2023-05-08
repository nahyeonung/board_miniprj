package thisisboard.main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import thisisboard.model.BoardDataSource;
import thisisboard.model.dao.BoardDao;
import thisisboard.model.dao.MemberDao;
import thisisboard.model.vo.BoardVo;
import thisisboard.model.vo.MemberVo;

public class Application {
	public static Scanner sc = new Scanner(System.in);
	public static MemberVo member = new MemberVo();
	public static MemberDao mDao = new MemberDao();
	public static BoardDao bDao = new BoardDao();
	public static void main(String[] args) {		// 콘솔 ui
		while(true) {
			System.out.println("-------------------------------------------------");
			System.out.println("게시판에 오신걸 환영합니다.");
			System.out.println("1.로그인     2.회원가입   3.종료");
			System.out.print("메뉴 번호 입력: ");
			try {
				int num = sc.nextInt();
				sc.nextLine();
				switch(num) {
				case 1:
					login();
					break;
				case 2:
					register();
					break;
				case 3:
					System.exit(0);
					break;
				default:
					System.out.println("그런 메뉴는 없어요.");
				}
			}catch(InputMismatchException e) {
				System.out.println("틀렸습니다.");
			}	
		}
	}
	public static void login() {
		Connection con = null;
		System.out.print("아이디를 입력해주세요: ");
		String id = sc.nextLine();
		System.out.print("비밀번호를 입력해주세요: ");
		String pwd = sc.nextLine();
		String sql = "select userid, username, userpassword from users where userid=? and userpassword=?";
		try {
			con = BoardDataSource.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, id);
			stmt.setString(2, pwd);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				member.setUserid(rs.getString(1));
				member.setUsername(rs.getString(2));
				member.setUserpassword(rs.getString(3));
				mainPage();
			}else {
				System.out.println("아이디 또는 비밀번호가 틀렸습니다.");
			}
			System.out.println("출력끝");
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			if(con!=null) {
				try {con.close();}catch(Exception e) {}
			}
		}
	}
	public static void register() {
		Connection con = null;
		System.out.print("아이디를 입력해주세요: ");
		member.setUserid(sc.nextLine());
		System.out.print("비밀번호를 입력해주세요: ");
		member.setUserpassword(sc.nextLine());
		System.out.print("이름을 입력해주세요: ");
		member.setUsername(sc.nextLine());
		mDao.insertMember(member);
	}
	public static void mainPage() {
		while(true) {
			System.out.println("---------------------------------------------------");
			System.out.print(member.getUserid() + "님");
			System.out.println("kosa 게시판에 오신 걸 환영합니다.");
			try {
				ArrayList<BoardVo> list=new ArrayList<BoardVo>();
				BoardDao bDao = new BoardDao();
				list=bDao.getAllBaordList();
				for(BoardVo vo:list) {
					System.out.println(vo);
				}
			} catch (RuntimeException e) {
				System.out.println(e.getMessage());
			}
			
			System.out.println("1.내 글 확인 2.글 쓰기 3.로그아웃");
			System.out.println("---------------------------------------------------");
			System.out.print("번호 입력: ");
			int num = sc.nextInt();
			sc.nextLine();
			switch(num) {
			case 1: System.out.println("내 글 확인 페이지"); 
				myBoard(member.getUserid());
				break;
			case 2: System.out.println("글 쓰기");
				write(member.getUserid());
				break;
			case 3: {
				System.out.println("로그아웃 되었습니다.");
				break;
			}
			default: System.out.println("번호를 잘못 입력하셨습니다.");
			}
			if(num == 3) {
				break;
			}
		}
		
	}
	private static void myBoard(String username) {
		// TODO Auto-generated method stub
		try {
			ArrayList<BoardVo> list=new ArrayList<BoardVo>();
			BoardDao bDao = new BoardDao();
			list=bDao.getMyBoardList(username);
			for(BoardVo vo:list) {
				System.out.println(vo);
			}
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("1.글 수정  2.글 삭제");
		System.out.print("번호를 입력하세요: ");
		int num = sc.nextInt();
		sc.nextLine();
		int bno;
		switch(num){
		case 1:
			System.out.print("수정할 글 번호: ");
			bno=sc.nextInt();
			sc.nextLine();
			updateBoard(bno);
			break;
		case 2:
			System.out.println("글 삭제할 게시물 번호");
			bno=sc.nextInt();
			delete(bno);
			break;
		}
	}
	private static void updateBoard(int bno) {
		BoardVo bVo = new BoardVo();
		bVo.setBno(bno);
		System.out.print("제목을 입력하세요: ");
		bVo.setBtitle(sc.nextLine());
		System.out.print("내용을 입력하세요: ");
		bVo.setBcontent(sc.nextLine());
		try {
			bDao.updateMyBoard(bVo);
			System.out.println("zz");
		}catch(RuntimeException e) {
			System.out.println(e.getMessage());
		}
		
	}
	private static void delete(int bno) {
		try {
			bDao.deleteBoard(bno);
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
		}
	}
	private static void write(String userid) {
		BoardVo bVo = new BoardVo();
		bVo.setBwriter(userid);
		System.out.print("제목을 입력하세요: ");
		bVo.setBtitle(sc.nextLine());
		System.out.print("내용을 입력하세요: ");
		bVo.setBcontent(sc.nextLine());
		try {
			bDao.insertMyboard(bVo);
		}catch(RuntimeException e) {
			System.out.println(e.getMessage());
		}
	}
}
