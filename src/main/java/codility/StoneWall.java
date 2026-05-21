package main.java.codility;

public class StoneWall {
    /*
    You are going to build a stone wall. The wall should be straight and N meters long, and its thickness should be constant; however, it should have different heights in different places. The height of the wall is specified by an array H of N positive integers. H[I] is the height of the wall from I to I+1 meters to the right of its left end. In particular, H[0] is the height of the wall's left end and H[N−1] is the height of the wall's right end.

    The wall should be built of cuboid stone blocks (that is, all sides of such blocks are rectangular). Your task is to compute the minimum number of blocks needed to build the wall.

    Write a function:

    class Solution { public int solution(int[] H); }
    content_copy

    that, given an array H of N positive integers specifying the height of the wall, returns the minimum number of blocks needed to build it.

    For example, given array H containing N = 9 integers:

    H[0] = 8    H[1] = 8    H[2] = 5
    H[3] = 7    H[4] = 9    H[5] = 8
    H[6] = 7    H[7] = 4    H[8] = 8

    content_copy
    the function should return 7. The figure shows one possible arrangement of seven blocks.
    */

    public int solution(int[] H) {
        // `blocks` will count the minimum number of stone blocks required.
        int blocks = 0;

        // Use a stack to keep track of the current block heights as we move from left to right.
        // Each stack entry represents a block height that is still "open" to the right.
        java.util.Stack<Integer> stack = new java.util.Stack<>();

        // Walk through each height in the wall profile.
        for (int height : H) {
            // If the current required height is lower than the last open block height,
            // close blocks until the stack height is <= current height.
            while (!stack.isEmpty() && stack.peek() > height) {
                stack.pop();
            }

            // If there are no open blocks left, or the last open block is shorter than
            // the current required height, we need a new block of this height.
            if (stack.isEmpty() || stack.peek() < height) {
                stack.push(height);
                blocks++; // count the new block.
            }

            // If the top of stack equals the current height, we can continue using the
            // existing open block and do not need to add another one.
        }

        // Return the total minimum number of blocks needed.
        return blocks;
    }
}
