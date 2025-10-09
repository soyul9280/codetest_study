package week04.soyul;

/**
 * 두 인덱스 원소와 그 사이 원소 모두 포함 부분 수열
 * 부분수열의 합=k
 * 여러개면 짧은 수열
 * 길이도 같다면 앞(시작인덱스 작은) 수열
 * 입력: sequence(수열 -정수배열<=1000), k
 * 출력: 부분수열의 시작 인덱스와 마지막 인덱스
 */
public class SequenceSumSy {
  public int[] solution(int[] sequence, int k) {
    int[] answer = new int[2];
    int left = 0;
    int right =0;
    int sum=0;
    int size=sequence.length;
    int minLen=Integer.MAX_VALUE;//최소 길이 저장용
    while (true) {
      if (sum >= k) {
        if (sum == k && (right - left) < minLen) {
          minLen = right - left;
          answer[0] = left;
          answer[1] = right-1;
        }
        sum -= sequence[left++];
      }else {
        if(right==size) break;
        sum += sequence[right++];
      }
    }
    return answer;
  }
}
