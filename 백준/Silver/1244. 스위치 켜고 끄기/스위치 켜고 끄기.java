import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int switchNum = Integer.parseInt(br.readLine());
		int[] switches = new int[switchNum + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= switchNum; i++) {
			switches[i] = Integer.parseInt(st.nextToken());
		}
		
		int studentNum = Integer.parseInt(br.readLine());
		for (int i = 0; i < studentNum; i++) {
			st = new StringTokenizer(br.readLine());
			int sex = Integer.parseInt(st.nextToken());
			int num = Integer.parseInt(st.nextToken());
			
			// 남학생: 1, 여학생: 2
			if (sex == 1) {
				for (int idx = num; idx <= switchNum; idx += num) {
					if (switches[idx] == 0) {
						switches[idx] = 1;
					} else {
						switches[idx] = 0;
					}
				}
			} else {
				int lt = -1;
				int rt = 1;
				
				if (switches[num] == 0) {
					switches[num] = 1;
				} else {
					switches[num] = 0;
				}
				
				while (true) {
					if (num + lt >= 1 && num + rt <= switchNum && switches[num + lt] == switches[num + rt]) {
						if (switches[num + lt] == 0) {
							switches[num + lt] = 1;
						} else {
							switches[num + lt] = 0;
						}
						
						if (switches[num + rt] == 0) {
							switches[num + rt] = 1;
						} else {
							switches[num + rt] = 0;
						}
						
						lt--;
						rt++;
					} else {
						break;
					}
				}
			}
		}
		
		for (int i = 1; i <= switchNum; i++) {
			sb.append(switches[i] + " ");
			if (i % 20 == 0) {
				sb.append("\n");
			}
		}
		
		System.out.println(sb.toString());
	}
}