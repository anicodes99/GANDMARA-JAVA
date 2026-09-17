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