package week01.soyul;

public class ForkLiftCrane2Sy {
  int n;
  int m;
  int answer;
  int[] dx = {-1,1,0,0};
  int[] dy = {0,0,-1,1};
  boolean[][] visited;
  int[][] graph;

  public int solution(String[] storage, String[] requests) {
    n = storage.length;
    m = storage[0].length();
    String[][] now = new String[n][m];
    visited = new boolean[n][m];
    graph = new int[n][m];
    answer = now.length;

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        String value = storage[i];
        now[i][j] = String.valueOf(value.charAt(j));
        graph[i][j] = 1;
      }
    }

    for (int i = 0; i < requests.length; i++) {
      if (requests[i].length() == 2) {
        playCrane(requests[i],now);
      }else{
        boolean a =playCar(requests[i],now);
      }
    }
    return answer;
  }

  private void playCrane(String request, String[][] now) {
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if(request.charAt(i) == now[i][j].charAt(0)) {
          answer--;
          graph[i][j] = 0;
        }
      }
    }
  }
  private boolean playCar(String request, String[][] now) {
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (graph[i][j] == 0) {
          return false;
        }
      }

      if(now[i][0].equals(request)&& graph[i][0]==1) {
        answer--;
        visited[i][0] = true;
        graph[i][0]=0;
        return true;
      }
      if(now[i][n-1].equals(request)) {
        answer--;
        visited[i][n-1] = true;
        return true;
      }
      if(now[0][i].equals(request)) {
        answer--;
        visited[0][i] = true;
        return true;
      }
      if(now[m-1][i].equals(request)) {
        answer--;
        visited[m-1][i] = true;
        return true;
      }

    }
  }


}
