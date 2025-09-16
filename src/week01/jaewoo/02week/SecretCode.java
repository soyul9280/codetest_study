import java.util.*;

class Solution {
    
    private static final int SIZE = 5;
    private int count = 0;
    
    public int solution(int n, int[][] q, int[] ans) {
        backtrack(new ArrayList<>(), 1, n, q, ans);
        return count;
    }
    
    private void backtrack(List<Integer> current, int start, int n, int[][] q, int[] ans) {
        if (current.size() == SIZE) {
            if (isAvailable(current, q, ans)) {
                count++;
            }
            return;
        }
        
        for (int i = start; i <= n; i++) {
            current.add(i);
            backtrack(current, i + 1, n, q, ans);
            current.remove(current.size() -1);
        }
    }
    
    private boolean isAvailable(List<Integer> candidate, int[][]q, int[] ans) {
        for (int i = 0; i < q.length; i++) {
            int overlap = 0;
            for (int x : q[i]) {
                if (candidate.contains(x)) {
                    overlap++;
                }
            }
            if (overlap != ans[i]) {
                return false;
            }
        }
        return true;
    }
}

/*
1. 모든 후보 군을 만든다 (nC5)
2. 각 후보에 대해 모든 시도를 검사한다.
3. 시도 입력값과 후보가 겹치는 개수가 같으면 카운트
4. 모든 카운트 출력
*/