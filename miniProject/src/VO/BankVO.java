package VO;

public class BankVO {
	private int bankid;
	private String bankname;
	public int getBankid() {
		return bankid;
	}
	public void setBankid(int bankid) {
		this.bankid = bankid;
	}
	public String getBankname() {
		return bankname;
	}
	public void setBankname(String bankname) {
		this.bankname = bankname;
	}
	@Override
	public String toString() {
		return "BankVO [bankid=" + bankid + ", bankname=" + bankname + "]";
	}
	
}
