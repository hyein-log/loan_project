package CONTROLLER;

import java.util.List;
import java.util.Scanner;

import SERVICE.Service;
import VIEW.View;
import VO.BankGradeVO;
import VO.BankVO;
import VO.LoanProductVO;

public class Banker_Controller {

	static Service service = new Service();
	static Scanner scan = new Scanner(System.in);
	static String ID = "banker";
	static String PASSWORD = "b111";
	static boolean flag = true;
	static int result = 0;
	static int loanproductid = 0;
	public static void Banker_Mode() {
		System.out.println("=================");
		System.out.println("[Banker_Display ]");
		System.out.println("=================");
		System.out.println(">> 로그인이 필요합니다.");
		List<BankVO> banks = service.select_BANK();
		System.out.println(View.bankselect_view(banks));
		System.out.print("로그인할 은행사를 선택하세요. >> ");
		int banknum = Integer.parseInt(scan.nextLine());
		BankVO bank = service.select_BANKbyNUM(banknum);
		int bankid = bank.getBankid();
		System.out.print("ID -->");
		String id = scan.nextLine();
		System.out.print("PASSWORD -->");
		String password = scan.nextLine();
		if (id.equals(ID) && password.equals(PASSWORD)) {
			System.out.println(">> 로그인성공.");
			System.out.println("안녕하세요. " + View.bankselect_view(banks, bankid) + "입니다.");
			flag = true;
			while (flag) {
				int select_bno = Banker_Display();
				switch (select_bno) {
				case 1:
					bk1(bankid);
					break;
				case 2:
					bk2(bankid);
					break;
				case 3:
					bk3(bankid);
					break;
				case 4:
					bk4(bankid);
					break;
				case 0:
					flag = false;
					break;
				}
			}
		} else {
			System.out.println(">> 로그인실패.");
		}
	}

	private static void bk4(int bankid) {
		// 4.이자율 수정
		System.out.println(">> 이자율 수정.");
		View.bankgrade_view(service.select_BANKGRADE(), bankid);
		System.out.print("수정할 이자율 등급을 입력하세요. >> ");
		int grade = Integer.parseInt(scan.nextLine());
		if(grade>0 && grade<8) {
		System.out.print("이자율을 수정하세요. >> ");
		double interest = Double.parseDouble(scan.nextLine());
		BankGradeVO bankGradeVO = service.select_BANKGRADEGETINTEREST(bankid, grade);
		bankGradeVO.setINTEREST(interest);
		int result = service.update_BANKGRADE(bankGradeVO);
		System.out.println(result > 0 ? ">> 수정 완료." : "수정 실패");
		}
		else {
			System.out.println(">> 잘못된 선택입니다.");
		}
	}

