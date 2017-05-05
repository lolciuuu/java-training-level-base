package com.sda.collection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;

public class ArrayListTest {

    private ArrayList newList;

    @Before
    public void init() {
        newList = new ArrayList();
    }

    @Test
    public void shouldAddNewElement() {
        // given
        String sda = "test";

        // when
        newList.add(sda);
        // then
        Assert.assertTrue(newList.size() == 1);
    }

    @Test
    public void shouldOverSizeArray() {
        //when
        for (int i = 0; i < 11; i++) {
            newList.add(i);
        }

        //then
        Assert.assertTrue(newList.size() == 11);
    }

    @Test
    public void shouldReturnElement() {
        //given
        Integer sda = 1;

        //when
        newList.add(sda);
        Integer result = (int) newList.get(0);

        //then
        Assert.assertTrue(result == 1);
    }

    @Test
    public void shouldAddElementIndex() {
        //given
        int index = 2;
        Integer sda = 3;
        newList.add(1);
        newList.add(2);
        newList.add(4);
        newList.add(5);

        //when
        newList.add(sda, index);
        Integer result = (Integer) newList.get(2);

        //then
        Assert.assertTrue(result == 3);
        Assert.assertTrue("should be size 5", newList.size() == 5);
    }

    @Test
    public void shouldRemoveElement() {
        // given
        newList.add(1);
        newList.add(2);
        newList.add(3);

        // when

        boolean result = newList.remove(1);

        // then
        Assert.assertTrue("should remove element", result);
        Assert.assertTrue("size should be reduced", newList.size() == 2);
        Assert.assertTrue("first element of the list should be 1", (int) newList.get(0) == 1);
        Assert.assertTrue("second element of the list should be 3", (int) newList.get(1) == 3);
    }

    @Test
    public void shouldReturnCorrectString() {
        // given
        newList.add(1);
        newList.add(2);
        newList.add(3);

        // when
        String result = newList.toString();
        System.out.println(result);

        // then
        Assert.assertTrue(result.equals("[1,2,3]"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldCheckIndex() {
        // given
        newList.add(1);

        // when
        newList.add("", 3);
    }

    @Test
    public void shouldRemoveObject() {
//        given
        String string = "tekst";
        newList.add(string);
//        when
        newList.remove(string);
//        then
        Assert.assertTrue(newList.size() == 0);
    }
}
