import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int[] dt = {-1, 1, 2};
	static int answer = 0;
	
	static class Subin {
		int pos;
		ArrayList<Integer> moved;
		
		public Subin(int pos) {
			this.pos = pos;
			this.moved = new ArrayList<>();
		}
		
		public void setMoved(ArrayList<Integer> moved) {
			this.moved = moved;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		if (N > K) {
			sb.append(N - K + "\n");
			for (int i = N; i >= K; i--) {
				sb.append(i + " ");
			}
		} else {
			Subin subin = bfs(N, K);
			
			sb.append(answer + "\n");
			for (int move : subin.moved) {
				sb.append(move + " ");
			}
		}
		
		System.out.println(sb);
	}
	
	static Subin bfs(int N, int K) {
		Queue<Subin> queue = new LinkedList<>();
		boolean[] check = new boolean[100_001];
		int time = 0;
		
		Subin init = new Subin(N);
		init.moved.add(N);
		queue.offer(init);
		check[N] = true;
		
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int it = 0; it < size; it++) {
				Subin subin = queue.poll();
				if (subin.pos == K) {
					answer = time;
					return subin;
				}
				
				for (int i = 0; i < 3; i++) {
					int nPos = 0;
					if (i < 2) {
						nPos = subin.pos + dt[i];
					} else {
						nPos = subin.pos * dt[i];
					}
					
					if (nPos >= 0 && nPos <= 100_000 && !check[nPos]) {
						Subin nSubin = new Subin(nPos);
						nSubin.setMoved((ArrayList<Integer>)subin.moved.clone());
						nSubin.moved.add(nPos);
						queue.offer(nSubin);
						check[nPos] = true;
					}
				}
			}
			time++;
		}
		
		return null;
	}
}