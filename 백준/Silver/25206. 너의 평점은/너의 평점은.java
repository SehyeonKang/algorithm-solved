import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		Map<String, Double> map = new HashMap<>();
		map.put("A+", 4.5);
		map.put("A0", 4.0);
		map.put("B+", 3.5);
		map.put("B0", 3.0);
		map.put("C+", 2.5);
		map.put("C0", 2.0);
		map.put("D+", 1.5);
		map.put("D0", 1.0);
		map.put("F", 0.0);
		double answer = 0.0;
		double sum = 0.0;
		double scoreSum = 0;
		
		for (int i = 0; i < 20; i++) {
			st = new StringTokenizer(br.readLine());
			String name = st.nextToken();
			double score = Double.parseDouble(st.nextToken());
			String rank = st.nextToken();
			
			if (!rank.equals("P")) {
				scoreSum += score;
				sum += score * map.get(rank);		
			}
		}
		
		answer = sum / scoreSum;
		sb.append(answer);
		
		System.out.println(sb);
	}
}