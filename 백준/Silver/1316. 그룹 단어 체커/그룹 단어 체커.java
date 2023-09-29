import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(br.readLine());
		int chk = 0;
		int result = n;
		
		char chrCheck = ' ';
		
		for(int i=0; i<n; i++) {
			String s = br.readLine();
			int arr[] = new int[26];
			
			for(int j=0; j<s.length(); j++) {		
				if(j == 0)
					chrCheck = s.charAt(j);
				
				if(chrCheck != s.charAt(j)) {
					chk = 1;
				} else
					chk = 0;
					
				if(chk == 1 && arr[(int)s.charAt(j) - 97] == 1) {
					result --;
					break;
				}
				
				arr[(int)s.charAt(j) - 97] = 1;
				chrCheck = s.charAt(j);
			}
		}
		
		bw.write(result +"");
		
		bw.flush();
		bw.close();
		br.close();
	}
}