class Solution {
public int solution(int[][] info, int n, int m) {
        int ATotal = 0;
        int BTotal = 0;
        
        Arrays.sort(info, (costA, costB) -> {
          // B 배열 수의 차
          int sub = costB[1] - costA[1];
          if (sub == 0) {
              return costA[0] - costB[0];
          }
          return sub;
        });
        
        for (int[] leftOver : info) {
            int thiefA = leftOver[0];
            int thiefB = leftOver[1];
            // 일단 B가 최대한 훔쳐야 됨.
            if (BTotal + thiefB < m) {
                BTotal += thiefB;
                continue;
            }
            
            // 만약 잡힐 수 밖에 없다면 -1 리턴
            if (ATotal + thiefA >= n) {
                return -1;
            }
            
            ATotal += thiefA;
        }        
        
        return ATotal;
    }
}