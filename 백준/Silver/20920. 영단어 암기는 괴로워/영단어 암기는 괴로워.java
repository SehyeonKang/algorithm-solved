import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	static class EnglishWord implements Comparable<EnglishWord>{
		String word;
		int count;
		
		EnglishWord(String word, int count) {
			this.word = word;
			this.count = count;
		}

		@Override
		public int compareTo(EnglishWord o) {
			if (this.count == o.count && this.word.length() == o.word.length()) {
				return this.word.compareTo(o.word);
			}
			
			if (this.count == o.count) {
				return o.word.length() - this.word.length();
			}
			
			return o.count - this.count;
		}
		
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		List<EnglishWord> words = new ArrayList<>();
		Map<String, Integer> map = new HashMap<>();
		
		for (int i = 0; i < N; i++) {
			String word = br.readLine();
			if (word.length() < M) {
				continue;
			}
			
			map.put(word, map.getOrDefault(word, 0) + 1);
		}
		
		for (String word : map.keySet()) {
			words.add(new EnglishWord(word, map.get(word)));
		}
		
		Collections.sort(words);
		
		for (EnglishWord englishWord : words) {
			sb.append(englishWord.word + "\n");
		}
		System.out.println(sb.toString());
	}
}