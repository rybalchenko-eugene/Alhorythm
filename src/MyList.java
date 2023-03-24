// односвязный список
public class MyList {
    Node Head;
    private class Node{
        int value;
        Node next;
    }

    public void add(int value){
        Node newNode = new Node();
        newNode.value = value;
        Node curr = Head;
        if (curr != null) {
            Head = newNode;
            newNode.next = curr;
        } else Head = newNode;

    }

    public void revert(){
        if(Head == null || Head.next == null) return;
        Node currentNode = Head.next;
        Node previous = Head;
        Node next = currentNode.next;
        while (currentNode != null){
            currentNode.next = previous;
            previous = currentNode;
            currentNode = next;
            if (next != null) next = next.next;
        }
        Head.next = null;
     Head = previous;

    }
    public int count(){
        Node currentNode = Head;
        int count = 0;
        while (currentNode != null){
            count++;
            currentNode = currentNode.next;
        }
        return count;
    }

    public void print(){
        Node currentNode = Head;
        while (currentNode != null){
            System.out.printf("%d ", currentNode.value);
            currentNode = currentNode.next;
        }
    }
}
