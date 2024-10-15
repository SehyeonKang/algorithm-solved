import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

	static int N, M;
	static int[] arr, selected;
	static boolean[] visited;
	static Set<String> set = new HashSet<>();
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];
		selected = new int[M];
		visited = new boolean[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);

		recur(0, 0);
		System.out.println(sb);
	}

	static void recur(int cnt, int idx) {
		if (cnt == M) {
			StringBuilder tmpSb = new StringBuilder();
			for (int s : selected) {
				tmpSb.append(arr[s]).append(" ");
			}

			if (!set.contains(tmpSb.toString())) {
				set.add(tmpSb.toString());
				sb.append(tmpSb).append("\n");
			}

			return;
		}

		for (int i = idx; i < N; i++) {
			if (!visited[i]) {
				visited[i] = true;
				selected[cnt] = i;
				recur(cnt + 1, i + 1);
				visited[i] = false;
			}
		}
	}

}
