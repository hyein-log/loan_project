package VO;

public class BankMemberVO {
	private int BANKMEMBERID;
	private String ACCOUNT;
	private int MEMBERID;
	private int BANKID;
	private int SCORE;
	private Long BALANCE;
	private String PLUSACCOUNT;
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
	@Override
	public String toString() {
		return "BankMemberVO [BANKMEMBERID=" + BANKMEMBERID + ", ACCOUNT=" + ACCOUNT + ", MEMBERID=" + MEMBERID
				+ ", BANKID=" + BANKID + ", SCORE=" + SCORE + ", BALANCE=" + BALANCE + ", PLUSACCOUNT=" + PLUSACCOUNT
				+ "]";
	}
	
} 
