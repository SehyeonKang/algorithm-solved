import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        int num[] = new int[n];

        for (int i = 0; i < n; i++) {
            num[i] = Integer.parseInt(br.readLine());
        }

        ArrayList<String> results = new ArrayList<>();
        results = solve(num);

        for (String result : results) {
            sb.append(result).append("\n");
        }

        System.out.println(sb);

        br.close();
    }

    public static ArrayList<String> solve(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        ArrayList<String> result = new ArrayList<>();
        int num = 2; // 스택에 넣을 숫자
        int check = 0; // 가능 여부 체크 (0: 가능, 1: 불가능)

        stack.push(1);
        result.add("+");
        for (int i = 0; i < arr.length; i++) {
            while (true){
                if (!stack.empty()) {
                    if (stack.peek() == arr[i]) {
                        stack.pop();
                        result.add("-");
                        break;
                    } else {
                        if (num > arr.length) {
                            check = 1;
                            result.clear();
                            result.add("NO");
                            break;
                        }
                    }
                }

                stack.push(num);
                result.add("+");
                num ++;
            }

            if (check == 1) {
                break;
            }
        }

        return result;
    }
}