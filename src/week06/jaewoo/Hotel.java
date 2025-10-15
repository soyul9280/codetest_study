import java.util.*;

class Solution {
    public int solution(String[][] book_times) {
        List<Book> time = new ArrayList<>();
        for (String[] book_time : book_times) {
            time.add(
              new Book(
                  parseToInt(book_time[0]),
                  parseToInt(book_time[1])
              )
            );
        }
        Collections.sort(time);
        
        PriorityQueue<Integer> room = new PriorityQueue<>();
        
        for (Book book : time) {
            int start = book.start;
            int end = book.end + 10;
            if (!room.isEmpty() && room.peek() <= start) {
                room.poll();
            }
            
            room.add(end);
        }
        return room.size();
    }
    
    static class Book implements Comparable<Book> {
        int start;
        int end;
        
        public Book(int start, int end) {
            this.start = start;
            this.end = end;
        }
        
        public int compareTo(Book other) {
            return this.start - other.start;
        }
    }
    
    private int parseToInt(String time) {
        String[] splitTime = time.split(":");
        int hour = Integer.parseInt(splitTime[0]);
        int min = Integer.parseInt(splitTime[1]);
        return (hour * 60) + (min);
    }
}
