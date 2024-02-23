import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

	static int N, M, K;
	static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};
	static ArrayList<Fireball> fireballs;
	
	static class Fireball implements Comparable<Fireball> {
		int r, c, m, d, s, num; // num은 2차원 배열의 몇번째 칸인지를 의미함

		public Fireball(int r, int c, int m, int d, int s) {
			this.r = r;
			this.c = c;
			this.m = m;
			this.d = d;
			this.s = s;
		}

		@Override
		public int compareTo(Fireball o) {
			return this.num - o.num;
		}
	}
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        // 초기 세팅
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        fireballs = new ArrayList<>();
        
        for (int i = 0; i < M; i++) {
        	st = new StringTokenizer(br.readLine());
        	int r = Integer.parseInt(st.nextToken());
        	int c = Integer.parseInt(st.nextToken());
        	int m = Integer.parseInt(st.nextToken());
        	int s = Integer.parseInt(st.nextToken());
        	int d = Integer.parseInt(st.nextToken());
        	fireballs.add(new Fireball(r, c, m, d, s));
        }
        
        play();
        
        int answer = 0;
        for (Fireball fb : fireballs) {
        	answer += fb.m;
        }
        System.out.println(answer);
    }
    
    private static void play() {
    	// K번동안 반복
    	for (int time = 0; time < K; time++) {
    		for (int i = 0; i < fireballs.size(); i++) {
    			Fireball now = fireballs.get(i);
    			// 속력 s만큼 방향 d로 이동
    			int nr = now.r + dr[now.d] * (now.s % N);
    			int nc = now.c + dc[now.d] * (now.s % N);
    			
    			if (nr > 0) {
    				nr %= N;
    			} else if (nr < 0) {
    				nr += N;
    			}
    			if (nc > 0) {
    				nc %= N;
    			} else if (nc < 0) {
    				nc += N;
    			}
    			
    			now.r = nr;
    			now.c = nc;
    			now.num = nr * N + nc;
    		}
    		
    		// 위치순으로 파이어볼 정렬
    		Collections.sort(fireballs);
    		
    		for (int i = 0; i < fireballs.size() - 1; i++) {
    			Fireball now = fireballs.get(i);
    			Fireball next = fireballs.get(i + 1);
    			
    			// 2개 이상의 파이어볼이 있는 칸을 찾은 경우
    			if (now.num == next.num) {
    				int num = now.num;
    				boolean oddFlag = false;
    				boolean evenFlag = false;
    				int mSum = 0;
    				int sSum = 0;
    				int cnt = 0;
    				
    				// 같은 칸에 있는 파이어볼들을 찾아서 로직을 수행
    				while (i < fireballs.size() && num == fireballs.get(i).num) {
    					Fireball fb = fireballs.get(i);
    					mSum += fb.m;
    					sSum += fb.s;
    					if (fb.d % 2 == 0) {
    						evenFlag = true;
    					} else {
    						oddFlag = true;
    					}
    					fireballs.remove(i);
    					cnt++;
    				}
    				mSum /= 5;
    				sSum /= cnt;
    				
    				// 질량이 1이상이라면
    				if (mSum > 0) {
    					// 합쳐지는 파이어볼의 방향이 모두 홀수이거나 짝수이면
    					if (!oddFlag || !evenFlag) {
    						for (int j = 0; j < 7; j += 2) {
    							fireballs.add(i, new Fireball(num / N, num % N, mSum, j, sSum));
    						}
    					// 그렇지 않다면
    					} else {
    						for (int j = 1; j < 8; j += 2) {
    							fireballs.add(i, new Fireball(num / N, num % N, mSum, j, sSum));
    						}
    					}
    					i += 3;
    					
    				// 질량이 0이라면 파이어볼이 사라지므로 기존 index를 다시 탐색해야함
    				} else {
    					i--;
    				}
    			}
    		}
    	}
    }
}