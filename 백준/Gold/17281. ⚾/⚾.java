import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int maxScore = 0;
	static int N;
	static Player[] players;
	static int[] order;
	static boolean[] selected;
	
	private static class Player {
		int[] scores;
		
		Player() {
			scores = new int[N + 1];
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		players = new Player[10];
		order = new int[10];
		selected = new boolean[10];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= 9; j++) {
				if (i == 1) {
					players[j] = new Player();
				}
				players[j].scores[i] = Integer.parseInt(st.nextToken());
			}
		}
		
		selected[1] = true;
		order[4] = 1;
		recur(1);
		
		System.out.println(maxScore);
	}
	
	private static void recur(int count) {
		if (count == 4) {
			recur(5);
			return;
		}
		
		if (count == 10) {
			Queue<Player> q = new ArrayDeque<>();
			for (int i = 1; i <= 9; i++) {
				q.offer(players[order[i]]);
			}
			
			playGame(q);
			return;
		}
		
		for (int i = 2; i <= 9; i++) {
			if (selected[i]) {
				continue;
			}
			
			selected[i] = true;
			order[count] = i;
			recur(count + 1);
			selected[i] = false;
		}
	}
	
	private static void playGame(Queue<Player> q) {
		int scoreSum = 0;
		
		for (int inning = 1; inning <= N; inning++) {
			int outCnt = 0;
			boolean[] baseCond = new boolean[4];
			while (outCnt < 3) {
				Player player = q.poll();
				int hitOption = player.scores[inning];
				
				if (hitOption == 0) {
					outCnt++;
				} else {
					baseCond[0] = true;
					int score = hit(baseCond, hitOption);
					scoreSum += score;
				}
				
				q.offer(player);
			}
		}
		
		maxScore = Math.max(maxScore, scoreSum);
	}
	
	private static int hit(boolean[] baseCond, int option) {
		int score = 0;
		for (int i = 3; i >= 0; i--) {
			if (baseCond[i]) {
				if (i + option > 3) {
					score++;
					baseCond[i] = false;
				} else {
					baseCond[i + option] = true;
					baseCond[i] = false;
				}
			}
		}
		return score;
	}
}
// 0h 50m