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
		int arr[] = new int [26];
		int max1 = -1;
		int max2 = -1;
		int check = -1;
		
		for(int i=0; i<s.length(); i++) {
			int n = (int)s.charAt(i);
			
			if(n < 91) {
				n -= 65;
			} else {
				n -= 97;
			}
			arr[n]++;
		}
		
		for(int i=0; i<26; i++) {
			if(max1 < arr[i]) {
				max1 = arr[i];
				check = i;
			}
		}
		
		for(int i=0; i<26; i++) {
			if(i != check && max2 < arr[i]) {
				max2 = arr[i];
			}
		}
		
		if(max1 == max2) {
			bw.write("?\n");
		} else
			bw.write((char)(check+65) + "\n");
		
		bw.flush();
		bw.close();
		br.close();
	}
}