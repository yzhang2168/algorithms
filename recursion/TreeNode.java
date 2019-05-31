package recursion;


public class TreeNode {
    public Integer value;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(Integer e) {
        this.value = e;
        left = null;
        right = null;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
