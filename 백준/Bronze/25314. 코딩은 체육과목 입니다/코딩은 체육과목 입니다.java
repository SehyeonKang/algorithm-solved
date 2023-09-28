
import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int recur = n / 4;
        for (int i = 0; i < recur; i++) {
            sb.append("long ");
        }
        sb.append("int");

        bw.write(sb.toString());
        bw.flush();
    }
}