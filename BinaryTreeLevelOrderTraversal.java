/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

 /*
Approach: 
* Maintain a queue to do BFS, for each level we get the size of the queue and within the loop we are doing two things
    1) Add nodes of current level to the current level list
    2) Add the neighbors of nodes at the current level to the queue
Time Complexity: O(N)
Space Complexity: O(N/2) = O(N), N/2 = leaf nodes of a perfect binary tree, at the end queue will have all the leaf nodes
  */
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {

        List<List<Integer>> res = new ArrayList<>();
        if (root == null)
            return res;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int queueSize = 0;

        while (!queue.isEmpty()) {
            List<Integer> curr = new ArrayList<>();
            queueSize = queue.size();
            for (int i = 0; i < queueSize; i++) {
                TreeNode temp = queue.poll();
                if (temp.left != null) {
                    queue.add(temp.left);
                }
                if (temp.right != null) {
                    if (temp.right != null) {
                        queue.add(temp.right);
                    }
                }
                curr.add(temp.val);
            }

            res.add(curr);

        }
        return res;
    }
}