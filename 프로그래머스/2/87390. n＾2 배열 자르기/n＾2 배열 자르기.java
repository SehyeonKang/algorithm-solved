class Solution {
    public int[] solution(int n, long left, long right) {
        int[] answer = new int[Long.valueOf(right - left).intValue() + 1];
        
        int row = Long.valueOf(left / n + 1L).intValue();
        int col = Long.valueOf(left % n + 1L).intValue();
        int cnt = 0;
        
        for (int i = row; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == row && j < col) {
                    continue;
                }

                int num = 0;
                if (i >= j) {
                    num = i;
                } else {
                    num = j;
                }
                answer[cnt++] = num;

                if (cnt > right - left)
                    return answer;
            }
        }
        return answer;
    }
}

/*
1 2 3 4 5
2 2 3 4 5
3 3 3 4 5
4 4 4 4 5
5 5 5 5 5
6 / 5
*/