package CONTROLLER;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import SERVICE.Service;
import VIEW.View;
import VO.BankGradeVO;
import VO.BankVO;
import VO.GradeVO;

public class Manager_Controller {
	static Service service = new Service();
	static Scanner scan = new Scanner(System.in);
	static String ID = "manager";
	static String PASSWORD = "m111";
	static boolean flag = true;
	public static void Manager_Mode() {
		System.out.println("==================");
		System.out.println("[ Manager_Display ]");
		System.out.println("==================");
		System.out.println(">> 로그인이 필요합니다.");
		System.out.print("ID -->");
		String id = scan.nextLine();
		System.out.print("PASSWORD -->");
		String password = scan.nextLine();
		if (id.equals(ID) && password.equals(PASSWORD)) {
			System.out.println(">> 로그인성공");
			boolean flag = true;
			while (flag) {
				int select_bno = Manager_Display();
				switch (select_bno) {
				case 1:
					mng1();
					break;
				case 2:
					mng2();
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

	private static void mng2() {
		//은행사 [1. 조회] | [2. 추가] | [3. 삭제] 
		System.out.println(">> 전체 은행사 상세보기.");
		while(flag) {
			System.out.print("은행사 [1] 조회 [2] 추가 [3] 삭제 [0] 뒤로가기 >> 작업을 선택하세요 >> ");
			int selnum = Integer.parseInt(scan.nextLine());
			switch (selnum) {
			case 1:
				//조회
			//	System.out.println(View.bankselect_view(service.select_BANK()));
				List<BankGradeVO> bankgradeVOs = service.select_BANKGRADE();
				List<GradeVO> gradeVOs=service.select_GRADE();
				List<BankVO>bankVOs= service.select_BANK();
				ArrayList<Integer> arrbank = new ArrayList<>();
				for(BankVO bank:bankVOs) {
					arrbank.add(bank.getBankid());
				}
				ArrayList<Integer> arr = new ArrayList<>();
				for(GradeVO grade:gradeVOs) {
					arr.add(grade.getGradeid());
				}
				int d =0;
				for(int i=0; i<arrbank.size(); i++ ) {
					System.out.print(bankVOs.get(i).getBankname()+"---");
					for(int j=0; j<arr.size(); j++) {
						System.out.printf("["+(j+1)+"] "+bankgradeVOs.get(d).getINTEREST()+" \t ");
						d++;
					}
					System.out.println();
				}
				break;
			case 2:
				//추가
				int bankid =0;
				System.out.print("추가할 은행명을 입력하세요 >>");
				String bankname = scan.nextLine();
				BankVO bankvo = new BankVO();
				bankvo.setBankname(bankname);
				int result = service.insert_BANK(bankvo);
				System.out.println(result>0?">> 추가되었습니다." : "*추가되지 않았습니다.*");
				BankVO bank= service.select_BANKBYNAME(bankname);
				bankid = bank.getBankid();			
				for(int i=1; i<8; i++) {
					BankGradeVO bv = new BankGradeVO();
					bv.setBANKID(bankid);
					bv.setGRADEID(i);
					service.insert_BANKGRADE(bv);
				}
				break;
			case 3:
				//삭제
				System.out.print("삭제할 은행명을 입력하세요 >>");
				result = service.delete_bank(scan.nextLine());
				System.out.println(result>0?">> 삭제되었습니다." : "*삭제되지 않았습니다.*");
				break;

			default:
				System.out.println(">> 창을 나갑니다.");
				flag = false;
				break;
			}
		}
	}

	private static void mng1() {
		// 전체고객조회
		View.Member_view(service.select_Member());
	}

	private static int Manager_Display() {
		System.out.println("1.전체 고객 조회");
		System.out.println("2.전체 은행사 상세보기 ");//[1]조회 [2]추가 [3]삭제
		System.out.println("0.뒤로 가기");
		System.out.print("작업을 선택하세요 >>");
		return Integer.parseInt(scan.nextLine());
	}

}
