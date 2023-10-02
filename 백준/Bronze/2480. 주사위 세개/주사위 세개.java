import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		int c = sc.nextInt();
		int money = 0;
		
		if(a == b && b == c) {
			money = 10000 + a * 1000;
		} else if((a == b && b != c) || (a == c && a != b)) {
			money = 1000 + a * 100;
		} else if(a != b && b == c) {
			money = 1000 + b * 100;
		} else {
			if (a >= b && a >= c) {
				money = a * 100;
			}
			else if (b >= a && b >= c) {
				money = b * 100;
			} else
				money = c * 100;
		}
		
		System.out.print(money);
	}
}