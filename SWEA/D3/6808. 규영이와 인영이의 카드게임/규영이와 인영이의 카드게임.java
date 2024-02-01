import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
	
	static boolean[] isSelected;
	static boolean[] inputCheck;
	static int[] inputs;
	static int[] numbers;
	static int winCnt;
	static int loseCnt;
	
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int testCase = 1; testCase <= T; testCase++) {
           st = new StringTokenizer(br.readLine());
           isSelected = new boolean[19];
           inputCheck = new boolean[19];
           inputs = new int[9];
           numbers = new int[9];
           winCnt = 0;
           loseCnt = 0;
           
           for (int i = 0; i < 9; i++) {
        	   inputs[i] = Integer.parseInt(st.nextToken());
        	   inputCheck[inputs[i]] = true;
           }
           
           recur(0);
           
           sb.append("#" + testCase + " " + winCnt + " " + loseCnt + "\n");
        }
        
        System.out.println(sb);
    }
	
	private static void recur(int cnt) {
		if (cnt == 9) {
			int sum1 = 0;
			int sum2 = 0;
			for (int i = 0; i < 9; i++) {
				int num1 = inputs[i];
				int num2 = numbers[i];
				
				if (num1 > num2) {
					sum1 += num1 + num2;
				} else if (num2 > num1) {
					sum2 += num1 + num2;
				}
			}
			
			if (sum1 > sum2) {
				winCnt++;
			} else if (sum1 < sum2) {
				loseCnt++;
			}
			
			return;
		}
		
		for (int i = 1; i < 19; i++) {
			if (isSelected[i] || inputCheck[i]) {
				continue;
			}
			
			isSelected[i] = true;
			numbers[cnt] = i;
			recur(cnt + 1);
			isSelected[i] = false;
		}
	}
}
