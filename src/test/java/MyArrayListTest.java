import org.example.MyArrayList;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
public class MyArrayListTest {
    @Test
    public void myArrayListCreation() {
        MyArrayList<String> myArrayList = new MyArrayList<>();
        assertEquals(0, myArrayList.size());
    }
    @Test
    public void myArrayListZeroCapacity() {
        MyArrayList<Double> myArrayList = new MyArrayList<>(0);
        myArrayList.add(0.5);
        myArrayList.add((double)1/3);
        myArrayList.add(-0.5);
        myArrayList.sort((o1, o2) -> {
            if (Objects.equals(o1, o2)) return 0;
            if (o1>o2) return 1;
            return -1;
        });
        assertEquals("[-0.5, " + (double)1/3 + ", 0.5]", myArrayList.toString());
    }
    @Test
    public void emptyMyArrayListToString() {
        MyArrayList<String> myArrayList = new MyArrayList<>();
        assertEquals("[]", myArrayList.toString());
    }
    @Test
    public void getElementFromEmptyList() {
        MyArrayList<Character> myArrayList = new MyArrayList<>(15);
        myArrayList.trimToSize();
        assertThrows(IndexOutOfBoundsException.class, () ->
                myArrayList.get(0));
    }
    @Test
    public void bigDataSorts() {
        MyArrayList<Integer> myArrayList1 = new MyArrayList<>();
        MyArrayList<Integer> myArrayList2 = new MyArrayList<>();
        for (int i=0; i<250; i++) {
            int val = i%37;
            myArrayList1.add(val);
            myArrayList2.add(val);
        }
        myArrayList1.sort(Comparator.comparingInt(o -> o));
        myArrayList2.quicksort(Comparator.comparingInt(o -> o));
        assertEquals(myArrayList1.toString(), myArrayList2.toString());
    }
    @Test
    public void addElement() {
        MyArrayList<Character> myArrayList = new MyArrayList<>();
        myArrayList.add('5');
        myArrayList.add('2');
        myArrayList.add('0');
        myArrayList.add('8');
        assertEquals('8', myArrayList.get(myArrayList.size()-1));
    }
    @Test
    public void setElement() {
        MyArrayList<Boolean> myArrayList = new MyArrayList<>();
        myArrayList.add(true);
        myArrayList.add(false);
        myArrayList.add(false);
        myArrayList.set(1,true);
        assertEquals(true, myArrayList.get(1));
    }
    @Test
    public void nullComparator() {
        MyArrayList<Integer> myArrayList = new MyArrayList<>();
        myArrayList.add(2);
        myArrayList.add(0);
        myArrayList.add(-8);
        myArrayList.sort(null);
        assertEquals("[-8, 0, 2]", myArrayList.toString());
    }
    @Test
    public void removeTest() {
        MyArrayList<Integer> myArrayList = new MyArrayList<>();
        myArrayList.add(1);
        myArrayList.add(2);
        myArrayList.add(3);
        myArrayList.add(4);
        myArrayList.add(5);
        myArrayList.remove(0);
        myArrayList.remove((Integer)2);
        myArrayList.remove(0);
        assertEquals("[4, 5]", myArrayList.toString());
    }
}
