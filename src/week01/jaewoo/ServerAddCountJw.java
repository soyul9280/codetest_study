import java.util.*;

class Solution {
    
    private final Map<Integer, Integer> servers = new HashMap<>();
    
    public int solution(int[] players, int m, int k) {
        // 증설된 서버의 총 개수
        int increaseServerCount = 0;
        int currentServerCount = 0;
        for (int time = 0; time < players.length; time++) {
            // 증설된 서버가 있다면, k 만큼 지난 후에 제거해야 한다.
            int expiredTime = time - k;
            if (expiredTime > 0 && servers.containsKey(expiredTime)) {
                int expiredServerCount = servers.remove(expiredTime);
                currentServerCount -= expiredServerCount;
                System.out.println("삭제 시간 : " + time + ", 삭제된 서버 개수 : " + expiredServerCount + ", 현재 서버 개수 : " + currentServerCount);
            }
            // 특정 시간대 유저 수 확인
            int userCount = players[time];
        
            // 서버가 유저를 감당 할 수 없다면 감당할 수 있는 서버 개수로 증설한다.
            if (userCount >= m && userCount >= currentServerCount * 2 * m) {
                int requiredServerCount = (userCount / m) - currentServerCount;
                currentServerCount += requiredServerCount;
                increaseServerCount += requiredServerCount;
                servers.put(time, requiredServerCount);
                System.out.println("증설 시간 : " + time + ", 필요 서버 개수 : " + requiredServerCount + ", 총 증설 개수 : " + increaseServerCount);
                
            }

        }
        return increaseServerCount;
    }
}