package m04;

/**
 * @Author: YST
 * @Date: 2021/4/5 19:51
 * @Version: 1.0
 * @Description: 合并两个有序整数数组
 * 给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
 * <p>
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。你可以假设 nums1 的空间大小等于 m + n，这样它就有足够的空间保存来自 nums2 的元素。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MergeSortedArray {

    /**
     * @Author: YST
     * @Date: 21:02 2021/4/5
     * @Param: [nums1, m, nums2, n]
     * @Return: void
     * @Description:
     **/
    public void merge(int[] nums1, int m, int[] nums2, int n) {

        if (m == 0) {
            for (int i = 0; i < nums2.length; i++) {
                nums1[i] = nums2[i];
            }
            return;
        }
        if (n == 0) {
            return;
        }

        int j = m - 1;
        int k = n - 1;
        int i = m + n - 1;
        label:
        while (true) {

            while (nums1[j] >= nums2[k]) {
                nums1[i] = nums1[j];
                j--;
                /**num1指针小于0 将num2剩余全部元素拷贝*/
                if (j < 0) {
                    for (int k1 = 0; k1 <= k; k1++) {
                        nums1[k1] = nums2[k1];
                    }
                    break label;
                }
                i--;
            }

            while (nums1[j] < nums2[k]) {
                nums1[i] = nums2[k];
                k--;
                /**num2指针小于0 结束*/
                if (k < 0) {
                    break label;
                }
                i--;
            }
        }

    }


    public static void main(String[] args) {
        int[] nums1 = new int[]{2, 0};
        int m = 1;
        int[] nums2 = new int[]{1};
        int n = 1;

        new MergeSortedArray().merge(nums1, m, nums2, n);

        System.out.println(nums1);
    }

    /**
     * @Author: LeetCode-Solution
     * @Date: 21:48 2021/4/5
     * @Param: [nums1, m, nums2, n]
     * @Return: void
     * @Description:
     **/
    public void merge1(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m - 1, p2 = n - 1;
        int tail = m + n - 1;
        int cur;
        while (p1 >= 0 || p2 >= 0) {
            if (p1 == -1) {
                cur = nums2[p2--];
            } else if (p2 == -1) {
                cur = nums1[p1--];
            } else if (nums1[p1] > nums2[p2]) {
                cur = nums1[p1--];
            } else {
                cur = nums2[p2--];
            }
            nums1[tail--] = cur;
        }
    }
}

