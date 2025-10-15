package week05.soyul;

import java.util.ArrayDeque;

public class LicoSy {
  public int solution(String[] board) {
    int n = board.length;
    int m = board[0].length();
    int sx = -1;//시작위치
    int sy = -1;//시작위치
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if(board[i].charAt(j) == 'R') {
          sx = i;
          sy = j;
        }
      }
    }
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};

    boolean[][] visited = new boolean[n][m];
    ArrayDeque<int[]> q = new ArrayDeque<>();
    q.offer(new int[]{sx, sy,0});//x y 이동횟수
    visited[sx][sy] = true;//시작위치

    while (!q.isEmpty()) {
      int[] cur = q.poll();//현재위치꺼내기
      int x = cur[0];
      int y = cur[1];
      int d = cur[2];
      if(board[x].charAt(y) == 'G') {
        return d;
      }
      for (int dir = 0; dir < 4; dir++) {//4방향 미끄러지기
        int nx = x;
        int ny = y;
        while (true) {
          int tx = nx + dx[dir];
          int ty = ny + dy[dir];
          if(tx<0||tx>=n||ty<0||ty>=m) break;
          if(board[tx].charAt(ty) == 'D') break;
          //더 전진
          nx = tx;
          ny = ty;
        }
        //정지 지점이 아직 방문 안했다면 큐 추가
        if(!visited[nx][ny]) {
          visited[nx][ny] = true;
          q.offer(new int[]{nx, ny, d+1});
        }

      }

    }
    return -1;

  }

}
