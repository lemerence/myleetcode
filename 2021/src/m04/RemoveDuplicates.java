package m04;

import java.util.Arrays;

/**
 * @Author: YST
 * @Date: 2021/4/11 0:04
 * @Version: 1.0
 * @Description: 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使每个元素 最多出现两次 ，返回删除后数组的新长度。
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。  来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array-ii 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 输入：nums = [1,1,1,2,2,3]
 * 输出：5, nums = [1,1,2,2,3]
 * 解释：函数应返回新长度 length = 5, 并且原数组的前五个元素被修改为 1, 1, 2, 2, 3 。 不需要考虑数组中超出新长度后面的元素。
 * <p>
 * 输入：nums = [0,0,1,1,1,1,2,3,3]
 * 输出：7, nums = [0,0,1,1,2,3,3]
 * 解释：函数应返回新长度 length = 7, 并且原数组的前五个元素被修改为 0, 0, 1, 1, 2, 3, 3 。 不需要考虑数组中超出新长度后面的元素。
 */
public class RemoveDuplicates {

    public int removeDuplicates(int[] nums) {

        if (nums.length <= 2) {
            return nums.length;
        }

        /**新数组索引*/
        int index = 0;
        /**统计重复数*/
        int count = 0;
        /**正在被统计的值*/
        int value = nums[0];
        for (int i = 0; i < nums.length; i++) {

            if (index != i) {
                /**只要当前所有 与 新数组的所以不同 则将当前所有值赋值于新数组索引处*/
                nums[index] = nums[i];
            }

            if (value == nums[i]) {
                /**值重复统计*/
                count++;
                /**允许两个重复 两个以内新数组索引 ++, 超过两个则认为应该剔除，故不++*/
                if (count <= 2) {
                    index++;
                }
            } else {
                /**一旦元素与被统计值不同 重置被统计值、统计数量  索引正常++  */
                value = nums[i];
                count = 1;
                index++;
            }
        }
        return index;
    }

    public int removeDuplicates1(int[] nums) {

        if (nums.length <= 2) {
            return nums.length;
        }

        int slow = 2;
        int fast = 2;
        while (fast < nums.length) {
            if (nums[slow - 2] != nums[fast]) {
                nums[slow++] = nums[fast];
            }
            fast++;
        }
        return slow;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0, 0, 1, 1, 1, 1, 2, 3, 3};
        int i = new RemoveDuplicates().removeDuplicates1(nums);
        System.out.println(i);
    }


}
