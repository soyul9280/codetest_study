class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int left = 1;
        int right = 100_000;        
        int answer = 0 ;
        while (left <= right) {
            int mid = (left + right) / 2;
            
            if (canFinish(diffs, times, limit, mid)) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        
        return answer;
    }
    
    private boolean canFinish(int[] diffs, int[] times, long limit, int level) {
        long totalTime = 0;
        
        for (int i = 0; i < diffs.length; i++) {
            if (diffs[i] <= level) {
                totalTime += times[i];
            } else {
                int requiredRound = diffs[i] - level;
                int roundTime = times[i-1] + times[i];
                totalTime += (roundTime * requiredRound) + times[i];
            }
            
            if (totalTime > limit) return false;
        }
        
        return totalTime <= limit;
    }
}
