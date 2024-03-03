import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int[] answer;
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {-1, 0, 1, 0};
	static int[][] map;
	static List<Magic> magics;
	static List<Integer> balls;
	
	static class Magic {
		int d, s;

		public Magic(int d, int s) {
			this.d = d;
			this.s = s;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 초기 세팅
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		answer = new int[4];
		map = new int[N][N];
		magics = new ArrayList<>();
		balls = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			magics.add(new Magic(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		
		setBalls();
		blizzard();
		
		int result = 0;
		for (int i = 1; i < 4; i++) {
			result += i * answer[i];
		}
		System.out.println(result);
	}
	
	
	// 맵 상의 구슬들을 순서대로 리스트에 넣기
	private static void setBalls() {
		int r = N / 2;
		int c = N / 2;
		int dir = 0;
		boolean noBallFlag = false;
		
		for (int move = 1; move < N; move++) {
			for (int i = 0; i < 2; i++) {
				for (int cnt = 0; cnt < move; cnt++) {
					r += dr[dir];
					c += dc[dir];
					
					// 더이상 구슬이 없다면 탐색 중지
					if (map[r][c] == 0) {
						noBallFlag = true;
						break;
					}
					
					balls.add(map[r][c]);
				}
				
				if (noBallFlag) {
					break;
				}
				dir = (dir + 1) % 4;
			}
			
			if (noBallFlag) {
				break;
			}
		}
		
		if (!noBallFlag) {
			for (int cnt = 0; cnt < N - 1; cnt++) {
				r += dr[dir];
				c += dc[dir];
				
				// 더이상 구슬이 없다면 탐색 중지
				if (map[r][c] == 0) {
					noBallFlag = true;
					break;
				}
				
				balls.add(map[r][c]);
			}
		}
	}
	
	// 블리자드 시전
	private static void blizzard() {
		for (int magicIdx = 0; magicIdx < M; magicIdx++) {
			destroyBalls(magicIdx);
			boomBalls();
			transformBalls();
		}
	}
	
	// 구슬을 파괴한다
	private static void destroyBalls(int magicIdx) {
		Magic magic = magics.get(magicIdx);
		int d = magic.d - 1;
		int s = magic.s;
		int cnt = 1;
		int idx = 0;
		
		// 상단 방향인 경우
		if (d == 0) {
			for (int move = 0; move < s; move++) {
				idx += cnt * 2 + (cnt + 1) * 2;
				if (balls.size() <= idx) {
					break;
				}
				balls.remove(idx);
				cnt += 2;
			}
			
		// 하단 방향인 경우
		} else if (d == 1) {
			for (int move = 0; move < s; move++) {
				if (move == 0) {
					idx += cnt * 2;
					cnt++;
				} else {
					idx += cnt * 2 + (cnt + 1) * 2;
					cnt += 2;
				}
				
				if (balls.size() <= idx) {
					break;
				}
				balls.remove(idx);
			}
		
		// 좌측 방향인 경우
		} else if (d == 2) {
			for (int move = 0; move < s; move++) {
				if (move == 0) {
					if (balls.size() <= idx + 1) {
						break;
					}
					balls.remove(idx);
					continue;
				}
				
				idx += cnt + (cnt + 1) * 2 + cnt + 2;
				if (balls.size() <= idx) {
					break;
				}
				balls.remove(idx);
				cnt += 2;
			}
			
		// 우측 방향인 경우
		} else if (d == 3) {
			for (int move = 0; move < s; move++) {
				if (move == 0) {
					idx += cnt * 4;
					if (balls.size() <= idx) {
						break;
					}
					balls.remove(idx);
					cnt++;
					continue;
				}
				
				idx += cnt + (cnt + 1) * 2 + cnt + 2;
				if (balls.size() <= idx) {
					break;
				}
				balls.remove(idx);
				cnt += 2;
			}
		}
	}
	
	// 구슬을 폭발시킨다
	private static void boomBalls() {
		while (true) {
			boolean boomFlag = false;
			for (int idx = 0; idx < balls.size() - 3; idx++) {
				int current = balls.get(idx);
				int cnt = 1;
				
				// 구슬 번호가 같다면 그룹에 포함
				while (current == balls.get(idx + cnt)) {
					cnt++;
					if (cnt == balls.size()) {
						break;
					}
				}
				
				// 4개 이상 연속하는 구슬이 있을 경우
				if (cnt > 3) {
					for (int i = 0; i < cnt; i++) {
						balls.remove(idx);
					}
					answer[current] += cnt;
					idx--;
					boomFlag = true;
				}
			}
			
			// 폭발할 구슬이 없는 경우
			if (!boomFlag) {
				break;
			}
		}
	}
	
	// 구슬을 변화시킨다
	private static void transformBalls() {
		int idx = 0;
		for (; idx < balls.size(); idx++) {
			int current = balls.get(idx);
			int cnt = 1;
			
			// 구슬 번호가 같다면 그룹에 포함
			if (idx < balls.size() - 1) {
				while (current == balls.get(idx + cnt)) {
					cnt++;
					if (idx + cnt == balls.size()) {
						break;
					}
				}
			}
			
			for (int i = 0; i < cnt; i++) {
				balls.remove(idx);
			}
			balls.add(idx, current);
			balls.add(idx, cnt);
			idx++;
			
			if (idx >= N * N - 1) {
				break;
			}
		}
		
		while (balls.size() > N * N - 1) {
			balls.remove(N * N - 1);
		}
	}
}
// 2h 20m