package week01.soyul;

/**
 * 조건: m명 사람 이상이어야 서버 +1
 * 이용자: n x m명 이상 (n+1)x m명 미만 -> 최소 n개 서버
 * 서버는 k시간동안 운영하고 그 이후 반납
 * 모든 이용자가 사용하기위해 서버를 최소 몇번 증설해야할까?
 *
 */
public class ServerAddCountSy {
  public int solution(int[] players, int m, int k){
    int n = players.length;
    //각 시간에 종료될 서버수
    int[] end = new int[n + k +1];
    //가동 중인 서버 수
    int active =0;
    //총 증설횟수
    int answer =0;
    for (int i = 0; i < n; i++) {
      if (end[i] != 0) {
        active -= end[i];
      }
      //필요한 서버수
      int x = players[i]/m;

      if (active < x) {
        //증설할 서버 수
        int y = x - active;
        answer +=y;
        active +=y;
        end[i+k]=y;
      }
    }

    return answer;
  }

}

/**
 * 처음 시도: 시간대마다 만료되는 서버 수 고려 안함. 만료되는 서버가 여러 시점인 경우 처리 불가
 *
 *  int size = players.length;
 *     //증설할 때 시간
 *     int time = 0;
 *     //증설 횟수
 *     int answer =0;
 *     //증설된 서버 수
 *     int y =0;
 *
 *     for (int i = 0; i < size + 1; i++) {
 *       if (y != 0 && i ==time +k) {
 *         y--;
 *       }
 *       if (players[i] != 0 && players[i] % m ==0) {
 *         //필요한 서버 수
 *         int z=players[i]/m;
 *         if (y < z) {
 *           answer= answer + z-y;
 *         }
 *         time = i;
 *       }
 *     }
 *     return answer;
 */