class Solution {
    public int[] solution(String s) {
        int removedZeroCount = 0;
        int parseCount = 0;

        while (!s.equals("1")) {
            int zeroCount = 0;
            for (char c : s.toCharArray()) {
                if (c == '0') {
                    zeroCount++;
                }
            }
            removedZeroCount += zeroCount;
            int parsedLength = s.length() - zeroCount;
            s = Integer.toBinaryString(parsedLength);
            parseCount++;
        }

        return new int[]{parseCount, removedZeroCount};
    }
}