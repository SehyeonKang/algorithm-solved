import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    
	static int answer = Integer.MAX_VALUE;
	//idx0: n번째 판, idx1: 회전횟수, idx2,3: 판 정보
    static int[][][][] boards;
    // z, x, y축 좌표 순서
    static int[][][] miro;
    static int[] rotates;
    static boolean[] selected;
    static int[] boardOrder;
    static int[] dx = {1, -1, 0, 0, 0, 0};
    static int[] dy = {0, 0, 1, -1, 0, 0};
    static int[] dz = {0, 0, 0, 0, 1, -1};
    
    static class Position {
    	int x, y, z;

		public Position(int x, int y, int z) {
			this.x = x;
			this.y = y;
			this.z = z;
		}
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        boards = new int[5][4][5][5];
        miro = new int[5][5][5];
        rotates = new int[5];
        boardOrder = new int[5];
        
        int[][] board;
        for (int i = 0; i < 5; i++) {
        	board = new int[5][5];
            for (int r = 0; r < 5; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < 5; c++) {
                    board[r][c] = Integer.parseInt(st.nextToken());
                }
            }
            
            for (int j = 0; j < 4; j++) {
                board = rotate(board, j);
                boards[i][j] = board.clone();
            }
        }
        
        recur(0);
        
        if (answer < Integer.MAX_VALUE) {
        	System.out.println(answer);
        } else {
        	System.out.println(-1);
        }
    }
    
    //  판을 회전시키는 메서드
    private static int[][] rotate(int[][] board, int cnt) {
        if (cnt == 0) {
            return board.clone();
        }
        
        int[][] newBoard = new int[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                newBoard[i][j] = board[4 - j][i];
            }
        }
        return newBoard;
    }
    
    // 보드 회전 조합을 생성하는 메서드
    private static void recur(int cnt) {
        if (cnt == 5) {
        	// 미로 생성
        	selected = new boolean[5];
        	makeMiro(0);
            return;
        }
        
        for (int i = 0; i < 4; i++) {
            rotates[cnt] = i;
            recur(cnt + 1);
        }
    }
    
    // 미로 조합을 생성하는 메서드
    private static void makeMiro(int cnt) {
    	if (cnt == 5) {
    		// 미로 생성
        	for (int i = 0; i < 5; i++) {
        		miro[i] = boards[boardOrder[i]][rotates[i]];
        	}
        	
        	for (int i = 0; i < 5; i += 4) {
        		for (int j = 0; j < 5; j += 4) {
        			int endX;
        			int endY;
        			if (i == 0) {
        				endX = 4;
        			} else {
        				endX = 0;
        			}
        			if (j == 0) {
        				endY = 4;
        			} else {
        				endY = 0;
        			}
        			
        			if (isBlocked(i, j, endX, endY)) {
        				return;
        			}
        			
        			bfs(i, j, endX, endY);
        		}
        	}
        	
    		return;
    	}
    	
    	for (int i = 0; i < 5; i++) {
    		if (selected[i]) {
    			continue;
    		}
    		selected[i] = true;
    		boardOrder[cnt] = i;
    		makeMiro(cnt + 1);
    		selected[i] = false;
    	}
    }
    
    // 입구 또는 출구가 막혀있는지 확인하는 메서드
    private static boolean isBlocked(int startX, int startY, int endX, int endY) {
    	return !(miro[0][startX][startY] == 1 && miro[4][endX][endY] == 1);
    }
    
    // 미로를 탐색하는 메서드
    private static void bfs(int startX, int startY, int endX, int endY) {
    	boolean[][][] visited = new boolean[5][5][5];
    	Queue<Position> q = new ArrayDeque<>();
    	q.offer(new Position(startX, startY, 0));
    	visited[0][startX][startY] = true;
    	int moveCnt = 1;
    	
    	while (!q.isEmpty()) {
    		int qSize = q.size();
    		for (int i = 0; i < qSize; i++) {
    			Position p = q.poll();
    			int x = p.x;
    			int y = p.y;
    			int z = p.z;
    			
    			for (int d = 0; d < 6; d++) {
    				int nx = x + dx[d];
    				int ny = y + dy[d];
    				int nz = z + dz[d];
    				
    				// 이동한 위치가 맵 안이고 방문 전이고 이동 가능한 칸인 경우
    				if (isIn(nx, ny, nz) && !visited[nz][nx][ny] && miro[nz][nx][ny] == 1) {
    					// 도착한 경우
    					if (nx == endX && ny == endY && nz == 4) {
    						answer = Math.min(answer, moveCnt);
    						return;
    					}
    					
    					q.offer(new Position(nx, ny, nz));
    					visited[nz][nx][ny] = true;
    				}
    			}
    		}
    		
    		moveCnt++;
    	}
    	
    }
    
    private static boolean isIn(int x, int y, int z) {
    	return x >= 0 && x < 5 && y >= 0 && y < 5 && z >= 0 && z < 5;
    }
}
