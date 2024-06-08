package deque;

import java.util.Iterator;

import java.lang.Math;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {

    private Comparator<T> comparator;

    public MaxArrayDeque(Comparator<T> c) {

        super();
        comparator = c;
    }


    public T max() {
        return max(comparator);
    }

    public T max(Comparator<T> c) {
        if (this.isEmpty()) {
            return null;
        }

        int maxValueIndex = 0;
        for (int i = 1; i < size(); i++) {
            if (c.compare(get(maxValueIndex), get(i)) < 0) {
                maxValueIndex = i;
            }
        }

        return get(maxValueIndex);

    }


}

