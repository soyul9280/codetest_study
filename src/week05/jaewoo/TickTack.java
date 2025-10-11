import java.util.*;

class Solution {

    public int solution(String[] board) {
        
        int countO = 0;
        int countX = 0;

        
        for (String row : board) {
            for (char c : row.toCharArray()) {
                if (c == 'O') {
                    countO++;
                    continue;
                }
                if (c == 'X') {
                    countX++;
                    continue;
                }
            }
        }
        
        if (!(countO == countX || countO == countX +1)) {
            return 0;
        }
        
        boolean oWin = isWin(board, 'O');
        boolean xWin = isWin(board, 'X');
        
        if (oWin && xWin) return 0;
        if (oWin && countO != countX+1) return 0;
        if (xWin && countO != countX) return 0;
        
        return 1;
    }
    
    private boolean isWin(String[] board, char mark) {
        for (int i = 0; i < 3; i++) {
            if (board[i].charAt(0) == mark && board[i].charAt(1) == mark && board[i].charAt(2) == mark)
                return true;
            if (board[0].charAt(i) == mark && board[1].charAt(i) == mark && board[2].charAt(i) == mark)
                return true;
        }
        if (board[0].charAt(0) == mark && board[1].charAt(1) == mark && board[2].charAt(2) == mark)
            return true;
        if (board[0].charAt(2) == mark && board[1].charAt(1) == mark && board[2].charAt(0) == mark)
            return true;
        return false;
    }
    
}
