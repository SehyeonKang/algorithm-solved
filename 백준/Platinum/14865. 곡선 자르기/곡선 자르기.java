import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static class Element implements Comparable<Element> {
		int start, end;

		public Element(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Element o) {
			return this.start - o.start;
		}
	}
	
	static class Info implements Comparable<Info> {
		int x;
		char c;
		
		public Info(int x, char c) {
			this.x = x;
			this.c = c;
		}

		@Override
		public int compareTo(Info o) {
			return this.x - o.x;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Info> pq = new PriorityQueue<>();
		ArrayList<Point> elements = new ArrayList<>();
		int startX = Integer.MAX_VALUE;
		int startY = Integer.MAX_VALUE;
		int startIdx = -1;
		
		// 제일 왼쪽 하단의 시작 정점 찾으면서 정점 리스트 생성
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			if (y < 0 && startX > x) {
				startX = x;
				startY = y;
				startIdx = i;
			}
			
			elements.add(new Point(x, y));
		}
		
		ArrayList<Element> mountains = new ArrayList<>();
		
		int x = startX;
		int y = startY;
		for (int i = 0; i < N; i++) {
			int idx = (startIdx + i) % N;
			Point p = elements.get(idx);
			int nx = p.x;
			int ny = p.y;
			
			// 봉우리가 올라가는 경우
			if (nx == x && y < 0 && ny > 0) {
				mountains.add(new Element(x, 0));
			}
			// 봉우리가 내려가는 경우
			if (nx == x && y > 0 && ny < 0) {
				mountains.get(mountains.size() - 1).end = Math.max(x, mountains.get(mountains.size() - 1).start);
				mountains.get(mountains.size() - 1).start = Math.min(x, mountains.get(mountains.size() - 1).start);
				pq.offer(new Info(mountains.get(mountains.size() - 1).start, '('));
				pq.offer(new Info(mountains.get(mountains.size() - 1).end, ')'));
			}
			
			x = nx;
			y = ny;
		}
		
		int noIncludedCnt = 0;
		int noIncludingCnt = 0;
		ArrayDeque<Character> stack = new ArrayDeque<>();
		char preChar = ' ';
		while (!pq.isEmpty()) {
			Info i = pq.poll();
			
			if (i.c == ')') {
				if (preChar == '(') {
					noIncludingCnt++;
				}
				stack.pop();
				
				if (stack.isEmpty()) {
					noIncludedCnt++;
				}
			} else {
				stack.push(i.c);
			}
			preChar = i.c;
		}
		
		System.out.println(noIncludedCnt + " " + noIncludingCnt);
	}
	
}