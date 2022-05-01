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
		System.out.println(">> �α����� �ʿ��մϴ�.");
		List<BankVO> banks = service.select_BANK();
		System.out.println(View.bankselect_view(banks));
		System.out.print("�α����� ����縦 �����ϼ���. >> ");
		int banknum = Integer.parseInt(scan.nextLine());
		BankVO bank = service.select_BANKbyNUM(banknum);
		int bankid = bank.getBankid();
		System.out.print("ID -->");
		String id = scan.nextLine();
		System.out.print("PASSWORD -->");
		String password = scan.nextLine();
		if (id.equals(ID) && password.equals(PASSWORD)) {
			System.out.println(">> �α��μ���.");
			System.out.println("�ȳ��ϼ���. " + View.bankselect_view(banks, bankid) + "�Դϴ�.");
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
			System.out.println(">> �α��ν���.");
		}
	}

	private static void bk4(int bankid) {
		// 4.������ ����
		System.out.println(">> ������ ����.");
		View.bankgrade_view(service.select_BANKGRADE(), bankid);
		System.out.print("������ ������ ����� �Է��ϼ���. >> ");
		int grade = Integer.parseInt(scan.nextLine());
		if(grade>0 && grade<8) {
		System.out.print("�������� �����ϼ���. >> ");
		double interest = Double.parseDouble(scan.nextLine());
		BankGradeVO bankGradeVO = service.select_BANKGRADEGETINTEREST(bankid, grade);
		bankGradeVO.setINTEREST(interest);
		int result = service.update_BANKGRADE(bankGradeVO);
		System.out.println(result > 0 ? ">> ���� �Ϸ�." : "���� ����");
		}
		else {
			System.out.println(">> �߸��� �����Դϴ�.");
		}
	}

	private static void bk3(int bankid) {
		// 3.�����ǰ [1. ��ȸ] | [2. �߰�] | [3. ����] | [4. ����]
		boolean check = true;
		System.out.println(">> �����ǰ �󼼺���.");
		while (check) {
			System.out.println("[1] ��ȸ [2] �߰� [3] ���� [4] ���� [0] �ڷΰ���");
			System.out.print("��ȣ�� �Է��ϼ���. >> ");
			int num = Integer.parseInt(scan.nextLine());
			switch (num) {
			case 1:
				System.out.println(">> ��ȸ ����.");
				System.out.println("    <�����ǰ��>          <�ִ���Ⱑ�ɱݾ�>");
				View.loanproduct_view(service.select_LOANPRODUCT(), bankid);
				System.out.println(">> ��ȸ ����.");
				break;
			case 2:
				System.out.println(">> �߰� ����");
				LoanProductVO loanProductVO = new LoanProductVO();
				loanProductVO.setBANKID(bankid);
				System.out.print("�����ǰ���� �Է��ϼ���. >> ");
				loanProductVO.setPRODUCTNAME(scan.nextLine());
				System.out.print("�ִ� ���Ⱑ���� �ݾ��� �Է��ϼ���. >> ");
				loanProductVO.setMAXAMOUNT(Long.parseLong(scan.nextLine()));
				System.out.print("�����ǰ�� ������ �Է��ϼ���. >> ");
				loanProductVO.setCONDITION(scan.nextLine());

				int result = service.insert_LOANPRODUCT(loanProductVO);
				System.out.println(result > 0 ? ">> �߰� �Ϸ�." : "�߰� ����");
				break;
			case 3:
				System.out.println(">> ���� ����.");
				View.loanproduct_view(service.select_LOANPRODUCT(), bankid);
				System.out.print("��ǰ�� �����ϼ���. >> ");
				int updatenum = Integer.parseInt(scan.nextLine());
				while(update(bankid, updatenum));
				break;
			case 4:
				// ����
				System.out.println(">> ���� ����.");
				View.loanproduct_view(service.select_LOANPRODUCT(), bankid);
				System.out.print("��ǰ�� �����ϼ���. >> ");
				updatenum = Integer.parseInt(scan.nextLine());
				loanproductid = View.loanproduct_select(bankid, updatenum);
				LoanProductVO loanProduct = service.select_LOANPRODUCTBYPRODUCTID(loanproductid);
				result = service.delete_LOANPRODUCT(loanProduct.getLOANPRODUCTID());
				System.out.println(result > 0 ? ">> ���� �Ϸ�." : "���� ����");
				break;

			default:
				System.out.println(">> �ڷΰ���.");
				check = false;
				break;
			}
		}
	}

	private static void bk2(int banknum) {
		// 2.����� ��ȸ -> ���⳻��(����� ��ȯ�� ������ ����Ƚ�� ��ǰ�̸� ) �̸�, ����, ����,
		View.BankMemberandallloan_view(service.select_LOAN_LOANAPPLY_LOANPRODUCT_BANKMEMBER_MEMBER_JOIN(banknum));
	}

	private static void bk1(int banknum) {
		// 1.��ü�� ��ȸ -> �̸� ���� ������ �ܾ� ����
		View.BankMember_view(service.select_BANKMEMBER_MEMBER_JOIN(banknum));
	}

	private static int Banker_Display() {
		System.out.println("1.��ü�� ��ȸ");
		System.out.println("2.����� ��ȸ");
		System.out.println("3.�����ǰ �󼼺���"); // ��ȸ �߰� ���� ����
		System.out.println("4.������ ����");
		System.out.println("0.�ڷ� ���� \t");
		System.out.print("�۾��� �����ϼ��� >>");
		return Integer.parseInt(scan.nextLine());
	}

	private static boolean update(int bankid, int updatenum) {
		loanproductid = View.loanproduct_select(bankid,updatenum); // ȭ������� ���� ������ ���ڿ� ���� ��ǰid�� �ٸ� �� �־ 
																// ���� id�� ã�� ������ �ʿ���
		LoanProductVO loanProduct = service.select_LOANPRODUCTBYPRODUCTID(loanproductid);
		System.out.print("[1] �ִ���Ⱑ�ɱݾ� ����  [2] �������� ���� [0] �ڷΰ��� >>");
		int select = Integer.parseInt(scan.nextLine());
		if (select == 1) {
			System.out.println("������ �ִ���Ⱑ�ɱݾ��� �Է��ϼ���. >> ");
			loanProduct.setMAXAMOUNT(Long.parseLong(scan.nextLine()));
			loanProduct.setCONDITION(loanProduct.getCONDITION());
		} else if (select == 2) {
			System.out.println("������ ���������� �Է��ϼ���. >> ");
			loanProduct.setMAXAMOUNT(loanProduct.getMAXAMOUNT());
			loanProduct.setCONDITION(scan.nextLine());
		} else if (select == 0) {
			System.out.println(">> �ڷΰ���.");
			return false;
		} else {
			System.out.println(">> �߸��� �����Դϴ�.");
			System.out.println(">> �ٽ� �������ּ���.");
			return true;
		}
		result = service.update_loanproduct(loanProduct);
		System.out.println(result > 0 ? ">> ���� �Ϸ�." : "���� ����");
		return false;
	}

}
