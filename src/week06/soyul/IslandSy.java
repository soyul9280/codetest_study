package week06.soyul;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class IslandSy {
    public int[] solution(String[] maps) {
        int n = maps.length;              // 행 개수
        int m = maps[0].length();         // 열 개수

        boolean[][] visited = new boolean[n][m];
        List<Integer> result = new ArrayList<>();

        // 4방향 이동 벡터
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        // 모든 칸 탐색
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // 아직 방문 안 했고, 바다가 아니라면 -> 새로운 섬 시작
                if (!visited[i][j] && maps[i].charAt(j) != 'X') {
                    int sum = 0;
                    Queue<int[]> q = new LinkedList<>();
                    q.offer(new int[]{i, j});
                    visited[i][j] = true;

                    // BFS 탐색 시작
                    while (!q.isEmpty()) {
                        int[] cur = q.poll();
                        int x = cur[0];
                        int y = cur[1];
                        // 현재 칸 식량 더하기
                        sum += maps[x].charAt(y) - '0';

                        // 4방향 탐색
                        for (int d = 0; d < 4; d++) {
                            int nx = x + dx[d];
                            int ny = y + dy[d];
                            // 범위 안이고, 방문 전이며, 육지라면 큐에 추가
                            if (nx >= 0 && nx < n && ny >= 0 && ny < m
                                    && !visited[nx][ny] && maps[nx].charAt(ny) != 'X') {
                                visited[nx][ny] = true;
                                q.offer(new int[]{nx, ny});
                            }
                        }
                    }

                    // 한 섬의 식량 합 추가
                    result.add(sum);
                }
            }
        }

        // 섬이 없다면 -1 반환
        if (result.isEmpty()) return new int[]{-1};

        // 오름차순 정렬 후 배열로 변환
        Collections.sort(result);
        return result.stream().mapToInt(Integer::intValue).toArray();
    }
}
