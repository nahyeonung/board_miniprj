package thisisboard.main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
			bDao.getAllBaordList();
			System.out.println("1.내 글 확인 2.글 쓰기");
			System.out.println("---------------------------------------------------");
			System.out.print("번호 입력: ");
			int num = sc.nextInt();
			sc.nextLine();
			switch(num) {
			case 1: System.out.println("내 글 확인 페이지"); break;
			case 2: System.out.println("글 쓰기"); break;
			case 3: System.exit(0); 
			default: System.out.println("번호를 잘못 입력하셨습니다.");
			}
		}
		
	}
}
