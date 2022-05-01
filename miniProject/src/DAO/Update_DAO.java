package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Util.DBUtil;
import VO.BankGradeVO;
import VO.BankMemberVO;
import VO.LoanApplyVO;
import VO.LoanProductVO;
import VO.MemberVO;


public class Update_DAO {
	static final String SQL_UPDATE_LOANAPPLY = "update LOANAPPLY set ENDDATE = ?  ,PROLONG = ? where  LOANID =?";
	static final String SQL_UPDATE_LOANPRODUCT = "update LOANPRODUCT set MAXAMOUNT = ? ,CONDITION = ? where  PRODUCTNAME =? and bankid = ?";
	static final String SQL_UPDATE_BANKGRADE = "update BANKGRADE set INTEREST = ? where  GRADEID =? and bankid = ?";
	static final String SQL_UPDATE_BANKMEMBER_BALANCE = "update BANKMEMBER set BALANCE = ?  where BANKMEMBERID = ?";
	static final String SQL_UPDATE_BANKMEMBER_SCORE = "update BANKMEMBER set  SCORE = ? where MEMBERID = ?";
	static final String SQL_UPDATE_MEMBER = "update MEMBER set  PASSWORD = ? ,ANNUALSALES=?, EMPNUM=? where MEMBERID = ?";
	Connection conn;
	Statement st;
	PreparedStatement pst; // 바인딩변수지원 [?]
	ResultSet rs;
	int result;

	public int update_LOANAPPLY(LoanApplyVO loanapply) {
		int result = 0;
		
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(SQL_UPDATE_LOANAPPLY); // sql문장 미리 준비
			pst.setDate(1, loanapply.getENDDATE());
			pst.setInt(2, loanapply.getPROLONG());
			pst.setInt(3, loanapply.getLOANID());
			
			result = pst.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		return result;
	}
	public int update_MEMBER(MemberVO mamber) {
		int result = 0;
		
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(SQL_UPDATE_MEMBER); // sql문장 미리 준비
			pst.setInt(1, mamber.getPASSWORD());
			pst.setLong(2, mamber.getANNUALSALES());
			pst.setInt(3, mamber.getEMPNUM());
			pst.setInt(4, mamber.getMEMBERID());
			
			result = pst.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		return result;
	}
	public int update_BANKMEMBER_SCORE(BankMemberVO bankmember,int memberid) {
		int result = 0;
		
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(SQL_UPDATE_BANKMEMBER_SCORE); // sql문장 미리 준비
			pst.setLong(1, bankmember.getSCORE());
			pst.setInt(2, memberid);
			
			result = pst.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		return result;
	}
	public int update_BANKMEMBER_BALANCE(BankMemberVO bankmember) {
		int result = 0;
		
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(SQL_UPDATE_BANKMEMBER_BALANCE); // sql문장 미리 준비
			pst.setLong(1, bankmember.getBALANCE());
			pst.setInt(2, bankmember.getBANKMEMBERID());
			
			result = pst.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		return result;
	}
	public int update_BANKGRADE(BankGradeVO bankGradeVO ) {
		int result = 0;
		
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(SQL_UPDATE_BANKGRADE); // sql문장 미리 준비
			pst.setDouble(1, bankGradeVO.getINTEREST());
			pst.setInt(2, bankGradeVO.getGRADEID());
			pst.setInt(3, bankGradeVO.getBANKID());
			result = pst.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		return result;
	}
	public int update_loanproduct(LoanProductVO loanProduct) {
		int result = 0;

		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(SQL_UPDATE_LOANPRODUCT); // sql문장 미리 준비
			pst.setLong(1, loanProduct.getMAXAMOUNT());
			pst.setString(2, loanProduct.getCONDITION());
			pst.setString(3, loanProduct.getPRODUCTNAME());
			pst.setInt(4, loanProduct.getBANKID());
			result = pst.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		return result;
	}
}
