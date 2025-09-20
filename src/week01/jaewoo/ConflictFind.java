import java.util.*;

class Solution {
    public int solution(int[][] points, int[][] routes) {
        // 1. 포인트 번호 -> 좌표 매핑
        Map<Integer, int[]> pointMap = new HashMap<>();
        for (int i = 0; i < points.length; i++) {
            pointMap.put(i + 1, points[i]); // i+1번째 포인트의 좌표
        }

        // 2. 각 로봇의 전체 이동 경로 저장
        List<List<int[]>> robotPaths = new ArrayList<>();
        for (int[] route : routes) {
            List<int[]> path = new ArrayList<>();
            // 출발 좌표
            int r = pointMap.get(route[0])[0];
            int c = pointMap.get(route[0])[1];
            path.add(new int[]{r, c}); // 시작 위치 기록

            // 경로 순서대로 이동
            for (int k = 1; k < route.length; k++) {
                int endR = pointMap.get(route[k])[0];
                int endC = pointMap.get(route[k])[1];

                // r 먼저 맞추기
                while (r != endR) {
                    r += (endR > r) ? 1 : -1;
                    path.add(new int[]{r, c});
                }
                // c 맞추기
                while (c != endC) {
                    c += (endC > c) ? 1 : -1;
                    path.add(new int[]{r, c});
                }
            }
            robotPaths.add(path);
        }

        // 3. 시간별 충돌 검사
        int maxTime = 0;
        for (List<int[]> path : robotPaths) {
            maxTime = Math.max(maxTime, path.size());
        }

        int dangerCount = 0;
        for (int t = 0; t < maxTime; t++) {
            Map<String, Integer> counter = new HashMap<>();

            for (List<int[]> path : robotPaths) {
                if (t < path.size()) { // 아직 도착하지 않은 로봇만
                    int[] pos = path.get(t);
                    String key = pos[0] + "," + pos[1];
                    counter.put(key, counter.getOrDefault(key, 0) + 1);
                }
            }

            // 충돌 발생한 좌표 개수만큼 위험 횟수 추가
            for (int count : counter.values()) {
                if (count >= 2) {
                    dangerCount++;
                }
            }
        }

        return dangerCount;
    }
}
