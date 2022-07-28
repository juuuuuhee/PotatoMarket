package util;

import java.util.Random;

public class Code {

Random ran = new Random();


	
	public int rCode() {
		int code = ran.nextInt(8999)+1000; 
		
		return code;

	}
}
