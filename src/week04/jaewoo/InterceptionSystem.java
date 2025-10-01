import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        int answer = 0;
        Arrays.sort(targets, (a, b) -> Integer.compare(a[1], b[1]));
        int lastShot = -1;
        
        for (int[] cur : targets) {
            if (lastShot <= cur[0]) {
                answer++;
                lastShot = cur[1];
            }
        }
        return answer;
    }
}

/*
겹치는 부분의 최소 개수를 구한다.
구간 문제 (Interval)
*/