	private static void bk3(int bankid) {
		// 3.대출상품 [1. 조회] | [2. 추가] | [3. 수정] | [4. 삭제]
		boolean check = true;
		System.out.println(">> 대출상품 상세보기.");
		while (check) {
			System.out.println("[1] 조회 [2] 추가 [3] 수정 [4] 삭제 [0] 뒤로가기");
			System.out.print("번호를 입력하세요. >> ");
			int num = Integer.parseInt(scan.nextLine());
			switch (num) {
			case 1:
				System.out.println(">> 조회 시작.");
				System.out.println("    <대출상품명>          <최대대출가능금액>");
				View.loanproduct_view(service.select_LOANPRODUCT(), bankid);
				System.out.println(">> 조회 종료.");
				break;
			case 2:
				System.out.println(">> 추가 시작");
				LoanProductVO loanProductVO = new LoanProductVO();
				loanProductVO.setBANKID(bankid);
				System.out.print("대출상품명을 입력하세요. >> ");
				loanProductVO.setPRODUCTNAME(scan.nextLine());
				System.out.print("최대 대출가능한 금액을 입력하세요. >> ");
				loanProductVO.setMAXAMOUNT(Long.parseLong(scan.nextLine()));
				System.out.print("대출상품의 조건을 입력하세요. >> ");
				loanProductVO.setCONDITION(scan.nextLine());

				int result = service.insert_LOANPRODUCT(loanProductVO);
				System.out.println(result > 0 ? ">> 추가 완료." : "추가 실패");
				break;
			case 3:
				System.out.println(">> 수정 시작.");
				View.loanproduct_view(service.select_LOANPRODUCT(), bankid);
				System.out.print("상품을 선택하세요. >> ");
				int updatenum = Integer.parseInt(scan.nextLine());
				while(update(bankid, updatenum));
				break;
			case 4:
				// 삭제
				System.out.println(">> 삭제 시작.");
				View.loanproduct_view(service.select_LOANPRODUCT(), bankid);
				System.out.print("상품을 선택하세요. >> ");
				updatenum = Integer.parseInt(scan.nextLine());
				loanproductid = View.loanproduct_select(bankid, updatenum);
				LoanProductVO loanProduct = service.select_LOANPRODUCTBYPRODUCTID(loanproductid);
				result = service.delete_LOANPRODUCT(loanProduct.getLOANPRODUCTID());
				System.out.println(result > 0 ? ">> 삭제 완료." : "삭제 실패");
				break;

			default:
				System.out.println(">> 뒤로가기.");
				check = false;
				break;
			}
		}
	}

	private static void bk2(int banknum) {
		// 2.대출고객 조회 -> 대출내역(대출금 반환일 시작일 연장횟수 상품이름 ) 이름, 계좌, 이자,
		View.BankMemberandallloan_view(service.select_LOAN_LOANAPPLY_LOANPRODUCT_BANKMEMBER_MEMBER_JOIN(banknum));
	}

	private static void bk1(int banknum) {
		// 1.전체고객 조회 -> 이름 계좌 연매출 잔액 점수
		View.BankMember_view(service.select_BANKMEMBER_MEMBER_JOIN(banknum));
	}

	private static int Banker_Display() {
		System.out.println("1.전체고객 조회");
		System.out.println("2.대출고객 조회");
		System.out.println("3.대출상품 상세보기"); // 조회 추가 수정 삭제
		System.out.println("4.이자율 수정");
		System.out.println("0.뒤로 가기 \t");
		System.out.print("작업을 선택하세요 >>");
		return Integer.parseInt(scan.nextLine());
	}

	private static boolean update(int bankid, int updatenum) {
		loanproductid = View.loanproduct_select(bankid,updatenum); // 화면상으로 보여 선택한 숫자와 실제 상품id는 다를 수 있어서 
																// 실제 id를 찾는 과정이 필요함
		LoanProductVO loanProduct = service.select_LOANPRODUCTBYPRODUCTID(loanproductid);
		System.out.print("[1] 최대대출가능금액 수정  [2] 대출조건 수정 [0] 뒤로가기 >>");
		int select = Integer.parseInt(scan.nextLine());
		if (select == 1) {
			System.out.println("수정할 최대대출가능금액을 입력하세요. >> ");
			loanProduct.setMAXAMOUNT(Long.parseLong(scan.nextLine()));
			loanProduct.setCONDITION(loanProduct.getCONDITION());
		} else if (select == 2) {
			System.out.println("수정할 대출조건을 입력하세요. >> ");
			loanProduct.setMAXAMOUNT(loanProduct.getMAXAMOUNT());
			loanProduct.setCONDITION(scan.nextLine());
		} else if (select == 0) {
			System.out.println(">> 뒤로가기.");
			return false;
		} else {
			System.out.println(">> 잘못된 선택입니다.");
			System.out.println(">> 다시 선택해주세요.");
			return true;
		}
		result = service.update_loanproduct(loanProduct);
		System.out.println(result > 0 ? ">> 수정 완료." : "수정 실패");
		return false;
	}

}
