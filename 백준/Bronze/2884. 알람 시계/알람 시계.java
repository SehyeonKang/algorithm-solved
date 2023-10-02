import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int hours = sc.nextInt();
		int minutes = sc.nextInt();
		int resultHours = -1;
		int resultMinutes = -1;
		
		if(minutes < 45) {
			if(hours == 0) {
				resultHours = 23;
				resultMinutes = 60 - (45 - minutes);
			}
			else {
				resultHours = hours - 1;
				resultMinutes = 60 - (45 - minutes);
			}
		} else {
			resultHours = hours;
			resultMinutes = minutes - 45;
		}
		
		System.out.println(resultHours + " " + resultMinutes);
	}
}