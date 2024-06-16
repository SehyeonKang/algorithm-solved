import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int R, C, answer;
    static char[][] map;
    static boolean[][] visited;
    static Queue<Point> playerQ, fireQ;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, -1, 0, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        visited = new boolean[R][C];
        playerQ = new ArrayDeque<>();
        fireQ = new ArrayDeque<>();

        for (int i = 0; i < R; i++) {
            String s = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = s.charAt(j);
                if (map[i][j] == 'J') {
                    playerQ.offer(new Point(i, j));
                } else if (map[i][j] == 'F') {
                    fireQ.offer(new Point(i, j));
                }
            }
        }

        bfs();

        if (answer == 0) {
            System.out.println("IMPOSSIBLE");
        } else {
            System.out.println(answer);
        }
    }

    private static void bfs() {
        int time = 1;

        while (!playerQ.isEmpty()) {
            int playerQSize = playerQ.size();
            int fireQSize = fireQ.size();

            for (int i = 0; i < playerQSize; i++) {
                Point cur = playerQ.poll();
                int r = cur.x;
                int c = cur.y;

                if (map[r][c] == 'F') {
                    continue;
                }

                if (r == 0 || r == R - 1 || c == 0 || c == C - 1) {
                    answer = time;
                    return;
                }

                for (int j = 0; j < 4; j++) {
                    int nr = r + dr[j];
                    int nc = c + dc[j];
                    if (map[nr][nc] == '.' && !visited[nr][nc]) {
                        playerQ.offer(new Point(nr, nc));
                        visited[nr][nc] = true;
                    }
                }
            }

            for (int i = 0; i < fireQSize; i++) {
                Point cur = fireQ.poll();
                int r = cur.x;
                int c = cur.y;

                for (int j = 0; j < 4; j++) {
                    int nr = r + dr[j];
                    int nc = c + dc[j];
                    if (isIn(nr, nc) && (map[nr][nc] == '.' || map[nr][nc] == 'J')) {
                        map[nr][nc] = 'F';
                        fireQ.offer(new Point(nr, nc));
                    }
                }
            }

            time++;
        }
    }

    private static boolean isIn(int r, int c) {
        return r >= 0 && r < R && c >= 0 && c < C;
    }
}
