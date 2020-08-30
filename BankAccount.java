import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class KeyPad extends JPanel{
	public KeyPad(){//생성자
		setLayout(new BorderLayout());//keypad패널을 borderlayout으로설정 
		
		display = new JTextField();//텍스트필드
		add(display, "North");//텍스트필드를 north정렬함
		
		buttonPanel = new JPanel();//jpanel타입의 buttonpanel
		buttonPanel.setLayout(new GridLayout(4,3));//버튼패널을 4*3배열의 gridlayout으로 설정
		
		addButton("7");//버튼 추가
		addButton("8");
		addButton("9");
		addButton("4");
		addButton("5");
		addButton("6");
		addButton("1");
		addButton("2");
		addButton("3");
		addButton("0");
		addButton(".");
		
		clearButton = new JButton("CE");//초기화버튼 ce
		buttonPanel.add(clearButton);//초기화버튼 ce 버튼패널에 추가
		
		class ClearButtonListener implements ActionListener{//초기화버튼 이벤트
			public void actionPerformed(ActionEvent event){
				display.setText("");//텍스트필드를 비움
			}
		}
		ActionListener listener = new ClearButtonListener();
		
		clearButton.addActionListener(new ClearButtonListener());//초기화버튼에 이벤트 추가
		
		add(buttonPanel, "Center");//버튼을 가운데정렬함
	}
	
	private void addButton(final String label){//버튼을 추가하는 메소드
		class DigitButtonListener implements ActionListener{//버튼이벤트
			public void actionPerformed(ActionEvent event){
				if(label.equals(".")&&display.getText().indexOf(".")!=-1)//받아온 값이 .이고  .과 일치하는 문자를 찾았을경우
					return;//아무일도 일어나지 않는다.
				display.setText(display.getText() + label);//텍스트상자에 원래 텍스트상자에있던값+label을 추가한다.(예: 1버튼을 눌렀을시 텍스트상자에 1추가)
			}
		}
		JButton button = new JButton(label);//라벨버튼 추가(0~9와.까지)
		buttonPanel.add(button);//버튼패널에 라벨버튼 추가
		ActionListener listener = new DigitButtonListener();
		button.addActionListener(listener);//버튼 이벤트 추가
	}
	public double getValue(){
		return Double.parseDouble(display.getText());//텍스트필드의 값을 더블로변환하여 리턴
	}
	public void clear(){//텍스트필드 초기화
		display.setText("");//텍스트필드의 값이 없게 초기화한다.
	}
	private JPanel buttonPanel;
	private JButton clearButton;
	private JTextField display;
}





public class BankAccount {

	private double balance;

	public BankAccount() {
		balance = 0;
	}

	public BankAccount(double initialBalance) {
		balance = initialBalance;
	}

	public void deposit(double amount) {
		balance += amount;
	}

	public void withdraw(double amount) {
		if (balance < amount) {
			System.out.println("금액이 부족합니다.");
		} else
			balance -= amount;
	}

	public double getBalance() {
		return balance;
	}

}