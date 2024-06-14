import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        while (true) {
            String input = br.readLine();
            if (input.equals("end")) {
                break;
            }

            char[][] board = new char[3][3];
            int cnt = 0;
            int emptyCnt = 0;
            int xCount = 0;
            int oCount = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    board[i][j] = input.charAt(cnt++);
                    if (board[i][j] == '.') {
                        emptyCnt++;
                    } else if (board[i][j] == 'X') {
                        xCount++;
                    } else {
                        oCount++;
                    }
                }
            }

            int bingo = 0;
            int rowBingo = 0;
            int colBingo = 0;
            int oBingo = 0;
            int xBingo = 0;
            for (int i = 0; i < 3; i++) {
                char first = board[i][0];
                if (first != '.' && first == board[i][1] && first == board[i][2]) {
                    bingo++;
                    rowBingo++;
                    if (first == 'O') {
                        oBingo++;
                    } else {
                        xBingo++;
                    }
                }
            }
            for (int i = 0; i < 3; i++) {
                char first = board[0][i];
                if (first != '.' && first == board[1][i] && first == board[2][i]) {
                    bingo++;
                    colBingo++;
                    if (first == 'O') {
                        oBingo++;
                    } else {
                        xBingo++;
                    }
                }
            }
            if (board[0][0] != '.' && board[0][0] == board[1][1] && board[0][0] == board[2][2]) {
                bingo++;
                if (board[0][0] == 'O') {
                    oBingo++;
                } else {
                    xBingo++;
                }
            }
            if (board[0][2] != '.' && board[0][2] == board[1][1] && board[0][2] == board[2][0]) {
                bingo++;
                if (board[0][2] == 'O') {
                    oBingo++;
                } else {
                    xBingo++;
                }
            }

            if (!(oCount == xCount || oCount + 1 == xCount)) {
                sb.append("invalid\n");
                continue;
            }
            if (bingo == 2 && rowBingo < 2 && colBingo < 2) {
                sb.append("valid\n");
                continue;
            }
            if (bingo == 1) {
                if (oBingo == 1 && oCount + 1 == xCount) {
                    sb.append("invalid\n");
                    continue;
                }
                if (xBingo == 1 && xCount == oCount) {
                    sb.append("invalid\n");
                    continue;
                }
                sb.append("valid\n");
                continue;
            }
            if (bingo == 0 && emptyCnt == 0) {
                sb.append("valid\n");
                continue;
            }
            sb.append("invalid\n");
        }
        System.out.println(sb);
    }
}
