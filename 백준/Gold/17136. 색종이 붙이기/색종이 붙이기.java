import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int minAnswer = 30;
	static int[][] map;
	static int[] counts;
	static boolean answerFlag = false;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		map = new int[10][10];
		counts = new int[6];
		for (int i = 0; i < 10; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 10; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) {
				}
			}
		}
		
		recur(0, 0);
		
		if (minAnswer == 30) {
			System.out.println(-1);
		} else {
			System.out.println(minAnswer);
		}
	}
	
	private static void recur(int index, int count) {
		if (index == 100) {
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					if (map[i][j] == 1) {
						return;
					}
				}
			}
			
			minAnswer = Math.min(minAnswer, count);
			return;
		}
		
		int r = index / 10;
		int c = index % 10;
		
		if (map[r][c] == 0) {
			recur(index + 1, count);
		} else {
			for (int size = 1; size <= 5; size++) {
				if (isPossible(r, c, size) && counts[size] < 5) {
					fill(r, c, size, 0);
					counts[size]++;
					recur(index + 1, count + 1);
					fill(r, c, size, 1);
					counts[size]--;
				}
			}
		}
		
	}
	
	private static boolean isPossible(int r, int c, int size) {
		if (r + size > 10 || c + size > 10) {
			return false;
		}
		
		for (int i = r; i < r + size; i++) {
			for (int j = c; j < c + size; j++) {
				if (map[i][j] == 0) {
					return false;
				}
			}
		}
		return true;
	}
	
	private static void fill(int r, int c, int size, int num) {
		for (int i = r; i < r + size; i++) {
			for (int j = c; j < c + size; j++) {
				map[i][j] = num;
			}
		}
	}
}
