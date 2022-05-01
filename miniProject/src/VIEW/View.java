package VIEW;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Scanner;

import SERVICE.Service;
import VO.BankGradeVO;
import VO.BankMember_MemberVO;
import VO.BankVO;
import VO.GradeVO;
import VO.LoanProductVO;
import VO.Loan_LoanApplay_Bankmember_Member_Loanproduct_JoinVO;
import VO.MemberVO;

public class View {
	static Scanner scan = new Scanner(System.in);
	static Service service = new Service();
	static DecimalFormat decFormat = new DecimalFormat("###,###");
	public static void Member_view(List<MemberVO> memberlist) {
		// manager_controller => 1.��ü����ȸ
		System.out.println(">> ��ü����ȸ.");
		for (MemberVO member : memberlist) {
			System.out.println("ȸ��� : " + member.getMEMBERNAME() + " - ������ : " + member.getANNUALSALES() + " - ������ : "
					+ member.getEMPNUM() + " - ȸ�缳���� : " + member.getESTDATE());
		}
	}

	public static void BankMember_view(List<BankMember_MemberVO> banklist) {
		// Banker_Controller => 1.��ü����ȸ
		System.out.println(">> ��ü����ȸ.");
		if(banklist.size()>0) {
		System.out.printf("%-8s\t %-10s\t %-10s\t %-3s\t %-10s\n","<�̸�>","<����>","<������>","<����>", "<�ܾ�>");
		for (BankMember_MemberVO bankmember : banklist) {
			System.out.printf("%-8s\t %-10s\t %-10s\t %-3d\t %-10s\n", bankmember.getMEMBERNAME(), bankmember.getACCOUNT(),decFormat.format(bankmember.getANNUALSALES()),bankmember.getSCORE() ,decFormat.format(bankmember.getBALANCE()));
		}
		}else {
			System.out.println(">> ���� �����ϴ�.");
		}
	}

	public static void BankMemberandallloan_view(List<Loan_LoanApplay_Bankmember_Member_Loanproduct_JoinVO> lblist) {
		//2.����� ��ȸ
		System.out.println(">> �������ȸ.");
		int nowscore = 0, gradeid = 0;
		double interest = 0;
		if(lblist.size()>0) {
			System.out.printf("%-8s\t %-10s\t %-10s\t %-10s\t %-10s\n","<����>","<���¹�ȣ>","<�����ǰ��>","<�������>", "<���ڱݾ�>");
			
		}
		for (Loan_LoanApplay_Bankmember_Member_Loanproduct_JoinVO lb : lblist) {
			nowscore = lb.getNOWSCORE() / 100 * 100;
			GradeVO grade = service.select_GRADEGETID(nowscore);
			gradeid = grade.getGradeid();
			BankGradeVO bankinterest = service.select_BANKGRADEGETINTEREST(lb.getBANKID(), gradeid);
			interest = bankinterest.getINTEREST();
			if (lb.getAMOUNT() > 0) {
				System.out.printf("%-8s\t %-10s\t %-10s\t %-3s\t %-10s\n",lb.getMEMBERNAME(), lb.getACCOUNT(),lb.getPRODUCTNAME(),decFormat.format(lb.getAMOUNT()),decFormat.format((lb.getAMOUNT() * interest / 100)));
				
			}else {
				System.out.println(">> ������� �����ϴ�.");
			}
		}
	}

	public static String bankselect_view(List<BankVO> banklist) {
		//����� ��ü ��ȸ
		int i = 1;
		StringBuffer sb = new StringBuffer();
		for (BankVO bank : banklist) {
			sb.append("[" + i + "] " + bank.getBankname()+"\t");
			i++;
		}
		return sb.toString();
	}
	public static String bankselect_view(List<BankVO> banklist, int bankid) {
		//Ư�� bankid�� �ش��ϴ� ����� ��ȸ
		BankVO bank = null;
		loop : for (BankVO banks : banklist) {
			if(banks.getBankid() == bankid) {
				bank = banks;
				break loop;
			}
		}
		return bank.getBankname();
	}
	public static void bankgrade_view(List<BankGradeVO> bankgrade, int bankid) {
		
		for (int i = 1; i < 8; i++) {
			System.out.printf("<%d>\t",i);
		}
		System.out.println();
		for (BankGradeVO bg : bankgrade) {
			if (bg.getBANKID() == bankid) {
				System.out.printf("%-3.2f\t",bg.getINTEREST());
			}
		}
		System.out.println();
	}

	public static void loanproduct_view(List<LoanProductVO> loanproductlist, int bankid) {
		int i = 1;
		for (LoanProductVO loanproduct : loanproductlist) {
			if (loanproduct.getBANKID() == bankid) {
				System.out.printf("[%d] %-10s \t %-10s\n", i, loanproduct.getPRODUCTNAME() ,decFormat.format(loanproduct.getMAXAMOUNT()));
				i++;
			}
		}
	}

	public static void loanproduct_view(List<LoanProductVO> loanproductlist) {
		int i = 1;
		for (LoanProductVO loanproduct : loanproductlist) {
			System.out.println("[" + i + "] " + loanproduct.getPRODUCTNAME() + "  " + decFormat.format(loanproduct.getMAXAMOUNT()));
			i++;
		}
	}

	public static int loanproduct_select(int bankid, int updatenum) {
		LoanProductVO lp = service.select_LOANPRODUCTBYNUM(bankid, updatenum);
		int loanproductid = lp.getLOANPRODUCTID();
		return loanproductid;
	}
}
