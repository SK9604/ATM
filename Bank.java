public class Bank {
	
	public Bank(){
		customers = new ArrayList<Customer>();//customer타입의 배열리스트 초기화.
	}
	public void readCustomers(String filename)//고객파일을 받아옴
			throws IOException
	{
		Scanner in = new Scanner(new FileReader(filename));//스캐너를 통해 고객파일을 불러옴
		boolean done = false;//done의 초기값 false
		while(in.hasNext()){//파일의 더이상 불러올값이 없을때 while문을 벗어난다.
			int number = in.nextInt();//처음불러온 숫자값을 number에 지정한다.
			int pin = in.nextInt();//두번째 불러온 숫자값을 pin에 저장한다.
			Customer c = new Customer(number, pin);//커스토머타입의 c에 number와pin을 묶어 c를 만든다.
			addCustomer(c);//c를 customer에 추가한다.
		}
		in.close();//스캐너 사용 끝
	}
	public void addCustomer(Customer c){//customer c를 받아와 리스트 customers에 추가한다.
		customers.add(c);
	}
	public Customer findCustomer(int aNumber, int aPin){ //계좌번호와 비밀번호를 받아와 매치되는 손님이 있는지 확인하는 메소드
		for(Customer c : customers){// customer타입의 c에 리스트customers를 넣는다.(0~length-1까지 반복)
			if(c.match(aNumber, aPin))//받은 계좌번호와 비밀번호가 매치되는지 확인
				return c;//맞을경우 c를 리턴
		}
		return null;//아닐경우 null을 리턴
	}
	private ArrayList<Customer> customers;
}