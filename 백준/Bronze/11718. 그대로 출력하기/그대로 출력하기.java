
import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        String s = br.readLine();
        while (!(s == null)) {
            sb.append(s + "\n");
            s = br.readLine();
        }

        bw.write(sb.toString());
        bw.flush();
    }
}