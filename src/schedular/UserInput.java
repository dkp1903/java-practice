package schedular;
import java.util.Scanner;
public class UserInput {
	public static int getInt()
	{
		while(true){
			Scanner sc = new Scanner(System.in);
			try{
				int no = sc.nextInt();
				return no;
			}
			catch(Exception e){
				System.out.println("Enter a valid no ...");
			}
		}
	}
	
	public static String getString()
	{
		Scanner sc = new Scanner(System.in);
		return sc.nextLine();
	}
}
