import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	
	static int[][] board;
	static boolean[][] visited;
	static List<int[]> coreList;
	static int[] dirx = {0,0,-1,1};
	static int[] diry = {1,-1,0,0};
	static int maxConnectedCore = Integer.MIN_VALUE;
	static int result = Integer.MAX_VALUE;
	static int N;
	static boolean chk;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스 개수
		for(int t=1; t<=T; t++) {
			N = Integer.parseInt(br.readLine()); // NxN 배열
			board = new int[N][N];
			visited = new boolean[N][N];
            
            maxConnectedCore = Integer.MIN_VALUE;
			result = Integer.MAX_VALUE;
			
			coreList = new ArrayList<int[]>();
			for(int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
					// 맵 가장자리가 아니고 코어가 존재하는 경우
					if(i>=1 && i<N-1 && j>=1 && j<N-1 && board[i][j]==1) {
						coreList.add(new int[] {i,j});
					}
				}
			}
			
			solve(0, 0);
			System.out.println("#" + t + " " + result);
		}
		
	}

	private static void solve(int cur, int cnt) { // cur: 현재, cnt: 연결 코어 수
		if(cur==coreList.size()) { // 모든 코어를 확인했을 때
			// cnt를 통해 몇 개가 연결 되어 있는지 확인
			
			// cnt가 Math.max 그 전 값과 비교하여 더 클 경우(연결 코어가 많다면)
			// result를 다시 저장(result는 visited 배열에서 true 세주면 됨)
			if(maxConnectedCore < cnt) {
				maxConnectedCore = cnt;
				int lineCount = 0;
				for(int i=0; i<N; i++) {
					for(int j=0; j<N; j++) {
						if(visited[i][j]) lineCount++;
					}
				}
				result = lineCount;
			}
			
			// cnt가 Math.min 그 전 값과 비교하여 같을 경우
			// result를 비교해주고  Math.min 써서 더 작은 값을 result에 저장
			// (result는 visited 배열에서 true 세주면 됨)
			if(maxConnectedCore == cnt) {
				int lineCount = 0;
				for(int i=0; i<N; i++) {
					for(int j=0; j<N; j++) {
						if(visited[i][j]) lineCount++;
					}
				}
				if(result > lineCount)
					result = lineCount;
			}
			
			return;
		}
		
		int[] data = coreList.get(cur);
		int xpos = data[0];
		int ypos = data[1];
		
		for(int d=0; d<4; d++) {
			
			int count = 0;
			chk = true;
			while(chk) {
				count++;
				int nx = xpos + dirx[d] * count;
				int ny = ypos + diry[d] * count;
				
				if(nx>=0 && nx<N && ny>=0 && ny<N) {
					if(board[nx][ny]==1) { // 다른 코어와 만났을 때
						count = 0;
						break;
					}
					if(visited[nx][ny]) { // 전선과 부딪혔을 때(visited가 true일 때)
						count = 0;
						break;
					}
				}
				
				if(nx<0 || nx>=N || ny<0 || ny>=N) { // 가장자리에 도달했을 경우
					if(nx<xpos) // 위로 도달
						for(int i=xpos-1; i>=0; i--) visited[i][ypos] = true;
					if(nx>xpos) // 아래로 도달
						for(int i=xpos+1; i<N; i++) visited[i][ypos] = true;
					if(ny<ypos) // 왼쪽으로 도달
						for(int i=ypos-1; i>=0; i--) visited[xpos][i] = true;
					if(ny>ypos) // 오른쪽으로 도달
						for(int i=ypos+1; i<N; i++) visited[xpos][i] = true;
					
					solve(cur+1, cnt+1); // 다음 타겟으로 이동, 연결 횟수도 1 증가
					
					if(nx<xpos) // 위로 도달
						for(int i=xpos-1; i>=0; i--) {
							visited[i][ypos] = false;
							count = 0;
							chk = false;
						}
					if(nx>xpos) // 아래로 도달
						for(int i=xpos+1; i<N; i++) {
							visited[i][ypos] = false;
							count = 0;
							chk = false;
						}
					if(ny<ypos) // 왼쪽으로 도달
						for(int i=ypos-1; i>=0; i--) {
							visited[xpos][i] = false;
							count = 0;
							chk = false;
						}
					if(ny>ypos) // 오른쪽으로 도달
						for(int i=ypos+1; i<N; i++) {
							visited[xpos][i] = false;
							count = 0;
							chk = false;
						}
				}
			}
		}
		solve(cur+1, cnt); // 다음 타겟으로 이동, 연결이 이루어지지 않았으므로 그대로 cnt
		
	}
}
