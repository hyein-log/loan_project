package SERVICE;

import java.util.List;

import DAO.Delete_DAO;
import DAO.Insert_DAO;
import DAO.Select_DAO;
import DAO.Update_DAO;
import VO.BankGradeVO;
import VO.BankMemberVO;
import VO.BankMember_MemberVO;
import VO.BankVO;
import VO.GradeVO;
import VO.LoanApplyVO;
import VO.LoanProductVO;
import VO.LoanVO;
import VO.Loan_Bankmember_Loanproduct_JoinVO;
import VO.Loan_LoanApplay_Bankmember_Member_Loanproduct_JoinVO;
import VO.Loan_LoanApply_Bankmember_LoanproductVO;
import VO.MemberVO;

public class Service {
	Select_DAO selectDAO = new Select_DAO();
	Insert_DAO insertDAO = new Insert_DAO();
	Delete_DAO deleteDAO = new Delete_DAO();
	Update_DAO updateDAO = new Update_DAO();
	
	
	public List<MemberVO> select_Member() {
		return selectDAO.select_Member();
	}
	public List<BankVO> select_BANK() {
		return selectDAO.select_BANK();
	}
	public List<BankMemberVO> select_BANKMEMBER() {
		return selectDAO.select_BANKMEMBER();
	}
	public List<BankMember_MemberVO> select_BANKMEMBER_MEMBER_JOIN(int bankid) {
		return selectDAO.select_BANKMEMBER_MEMBER_JOIN(bankid);
	}
	public List<Loan_LoanApplay_Bankmember_Member_Loanproduct_JoinVO> select_LOAN_LOANAPPLY_LOANPRODUCT_BANKMEMBER_MEMBER_JOIN(int bankid) {
		return selectDAO.select_LOAN_LOANAPPLY_LOANPRODUCT_BANKMEMBER_MEMBER_JOIN(bankid);
	}
	public List<BankGradeVO> select_BANKGRADE() {
		return selectDAO.select_BANKGRADE();
	}
	public List<GradeVO> select_GRADE() {
		return selectDAO.select_GRADE();
	}
	public BankVO select_BANKbyNUM(int num) {
		return selectDAO.select_BANKbyNUM(num);
	}
	public List<LoanProductVO> select_LOANPRODUCT() {
		return selectDAO.select_LOANPRODUCT();
	}
	public LoanProductVO select_LOANPRODUCTBYNUM(int bankid, int num) {
		return selectDAO.select_LOANPRODUCTBYNUM(bankid,num);
	}
	public BankMemberVO select_BANKbyNUMID(int bankid, int memberid, int num) {
		return selectDAO.select_BANKbyNUMID(bankid, memberid, num);
	}
	public List<LoanVO> select_LOAN() {
		return selectDAO.select_LOAN();
	}
	public LoanVO select_LOANBYID(int BANKMEMBERID ,int LOANPRODUCTID ) {
		return selectDAO.select_LOANBYID(BANKMEMBERID, LOANPRODUCTID);
	}
	public List<BankMember_MemberVO> select_BANKMEMBER_MEMBER_JOIN_bymemberid(int memberid) {
		return selectDAO.select_BANKMEMBER_MEMBER_JOIN_bymemberid(memberid);
	}
	public BankMember_MemberVO select_BANKMEMBER_MEMBER_JOIN_bynum(int memberid, int num) {
		return selectDAO.select_BANKMEMBER_MEMBER_JOIN_bynum(memberid, num);
	}
	public List<Loan_LoanApplay_Bankmember_Member_Loanproduct_JoinVO> select_LOANBYID_BYBANKMEMBERID(int BANKMEMBERID ) {
		return selectDAO.select_LOANBYID_BYBANKMEMBERID(BANKMEMBERID);
	}
	public BankVO select_BANKBYNAME(String bankname) {
		return selectDAO.select_BANKBYNAME(bankname);
	}
	public BankGradeVO select_BANKGRADEGETINTEREST(int bankid, int gradeid) {
		return selectDAO.select_BANKGRADEGETINTEREST(bankid, gradeid);
	}
	public List<Loan_LoanApplay_Bankmember_Member_Loanproduct_JoinVO> select_LOANBYID_BYBANKMEMBERBANKLOANID(int LOANID ) {
		return selectDAO.select_LOANBYID_BYBANKMEMBERBANKLOANID(LOANID);
	}
	public List<Loan_LoanApplay_Bankmember_Member_Loanproduct_JoinVO> select_LOANBYID_BYBANKMEMBERIDNUM(int BANKMEMBERID, int num ) {
		return selectDAO.select_LOANBYID_BYBANKMEMBERIDNUM(BANKMEMBERID, num);
	}
	public List<LoanApplyVO> SELECT_LOANAPPLY() {
		return selectDAO.SELECT_LOANAPPLY();
	}
	public BankMemberVO select_BANKMEMGER_Y(int bankid, int memberid) {
		return selectDAO.select_BANKMEMGER_Y(bankid,memberid);
	}
	public List<BankMemberVO> select_BANKMEMGER_N(int bankid, int memberid) {
		return selectDAO.select_BANKMEMGER_N(bankid,memberid);
	}
	public List<LoanProductVO> select_LOANPRODUCTBYBANKID(int bankid) {
		return selectDAO.select_LOANPRODUCTBYBANKID(bankid);
	}
	public LoanProductVO select_LOANPRODUCTBYBANKID(int bankid, int num) {
		return selectDAO.select_LOANPRODUCTBYBANKID(bankid, num);
	}
	public List<Loan_LoanApply_Bankmember_LoanproductVO> select_ALL_JOIN(int bankmemberid) {
		return selectDAO.select_ALL_JOIN(bankmemberid);
	}
	public BankVO select_BANKbyid(int bankid) {
		return selectDAO.select_BANKbyid(bankid);
	}
	public BankMemberVO select_BANKMEMGER_ACCOUNT(int memberid, int num) {
		return selectDAO.select_BANKMEMGER_ACCOUNT(memberid, num);
	}
	public List<BankMemberVO> select_BANKMEMBERBYMEMBERID(int memberid) {
		return selectDAO.select_BANKMEMBERBYMEMBERID(memberid);
	}
	public GradeVO select_GRADEGETID(int gradevalue) {
		return selectDAO.select_GRADEGETID(gradevalue);
	}
	public MemberVO select_MEMBERBYIDPASSWORD(String membername, int password ) {
		return selectDAO.select_MEMBERBYIDPASSWORD(membername, password);
	}
	public LoanProductVO select_LOANPRODUCTBYPRODUCTID(int LOANPRODUCTID) {
		return selectDAO.select_LOANPRODUCTBYPRODUCTID(LOANPRODUCTID);
	}
	public int select_BANKMEMBER_count_n(int memberid, int bankid) {
		return selectDAO.select_BANKMEMBER_count_n(memberid, bankid);
	}
	public int select_BANKMEMBER_count_y(int memberid, int bankid) {
		return selectDAO.select_BANKMEMBER_count_y(memberid, bankid);
	}
	public BankMemberVO select_BANKMEMBER_byaccount(String account) {
		return selectDAO.select_BANKMEMBER_byaccount(account);
	}
	public List<BankMemberVO> select_BANKMEMGER_Y(int memberid) {
		return selectDAO.select_BANKMEMGER_Y(memberid);
	}
	public List<BankMemberVO> select_BANKMEMGER_N(int memberid) {
		return selectDAO.select_BANKMEMGER_N(memberid);
	}
	public Loan_LoanApply_Bankmember_LoanproductVO select_ALL_JOIN_num(int bankmemberid, int num) {
		return selectDAO.select_ALL_JOIN_num(bankmemberid, num);
	}
	public BankMemberVO select_BANKbyNUMID_N(int bankid, int memberid, int num) {
		return selectDAO.select_BANKbyNUMID_N(bankid,memberid,num);
	}
	public LoanApplyVO SELECT_LOANAPPLY_id(int loanid) {
		return selectDAO.SELECT_LOANAPPLY_id(loanid);
	}
	public List<Loan_Bankmember_Loanproduct_JoinVO> SELECT_LOAN_LOANPRODUCT_BANKMEMBER(int bankid, int memberid) {
		return selectDAO.SELECT_LOAN_LOANPRODUCT_BANKMEMBER(bankid, memberid);
	}
	
