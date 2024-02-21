import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int maxGroupSize, N, M, answer;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static int basicBlockCheck;
	static int[][] map;
	static boolean[][] visited;
	static List<BlockGroup> maxGroups;
	
	static class BlockGroup implements Comparable<BlockGroup> {
		int mainR, mainC, rainbowCnt;
		List<Point> blocks;
		
		public BlockGroup(int mainR, int mainC) {
			super();
			this.mainR = mainR;
			this.mainC = mainC;
			this.rainbowCnt = 0;
			this.blocks = new ArrayList<>();
		}

		@Override
		public int compareTo(BlockGroup o) {
			if (this.rainbowCnt != o.rainbowCnt) {
				return o.rainbowCnt - this.rainbowCnt;
			} else if (this.mainR != o.mainR) {
				return o.mainR - this.mainR;
			} else {
				return o.mainC - this.mainC;
			}
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
        map = new int[N][N];
        maxGroups = new ArrayList<>();
        for (int i = 0; i < N; i++) {
        	st = new StringTokenizer(br.readLine());
        	for (int j = 0; j < N; j++) {
        		map[i][j] = Integer.parseInt(st.nextToken());
        		if (map[i][j] > 0) {
        			basicBlockCheck++;
        		}
        	}
        }
        
        while (basicBlockCheck > 0) {
        	maxGroupSize = 0;
        	visited = new boolean[N][N];
        	
        	// BFS 탐색하며 제거할 블록 그룹 찾기
        	for (int i = 0; i < N; i++) {
        		for (int j = 0; j < N; j++) {
        			if (map[i][j] > 0 && !visited[i][j]) {
        				bfs(i, j, map[i][j]);
        			}
        		}
        	}
        	
        	if (maxGroupSize < 2) {
        		break;
        	}
        	
        	// 조건에 해당하는 블록 그룹의 블록들 제거
        	Collections.sort(maxGroups);
        	removeBlocks(maxGroups.get(0));
        	
        	// 점수 더하기
        	answer += Math.pow(maxGroupSize, 2);
        	
        	downBlocks();
        	rotateMap();
        	downBlocks();
        }
        
        System.out.println(answer);
        
    }
    
    // 최대 블록 수를 가지고 있는 블록 그룹들 찾기
    private static void bfs(int startR, int startC, int color) {
    	BlockGroup blockGroup = new BlockGroup(startR, startC);
    	Queue<Point> q = new ArrayDeque<>();
    	boolean[][] visitedRainbow = new boolean[N][N];
    	
    	q.offer(new Point(startR, startC));
    	visited[startR][startC] = true;
    	blockGroup.blocks.add(new Point(startR, startC));
    	
    	while (!q.isEmpty()) {
    		Point p = q.poll();
    		int r = p.x;
    		int c = p.y;
    		
    		for (int dir = 0; dir < 4; dir++) {
    			int nr = r + dr[dir];
    			int nc = c + dc[dir];
    			
    			if (isIn(nr, nc)) {
    				// 무지개 블록이라면
    				if (map[nr][nc] == 0 && !visitedRainbow[nr][nc]) {
    					q.offer(new Point(nr, nc));
        				blockGroup.blocks.add(new Point(nr, nc));
    					blockGroup.rainbowCnt++;
    					visitedRainbow[nr][nc] = true;
    				} else if (map[nr][nc] == color && !visited[nr][nc]){ // 일반 블록이라면
    					q.offer(new Point(nr, nc));
        				blockGroup.blocks.add(new Point(nr, nc));
    					visited[nr][nc] = true;
    				}
    			}
    		}
    	}
    	
    	if (maxGroupSize == blockGroup.blocks.size()) {
    		maxGroups.add(blockGroup);
    	} else if (maxGroupSize < blockGroup.blocks.size()) {
    		maxGroupSize = blockGroup.blocks.size();
    		maxGroups.clear();
    		maxGroups.add(blockGroup);
    	}
    }
    
    // 블록 제거하기
    private static void removeBlocks(BlockGroup blockGroup) {
    	for (Point p : blockGroup.blocks) {
    		if (map[p.x][p.y] > 0) {
    			basicBlockCheck--;
    		}
    		map[p.x][p.y] = -99;
    	}
    }
    
    // 블록들 내리기
    private static void downBlocks() {
    	for (int i = N - 2; i >= 0; i--) {
    		for (int j = 0; j < N; j++) {
    			int color = map[i][j];
    			int r = i;
    			int c = j;
    			
    			if (color >= 0) {
    				int nr = r + 1;
    				
    				while (isIn(nr, c) && map[nr][c] == -99) {
    					map[r][c] = -99;
    					map[nr][c] = color;
    					r = nr;
    					nr = r + 1;
    				}
    			}
    		}
    	}
    }
    
    // 격자를 90도 반시계 방향으로 회전하기
    private static void rotateMap() {
    	int[][] tmpMap = new int[N][N];
    	
    	for (int i = 0; i < N; i++) {
    		for (int j = 0; j < N; j++) {
    			tmpMap[i][j] = map[j][N - 1 - i];
    		}
    	}
    	
    	map = tmpMap;
    }
    
    private static boolean isIn(int r, int c) {
    	return r >= 0 && c >= 0 && r < N && c < N;
    }
}

// 1h 50m