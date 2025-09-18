package week02.soyul;

/**
 * diff: 난이도
 * time_cur: 현재 소요시간
 * time_prev: 예전 소요시간
 * level: 숙련도
 * diff<=level : 안틀림. time_cur
 *
 * diff>level: diff-level만큼 틀림.
 * 틀릴때마다 time_cur,
 * time_prev로 이전문제 풀기(항상맞음)
 * time_cur
 *
 * 출력: limit내 모두 해결하기위한 숙련도 최소값.
 */
public class PuzzleGameChallengeSy {


  public int solution(int[] diffs, int[] times, long limit) {
    int low = 0;
    int high = 0;
    int level = low;

    int[] time = new int[diffs.length];//한번 퍼즐푸는데 걸린 시간

    //퍼즐 난이도 중 가장 큰값 넣기.(반복문보다 Math.max를 이용하자)
    for (int i = 0; i < diffs.length; i++) {
     high = Math.max(high, diffs[i]);
    }

    //이분탐색 시작->while low high패턴
    while (low <= high) {
      int mid = (low + high) / 2;
      //중간레벨로 풀수있는지 확인
      if (canSolve(mid, diffs, times, limit)) {
        //더 낮은 레벨도 가능한지-왼쪽이동
        level = mid;
        high = mid - 1;
      } else {
        //불가능하면 레벨 높임-오른쪽이동
        low = mid + 1;
      }
    }
    return level;
  }

//제한시간에 풀수있는지 확인용
  private boolean canSolve(int mid, int[] diffs, int[] times, long limit) {
    long total_time = 0;
    for (int i = 0; i < diffs.length; i++) {
        if (diffs[i] <= mid) {
          total_time += times[i];
        } else {
          if (i == 0) {
            //이전 퍼즐 없으니까 false
            return false;
          }
          total_time += (long) (diffs[i] - mid) * (times[i - 1] + times[i]) + times[i];
        }
      if (total_time > limit) {
        return false;
      }
    }
      return total_time <= limit;
  }
}

