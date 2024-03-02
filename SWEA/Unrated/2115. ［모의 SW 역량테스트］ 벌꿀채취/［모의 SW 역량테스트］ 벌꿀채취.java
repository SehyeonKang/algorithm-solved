import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
	
	static int N, M, C, answer, partSum1, partSum2;
	static int[][] map;
    static boolean[] selected;
	static User[] users;
	
	static class User {
		int r, c;

		public User(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
        	// 초기 세팅
        	st = new StringTokenizer(br.readLine());
        	N = Integer.parseInt(st.nextToken());
        	M = Integer.parseInt(st.nextToken());
        	C = Integer.parseInt(st.nextToken());
        	map = new int[N][N];
    		selected = new boolean[M];
        	users = new User[2];
        	for (int i = 0; i < N; i++) {
        		st = new StringTokenizer(br.readLine());
        		for (int j = 0; j < N; j++) {
        			map[i][j] = Integer.parseInt(st.nextToken());
        		}
        	}

        	answer = 0;
        	findHoney(-1, -1, -1, -1);
        	sb.append("#" + tc + " " + answer + "\n");
        }
        
        System.out.println(sb);
    }
    
    private static void findHoney(int r1, int c1, int r2, int c2) {
    	// 두 일꾼의 위치가 정해진 경우
    	if (r2 >= 0) {
    		calculation(r1, c1, r2, c2);
    		return;
    	}
    	
    	// 첫번째 일꾼 위치 지정
    	if (r1 == -1) {
    		for (int i = 0; i < N; i++) {
    			for (int j = 0; j <= N - M; j++) {
    				findHoney(i, j, r2, c2);
    			}
    		}
    	} else { // 두번째 일꾼 위치 지정
    		for (int i = r1; i < N; i++) {
    			if (i == r1) { // 첫번째 일꾼과 동일 행이라면
    				for (int j = c1 + M; j <= N - M; j++) {
        				findHoney(r1, c1, i, j);
        			}
    			} else { // 첫번째 일꾼과 다른 행이라면
    				for (int j = 0; j <= N - M; j++) {
    					findHoney(r1, c1, i, j);
    				}
    			}
    		}
    	}
    }
    
    // 수익을 계산하기
    private static void calculation(int r1, int c1, int r2, int c2) {
    	int sum1 = 0;
    	int sum2 = 0;
    	
    	// 첫번째 일꾼의 벌통 개수가 C이하일 경우
    	if (isPossible(r1, c1)) {
    		for (int i = c1; i < c1 + M; i++) {
    			sum1 += Math.pow(map[r1][i], 2);
    		}
    	} else {
    		partSum1 = 0;
    		recur(0, r1, c1, 0, 1);
    		sum1 = partSum1;
    	}
    	
    	// 두번째 일꾼의 벌통 개수가 C이하일 경우
    	if (isPossible(r2, c2)) {
    		for (int i = c2; i < c2 + M; i++) {
    			sum2 += Math.pow(map[r2][i], 2);
    		}
    	} else {
    		partSum2 = 0;
    		recur(0, r2, c2, 0, 2);
    		sum2 = partSum2;
    	}
    	
    	answer = Math.max(answer, sum1 + sum2);
    }
    
    // 모든 벌통을 채취할 수 있는지 확인하기
    private static boolean isPossible(int r, int c) {
    	int sum = 0;
		for (int idx = c; idx < c + M; idx++) {
			sum += map[r][idx];
		}
		
		if (sum > C) {
			return false;
		}
		return true;
    }
    
    // 벌통 수익의 최대 경우를 구하기
    private static void recur(int cnt, int r, int c, int sum, int check) {
    	// 벌통 개수 합이 C를 넘는 경우
    	if (sum > C) {
    		return;
    	}
    	
    	if (cnt == M) {
    		int idx = 0;
    		int result = 0;
    		for (int i = c; i < c + M; i++) {
    			if (selected[idx++]) {
    				result += Math.pow(map[r][i], 2);
    			}
     		}
    		
    		if (check == 1) {
    			partSum1 = Math.max(partSum1, result);
    		} else {
    			partSum2 = Math.max(partSum2, result);
    		}
    		return;
    	}
    	
    	selected[cnt] = true;
    	recur(cnt + 1, r, c, sum + map[r][c + cnt], check);
    	selected[cnt] = false;
    	recur(cnt + 1, r, c, sum, check);
    }
}
