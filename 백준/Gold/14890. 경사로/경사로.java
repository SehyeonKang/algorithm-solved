import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N, L, answer;
	static int[][] map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		rowCheck();
		colCheck();
		System.out.println(answer);
	}
	
	private static void rowCheck() {
		int count = 0;
		for (int row = 0; row < N; row++) {
			boolean downCheck = false;
			boolean flag = true;
			int preNum = map[row][0];
			int lengthCnt = 1;
			
			for (int col = 1; col < N; col++) {
				int num = map[row][col];
				
				if (num == preNum) {
					lengthCnt++;
				} else if (Math.abs(num - preNum) > 1) {
					flag = false;
					break;
				} else if (preNum + 1 == num) {
					if (downCheck) {
						if (lengthCnt >= L * 2) {
							preNum = num;
							lengthCnt = 1;
							downCheck = false;
						} else {
							flag = false;
							break;
						}
					} else {
						if (lengthCnt >= L) {
							preNum = num;
							lengthCnt = 1;
						} else {
							flag = false;
							break;
						}
					}
				} else if (preNum - 1 == num) {
					if (downCheck) {
						if (lengthCnt >= L) {
							preNum = num;
							lengthCnt = 1;
						} else {
							flag = false;
							break;
						}
					} else {
						downCheck = true;
						preNum = num;
						lengthCnt = 1;
					}
				}
			}
			
			if (downCheck && lengthCnt < L) {
				flag = false;
			}
			
			if (flag) {
				count++;
			}
		}
		
		answer += count;
	}
	
	private static void colCheck() {
		int count = 0;
		for (int col = 0; col < N; col++) {
			boolean downCheck = false;
			boolean flag = true;
			int preNum = map[0][col];
			int lengthCnt = 1;
			
			for (int row = 1; row < N; row++) {
				int num = map[row][col];
				
				if (num == preNum) {
					lengthCnt++;
				} else if (Math.abs(num - preNum) > 1) {
					flag = false;
					break;
				} else if (preNum + 1 == num) {
					if (downCheck) {
						if (lengthCnt >= L * 2) {
							preNum = num;
							lengthCnt = 1;
							downCheck = false;
						} else {
							flag = false;
							break;
						}
					} else {
						if (lengthCnt >= L) {
							preNum = num;
							lengthCnt = 1;
						} else {
							flag = false;
							break;
						}
					}
				} else if (preNum - 1 == num) {
					if (downCheck) {
						if (lengthCnt >= L) {
							preNum = num;
							lengthCnt = 1;
						} else {
							flag = false;
							break;
						}
					} else {
						downCheck = true;
						preNum = num;
						lengthCnt = 1;
					}
				}
			}
			
			if (downCheck && lengthCnt < L) {
				flag = false;
			}
			
			if (flag) {
				count++;
			}
		}
		
		answer += count;
	}
}