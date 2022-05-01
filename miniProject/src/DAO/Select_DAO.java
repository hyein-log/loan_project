package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Util.DBUtil;
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


public class Select_DAO {
	static final String SQL_SELECT_BANKMEMBER_ACCOUNT ="SELECT * FROM (SELECT rownum AS num , b.* FROM (SELECT * FROM BANKMEMBER WHERE MEMBERID = ? AND PLUSACCOUNT= 'N' ORDER BY MEMBERID) b)a where a.num =? ";
	static final String SQL_SELECT_BANKMEMBER_Y ="SELECT * FROM BANKMEMBER WHERE BANKID =? AND MEMBERID = ? AND PLUSACCOUNT= 'Y' ORDER BY 1";
	static final String SQL_SELECT_BANKMEMBER_Y_ID ="SELECT * FROM BANKMEMBER WHERE MEMBERID = ? AND PLUSACCOUNT= 'Y' ORDER BY 1";
	static final String SQL_SELECT_BANKMEMBER_N_ID ="SELECT * FROM BANKMEMBER WHERE MEMBERID = ? AND PLUSACCOUNT= 'N' ORDER BY 1";
	static final String SQL_SELECT_BANKMEMBER_N ="SELECT * FROM BANKMEMBER WHERE BANKID =? AND MEMBERID = ? AND PLUSACCOUNT= 'N' ORDER BY 1";
	static final String SQL_SELECT_MEMBER ="SELECT * FROM MEMBER ORDER BY 1";
	static final String SQL_SELECT_MEMBERBYIDPASSWORD ="SELECT * FROM MEMBER WHERE MEMBERNAME = ? AND PASSWORD =?";
	static final String SQL_SELECT_GRADE ="SELECT * FROM GRADE ORDER BY 1";
	static final String SQL_SELECT_GRADEGETID ="SELECT * FROM GRADE where grade = ? ORDER BY 1";
	static final String SQL_SELECT_BANK ="SELECT * FROM BANK ORDER BY 1";
	static final String SQL_SELECT_BANKBYID ="SELECT * FROM BANK where bankid= ? ORDER BY 1";
	static final String SQL_SELECT_BANKBYNAME ="SELECT * FROM BANK WHERE BANKNAME = ? ORDER BY 1";
	static final String SQL_SELECT_BANKBYNUM ="SELECT * FROM (SELECT rownum AS num , b.* from (SELECT * FROM bank ORDER BY bankid) b) a WHERE a.num = ?";
	static final String SQL_SELECT_BANKBYNUMANDID ="SELECT * FROM (SELECT rownum AS num, b.* FROM(SELECT * FROM BANKMEMBER WHERE bankid =? AND memberid =? ORDER BY memberid) b) a WHERE a.num=?";
	static final String SQL_SELECT_BANKBYNUMANDID_N ="SELECT * FROM (SELECT rownum AS num, b.* FROM(SELECT * FROM BANKMEMBER WHERE bankid =? AND memberid =? and PLUSACCOUNT='N' ORDER BY memberid) b) a WHERE a.num=?";
	static final String SQL_SELECT_BANKGRADE ="SELECT * FROM BANKGRADE ORDER BY 1";
	static final String SQL_SELECT_BANKGRADEGETINTEREST ="SELECT * FROM BANKGRADE WHERE BANKID =? AND GRADEID =? ORDER BY 1";
	static final String SQL_SELECT_BANKMEMBER ="SELECT * FROM BANKMEMBER ORDER BY MEMBERID";
	static final String SQL_SELECT_BANKMEMBER_BYACCOUNT ="SELECT * FROM BANKMEMBER where account =? ";
	static final String SQL_SELECT_BANKMEMBER_COUNT_N ="SELECT COUNT(*) FROM BANKMEMBER where memberid = ? and bankid = ? and PLUSACCOUNT='N'";
	static final String SQL_SELECT_BANKMEMBER_COUNT_Y ="SELECT COUNT(*) FROM BANKMEMBER where memberid = ? and bankid = ? and PLUSACCOUNT='Y'";
	static final String SQL_SELECT_BANKMEMBERBYMEMBERID ="SELECT * FROM BANKMEMBER where memberid =? ORDER BY MEMBERID";
	static final String SQL_SELECT_BANKMEMBER_MEMBER_JOIN ="SELECT * FROM BANKMEMBER JOIN MEMBER USING(MEMBERID) WHERE BANKID =? ORDER BY MEMBERID";
	static final String SQL_SELECT_BANKMEMBER_MEMBER_JOIN_BYNUM ="SELECT * FROM (SELECT rownum AS num , j.* FROM(SELECT * FROM BANKMEMBER JOIN MEMBER USING(MEMBERID) WHERE MEMBERID =?) j)a WHERE a.num=?";
	static final String SQL_SELECT_BANKMEMBER_MEMBER_JOINBY_MEMBERID ="SELECT * FROM BANKMEMBER JOIN MEMBER USING(MEMBERID) WHERE MEMBERID =?";
	static final String SQL_SELECT_LOAN_LOANAPPLY_LOANPRODUCT_BANKMEMBER_MEMBER_JOIN ="SELECT * FROM LOANAPPLY JOIN LOAN using(loanid) JOIN BANKMEMBER b using(bankmemberid) JOIN MEMBER using(memberid) JOIN LOANPRODUCT using(loanproductid) WHERE b.bankid=?";
	static final String SQL_SELECT_LOAN_LOANAPPLY_LOANPRODUCT_BANKMEMBER_JOIN ="select * from (SELECT rownum as num , k.* FROM (SELECT * FROM LOANAPPLY l3 JOIN LOAN  using(loanid) JOIN BANKMEMBER b USING(BANKMEMBERID) JOIN LOANPRODUCT l2 using(LOANPRODUCTID) WHERE BANKMEMBERID =? ORDER BY AMOUNT)k)";
	static final String SQL_SELECT_LOAN_LOANAPPLY_LOANPRODUCT_BANKMEMBER_JOIN_NUM ="select * from (SELECT rownum as num , k.* FROM (SELECT * FROM LOANAPPLY l3 JOIN LOAN  using(loanid) JOIN BANKMEMBER b USING(BANKMEMBERID) JOIN LOANPRODUCT l2 using(LOANPRODUCTID) WHERE BANKMEMBERID =? ORDER BY AMOUNT)k)a where a.num = ? ";
	static final String SQL_SELECT_LOANPRODUCT ="SELECT * FROM LOANPRODUCT order by MAXAMOUNT desc";
	static final String SQL_SELECT_LOANPRODUCTBYPRODUCTID ="SELECT * FROM LOANPRODUCT WHERE LOANPRODUCTID = ? order by 1";
	static final String SQL_SELECT_LOANPRODUCTBYBANKID ="SELECT * FROM LOANPRODUCT WHERE BANKID = ?order by MAXAMOUNT desc";
	static final String SQL_SELECT_LOANPRODUCTBYBANKID_ANDNUM ="SELECT * FROM(SELECT rownum AS num , l.* from(select * FROM LOANPRODUCT WHERE BANKID = ? order by MAXAMOUNT DESC) l )a WHERE a.num=?";
	static final String SQL_SELECT_LOANPRODUCTBYNUM ="SELECT * FROM(SELECT rownum AS num , l.* FROM (SELECT * FROM LOANPRODUCT  WHERE bankid = ? ORDER BY MAXAMOUNT desc)l)a WHERE a.num=? ";
	static final String SQL_SELECT_LOAN ="SELECT * FROM LOAN ORDER BY 1 ";
	static final String SQL_SELECT_LOAN_LOANPRODUCT_BANKMEMBER ="SELECT * FROM LOAN JOIN LOANPRODUCT l USING(LOANPRODUCTID) JOIN BANKMEMBER b USING(BANKMEMBERID) WHERE l.BANKID = ? AND MEMBERID= ?";
	static final String SQL_SELECT_LOANAPPLY ="SELECT * FROM LOANAPPLY ORDER BY 1 ";
	static final String SQL_SELECT_LOANAPPLY_ID ="SELECT * FROM LOANAPPLY WHERE LOANID =? ORDER BY 1 ";
	static final String SQL_SELECT_LOANBYID ="SELECT * FROM LOAN WHERE BANKMEMBERID =? AND LOANPRODUCTID =? ORDER BY 1 ";
	static final String SQL_SELECT_LOANBYID_BYBANKMEMBERID ="SELECT * FROM LOANAPPLY JOIN LOAN using(loanid) JOIN BANKMEMBER b using(bankmemberid) JOIN MEMBER using(memberid) JOIN LOANPRODUCT using(loanproductid) WHERE BANKMEMBERID =?";
	static final String SQL_SELECT_LOANBYID_BYBANKMEMBERIDNUM ="SELECT * FROM (SELECT rownum AS num , v.* FROM (SELECT * FROM LOANAPPLY JOIN LOAN using(loanid) JOIN BANKMEMBER b using(bankmemberid) JOIN MEMBER using(memberid) JOIN LOANPRODUCT using(loanproductid) WHERE BANKMEMBERID =?)v)a WHERE a.num = ?";
	static final String SQL_SELECT_LOANBYID_BYBANKLOANID ="SELECT * FROM LOANAPPLY JOIN LOAN using(loanid) JOIN BANKMEMBER b using(bankmemberid) JOIN MEMBER using(memberid) JOIN LOANPRODUCT using(loanproductid) WHERE LOANID =?";
	Connection conn;
	Statement st;
	PreparedStatement pst; // 바인딩변수지원 [?]
	ResultSet rs;
	int result;
	

