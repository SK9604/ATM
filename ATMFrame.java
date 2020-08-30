import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ATMFrame extends JFrame {
	public ATMFrame(ATM anATM) {// atm타입의 anATM을 받아오는 생성자
		theATM = anATM;// theatm에 받아온 anatm저장
		pad = new KeyPad();// keypad타입의 객체 pad 초기화
		display = new JTextArea(4, 20);// 텍스트상자 display 초기화

		aButton = new JButton(" A ");// 버튼 a초기화
		aButton.addActionListener(new AButtonListener());// abutton의 이벤트를
															// abutton에 입력
		bButton = new JButton(" B ");
		bButton.addActionListener(new BButtonListener());
		cButton = new JButton(" C ");
		cButton.addActionListener(new CButtonListener());// a버튼과 같은과정

		JPanel buttonPanel = new JPanel();// 버튼이 들어갈 패널만듦
		buttonPanel.setLayout(new GridLayout(1, 3));// 1*3의 표타입gridlayout로 패널을 설정.
		buttonPanel.add(aButton);// 패널에 차례로 a,b,c버튼 삽입
		buttonPanel.add(bButton);
		buttonPanel.add(cButton);

		setLayout(new FlowLayout());// 전체적인 frame은 flowlayout으로 설정
		add(pad);// 패드(불러온 keypad) 삽입
		add(display);// 텍스트상자 삽입
		add(buttonPanel);// 버튼삽입
		showState();// showstate메소드 실행

		setSize(FRAME_WIDTH, FRAME_HEIGHT);// 사이즈 지정

	}

	public void showState() {
		int state = theATM.getState();//상태를 받아옴
		pad.clear();//텍스트상자를 비움
		if (state == ATM.START)//1단계일경우
			display.setText("Enter customer number\nA = OK");//상자에 출력
		else if (state == ATM.PIN)//2단계일경우
			display.setText("Enter PIN\nA = OK");//상자에 출력
		else if (state == ATM.ACCOUNT)//3단계일경우
			display.setText("Select Account\n" + "A = Checking\nB = Savings\nC =Exit");//상자에 출력
		else if (state == ATM.TRANSACT)//4단계일경우
			display.setText("Balance = " + theATM.getBalance() + "\nEnter amount and select transaction\n"
					+ " A = Withdraw\nB = Deposit\nC = Cancel");//상자에 출력
	}

	private class AButtonListener implements ActionListener {// a버튼 이벤트
		public void actionPerformed(ActionEvent event) {
			int state = theATM.getState();// 상태를 불러옴
			if (state == ATM.START)// 1단계일경우
				theATM.setCustomerNumber((int) pad.getValue());// 키패드로 입력한값을 int타입으로 변환해 atm의 stecustomernumber에 입력
			else if (state == ATM.PIN)// 2단계일경우
				theATM.selectCustomer((int) pad.getValue());// 키패드로 입력한 값을 int타입으로 변환해 atm의 selectcustomer에 입력
			else if (state == ATM.ACCOUNT)// 3단계일경우(계좌타입선택)
				theATM.selectionAccount(ATM.CHECKING);// a버튼인 checking을 선택했으므로 checking계좌를 불러옴
			else if (state == ATM.TRANSACT)// 4단계일경우(출금/입금)
			{
				theATM.withdraw(pad.getValue());// 키패드로 받아온 값을 출금함
				theATM.back();// 뒤단계로 돌아감
			}
			showState();// showstate메소드 실행
		}
	}

	private class BButtonListener implements ActionListener {//b버튼 이벤트
		public void actionPerformed(ActionEvent event) {
			int state = theATM.getState();//상태를 받아옴
			if (state == ATM.ACCOUNT)//3단계일경우(계좌타입입력)
				theATM.selectionAccount(ATM.SAVINGS);//savings계좌를 불러옴
			else if (state == ATM.TRANSACT) {//4단계일경우(입출금)
				theATM.deposit(pad.getValue());//키패드로 받아온값만큼 입금
				theATM.back();//뒤단계로 돌아감
			}
			showState();//showstate메소드 실행
		}
	}

	private class CButtonListener implements ActionListener {//c버튼 이벤트
		public void actionPerformed(ActionEvent event) {
			int state = theATM.getState();//상태를 받아옴
			if (state == ATM.ACCOUNT)//3단계일경우(계좌타입)
				theATM.reset();//처음으로 돌아감(cancel버튼상태이다.)
			else if (state == ATM.TRANSACT)//4단계일경우(입출금)
				theATM.back();//뒤단계로 돌아감
			showState();//showstate메소드 실행
		}
	}

	private JButton aButton;
	private JButton bButton;
	private JButton cButton;

	private KeyPad pad;
	private JTextArea display;

	private ATM theATM;

	private static final int FRAME_WIDTH = 300;
	private static final int FRAME_HEIGHT = 300;
}