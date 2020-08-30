import java.io.IOException;

public class ATM {
	public ATM(Bank aBank){
		theBank = aBank;//파라메타패싱한 bank타입의 abank를 thebank에 저장
		reset();//리셋메소드를 불러옴
	}
	public void reset(){//리셋
		customerNumber = -1;//customernumber를 -1로 저장.
		currentAccount = null;//currentAccount를 null로 저장.
		state = START;//state값을 1로 저장.
	}
	public void setCustomerNumber(int number){//customernumber를 입력한다.
		assert state == START;//assert는 검증 즉 불리언값을 가진다.
		customerNumber = number;//customernumber에 파라메타패싱받은 값을 저장.
		state = PIN;//상태를 1증가
	}
	public void selectCustomer(int pin){//customer의 비밀번호를 받고 매칭
		assert state == PIN;//상태 검증
		currentCustomer = theBank.findCustomer(customerNumber, pin);//bank의 findcustomer에 손님번호와 파라메타패싱받은값을 보낸 결과를 currentcustomer에 저장
		if(currentCustomer == null)//받은값이 null일경우(즉 손님의 번호와 비밀번호가 매칭되지 않는다(비밀번호 틀림))
			state = START;//처음상태로 돌아감
		else//제대로 입력이 됐을경우
			state = ACCOUNT;//다음단계로 넘어감
	}
	public void selectionAccount(int account){//계좌선택하는 메소드
		assert state == ACCOUNT || state == TRANSACT;//검증
		if(account == CHECKING)//받아온 계좌가 checking일경우
			currentAccount = currentCustomer.getCheckingAccount();//bankaccount타입의 currentaccount에 checkingaccount를 저장.
		else//받아온 계좌가 saving일경우
			currentAccount = currentCustomer.getSavingsAccount();//bankaccount타입의 currentaccount에 savingsaccount를 저장.
		state = TRANSACT;//다음단계
	}
	public void withdraw(double value){//출금
		assert state == TRANSACT;//검증
		currentAccount.withdraw(value);//currentaccount에서 입력받은 값만큼 출금
	}
	public void deposit(double value){//입금
		assert state == TRANSACT;//검증
		currentAccount.deposit(value);//currentaccount에서 입력받은 값만큼 입금
	}
	public double getBalance(){//값을 받아옴
		assert state == TRANSACT;//검증
		return currentAccount.getBalance();//currentaccount의 잔액을 불러와 리턴
	}
	public void back(){//뒤단계로 넘어가는 메소드
		if(state == TRANSACT)//상태가 transact일때
			state = ACCOUNT;// 그전단계인 account로 돌아감.
		else if(state == ACCOUNT)//상태가 account일때
			state = PIN;//그 전 단계인 pin으로 돌아감.
		else if(state == PIN)//상태가 pin일때 
			state = START;//그 전단계인 start로 돌아감.
	}
	public int getState(){//상태를 리턴
		return state;
	}
	
	private int state;
	private int customerNumber;
	private Customer currentCustomer;
	private BankAccount currentAccount;
	private Bank theBank;
	
	public static final int START =1;
	public static final int PIN = 2;
	public static final int ACCOUNT = 3;
	public static final int TRANSACT = 4;
	
	public static final int CHECKING = 1;
	public static final int SAVINGS = 2;
}