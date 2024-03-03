

    public static void main(String[] args) {
        int[] nums = {1, 3, 4, 2, 2}; // Array with a duplicate number
        int duplicate = findDuplicate(nums);
        System.out.println("The duplicate number is: " + duplicate);
    }

    public static int findDuplicate(int[] nums) {
        int slow = nums[0];
        int fast = nums[nums[0]];
        System.out.println(slow);
        System.out.println(fast);
        // Find the intersection point of the two pointers
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }

        // Find the entrance to the cycle
        fast = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }

        return slow;
    }

