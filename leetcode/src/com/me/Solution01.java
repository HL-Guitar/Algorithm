package com.me;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: Light
 * @CreateDate: 2019/9/30$ 10:01$
 * @Version: 1.0
 */
public class Solution01 {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> map  = new HashMap<>();
        int result = 0;
        for (int i = 0,len=nums.length; i < len ; i++) {
            result = target - nums[i];
            if(map.containsKey(result) ){
                return new int[]{i,map.get(result)};
            }
            map.put(nums[i],i); // 放这里可以避免重复使用
        }
        return null;
    }
}
