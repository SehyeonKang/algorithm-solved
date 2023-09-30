import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int arr[] = new int[n];
		int sum = 0;
		
		for(int i=0; i<n; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(i == j)
					break;
				for(int k=0; k<n; k++) {
					if(i == k || j == k)
						break;
					int tmp = arr[i] + arr[j] + arr[k];
					
					if(tmp <= m && tmp > sum)
						sum = tmp;
				}
			}
		}
		
		bw.write(sum+"");
		
		bw.flush();
		bw.close();
		br.close();
	}
}