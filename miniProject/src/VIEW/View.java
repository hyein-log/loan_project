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
		// manager_controller => 1.전체고객조회
		System.out.println(">> 전체고객조회.");
		for (MemberVO member : memberlist) {
			System.out.println("회사명 : " + member.getMEMBERNAME() + " - 연매출 : " + member.getANNUALSALES() + " - 직원수 : "
					+ member.getEMPNUM() + " - 회사설립일 : " + member.getESTDATE());
		}
	}

	public static void BankMember_view(List<BankMember_MemberVO> banklist) {
		// Banker_Controller => 1.전체고객조회
		System.out.println(">> 전체고객조회.");
		if(banklist.size()>0) {
		System.out.printf("%-8s\t %-10s\t %-10s\t %-3s\t %-10s\n","<이름>","<계좌>","<연매출>","<점수>", "<잔액>");
		for (BankMember_MemberVO bankmember : banklist) {
			System.out.printf("%-8s\t %-10s\t %-10s\t %-3d\t %-10s\n", bankmember.getMEMBERNAME(), bankmember.getACCOUNT(),decFormat.format(bankmember.getANNUALSALES()),bankmember.getSCORE() ,decFormat.format(bankmember.getBALANCE()));
		}
		}else {
			System.out.println(">> 고객이 없습니다.");
		}
	}

	public static void BankMemberandallloan_view(List<Loan_LoanApplay_Bankmember_Member_Loanproduct_JoinVO> lblist) {
		//2.대출고객 조회
		System.out.println(">> 대출고객조회.");
		int nowscore = 0, gradeid = 0;
		double interest = 0;
		if(lblist.size()>0) {
			System.out.printf("%-8s\t %-10s\t %-10s\t %-10s\t %-10s\n","<고객명>","<계좌번호>","<대출상품명>","<대출원금>", "<이자금액>");
			
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
				System.out.println(">> 대출고객이 없습니다.");
			}
		}
	}

	public static String bankselect_view(List<BankVO> banklist) {
		//은행사 전체 조회
		int i = 1;
		StringBuffer sb = new StringBuffer();
		for (BankVO bank : banklist) {
			sb.append("[" + i + "] " + bank.getBankname()+"\t");
			i++;
		}
		return sb.toString();
	}
	public static String bankselect_view(List<BankVO> banklist, int bankid) {
		//특정 bankid에 해당하는 은행사 조회
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
