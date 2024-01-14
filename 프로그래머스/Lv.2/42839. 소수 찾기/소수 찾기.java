class Solution {
    static int answer = 0;
	static boolean[] arr = new boolean[10_000_000];
    
    public int solution(String numbers) {
        calculation();
        boolean[] check = new boolean[numbers.length()];
        for (int i = 0; i < numbers.length(); i++) {
        	check[i] = true;
        	recur(numbers, i, true, "", check);
        	recur(numbers, i, false, "", check);
        	check[i] = false;
        }
        
        return answer;
    }
    
    static void calculation() {
		arr[1] = true;
		for (int i = 2; i < arr.length; i++) {
        	if (!arr[i]) {
        		for (int j = 2; i * j < arr.length; j++) {
        			arr[i * j] = true;
        		}
        	}
        }
	}
	
	static void recur(String numbers, int index, boolean flag, String number, boolean[] check) {
		if ((numbers.charAt(index) == '0' && number.length() == 0) || numbers.length() == index) {
			return;
		}
		
		if (flag) {
			number += numbers.charAt(index);
			if (!arr[Integer.parseInt(number)]) {
				arr[Integer.parseInt(number)] = true;
				answer++;
			}
		}
		
		for (int i = 0; i < numbers.length(); i++) {
			if (!check[i]) {
				check[i] = true;
				recur(numbers, i, true, number, check);
				recur(numbers, i, false, number, check);
				check[i] = false;
			}
        }
	}
}