package thisisboard.main;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Application {
	public static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		// 콘솔 ui
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
					System.out.println("회원가입 화면으로 이동");
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
		System.out.print("아이디를 입력해주세요: ");
		String id = sc.nextLine();
		System.out.print("비밀번호를 입력해주세요: ");
		String pwd = sc.nextLine();
	}
	public static void register() {
		System.out.print("아이디를 입력해주세요: ");
		String id = sc.nextLine();
		System.out.print("비밀번호를 입력해주세요: ");
		String pwd = sc.nextLine();
	}
}
