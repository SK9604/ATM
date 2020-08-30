import java.io.IOException;
import java.util.Scanner;

public class ATMTester { // class ATMTester

	public static void main(String[] args) { // 메인메소드
		ATM theATM; // ATM타입의 theATM객체 생성
		try { // try-catch문이용 파일이 정상적으로 열리지 않을 시에 에러메시지 출력
			Bank theBank = new Bank(); // Bank타입의 theBank객체 생성
			theBank.readCustomers("F:\\Kim\\대학\\이클립스\\workspace\\ATM\\src\\customers");// Bank클래스의 readCustomers에 파일이름 파라메타패싱
			theATM = new ATM(theBank); // theATM에 theBank를 넣어 초기화
		} catch (IOException e) { // 파일을 불러오는데 오류가 있을시 오류메시지 출력
			System.out.println("Error opening accounts file.");
			return;
		}

		Scanner in = new Scanner(System.in); // 사용자로 부터 입력을 받기 위해 스캐너 사용

		while (true) { // 무한루프문
			int state = theATM.getState();//int타입의 state에 atm의 state를 받아옴
			if (state == ATM.START) {//state값이 1일경우(첫번째 단계)
				System.out.print("Enter account number: ");//사용자의 계좌번호를 입력하라는 메시지 출력
				int number = in.nextInt();//사용자로부터 계좌번호를 받음
				theATM.setCustomerNumber(number);//커스토머넘버를 ATM에 저장하고 상태를 1늘려 pin즉 두번째단계로 넘어간다.
			} else if (state == ATM.PIN) {//state값이 2일경우(두번째 단계)
				System.out.print("Enter PIN: ");//pin 즉 비밀번호를 입력하라는 메시지 출력
				int pin = in.nextInt();//사용자로부터 비밀번호를 받음
				theATM.selectCustomer(pin);//사용자가 입력한 비밀번호를 atm의 selectcustomer에 파라메타패싱하여 고객정보의 값과 같은지 대조후 맞으면 state값을 증가시키고 아니면 처음으로 돌아간다.
			} else if (state == ATM.ACCOUNT) {//state값이 3일경우(세번째단계)
				System.out.print("A=Checking, B=Savings, C=Quit: ");//출력
				String command = in.next();//command에 글자를 입력받음
				if (command.equalsIgnoreCase("A"))//command가 A일경우(대소문자 구분x)<-equalsIgnoreCase
					theATM.selectionAccount(ATM.CHECKING);//checking선택함
				else if (command.equalsIgnoreCase("B"))//cammand가 B일경우(대소문자 구분x)
					theATM.selectionAccount(ATM.SAVINGS);//saving선택함
				else if (command.equalsIgnoreCase("C"))//command가 C일경우(대소문자 구분x)
					theATM.reset();//처음으로 리셋(state값을 1로 한다)
				else//a,b,c중 입력하지 않고 다른값을 입력했을경우
					System.out.println("Illegal input!");//잘못입력됨을 출력
			} else if (state == ATM.TRANSACT) {//state값이 4일경우(네번째단계)
				System.out.println("Balance=" + theATM.getBalance());//atm으로부터 잔액을 불러와 출력
				System.out.print("A=Deposit, B=Withdrawal, C=Cancel: ");//선택지 출력
				String command = in.next();//사용자로부터 스트링타입으로 입력받음
				if (command.equalsIgnoreCase("A")) {//a를 입력할경우
					System.out.print("Amount: ");//출력
					double amount = in.nextDouble();//사용자가 입금할 금액 입력
					theATM.deposit(amount);//atm에 입금
					theATM.back();//state상태를 한단계 아래로 내려감(즉 세번째단계로 back)
				} else if (command.equalsIgnoreCase("B")) {//b를 입력할경우
					System.out.print("Amount: ");//출력
					double amount = in.nextDouble();//출금할 금액을 입력받음
					theATM.withdraw(amount);//atm에 출금
					theATM.back();//state값를 한단계아래로
				} else if (command.equalsIgnoreCase("C"))//c를 입력할경우(cancel)
					theATM.back();//state값을 한단계아래로
				else//abc이외으 값을 입력할경우
					System.out.println("Illegal input!");//잘못입력함을 출력

			}
		}
	}
}