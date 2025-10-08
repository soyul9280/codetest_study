import java.util.*;

class Solution {
    public String[] solution(String[][] plans) {
        // 1. 시작 시간을 기준으로 정렬
        Arrays.sort(plans, (a, b) -> a[1].compareTo(b[1]));

        Deque<Task> stack = new ArrayDeque<>();
        List<String> answer = new ArrayList<>();

        for (int i = 0; i < plans.length; i++) {
            String name = plans[i][0];
            int start = convertToMin(plans[i][1]);
            int playtime = Integer.parseInt(plans[i][2]);

            Task cur = new Task(name, start, playtime);

            if (i < plans.length - 1) {
                int nextStart = convertToMin(plans[i+1][1]);
                int interval = nextStart - start;

                // 현재 과제 다 끝낼 수 있음
                if (playtime <= interval) {
                    answer.add(cur.name);
                    interval -= playtime;

                    // 빈 시간이 있으면 스택에서 꺼내 처리
                    while (interval > 0 && !stack.isEmpty()) {
                        Task prev = stack.pop();
                        if (prev.left <= interval) {
                            answer.add(prev.name);
                            interval -= prev.left;
                        } else {
                            prev.left -= interval;
                            stack.push(prev);
                            break;
                        }
                    }
                } else {
                    // 다 못 끝내면 스택에 저장
                    cur.left = playtime - interval;
                    stack.push(cur);
                }
            } else {
                // 마지막 과제는 무조건 끝까지 실행
                answer.add(cur.name);
            }
        }

        // 스택에 남은 과제 처리
        while (!stack.isEmpty()) {
            answer.add(stack.pop().name);
        }

        return answer.toArray(new String[0]);
    }

    private int convertToMin(String time) {
        String[] t = time.split(":");
        return Integer.parseInt(t[0]) * 60 + Integer.parseInt(t[1]);
    }

    class Task {
        String name;
        int left;

        Task(String name, int start, int left) {
            this.name = name;
            this.left = left;
        }
    }
}
