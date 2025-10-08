package week03.soyul;

import java.util.HashMap;
import java.util.Map;

public class DonutStickGraphSy {
  public int[] solution(int[][] edges) {
    /* 정점별 나가는 간선과 들어오는 간선의 개수를 담아둘 map 생성 */
    Map<Integer,Integer> out = new HashMap<>();
    Map<Integer,Integer> in = new HashMap<>();
    //추가 생성된 정점(정답의 1번 값)
    int newNode = -1;
    //도넛그래프
    int donut = 0;
    //막대그래프
    int bar = 0;
    //8자그래프
    int eight = 0;

    /* 간선들을 for문돌면서 각 정점의 나가는 간선, 들어오는 간선의 개수를 저장한다
     * map.getOrDefault(d,n) :  map에서 key가 d인 value를 리턴하는데 만약 d가 없으면 n 리턴
     */
    for(int [] e : edges){
      //(그 정점의 나가는 간선 수,없으면 0)+1
      out.put(e[0],out.getOrDefault(e[0],0) + 1);
      in.put(e[1],in.getOrDefault(e[1],0) + 1);
    }
    for(int node : out.keySet()){
      if(out.get(node) > 1) {
        /* 나가는 간선은 2개이상이지만, 들어오는 간선이 없는 정점 : 추가생성 정점 */
        if(!in.containsKey(node)) newNode = node;
          /* 나가는 간선이 2개이상이고 들어오는 간선도 1개 이상 : 8그래프의 가운데 정점 */
        else eight++;
      }
    }
    /* 나가는 간선이 없는 정점 : 막대 그래프의 마지막 정점 */
    for(int node : in.keySet()){
      if(!out.containsKey(node)) bar++;
    }
    /* 추가 생성 정점의 간선 개수는 도넛, 막대, 8자 그래프 수의 합과 같음
    * 도넛 개수 = (newNode의 out-degree) - bar - eight. */
    donut = out.get(newNode) - bar - eight;

    int[] answer = {newNode,donut,bar,eight};
    return answer;
  }

}
