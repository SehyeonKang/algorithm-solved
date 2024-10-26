import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < t; tc++) {
            int n = Integer.parseInt(br.readLine());
            ArrayDeque<Point> queue = new ArrayDeque<>();
            boolean[] visited = new boolean[n];
            boolean flag = false;

            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            Point house = new Point(x, y);
            queue.offer(house);

            List<Point> points = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                x = Integer.parseInt(st.nextToken());
                y = Integer.parseInt(st.nextToken());
                points.add(new Point(x, y));
            }
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            Point festival = new Point(x, y);

            while (!queue.isEmpty()) {
                Point p = queue.poll();

                if (Math.abs(p.x - festival.x) + Math.abs(p.y - festival.y) <= 1000) {
                    sb.append("happy").append("\n");
                    flag = true;
                    break;
                }

                for (int i = 0; i < n; i++) {
                    if (!visited[i]) {
                        int nx = points.get(i).x;
                        int ny = points.get(i).y;

                        if (Math.abs(p.x - nx) + Math.abs(p.y - ny) <= 1000) {
                            queue.offer(new Point(nx, ny));
                            visited[i] = true;
                        }
                    }
                }
            }

            if (!flag)
                sb.append("sad").append("\n");
        }

        System.out.println(sb);
    }

}
