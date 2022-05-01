package VO;

public class BankGradeVO {
	private int BANKGRADEID;
	private int BANKID;
	private int GRADEID;
	private double INTEREST;
	public int getBANKGRADEID() {
		return BANKGRADEID;
	}
	public void setBANKGRADEID(int bANKGRADEID) {
		BANKGRADEID = bANKGRADEID;
	}
	public int getBANKID() {
		return BANKID;
	}
	public void setBANKID(int bANKID) {
		BANKID = bANKID;
	}
	public int getGRADEID() {
		return GRADEID;
	}
	public void setGRADEID(int gRADEID) {
		GRADEID = gRADEID;
	}
	public double getINTEREST() {
		return INTEREST;
	}
	public void setINTEREST(double iNTEREST) {
		INTEREST = iNTEREST;
	}
	@Override
	public String toString() {
		return "BankGradeVO [BANKGRADEID=" + BANKGRADEID + ", BANKID=" + BANKID + ", GRADEID=" + GRADEID + ", INTEREST="
				+ INTEREST + "]";
	}
	
}