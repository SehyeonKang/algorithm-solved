import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int K;
	static Wheel[] wheels; // 톱니바퀴의 정보를 담는 배열
	static RotationInfo[] infos; // 회전 정보를 담는 배열
	
	// 톱니바퀴의 각 톱니별 극 상태와 12시, 3시, 9시 방향을 가리키는 index를 나타내는 변수를 저장하는 클래스
	static class Wheel {
		int[] state;
		int scorePos, rightPos, leftPos;
		
		Wheel(int[] state) {
			this.state = state;
			scorePos = 0;
			rightPos = 2;
			leftPos = 6;
		}
		
		// 톱니바퀴를 시계방향으로 회전할 때
		void rotate() {
			scorePos = (scorePos + 7) % 8;
			rightPos = (rightPos + 7) % 8;
			leftPos = (leftPos + 7) % 8;
		}

		// 톱니바퀴를 반시계방향으로 회전할 때
		void rotateReverse() {
			scorePos = (scorePos + 1) % 8;
			rightPos = (rightPos + 1) % 8;
			leftPos = (leftPos + 1) % 8;
		}
	}
	
	// 회전하려는 톱니바퀴의 번호와 방향을 저장하는 클래스
	static class RotationInfo {
		int wheelNum, direction;
		
		RotationInfo(int wheelNum, int direction) {
			this.wheelNum = wheelNum;
			this.direction = direction;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		// 초기 세팅
		wheels = new Wheel[5];
		for (int i = 1; i <= 4; i++ ) {
			int[] state = new int[8];
			String states = br.readLine();
			for (int j = 0; j < 8; j++) {
				state[j] = states.charAt(j) - '0';
			}
			wheels[i] = new Wheel(state);
		}
		
		K = Integer.parseInt(br.readLine());
		infos = new RotationInfo[K];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			infos[i] = new RotationInfo(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		play();
		int answer = calculateScore();
		System.out.println(answer);
	}

	// 톱니바퀴들을 회전하는 메서드
	static void play() {
		for (int i = 0; i < K; i++) {
			RotationInfo info = infos[i];
			int wheelNum = info.wheelNum;
			int direction = info.direction;
			int leftNum = wheelNum - 1;	// 왼쪽 톱니바퀴의 번호
			int rightNum = wheelNum + 1; // 오른쪽 톱니바퀴의 번호
			int[] rotateCheck = new int[5]; // 회전하기 전 각 톱니바퀴가 회전하는지, 어느 방향으로 회전하는지를 담는 배열. 0: 회전x, 1: 시계방향, -1: 반시계 방향
			rotateCheck[wheelNum] = direction;
			
			// 왼쪽 방향에 톱니바퀴가 있는 경우 붙어있는 톱니의 극을 비교해서 다르다면 회전 방향을 저장
			int leftState = wheels[wheelNum].state[wheels[wheelNum].leftPos];
			int leftDirection = direction * -1;
			while (leftNum > 0) {
				int rightState = wheels[leftNum].state[wheels[leftNum].rightPos];
				
				if (leftState != rightState) {
					rotateCheck[leftNum] = leftDirection;
				} else {
					break;
				}
				
				leftState = wheels[leftNum].state[wheels[leftNum].leftPos];
				leftDirection *= -1;
				leftNum--;
			}
			
			// 오른쪽 방향에 톱니바퀴가 있는 경우 붙어있는 톱니의 극을 비교해서 다르다면 회전 방향을 저장
			int rightState = wheels[wheelNum].state[wheels[wheelNum].rightPos];
			int rightDirection = direction * -1;
			while (rightNum < 5) {
				leftState = wheels[rightNum].state[wheels[rightNum].leftPos];
				
				if (rightState != leftState) {
					rotateCheck[rightNum] = rightDirection;
				} else {
					break;
				}
				
				rightState = wheels[rightNum].state[wheels[rightNum].rightPos];
				rightDirection *= - 1;
				rightNum++;
			}
			
			// 회전해야하는 톱니바퀴들을 회전
			for (int j = 1; j < 5; j++) {
				if (rotateCheck[j] == 1) {
					wheels[j].rotate();
				} else if (rotateCheck[j] == -1) {
					wheels[j].rotateReverse();
				}
			}
		}
	}
	
	// 총 점수 합계를 계산하는 메서드
	static int calculateScore() {
		int score = 0;
		
		for (int i = 1; i < 5; i++) {
			if (wheels[i].state[wheels[i].scorePos] == 1) {
				score += Math.pow(2, i - 1);
			}
		}
		
		return score;
	}
}