import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Solution {
	
	static int N, answer;
	static int[] dr = {1, 1, -1, -1};
	static int[] dc = {1, -1, -1, 1};
	static int[][] map;
	static List<Integer> numbers;
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
        	// 초기 세팅
        	N = Integer.parseInt(br.readLine());
        	answer = 0;
        	map = new int[N][N];
        	numbers = new ArrayList<>();
        	for (int i = 0; i < N; i++) {
        		st = new StringTokenizer(br.readLine());
        		for (int j = 0; j < N; j++) {
        			map[i][j] = Integer.parseInt(st.nextToken());
        		}
        	}
        	
        	findDesserts();
        	
        	if (answer == 0) {
        		answer = -1;
        	}
        	sb.append("#" + tc + " " + answer + "\n");
        }
        
        System.out.println(sb);
    }
    
    private static void findDesserts() {
    	for (int i = 0; i < N - 2; i++) {
    		for (int j = 1; j < N - 1; j++) {
    			int cnt = 0;
    			int nr = i;
    			int nc = j;
    			numbers.add(map[i][j]);
    			while (true) {
    				cnt++;
    				nr += dr[0];
    				nc += dc[0];
    				if (!isIn(nr, nc) || numbers.contains(map[nr][nc])) {
    					break;
    				}
    				numbers.add(map[nr][nc]);
    				move(nr, nc, 1, cnt, 0);
    			}
    			numbers.clear();
    		}
    	}
    }
    
    private static void move(int r, int c, int dir, int rightCnt, int leftCnt) {
    	int cnt = 0;
    	int nr = r;
    	int nc = c;
    	// 좌하단 방향
    	if (dir == 1) {
    		// 맵 밖으로 나가거나 디저트 종류가 중복될 때까지 좌하단 탐색
    		while (true) {
				nr += dr[1];
				nc += dc[1];
				if (!isIn(nr, nc) || numbers.contains(map[nr][nc])) {
					break;
				}
				leftCnt++;
				numbers.add(map[nr][nc]);
				
				if (answer < 2 * (rightCnt + leftCnt)) {
					move(nr, nc, 2, rightCnt, leftCnt);
				}
			}
			
    		// 좌하단으로 이동할 때 넣었던 디저트 종류 삭제
    		for (int i = 0; i < leftCnt; i++) {
    			numbers.remove(rightCnt + 1);
    		}
    	} else if (dir == 2) { // 좌상단 탐색
    		// 맵 밖으로 나가거나 디저트 종류가 중복될 때까지 탐색
    		int i = 0;
    		for (; i < rightCnt; i++) {
				nr += dr[2];
				nc += dc[2];
				if (!isIn(nr, nc) || numbers.contains(map[nr][nc])) {
					break;
				}
				cnt++;
				numbers.add(map[nr][nc]);
			}
    		
    		// 좌상단 탐색을 모두 마친 경우
    		if (i == rightCnt) {
    			move(nr, nc, 3, rightCnt, leftCnt);
    		}
			
    		// 좌상단으로 이동할 때 넣었던 디저트 종류 삭제
    		for (i = 0; i < cnt; i++) {
    			numbers.remove(rightCnt + leftCnt + 1);
    		}
    	} else if (dir == 3) { // 우상단 탐색
    		// 맵 밖으로 나가거나 디저트 종류가 중복될 때까지 탐색
    		int i = 0;
    		for (; i < leftCnt - 1; i++) {
				nr += dr[3];
				nc += dc[3];
				if (!isIn(nr, nc) || numbers.contains(map[nr][nc])) {
					break;
				}
				cnt++;
				numbers.add(map[nr][nc]);
			}
    		
    		// 우상단 탐색을 모두 마친 경우
    		if (i == leftCnt - 1) {
    			answer = 2 * (rightCnt + leftCnt);
    		}
			
    		// 우상단으로 이동할 때 넣었던 디저트 종류 삭제
    		for (i = 0; i < cnt; i++) {
    			numbers.remove(2 * rightCnt + leftCnt + 1);
    		}
    	}
    }
    
    private static boolean isIn(int r, int c) {
    	return r >= 0 && r < N && c >= 0 && c < N;
    }
}
