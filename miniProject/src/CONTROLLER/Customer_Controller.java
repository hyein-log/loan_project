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
				System.out.println(">>뒤로가기<<");
				flag = false;
				break;

			default:
				break;
			}
		}
	}

	private static void cstm2() {
		// 회원가입
		flag = true;
		MemberVO memberVO = new MemberVO();
		System.out.println(">> 회원가입 창입니다.");
		System.out.print("회사명을 입력하세요. >> ");
		String id = scan.nextLine();
		System.out.print("비밀번호를 설정하세요. >> ");
		int password = Integer.parseInt(scan.nextLine());
		if (!check(id, password)) {
			memberVO.setMEMBERNAME(id);
			memberVO.setPASSWORD(password);
			System.out.print("연매출을 입력하세요. >> ");
			memberVO.setANNUALSALES(Long.parseLong(scan.nextLine()));
			System.out.print("직원수를 입력하세요. >> ");
			memberVO.setEMPNUM(Integer.parseInt(scan.nextLine()));
			while (flag) {
				System.out.print("회사설립일을 입력하세요. ex: 1900-01-01 >> ");
				String checkDate = scan.nextLine();
				if (checkDate(checkDate)) {
					memberVO.setESTDATE(DateUtil.convertToDate(checkDate));

					int result = service.insert_member(memberVO);
					System.out.println(result > 0 ? ">> 회원가입 완료." : "*회원가입 되지 않았습니다.*");
					flag = false;
					break;
				} else {
					System.out.println(">> 잘못된 형식입니다. 다시 입력해주세요.");
				}
			}
		} else {
			System.out.println("*이미 회원가입 되어있습니다.*");
		}

	}

	public static boolean checkDate(String checkDate) {
		try {
			SimpleDateFormat dateFormatParser = new SimpleDateFormat("yyyy-MM-dd"); // 검증할 날짜 포맷 설정
			dateFormatParser.setLenient(false); // false일경우 처리시 입력한 값이 잘못된 형식일 시 오류가 발생
			dateFormatParser.parse(checkDate); // 대상 값 포맷에 적용되는지 확인
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
		// 로그인
		System.out.println(">> 로그인 창입니다.");
		System.out.print("회사명 -->");
		String id = scan.nextLine();
		System.out.print("PASSWORD -->");
		int password = Integer.parseInt(scan.nextLine());
		if (check(id, password)) {
			System.out.println(">> 로그인성공.");
			login(mem);
		} else {
			System.out.println(">> 로그인실패.");
		}
	}

	private static void login(MemberVO member) {
		int memberid = member.getMEMBERID();
		// 로그인창
		boolean flag = true;
		while (flag) {
			int select_bno = login_Display();
			switch (select_bno) {
			case 1:// 1.[입금][출금]
				lo1(member, memberid);
				break;
			case 2:// 2.대출신청
				lo2(member, memberid);
				break;
			case 3:// 3.대출상세보기
				lo3(member, memberid);
				break;
			case 4:// 4.개인정보 [조회][수정]
				lo4(member, memberid);
				break;
			case 5:// 5.은행계좌개설
				lo5(member, memberid);
				break;
			case 6:// 이자율 확인
				lo6(memberid);
				break;
			case 0:
				System.out.println(">>로그아웃<<");
				flag = false;
				break;
			default:
				break;
			}
		}

	}

	private static void lo6(int memberid) {
		// 은행별 이자율 확인
		interestview(memberid);
	}

	private static void lo5(MemberVO member, int memberid) {
		// 5.은행계좌개설
		flag = true;
		int score = score(member);
		System.out.println(View.bankselect_view(bankVOs));
		System.out.print("은행을 선택하세요. >>");
		int banknum = Integer.parseInt(scan.nextLine());
		int bankid = service.select_BANKbyNUM(banknum).getBankid();
		while (flag) {
			System.out.print("계좌 유형을 선택하세요. [1] 일반계좌 [2] 대출계좌 [0] 뒤로가기 >>");
			int num = Integer.parseInt(scan.nextLine());
			BankMemberVO bankMemberVO = new BankMemberVO();
			if (num == 1) {
				while (flag) {
					System.out.print("계좌번호를 생성하세요. >> ");
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
						System.out.println(result > 0 ? "일반계좌가 생성되었습니다." : "생성 실패");
						flag = false;
						break;
					} else {
						System.out.println(">> 사용할 수 없는 계좌번호입니다. 다시 입력해주세요.");
					}
				}
			} else if (num == 2) {
				int count_n = service.select_BANKMEMBER_count_n(memberid, bankid); // 일반계좌 개수 count
				if (count_n != 0) {
					int count_y = service.select_BANKMEMBER_count_y(memberid, bankid); // 대출계좌 개수
					if (count_y == 0) {
						while (flag) {
							System.out.print("계좌번호를 생성하세요. >> ");
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
								System.out.println(result > 0 ? ">> 대출계좌가 생성되었습니다." : "생성 실패");
								flag = false;
								break;
							} else {
								System.out.println(">> 사용할 수 없는 계좌번호입니다. 다시 입력해주세요.");
							}
						}
					} else {
						System.out.println(">> 이미 대출계좌가 존재합니다. 더이상 개설할 수 없습니다.");
					}
				} else {
					System.out.println(">> 일반계좌가 1개 이상 있어야 대출계좌 개설이 가능합니다. 일반계좌를 먼저 개설해주세요.");
					break;
				}
			} else if (num == 0) {
				System.out.println(">>뒤로 가기<<");
				flag = false;
				break;
			} else {
				System.out.println("*잘못된 선택입니다.*");
				break;
			}
		}
	}

	private static void lo4(MemberVO member, int memberid) {
		// 4.개인정보 [조회][수정]
		flag = true;
		System.out.println("<<" + member.getMEMBERNAME() + ">>님의 개인정보입니다.");
		while (flag) {
			List<BankMember_MemberVO> memlist = service.select_BANKMEMBER_MEMBER_JOIN_bymemberid(memberid);
			System.out.println("연매출 : " + member.getANNUALSALES() + "\t 직원수 : " + member.getEMPNUM() + "\t 회사설립일 : "
					+ member.getESTDATE());
			if (memlist.size() > 0) {
				System.out.printf("    %-5s \t %-8s \t %-10s \t %8s \n", "<은행>", "<계좌정보>", "<계좌>", "<잔액>");
				int i = 1;
				for (BankMember_MemberVO mem : memlist) {
					if (mem.getPLUSACCOUNT().equals("N")) {
						System.out.printf("[%d] %-5s \t %-8s \t %-10s \t %8s원 \n", i,
								View.bankselect_view(bankVOs, mem.getBANKID()), "일반계좌", mem.getACCOUNT(),
								decFormat.format(mem.getBALANCE()));
						i++;
					} else {
						System.out.printf("[%d] %-5s \t %-8s \t %-10s \t %8s \n", i,
								View.bankselect_view(bankVOs, mem.getBANKID()), "대출계좌", mem.getACCOUNT(), "ㅡ"); // ,decFormat.format(mem.getBALANCE()));
																												// 나중에
																												// 추가될
																												// 기능 ->
																												// 대출계좌
																												// 잔액
						i++;
					}
				}
			} else {
				System.out.println(">> 계좌가 없습니다.");
			}
			System.out.print("[1]개인정보수정 [0]뒤로가기 >>");
			int num = Integer.parseInt(scan.nextLine());
			if (num == 1) {
				System.out.print("[1] 비밀번호 변경 [2] 연매출변경 [3] 직원수 변경 [0] 뒤로가기 >>");
				int n = Integer.parseInt(scan.nextLine());
				if (n == 1) {
					System.out.print("변경할 비밀번호를 입력하세요. >> ");
					member.setPASSWORD(Integer.parseInt(scan.nextLine()));
				} else if (n == 2) {
					System.out.print("변경할 연매출을 입력하세요. >> ");
					member.setANNUALSALES(Long.parseLong(scan.nextLine()));
				} else if (n == 3) {
					System.out.print("변경할 직원수를 입력하세요. >> ");
					member.setEMPNUM(Integer.parseInt(scan.nextLine()));
				} else if (n == 0) {
					System.out.println(">> 뒤로가기");
					flag = false;
					break;
				} else {
					System.out.println("*잘못된 선택입니다.*");
					break;
				}
				int result = service.update_MEMBER(member);
				System.out.println(result > 0 ? ">> 수정 완료" : "수정 실패");
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
				System.out.println("*잘못된 선택입니다.*");
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
		// 3.대출상세보기 [대출금반환] 연장
		flag = true;
		List<Loan_LoanApply_Bankmember_LoanproductVO> ALL = new ArrayList<>();
		int bankmemberid = 0, i = 1;
		BankVO bankvo = null;
		System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
		System.out.println(">>대출상세보기 창입니다.");
		List<BankMemberVO> bankmemberlisty = service.select_BANKMEMGER_Y(memberid);// 은행당 1개의 대출계좌 생성가능하니까 여러개 있을 수 있음
		for (BankMemberVO bankmember : bankmemberlisty) {
			bankmemberid = bankmember.getBANKMEMBERID();
			ALL = service.select_ALL_JOIN(bankmemberid);// 한개의 대출계좌가 여러개의 대출을 받을 수 있음
		}
		while (flag) {

			System.out.println(">> " + member.getMEMBERNAME() + "님의 대출 현황입니다.");
			System.out.println("=====================================================");
			if (ALL.size() == 0) {
				System.out.println(">> 대출 받은 사항이 없습니다. ");
				flag = false;
				break;
			}
			System.out.printf("    %-5s \t %-18s \t %-8s \t %-6s \t %-10s \n", "<은행>", "<대출상품>", "<대출원금>", "<이자금액>",
					"<반환일>");
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
				System.out.print("[1] 대출금 반환 [2] 대출 연장 [3] 이자율 확인 [0] 뒤로가기 >> 번호를 선택하세요. >> ");
				int num = Integer.parseInt(scan.nextLine());
				if (num == 1) {
					// 대출금 반환
					boolean check = true;
					while (check) {
						int j = 1;
						System.out.print("대출금 반환을 원하는 상품을 선택하세요. >>");
						int sel = Integer.parseInt(scan.nextLine());
						Loan_LoanApply_Bankmember_LoanproductVO vo = service.select_ALL_JOIN_num(bankmemberid, sel);
						Long amount = vo.getAMOUNT();
						if (sel > 0 && sel < i) {
							System.out.println("=====================================================");
							List<BankMemberVO> bankMemberVOs = service.select_BANKMEMGER_N(memberid);

							if (bankMemberVOs.size() > 0) {
								System.out.printf("    <은행> \t <계좌번호> \t <통장잔액>\n");
								for (BankMemberVO bankmember : bankMemberVOs) {
									System.out.printf("[%d] %-5s \t %-8s \t %-8d \n", j,
											service.select_BANKbyid(bankmember.getBANKID()).getBankname(),
											bankmember.getACCOUNT(), bankmember.getBALANCE());
									j++;
								}
								System.out.print("대출금을 이체할 계좌를 선택하세요. >>");
								int outaccount = Integer.parseInt(scan.nextLine());
								if (outaccount > 0 && outaccount < j) {
									BankMemberVO bankmember = service.select_BANKMEMGER_ACCOUNT(memberid, outaccount);
									long money = Math
											.round(amount + amount * interest_member(memberid, vo.getBANKID()) / 100);
									if (bankmember.getBALANCE() >= money) {
										System.out.println(
												"[ " + bankmember.getACCOUNT() + " ] 에서 " + money + "원을 이체합니다.");
										bankmember.setBALANCE(bankmember.getBALANCE() - money);
										int result = service.update_BANKMEMBER_BALANCE(bankmember);
										System.out.println(result > 0 ? ">> 대출반환을 완료했습니다." : "반환실패");
										if (result > 0) {
											service.delete_loan(vo.getLOANID());
										} else {
											System.out.println("대출실패");
											flag = false;
											break;
										}
										flag = false;
										break;
									} else {
										System.out.println(">> 잔액이 부족합니다.");
									}
								} else {
									System.out.println(">> 잘못된 선택입니다.");
								}
							} else {
								System.out.println(">> 이체 가능한 계좌가 없습니다. 일반 계좌를 개설해주세요.");
							}
							check = false;
							break;
						} else {
							System.out.println("다시 선택해주세요. >>");
						}
					}
				} else if (num == 2) {
					// 대출 연장
					System.out.print("대출기간 연장을 원하는 상품을 선택하세요. >>");
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
						System.out.println(result > 0 ? ">> 대출 기간 연장을 완료했습니다." : "연장 실패");
					} else {
						System.out.println(">> 연장 가능 횟수를 초과하였습니다.");
						System.out.println(">> 더 이상 대출 기간이 연장되지 않습니다.");
					}
				} else if (num == 3) {
					// 이자율 확인
					interestview(memberid);

				} else if (num == 0) {
					System.out.println(">> 뒤로가기.");
					flag = false;
					break;
				} else {
					System.out.println(">> 잘못된 선택입니다.");
					break;
				}
			}
		}
	}

	private static void interestview(int memberid) {
		StringBuffer sb = new StringBuffer();
		System.out.println("<은행> \t <이자율>");
		for (BankVO bank : bankVOs) {
			sb.append(bank.getBankname() + "\t " + interest_member(memberid, bank.getBankid()) + "\n");
		}
		System.out.println(sb.toString());
	}

	private static void lo3(MemberVO member, int memberid) {
		// 2.대출신청
		flag = true;
		System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
		System.out.println(">>대출신청 창입니다.");

		while (flag) {
			System.out.print(View.bankselect_view(service.select_BANK()));
			System.out.print(" << 은행을 선택하세요. [0] 뒤로가기 >>");
			int banknum = Integer.parseInt(scan.nextLine());
			if (View.bankselect_view(bankVOs).contains(String.valueOf(banknum))) {
				int bankid = service.select_BANKbyNUM(banknum).getBankid();
				BankMemberVO bankmembervo = service.select_BANKMEMGER_Y(bankid, memberid);
				if (bankmembervo != null) {
					if (bankmembervo.getPLUSACCOUNT().equals("Y")) {
						List<LoanProductVO> loanplist = service.select_LOANPRODUCTBYBANKID(bankid);
						System.out.println();
						System.out.printf("    <조건>\t\t <대출가능금액>\t\t <조건>");
						System.out.println();
						int i = 1;
						for (LoanProductVO loan : loanplist) {
							System.out.printf("[%d] %-10s \t %-10s \t %-20s \n", i, loan.getPRODUCTNAME(),
									decFormat.format(loan.getMAXAMOUNT()), loan.getCONDITION());
							i++;
						}
						int bankmemberid = bankmembervo.getBANKMEMBERID();
						System.out.println();
						System.out.print(">> 대출받을 상품의 번호를 입력하세요. >> ");
						int num = Integer.parseInt(scan.nextLine());
						if (num > 0 && num < i) {
							LoanProductVO lp = service.select_LOANPRODUCTBYBANKID(bankid, num);
							int loanproductid = lp.getLOANPRODUCTID();
							LoanVO lo = service.select_LOANBYID(bankmemberid, loanproductid);
							if (lo != null) {
								System.out.println(">> 같은 상품의 중복신청은 안됩니다.");
								break;
							}

							System.out.println(">> '" + lp.getPRODUCTNAME() + "'을 선택하셨습니다.");
							LoanVO loan = new LoanVO();
							loan.setBANKMEMBERID(bankmemberid);
							loan.setLOANPRODUCTID(loanproductid);
							System.out.print(">> 대출 신청할 금액을 입력하세요. >>");
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
								System.out.println(result > 0 ? ">> 대출 신청이 되었습니다." : "대출 신청 실패");
								if (result > 0) {
									lo = service.select_LOANBYID(bankmemberid, loanproductid);
									int loid = lo.getLOANID();
									LoanApplyVO applyVO = new LoanApplyVO();
									applyVO.setLOANID(loid);
									applyVO.setNOWSCORE(bankmembervo.getSCORE());
									result = service.insert_loanapply(applyVO);
									System.out.println(result > 0 ? "****** 대출이 완료 되었습니다." : "대출 신청 실패");
// -나중에 추가시킬 기능 					bankmembervo.setBALANCE(bankmembervo.getBALANCE()+amount);
// 대출 완료되면 대출계좌에 				result = service.update_BANKMEMBER_BALANCE(bankmembervo);
// 대출금 입금됨						System.out.println(result > 0 ? ">> 대출금 입금 완료" : "입금 실패");
									flag = false;
									break;
								}
							} else {
								if (lp.getMAXAMOUNT() >= amount
										&& (member.getANNUALSALES() * 0.6) < allamount + amount) {
									System.out
											.println(">> " + decFormat.format(member.getANNUALSALES() * 0.6 - allamount)
													+ "원 이하까지 대출이 가능합니다.");
								}
								System.out.println(">> 대출 가능 한도를 넘었습니다.");
								break;
							}
						} else {
							System.out.println(">> 잘못된 선택입니다.");
							break;
						}
					} else {
						System.out.println(">> 해당은행에 계좌가 없습니다. 신규가입해주세요.");
						System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
						flag = false;
						break;
					}
				} else {
					System.out.println(">> 해당은행에 대출계좌가 없습니다. 신규개설해주세요.");
					System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
					flag = false;
					break;
				}
			} else if (banknum == 0) {
				System.out.println(">> 창을 나갑니다.");
				System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
				flag = false;
				break;
			}
		}

	}

	private static void lo1(MemberVO member, int memberid) {
		// 1.[입금][출금]
		flag = true;
		System.out.println(">> 입출금 창입니다.");
		while (flag) {
			System.out.print(View.bankselect_view(bankVOs));
			System.out.print(" >> 은행을 선택하세요. [0] 뒤로가기 >>");
			int banknum = Integer.parseInt(scan.nextLine());
			if (banknum == 0) {
				System.out.println(">> 뒤로가기");
				flag = false;
				break;
			}
			if (View.bankselect_view(bankVOs).contains(String.valueOf(banknum))) {
				int bankid = service.select_BANKbyNUM(banknum).getBankid();
				bankmemberlist = service.select_BANKMEMGER_N(bankid, memberid);

				int i = 1;
				System.out.println(">>" + member.getMEMBERNAME() + "님의 계좌정보입니다.<<");
				for (BankMemberVO bankmember : bankmemberlist) {
					if (bankmember.getBANKID() == bankid && bankmember.getMEMBERID() == memberid) {
						System.out.println("[" + i + "] " + bankmember.getACCOUNT() + "----"
								+ decFormat.format(bankmember.getBALANCE()) + "원");
						i++;
					}
				}
				if (bankmemberlist.size() == 0) {
					System.out.println(">>계좌가 존재하지 않습니다. 해당 은행에 계좌를 개설해주세요.<<");
					break;
				}
				System.out.print("입출금할 계좌를 선택하세요. >> ");
				int accountnum = Integer.parseInt(scan.nextLine());
				BankMemberVO bankmember = service.select_BANKbyNUMID_N(bankid, memberid, accountnum);
				while (flag) {
					Long balance = bankmember.getBALANCE();
					System.out.print("[1] 입금 [2] 출금 [0] 뒤로가기 >> ");
					long select = Integer.parseInt(scan.nextLine());
					if (select == 1) {
						System.out.println(">> 입금 창입니다.");
						System.out.print("입금할 금액을 입력하세요. >> ");
						long inmoney = Long.parseLong(scan.nextLine());
						bankmember.setBALANCE(balance + inmoney);
						int result = service.update_BANKMEMBER_BALANCE(bankmember);
						System.out.println(result > 0 ? ">> 입금 완료" : "입금 실패");
					} else if (select == 2) {
						System.out.println(">> 출금 창입니다.");
						System.out.print("출금할 금액을 입력하세요. >> ");
						long outmoney = Long.parseLong(scan.nextLine());
						long output = balance - outmoney;
						int result = 0;
						if (output >= 0) {
							bankmember.setBALANCE(balance - outmoney);
							result = service.update_BANKMEMBER_BALANCE(bankmember);
						}
						System.out.println(result > 0 ? ">> 출금 완료." : "출금 실패");
					} else {
						flag = false;
						break;
					}
				}
			} else {
				System.out.println(">> 잘못된 선택입니다.");
				break;
			}
		}
	}

	private static int login_Display() {
		System.out.println("1.[입금][출금]");
		System.out.println("2.[대출금 반환][대출 연장]"); // [대출금반환] 연장
		System.out.println("3.대출신청");
		System.out.println("4.개인정보 [조회][수정]");
		System.out.println("5.은행계좌개설");
		System.out.println("6.은행별 이자율 확인");
		System.out.println("0.로그아웃");
		System.out.print("작업을 선택하세요 >>");
		return Integer.parseInt(scan.nextLine());
	}

	private static int Customer_Display() {
		System.out.println("1.로그인");
		System.out.println("2.회원가입");
		System.out.println("0.뒤로가기");
		System.out.print("작업을 선택하세요 >>");
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
