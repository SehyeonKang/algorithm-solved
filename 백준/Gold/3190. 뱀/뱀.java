import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int N, K, L, timeCount;
	// 맵 정보를 저장하는 2차원 배열. 빈 공간: 0, 사과: 1, 뱀: 2
	static int[][] map;
	// 델타 배열은 동-남-서-북 순으로
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
	// 뱀의 몸 위치들을 담을 큐
	static ArrayDeque<Snake> snake = new ArrayDeque<>();
	// 방향 변환 정보를 담을 큐
	static Queue<Direction> directions = new ArrayDeque<>();
	
	// 뱀 위치를 저장하는 클래스
	static class Snake {
		int r, c;
		
		Snake(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	// 방향 전환 정보를 저장하는 클래스
	static class Direction {
		int time;
		char vector;
		
		Direction(int time, char vector) {
			this.time = time;
			this.vector = vector;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		// 초기 세팅
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		map = new int[N][N];
		map[0][0] = 2;
		snake.offer(new Snake(0, 0));
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			map[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] = 1;
		}
		L = Integer.parseInt(br.readLine());
		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			directions.offer(new Direction(Integer.parseInt(st.nextToken()), st.nextToken().charAt(0)));
		}
		
		// Dummy 게임 시작
		playDummy();
		
		System.out.println(timeCount);
	}
	
	static void playDummy() {
		// 현재 뱀의 머리가 향하는 방향. 0: 동, 1: 남, 2: 서, 3: 북
		int currentDirection = 0;
		
		while(true) {
			// 뱀의 머리 확인
			Snake curSnake = snake.peekLast();
			int r = curSnake.r;
			int c = curSnake.c;
			int nr = r + dr[currentDirection];
			int nc = c + dc[currentDirection];
			
			// 머리가 이동한 위치가 맵 밖이거나 뱀 몸통인 경우
			if (!isIn(nr, nc) || map[nr][nc] == 2) {
				break;
			}
			
			// 머리가 이동한 위치에 사과가 없는 경우
			if (map[nr][nc] == 0) {
				Snake tail = snake.poll();
				map[tail.r][tail.c] = 0;
			}
			
			// 뱀 머리 이동
			map[nr][nc] = 2;
			snake.offer(new Snake(nr, nc));
			
			timeCount++;
			
			// 방향 전환 정보 확인 후, 시간이 맞다면 뱀 머리 방향 전환
			if (!directions.isEmpty()) {
				Direction direction = directions.peek();
				if (direction.time == timeCount) {
					directions.poll();
					if (direction.vector == 'L') {
						currentDirection = (currentDirection + 3) % 4;
					}
					
					if (direction.vector == 'D') {
						currentDirection = (currentDirection + 1) % 4;
					}
				}
			}
		}
		
		// 시간이 종료되기 전에 while문을 빠져나오므로 timeCount를 1 증가시킴
		timeCount++;
	}
	
	static boolean isIn(int r, int c) {
		if (r >= 0 && c >= 0 && r < N && c < N) {
			return true;
		}
		return false;
	}
}