package week05.soyul;

/**
 * picks: 1<=다이아 철 돌 곡괭이 수<=5
 * minerals: 5<=다이아 철 돌<=50
 *
 * 출력: 최소한의 피로도
 *
 * 피로도:순서=다이아 철 돌
 * 다이아괭이: 1 1 1
 * 철갱이: 5 1 1
 * 돌괭이: 25 5 1
 * 최대 5번 사용 가능
 */
public class MiningSy {
  public int solution(int[] picks, String[] minerals) {
    /**
     * 다이아로 최대한 캐고 남은 철로 하는게 가장! 피로도 낮음.
     */
    int diaNum=picks[0];
    int ironNum=picks[1];
    int stoneNum=picks[2];
    int answer = 0;
    int countD=diaNum*5;
    int countI=ironNum*5;
    int countS=stoneNum*5;

    for (int i = 0; i < minerals.length; i++) {
      if (diaNum != 0&&countD!=0) {
        if (minerals[i].equals("diamond")) {
          answer +=1;
        }else if (minerals[i].equals("iron")) {
          answer +=1;
        }else answer +=1;
        countD--;
      } else if (ironNum != 0&&countI!=0) {
        if (minerals[i].equals("diamond")) {
          answer += 5;
        } else if (minerals[i].equals("iron")) {
          answer += 1;
        } else {
          answer += 1;
        }
        countI--;
      } else if(stoneNum != 0&&countS!=0) {
        if (minerals[i].equals("diamond")) {
          answer += 25;
        } else if (minerals[i].equals("iron")) {
          answer += 5;
        } else {
          answer += 1;
        }
        countS--;
      }
    }
    return answer;
  }
}
