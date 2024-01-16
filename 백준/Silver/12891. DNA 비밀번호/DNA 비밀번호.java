import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int S = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		String str = br.readLine();
		char[] arr = str.toCharArray();
		st = new StringTokenizer(br.readLine());
		Map<Character, Integer> map = new HashMap<>();
		map.put('A', Integer.parseInt(st.nextToken()));
		map.put('C', Integer.parseInt(st.nextToken()));
		map.put('G', Integer.parseInt(st.nextToken()));
		map.put('T', Integer.parseInt(st.nextToken()));
		
		int lt = 0;
		int rt = P - 1;
		for (int i = lt; i <= rt; i++) {
			if (map.containsKey(arr[i])) {
				map.put(arr[i], map.get(arr[i]) - 1);
			}
		}
		
		int answer = 0;
		while (true) {
			if (isUsable(map)) {
				answer++;
			}
			
			if (map.containsKey(arr[lt])) {
				map.put(arr[lt], map.get(arr[lt]) + 1);
			}
			
			if (rt + 1 == S) {
				break;
			} else {
				if (map.containsKey(arr[rt + 1])) {
					map.put(arr[rt + 1], map.get(arr[rt + 1]) - 1);
				}
			}
			
			lt++;
			rt++;
		}
		
		System.out.println(answer);
	}
	
	static boolean isUsable(Map<Character, Integer> map) {
		for (char c : map.keySet()) {
			if (map.get(c) > 0) {
				return false;
			}
		}
		return true;
	}
}