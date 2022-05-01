package VO;

import java.sql.Date;

public class BankMember_MemberVO {
	private int BANKMEMBERID;
	private String ACCOUNT;
	private int MEMBERID;
	private int BANKID;
	private int SCORE;
	private Long BALANCE;
	private String PLUSACCOUNT;
	private String MEMBERNAME;
	private int PASSWORD;
	private Long ANNUALSALES;
	private int EMPNUM;
	private Date ESTDATE;
	public int getBANKMEMBERID() {
		return BANKMEMBERID;
	}
	public void setBANKMEMBERID(int bANKMEMBERID) {
		BANKMEMBERID = bANKMEMBERID;
	}
	public String getACCOUNT() {
		return ACCOUNT;
	}
	public void setACCOUNT(String aCCOUNT) {
		ACCOUNT = aCCOUNT;
	}
	public int getMEMBERID() {
		return MEMBERID;
	}
	public void setMEMBERID(int mEMBERID) {
		MEMBERID = mEMBERID;
	}
	public int getBANKID() {
		return BANKID;
	}
	public void setBANKID(int bANKID) {
		BANKID = bANKID;
	}
	public int getSCORE() {
		return SCORE;
	}
	public void setSCORE(int sCORE) {
		SCORE = sCORE;
	}
	public Long getBALANCE() {
		return BALANCE;
	}
	public void setBALANCE(Long bALANCE) {
		BALANCE = bALANCE;
	}
	public String getPLUSACCOUNT() {
		return PLUSACCOUNT;
	}
	public void setPLUSACCOUNT(String pLUSACCOUNT) {
		PLUSACCOUNT = pLUSACCOUNT;
	}
	public String getMEMBERNAME() {
		return MEMBERNAME;
	}
	public void setMEMBERNAME(String mEMBERNAME) {
		MEMBERNAME = mEMBERNAME;
	}
	public int getPASSWORD() {
		return PASSWORD;
	}
	public void setPASSWORD(int pASSWORD) {
		PASSWORD = pASSWORD;
	}
	public Long getANNUALSALES() {
		return ANNUALSALES;
	}
	public void setANNUALSALES(Long aNNUALSALES) {
		ANNUALSALES = aNNUALSALES;
	}
	public int getEMPNUM() {
		return EMPNUM;
	}
	public void setEMPNUM(int eMPNUM) {
		EMPNUM = eMPNUM;
	}
	public Date getESTDATE() {
		return ESTDATE;
	}
	public void setESTDATE(Date eSTDATE) {
		ESTDATE = eSTDATE;
	}
	@Override
	public String toString() {
		return "BankMember_MemberVO [BANKMEMBERID=" + BANKMEMBERID + ", ACCOUNT=" + ACCOUNT + ", MEMBERID=" + MEMBERID
				+ ", BANKID=" + BANKID + ", SCORE=" + SCORE + ", BALANCE=" + BALANCE + ", PLUSACCOUNT=" + PLUSACCOUNT
				+ ", MEMBERNAME=" + MEMBERNAME + ", PASSWORD=" + PASSWORD + ", ANNUALSALES=" + ANNUALSALES + ", EMPNUM="
				+ EMPNUM + ", ESTDATE=" + ESTDATE + "]";
	}
	
}