	/////////////insert
	public int insert_BANK(BankVO bankname) {
		return insertDAO.insert_BANK(bankname);
	}
	public int insert_BANKGRADE(BankGradeVO bgvo) {
		return insertDAO.insert_BANKGRADE(bgvo);
	}
	public int insert_LOANPRODUCT(LoanProductVO loanproduct) {
		return insertDAO.insert_LOANPRODUCT(loanproduct);
	}
	public int insert_member(MemberVO member) {
		return insertDAO.insert_member(member);
	}
	public int insert_loan(LoanVO loan) {
		return insertDAO.insert_loan(loan);
	}
	public int insert_loanapply(LoanApplyVO loanApply) {
		return insertDAO.insert_loanapply(loanApply);
	}
	public int insert_BANKMEMBER(BankMemberVO BANKMEMBER) {
		return insertDAO.insert_BANKMEMBER(BANKMEMBER);
	}
	
	/////////////update
	public int update_loanproduct(LoanProductVO loanProduct) {
		return updateDAO.update_loanproduct(loanProduct);
	}
	public int update_BANKGRADE(BankGradeVO bankGradeVO) {
		return updateDAO.update_BANKGRADE(bankGradeVO);
	}
	public int update_BANKMEMBER_BALANCE(BankMemberVO bankmember) {
		return updateDAO.update_BANKMEMBER_BALANCE(bankmember);
	}
	public int update_MEMBER(MemberVO mamber) {
		return updateDAO.update_MEMBER(mamber);
	}
	public int update_BANKMEMBER_SCORE(BankMemberVO bankmember,int memberid) {
		return updateDAO.update_BANKMEMBER_SCORE(bankmember,memberid);
	}
	public int update_LOANAPPLY(LoanApplyVO loanapply) {
		return updateDAO.update_LOANAPPLY(loanapply);
	}
	
	
	////////////delete
	public int delete_bank(String bankname) {
		return deleteDAO.delete_bank(bankname);
	}
	public int delete_LOANPRODUCT(int loanproductid) {
		return deleteDAO.delete_LOANPRODUCT(loanproductid);
	}
	public int delete_loan(int loanid) {
		return deleteDAO.delete_loan(loanid);
	}
}
