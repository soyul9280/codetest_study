package week06;

import java.util.Arrays;
import java.util.PriorityQueue;

public class HotelLentSy {
    public int solution(String[][] book_time) {
        int n = book_time.length;
        int[][] times=new int[n][2];
        for(int i=0; i<n; i++) {
            times[i][0]=toMinutes(book_time[i][0]);
            times[i][1]=toMinutes(book_time[i][1])+10;
            }

        Arrays.sort(times,(a,b)->a[0]-b[0]);

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int[] t : times) {
            if (!pq.isEmpty() && pq.peek() <= t[0]) {
                pq.poll();
            }
            pq.offer(t[1]);
        }

        return pq.size();
    }
    private int toMinutes(String time) {
        String[] parts = time.split(":");
        return Integer.parseInt(parts[0])*60+Integer.parseInt(parts[1]);
    }
}
