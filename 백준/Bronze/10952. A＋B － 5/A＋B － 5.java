import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		ArrayList<Integer> arr = new ArrayList<Integer>();
		int cnt = 0;
		String s;
		
		while(true) {
			s = bf.readLine();
			StringTokenizer st = new StringTokenizer(s);
			arr.add(Integer.parseInt(st.nextToken()));
			arr.add(Integer.parseInt(st.nextToken()));
			
			if(arr.get(cnt*2) == 0 && arr.get(cnt*2+1) == 0)
				break;
			
			cnt++;
		}
		
		for(int i=0; i<cnt; i++)
			bw.write(arr.get(i*2)+arr.get(i*2+1) + "\n");
		
		bw.flush();
		bw.close();
	}
}