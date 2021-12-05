///////////////////////////////////////////////////////////////////////////////
//
// File:               HeapTest.java
// Quarter:            CSE12 Winter 2021
//
// Author:             Mingyi Li
// Email:              mil011@ucsd.edu
// Instructor's Name:  Professor Miranda
//

import static org.junit.Assert.*;
import org.junit.*;

import java.util.*;

public class HeapTest {
    Comparator minHeap = new Comparator() {
        @Override
        public int compare(Object o1, Object o2) {
            if (o1.hashCode()>o2.hashCode()){
                return -1;
            }
            else if (o1.hashCode()==o2.hashCode()){
                return 0;
            }
            else {
                return 1;
            }
        }
    };
    Heap heap1 = new Heap(minHeap);

    @Test
    public void testToArray(){
        heap1.add(0,"E");
        heap1.add(1,"N");
        heap1.add(2,"R");
        heap1.add(3,"G");
        heap1.add(4,"J");

        assertEquals(5,heap1.toArray().size());
        //System.out.println(heap1.toArray().toString());
    }

    @Test
    public void test_addMin(){
        heap1.add(20,"G");
        heap1.add(10,"E");
        heap1.add(30,"N");
        heap1.add(5,"P");
        heap1.add(1,"G");
        heap1.add(22,"R");
        assertEquals(6,heap1.toArray().size());
        //System.out.println(heap1.toArray().toString());
    }

    Comparator maxHeap = new Comparator() {
        @Override
        public int compare(Object o1, Object o2) {
            if (o1.hashCode()>o2.hashCode()){
                return 1;
            }
            else if (o1.hashCode()==o2.hashCode()){
                return 0;
            }
            else {
                return -1;
            }
        }
    };
    Heap heap2 = new Heap(maxHeap);

    @Test
    public void test_addMax(){
        heap2.add(20,"a");
        heap2.add(10,"d");
        heap2.add(30,"r");
        heap2.add(5,"t");
        heap2.add(1,"a");
        heap2.add(22,"a");
        assertEquals(6,heap2.toArray().size());
        //System.out.println(heap2.toArray().toString());
    }

    @Test
    public void test_peek(){
        String[] values = {"E","N","R","G","J"};
        int[] keys = {0,1,2,3,4};
        for (int i=0;i< values.length;i++){
            heap1.add(keys[i],values[i]);
        }
        assertEquals("E",heap1.peek().value);
        assertEquals(0,heap1.peek().key);
        //System.out.println(heap1.toString());
    }

    @Test
    public void test_removeMin(){
        String[] values = {"E","N","R","G","J","A"};
        int[] keys = {20,10,30,5,1,22};
        for (int i=0;i< values.length;i++){
            heap1.add(keys[i],values[i]);
        }
        assertEquals(1,heap1.poll().key);
        assertEquals(5,heap1.size());
        /*for (int i=0;i< values.length;i++){
            System.out.println(heap1.poll());
        }*/
    }

    @Test
    public void test_removeMax(){
        String[] values = {"E","N","R","G","J","A"};
        int[] keys = {20,10,30,5,1,22};
        for (int i=0;i< values.length;i++){
            heap2.add(keys[i],values[i]);
            //System.out.println(heap2.toArray());
        }
        assertEquals(30,heap2.poll().key);
        /*for (int i=0;i< values.length;i++){
            System.out.println(heap2.poll().toString());
        } */
    }
    @Test
    public void test_isEmpty(){
        assertEquals(true,heap1.isEmpty());
        heap1.add(1,1);
        assertEquals(false,heap1.isEmpty());
        assertEquals(1,heap1.peek().value);
    }

    @Test
    public void testAdding(){
        heap2.add(90,0);
        heap2.add(65,0);
        heap2.add(50,0);
        heap2.add(30,0);
        heap2.add(25,0);
        heap2.add(20,0);
        heap2.add(10,0);
        heap2.poll();
        System.out.println(heap2.toArray().get(0));
    }

}