	public LoanApplyVO SELECT_LOANAPPLY_id(int loanid) {
		LoanApplyVO  loan = null;
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(SQL_SELECT_LOANAPPLY_ID); // sql문장 미리 준비
			pst.setInt(1, loanid); 
			rs = pst.executeQuery(); 
		
			while (rs.next()) {
				loan=loan2(rs);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		
		return loan;
	}
	public List<LoanApplyVO> SELECT_LOANAPPLY() {
		List<LoanApplyVO>  loan = new ArrayList<>();
		conn = DBUtil.getConnection();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(SQL_SELECT_LOANAPPLY);
			
			while (rs.next()) {
				loan.add(loan2(rs));
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		
		return loan;
	}
	private LoanApplyVO loan2(ResultSet rs) throws SQLException {
		LoanApplyVO loan = new LoanApplyVO();
		loan.setENDDATE(rs.getDate("ENDDATE"));
		loan.setLOANAPPLYNUM(rs.getInt("LOANAPPLYNUM"));
		loan.setLOANID(rs.getInt("LOANID"));
		loan.setNOWSCORE(rs.getInt("NOWSCORE"));
		loan.setPROLONG(rs.getInt("PROLONG"));
		loan.setSTARTDATE(rs.getDate("STARTDATE"));
		return loan;
	}
	public List<LoanVO> select_LOAN() {
		List<LoanVO>  loan = new ArrayList<>();
		conn = DBUtil.getConnection();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(SQL_SELECT_LOAN);
			
			while (rs.next()) {
				loan.add(loan(rs));
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		
		return loan;
	}
	public BankMemberVO select_BANKMEMGER_ACCOUNT(int memberid, int num) {
		BankMemberVO  bankmember = null;
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(SQL_SELECT_BANKMEMBER_ACCOUNT); // sql문장 미리 준비
			pst.setInt(1, memberid); 
			pst.setInt(2, num); 
			rs = pst.executeQuery(); 
			
			while (rs.next()) {
				bankmember=bankmember(rs);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		
		return bankmember;
	}
	public List<BankMemberVO> select_BANKMEMGER_N(int bankid, int memberid) {
		List<BankMemberVO>  bankmember = new ArrayList<>();
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(SQL_SELECT_BANKMEMBER_N); // sql문장 미리 준비
			pst.setInt(1, bankid); 
			pst.setInt(2, memberid); 
			rs = pst.executeQuery(); 
			
			while (rs.next()) {
				bankmember.add(bankmember(rs));
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		
		return bankmember;
	}
	public List<BankMemberVO> select_BANKMEMGER_N(int memberid) {
		List<BankMemberVO>  bankmember = new ArrayList<>();
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(SQL_SELECT_BANKMEMBER_N_ID); // sql문장 미리 준비
			pst.setInt(1, memberid); 
			rs = pst.executeQuery(); 
			
			while (rs.next()) {
				bankmember.add(bankmember(rs));
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		
		return bankmember;
	}
	public List<BankMemberVO> select_BANKMEMGER_Y(int memberid) {
		List<BankMemberVO>  bankmember = new ArrayList<>();
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(SQL_SELECT_BANKMEMBER_Y_ID); // sql문장 미리 준비
			pst.setInt(1, memberid); 
			rs = pst.executeQuery(); 
			
			while (rs.next()) {
				bankmember.add(bankmember(rs));
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		
		return bankmember;
	}
	public BankMemberVO select_BANKMEMGER_Y(int bankid, int memberid) {
		BankMemberVO  bankmember = null;
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(SQL_SELECT_BANKMEMBER_Y); // sql문장 미리 준비
			pst.setInt(1, bankid); 
			pst.setInt(2, memberid); 
			rs = pst.executeQuery(); 
			
			while (rs.next()) {
				bankmember=bankmember(rs);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		
		return bankmember;
	}
	private LoanVO loan(ResultSet rs) throws SQLException {
		LoanVO loan = new LoanVO();
		loan.setAMOUNT(rs.getLong("AMOUNT"));
		loan.setBANKMEMBERID(rs.getInt("BANKMEMBERID"));
		loan.setLOANPRODUCTID(rs.getInt("LOANPRODUCTID"));
		loan.setLOANID(rs.getInt("LOANID"));
		return loan;
	}
	public List<Loan_LoanApplay_Bankmember_Member_Loanproduct_JoinVO> select_LOANBYID_BYBANKMEMBERBANKLOANID(int LOANID ) {
		List<Loan_LoanApplay_Bankmember_Member_Loanproduct_JoinVO> loanProduct =  new ArrayList<>();;
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(SQL_SELECT_LOANBYID_BYBANKLOANID); // sql문장 미리 준비
			pst.setInt(1, LOANID); 
			rs = pst.executeQuery(); 
			
			while (rs.next()) {
				loanProduct.add(bankmemberandmemberANDLOANS(rs));
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		
		return loanProduct;
	}
	public List<Loan_LoanApplay_Bankmember_Member_Loanproduct_JoinVO> select_LOANBYID_BYBANKMEMBERIDNUM(int BANKMEMBERID, int num ) {
		List<Loan_LoanApplay_Bankmember_Member_Loanproduct_JoinVO> loanProduct =  new ArrayList<>();;
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(SQL_SELECT_LOANBYID_BYBANKMEMBERIDNUM); // sql문장 미리 준비
			pst.setInt(1, BANKMEMBERID); 
			pst.setInt(2, num); 
			rs = pst.executeQuery(); 
			
			while (rs.next()) {
				loanProduct.add(bankmemberandmemberANDLOANS(rs));
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		
		return loanProduct;
	}
	public List<Loan_LoanApplay_Bankmember_Member_Loanproduct_JoinVO> select_LOANBYID_BYBANKMEMBERID(int BANKMEMBERID ) {
		List<Loan_LoanApplay_Bankmember_Member_Loanproduct_JoinVO> loanProduct =  new ArrayList<>();;
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(SQL_SELECT_LOANBYID_BYBANKMEMBERID); // sql문장 미리 준비
			pst.setInt(1, BANKMEMBERID); 
			rs = pst.executeQuery(); 
			
			while (rs.next()) {
				loanProduct.add(bankmemberandmemberANDLOANS(rs));
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		
		return loanProduct;
	}
	public LoanVO select_LOANBYID(int BANKMEMBERID ,int LOANPRODUCTID ) {
		LoanVO loanProduct = null;
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(SQL_SELECT_LOANBYID); // sql문장 미리 준비
			pst.setInt(1, BANKMEMBERID); 
			pst.setInt(2, LOANPRODUCTID); 
			rs = pst.executeQuery(); 
			
			while (rs.next()) {
				loanProduct=loan(rs);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		
		return loanProduct;
	}
	public LoanProductVO select_LOANPRODUCTBYNUM(int bankid, int num) {
		LoanProductVO loanProduct = null;
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(SQL_SELECT_LOANPRODUCTBYNUM); // sql문장 미리 준비
			pst.setInt(1, bankid); 
			pst.setInt(2, num); 
			rs = pst.executeQuery(); 
			
			while (rs.next()) {
				loanProduct=loanproduct(rs);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		
		return loanProduct;
	}
	public Loan_LoanApply_Bankmember_LoanproductVO select_ALL_JOIN_num(int bankmemberid, int num) {
		Loan_LoanApply_Bankmember_LoanproductVO bankmemberANDloans= null;
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(SQL_SELECT_LOAN_LOANAPPLY_LOANPRODUCT_BANKMEMBER_JOIN_NUM); // sql문장 미리 준비
			pst.setInt(1, bankmemberid); 
			pst.setInt(2, num); 
			rs = pst.executeQuery(); 
			while (rs.next()) {
				bankmemberANDloans=loansbankmember(rs);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		
		return bankmemberANDloans;
	}
	public List<Loan_Bankmember_Loanproduct_JoinVO> SELECT_LOAN_LOANPRODUCT_BANKMEMBER(int bankid, int memberid) {
		List<Loan_Bankmember_Loanproduct_JoinVO> bankmemberANDloans = new ArrayList<>();
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(SQL_SELECT_LOAN_LOANPRODUCT_BANKMEMBER); // sql문장 미리 준비
			pst.setInt(1, bankid); 
			pst.setInt(2, memberid); 
			rs = pst.executeQuery(); 
			while (rs.next()) {
				bankmemberANDloans.add(loansbankmem(rs));
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		
		return bankmemberANDloans;
	}
	private Loan_Bankmember_Loanproduct_JoinVO loansbankmem(ResultSet rs) throws SQLException {
		Loan_Bankmember_Loanproduct_JoinVO loansbankmem = new Loan_Bankmember_Loanproduct_JoinVO();
		loansbankmem.setACCOUNT(rs.getString("ACCOUNT"));
		loansbankmem.setAMOUNT(rs.getLong("AMOUNT"));
		loansbankmem.setBALANCE(rs.getLong("BALANCE"));
		loansbankmem.setBANKID(rs.getInt("BANKID"));
		loansbankmem.setBANKMEMBERID(rs.getInt("BANKMEMBERID"));
		loansbankmem.setCONDITION(rs.getString("CONDITION"));
		loansbankmem.setMEMBERID(rs.getInt("MEMBERID"));
		loansbankmem.setPLUSACCOUNT(rs.getString("PLUSACCOUNT"));
		loansbankmem.setSCORE(rs.getInt("SCORE"));
		loansbankmem.setLOANPRODUCTID(rs.getInt("LOANPRODUCTID"));
		loansbankmem.setLOANID(rs.getInt("LOANID"));
		loansbankmem.setMAXAMOUNT(rs.getLong("MAXAMOUNT"));
		loansbankmem.setPRODUCTNAME(rs.getString("PRODUCTNAME"));
		return loansbankmem;
	}
	public List<Loan_LoanApply_Bankmember_LoanproductVO> select_ALL_JOIN(int bankmemberid) {
		List<Loan_LoanApply_Bankmember_LoanproductVO> bankmemberANDloanslist = new ArrayList<>();
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(SQL_SELECT_LOAN_LOANAPPLY_LOANPRODUCT_BANKMEMBER_JOIN); // sql문장 미리 준비
			pst.setInt(1, bankmemberid); 
			rs = pst.executeQuery(); 
			while (rs.next()) {
				bankmemberANDloanslist.add(loansbankmember(rs));
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		
		return bankmemberANDloanslist;
	}
	
	private Loan_LoanApply_Bankmember_LoanproductVO loansbankmember(ResultSet rs) throws SQLException {
		Loan_LoanApply_Bankmember_LoanproductVO loansbankmember = new Loan_LoanApply_Bankmember_LoanproductVO();
		loansbankmember.setACCOUNT(rs.getString("ACCOUNT"));
		loansbankmember.setAMOUNT(rs.getLong("AMOUNT"));
		loansbankmember.setBALANCE(rs.getLong("BALANCE"));
		loansbankmember.setBANKID(rs.getInt("BANKID"));
		loansbankmember.setBANKMEMBERID(rs.getInt("BANKMEMBERID"));
		loansbankmember.setCONDITION(rs.getString("CONDITION"));
		loansbankmember.setMEMBERID(rs.getInt("MEMBERID"));
		loansbankmember.setPLUSACCOUNT(rs.getString("PLUSACCOUNT"));
		loansbankmember.setSCORE(rs.getInt("SCORE"));
		loansbankmember.setLOANPRODUCTID(rs.getInt("LOANPRODUCTID"));
		loansbankmember.setLOANID(rs.getInt("LOANID"));
		loansbankmember.setMAXAMOUNT(rs.getLong("MAXAMOUNT"));
		loansbankmember.setPRODUCTNAME(rs.getString("PRODUCTNAME"));
		loansbankmember.setENDDATE(rs.getDate("ENDDATE"));
		loansbankmember.setLOANAPPLYNUM(rs.getInt("LOANAPPLYNUM"));
		loansbankmember.setNOWSCORE(rs.getInt("NOWSCORE"));
		loansbankmember.setPROLONG(rs.getInt("PROLONG"));
		loansbankmember.setSTARTDATE(rs.getDate("STARTDATE"));
		return loansbankmember;
	}
	
	
	public List<Loan_LoanApplay_Bankmember_Member_Loanproduct_JoinVO> select_LOAN_LOANAPPLY_LOANPRODUCT_BANKMEMBER_MEMBER_JOIN(int bankid) {
		List<Loan_LoanApplay_Bankmember_Member_Loanproduct_JoinVO> bankmemberANDmemberlist = new ArrayList<>();
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(SQL_SELECT_LOAN_LOANAPPLY_LOANPRODUCT_BANKMEMBER_MEMBER_JOIN); // sql문장 미리 준비
			pst.setInt(1, bankid); 
			rs = pst.executeQuery(); 
			while (rs.next()) {
				bankmemberANDmemberlist.add(bankmemberandmemberANDLOANS(rs));
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		
		return bankmemberANDmemberlist;
	}
	private Loan_LoanApplay_Bankmember_Member_Loanproduct_JoinVO bankmemberandmemberANDLOANS(ResultSet rs) throws SQLException {
		Loan_LoanApplay_Bankmember_Member_Loanproduct_JoinVO bankmembermember = new Loan_LoanApplay_Bankmember_Member_Loanproduct_JoinVO();
		bankmembermember.setACCOUNT(rs.getString("ACCOUNT"));
		bankmembermember.setBALANCE(rs.getLong("BALANCE"));
		bankmembermember.setBANKID(rs.getInt("BANKID"));
		bankmembermember.setBANKMEMBERID(rs.getInt("BANKMEMBERID"));
		bankmembermember.setMEMBERID(rs.getInt("MEMBERID"));
		bankmembermember.setPLUSACCOUNT(rs.getString("PLUSACCOUNT"));
		bankmembermember.setSCORE(rs.getInt("SCORE"));
		bankmembermember.setANNUALSALES(rs.getLong("ANNUALSALES"));
		bankmembermember.setEMPNUM(rs.getInt("EMPNUM"));
		bankmembermember.setESTDATE(rs.getDate("ESTDATE"));
		bankmembermember.setMEMBERNAME(rs.getString("MEMBERNAME"));
		bankmembermember.setPASSWORD(rs.getInt("PASSWORD"));
		bankmembermember.setAMOUNT(rs.getLong("AMOUNT"));
		bankmembermember.setENDDATE(rs.getDate("ESTDATE"));
		bankmembermember.setLOANAPPLYNUM(rs.getInt("LOANAPPLYNUM"));
		bankmembermember.setLOANID(rs.getInt("LOANID"));
		bankmembermember.setLOANPRODUCTID(rs.getInt("LOANPRODUCTID"));
		bankmembermember.setMAXAMOUNT(rs.getLong("MAXAMOUNT"));
		bankmembermember.setNOWSCORE(rs.getInt("NOWSCORE"));
		bankmembermember.setPRODUCTNAME(rs.getString("PRODUCTNAME"));
		bankmembermember.setPROLONG(rs.getInt("PROLONG"));
		bankmembermember.setSTARTDATE(rs.getDate("STARTDATE"));
		return bankmembermember;
	}
	public List<BankMember_MemberVO> select_BANKMEMBER_MEMBER_JOIN_bymemberid(int memberid) {
		List<BankMember_MemberVO> bankmemberANDmemberlist = new ArrayList<>();
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(SQL_SELECT_BANKMEMBER_MEMBER_JOINBY_MEMBERID); // sql문장 미리 준비
			pst.setInt(1, memberid); 
			rs = pst.executeQuery(); 
			while (rs.next()) {
				bankmemberANDmemberlist.add(bankmemberandmember(rs));
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		
		return bankmemberANDmemberlist;
	}
	public BankMember_MemberVO select_BANKMEMBER_MEMBER_JOIN_bynum(int memberid, int num) {
		BankMember_MemberVO bankmemberANDmember = null;
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(SQL_SELECT_BANKMEMBER_MEMBER_JOIN_BYNUM); // sql문장 미리 준비
			pst.setInt(1, memberid); 
			pst.setInt(2, num); 
			rs = pst.executeQuery(); 
			while (rs.next()) {
				bankmemberANDmember=bankmemberandmember(rs);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		
		return bankmemberANDmember;
	}
	public List<BankMember_MemberVO> select_BANKMEMBER_MEMBER_JOIN(int bankid) {
		List<BankMember_MemberVO> bankmemberANDmemberlist = new ArrayList<>();
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(SQL_SELECT_BANKMEMBER_MEMBER_JOIN); // sql문장 미리 준비
			pst.setInt(1, bankid); 
			rs = pst.executeQuery(); 
			while (rs.next()) {
				bankmemberANDmemberlist.add(bankmemberandmember(rs));
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		
		return bankmemberANDmemberlist;
	}
	private BankMember_MemberVO bankmemberandmember(ResultSet rs) throws SQLException {
		BankMember_MemberVO bankmembermember = new BankMember_MemberVO();
		bankmembermember.setACCOUNT(rs.getString("ACCOUNT"));
		bankmembermember.setBALANCE(rs.getLong("BALANCE"));
		bankmembermember.setBANKID(rs.getInt("BANKID"));
		bankmembermember.setBANKMEMBERID(rs.getInt("BANKMEMBERID"));
		bankmembermember.setMEMBERID(rs.getInt("MEMBERID"));
		bankmembermember.setPLUSACCOUNT(rs.getString("PLUSACCOUNT"));
		bankmembermember.setSCORE(rs.getInt("SCORE"));
		bankmembermember.setANNUALSALES(rs.getLong("ANNUALSALES"));
		bankmembermember.setEMPNUM(rs.getInt("EMPNUM"));
		bankmembermember.setESTDATE(rs.getDate("ESTDATE"));
		bankmembermember.setMEMBERID(rs.getInt("MEMBERID"));
		bankmembermember.setMEMBERNAME(rs.getString("MEMBERNAME"));
		bankmembermember.setPASSWORD(rs.getInt("PASSWORD"));
		return bankmembermember;
	}
	public LoanProductVO select_LOANPRODUCTBYBANKID(int bankid, int num) {
		LoanProductVO loanproduct = null;
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(SQL_SELECT_LOANPRODUCTBYBANKID_ANDNUM); // sql문장 미리 준비
			pst.setInt(1, bankid); 
			pst.setInt(2, num); 
			rs = pst.executeQuery(); 
			while (rs.next()) {
				loanproduct=loanproduct(rs);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		
		return loanproduct;
	}
	public LoanProductVO select_LOANPRODUCTBYPRODUCTID(int LOANPRODUCTID) {
		LoanProductVO loanproduct = null;
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(SQL_SELECT_LOANPRODUCTBYPRODUCTID); // sql문장 미리 준비
			pst.setInt(1, LOANPRODUCTID); 
			rs = pst.executeQuery(); 
			while (rs.next()) {
				loanproduct=loanproduct(rs);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		
		return loanproduct;
	}
	public List<LoanProductVO> select_LOANPRODUCTBYBANKID(int bankid) {
		List<LoanProductVO> loanproductlist = new ArrayList<>();
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(SQL_SELECT_LOANPRODUCTBYBANKID); // sql문장 미리 준비
			pst.setInt(1, bankid); 
			rs = pst.executeQuery(); 
			while (rs.next()) {
				loanproductlist.add(loanproduct(rs));
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		
		return loanproductlist;
	}
	public List<LoanProductVO> select_LOANPRODUCT() {
		List<LoanProductVO> loanproductlist = new ArrayList<>();
		conn = DBUtil.getConnection();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(SQL_SELECT_LOANPRODUCT);
			while (rs.next()) {
				loanproductlist.add(loanproduct(rs));
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		
		return loanproductlist;
	}
	private LoanProductVO loanproduct(ResultSet rs) throws SQLException {
		LoanProductVO loanproduct = new LoanProductVO();
		loanproduct.setBANKID(rs.getInt("BANKID"));
		loanproduct.setCONDITION(rs.getString("CONDITION"));
		loanproduct.setLOANPRODUCTID(rs.getInt("LOANPRODUCTID"));
		loanproduct.setMAXAMOUNT(rs.getLong("MAXAMOUNT"));
		loanproduct.setPRODUCTNAME(rs.getString("PRODUCTNAME"));
		
		return loanproduct;
	}
	public List<BankMemberVO> select_BANKMEMBERBYMEMBERID(int memberid) {
		List<BankMemberVO> bankmember = new ArrayList<>();
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(SQL_SELECT_BANKMEMBERBYMEMBERID); // sql문장 미리 준비
			pst.setInt(1, memberid); 
			rs = pst.executeQuery(); 
		
			while (rs.next()) {
				bankmember.add(bankmember(rs));
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		
		return bankmember;
	}
	public int select_BANKMEMBER_count_y(int memberid, int bankid) {
		int resultcount = 0;
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(SQL_SELECT_BANKMEMBER_COUNT_Y); // sql문장 미리 준비
			pst.setInt(1, memberid); 
			pst.setInt(2, bankid); 
			rs = pst.executeQuery(); 
			while (rs.next()) {
				resultcount = rs.getInt(1);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		
		return resultcount;
	}
	public int select_BANKMEMBER_count_n(int memberid, int bankid) {
		int resultcount = 0;
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(SQL_SELECT_BANKMEMBER_COUNT_N); // sql문장 미리 준비
			pst.setInt(1, memberid); 
			pst.setInt(2, bankid); 
			rs = pst.executeQuery(); 
			while (rs.next()) {
				resultcount = rs.getInt(1);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		
		return resultcount;
	}
	public BankMemberVO select_BANKMEMBER_byaccount(String account) {
		BankMemberVO bankmember= null;
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(SQL_SELECT_BANKMEMBER_BYACCOUNT); // sql문장 미리 준비
			pst.setString(1, account); 
			rs = pst.executeQuery(); 
			while (rs.next()) {
				bankmember=bankmember(rs);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		
		return bankmember;
	}
	public List<BankMemberVO> select_BANKMEMBER() {
		List<BankMemberVO> bankmemberlist = new ArrayList<>();
		conn = DBUtil.getConnection();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(SQL_SELECT_BANKMEMBER);
			while (rs.next()) {
				bankmemberlist.add(bankmember(rs));
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		
		return bankmemberlist;
	}
	private BankMemberVO bankmember(ResultSet rs) throws SQLException {
		BankMemberVO bankmember = new BankMemberVO();
		bankmember.setACCOUNT(rs.getString("ACCOUNT"));
		bankmember.setBALANCE(rs.getLong("BALANCE"));
		bankmember.setBANKID(rs.getInt("BANKID"));
		bankmember.setBANKMEMBERID(rs.getInt("BANKMEMBERID"));
		bankmember.setMEMBERID(rs.getInt("MEMBERID"));
		bankmember.setPLUSACCOUNT(rs.getString("PLUSACCOUNT"));
		bankmember.setSCORE(rs.getInt("SCORE"));
		return bankmember;
	}
	public BankGradeVO select_BANKGRADEGETINTEREST(int bankid, int gradeid) {
		BankGradeVO bankgrade = null;
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(SQL_SELECT_BANKGRADEGETINTEREST); // sql문장 미리 준비
			pst.setInt(1, bankid); 
			pst.setInt(2, gradeid); 
			rs = pst.executeQuery(); 
			while (rs.next()) {
				bankgrade=bankgrade(rs);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		
		return bankgrade;
	}
	public List<BankGradeVO> select_BANKGRADE() {
		List<BankGradeVO> banklist = new ArrayList<>();
		conn = DBUtil.getConnection();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(SQL_SELECT_BANKGRADE);
			while (rs.next()) {
				banklist.add(bankgrade(rs));
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		
		return banklist;
	}
	private BankGradeVO bankgrade(ResultSet rs) throws SQLException {
		BankGradeVO bankgrade = new BankGradeVO();
		bankgrade.setBANKGRADEID(rs.getInt("BANKGRADEID"));
		bankgrade.setBANKID(rs.getInt("BANKID"));
		bankgrade.setGRADEID(rs.getInt("GRADEID"));
		bankgrade.setINTEREST(rs.getDouble("INTEREST"));
		return bankgrade;
	}
	public GradeVO select_GRADEGETID(int gradevalue) {
		GradeVO grade = null;
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(SQL_SELECT_GRADEGETID); // sql문장 미리 준비
			pst.setInt(1, gradevalue); 
			rs = pst.executeQuery(); 
			while (rs.next()) {
				grade=grade(rs);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		
		return grade;
	}
	public List<GradeVO> select_GRADE() {
		List<GradeVO> gradelist = new ArrayList<>();
		conn = DBUtil.getConnection();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(SQL_SELECT_GRADE);
			while (rs.next()) {
				gradelist.add(grade(rs));
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		
		return gradelist;
	}
	private GradeVO grade(ResultSet rs) throws SQLException {
		GradeVO grade = new GradeVO();
		grade.setGrade(rs.getInt("Grade"));
		grade.setGradeid(rs.getInt("Gradeid"));
		return grade;
	}
	public BankMemberVO select_BANKbyNUMID_N(int bankid, int memberid, int num) {
		BankMemberVO bankmember = null;
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(SQL_SELECT_BANKBYNUMANDID_N); // sql문장 미리 준비
			pst.setInt(1, bankid); 
			pst.setInt(2, memberid); 
			pst.setInt(3, num); 
			rs = pst.executeQuery(); 
			while (rs.next()) {
				bankmember=bankmember(rs);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		
		return bankmember;
	}
	public BankMemberVO select_BANKbyNUMID(int bankid, int memberid, int num) {
		BankMemberVO bankmember = null;
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(SQL_SELECT_BANKBYNUMANDID); // sql문장 미리 준비
			pst.setInt(1, bankid); 
			pst.setInt(2, memberid); 
			pst.setInt(3, num); 
			rs = pst.executeQuery(); 
			while (rs.next()) {
				bankmember=bankmember(rs);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		
		return bankmember;
	}
	public BankVO select_BANKbyNUM(int num) {
		BankVO bank = null;
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(SQL_SELECT_BANKBYNUM); // sql문장 미리 준비
			pst.setInt(1, num); 
			rs = pst.executeQuery(); 
			while (rs.next()) {
				bank=bank(rs);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		
		return bank;
	}
	public BankVO select_BANKBYNAME(String bankname) {
		BankVO bank = null;
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(SQL_SELECT_BANKBYNAME); // sql문장 미리 준비
			pst.setString(1, bankname); 
			rs = pst.executeQuery(); 
			while (rs.next()) {
				bank=bank(rs);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		
		return bank;
	}
	public BankVO select_BANKbyid(int bankid) {
		BankVO bank = null;
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(SQL_SELECT_BANKBYID); // sql문장 미리 준비
			pst.setInt(1, bankid); 
			rs = pst.executeQuery(); 
			while (rs.next()) {
				bank=bank(rs);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		
		return bank;
	}
	public List<BankVO> select_BANK() {
		List<BankVO> banklist = new ArrayList<>();
		conn = DBUtil.getConnection();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(SQL_SELECT_BANK);
			while (rs.next()) {
				banklist.add(bank(rs));
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		
		return banklist;
	}
	private BankVO bank(ResultSet rs) throws SQLException {
		BankVO bank = new BankVO();
		bank.setBankid(rs.getInt("BANKID"));
		bank.setBankname(rs.getString("BANKNAME"));
		return bank;
	}
	public MemberVO select_MEMBERBYIDPASSWORD(String membername, int password ) {
		MemberVO board = null;
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(SQL_SELECT_MEMBERBYIDPASSWORD); // sql문장 미리 준비
			pst.setString(1, membername); 
			pst.setInt(2, password); 
			rs = pst.executeQuery();
			while (rs.next()) {
				board=member(rs);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		
		return board;
	}
	public List<MemberVO> select_Member() {
		List<MemberVO> boardlist = new ArrayList<>();
		conn = DBUtil.getConnection();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(SQL_SELECT_MEMBER);
			while (rs.next()) {
				boardlist.add(member(rs));
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}

		return boardlist;
	}
	private MemberVO member(ResultSet rs) throws SQLException {
		MemberVO member = new MemberVO();
		member.setANNUALSALES(rs.getLong("ANNUALSALES"));
		member.setEMPNUM(rs.getInt("EMPNUM"));
		member.setESTDATE(rs.getDate("ESTDATE"));
		member.setMEMBERID(rs.getInt("MEMBERID"));
		member.setMEMBERNAME(rs.getString("MEMBERNAME"));
		member.setPASSWORD(rs.getInt("PASSWORD"));
		return member;
	}
	
}
