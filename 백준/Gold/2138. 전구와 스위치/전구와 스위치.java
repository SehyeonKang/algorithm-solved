import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int answer1 = 0;
        int answer2 = 0;
        String originString = br.readLine();
        String targetString = br.readLine();
        boolean[] origin = new boolean[N];
        boolean[] target = new boolean[N];
        for (int i = 0; i < N; i++) {
            if (originString.charAt(i) == '0') {
                origin[i] = true;
            } else {
                origin[i] = false;
            }
            if (targetString.charAt(i) == '0') {
                target[i] = true;
            } else {
                target[i] = false;
            }
        }

        answer1++;
        origin[0] = !origin[0];
        origin[1] = !origin[1];

        for (int i = 1; i < N - 1; i++) {
           if (origin[i - 1] != target[i - 1]) {
               answer1++;
               origin[i - 1] = !origin[i - 1];
               origin[i] = !origin[i];
               origin[i + 1] = !origin[i + 1];
           }
        }

        if (origin[N - 2] != target[N - 2]) {
            answer1++;
            origin[N - 2] = !origin[N - 2];
            origin[N - 1] = !origin[N -1];
        }

        if (origin[N - 1] != target[N - 1]) {
            answer1 = -1;
        }

        for (int i = 0; i < N; i++) {
            if (originString.charAt(i) == '0') {
                origin[i] = true;
            } else {
                origin[i] = false;
            }
        }

        for (int i = 1; i < N - 1; i++) {
            if (origin[i - 1] != target[i - 1]) {
                answer2++;
                origin[i - 1] = !origin[i - 1];
                origin[i] = !origin[i];
                origin[i + 1] = !origin[i + 1];
            }
        }

        if (origin[N - 2] != target[N - 2]) {
            answer2++;
            origin[N - 2] = !origin[N - 2];
            origin[N - 1] = !origin[N - 1];
        }

        if (origin[N - 1] != target[N - 1]) {
            answer2 = -1;
        }

        if (answer1 == -1 && answer2 == -1) {
            System.out.println(-1);
        } else if (answer1 == -1) {
            System.out.println(answer2);
        } else if (answer2 == -1) {
            System.out.println(answer1);
        } else {
            System.out.println(Math.min(answer1, answer2));
        }
    }
}
