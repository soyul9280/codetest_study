class Solution {
        int startNum = 0;
        int donut = 0;
        int stick = 0;
        int eight = 0;
    
    public int[] solution(int[][] edges) {
        int maxNode = getMax(edges);

        int[] inDegree = new int[maxNode + 1];
        int[] outDegree = new int[maxNode + 1];
        
        for (int[] edge : edges) {
            outDegree[edge[0]]++;
            inDegree[edge[1]]++;
        }
        
        for (int i = 1; i <= maxNode; i++) {
            if (inDegree[i] == 0 && outDegree[i] >= 2) {
                startNum = i;
            } else if (outDegree[i] == 0) {
                stick++;
            } else if (outDegree[i] >= 2 && inDegree[i] >= 2) {
                eight++;
            }
        }
        donut = outDegree[startNum] - stick - eight;
        return new int[]{startNum, donut, stick, eight};
    }
    
    private int getMax(int[][] edges) {
        int max = 0;
        
        for (int[] edge : edges) {
            int maxedge = Math.max(edge[0], edge[1]);
            max = Math.max(max, maxedge);
        }
        
        return max;
    }
}
