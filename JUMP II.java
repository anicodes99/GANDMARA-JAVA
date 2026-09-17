//You are given a 0-indexed array of integers nums of length n. You are initially positioned at index 0.

//Each element nums[i] represents the maximum length of a forward jump from index i. In other words, if you are at index i, you can jump to any index (i + j) where:

//0 <= j <= nums[i] and
//i + j < n
//Return the minimum number of jumps to reach index n - 1. The test cases are generated such that you can reach index n - 1.
    class jumpII{
    public int jump(int[]nums){
        int jumps=0;
        int currend=0;
        int currfar=0;


        for(int i=0;i<nums.length-1;i++){
            currfar=Math.max(currfar,i+nums[i]);

            if(i==currend){
                jumps++;
                currend=currfar;
                if(currend=>=nums.length-1)
                break;
            }
        }
    }

}
