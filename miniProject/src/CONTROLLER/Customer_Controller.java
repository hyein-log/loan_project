package CONTROLLER;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import SERVICE.Service;
import Util.DateUtil;
import VIEW.View;
import VO.BankGradeVO;
import VO.BankMemberVO;
import VO.BankMember_MemberVO;
import VO.BankVO;
import VO.GradeVO;
import VO.LoanApplyVO;
import VO.LoanProductVO;
import VO.LoanVO;
import VO.Loan_Bankmember_Loanproduct_JoinVO;
import VO.Loan_LoanApply_Bankmember_LoanproductVO;
import VO.MemberVO;

public class Customer_Controller {
	static Service service = new Service();
	static Scanner scan = new Scanner(System.in);
	static boolean flag = true;
	static MemberVO mem = null;
	static BankMemberVO bankmem = null;
	static List<BankMemberVO> bankmems = new ArrayList<>();
	static DecimalFormat decFormat = new DecimalFormat("###,###");
	static List<BankVO> bankVOs = service.select_BANK();
	static List<BankMemberVO> bankmemberlist = new ArrayList<>();
	static List<BankMemberVO> bankMemberVO = new ArrayList<>();

	public static void public_Mode() {
		System.out.println("==================");
		System.out.println("[ Customer_Display ]");
		System.out.println("==================");
		boolean flag = true;
		while (flag) {
			int select_bno = Customer_Display();
			switch (select_bno) {
			case 1:
				cstm1();
				break;
			case 2:
				cstm2();
				break;
			case 0:
				System.out.println(">>�ڷΰ���<<");
				flag = false;
				break;

			default:
				break;
			}
		}
	}

	private static void cstm2() {
		// ȸ������
		flag = true;
		MemberVO memberVO = new MemberVO();
		System.out.println(">> ȸ������ â�Դϴ�.");
		System.out.print("ȸ����� �Է��ϼ���. >> ");
		String id = scan.nextLine();
		System.out.print("��й�ȣ�� �����ϼ���. >> ");
		int password = Integer.parseInt(scan.nextLine());
		if (!check(id, password)) {
			memberVO.setMEMBERNAME(id);
			memberVO.setPASSWORD(password);
			System.out.print("�������� �Է��ϼ���. >> ");
			memberVO.setANNUALSALES(Long.parseLong(scan.nextLine()));
			System.out.print("�������� �Է��ϼ���. >> ");
			memberVO.setEMPNUM(Integer.parseInt(scan.nextLine()));
			while (flag) {
				System.out.print("ȸ�缳������ �Է��ϼ���. ex: 1900-01-01 >> ");
				String checkDate = scan.nextLine();
				if (checkDate(checkDate)) {
					memberVO.setESTDATE(DateUtil.convertToDate(checkDate));

					int result = service.insert_member(memberVO);
					System.out.println(result > 0 ? ">> ȸ������ �Ϸ�." : "*ȸ������ ���� �ʾҽ��ϴ�.*");
					flag = false;
					break;
				} else {
					System.out.println(">> �߸��� �����Դϴ�. �ٽ� �Է����ּ���.");
				}
			}
		} else {
			System.out.println("*�̹� ȸ������ �Ǿ��ֽ��ϴ�.*");
		}

	}

