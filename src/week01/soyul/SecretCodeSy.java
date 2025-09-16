package week01.soyul;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * 1~n 서로 다른 5개 정수 오름차순
 * m번 시도-몇개 맞았는지
 * 입력 : 5개 정수
 * 출력:비밀코드로 가증한 정수 조합 개수
 */
public class SecretCodeSy {
  int[] arr = new int[5]; // 내가 고른 숫자들 전용
  boolean[] visited; //백트래킹 위한 추적
  Set<Integer>[] set;//질문을 set으로 바꿔저장
  int answer = 0;//답

  public int solution(int n, int[][] q, int[] ans) {
    set = new Set[q.length];
    for (int i = 0; i <q.length; i++) {
      set[i] = new HashSet<>();//i번째용 질문
      for (int j = 0; j < 5; j++) {
        set[i].add(q[i][j]);
      }
    }
    visited = new boolean[n+1]; // N개의 숫자중 넣은 값이 있는지 체크용
    backTrace(0, 1, n, q, ans);
    return answer;
  }

  public void backTrace(int depth, int cur, int n, int[][] q, int[] ans) {
    if (depth == 5) {//숫자 5개 다 골랐으면
      if(isValid(q,ans)){//모든 질문 조건과 맞는지 검사
        answer++;
      }
      return;//끝
    }
    for (int i = cur; i <= n; i++) {
      if(visited[i]) {// 이미 선택했으면 건너띄기
        continue;
      }
      arr[depth] = i;
      visited[i] = true;
      backTrace(depth + 1, i + 1, n, q, ans);
      visited[i] = false;//돌아오기-> i선택 취소 다른 숫자 넣기 시도
    }
  }
  public boolean isValid(int[][] q, int[] ans) {
    for (int i = 0; i < q.length; i++) {
      int sum =0;// 겹친 수 세기
      for (int num : arr) { //내가 직접 고른 5개 수 보면서
        if(set[i].contains(num)) {//질문에 들어있는지 확인
          sum ++;//겹치는수+1
        }
        if (ans[i] != sum) {//정답 수랑 다르면
          return false; // 실패!
        }
        return true;
      }
    }
  }

}
