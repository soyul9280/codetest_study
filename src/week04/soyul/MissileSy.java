package week04.soyul;

import java.util.Arrays;

public class MissileSy {
  public int solution(int[][] targets) {
    int answer = 0;

    //e기준 오름차순 정렬-(1,4), (2,6), (5,7)이 있으면 (1,4)가 제일 빨리 끝나니까 x=3쯤 쏘는 게 이득
    Arrays.sort(targets, (o1, o2) -> o1[1] - o2[1]);

    //직전에 쏜 미사일 x좌표
    int before = 0;
    //미사일 구간 검사
    for(int i=0;i<targets.length;i++){
      //시작점보다 요격 발사시점이 전이면 현재구역 요격x
      if(before <= targets[i][0]){
        //현재 구간 끝점 전에 쏘기
        before = targets[i][1];
        answer++;
      }
    }

    return answer;
  }
}
