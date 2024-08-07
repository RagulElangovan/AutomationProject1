package ragulProject1;

import java.time.LocalDateTime;

public class Demo123 {
	
	
	public static void main(String[] args) {
		
		String string = LocalDateTime.now().toString();
		System.out.println(System.getProperty("user.dir") + "\\report\\" + string + ".png");
	}
	

}
