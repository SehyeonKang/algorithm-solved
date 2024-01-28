import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static int recW;
	static int recH;
	
	static class Store {
		int direction;
		int pos;
		
		Store(int direction, int pos) {
			this.direction = direction;
			this.pos = pos;
		}
	}	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		recW = Integer.parseInt(st.nextToken());
		recH = Integer.parseInt(st.nextToken());
		int storeCount = Integer.parseInt(br.readLine());
		List<Store> stores = new ArrayList<>();
		
		for (int i = 0; i < storeCount; i++) {
			st = new StringTokenizer(br.readLine());
			int direction = Integer.parseInt(st.nextToken());
			int pos = Integer.parseInt(st.nextToken());
			stores.add(new Store(direction, pos));
		}
		
		st = new StringTokenizer(br.readLine());
		int playerDir = Integer.parseInt(st.nextToken());
		int playerPos = Integer.parseInt(st.nextToken());
		
		int answer = 0;
		for (Store store : stores) {
			answer += calculate(store, playerDir, playerPos);
		}
		
		System.out.println(answer);
	}
	
	static int calculate(Store store, int playerDir, int playerPos) {
		int storeDir = store.direction;
		int storePos = store.pos;
		
		if (storeDir == playerDir) {
			return Math.abs(storePos - playerPos);
			
		} else if (storeDir < 3 && playerDir < 3) {
			return Math.min(recH + storePos + playerPos, recH + 2 * recW - storePos - playerPos);
			
		} else if (storeDir > 2 && playerDir > 2) {
			return Math.min(recW + storePos + playerPos, recW + 2 * recH - storePos - playerPos);
			
		} else if (playerDir == 1) {
			if (storeDir == 3) {
				return playerPos + storePos;
			} else {
				return recW - playerPos + storePos;
			}
		} else if (playerDir == 2) {
			if (storeDir == 3) {
				return playerPos + recH - storePos;
			} else {
				return recW - playerPos + recH - storePos;
			}
		} else if (playerDir == 3) {
			if (storeDir == 1) {
				return playerPos + storePos;
			} else {
				return recH -playerPos + storePos;
			}
		} else if (playerDir == 4) {
			if (storeDir == 1) {
				return playerPos + recW - storePos;
			} else {
				return recH - playerPos + recW - storePos;
			}
		}
		
		return 0;
	}
}