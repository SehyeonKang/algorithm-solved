import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] dr = {1, 0, -1, 0};
	static int[] dc = {0, 1, 0, -1};
	static int answer = 1;
	static int R;
	static int C;
	
	static class Position {
		int row;
		int col;
		
		Position(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		boolean[] alphabets = new boolean[26];
		char[][] board = new char[R][C];
		for (int i = 0; i < R; i++) {
			String s = br.readLine();
			for (int j = 0; j < C; j++) {
				board[i][j] = s.charAt(j);
			}
		}
		
		alphabets[board[0][0] - 'A'] = true;
		dfs(board, alphabets, new Position(0, 0), 1);
		
		System.out.println(answer);
	}
	
	static void dfs(char[][] board, boolean[] alphabets, Position pos, int count) {
		for (int i = 0; i < 4; i++) {
			int nRow = pos.row + dr[i];
			int nCol = pos.col + dc[i];
			
			if (nRow > -1 && nCol > -1 && nRow < R && nCol < C && !alphabets[board[nRow][nCol] - 'A']) {
				alphabets[board[nRow][nCol] - 'A'] = true;
				dfs(board, alphabets, new Position(nRow, nCol), count + 1);
				alphabets[board[nRow][nCol] - 'A'] = false;
			}
		}
		
		answer = Math.max(answer, count);
	}
}