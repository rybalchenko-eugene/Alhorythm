import javax.sound.midi.Soundbank;
import javax.swing.*;
import java.awt.*;

public class BinaryTree {
    Node root;
    private class Node{
        int value;
        Node left;
        Node right;
        nodeColor Color;
    }
    public void add(int value) {
        if (root == null){
            root = new Node();
            root.value = value;
            root.Color = nodeColor.Black;
        }
        else {
            add(root, value);
            root = recalc(root);
            root.Color = nodeColor.Black;
        }
    }

    // Добавление узла
    private void add(Node node, int value){
            if (node.value > value){
                if (node.left != null){
                    add(node.left, value);
                    node.left = recalc(node.left);
                }
                else {
                    node.left = new Node();
                    node.left.Color = nodeColor.Red;
                    node.left.value = value;

                }
            } else {
                if (node.right != null){
                    add(node.right, value);
                    node.right = recalc(node.right);
                }
                else {
                    node.right = new Node();
                    node.right.Color = nodeColor.Red;
                    node.right.value = value;
                }
            }
            return;
    }
    private enum nodeColor{
        Black,
        Red
    }

    // Поиск узла по значению
    public boolean find(int value){
        if(root != null){
            Node cur = root;
            while(cur != null){
                if(cur.value == value)
                    return true;
                if(value < cur.value) {
                    cur = cur.left;
                }
                else {
                    cur = cur.right;
                }
            }
        }
        return false;
    }
    private Node findParent(Node Child) {
        Node temp = root;
        if (Child == root) return null;
        while (temp != null) {
            if (temp.right == Child || temp.left == Child) break;
            else
                if (Child.value < temp.value)
                    temp = temp.left;
                else temp = temp.right;
        }
        return temp;
    }
    private Node findBrother(Node Child) {
        Node temp = root;
        if (Child == root) return null;
        Node parent = findParent(Child);
        if (parent.left == Child) return parent.right;
        else return parent.left;
    }

    private Node recalc(Node node) {
        Node res = node;
        boolean need_recalc;
        do {
            need_recalc = false;

            // если справа дочерняя красная, то правый поворот
            if (res.right != null && res.right.Color == nodeColor.Red &&
                    (res.left == null || res.left.Color == nodeColor.Black)) {
                res = rightTurn(res);
                need_recalc = true;
            }
            // если левая дочерняя красная и родитель красный, то правый поворот
            if (res.left != null && res.left.Color == nodeColor.Red &&
            res.left.left != null && res.left.left.Color == nodeColor.Red) {
                res = leftTurn(res);
                need_recalc = true;
            }
            // если два дочерних красные, то свайп цвета
            if (res.left != null && res.right != null &&
                    res.left.Color == nodeColor.Red && res.right.Color == nodeColor.Red) {
                swipe(res);
                need_recalc = true;
            }
        } while (need_recalc);
        return res;
    }
    private Node leftTurn (Node parent){
        Node left = parent.left;
        Node temp = left.right;
        left.right = parent;
        parent.left = temp;
        left.Color = parent.Color;
        parent.Color = nodeColor.Red;
        return left;
    }
    private Node rightTurn (Node parent){
        Node right = parent.right;
        Node temp = right.left;
        right.left = parent;
        parent.right = temp;
        right.Color = parent.Color;
        parent.Color = nodeColor.Red;
        return right;
    }
    private void swipe(Node parent){
        parent.Color = nodeColor.Red;
        parent.left.Color = nodeColor.Black;
        parent.right.Color = nodeColor.Black;

    }

    public void printTree(Node node, int level) {
            if (node != null) {
                System.out.print(" Node {" + node.value + " color:" + node.Color + "}Level =" + level);
                level++;
                printTree(node.left, level);
                printTree(node.right, level);
            } else System.out.println();
    }
}
