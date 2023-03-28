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
        else add(root, value);
    }

    // Добавление узла
    private void add(Node node, int value){
//        Node node = new Node();
//        node.value = value;
//        node.Color = nodeColor.Red; // добавим цвет
            if (node.value > value){
                if (node.left != null){
                    add(node.left, value);
                    node.left = recalc(node.left);
                    return;
                }
                else {
                    node.left = new Node();
                    node.left.Color = nodeColor.Red;
                    node.left.value = value;
                    return;
                }
            } else {
                if (node.right != null){
                    add(node.right, value);
                    node.right = recalc(node.right);
                    return;
                }
                else {
                    node.right = new Node();
                    node.right.Color = nodeColor.Red;
                    node.right.value = value;
                    return;
                }
            }
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
        Node result = node;
        boolean check;
        do {
            check = false;
            if ((node.right != null && node.right.Color == nodeColor.Red) &&
                    (node.left == null || node.left.Color == nodeColor.Black)) {
                result = rightTurn(result);
                check = true;
            }
            if (node.left != null && node.left.Color == nodeColor.Red &&
                    node.left.left != null && node.left.left.Color == nodeColor.Red){
                result = leftTurn(result);
                check = true;
            }
            if (node.left != null && node.left.Color == nodeColor.Red &&
                    node.right != null && node.right.Color == nodeColor.Red){
                swipe(result);
                check = true;
            }
        } while (check);
        return result;
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

    public void printTree(Node node) {
            if (node != null) {
                System.out.print(" Node {" + node.value + " color:" + node.Color + "}");
                printTree(node.left);
                printTree(node.right);
            } else System.out.println();

    }
}
