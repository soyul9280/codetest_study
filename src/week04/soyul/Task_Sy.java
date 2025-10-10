package week04.soyul;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 기존과제 멈추고 새로운 과제 진행
 * 진행중 과제 끝나면 멈춘 과제 시작-새로운게 있다면 새로운것부터
 * 가장 최근에 멈춘과제부터 시작
 * 출력: 과제 끝낸 순서대로 이름배열
 */
public class Task_Sy {
  public String[] solution(String[][] plans) {
    Arrays.sort(plans, (a, b) -> timeToInt(a[1]) - timeToInt(b[1]));
    Stack<Task> stack = new Stack<>();
    List<String> answer = new ArrayList<>();
    for (int i = 0; i < plans.length; i++) {
      String name = plans[i][0];
      int start = timeToInt(plans[i][1]);
      int playTime=Integer.parseInt(plans[i][2]);
      //다음과제 없으면 충분히 큰 값을 대체->24*60
      int nextStart = (i == plans.length - 1) ? (24*60+10) : timeToInt(plans[i + 1][1]);
      //현재과제 시작
      int remainTime=playTime;
      //가능시간
      int availableTime;
      if(nextStart==(24*60+10)){
        availableTime=playTime;
      }else {
        availableTime=nextStart-start;
      }
      if (remainTime <=availableTime) {
        answer.add(name);
        int spare = availableTime-remainTime;
        while(!stack.isEmpty()&&spare>0) {
          Task prev = stack.pop();
          if (prev.time <= spare) {
            answer.add(prev.name);
            spare-=prev.time;
          }else{
            prev.time-=spare;
            stack.push(prev);
            break;
          }
        }
      }else{
        int remain=playTime-availableTime;
        if(remain>0) stack.push(new Task(name, remain));
      }
    }
    while(!stack.isEmpty()) {
      answer.add(stack.pop().name);
    }
    
    return answer.toArray(new String[0]);
  }

  private int timeToInt(String s) {
    String[] parts = s.split(":");
    return Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]);
  }

  static class Task{
    String name;
    int time;
    public Task(String name, int time){
      this.name = name;
      this.time = time;
    }
  }
}
