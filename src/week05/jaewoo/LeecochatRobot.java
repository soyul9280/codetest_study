import java.util.*;

class Solution {
    
    // 좌표를 표현할 클래스
    static class Point {
        int x, y, dist;
        Point(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
    
    // 상, 하, 좌, 우 방향 벡터
    private static final int[][] DIRS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    private final char ROBOT = 'R';
    private final char DISABLE = 'D';
    private final char GOAL = 'G';

    
    public int solution(String[] board) {
        int n = board.length;
        int m = board[0].length();
        
        Point start = findPosition(board, ROBOT);
        Point goal = findPosition(board, GOAL);
        
        return bfs(board, start, goal, n, m);
    }
    
    private Point findPosition(String[] board, char target) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length(); j++) {
                if (board[i].charAt(j) == target) {
                    return new Point(i, j, 0);
                }
            }
        }
        return null;
    }
    
    private Point slide(Point now, int[] dir, String[] board, int n, int m) {
        int x = now.x;
        int y = now.y;
        
        // 다음 칸이 유효하고 장애물이 아니면 계속 이동
        while (true) {
            int nx = x + dir[0];
            int ny = y + dir[1];
            
            if (nx < 0 || ny < 0 || nx >= n || ny >= m) break;
            if (board[nx].charAt(ny) == DISABLE) break;
            x = nx;
            y = ny;
        }
        return new Point(x, y, now.dist + 1);
    }
    
    private int bfs(String[] board, Point start, Point goal, int n, int m) {
        Queue<Point> queue = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];
        
        queue.offer(start);
        visited[start.x][start.y] = true;
        
        while (!queue.isEmpty()) {
            Point cur = queue.poll();
            
            // 도착 지점이면 이동 횟수 반환
            if (cur.x == goal.x && cur.y == goal.y) {
                return cur.dist;
            }
            
            // 4방향으로 탐색
            for (int[] dir : DIRS) {
                Point next = slide(cur, dir, board, n, m);
                if (!visited[next.x][next.y]) {
                    visited[next.x][next.y] = true;
                    queue.offer(next);
                }
            }
            
        }
        return -1;
    }
}
