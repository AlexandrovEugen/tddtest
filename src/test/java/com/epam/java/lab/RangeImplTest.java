package com.epam.java.lab;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class RangeImplTest {


    private Range range;


    @Before
    public void setUp() throws Exception {
         range = new RangeImpl(4L, 10L);
    }

    @After
    public void tearDown() throws Exception {
        range = null;
    }


    @Test(expected = NullPointerException.class)
    public void nullArgs(){
        final Range rangeforFail = new RangeImpl(null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void initializeFail(){
       final Range rangeforFail = new RangeImpl(2L, 1L);
    }

    @Test
    public void getLowerBound() throws Exception {
        long lowerBound = range.getLowerBound();
        assertThat(lowerBound, is(equalTo(4L)));
    }

    @Test
    public void getUpperBound() throws Exception {
        long upperBound = range.getUpperBound();
        assertThat(upperBound, is(equalTo(10L)));
    }

    @Test
    public void isBefore() throws Exception {
        final Range otherRange = new RangeImpl(11L, 19L);
        boolean before = range.isBefore(otherRange);
        assertThat(before, is(true));
    }

    @Test
    public void isAfter() throws Exception {
        final Range otherRange = new RangeImpl(1L, 3L);
        boolean before = range.isAfter(otherRange);
        assertThat(before, is(true));
    }

    @Test
    public void isConcurrent() throws Exception {
        final Range otherRange = new RangeImpl(4L, 10L);
        boolean isConcurrent = range.isConcurrent(otherRange);
        assertThat(isConcurrent, is(true));
    }

    @Test
    public void contains() throws Exception {
        boolean contains = range.contains(7L);
        assertThat(contains, is(true));
    }

    @Test
    public void asList() throws Exception {
        final List<Long> expectedList = Arrays.asList(4L, 5L, 6L, 7L, 8L, 9L, 10L);
        final List<Long> actualList = range.asList();
        final int size = actualList.size();
        assertThat(size, is(equalTo(7)));
        for (Long l :expectedList) {
            boolean isContains = actualList.contains(l);
            assertThat(isContains, is(true));
        }
    }

    @Test
    public void asIterator() throws Exception {
        Iterator longIterator = range.asIterator();
        boolean b = longIterator.hasNext();
        assertThat(b, is(true));
        Long next = (Long) longIterator.next();
        assertThat(next, is(equalTo(4L)));
    }

}