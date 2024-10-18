package deque;

/** Index:[0][1][2][3][4][5][6][7]
 *        ^nextLast               ^nextFirst
 *        for en empty deque, nextLast = 0,nextFirst =7
 *        After addFirst(item):
 *        Place item at item[7]
 *        update nextFirst to 6
 *        Deque state: FIrst item is at index 7, nextFirst now points to 6, ready for the next add First
 * @param <T>
 */

public class ArrayDeque<T> {
    private T[] items;
    private int size;

    private int nextFirst;
    private int nextLast;

    public ArrayDeque(){
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 7;
        nextLast = 0;
    }

    /**resizes teh underlying array to the target capacity*/
    /** invariants [0][1][2][3][4][5][-3][-2][-1]*/
    /**                              ^nextFirst&nextLast
    /**[-3][-2][-1][0][1][2][3][4][5][null][null][null][null][null][null][null][null]*/
    /**                              ^nextLast                                       ^nextFirst*/
    private void resize(int cap){
        T[] newItems = (T []) new Object[cap];
        int index = plusone(nextFirst);
        for (int i = 0; i < size; i++){
            newItems[i] = items[index];
            index = plusone(index);
        }
        items = newItems;
        nextFirst = cap -1;
        nextLast = size;

    }

    private void checksizeDown(){
        if(items.length >=16 && size < items.length / 4){
            resize(items.length/2);
        }
    }

    /**Helper method to calculate the index minus one, circularly*/
    private int minusOne(int index){
        return (index - 1 + items.length)%items.length;
    }

    private int plusone(int index){
        return (index +1)%items.length;
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        int index = plusone(nextFirst);
        for (int i = 0; i < size; i++){
            System.out.print(items[index] + " ");
            index = plusone(index);
        }
        System.out.println();
    }

    public boolean isempty(){
        return size==0;
    }
    public void addFirst(T item){
        if(size == items.length){
            resize(items.length*2);
        }
        items[nextFirst] = item;
        nextFirst = minusOne(nextFirst);
        size++;
    }


    public void addLast(T item){
        if (size == items.length){
            resize(items.length *2);
        }
        items[nextLast] = item;
        nextLast = plusone(nextLast);
        size++;
    }

    /**Removes and returns the item at the front of the deque, if no such items exist then return null;*/
    public T removeFirst(){
        if (isempty()){
            return null;
        }
        nextFirst = plusone(nextFirst);
        T item = items[nextFirst];
        items[nextFirst] = null;
        size--;
        checksizeDown();
        return item;
    }


    public T removeLast(){
        if (isempty()){
            return null;
        }
        nextLast = minusOne(nextLast);
        T item = items[nextLast];
        items[nextLast] = null;
        size --;
        checksizeDown();
        return item;
    }
    public T get(int index){
        if (index<0 || index>size || isempty()){
            return null;
        }
        int actualIndex = (plusone(nextFirst) + index)%items.length;
        return items[actualIndex];
    }
}
