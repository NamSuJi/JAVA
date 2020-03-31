package templateMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class mtokStrategy implements Application2{
	private InputStreamReader isr;
	private BufferedReader br;
	private boolean isDone = false;
	
	public static void main(String[] args) throws Exception{
		(new ApplicationRunner(new mtokStrategy())).run();	
	}
	public void init() {
		isr = new InputStreamReader(System.in);
		br = new BufferedReader(isr);
	}
	public void idle() {
		String mileString = readLineAndReturnNullIfError();
		if(mileString == null || mileString.length() == 0){
			isDone = true;
		}else {
			double mile = Double.parseDouble(mileString);
			double kilo = mile/1.6;
			System.out.println("kilo="+kilo+", mile="+mile);
		}
	}
	public void cleanup() {
		System.out.println("exit");
	}
	public boolean done() {
		return isDone;
	}
	private String readLineAndReturnNullIfError() {
		String s;
		try {
			s = br.readLine();
		}catch(IOException e) {
			s = null;
		}
		return s;
	}
}
