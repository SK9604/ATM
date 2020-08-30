public class Customer {
	
	public Customer(int aNumber, int aPin){//계좌번호와 비밀번호를 받음
		customerNumber = aNumber;//받아온 계좌번호를 customernumber에 저장
		pin = aPin;//받아온비밀번호를 pin에 저장
		checkingAccount = new BankAccount();//bankaccount타입의 checkingaccount 초기화.
		savingsAccount = new BankAccount();//bankaccount타입의 savingsaccount 초기화.
	}
	public boolean match(int aNumber, int aPin){//계좌번호와 비밀번호가 매칭되는지 확인하는 메소드
		return customerNumber == aNumber && pin == aPin;//customernumber와 pin이 모두 같을경우 true리턴
	}
	public BankAccount getCheckingAccount(){//checkingaccount를 리턴
		return checkingAccount;
	}
	public BankAccount getSavingsAccount(){//savingsaccount를 리턴
		return savingsAccount;
	}
	private int customerNumber;
	private int pin;
	private BankAccount checkingAccount;
	private BankAccount savingsAccount;
}