package week02.soyul;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (r,c): n개 포인트, 1~n 다른 번호
 * 운송경로:m개
 * 로봇 개수:x, 0초에 동시 출발
 * 1초마다 r c중 하나만 감소/증가
 * 최단경로 이동. -> 가능성 여러개면 r먼저
 * 충돌: 같은 좌표
 * 출력: 충돌횟수
 * 입력: points=n개좌표 routes=로봇경로
 */
public class ConflictDangerFindSy {
  public int solution(int[][] points, int[][] routes) {
    int answer = 0;
    //로봇 개수
    int m = routes.length;
    //로봇마다 시간별 위치 저장 리스트 history.get(i).get(i) = i번 로봇이 t초에 있는 좌표
    //필요한 만큼 메모리 동적 확장 - 노드 수 많을 때 유리, 간선 위주 dfs bfs
    List<List<int[]>> history = new ArrayList<>();
    //가장 오래 움직이는 로봇 총 시간
    int maxTime = 0;

    for (int i = 0; i < m; i++) {
      //i번째 로봇 경로 저장
     List<int[]> path = new ArrayList<>();
     //시작포인트
     int[] start= points[routes[i][0]-1];
     path.add(new int[]{start[0],start[1]});
     // 이전 다음 포인트 이동 준비
      for (int j = 1; j < routes[i].length; j++) {
        int[] prev = points[routes[i][j-1]-1];
        int[] next = points[routes[i][j]-1];
        int curX = prev[0];
        int curY = prev[1];
        //r행부터 맞추기
        while (curX != next[0]) {
          curX +=(curX<next[0])?1:-1;
          path.add(new int[]{curX,curY});
        }
        while(curY != next[1]){
          curY +=(curY<next[1])?1:-1;
          path.add(new int[]{curX,curY});
        }
      }
      //전체 경로 저장
      history.add(path);
      //가장 긴 시간 갱신 - 모든 로봇 같은 시간축 비교
      maxTime = Math.max(maxTime,path.size());
    }
    //t 시간에 몇대 충돌했는지 확인
    for (int t = 0; t < maxTime; t++) {
      //t초에 그 위치에 있는 로봇수 세기
      Map<String,Integer> counter = new HashMap<>();
      for (int i = 0; i < m; i++) {
        List<int[]> path = history.get(i);
        if (t < path.size()) {//아직 로봇 움직이는 중
          int[] pos = path.get(t);//t초 좌표
          String key = pos[0]+","+pos[1];//r,c가 키
          //이미 그 좌표에 로봇이 있으면 그 로봇 수 가져오고 없으면 0+1
          counter.put(key,counter.getOrDefault(key,0)+1);
        }
      }
      //충돌하면 !
      for (Integer cnt : counter.values()) {
        if (cnt >= 2) {
          answer++;
        }
      }
    }

    return answer;

  }
}
