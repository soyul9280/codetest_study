package week01.soyul;

import java.util.Arrays;

/**
 * 설계
 * [i][0] 더한게 <n
 * [i][1] 더한게 <m
 * 일때 최소 a 값
 *
 * 못훔치면 -1
 */
public class PerfectCrimeSy {
  static final int INF = 100000;// 아직 안가본 상태가 0 이면 안훔친 경우와 구분이 안됨.
  public int solution(int[][] info, int n, int m) {
    int size= info.length; //물건 개수
    // dp[물건개수][b흔적]=a흔적, dp 0부터 시작이니까 +1
    int[][] dp = new int[size + 1][m];

    for (int i = 0; i <= size; i++) {
      Arrays.fill(dp[i], INF); //불가능한 숫자 넣어넣기
    }

    dp[0][0] = 0; //아직 아무것도 안훔침

    for (int i = 1; i <= size; i++) {
      int a = info[i - 1][0]; // a흔적 수
      int b = info[i - 1][1]; //b 흔적수

      for (int j = 0; j < m; j++) {
        //a가 훔칠 때, 새로 구해진 a흔적이 작으면 갱신
        dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + a);
        if (j + b < m) {
          //b가 훔칠 때, i-1개 물건을 훔쳤을 때보다 새로 구해진 a흔적이 작으면 갱신
          dp[i][j + b] = Math.min(dp[i][j + b], dp[i - 1][j]);
        }
      }
    }
    //j가 m 보다 작을 경우에 a흔적 가장 작은 값 찾기.
    int min = INF;
    for (int j = 0; j < m; j++) {
      min = Math.min(dp[size][j], min);
    }
    return min >= n ? -1 : min;
  }

}
