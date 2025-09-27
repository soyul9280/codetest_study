package week02.soyul;

import java.util.Arrays;

/**
 * (r,c): n개 포인트, 1~n 다른 번호
 * 운송경로:m개
 * 로봇 개수:x, 0초에 동시 출발
 * 1초마다 r c중 하나만 감소/증가
 * 최단경로 이동. -> 가능성 여러개면 r먼저
 * 충돌: 같은 좌표
 * 출력: 충돌횟수
 * 입력: points=n개좌표 routes=로봇경로
 */
public class ConflictDangerFindSy {
  int[][] historyX;
  int[][] historyY;
  public int solution(int[][] points, int[][] routes) {
    int answer = 0;
    int maxRoad=0;
    //로봇 개수
    int m = routes.length;
    int x1=0;
    int y1=0;
    int x2=0;
    int y2=0;

    //TODO: 방문한 좌표 길이 어떻게 정하지 = 이동결로 모두 합치기
    //routes에서 시작과 끝점 추출 -> 시작점과 끝점의 x,y좌표를 각각 꺼내기.
    for (int i = 0; i < m; i++) {
      int start = routes[i][0];
      int end = routes[i][1];
      x1 = points[start-1][0];
      y1 = points[start-1][1];
      x2 = points[end-1][0];
      y2 = points[end-1][1];
      int absX = Math.abs(x1 - x2);
      int absY = Math.abs(y1 - y2);
      int road = absY + absX;
      maxRoad = Math.max(maxRoad,road);
    }

    historyX = new int[m][maxRoad];
    historyY = new int[m][maxRoad];

    for (int i = 0; i < m; i++) {
      int start = routes[i][0];
      int end = routes[i][1];
      x1 = points[start-1][0];
      y1 = points[start-1][1];
      x2 = points[end-1][0];
      y2 = points[end-1][1];
      //처음에 x좌표가 같다면 y차이만큼 이동 -> 기록
      //history[로봇번호][경로순서]
      if(x1 == x2){
        if(y1 < y2){
          for (int j = 0; j < y2-y1; j++) {
            historyX[i][j]=x1;
            historyY[i][j]=y1++;
          }
          System.out.println("x:"+ Arrays.toString(historyX[i])+" y:"+Arrays.toString(historyY[i]));
        }
        else if(y1 > y2){
          for (int j = 0; j < y1-y2; j++) {
            historyX[i][j]=x1;
            historyY[i][j]=y1--;
          }
          System.out.println("x:"+ Arrays.toString(historyX[i])+" y:"+Arrays.toString(historyY[i]));
        }
      }
      //처음에 y좌표가 같을 때
      else if(y1 == y2){
        if(x1 < x2){
          for (int j = 0; j < x2-x1; j++) {
            historyX[i][j]=x1++;
            historyY[i][j]=y1;
          }
          System.out.println("x:"+ Arrays.toString(historyX[i])+" y:"+Arrays.toString(historyY[i]));
        }
        else if(x1 > x2){
          for (int j = 0; j < x1-x2; j++) {
            historyX[i][j]=x1--;
            historyY[i][j]=y1;
          }
          System.out.println("x:"+ Arrays.toString(historyX[i])+" y:"+Arrays.toString(historyY[i]));
        }
      }
      //처음에 아무것도 같지 않을때
      else{
        //y좌표가 같아질때까지 이동
        if(y1 < y2){
          for (int j = 0; j < y2-y1; j++) {
            historyY[i][j]=y1++;
            historyX[i][j]=x1;
          }
          System.out.println("x:"+ Arrays.toString(historyX[i])+" y:"+Arrays.toString(historyY[i]));
          //y좌표 맞춘 후 x좌표 맞추기
          if(x1 < x2){
            for (int j = y2-y1; j < x2-x1; j++) {
              historyX[i][j]=x1++;
              historyY[i][j]=y2;
            }
            System.out.println("x:"+ Arrays.toString(historyX[i])+" y:"+Arrays.toString(historyY[i]));
          }
          if(x1 > x2){
            for (int j = y2-y1; j < x1-x2; j++) {
              historyX[i][j]=x1--;
              historyY[i][j]=y2;
            }
            System.out.println("x:"+ Arrays.toString(historyX[i])+" y:"+Arrays.toString(historyY[i]));
          }
        }else if(y1 > y2){
          for (int j = 0; j < y1-y2; j++) {
            historyY[i][j]=y1--;
            historyX[i][j]=x1;
          }
          System.out.println("x:"+ Arrays.toString(historyX[i])+" y:"+Arrays.toString(historyY[i]));
          //y좌표 맞춘 후 x좌표 맞추기
          if(x1 < x2){
            for (int j = y1-y2; j < x2-x1; j++) {
              historyX[i][j]=x1++;
              historyY[i][j]=y1;
            }
            System.out.println("x:"+ Arrays.toString(historyX[i])+" y:"+Arrays.toString(historyY[i]));
          }
          if(x1 > x2){
            for (int j = y1-y2; j < x1-x2; j++) {
              historyX[i][j]=x1--;
              historyY[i][j]=y1;
            }
            System.out.println("x:"+ Arrays.toString(historyX[i])+" y:"+Arrays.toString(historyY[i]));
          }
        }
      }
    }
    for (int i = 0; i < maxRoad; i++) {
      for (int j = 0; j < m; j++) {
        for (int k = j+1; k < m; k++) {
          if(historyX[j][i]==historyX[k][i]&&historyY[j][i]==historyY[k][i]){
            answer++;
          }
        }
      }
    }

    return answer;

  }
}
