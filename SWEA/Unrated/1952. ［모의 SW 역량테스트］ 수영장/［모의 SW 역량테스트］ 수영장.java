import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
	
	static int[] tickets;
	static int[] plans;
	
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int testCase = 1; testCase <= T; testCase++) {
        	tickets = new int[4];
        	plans = new int[12];
        	st = new StringTokenizer(br.readLine());
        	for (int i = 0; i < 4; i++) {
        		tickets[i] = Integer.parseInt(st.nextToken());
        	}
        	st = new StringTokenizer(br.readLine());
        	for (int i = 0; i < 12; i++) {
        		plans[i] = Integer.parseInt(st.nextToken());
        	}
        	
        	int answer = calculate();
        	sb.append("#" + testCase + " " + answer + "\n");
        }
        
        System.out.println(sb);
    }
	
	private static int calculate() {
		int[] money = new int[12];
		int min = 0;
		
		// 1달 이용권 가격보다 1일 이용권으로만 이용하는게 비쌀 경우
		for (int i = 0; i < 12; i++) {
			if (plans[i] * tickets[0] > tickets[1]) {
				money[i] = tickets[1];
			} else {
				money[i] = plans[i] * tickets[0];
			}
			min += money[i];
		}
		
		// 3개월 이용권 개수의 경우의 수
		for (int count = 1; count <= 4; count++) {
			if (count == 1) {
				for (int i = 0; i < 10; i++) {
					int sum = 0;
					
					if (money[i] + money[i + 1] + money[i + 2] > tickets[2]) {
						sum += tickets[2];
						
						for (int j = 0; j < 12; j++) {
							if (j < i || j > i + 2) {
								sum += money[j];
							}
						}
						
						min = Math.min(min, sum);
					}
				}
			} else if (count == 2) {
				for (int i = 0; i < 7; i++) {
					for (int j = i + 3; j < 10; j++) {
						int sum = 0;
						
						if (money[i] + money[i + 1] + money[i + 2] > tickets[2]
								&& money[j] + money[j + 1] + money[j + 2] > tickets[2]) {
							sum += tickets[2] * 2;
							
							for (int k = 0; k < 12; k++) {
								if ((k < i || k > i + 2) && (k < j || k > j + 2)) {
									sum += money[k];
								}
							}
							
							min = Math.min(min, sum);
						}
					}
				}
			} else if (count == 3) {
				for (int i = 0; i < 4; i++) {
					for (int j = i + 3; j < 7; j++) {
						for (int k = j + 3; k < 10; k++) {
							int sum = 0;
							
							if (money[i] + money[i + 1] + money[i + 2] > tickets[2]
									&& money[j] + money[j + 1] + money[j + 2] > tickets[2]
									&& money[k] + money[k + 1] + money[k + 2] > tickets[2]) {
								sum += tickets[2] * 3;
								
								for (int l = 0; l < 12; l++) {
									if ((l < i || l > i + 2) && (l < j || l > j + 2) && (l < k || l > k + 2)) {
										sum += money[l];
									}
								}
								
								min = Math.min(min, sum);
							}
						}
					}
				}
			} else {
				min = Math.min(min, tickets[2] * 4);
			}
		}
		
		// 1년 이용권의 경우
		min = Math.min(min, tickets[3]);
		
		return min;
	}
}