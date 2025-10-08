package week03.soyul;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 세로n 가로m
 * 입력: land[][] 석유 땅, 석유위치
 * 출력: 시추관 하나로 가장 많은 석유량
 */
public class OilSituSy {
  static int n;
  static int m;
  //시추관 위치별 석유량 - 얻는 석유 총량 누적 배열
  static int[] oil;
  public int solution(int[][] land) {
    int answer = 0;

    //세로
    n = land.length;
    //가로 -> 석유 유무(0:없음 1: 있음)
    m = land[0].length;
    oil = new int[m];
    boolean[][] visited = new boolean[n][m];

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        //아직 방문안했고 석유있는 지역이면 그 칸으로 연결된 석유bfs처리
        if(land[i][j]==1&&visited[i][j]==false) {
          bfs(land, visited, i, j);
        }
      }
    }
    answer = Arrays.stream(oil).max().getAsInt();
    System.out.println(answer);
    return answer;
  }

  //그 칸이 속한 석유 덩어리 전체를 bfs로 처리
  public void bfs(int[][] land, boolean[][] visited, int i, int j) {
    //bfs용 큐 생성
    Queue<int[]> q = new LinkedList<>();
    //시작 칸을 큐에 넣음
    q.add(new int[]{i, j});
    visited[i][j] = true;
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};

    //현재 덩어리 포함
    int count = 1;
    //연결된 열 번호 모으는 집합
    Set<Integer> set = new HashSet<>();

    while(!q.isEmpty()) {
      int[] now = q.poll();
      //이 덩어리는 닿아있음 - 열저장
      set.add(now[1]);
      //4방향 확인
      for (int k = 0; k < 4; k++) {
        int nx = now[0] + dx[k];
        int ny = now[1] + dy[k];
        //격자 밖 패스
        if (!checkRange(nx, ny)) {
          continue;
        }
        //석유있고 방문전이라면
        if(land[nx][ny]==1&&visited[nx][ny]==false) {
          //큐에 넣고 방문처리 후 덩어리 크기 더하기
          q.add(new int[]{nx, ny});
          visited[nx][ny] = true;
          count +=1;
        }
      }
    }
    for (Integer index : set) {
      oil[index] +=count;
    }
  }
  public boolean checkRange(int x, int y) {
    if (x < 0 || x >= n || y < 0 || y >= m) {
      return false;
    }
    return true;
  }
}
