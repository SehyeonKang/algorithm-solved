import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static class Rectangle {
		double x;
		double y;
		double p;
		double q;
		double centerX;
		double centerY;
		double width;
		double height;
		
		public Rectangle(int x, int y, int p, int q) {
			this.x = x;
			this.y = y;
			this.p = p;
			this.q = q;
			calculation();
		}
		
		private void calculation() {
			centerX = (x + p) / 2;
			centerY = (y + q) / 2;
			width = p - x;
			height = q - y;
		}
	}
	
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for (int testCase = 0; testCase < 4; testCase++) {
			char answer = ' ';
			st = new StringTokenizer(br.readLine());
			int[][] pos = new int[2][4];
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < 4; j++) {
					pos[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			Rectangle recA = new Rectangle(pos[0][0], pos[0][1], pos[0][2], pos[0][3]);
			Rectangle recB = new Rectangle(pos[1][0], pos[1][1], pos[1][2], pos[1][3]);
			
			if (Math.abs(recA.centerX - recB.centerX) < recA.width / 2 + recB.width / 2
					&& Math.abs(recA.centerY - recB.centerY) < recA.height / 2 + recB.height / 2) {
				answer = 'a';
			} else if ((Math.abs(recA.centerX - recB.centerX) == recA.width / 2 + recB.width / 2
					&& Math.abs(recA.centerY - recB.centerY) < recA.height / 2 + recB.height / 2)
					|| (Math.abs(recA.centerX - recB.centerX) < recA.width / 2 + recB.width / 2
					&& Math.abs(recA.centerY - recB.centerY) == recA.height / 2 + recB.height / 2)) {
				answer = 'b';
			} else if (Math.abs(recA.centerX - recB.centerX) == recA.width / 2 + recB.width / 2
					&& Math.abs(recA.centerY - recB.centerY) == recA.height / 2 + recB.height / 2) {
				answer = 'c';
			} else {
				answer = 'd';
			}
		
			System.out.println(answer);
		}
	}
}