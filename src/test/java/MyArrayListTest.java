import org.example.MyArrayList;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
public class MyArrayListTest {
    @Test
    public void Should_SizeEqualsZero_When_MyArrayListCreated() {
        MyArrayList<String> myArrayList = new MyArrayList<>();
        assertEquals(0, myArrayList.size());
    }
    @Test
    public void Should_SortMyArrayList_When_UseSort() {
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
    public void Should_ReturnEmptyString_When_MethodToStringToEmptyList() {
        MyArrayList<String> myArrayList = new MyArrayList<>();
        assertEquals("[]", myArrayList.toString());
    }
    @Test
    public void Should_ThrowException_When_GetNonexistentElement() {
        MyArrayList<Character> myArrayList = new MyArrayList<>(15);
        myArrayList.trimToSize();
        assertThrows(IndexOutOfBoundsException.class, () ->
                myArrayList.get(0));
    }
    @Test
    public void Should_BeEqualLists_When_UseDifferentSorts() {
        MyArrayList<Integer> myArrayList1 = new MyArrayList<>();
        MyArrayList<Integer> myArrayList2 = new MyArrayList<>();
        for (int i=0; i<2500; i++) {
            int val = i%47;
            myArrayList1.add(val);
            myArrayList2.add(val);
        }
        myArrayList1.sort(Comparator.comparingInt(o -> o));
        myArrayList2.quicksort(Comparator.comparingInt(o -> o));
        assertEquals(myArrayList1.toString(), myArrayList2.toString());
    }
    @Test
    public void Should_ReturnRequestedElement_When_UseGetMethod() {
        MyArrayList<Character> myArrayList = new MyArrayList<>();
        myArrayList.add('5');
        myArrayList.add('2');
        myArrayList.add('0');
        myArrayList.add('8');
        assertEquals('8', myArrayList.get(myArrayList.size()-1));
    }
    @Test
    public void Should_ChangeValue_When_UseSetMethod() {
        MyArrayList<Boolean> myArrayList = new MyArrayList<>();
        myArrayList.add(true);
        myArrayList.add(false);
        myArrayList.add(false);
        myArrayList.set(1,true);
        assertEquals(true, myArrayList.get(1));
    }
    @Test
    public void Should_ThrowException_When_SortWithNullComparator() {
        MyArrayList<Integer> myArrayList = new MyArrayList<>();
        myArrayList.add(2);
        myArrayList.add(0);
        myArrayList.add(-8);
        assertThrows(NullPointerException.class, () ->
                myArrayList.sort(null));
    }
    @Test
    public void Should_RemoveRequestedElements_When_UseRemoveMethod() {
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
