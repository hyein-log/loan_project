package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Util.DBUtil;

public class Delete_DAO {
	static final String SQL_DELETE_BANK = "delete from BANK where BANKNAME = ?";
	static final String SQL_DELETE_LOANPRODUCT = "delete from LOANPRODUCT where LOANPRODUCTid = ?";
	static final String SQL_DELETE_LOAN = "delete from LOAN where LOANID = ?";
	Connection conn;
	Statement st;
	PreparedStatement pst; // 바인딩변수지원 [?]
	ResultSet rs;
	int result;
	
	public int delete_loan(int loanid) {
		int result = 0;
		
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(SQL_DELETE_LOAN); // sql문장 미리 준비
			pst.setInt(1, loanid);
			
			result = pst.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		return result;
	}
	
	public int delete_bank(String bankname) {
		int result = 0;

		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(SQL_DELETE_BANK); // sql문장 미리 준비
			pst.setString(1, bankname);

			result = pst.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		return result;
	}
	public int delete_LOANPRODUCT(int loanproductid) {
		int result = 0;
		
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(SQL_DELETE_LOANPRODUCT); // sql문장 미리 준비
			pst.setInt(1, loanproductid);
			
			result = pst.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		return result;
	}
}
