import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int hours = sc.nextInt();
		int minutes = sc.nextInt();
		int cookingMinutes = sc.nextInt();
		int resultHours = -1;
		int resultMinutes = -1;
		
		if(minutes + cookingMinutes >= 60) {
			if(hours + (minutes + cookingMinutes)/60 > 23) {
				resultHours = hours + (minutes + cookingMinutes)/60 - 24;
				resultMinutes = minutes + cookingMinutes - (minutes + cookingMinutes)/60*60;
			} else {
				resultHours = hours + (minutes + cookingMinutes)/60;
				resultMinutes = minutes + cookingMinutes - (minutes + cookingMinutes)/60*60;
			}
		} else {
			resultHours = hours;
			resultMinutes = minutes + cookingMinutes;
		}
		
		System.out.println(resultHours + " " + resultMinutes);
	}
}