import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ATMViewer {

	public static void main(String[] args) {
		ATM theATM;//atm타입의 theatm선언.
		try{//try-catch문을 이용하여 파일을 불러오는데 실패했을경우 에러메시지 출력
			Bank theBank = new Bank();//bank타입의 thebank객체를 만듦
			theBank.readCustomers("F:\\Kim\\대학\\이클립스\\workspace\\ATM\\src\\customers");//고객파일을 불러옴
			theATM = new ATM(theBank);//theatm에 thebank를 넣어 초기화.
			
		}
		catch(IOException e){//파일을 불러오는데 실패했을경우 에러메시지 출력
			JOptionPane.showMessageDialog(null, "Error opening accounts file");//파일을 불러오지 못할경우 에러메시지가 포함된창을 띄운다.
			return;
		}
		JFrame frame = new ATMFrame(theATM);//atmframe에 theatm으로 frame 객체를 만듦
		frame.setTitle("First National Bank of java");//창의 이름
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);		//frame이 보이게 함
	}
}