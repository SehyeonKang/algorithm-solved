import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String info = bf.readLine();
		String num = bf.readLine();
		StringTokenizer infoSt = new StringTokenizer(info);
		StringTokenizer numSt = new StringTokenizer(num);
		
		int n = Integer.parseInt(infoSt.nextToken());
		int x = Integer.parseInt(infoSt.nextToken());
		int arr[] = new int[n];
		
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(numSt.nextToken());
		}
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		for(int i=0; i<n; i++) {
			if(arr[i]<x)
				bw.write(arr[i] + " ");
		}
		
		bw.flush();
		bw.close();
	}
}