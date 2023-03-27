import java.awt.*;

public class BinaryTree {
    Node root;
    private class Node{
        int value;
        Node left;
        Node right;
        nodeColor Color;
    }

    // Добавление узла
    public void add(int value){
        Node node = new Node();
        node.value = value;
        node.Color = nodeColor.Red; // добавим цвет


        if(root == null){
            root = node;
        }else{
            Node cur = root;
            while(cur != null){
                if(value < cur.value) {
                    if(cur.left == null){
                        cur.left = node;
                        recalc(cur); // после добавления сбалансируем дерево
                        break;
                    }
                    cur = cur.left;
                }
                else {
                    if(cur.right == null){
                        cur.right = node;
                        recalc(cur);// после добавления сбалансируем дерево
                        break;
                    }
                    cur = cur.right;
                }
            }
        }
    }
    public enum nodeColor{
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

    public void recalc(Node parent) {
        if (parent.right.Color == nodeColor.Red){
            leftTurn(parent);
        }
        if(parent.Color == nodeColor.Red && parent.left.Color == nodeColor.Red){
            rightTurn(parent);
        }
        if(parent.left.Color == nodeColor.Red && parent.right.Color == nodeColor.Red){
            swipe(parent);
        }
    }
    public void leftTurn (Node parent){
        Node temp = parent;
        temp.right = parent.right.left;
        temp.Color = nodeColor.Red;
        nodeColor tempColorParent = parent.Color;
        parent = parent.right;
        parent.left = temp;
        parent.Color = tempColorParent;
        if(parent == root){
            parent.Color = nodeColor.Black;
        }
    }
    public void rightTurn (Node parent){
        Node temp = parent;
        temp.left = parent.left.right;
        temp.Color = nodeColor.Red;
        nodeColor tempColorParent = parent.Color;
        parent = parent.left;
        parent.right = temp;
        parent.Color = tempColorParent;
        if(parent == root){
            parent.Color = nodeColor.Black;
        }
    }
    public void swipe(Node parent){
        parent.Color = nodeColor.Red;
        parent.left.Color = nodeColor.Black;
        parent.right.Color = nodeColor.Black;
        if(parent == root){
            parent.Color = nodeColor.Black;
        }
    }
}
