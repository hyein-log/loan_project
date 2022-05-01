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
		System.out.println(">> �α����� �ʿ��մϴ�.");
		System.out.print("ID -->");
		String id = scan.nextLine();
		System.out.print("PASSWORD -->");
		String password = scan.nextLine();
		if (id.equals(ID) && password.equals(PASSWORD)) {
			System.out.println(">> �α��μ���");
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
			System.out.println(">> �α��ν���.");
		}
	}

	private static void mng2() {
		//����� [1. ��ȸ] | [2. �߰�] | [3. ����] 
		System.out.println(">> ��ü ����� �󼼺���.");
		while(flag) {
			System.out.print("����� [1] ��ȸ [2] �߰� [3] ���� [0] �ڷΰ��� >> �۾��� �����ϼ��� >> ");
			int selnum = Integer.parseInt(scan.nextLine());
			switch (selnum) {
			case 1:
				//��ȸ
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
				//�߰�
				int bankid =0;
				System.out.print("�߰��� ������� �Է��ϼ��� >>");
				String bankname = scan.nextLine();
				BankVO bankvo = new BankVO();
				bankvo.setBankname(bankname);
				int result = service.insert_BANK(bankvo);
				System.out.println(result>0?">> �߰��Ǿ����ϴ�." : "*�߰����� �ʾҽ��ϴ�.*");
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
				//����
				System.out.print("������ ������� �Է��ϼ��� >>");
				result = service.delete_bank(scan.nextLine());
				System.out.println(result>0?">> �����Ǿ����ϴ�." : "*�������� �ʾҽ��ϴ�.*");
				break;

			default:
				System.out.println(">> â�� �����ϴ�.");
				flag = false;
				break;
			}
		}
	}

	private static void mng1() {
		// ��ü����ȸ
		View.Member_view(service.select_Member());
	}

	private static int Manager_Display() {
		System.out.println("1.��ü �� ��ȸ");
		System.out.println("2.��ü ����� �󼼺��� ");//[1]��ȸ [2]�߰� [3]����
		System.out.println("0.�ڷ� ����");
		System.out.print("�۾��� �����ϼ��� >>");
		return Integer.parseInt(scan.nextLine());
	}

}
