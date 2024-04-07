import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        int N = Integer.parseInt(br.readLine());
        int[] numbers = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
        	numbers[i] = Integer.parseInt(st.nextToken());
        }
        
        long answer = 0;
        int lt = 0;
        int rt = 0;
        Set<Integer> set = new HashSet<>();
        
        while (rt < N) {
        	int num = numbers[rt];
        	if (set.contains(num)) {
        		answer += rt - lt;
        		set.remove(numbers[lt++]);
        		continue;
        	}
        	set.add(num);
        	rt++;
        }
        answer += (long)(rt - lt + 1) * (long)(rt - lt) / 2;
        
        System.out.println(answer);
    }
}