import java.util.LinkedList;
import java.util.ArrayList;

public class inorder_BST {

    static abstract class abstract_tree<E> implements Tree<E> {

        @Override

        public void inorder() {
        }

        @Override

        public void postorder() {
        }

        @Override

        public void preorder() {
        }

        @Override

        public boolean isEmpty() {
            return getSize() == 0;
        }

        @Override

        public java.util.Iterator<E> iterator() {
            return null;
        }
    }


    static class BST<E extends Comparable<E>> extends abstract_tree<E> {

        protected tree_Node<E> root;

        protected int size = 0;

        public void inorder2() {

            if(root == null) {

                return;
            }

            LinkedList<tree_Node<E>> list = new LinkedList<>();

            LinkedList<tree_Node<E>> stack = new LinkedList<>();

            stack.add(root);

            while (!stack.isEmpty()) {

                tree_Node<E> node = stack.getFirst();

                if ((node.left != null) && (!list.contains(node.left))) {

                    stack.push(node.left);

                } else {

                    stack.removeFirst();

                    list.add(node);

                    if (node.right != null) {

                        stack.addFirst(node.right);
                    }
                }
            }
            for (tree_Node<E> treeNode : list) {

                System.out.print(treeNode.element + " ");
            }
        }



        public void breadthFirstTraversal() {

            if(root == null) {

                return;
            }
            ArrayList<tree_Node<E>> list = new ArrayList<>();

            list.add(root);

            while(!list.isEmpty()) {

                ArrayList<tree_Node<E>> tmpList = new ArrayList<>();

                for (tree_Node<E> treeNode : list) {

                    if(treeNode!= null) {

                        System.out.print(treeNode.element + " ");

                        tmpList.add(treeNode.left);

                        tmpList.add(treeNode.right);

                    }
                    list = tmpList;
                }
            }
        }


        public boolean isFullBST() {

            return size == Math.round(Math.pow(2, height()) - 1);
        }


        public int height() {

            return height(root);
        }

        public int height(tree_Node<E> node) {

            if(node == null) {

                return 0;

            } else {

                return 1 + Math.max(height(node.left), height(node.right));
            }
        }


        public BST() {
        }


        public BST(E[] objects) {

            for (int i = 0; i < objects.length; i++)

                insert(objects[i]);
        }

        @Override

        public boolean search(E e) {

            tree_Node<E> current = root; // Start from the root

            while (current != null) {

                if (e.compareTo(current.element) < 0) {

                    current = current.left;

                } else if (e.compareTo(current.element) > 0) {

                    current = current.right;

                } else

                    return true;
            }

            return false;
        }

        @Override

        public boolean insert(E e) {

            if (root == null)

                root = createNewNode(e);

            else {

                tree_Node<E> parent = null;

                tree_Node<E> current = root;

                while (current != null)

                    if (e.compareTo(current.element) < 0) {

                        parent = current;

                        current = current.left;

                    } else if (e.compareTo(current.element) > 0) {

                        parent = current;

                        current = current.right;

                    } else

                        return false;


                if (e.compareTo(parent.element) < 0)

                    parent.left = createNewNode(e);

                else

                    parent.right = createNewNode(e);
            }

            size++;

            return true;
        }

        protected tree_Node<E> createNewNode(E e) {

            return new tree_Node<E>(e);
        }

        @Override

        public void inorder() {

            inorder(root);
        }


        protected void inorder(tree_Node<E> root) {

            if (root == null)

                return;

            inorder(root.left);

            System.out.print(root.element + " ");

            inorder(root.right);
        }

        @Override

        public void postorder() {

            postorder(root);
        }


        protected void postorder(tree_Node<E> root) {

            if (root == null)

                return;

            postorder(root.left);

            postorder(root.right);

            System.out.print(root.element + " ");
        }

        @Override

        public void preorder() {

            preorder(root);
        }


        protected void preorder(tree_Node<E> root) {

            if (root == null)

                return;

            System.out.print(root.element + " ");

            preorder(root.left);

            preorder(root.right);
        }


        public static class tree_Node<E extends Comparable<E>> {

            protected E element;

            protected tree_Node<E> left;

            protected tree_Node<E> right;

            public tree_Node(E e) {

                element = e;
            }
        }

        @Override

        public int getSize() {

            return size;
        }


        public tree_Node<E> getRoot() {

            return root;
        }


        public java.util.ArrayList<tree_Node<E>> path(E e) {

            java.util.ArrayList<tree_Node<E>> list = new java.util.ArrayList<tree_Node<E>>();

            tree_Node<E> current = root;

            while (current != null) {

                list.add(current);

                if (e.compareTo(current.element) < 0) {

                    current = current.left;

                } else if (e.compareTo(current.element) > 0) {

                    current = current.right;
                } else

                    break;
            }

            return list;
        }

        @Override

        public boolean delete(E e) {

            tree_Node<E> parent = null;

            tree_Node<E> current = root;

            while (current != null) {

                if (e.compareTo(current.element) < 0) {

                    parent = current;

                    current = current.left;

                } else if (e.compareTo(current.element) > 0) {

                    parent = current;

                    current = current.right;

                } else
                    break;
            }

            if (current == null)

                return false;


            if (current.left == null) {

                if (parent == null) {

                    root = current.right;
                } else {

                    if (e.compareTo(parent.element) < 0)

                        parent.left = current.right;

                    else

                        parent.right = current.right;
                }

            } else {

                tree_Node<E> parentOfRightMost = current;

                tree_Node<E> rightMost = current.left;

                while (rightMost.right != null) {

                    parentOfRightMost = rightMost;

                    rightMost = rightMost.right;
                }


                current.element = rightMost.element;


                if (parentOfRightMost.right == rightMost)

                    parentOfRightMost.right = rightMost.left;

                else

                    parentOfRightMost.left = rightMost.left;
            }

            size--;

            return true;
        }

        @Override

        public java.util.Iterator<E> iterator() {

            return new InorderIterator();
        }


        private class InorderIterator implements java.util.Iterator<E> {

            private java.util.ArrayList<E> list = new java.util.ArrayList<E>();

            private int current = 0;

            public InorderIterator() {

                inorder();
            }


            private void inorder() {

                inorder(root);
            }


            private void inorder(tree_Node<E> root) {

                if (root == null)

                    return;

                inorder(root.left);

                list.add(root.element);

                inorder(root.right);
            }

            @Override

            public boolean hasNext() {

                if (current < list.size())

                    return true;

                return false;
            }

            @Override

            public E next() {

                return list.get(current++);
            }

            @Override

            public void remove() {

                delete(list.get(current));

                list.clear();

                inorder();
            }
        }


        public void clear() {

            root = null;

            size = 0;
        }
    }



    interface Tree<E> extends Iterable<E> {

        public boolean search(E e);


        public boolean insert(E e);


        public boolean delete(E e);


        public void inorder();


        public void postorder();


        public void preorder();


        public int getSize();


        public boolean isEmpty();

        public java.util.Iterator<E> iterator();
    }

    public static void main(String[] args) {

        BST<Integer> tree = new BST<>(new Integer[] {10, 30, 55, 56, 200, 100, 456, 23, 100});

        tree.inorder();

        System.out.println();

        tree.inorder2();
    }
}