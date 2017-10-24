import static org.junit.Assert.*;

public class inorder_BST_Test {
    @org.junit.Test
    public void main() throws Exception {
        BST<Integer> tree_test1 = new BST<>(new Integer [] {6,2,3,4,5,6,8,2,10,22});
        asserEquals("expected: 2, 2, 3, 4, 5, 6, 6, 8, 10, 22", {2, 2, 3, 4, 5, 6, 6, 8, 10, 22}, tree_test1.inorder());
    }

}