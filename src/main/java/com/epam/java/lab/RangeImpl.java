package com.epam.java.lab;

import java.util.*;


public class RangeImpl implements Range {

    private final Long lowerBound;
    private final Long upperBound;

    public RangeImpl(Long lowerBound, Long upperBound) {
        Objects.requireNonNull(lowerBound);
        Objects.requireNonNull(upperBound);
        if (lowerBound.compareTo(upperBound) > 0) {
            throw new IllegalArgumentException();
        }
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    public boolean isBefore(Range otherRange) {
        return upperBound.compareTo(otherRange.getLowerBound()) < 0;
    }

    public boolean isAfter(Range otherRange) {
        return lowerBound.compareTo(otherRange.getUpperBound()) > 0;
    }

    public boolean isConcurrent(Range otherRange) {
        return lowerBound.compareTo(otherRange.getLowerBound()) >= 0 && upperBound.compareTo(otherRange.getUpperBound()) <= 0
                || lowerBound.compareTo(otherRange.getUpperBound()) >= 0;
    }

    public long getLowerBound() {
        return lowerBound;
    }

    public long getUpperBound() {
        return upperBound;
    }

    public boolean contains(long value) {
        final Long box = value;
        return box.compareTo(lowerBound) >= 0 && box.compareTo(upperBound) <= 0;
    }

    public List<Long> asList() {
        final List<Long> longList = new ArrayList<>();
        for (Iterator it = asIterator(); it.hasNext(); ) {
            Long l = (Long) it.next();
            longList.add(l);
        }
        return longList;
    }

    public Iterator asIterator() {
        return new Iterator<Long>() {

            private Long start = lowerBound;

            @Override
            public boolean hasNext() {
                return start.compareTo(upperBound) <= 0;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }

            @Override
            public Long next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                } else {
                    return start++;
                }
            }
        };
    }
}
