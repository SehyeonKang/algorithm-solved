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
		
		int answer = 0;
		int N = Integer.parseInt(br.readLine());
		Map<String, Boolean> map = new HashMap<>();
		for (int i = 0; i < N; i++) {
			String message = br.readLine();
			
			if (message.equals("ENTER")) {
				answer += map.size();
				map.clear();
			} else {
				if (!map.containsKey(message)) {
					map.put(message, true);
				}
			}
		}
		
		answer += map.size();
		System.out.println(answer);
	}
}