import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        int N = Integer.parseInt(br.readLine());
        int answer = 0;
        int[] buildings = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
        	buildings[i] = Integer.parseInt(st.nextToken());
        }
        
        for (int i = 0 ; i < N; i++) {
        	int lt = i;
        	int rt = i ;
        	int height = buildings[i];
        	int cnt = 0;
        	while (lt-- > 0) {
        		double gap = height - buildings[lt];
        		double tmp = gap / (i - lt);
        		boolean flag = true;
        		for (int j = lt + 1; j < i; j++) {
        			if (buildings[j] >= buildings[lt] + tmp * (j - lt)) {
        				flag = false;
        				break;
        			}
        		}
        		if (flag) {
        			cnt++;
        		}
        	}
        	while (rt++ < N - 1) {
        		double gap = buildings[rt] - height;
        		double tmp = gap / (rt - i);
        		boolean flag = true;
        		for (int j = i + 1; j < rt; j++) {
        			if (buildings[j] >= buildings[i] + tmp * (j - i)) {
        				flag = false;
        				break;
        			}
        		}
        		if (flag) {
        			cnt++;
        		}
        	}
        	answer = Math.max(answer, cnt);
        }
        
        System.out.println(answer);
    }
}