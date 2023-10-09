import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String s = br.readLine();
		int arr[] = new int[26];
		for(int i=0; i<26; i++)
			arr[i] = -1;
		
		for(int i=0; i<s.length(); i++) {
			int asc = (int)s.charAt(i) - 97;
			if(arr[asc] == -1)
				arr[asc] = i;
		}
		
		for(int i=0; i<26; i++)
			bw.write(arr[i] + " ");
			
		bw.flush();
		bw.close();
		br.close();
	}
}