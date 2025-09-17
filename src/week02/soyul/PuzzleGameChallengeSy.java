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
    int diff;
    int time_cur;
    int time_prev;
    int[] time=new int[diffs.length];//한번 퍼즐푸는데 걸린 시간
    int total_time[] = new int[times.length];
    int level=0;

    for (int i = 0; i < diffs.length; i++) {
      level = diffs[i];
      for (int k = 0; k < diffs.length; k++) {
        diff = diffs[k];//현재난이도
        time_cur = times[k];//현재시간
        time_prev = times[k-1];//이전시간
        if (level >= diff) {
          time[k]=time_cur;
        } else {
          int count = diff-level;
          time[k]= (time_cur + time_prev) * count + time_cur;
        }
        for (int j = 0; j < time.length; j++) {
          total_time[i] += time[j];
        }
      }

    }

    int low =0;
    int high = diffs.length-1;
    for (int i = 0; i <= high; i++) {
      int small = 0;
      if(total_time[i]>limit){
        continue;
      }
      if(total_time[i]<=limit && small > total_time[i]){
        small = total_time[i];
        level = diffs[i];
      }
    }
    return level;
  }
}
