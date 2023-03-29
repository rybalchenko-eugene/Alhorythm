public class Main {
    // Задание №2 разворот списка
    public static void main(String[] args) {

//        MyList L = new MyList();
//
//        int res;
//        for (int i = 0; i < 10; i++) {
//            res = (int) (Math.random() * 100);
//            L.add(res);
//        }
//        L.print();
//        L.revert();
//        System.out.println();
//        L.print();
    BinaryTree tree = new BinaryTree();

        for (int i = 1; i < 10; i++) {
            tree.add(i);
        }
    tree.printTree(tree.root, 0);
    }


}