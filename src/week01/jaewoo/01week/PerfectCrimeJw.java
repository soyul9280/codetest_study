import java.util.*;

class Solution {
    private static final int INF = 121;
    
    public int solution(int[][] info, int n, int m) {
        int[][] dp = new int[info.length + 1][m];
        Arrays.stream(dp)
            .forEach(each -> Arrays.fill(each, INF));
        
        dp[0][0] = 0; // thiefB가 훔친 개수는 현재 0이고, A의 값도 0으로 초기화
        for (int round = 1; rount <= info.size; round++) {
            int theifA = info[round-1][0];
            int theifB = info[round-1][1];
            
            for (int j = 0; j < m; j++) {
                // A가 훔쳤을 떄
                
                // B가 훔쳤을 때
                
            }
        }
        return 0;
    }
}