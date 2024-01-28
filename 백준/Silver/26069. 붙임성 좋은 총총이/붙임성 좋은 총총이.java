import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		Map<String, Boolean> map = new HashMap<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String p1 = st.nextToken();
			String p2 = st.nextToken();
			
			if (p1.equals("ChongChong") || p2.equals("ChongChong")) {
				map.put(p1, true);
				map.put(p2, true);
				continue;
			}
			
			if ((map.containsKey(p1) && map.get(p1)) || (map.containsKey(p2) && map.get(p2))) {
				map.put(p1, true);
				map.put(p2, true);
				continue;
			}
			
			map.put(p1, false);
			map.put(p2, false);
		}
		
		int answer = 0;
		for (String name : map.keySet()) {
			if (map.get(name)) {
				answer++;
			}
		}
		System.out.println(answer);
	}
}