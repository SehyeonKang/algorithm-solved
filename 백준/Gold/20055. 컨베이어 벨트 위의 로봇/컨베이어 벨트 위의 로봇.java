import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    
    static int N, K;
    static Belt[] belts;
    
    // 벨트의 정보를 저장하는 클래스
    static class Belt {
        int robot, hp;

        public Belt(int robot, int hp) {
            this.robot = robot;
            this.hp = hp;
        }
        
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        belts = new Belt[N * 2];
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N * 2; i++) {
            belts[i] = new Belt(0, Integer.parseInt(st.nextToken()));
        }
        
        // 내구도가 0인 칸의 개수가 K개 이상일 때까지 반복
        int count = 1;
        while (true) {
            rotateBelt();
            
            if (K <= 0) {
                break;
            }
            count++;
        }
        
        System.out.println(count);
    }
    
    private static void rotateBelt() {
        // 벨트 회전
        Belt temp = belts[0];
        for (int i = 1; i < N * 2; i++) {
            Belt swap = belts[i];
            belts[i] = temp;
            temp = swap;
        }
        belts[0] = temp;
        belts[N - 1].robot = 0;
        
        // 로봇 이동
        for (int i = N - 2; i > 0; i--) {
            Belt cur = belts[i];
            Belt next = belts[i + 1];
            if (cur.robot == 1 && next.robot == 0 && next.hp > 0) {
                cur.robot = 0;
                next.robot = 1;
                next.hp--;
                
                if (next.hp == 0) {
                    K--;
                }
            }
        }
        belts[N - 1].robot = 0;
        
        // 올리는 위치에 로봇 올리기
        Belt first = belts[0];
        if (first.hp > 0) {
            first.robot = 1;
            first.hp--;
            
            if (first.hp == 0) {
                K--;
            }
        }
    }
}