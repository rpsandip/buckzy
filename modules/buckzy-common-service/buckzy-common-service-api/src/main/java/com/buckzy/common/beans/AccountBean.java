package com.buckzy.common.beans;

import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

public class AccountBean {
	private int accountCategory;
	private long acctid;
	private String acctnr;
	private String acctusgcd;
	private String acctinstnnm;
	private String acctnr4dgt;
	public int getBankId() {
		return bankId;
	}

	public void setBankId(int bankId) {
		this.bankId = bankId;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	private String routngnm;
	private String swftbic;
	private String nameonacct;
	private String addr1onacct;
	private String addr2onacct;
	private String cityonacct;
	private String stateonacct;
	private String cntryonacct;
	private String ziponacct;
	private String exprymnth;
	private String expryyear;
	private String prefacctflag;
	private String accttype;
	private String moddate;
	private String modiby;
	private String fName;
	private String lName;
	private int bankId;
	private int branchId;
	private BranchBean branchBean;
	
	public AccountBean(){
		
	}
	
	public int getAccountCategory() {
		return accountCategory;
	}
	public void setAccountCategory(int accountCategory) {
		this.accountCategory = accountCategory;
	}
	public long getAcctid() {
		return acctid;
	}
	public void setAcctid(long acctid) {
		this.acctid = acctid;
	}
	public String getAcctnr() {
		return acctnr;
	}
	public void setAcctnr(String acctnr) {
		this.acctnr = acctnr;
	}
	public String getAcctusgcd() {
		return acctusgcd;
	}
	public void setAcctusgcd(String acctusgcd) {
		this.acctusgcd = acctusgcd;
	}
	public String getAcctinstnnm() {
		return acctinstnnm;
	}
	public void setAcctinstnnm(String acctinstnnm) {
		this.acctinstnnm = acctinstnnm;
	}
	public String getRoutngnm() {
		return routngnm;
	}
	public void setRoutngnm(String routngnm) {
		this.routngnm = routngnm;
	}
	public String getSwftbic() {
		return swftbic;
	}
	public void setSwftbic(String swftbic) {
		this.swftbic = swftbic;
	}
	public String getNameonacct() {
		return nameonacct;
	}
	public void setNameonacct(String nameonacct) {
		this.nameonacct = nameonacct;
	}
	public String getAddr1onacct() {
		return addr1onacct;
	}
	public void setAddr1onacct(String addr1onacct) {
		this.addr1onacct = addr1onacct;
	}
	public String getAddr2onacct() {
		return addr2onacct;
	}
	public void setAddr2onacct(String addr2onacct) {
		this.addr2onacct = addr2onacct;
	}
	public String getCityonacct() {
		return cityonacct;
	}
	public void setCityonacct(String cityonacct) {
		this.cityonacct = cityonacct;
	}
	public String getStateonacct() {
		return stateonacct;
	}
	public void setStateonacct(String stateonacct) {
		this.stateonacct = stateonacct;
	}
	public String getCntryonacct() {
		return cntryonacct;
	}
	public void setCntryonacct(String cntryonacct) {
		this.cntryonacct = cntryonacct;
	}
	public String getZiponacct() {
		return ziponacct;
	}
	public void setZiponacct(String ziponacct) {
		this.ziponacct = ziponacct;
	}
	public String getExprymnth() {
		return exprymnth;
	}
	public void setExprymnth(String exprymnth) {
		this.exprymnth = exprymnth;
	}
	public String getExpryyear() {
		return expryyear;
	}
	public void setExpryyear(String expryyear) {
		this.expryyear = expryyear;
	}
	public String getPrefacctflag() {
		return prefacctflag;
	}
	public void setPrefacctflag(String prefacctflag) {
		this.prefacctflag = prefacctflag;
	}
	public String getAccttype() {
		return accttype;
	}
	public void setAccttype(String accttype) {
		this.accttype = accttype;
	}
	public String getModdate() {
		return moddate;
	}
	public BranchBean getBranchBean() {
		return branchBean;
	}

	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

	public void setModdate(String moddate) {
		this.moddate = moddate;
	}
	public String getModiby() {
		return modiby;
	}
	public void setModiby(String modiby) {
		this.modiby = modiby;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getAcctnr4dgt() {
		return acctnr4dgt;
	}

	public void setAcctnr4dgt(String acctnr) {
		String acctNr4digit = StringPool.BLANK;
		if(Validator.isNotNull(acctnr) && acctnr.length()>=4){
			acctNr4digit = acctnr.substring((acctnr.length()-4), acctnr.length());
		}
		this.acctnr4dgt = acctNr4digit;
	}

	
	
}
