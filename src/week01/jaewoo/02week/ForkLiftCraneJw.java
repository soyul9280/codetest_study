import java.util.*;

class Solution {
    private static final int[] dx = {-1, 1, 0, 0};
    private static final int[] dy = {0, 0, -1, 1};  
    
    private String[][] storage;
    private int[][] mark;

    public long solution(String[] storage, String[] requests) {
        this.storage = parseToStorage(storage);
        this.mark = initMark(storage.length, storage[0].length());
        
        for(String request : requests) {
            if (request.length() == 1) {
                directExtractByjigacar(request);
            } else {
                allExtractByCrane(request);
            }
        }
        
        return Arrays.stream(mark)
            .flatMapToInt(Arrays::stream)
            .filter(mark -> mark==1)
            .count();
    }
    
    // mark 상하좌우 검색 배열
    private int[][] initMark(int rowSize, int colSize) {
        int[][] mark = new int[rowSize][colSize];
        for (int[] row : mark) {
            Arrays.fill(row, 1);
        }
        return mark;
    }
    
    // 입력받은 배열을 2차원 배열로 변환
    public String[][] parseToStorage(String[] input) {
        String[][] storage = new String[input.length][input[0].length()];
        for (int i = 0; i < input.length; i++) {
            storage[i] = input[i].split("");
        }
        return storage;
    }
    
    // 지게차를 이용해 하나로만 출고
    private void directExtractByjigacar(String target) {
        for (int i = 0; i< storage.length; i++) {
            for (int j = 0; j < storage[i].length; j++) {
                if (!target.equals(storage[i][j])) continue;
                if (mark[i][j] == 0) continue;
                
                boolean accessible = false;
                
                if (i == 0 || j == 0 || i == storage.length - 1 || j == storage[0].length - 1) {
                    accessible = true;
                } else {
                    for (int d = 0; d < 4; d++) {
                        int ni = i + dx[d];
                        int nj = j + dy[d];
                        if (isInBounds(ni, nj) && mark[ni][nj] == 0) {
                            accessible = true;
                            break;
                        }
                    }
                }

                if (accessible) {
                    mark[i][j] = 0;
                }
            }
        }
    }
    
    private boolean isInBounds(int x, int y) {
        return x >= 0 && x < storage.length &&
               y >= 0 && y < storage[0].length;
    }
    
    // 크레인을 이용해 요청된 종류의 컨테이너 출고
    private void allExtractByCrane(String target) {
        String targetStr = String.valueOf(target.charAt(0));
        for (int i = 0; i < storage.length; i++) {
            for (int j = 0; j < storage[i].length; j++) {
                if (storage[i][j].equals(targetStr)) {
                    mark[i][j] = 0;
                }
            }
        }
    }
    
}