	public static boolean checkDate(String checkDate) {
		try {
			SimpleDateFormat dateFormatParser = new SimpleDateFormat("yyyy-MM-dd"); // ������ ��¥ ���� ����
			dateFormatParser.setLenient(false); // false�ϰ�� ó���� �Է��� ���� �߸��� ������ �� ������ �߻�
			dateFormatParser.parse(checkDate); // ��� �� ���˿� ����Ǵ��� Ȯ��
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean check(String id, int password) {
		MemberVO member = service.select_MEMBERBYIDPASSWORD(id, password);
		if (member != null) {
			mem = member;
			return true;
		}
		return false;
	}

	private static void cstm1() {
		// �α���
		System.out.println(">> �α��� â�Դϴ�.");
		System.out.print("ȸ��� -->");
		String id = scan.nextLine();
		System.out.print("PASSWORD -->");
		int password = Integer.parseInt(scan.nextLine());
		if (check(id, password)) {
			System.out.println(">> �α��μ���.");
			login(mem);
		} else {
			System.out.println(">> �α��ν���.");
		}
	}

	private static void login(MemberVO member) {
		int memberid = member.getMEMBERID();
		// �α���â
		boolean flag = true;
		while (flag) {
			int select_bno = login_Display();
			switch (select_bno) {
			case 1:// 1.[�Ա�][���]
				lo1(member, memberid);
				break;
			case 2:// 2.�����û
				lo2(member, memberid);
				break;
			case 3:// 3.����󼼺���
				lo3(member, memberid);
				break;
			case 4:// 4.�������� [��ȸ][����]
				lo4(member, memberid);
				break;
			case 5:// 5.������°���
				lo5(member, memberid);
				break;
			case 6:// ������ Ȯ��
				lo6(memberid);
				break;
			case 0:
				System.out.println(">>�α׾ƿ�<<");
				flag = false;
				break;
			default:
				break;
			}
		}

	}

	private static void lo6(int memberid) {
		// ���ະ ������ Ȯ��
		interestview(memberid);
	}

	private static void lo5(MemberVO member, int memberid) {
		// 5.������°���
		flag = true;
		int score = score(member);
		System.out.println(View.bankselect_view(bankVOs));
		System.out.print("������ �����ϼ���. >>");
		int banknum = Integer.parseInt(scan.nextLine());
		int bankid = service.select_BANKbyNUM(banknum).getBankid();
		while (flag) {
			System.out.print("���� ������ �����ϼ���. [1] �Ϲݰ��� [2] ������� [0] �ڷΰ��� >>");
			int num = Integer.parseInt(scan.nextLine());
			BankMemberVO bankMemberVO = new BankMemberVO();
			if (num == 1) {
				while (flag) {
					System.out.print("���¹�ȣ�� �����ϼ���. >> ");
					String account = scan.nextLine();
					BankMemberVO bankMember = service.select_BANKMEMBER_byaccount(account);
					if (bankMember == null) {
						bankMemberVO.setACCOUNT(account);
						bankMemberVO.setBANKID(bankid);
						bankMemberVO.setMEMBERID(memberid);
						bankMemberVO.setBALANCE(0l);
						bankMemberVO.setSCORE(score);
						bankMemberVO.setPLUSACCOUNT("N");
						int result = service.insert_BANKMEMBER(bankMemberVO);
						System.out.println(result > 0 ? "�Ϲݰ��°� �����Ǿ����ϴ�." : "���� ����");
						flag = false;
						break;
					} else {
						System.out.println(">> ����� �� ���� ���¹�ȣ�Դϴ�. �ٽ� �Է����ּ���.");
					}
				}
			} else if (num == 2) {
				int count_n = service.select_BANKMEMBER_count_n(memberid, bankid); // �Ϲݰ��� ���� count
				if (count_n != 0) {
					int count_y = service.select_BANKMEMBER_count_y(memberid, bankid); // ������� ����
					if (count_y == 0) {
						while (flag) {
							System.out.print("���¹�ȣ�� �����ϼ���. >> ");
							String account = scan.nextLine();
							BankMemberVO bankMember = service.select_BANKMEMBER_byaccount(account);
							if (bankMember == null) {
								bankMemberVO.setACCOUNT(account);
								bankMemberVO.setBANKID(bankid);
								bankMemberVO.setMEMBERID(memberid);
								bankMemberVO.setBALANCE(0l);
								bankMemberVO.setSCORE(score);
								bankMemberVO.setPLUSACCOUNT("Y");
								int result = service.insert_BANKMEMBER(bankMemberVO);
								System.out.println(result > 0 ? ">> ������°� �����Ǿ����ϴ�." : "���� ����");
								flag = false;
								break;
							} else {
								System.out.println(">> ����� �� ���� ���¹�ȣ�Դϴ�. �ٽ� �Է����ּ���.");
							}
						}
					} else {
						System.out.println(">> �̹� ������°� �����մϴ�. ���̻� ������ �� �����ϴ�.");
					}
				} else {
					System.out.println(">> �Ϲݰ��°� 1�� �̻� �־�� ������� ������ �����մϴ�. �Ϲݰ��¸� ���� �������ּ���.");
					break;
				}
			} else if (num == 0) {
				System.out.println(">>�ڷ� ����<<");
				flag = false;
				break;
			} else {
				System.out.println("*�߸��� �����Դϴ�.*");
				break;
			}
		}
	}

	private static void lo4(MemberVO member, int memberid) {
		// 4.�������� [��ȸ][����]
		flag = true;
		System.out.println("<<" + member.getMEMBERNAME() + ">>���� ���������Դϴ�.");
		while (flag) {
			List<BankMember_MemberVO> memlist = service.select_BANKMEMBER_MEMBER_JOIN_bymemberid(memberid);
			System.out.println("������ : " + member.getANNUALSALES() + "\t ������ : " + member.getEMPNUM() + "\t ȸ�缳���� : "
					+ member.getESTDATE());
			if (memlist.size() > 0) {
				System.out.printf("    %-5s \t %-8s \t %-10s \t %8s \n", "<����>", "<��������>", "<����>", "<�ܾ�>");
				int i = 1;
				for (BankMember_MemberVO mem : memlist) {
					if (mem.getPLUSACCOUNT().equals("N")) {
						System.out.printf("[%d] %-5s \t %-8s \t %-10s \t %8s�� \n", i,
								View.bankselect_view(bankVOs, mem.getBANKID()), "�Ϲݰ���", mem.getACCOUNT(),
								decFormat.format(mem.getBALANCE()));
						i++;
					} else {
						System.out.printf("[%d] %-5s \t %-8s \t %-10s \t %8s \n", i,
								View.bankselect_view(bankVOs, mem.getBANKID()), "�������", mem.getACCOUNT(), "��"); // ,decFormat.format(mem.getBALANCE()));
																												// ���߿�
																												// �߰���
																												// ��� ->
																												// �������
																												// �ܾ�
						i++;
					}
				}
			} else {
				System.out.println(">> ���°� �����ϴ�.");
			}
			System.out.print("[1]������������ [0]�ڷΰ��� >>");
			int num = Integer.parseInt(scan.nextLine());
			if (num == 1) {
				System.out.print("[1] ��й�ȣ ���� [2] �����⺯�� [3] ������ ���� [0] �ڷΰ��� >>");
				int n = Integer.parseInt(scan.nextLine());
				if (n == 1) {
					System.out.print("������ ��й�ȣ�� �Է��ϼ���. >> ");
					member.setPASSWORD(Integer.parseInt(scan.nextLine()));
				} else if (n == 2) {
					System.out.print("������ �������� �Է��ϼ���. >> ");
					member.setANNUALSALES(Long.parseLong(scan.nextLine()));
				} else if (n == 3) {
					System.out.print("������ �������� �Է��ϼ���. >> ");
					member.setEMPNUM(Integer.parseInt(scan.nextLine()));
				} else if (n == 0) {
					System.out.println(">> �ڷΰ���");
					flag = false;
					break;
				} else {
					System.out.println("*�߸��� �����Դϴ�.*");
					break;
				}
				int result = service.update_MEMBER(member);
				System.out.println(result > 0 ? ">> ���� �Ϸ�" : "���� ����");
				if (result > 0) {
					bankMemberVO = service.select_BANKMEMBERBYMEMBERID(memberid);
					for (BankMemberVO bankmember : bankMemberVO) {
						bankmember.setSCORE(score(member));
						result = service.update_BANKMEMBER_SCORE(bankmember, memberid);
						break;
					}
				}
			} else if (num == 0) {
				flag = false;
				break;
			} else {
				System.out.println("*�߸��� �����Դϴ�.*");
				break;
			}
		}
	}

	public static int score(MemberVO member) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy");
		Date DATE = member.getESTDATE(), sysdate = new Date();
		long ANN = member.getANNUALSALES();
		String dateToStr = dateFormat.format(DATE), nowyear = dateFormat.format(sysdate);
		int EST = Integer.parseInt(dateToStr) - Integer.parseInt(nowyear);
		int EMP = member.getEMPNUM();
		int ann = 0, emp = 0, date = 0;
		int allcount;

		if (ANN >= 10000000000l) {
			ann = 330;
		} else if (ANN >= 10000000) {
			ann = 220;
		} else {
			ann = 110;
		}
		if (EMP >= 10000) {
			emp = 330;
		} else if (EMP >= 1000) {
			emp = 220;
		} else {
			emp = 110;
		}
		if (EST >= 10) {
			date = 330;
		} else if (EST >= 5) {
			date = 220;
		} else {
			date = 110;
		}
		allcount = ann + emp + date;
		return allcount;
	}

	private static void lo2(MemberVO member, int memberid) {
		// 3.����󼼺��� [����ݹ�ȯ] ����
		flag = true;
		List<Loan_LoanApply_Bankmember_LoanproductVO> ALL = new ArrayList<>();
		int bankmemberid = 0, i = 1;
		BankVO bankvo = null;
		System.out.println("�ѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤ�");
		System.out.println(">>����󼼺��� â�Դϴ�.");
		List<BankMemberVO> bankmemberlisty = service.select_BANKMEMGER_Y(memberid);// ����� 1���� ������� ���������ϴϱ� ������ ���� �� ����
		for (BankMemberVO bankmember : bankmemberlisty) {
			bankmemberid = bankmember.getBANKMEMBERID();
			ALL = service.select_ALL_JOIN(bankmemberid);// �Ѱ��� ������°� �������� ������ ���� �� ����
		}
		while (flag) {

			System.out.println(">> " + member.getMEMBERNAME() + "���� ���� ��Ȳ�Դϴ�.");
			System.out.println("=====================================================");
			if (ALL.size() == 0) {
				System.out.println(">> ���� ���� ������ �����ϴ�. ");
				flag = false;
				break;
			}
			System.out.printf("    %-5s \t %-18s \t %-8s \t %-6s \t %-10s \n", "<����>", "<�����ǰ>", "<�������>", "<���ڱݾ�>",
					"<��ȯ��>");
			for (Loan_LoanApply_Bankmember_LoanproductVO a : ALL) {
				bankvo = service.select_BANKbyid(a.getBANKID());
//				LoanVO loan =  service.select_LOANBYID(a.getBANKMEMBERID(),a.getLOANPRODUCTID());
//				int loanid = loan.getLOANID();
				LoanApplyVO loanapply = service.SELECT_LOANAPPLY_id(a.getLOANID());
				int scoreid = loanapply.getNOWSCORE() / 100;
//				GradeVO grade = service.select_GRADEGETID(scoreid);
//				int gradeid = grade.getGradeid();
				BankGradeVO bankinterest = service.select_BANKGRADEGETINTEREST(bankvo.getBankid(), 10 - scoreid);
				double interest = bankinterest.getINTEREST();
				System.out.printf("[%d] %-5s \t %-18s \t %-8d \t %-6d \t %-10s \n", i, bankvo.getBankname(),
						a.getPRODUCTNAME(), a.getAMOUNT(), Math.round(a.getAMOUNT() * interest / 100), a.getENDDATE());
				i++;
			}
			System.out.println("=====================================================");
			while (flag) {
				System.out.print("[1] ����� ��ȯ [2] ���� ���� [3] ������ Ȯ�� [0] �ڷΰ��� >> ��ȣ�� �����ϼ���. >> ");
				int num = Integer.parseInt(scan.nextLine());
				if (num == 1) {
					// ����� ��ȯ
					boolean check = true;
					while (check) {
						int j = 1;
						System.out.print("����� ��ȯ�� ���ϴ� ��ǰ�� �����ϼ���. >>");
						int sel = Integer.parseInt(scan.nextLine());
						Loan_LoanApply_Bankmember_LoanproductVO vo = service.select_ALL_JOIN_num(bankmemberid, sel);
						Long amount = vo.getAMOUNT();
						if (sel > 0 && sel < i) {
							System.out.println("=====================================================");
							List<BankMemberVO> bankMemberVOs = service.select_BANKMEMGER_N(memberid);

							if (bankMemberVOs.size() > 0) {
								System.out.printf("    <����> \t <���¹�ȣ> \t <�����ܾ�>\n");
								for (BankMemberVO bankmember : bankMemberVOs) {
									System.out.printf("[%d] %-5s \t %-8s \t %-8d \n", j,
											service.select_BANKbyid(bankmember.getBANKID()).getBankname(),
											bankmember.getACCOUNT(), bankmember.getBALANCE());
									j++;
								}
								System.out.print("������� ��ü�� ���¸� �����ϼ���. >>");
								int outaccount = Integer.parseInt(scan.nextLine());
								if (outaccount > 0 && outaccount < j) {
									BankMemberVO bankmember = service.select_BANKMEMGER_ACCOUNT(memberid, outaccount);
									long money = Math
											.round(amount + amount * interest_member(memberid, vo.getBANKID()) / 100);
									if (bankmember.getBALANCE() >= money) {
										System.out.println(
												"[ " + bankmember.getACCOUNT() + " ] ���� " + money + "���� ��ü�մϴ�.");
										bankmember.setBALANCE(bankmember.getBALANCE() - money);
										int result = service.update_BANKMEMBER_BALANCE(bankmember);
										System.out.println(result > 0 ? ">> �����ȯ�� �Ϸ��߽��ϴ�." : "��ȯ����");
										if (result > 0) {
											service.delete_loan(vo.getLOANID());
										} else {
											System.out.println("�������");
											flag = false;
											break;
										}
										flag = false;
										break;
									} else {
										System.out.println(">> �ܾ��� �����մϴ�.");
									}
								} else {
									System.out.println(">> �߸��� �����Դϴ�.");
								}
							} else {
								System.out.println(">> ��ü ������ ���°� �����ϴ�. �Ϲ� ���¸� �������ּ���.");
							}
							check = false;
							break;
						} else {
							System.out.println("�ٽ� �������ּ���. >>");
						}
					}
				} else if (num == 2) {
					// ���� ����
					System.out.print("����Ⱓ ������ ���ϴ� ��ǰ�� �����ϼ���. >>");
					int sel = Integer.parseInt(scan.nextLine());
					Loan_LoanApply_Bankmember_LoanproductVO vo = service.select_ALL_JOIN_num(bankmemberid, sel);
					vo.getLOANAPPLYNUM();
					LoanApplyVO applyVO = service.SELECT_LOANAPPLY_id(vo.getLOANAPPLYNUM());
					if (applyVO.getPROLONG() < 5) {
						Date DATE = applyVO.getENDDATE();
						Calendar cal = Calendar.getInstance();
						cal.setTime(DATE);
						cal.add(Calendar.YEAR, 1);
						applyVO.setPROLONG(vo.getPROLONG() + 1);
						java.util.Date utilDate = cal.getTime();
						java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
						applyVO.setENDDATE(sqlDate);
						int result = service.update_LOANAPPLY(applyVO);
						System.out.println(result > 0 ? ">> ���� �Ⱓ ������ �Ϸ��߽��ϴ�." : "���� ����");
					} else {
						System.out.println(">> ���� ���� Ƚ���� �ʰ��Ͽ����ϴ�.");
						System.out.println(">> �� �̻� ���� �Ⱓ�� ������� �ʽ��ϴ�.");
					}
				} else if (num == 3) {
					// ������ Ȯ��
					interestview(memberid);

				} else if (num == 0) {
					System.out.println(">> �ڷΰ���.");
					flag = false;
					break;
				} else {
					System.out.println(">> �߸��� �����Դϴ�.");
					break;
				}
			}
		}
	}

	private static void interestview(int memberid) {
		StringBuffer sb = new StringBuffer();
		System.out.println("<����> \t <������>");
		for (BankVO bank : bankVOs) {
			sb.append(bank.getBankname() + "\t " + interest_member(memberid, bank.getBankid()) + "\n");
		}
		System.out.println(sb.toString());
	}

	private static void lo3(MemberVO member, int memberid) {
		// 2.�����û
		flag = true;
		System.out.println("�ѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤ�");
		System.out.println(">>�����û â�Դϴ�.");

		while (flag) {
			System.out.print(View.bankselect_view(service.select_BANK()));
			System.out.print(" << ������ �����ϼ���. [0] �ڷΰ��� >>");
			int banknum = Integer.parseInt(scan.nextLine());
			if (View.bankselect_view(bankVOs).contains(String.valueOf(banknum))) {
				int bankid = service.select_BANKbyNUM(banknum).getBankid();
				BankMemberVO bankmembervo = service.select_BANKMEMGER_Y(bankid, memberid);
				if (bankmembervo != null) {
					if (bankmembervo.getPLUSACCOUNT().equals("Y")) {
						List<LoanProductVO> loanplist = service.select_LOANPRODUCTBYBANKID(bankid);
						System.out.println();
						System.out.printf("    <����>\t\t <���Ⱑ�ɱݾ�>\t\t <����>");
						System.out.println();
						int i = 1;
						for (LoanProductVO loan : loanplist) {
							System.out.printf("[%d] %-10s \t %-10s \t %-20s \n", i, loan.getPRODUCTNAME(),
									decFormat.format(loan.getMAXAMOUNT()), loan.getCONDITION());
							i++;
						}
						int bankmemberid = bankmembervo.getBANKMEMBERID();
						System.out.println();
						System.out.print(">> ������� ��ǰ�� ��ȣ�� �Է��ϼ���. >> ");
						int num = Integer.parseInt(scan.nextLine());
						if (num > 0 && num < i) {
							LoanProductVO lp = service.select_LOANPRODUCTBYBANKID(bankid, num);
							int loanproductid = lp.getLOANPRODUCTID();
							LoanVO lo = service.select_LOANBYID(bankmemberid, loanproductid);
							if (lo != null) {
								System.out.println(">> ���� ��ǰ�� �ߺ���û�� �ȵ˴ϴ�.");
								break;
							}

							System.out.println(">> '" + lp.getPRODUCTNAME() + "'�� �����ϼ̽��ϴ�.");
							LoanVO loan = new LoanVO();
							loan.setBANKMEMBERID(bankmemberid);
							loan.setLOANPRODUCTID(loanproductid);
							System.out.print(">> ���� ��û�� �ݾ��� �Է��ϼ���. >>");
							Long amount = Long.parseLong(scan.nextLine());
							int allamount = 0;
							List<Loan_Bankmember_Loanproduct_JoinVO> bankmember_Loanproduct_JoinVOs = service
									.SELECT_LOAN_LOANPRODUCT_BANKMEMBER(bankid, memberid);
							for (Loan_Bankmember_Loanproduct_JoinVO a : bankmember_Loanproduct_JoinVOs) {
								allamount += a.getAMOUNT();
							}
							if (lp.getMAXAMOUNT() >= amount && (member.getANNUALSALES() * 0.6) >= allamount + amount) {
								loan.setAMOUNT(amount);
								int result = service.insert_loan(loan);
								System.out.println(result > 0 ? ">> ���� ��û�� �Ǿ����ϴ�." : "���� ��û ����");
								if (result > 0) {
									lo = service.select_LOANBYID(bankmemberid, loanproductid);
									int loid = lo.getLOANID();
									LoanApplyVO applyVO = new LoanApplyVO();
									applyVO.setLOANID(loid);
									applyVO.setNOWSCORE(bankmembervo.getSCORE());
									result = service.insert_loanapply(applyVO);
									System.out.println(result > 0 ? "****** ������ �Ϸ� �Ǿ����ϴ�." : "���� ��û ����");
// -���߿� �߰���ų ��� 					bankmembervo.setBALANCE(bankmembervo.getBALANCE()+amount);
// ���� �Ϸ�Ǹ� ������¿� 				result = service.update_BANKMEMBER_BALANCE(bankmembervo);
// ����� �Աݵ�						System.out.println(result > 0 ? ">> ����� �Ա� �Ϸ�" : "�Ա� ����");
									flag = false;
									break;
								}
							} else {
								if (lp.getMAXAMOUNT() >= amount
										&& (member.getANNUALSALES() * 0.6) < allamount + amount) {
									System.out
											.println(">> " + decFormat.format(member.getANNUALSALES() * 0.6 - allamount)
													+ "�� ���ϱ��� ������ �����մϴ�.");
								}
								System.out.println(">> ���� ���� �ѵ��� �Ѿ����ϴ�.");
								break;
							}
						} else {
							System.out.println(">> �߸��� �����Դϴ�.");
							break;
						}
					} else {
						System.out.println(">> �ش����࿡ ���°� �����ϴ�. �ű԰������ּ���.");
						System.out.println("�ѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤ�");
						flag = false;
						break;
					}
				} else {
					System.out.println(">> �ش����࿡ ������°� �����ϴ�. �ű԰������ּ���.");
					System.out.println("�ѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤ�");
					flag = false;
					break;
				}
			} else if (banknum == 0) {
				System.out.println(">> â�� �����ϴ�.");
				System.out.println("�ѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤ�");
				flag = false;
				break;
			}
		}

	}

	private static void lo1(MemberVO member, int memberid) {
		// 1.[�Ա�][���]
		flag = true;
		System.out.println(">> ����� â�Դϴ�.");
		while (flag) {
			System.out.print(View.bankselect_view(bankVOs));
			System.out.print(" >> ������ �����ϼ���. [0] �ڷΰ��� >>");
			int banknum = Integer.parseInt(scan.nextLine());
			if (banknum == 0) {
				System.out.println(">> �ڷΰ���");
				flag = false;
				break;
			}
			if (View.bankselect_view(bankVOs).contains(String.valueOf(banknum))) {
				int bankid = service.select_BANKbyNUM(banknum).getBankid();
				bankmemberlist = service.select_BANKMEMGER_N(bankid, memberid);

				int i = 1;
				System.out.println(">>" + member.getMEMBERNAME() + "���� ���������Դϴ�.<<");
				for (BankMemberVO bankmember : bankmemberlist) {
					if (bankmember.getBANKID() == bankid && bankmember.getMEMBERID() == memberid) {
						System.out.println("[" + i + "] " + bankmember.getACCOUNT() + "----"
								+ decFormat.format(bankmember.getBALANCE()) + "��");
						i++;
					}
				}
				if (bankmemberlist.size() == 0) {
					System.out.println(">>���°� �������� �ʽ��ϴ�. �ش� ���࿡ ���¸� �������ּ���.<<");
					break;
				}
				System.out.print("������� ���¸� �����ϼ���. >> ");
				int accountnum = Integer.parseInt(scan.nextLine());
				BankMemberVO bankmember = service.select_BANKbyNUMID_N(bankid, memberid, accountnum);
				while (flag) {
					Long balance = bankmember.getBALANCE();
					System.out.print("[1] �Ա� [2] ��� [0] �ڷΰ��� >> ");
					long select = Integer.parseInt(scan.nextLine());
					if (select == 1) {
						System.out.println(">> �Ա� â�Դϴ�.");
						System.out.print("�Ա��� �ݾ��� �Է��ϼ���. >> ");
						long inmoney = Long.parseLong(scan.nextLine());
						bankmember.setBALANCE(balance + inmoney);
						int result = service.update_BANKMEMBER_BALANCE(bankmember);
						System.out.println(result > 0 ? ">> �Ա� �Ϸ�" : "�Ա� ����");
					} else if (select == 2) {
						System.out.println(">> ��� â�Դϴ�.");
						System.out.print("����� �ݾ��� �Է��ϼ���. >> ");
						long outmoney = Long.parseLong(scan.nextLine());
						long output = balance - outmoney;
						int result = 0;
						if (output >= 0) {
							bankmember.setBALANCE(balance - outmoney);
							result = service.update_BANKMEMBER_BALANCE(bankmember);
						}
						System.out.println(result > 0 ? ">> ��� �Ϸ�." : "��� ����");
					} else {
						flag = false;
						break;
					}
				}
			} else {
				System.out.println(">> �߸��� �����Դϴ�.");
				break;
			}
		}
	}

	private static int login_Display() {
		System.out.println("1.[�Ա�][���]");
		System.out.println("2.[����� ��ȯ][���� ����]"); // [����ݹ�ȯ] ����
		System.out.println("3.�����û");
		System.out.println("4.�������� [��ȸ][����]");
		System.out.println("5.������°���");
		System.out.println("6.���ະ ������ Ȯ��");
		System.out.println("0.�α׾ƿ�");
		System.out.print("�۾��� �����ϼ��� >>");
		return Integer.parseInt(scan.nextLine());
	}

	private static int Customer_Display() {
		System.out.println("1.�α���");
		System.out.println("2.ȸ������");
		System.out.println("0.�ڷΰ���");
		System.out.print("�۾��� �����ϼ��� >>");
		return Integer.parseInt(scan.nextLine());
	}

	private static double interest_member(int memberid, int bankid) {
		bankMemberVO = service.select_BANKMEMBERBYMEMBERID(memberid);
		if (bankMemberVO.size() > 0) {
			int scoreid = bankMemberVO.get(0).getSCORE() / 100 * 100;
			GradeVO grade = service.select_GRADEGETID(scoreid);
			int gradeid = grade.getGradeid();
			BankGradeVO bankinterest = service.select_BANKGRADEGETINTEREST(bankid, gradeid);
			return bankinterest.getINTEREST();
		}
		return 0;
	}
}
