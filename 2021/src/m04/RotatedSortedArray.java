package m04;

/**
 * @Author: YST
 * @Date: 2021/4/12 21:50
 * @Version: 1.0
 * @Description: 二分查找
 * 已知存在一个按非降序排列的整数数组 nums ，数组中的值互不相同(searchI)。 数组中的值不必互不相同(searchII)。
 * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转 ，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。
 * 例如， [0,1,2,4,4,4,5,6,6,7] 在下标 5 处经旋转后可能变为 [4,5,6,6,7,0,1,2,4,4] 。
 * <p>
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/search-in-rotated-sorted-array-ii 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class RotatedSortedArray {

    /**
     * @Author: YST
     * @Date: 23:04 2021/4/12
     * @Param: [nums, target]
     * @Return: int
     * @Description: 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。
     * <p>
     * 输入：nums = [4,5,6,7,0,1,2], target = 0
     * 输出：4
     * <p>
     * 输入：nums = [4,5,6,7,0,1,2], target = 3
     * 输出：-1
     * <p>
     * 输入：nums = [1], target = 0
     * 输出：-1
     **/
    public int searchI(int[] nums, int target) {

        int n = nums.length;
        if (n == 0) {
            return -1;
        }
        if (n == 1) {
            return nums[0] == target ? 0 : -1;
        }


        return binarySearchI(nums, 0, n - 1, target);

    }

    public int binarySearchI(int[] nums, int left, int right, int target) {

        if (left > right) {
            return -1;
        }
        int mid = (left + right) / 2;
        int leftValue = nums[left];
        int rightValue = nums[right];
        int midValue = nums[mid];

        if (leftValue == target) {
            return left;
        }

        if (rightValue == target) {
            return right;
        }

        if (midValue == target) {
            return mid;
        }

        if (leftValue < midValue) {
            if (leftValue < target && target < midValue) {
                return binarySearchI(nums, left, mid - 1, target);
            } else {
                return binarySearchI(nums, mid + 1, right, target);
            }
        } else {
            if (midValue < target && target < rightValue) {
                return binarySearchI(nums, mid + 1, right, target);
            } else {
                return binarySearchI(nums, left, mid - 1, target);
            }
        }

    }

    /**
     * @Author: YST
     * @Date: 23:03 2021/4/12
     * @Param: [nums, target]
     * @Return: boolean
     * @Description: 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。
     * <p>
     * 输入：nums = [2,5,6,0,0,1,2], target = 0
     * 输出：true
     * <p>
     * 输入：nums = [2,5,6,0,0,1,2], target = 3
     * 输出：false
     **/
    public boolean searchII(int[] nums, int target) {
        int n = nums.length;
        if (n == 0) {
            return false;
        }
        if (n == 1) {
            return nums[0] == target;
        }


        return binarySearchII(nums, 0, n - 1, target);
    }

    public boolean binarySearchII(int[] nums, int left, int right, int target) {

        if (left > right) {
            return false;
        }
        int mid = (left + right) / 2;
        int leftValue = nums[left];
        int rightValue = nums[right];
        int midValue = nums[mid];

        if (leftValue == target) {
            return true;
        }

        if (rightValue == target) {
            return true;
        }

        if (midValue == target) {
            return true;
        }

        if (leftValue < midValue) {
            if (leftValue < target && target < midValue) {
                return binarySearchII(nums, left, mid - 1, target);
            } else {
                return binarySearchII(nums, mid + 1, right, target);
            }
        } else if (leftValue > midValue){
            if (midValue < target && target < rightValue) {
                return binarySearchII(nums, mid + 1, right, target);
            } else {
                return binarySearchII(nums, left, mid - 1, target);
            }
        }else {
            return binarySearchII(nums, left+1, right-1, target);
        }

    }
}
