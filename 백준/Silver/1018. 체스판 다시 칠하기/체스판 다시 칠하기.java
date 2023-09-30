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
		char arr[][] = new char[n][m];
		
		for(int i=0; i<n; i++) {
			String s = br.readLine();
			for(int j=0; j<m; j++)
				arr[i][j] = s.charAt(j);
		}
		
		int count = 65;
		
		for(int i=0; i<n-7; i++) {
			for(int j=0; j<m-7; j++) {
				int case1 = 0;
				int case2 = 0;
				for(int col=i; col<i+8; col++) {
					for(int row=j; row<j+8; row++) {
						if((col+row) % 2 == 0) {
							if(arr[col][row] != 'B')
								case1++;
						} else {
							if(arr[col][row] != 'W')
								case1++;
						}
						
						if((col+row) % 2 == 0) {
							if(arr[col][row] != 'W')
								case2++;
						} else {
							if(arr[col][row] != 'B')
								case2++;
						}
					}
				}
				
				if(case1 < count)
					count = case1;
				
				if(case2 < count)
					count = case2;
			}
		}
		
		bw.write(count + "");
		
		bw.flush();
		bw.close();
		br.close();
	}
}
