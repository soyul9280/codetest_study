import java.util.*;

class Solution {
    
    static int[][] fatigueTable = {{1, 1, 1},{5, 1, 1},{25, 5, 1}};
    
    public int solution(int[] picks, String[] minerals) {
        int answer = 0;
        int totalCount = picks[0] + picks[1] + picks[2];
        
        List<MineralGroup> groups = new ArrayList<>();
        // 곡괭이 개수 * 5 만큼만 광물 그룹을 생성
        for (int i = 0; i < minerals.length && groups.size() < totalCount; i += 5) {
            MineralGroup curGroup = new MineralGroup();
            
            // 5개씩 묶음의 피로도 계산
            for (int j = i; j < i + 5 && j < minerals.length; j++) {
                String mineral = minerals[j];
                int mineralIndex = getIndex(mineral);
                
                for (int k = 0; k < 3; k++) {
                    curGroup.fatigue[k] +=  fatigueTable[k][mineralIndex];
                }
            }
            groups.add(curGroup);
        }
        
        // 돌 곡괭이로 캤을 때 피로도가 높은 순서대로 정렬
        Collections.sort(groups);
        
        // 정렬된 그룹에 순서대로 곡괭이 사용
        for (MineralGroup group : groups) {
            if (picks[0] > 0) {
                answer += group.fatigue[0];
                picks[0]--;
            } else if (picks[1] > 0) {
                answer += group.fatigue[1];
                picks[1]--;
            } else { // picks[2] > 0 조건은 생략 가능 (앞선 두 경우가 아니면 마지막 곡괭이)
                answer += group.fatigue[2];
                picks[2]--;
            }
        }
        return answer;
    }
    
    private int getIndex(String mineral) {
        if ("diamond".equals(mineral)) return 0;
        if ("iron".equals(mineral)) return 1;
        return 2;
    }
    
    static class MineralGroup implements Comparable<MineralGroup> {
        int[] fatigue = new int[3];
        
        @Override
        public int compareTo(MineralGroup other) {
            return other.fatigue[2] - this.fatigue[2];
        }
    }
}
