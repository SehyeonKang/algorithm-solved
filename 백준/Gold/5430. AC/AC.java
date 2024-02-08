
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int cases = Integer.parseInt(br.readLine());

        for (int i = 0; i < cases; i++) {
            String p = br.readLine();
            int n = Integer.parseInt(br.readLine());
            String sArr = br.readLine();
            char[] pArr = p.toCharArray();
            st = new StringTokenizer(sArr, "[],");
            Deque<String> deque = new LinkedList<>();
            int size;
            boolean isRight = true;
            boolean error = false;

            for (int j = 0; j < n; j++) {
                deque.offer(st.nextToken());
            }

            size = deque.size();
            for (int j = 0; j < pArr.length; j++) {
                if (pArr[j] == 'R') {
                    isRight = !isRight;
                } else if (pArr[j] == 'D') {
                    if (size == 0) {
                        error = true;
                        break;
                    }
                    if (isRight) {
                        deque.poll();
                    } else {
                        deque.pollLast();
                    }
                    size--;
                }
            }

            if (error) {
                sb.append("error").append("\n");
                continue;
            }


            size = deque.size();
            sb.append("[");
            if (isRight) {
                for (int j = 0; j < size; j++) {
                    sb.append(deque.poll());

                    if (j < size - 1) {
                        sb.append(",");
                    }
                }
            } else {
                for (int j = 0; j < size; j++) {
                    sb.append(deque.pollLast());

                    if (j < size - 1) {
                        sb.append(",");
                    }
                }
            }
            sb.append("]\n");
        }

        System.out.println(sb);

    }
}