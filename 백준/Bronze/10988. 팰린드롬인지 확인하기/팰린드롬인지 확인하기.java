
import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        String s = br.readLine();
        StringBuilder sbReverse = new StringBuilder(s);
        sbReverse.reverse();
        String reverseString = sbReverse.toString();

        if (s.equals(reverseString)) {
            sb.append(1);
        } else
            sb.append(0);

        bw.write(sb.toString());

        bw.close();
        br.close();
    }
}