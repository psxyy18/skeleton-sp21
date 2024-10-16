package deque;
public class LinkedListDeque<T> {
        private class Node {
            public T item;
            public Node prev;
            public Node next;

            public Node(T i, Node p, Node n){
                item = i;
                prev = p;
                next = n;
            }

        }
        /**The sentinel node for the deque*/
        private Node sentinel;
        private int size;

        public LinkedListDeque(){
            sentinel = new Node(null, null, null);
            sentinel.prev = sentinel;
            sentinel.next = sentinel;
            size = 0;

        }

        /**Creates a deep copy of another deque*/
        public LinkedListDeque(LinkedListDeque<T> other){
            sentinel = new Node(null,null,null);
            size = 0;
            sentinel.prev = sentinel;
            sentinel.next = sentinel;

            //copy from another deque
            Node current = other.sentinel.next;
            while (current != other.sentinel){
                addLast(current.item);
                current = current.next;
            }

        }

        public void addFirst(T item) {
            Node newNode = new Node(item,sentinel,sentinel.next);
            sentinel.next.prev = newNode;
            sentinel.next = newNode;
            size++;
        }

        public void addLast(T item){
            Node newNode = new Node(item,sentinel.prev, sentinel);
            sentinel.prev = newNode;
            sentinel.prev.next = newNode;
            size++;
        }

        public boolean isEmpty() {
            return size==0;
        }

        public int size() {
            return size;
        }

        public void printDeque(){
            Node current = sentinel.next;
            while (current!=sentinel){
                System.out.print(current.item + " ");
                current = current.next;
            }
            System.out.println();
        }

        public T removeFirst(){
            if(isEmpty()){
                return null;
            }
            Node first = sentinel.next;
            T item = first.item;
            sentinel.next = first.next;
            first.next.prev = sentinel;
            size--;
            return item;
        }

         public T removeLast() {
            if(isEmpty()){
                return null;
            }
            Node last = sentinel.prev;
            T item = last.item;
            sentinel.prev = last.prev;
            last.prev.next = sentinel;
            size--;
            return item;
         }

         public T get(int index){
            if (index<0 || index >= size){
                return null;
            }
            Node current = sentinel.next;
            for (int i = 0; i < index; i++){
                current = current.next;
            }
            return current.item;
         }

         public T getRecursive(int index) {
            if (index<0 || index >=size){
                return null;
            }
            return getRecursiveHelper(sentinel.next, index);
         }

         private T getRecursiveHelper(Node node, int index){
            if (index == 0){
                return node.item;
            }else{
                return getRecursiveHelper(node.next, index -1);
            }
         }



    }