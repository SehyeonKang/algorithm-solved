import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		double a = input.nextLong();
		double b = input.nextLong();
		
		System.out.println(String.format("%.10f", a/b));
		
	}
}