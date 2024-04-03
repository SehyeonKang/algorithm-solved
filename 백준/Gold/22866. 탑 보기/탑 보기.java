import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
	
	static class Building {
		int no, height;

		public Building(int no, int height) {
			this.no = no;
			this.height = height;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[] buildings = new int[N + 1];
		// i번째 빌딩의 idx 0: 볼 수 있는 건물의 개수, idx 1: 볼 수 있는 가장 가까운 건물의 번호 중 작은 번호
		int[][] buildingView = new int[N + 1][2];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			buildings[i] = Integer.parseInt(st.nextToken());
		}
		
		ArrayDeque<Building> stack = new ArrayDeque<>();
		// 좌측부터 빌딩 탐색
		for (int i = 1; i <= N; i++) {
			int height = buildings[i];
			while (!stack.isEmpty()) {
				int leftHeight = stack.peek().height;
				
				if (leftHeight > height) {
					break;
				}
				stack.pop();
			}
			buildingView[i][0] = stack.size();
			if (!stack.isEmpty()) {
				buildingView[i][1] = stack.peek().no;
			}
			stack.push(new Building(i, height));
		}
		
		// 우측부터 빌딩 탐색
		stack.clear();
		for (int i = N; i > 0; i--) {
			int height = buildings[i];
			while (!stack.isEmpty()) {
				int rightHeight = stack.peek().height;
				
				if (rightHeight > height) {
					break;
				}
				stack.pop();
			}
			buildingView[i][0] += stack.size();
			if (!stack.isEmpty()) {
				int leftDistance = i - buildingView[i][1];
				if (leftDistance > stack.peek().no - i || leftDistance == i) {
					buildingView[i][1] = stack.peek().no;
				}
			}
			stack.push(new Building(i, height));
		}
		
		for (int i = 1; i <= N; i++) {
			sb.append(buildingView[i][0]);
			if (buildingView[i][0] > 0) {
				sb.append(" " + buildingView[i][1]);
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
}
// 1h 0m