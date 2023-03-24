public class Main {
    // Задание №2 разворот списка
    public static void main(String[] args) {
        System.out.println("Hello world!");

        MyList L = new MyList();

        int res;
        for (int i = 0; i < 10; i++) {
            res = (int) (Math.random() * 100);
            L.add(res);
        }
        L.print();
        L.revert();
        System.out.println();
        L.print();

    }


}