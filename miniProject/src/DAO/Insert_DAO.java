package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Util.DBUtil;
import VO.BankGradeVO;
import VO.BankMemberVO;
import VO.BankVO;
import VO.LoanApplyVO;
import VO.LoanProductVO;
import VO.LoanVO;
import VO.MemberVO;


public class Insert_DAO {
	static final String SQL_INSERT_BANK ="INSERT INTO BANK(BANKID,BANKNAME) VALUES (seq_bank.nextval, ?)";
	static final String SQL_INSERT_BANKGRADE ="INSERT INTO BANKGRADE(BANKGRADEID,BANKID,GRADEID) VALUES (seq_bankgrade.nextval, ?,?)";
	static final String SQL_INSERT_LOANPRODUCT ="INSERT INTO LOANPRODUCT VALUES (seq_LOANPRODUCT.nextval, ?,?,?,?)";
	static final String SQL_INSERT_MEMBER ="INSERT INTO MEMBER VALUES (seq_MEMBER.nextval, ?,?,?,?,?)";
	static final String SQL_INSERT_BANKMEMBER ="INSERT INTO BANKMEMBER VALUES (seq_BANKMEMBER.nextval, ?,?,?,?,?,?)";
	static final String SQL_INSERT_LOAN ="INSERT INTO LOAN VALUES (seq_LOAN.nextval, ?,?,?)";
	static final String SQL_INSERT_LOANAPPLY ="INSERT INTO LOANAPPLY(LOANAPPLYNUM,LOANID,NOWSCORE) VALUES (seq_LOANAPPLY.nextval, ?,?)";
	
	Connection conn;
	Statement st;
	PreparedStatement pst; // 바인딩변수지원 [?]
	ResultSet rs;
	int result;
	
	
	public int insert_BANKMEMBER(BankMemberVO BANKMEMBER) {
		int result = 0;
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(SQL_INSERT_BANKMEMBER); // sql문장 미리 준비
			pst.setString(1, BANKMEMBER.getACCOUNT());
			pst.setInt(2, BANKMEMBER.getMEMBERID());
			pst.setInt(3, BANKMEMBER.getBANKID());
			pst.setInt(4, BANKMEMBER.getSCORE());
			pst.setLong(5, BANKMEMBER.getBALANCE());
			pst.setString(6, BANKMEMBER.getPLUSACCOUNT());
			result = pst.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		return result;
	}
	
	public int insert_loanapply(LoanApplyVO loanApply) {
		int result = 0;
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(SQL_INSERT_LOANAPPLY); // sql문장 미리 준비
			pst.setInt(1, loanApply.getLOANID());
			pst.setInt(2, loanApply.getNOWSCORE());
			result = pst.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		return result;
	}
	
	public int insert_loan(LoanVO loan) {
		int result = 0;
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(SQL_INSERT_LOAN); // sql문장 미리 준비
			pst.setInt(1, loan.getBANKMEMBERID());
			pst.setInt(2, loan.getLOANPRODUCTID());
			pst.setLong(3, loan.getAMOUNT());
			result = pst.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		return result;
	}
	public int insert_member(MemberVO member) {
		int result = 0;
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(SQL_INSERT_MEMBER); // sql문장 미리 준비
			pst.setString(1, member.getMEMBERNAME());
			pst.setInt(2, member.getPASSWORD());
			pst.setLong(3, member.getANNUALSALES());
			pst.setInt(4, member.getEMPNUM());
			pst.setDate(5, member.getESTDATE());
			result = pst.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		return result;
	}
	public int insert_LOANPRODUCT(LoanProductVO loanproduct) {
		int result = 0;
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(SQL_INSERT_LOANPRODUCT); // sql문장 미리 준비
			pst.setLong(1, loanproduct.getMAXAMOUNT());
			pst.setInt(2, loanproduct.getBANKID());
			pst.setString(3, loanproduct.getPRODUCTNAME());
			pst.setString(4, loanproduct.getCONDITION());
			result = pst.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		return result;
	}
	public int insert_BANK(BankVO bankname) {
		int result = 0;
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(SQL_INSERT_BANK); // sql문장 미리 준비
			pst.setString(1, bankname.getBankname());
			result = pst.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		return result;
	}
	public int insert_BANKGRADE(BankGradeVO bgvo) {
		int result = 0;
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(SQL_INSERT_BANKGRADE); // sql문장 미리 준비
			pst.setInt(1, bgvo.getBANKID());
			pst.setInt(2, bgvo.getGRADEID());
			result = pst.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		return result;
	}

}
