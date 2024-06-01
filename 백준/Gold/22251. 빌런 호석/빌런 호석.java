import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, K, P, answer;
    static String X;
    static int[] LEDs;
    static boolean[][] origin;

    static boolean[][] numbers = {
            {true, true, true, false, true, true, true},
            {false, false, true, false, false, true, false},
            {true, false, true, true, true, false, true},
            {true, false, true, true, false, true, true},
            {false, true, true, true, false, true, false},
            {true, true, false, true, false, true, true},
            {true, true, false, true, true, true, true},
            {true, false, true, false, false, true, false},
            {true, true, true, true, true, true, true},
            {true, true, true, true, false, true, true}
    };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        answer = 0;
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        X = st.nextToken();
        origin = new boolean[K][7];

        origin = setNumberArr(X);
        checkNumber();

        System.out.println(answer);
    }

    private static boolean[][] setNumberArr(String s) {
        boolean[][] arr = new boolean[K][7];

        for (int i = 0; i < K; i++) {
            if (i < K - s.length()) {
                arr[i] = numbers[0];
            } else {
                int number = Character.getNumericValue(s.charAt(i - K + s.length()));
                arr[i] = numbers[number];
            }
        }

        return arr;
    }

    private static void checkNumber() {
        for (int i = 1; i <= N; i++) {
            if (i == Integer.parseInt(X)) {
                continue;
            }
            if (isDigit(i)) {
                answer++;
            }
        }
    }

    private static boolean isDigit(int number) {
        boolean[][] checkNumber = setNumberArr(String.valueOf(number));
        int changeCnt = 0;
        for (int i = 0; i < K; i++) {
            for (int j = 0; j < 7; j++) {
                if (checkNumber[i][j] != origin[i][j]) {
                    changeCnt++;
                    if (changeCnt > P) {
                        return false;
                    }
                }
            }
        }

        return true;
    }
}
