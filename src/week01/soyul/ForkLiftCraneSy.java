package week01.soyul;

/**
 * 입력
 * storage: 처음 배열
 * 컨테이너 종류
 * requests: 출고 방법 요청 순서 배열
 * requests[i]=1 : 지게차
 * requests[i]=2: 크레인
 * 출력
 * 남은 컨테이너 수
 */
public class ForkLiftCraneSy {
  int n;//행(세로줄수)
  int m;//열(가로줄수)
  //{위 아래 왼 오}
  final int[] DX = {-1,1,0,0};//행 변화량
  final int[] DY = {0,0,-1,1};//열 변화량

  boolean[][] visited;//방문기록
  char[][] map; //컨테이너 초기
  int answer;//남아있는 컨테이너 수
  public int solution(String[] storage, String[] requests) {
    n = storage.length;
    m =storage[0].length();

    answer = n*m;
    //char[행][열]
    map = new char[n][m];

    for (int i = 0; i < n; i++) {
      //storage[i]=String
      //String.toCharArray(): char배열로 변환
      //map[]: 행 자체를 의미함
      map[i] = storage[i].toCharArray();
    }

    for (String request : requests) {
      if (request.length() == 1) {
        useCar(request.charAt(0));
      }else {
        //requests[i]는 한종류의 알파벳이라 charAt(0)도 가능
        useCrane(request.charAt(0));
      }
    }

    return answer;
  }

  //단순 비교
  private void useCrane(char request) {
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if(map[i][j]==request){
          answer--;
          map[i][j]= 0;
        }
      }
    }
  }

  public void useCar(char request) {
    visited = new boolean[n][m];

    for (int i = 0; i < n; i++) {
      if (!visited[i][0]) {//왼쪽줄
        dfs(i, 0, request);
      }
      if (!visited[i][m-1]) {//오른쪽줄
        dfs(i, m - 1, request);
      }
    }
    for (int i = 0; i < m; i++) {
      if (!visited[0][i]) {//위쪽줄
        dfs(0, i, request);
      }
      if (!visited[n-1][i]) {//아래쪽 줄
        dfs(n-1, i, request);
      }

    }
  }

  private void dfs(int x, int y, char request) {
    visited[x][y] = true;//현재칸 방문

    if(map[x][y]==0){//현재칸이 빈칸이면
      for (int i = 0; i < DX.length; i++) {
        int lx = x + DX[i];//위 아래
        int ly = y + DY[i];//왼 오
        if (lx < 0 || ly < 0 || lx >= n || ly >= m) {
          continue;//격자 밖이면 스킵
        }
        if(!visited[lx][ly]){//아직 안갔으면 dfs
          dfs(lx,ly,request);
        }
      }
    }

    if(map[x][y]==request){//꺼내야하는 컨테이너 꺼내기
      answer--;
      map[x][y]= 0;
    }
  }
}
