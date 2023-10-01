import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int a = input.nextInt();
		int b = input.nextInt();
		
		int b1 = b%10;
		int b2 = b/10%10;
		int b3 = b/100;
		int result = a*b;
		
		System.out.println(a*b1);
		System.out.println(a*b2);
		System.out.println(a*b3);
		System.out.println(result);
	}
}
