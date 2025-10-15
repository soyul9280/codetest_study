import java.util.*;

/*
S : 시작 지점
E : 출구
L : 레버
O : 통로
X : 벽
*/
class Solution {
    static final char START = 'S';
    static final char END = 'E';
    static final char LEVER = 'L';
    static final char WALL = 'X';
    
    int n;
    int m;
    
    public int solution(String[] maps) {
        n = maps.length;
        m = maps[0].length();
        Point start = null;
        Point lever = null;
        Point exit = null;
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                char c = maps[i].charAt(j);
                if (c == START) start = new Point(i, j);
                if (c == END) exit = new Point(i, j);
                if (c == LEVER) lever = new Point(i, j);
            }
        }
        
        int sTol = bfs(maps, start, lever);
        int lToE = bfs(maps, lever, exit);
        
        if (sTol == -1 || lToE == -1) return -1;
        return sTol + lToE;
    }
    
    private int bfs(String[] maps, Point start, Point end) {
        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};
        
        Queue<Point> q = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];
        
        q.offer(start);
        visited[start.x][start.y] = true;
        
        while(!q.isEmpty()) {
            Point cur = q.poll();
            
            if (cur.x == end.x && cur.y == end.y) {
                return cur.distance;
            }
            
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                
                if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
                if (maps[nx].charAt(ny) == WALL || visited[nx][ny]) continue;
                
                visited[nx][ny] = true;
                q.offer(new Point(nx, ny, cur.distance + 1));
            }
        }
        return -1;
    }
    
    static class Point {
        int x;
        int y;
        int distance;
        
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
            distance = 0;
        }
        
        public Point(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
    }
}
