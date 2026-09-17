class Solution {
    public int longestValidParentheses(String s) {
        int maxLen = 0;
        int left = 0, right = 0;
        
        // first pass,left to right
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') left++;
            else right++;
            
            if (left == right) {
                // balanced, update max
                maxLen = Math.max(maxLen, 2 * right);
            } else if (right > left) {
                // closing paren with no match, gotta reset
                left = right = 0;
            }
        }
        
        // second pass, right to left (this is the part I always forget at first)
        left = right = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') left++;
            else right++;
            
            if (left == right) {
                maxLen = Math.max(maxLen, 2 * left);
            } else if (left > right) {
                left = right = 0;
            }
        }
        
        return maxLen;
    }
}   