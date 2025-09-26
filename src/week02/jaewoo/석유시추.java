import java.util.*;

class Solution {

    private int[] dx = {0, 0, -1, 1};
    private int[] dy = {-1, 1, 0, 0};
    private int n;
    private int m;

    public int solution(int[][] land) {
        n = land.length;
        m = land[0].length;
        
        int[][] oilIdMap = new int[n][m];
        Map<Integer, Integer> oilAmount = new HashMap<>();
        boolean[][] visited = new boolean[n][m];
        
        int id = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (land[i][j] == 1 && !visited[i][j]) {
                    int amount = bfs(land, visited, i, j, oilIdMap, id);
                    oilAmount.put(id, amount);
                    id++;
                }
            }
        }
        
        int maxOil = 0;
        for (int col = 0; col < m; col++) {
            Set<Integer> ids = new HashSet<>();
            for (int row = 0; row < n; row++) {
                if (oilIdMap[row][col] != 0) {
                    ids.add(oilIdMap[row][col]);
                }
            }
            int sum = 0;
            for (int target : ids) {
                sum += oilAmount.get(target);
            }
            maxOil = Math.max(maxOil, sum);
        }
        
        return maxOil;
    }
    
    private int bfs(
        int[][] land, 
        boolean[][] visited, 
        int row, int col,
        int[][] oilMap,
        int id
    ) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{row, col});
        visited[row][col] = true;
        oilMap[row][col] = id;
        
        int count = 1;
        
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0];
            int c = cur[1];
            
            for (int i = 0; i < 4; i++) {
                int nr = r + dx[i];
                int nc = c + dy[i];
                
                if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
                if (visited[nr][nc]) continue; 
                if (land[nr][nc] == 0) continue;
                
                visited[nr][nc] = true;
                oilMap[nr][nc] = id;
                q.add(new int[]{nr, nc});
                count++;
            }
        }
        return count;
    }
}
