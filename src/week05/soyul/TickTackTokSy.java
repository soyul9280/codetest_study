package week05.soyul;

public class TickTackTokSy {
  public int solution(String[] board) {
    int o = 0;
    int x = 0;
    //말개수 세기
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        char c = board[i].charAt(j);
        if (c == 'O') o++;
        else if (c == 'X') x++;
      }
    }
    //o가 먼저니까 o==x o==x+1
    if(!(o==x||o==x+1)) return 0;
    boolean oWin = isWin(board, 'O');
    boolean xWin = isWin(board, 'X');
    //동시 승리하면 불가
    if(oWin && xWin) return 0;
    //o가 이기면 하나더 많아야함
    if(oWin&&o!=x+1) return 0;
    //x가 이기면 개수 같아야함
    if(xWin&&o!=x) return 0;

    return 1;
  }

  private boolean isWin(String[] board, char p) {
    //가로-같은 말로 채워졌는지 확인
    for (int i = 0; i < 3; i++) {
      if (board[i].charAt(0) == p && board[i].charAt(1) == p && board[i].charAt(2) == p) {
        return true;
      }
    }
    //세로
    for (int i = 0; i < 3; i++) {
      if (board[0].charAt(i) == p && board[1].charAt(i) == p && board[2].charAt(i) == p) {
        return true;
      }
    }
    //대각선
    if (board[0].charAt(0) == p && board[1].charAt(1) == p && board[2].charAt(2) == p) {
      return true;
    }
    if(board[0].charAt(2)==p&&board[1].charAt(1)==p&&board[2].charAt(0)==p) return true;
    return false;
  }

}
