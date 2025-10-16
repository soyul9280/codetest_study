import java.util.*;

class Solution {
    
    private final boolean[][] visited = new boolean[101][101];

    private final int[] dx = {0, 0, -1, 1};  // 상하좌우
    private final int[] dy = {-1, 1, 0, 0};
    
    private int n;
    private int m;
    private char[][] map;
    
    public int[] solution(String[] maps) {
        n = maps.length;
        m = maps[0].length();
        map = new char[n][m];
        
        for (int i = 0; i < n; i++) {
            map[i] = maps[i].toCharArray();
        }
        
        
        List<Integer> result = new ArrayList<>();

        // 전체 맵 탐색
        for(int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] != 'X' && !visited[i][j]) {
                    int sum = dfs(i, j);
                    result.add(sum);
                }
            }
        }
        
        if (result.isEmpty()) return new int[]{-1};
        
        Collections.sort(result);
        return result.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }
    
    private int dfs(int x, int y) {
        visited[x][y] = true;
        int sum = map[x][y] - '0';
        
        for (int dir = 0; dir < 4; dir++) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];
            
            if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
            if (map[nx][ny] == 'X' || visited[nx][ny]) continue;
            
            sum += dfs(nx, ny);
        }
        
        return sum;
    }
    
}
