import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N, K;
	static int[] dr = {0, 0, -1 ,1};
	static int[] dc = {1, -1, 0, 0};
	static int[][] board, stackCount;
	static int[][][] boardStatus;
	static Node[] nodes;
	
	static class Node {
		int r, c, no, d;
		boolean stacked;
		
		public Node(int r, int c, int no, int d, boolean stacked) {
			super();
			this.r = r;
			this.c = c;
			this.no = no;
			this.d = d;
			this.stacked = stacked;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		board = new int[N][N];
		stackCount = new int[N][N];
		boardStatus = new int[N][N][K];
		nodes = new Node[K + 1];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 1; i <= K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int d = Integer.parseInt(st.nextToken()) - 1;
			nodes[i] = new Node(r, c, i, d, false);
			boardStatus[r][c][0] = i;
			stackCount[r][c] = 1;
		}
		
		play();
	}
	
	private static void play() {
		for (int time = 1; time <= 1000; time++) {
			for (int i = 1; i <= K; i++) {
				Node node = nodes[i];
				int r = node.r;
				int c = node.c;
				int d = node.d;
				if (node.stacked) {
					continue;
				}
				
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				// 흰색 칸인 경우
				if (isIn(nr, nc) && board[nr][nc] == 0) {
					int top = 0;
					for (int j = 0; j < K; j++) {
						if (boardStatus[nr][nc][j] == 0) {
							break;
						}
						top++;
					}
					
					for (int j = 0; j < K; j++) {
						if (boardStatus[r][c][j] == 0) {
							break;
						}
						int no = boardStatus[r][c][j];
						Node moveNode = nodes[no];
						if (top > 0 && !moveNode.stacked) {
							moveNode.stacked = true;
						}
						
						moveNode.r = nr;
						moveNode.c = nc;
						stackCount[nr][nc]++;
						boardStatus[r][c][j] = 0;
						boardStatus[nr][nc][top++] = no;
					}
					stackCount[r][c] = 0;
				// 빨간색 칸인 경우
				} else if (isIn(nr, nc) && board[nr][nc] == 1) {
					int top = 0;
					for (int j = 0; j < K; j++) {
						if (boardStatus[nr][nc][j] == 0) {
							break;
						}
						top++;
					}
					
					for (int j = K - 1; j >= 0; j--) {
						if (boardStatus[r][c][j] == 0) {
							continue;
						}
						int no = boardStatus[r][c][j];
						Node moveNode = nodes[no];
						
						if (top == 0 && moveNode.stacked) {
							moveNode.stacked = false;
						}
						
						if (top > 0 && !moveNode.stacked) {
							moveNode.stacked = true;
						}
						
						moveNode.r = nr;
						moveNode.c = nc;
						stackCount[nr][nc]++;
						boardStatus[r][c][j] = 0;
						boardStatus[nr][nc][top++] = no;
					}
					stackCount[r][c] = 0;
				// 파란색 칸이거나 맵 밖인 경우
				} else {
					int nd;
					if (d == 0) {
						nd = 1;
					} else if (d == 1) {
						nd = 0;
					} else if (d == 2) {
						nd = 3;
					} else {
						nd = 2;
					}
					node.d = nd;
					nr = r + dr[nd];
					nc = c + dc[nd];
					
					// 흰색 칸인 경우
					if (isIn(nr, nc) && board[nr][nc] == 0) {
						int top = 0;
						for (int j = 0; j < K; j++) {
							if (boardStatus[nr][nc][j] == 0) {
								break;
							}
							top++;
						}
						
						for (int j = 0; j < K; j++) {
							if (boardStatus[r][c][j] == 0) {
								break;
							}
							int no = boardStatus[r][c][j];
							Node moveNode = nodes[no];
							if (top > 0 && !moveNode.stacked) {
								moveNode.stacked = true;
							}
							
							moveNode.r = nr;
							moveNode.c = nc;
							stackCount[nr][nc]++;
							boardStatus[r][c][j] = 0;
							boardStatus[nr][nc][top++] = no;
						}
						stackCount[r][c] = 0;
					// 빨간색 칸인 경우
					} else if (isIn(nr, nc) && board[nr][nc] == 1) {
						int top = 0;
						for (int j = 0; j < K; j++) {
							if (boardStatus[nr][nc][j] == 0) {
								break;
							}
							top++;
						}
						
						for (int j = K - 1; j >= 0; j--) {
							if (boardStatus[r][c][j] == 0) {
								continue;
							}
							int no = boardStatus[r][c][j];
							Node moveNode = nodes[no];
							
							if (top == 0 && moveNode.stacked) {
								moveNode.stacked = false;
							}
							
							if (top > 0 && !moveNode.stacked) {
								moveNode.stacked = true;
							}
							
							moveNode.r = nr;
							moveNode.c = nc;
							stackCount[nr][nc]++;
							boardStatus[r][c][j] = 0;
							boardStatus[nr][nc][top++] = no;
						}
						stackCount[r][c] = 0;
					}
				}
				
				if (isIn(nr, nc) && stackCount[nr][nc] >= 4) {
					System.out.println(time);
					return;
				}
			}
		}
		
		System.out.println("-1");
	}
	
	private static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}
}

// 1h 40m