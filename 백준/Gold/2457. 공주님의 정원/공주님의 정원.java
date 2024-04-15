import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int N, answer;
	static Project[] projects;
	
	static class Project implements Comparable<Project>{
		int startM, startD, endM, endD;

		public Project(int startM, int startD, int endM, int endD) {
			this.startM = startM;
			this.startD = startD;
			this.endM = endM;
			this.endD = endD;
		}

		// 시작 일자가 빠른순으로 정렬
		@Override
		public int compareTo(Project o) {
			if (this.startM != o.startM) {
				return this.startM - o.startM;
			}
			return this.startD - o.startD;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// 초기 세팅
		N = Integer.parseInt(br.readLine());
		projects = new Project[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int startM = Integer.parseInt(st.nextToken());
			int startD = Integer.parseInt(st.nextToken());
			int endM = Integer.parseInt(st.nextToken());
			int endD = Integer.parseInt(st.nextToken());
			
			projects[i] = new Project(startM, startD, endM, endD);
		}
		
		Arrays.sort(projects);
		greedy();
		System.out.println(answer);
	}
	
	private static void greedy() {
		int prjCnt = 0;
		int currentEM = 3;
		int currentED = 1;
		int nextEM = 3;
		int nextED = 1;
		
		for (int i = 0; i < N; i++) {
			int prjSM = projects[i].startM;
			int prjSD = projects[i].startD;
			int prjEM = projects[i].endM;
			int prjED = projects[i].endD;
			
			// 시작 일자가 현재 프로젝트 종료 일자보다 빠르거나 같은 경우
			if (prjSM < currentEM || (prjSM == currentEM && prjSD <= currentED)) {
				// 종료 일자가 늦을수록 종료 일자를 갱신
				if (prjEM > nextEM || (prjEM == nextEM && prjED > nextED)) {
					nextEM = prjEM;
					nextED = prjED;
					
					// 새로운 프로젝트 종료 일자가 12월이라면 프로젝트 찾기 종료
					if (nextEM == 12) {
						answer = prjCnt + 1;
						return;
					}
				}
			// 시작 일자가 현재 프로젝트 종료 일자보다 느릴 경우
			} else {
				// 시작 일자가 다음 프로젝트 종료 일자보다 느릴 경우
				if (nextEM < prjSM || (nextEM == prjSM && nextED < prjSD)) {
					answer = 0;
					return;
				}
				
				// 새로운 프로젝트를 실행
				currentEM = nextEM;
				currentED = nextED;
				prjCnt++;
				i--;
				
				// 새로운 프로젝트 종료 일자가 12월이라면 프로젝트 찾기 종료
				if (currentEM == 12) {
					answer = prjCnt;
				}
			}
		}
		
		// 새로운 프로젝트 종료 일자가 12월이라면 프로젝트 찾기 종료
		if (nextEM == 12) {
			answer = ++prjCnt;
		}
	}
}