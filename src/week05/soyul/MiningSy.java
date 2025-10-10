package week05.soyul;

import java.util.ArrayList;
import java.util.List;

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
     * ->살패
     * 피로도 높은 구간에 다이아쓰기
     */
    List<int[]> list = new ArrayList<>();
    //광물 개수랑 곡괭이 개수에 따라 시도할 수 있는 횟수 달라짐
    int len=Math.min(minerals.length,(picks[0]+picks[1]+picks[2])*5);

    //5개 끊어서 피로도 구간 계산
    for (int i = 0; i < len; i+=5) {
      //이번 구간에서 곡괭이로 캤을 때 총 피로도 담을 변수
      int dia =0;
      int iron=0;
      int stone=0;
      for (int j = i; j < i + 5&&j<len; j++) {
        String m = minerals[j];
        if (m.equals("diamond")) {
          dia += 1;
          iron += 5;
          stone += 25;
        } else if (m.equals("iron")) {
          dia += 1;
          iron += 1;
          stone += 5;
        } else {
          dia += 1;
          iron += 1;
          stone += 1;
        }
      }
      //피로도 저장
      list.add(new int[]{dia,iron,stone});
      }
    //피로도 큰 순서로 정렬: 돌곡괭이로 캤을 때 피로도 큰 순서로 정렬
    list.sort((a,b)->b[2]-a[2]);
    int answer = 0;
    //정렬했으니 큰 구간 부터 캐기 시작
    for (int[] section : list) {
      if (picks[0] > 0) {
        answer += section[0];
        picks[0]--;
      } else if (picks[1] > 0) {
        answer += section[1];
        picks[1]--;
      } else if (picks[2] > 0) {
        answer += section[2];
        picks[2]--;
      }
    }
    return answer;
    }
}
