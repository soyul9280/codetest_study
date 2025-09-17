import java.util.*;

class Solution {
    int n;
    int m;
    char[][] inventory;
    boolean[][] existed;
    int[] dx = {0, 0, -1, 1};
    int[] dy = {-1, 1, 0, 0};
    
    int totalCount;
    
    public int solution(String[] storage, String[] requests) {
        n = storage.length;
        m = storage[0].length();
        totalCount = n * m;
        
        inventory = new char[n][m];
        existed = new boolean[n][m];
        
        init(storage);
        
        for (String req : requests) {
            char target = req.charAt(0);
            if (req.length() == 1) {
                // 지게차
                extractByCar(target);
            } else {
                // 크레인
                extractByCraine(target);
            }
        }
        
        return totalCount;
    }
    
    private void init(String[] storage) {
        for (int i = 0; i < n; i++) {
            inventory[i] = storage[i].toCharArray();
            Arrays.fill(existed[i], true);
        }
    }
    
    private void extractByCar(char target) {
        boolean[][] isAccessible = calcAccessible();
        for (boolean[] a : isAccessible) {
            System.out.print(Arrays.toString(a));
            System.out.println();
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (target == inventory[i][j] && existed[i][j] && isAccessible[i][j]) {
                    System.out.println(target + " 컨테이너 제거" + i + " " + j + " ");
                    totalCount--;
                    existed[i][j] = false;
                }
            }
        }
    }
    
    private boolean[][] calcAccessible() {
        boolean[][] accessible = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 || j == 0 || i == n -1 || j == m -1) {
                    accessible[i][j] = true;
                    continue;
                }
                
                boolean isAccessible = false;
                for (int d = 0; d < 4; d++) {
                    int nx = i + dx[d];
                    int ny = j + dy[d];
                    
                    if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
                    if (!existed[nx][ny]) isAccessible = true;
                }
                accessible[i][j] = isAccessible;
            }
        }
        return accessible;
    }
    
    private void extractByCraine(char target) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (existed[i][j] && target == inventory[i][j]) {
                    System.out.println(target + " 컨테이너 제거" + i + " " + j + " ");
                    totalCount--;
                    existed[i][j] = false;
                }
            }
        }
    }
}