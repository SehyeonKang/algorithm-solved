import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static class Element implements Comparable<Element> {
		int x;
		char c;
		
		public Element(int x, char c) {
			this.x = x;
			this.c = c;
		}

		@Override
		public int compareTo(Element o) {
			return this.x - o.x;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Element> pq = new PriorityQueue<>();
		
		st = new StringTokenizer(br.readLine());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		int startX = x;
		int startY = y;
		int preX = Integer.MAX_VALUE;
		int firstDownX = Integer.MAX_VALUE;
		
		for (int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int nx = Integer.parseInt(st.nextToken());
			int ny = Integer.parseInt(st.nextToken());
			
			// y축 부호가 바뀔 경우
			if ((ny > 0 && y < 0) || (ny < 0 && y > 0)) {
				// 봉우리가 올라갈 경우
				if (y < 0) {
					preX = nx;
				// 봉우리가 내려갈 경우
				} else {
					// 처음에 내려갈 때, 마지막에 올라오는 것과 연결해야함
					if (preX == Integer.MAX_VALUE) {
						firstDownX = nx;
					} else {
						if (preX < nx) {
							pq.offer(new Element(preX, '('));
							pq.offer(new Element(nx, ')'));
						} else {
							pq.offer(new Element(nx, '('));
							pq.offer(new Element(preX, ')'));
						}
					}
				}
			}
			
			x = nx;
			y = ny;
		}
		
		// 첫 정점과 마지막 정점으로 봉우리가 만들어질 경우
		if (startX == x) {
			// 처음에 y좌표가 양수여서 봉아리가 내려왔을 경우	
			if (firstDownX != Integer.MAX_VALUE) {
				if (x < firstDownX) {
					pq.offer(new Element(x, '('));
					pq.offer(new Element(firstDownX, ')'));
				} else {
					pq.offer(new Element(firstDownX, '('));
					pq.offer(new Element(x, ')'));
				}
			} else {
				if (preX < x) {
					pq.offer(new Element(preX, '('));
					pq.offer(new Element(x, ')'));
				} else {
					pq.offer(new Element(x, '('));
					pq.offer(new Element(preX, ')'));
				}
			}
			
		// 처음에 y좌표가 양수여서 봉아리가 내려왔을 경우	
		} else if (firstDownX != Integer.MAX_VALUE) {
			if (preX < firstDownX) {
				pq.offer(new Element(preX, '('));
				pq.offer(new Element(firstDownX, ')'));
			} else {
				pq.offer(new Element(firstDownX, '('));
				pq.offer(new Element(preX, ')'));
			}
		}
		
		int noIncludedCnt = 0;
		int noIncludingCnt = 0;
		ArrayDeque<Character> stack = new ArrayDeque<>();
		char preChar = ' ';
		while (!pq.isEmpty()) {
			Element e = pq.poll();
			
			if (e.c == ')') {
				if (preChar == '(') {
					noIncludingCnt++;
				}
				stack.pop();
				
				if (stack.isEmpty()) {
					noIncludedCnt++;
				}
			} else {
				stack.push(e.c);
			}
			preChar = e.c;
		}
		
		System.out.println(noIncludedCnt + " " + noIncludingCnt);
	}
	
}