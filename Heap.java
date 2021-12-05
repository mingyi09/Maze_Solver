///////////////////////////////////////////////////////////////////////////////
//
// File:               Heap.java
// Quarter:            CSE12 Winter 2021
//
// Author:             Mingyi Li
// Email:              mil011@ucsd.edu
// Instructor's Name:  Professor Miranda
//

import java.util.List;
import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.Comparator;

public class Heap<K extends Comparable<? super K>,V> implements PriorityQueue {
    public List<Entry<K,V>> entries;
    public Comparator<K> comparator;

    public Heap(Comparator<K> comparator){
        this.entries = new ArrayList<>();
        this.comparator = comparator;
    }

    /**
     * add the entry pair to the priority queue
     *
     * @param o1 The key of the entry
     * @param o2 The value of the entry
     */
    @Override
    public void add(Object o1, Object o2){
        if (this.entries.size()==0){
            this.entries.add(null); //no entry at index 0
        }
        Entry inserted = new Entry(o1,o2);
        this.entries.add(inserted);
        bubbleUp(size()); //add to the end, then move up
    }

    /**
     * remove and get the root entry in the priority queue
     *
     * @return the root entry of the heap
     */
    @Override
    public Entry<K,V> poll() {
        if (isEmpty()){
            throw new NoSuchElementException();
        }
        if (size()==1){
            return entries.remove(1);
        }
        Entry<K,V> last = entries.get(size());
        Entry<K,V> root = entries.remove(1); //remove the root
        entries.add(1,last); //add the root to be the last
        entries.remove(size()); //remove the last
        bubbleDown(1); //move down the entry
        return root;
    }

    /**
     * get without removing the root entry of the heap
     *
     * @return the root entry of the heap
     */
    @Override
    public Entry<K,V> peek() {
        if (isEmpty()){
            throw new NoSuchElementException();
        }
        return entries.get(1); //the root entry
    }

    /**
     * Get a list of entries in the heap
     *
     * @return the list of entries in the heap
     */
    @Override
    public List<Entry<K,V>> toArray() {
        List<Entry<K,V>> list = new ArrayList<>();
        for (Entry<K,V> e: entries){
            if (e!=null){ //avoid adding the first entry (null)
                list.add(e);
            }
        }
        return list;
    }
    ///

    /**
     * check if the List of entries is empty
     *
     * @return true if empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return size()==0;
    }

    //additional method
    /**
     * The parent entry of the given entry at the index
     *
     * @param index the index of which its parent to be found
     * @return the index of the parent entry
     */
    public int parent(int index){
        return index/2;
    }
    /**
     * The left child entry of the given entry at the index
     *
     * @param index the index of which its left child to be found
     * @return the index of the left child entry
     */
    public int left(int index){
        return 2*index;
    }
    /**
     * The right child entry of the given entry at the index
     *
     * @param index the index of which its right child to be found
     * @return the index of the right child entry
     */
    public int right(int index){
        return 2*index+1;
    }
    /**
     * Swap the entries at two indices
     *
     * @param i1 the index of which to be swapped
     * @param i2 the index of which to be swapped
     */
    public void swap(int i1, int i2){
        if(i1>size()||i2>size()){
            return; //if index out of bound is swapped
        }
        Entry<K,V> one = entries.get(i1);
        entries.set(i1,entries.get(i2));
        entries.set(i2,one);
    }

    /**
     * Moves the entry at the specified `index` to a smaller index
     * (up the tree) while maintaining the heap structure
     *
     * @param index the index of which to be moved up
     */
    public void bubbleUp(int index){
        if (index<=1){ //root
            return;
        }
        if (existsAndGreater(index,parent(index))){
            swap(index,parent(index));
            bubbleUp(parent(index));
        }
        else{
            return;
        }
    }

    /**
     * Moves the entry at the specified `index` to a larger index
     * (down the tree) while maintaining the heap structure
     *
     * @param index the index of which to be moved down
     */
    public void bubbleDown(int index){
        if (index>=size()){ //if reaches the last
            return;
        }
        if (left(index)>size()){ //the bottom of tree
            return;
        }
        int largerChildIndex = left(index);
        //check which child has higher priority
        if (existsAndGreater(right(index),left(index))){
            largerChildIndex = right(index);
        }
        //compare the parent with the higher child
        if (existsAndGreater(largerChildIndex,index)){
            swap(index,largerChildIndex);
            bubbleDown(largerChildIndex);
        }
    }

    /**
     *
     * Returns true if the entry at index1 is greater than that at index2
     *
     * @param index1 the index of which to compare
     * @param index2 the index of which to be compared
     */
    public boolean existsAndGreater(int index1, int index2){
        if(index1>size()||index2>size()){
            return false;
        }
        Entry<K,V> one = entries.get(index1);
        Entry<K,V> two = entries.get(index2);
        int result = comparator.compare(one.key,two.key);
        return result>0;
    }

    /**
     * Get the number of elements in entries.
     * If there's a null, ignore the null element.
     *
     * @@return the number of entries
     */
    public int size(){
        if (entries.size()==0){
            return 0;
        }
        return entries.size()-1; //eliminate the null entry
    }

    /**
     * a string representation of the elements in entries
     *
     * @@return the String that contains every entry in the heap
     */
    public String toString(){
        String result = "";
        String space = " ";
        for (Entry<K,V> e: entries){
            if (e!=null) {
                result += e.toString() + space;
            }
        }
        return result;
    }

}

class Entry<K, V> {
    K key; // aka the _priority_
    V value;
    public Entry(K k, V v) { this.key = k; this.value = v; }
    public String toString() {
        return key + ": " + value;
    }
}

