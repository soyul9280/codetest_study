import java.util.*;

class Solution {
    public int[] solution(int[] sequence, int k) {
        int n = sequence.length;
        int start = 0;
        int end = 0;
        int sum = sequence[0];
        
        int minLen =  Integer.MAX_VALUE;
        int[] answer = new int[2];
        
        while (start < n && end < n) {
            if (sum == k) {
                int len = end - start + 1;
                if (len < minLen) {
                    minLen = len;
                    answer[0] = start;
                    answer[1] = end;
                }
                sum -= sequence[start++];
            } else if (sum < k) {
                end++;
                if (end < n) sum += sequence[end];
            } else {
                sum -= sequence[start++];
            }
        }
        return answer;
    }
}
