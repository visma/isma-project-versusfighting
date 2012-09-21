package org.isma.web.versusfighting.model;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Grid<E> implements Iterable<Map.Entry<Point, E>> {
    private final Map<Point, E> dataMap = new HashMap<Point, E>();

    public E put(Point point, E element) {
        return dataMap.put(point, element);
    }

    public int size(){
        return dataMap.size();
    }

    public List<List<E>> getData() {
        List<List<E>> result = new ArrayList<List<E>>();
        Map.Entry<Point, E> oldElement = null;
        result.add(new ArrayList<E>());
        for (Map.Entry<Point, E> entry : this) {
            if (oldElement != null && oldElement.getKey().getY() != entry.getKey().getY()){
                result.add(new ArrayList<E>());
            }
            result.get(result.size() - 1).add(entry.getValue());
            oldElement = entry;
        }
        return result;
    }


    private class InnerIterator<F> implements Iterator<Map.Entry<Point, F>> {
        private final List<Map.Entry<Point, F>> entryList;
        private int index = 0;

        private InnerIterator(Map<Point, F> dataMap) {
            entryList = new ArrayList<Map.Entry<Point, F>>(dataMap.entrySet());
            Collections.sort(entryList, new EntryComparator());
        }

        @Override
        public boolean hasNext() {
            return index < entryList.size();
        }

        @Override
        public Map.Entry<Point, F> next() {
            return entryList.get(index++);
        }

        @Override
        public void remove() {
            throw new RuntimeException("not implemented");
        }

        private class EntryComparator implements Comparator<Map.Entry<Point, F>> {
            @Override
            public int compare(Map.Entry<Point, F> o1, Map.Entry<Point, F> o2) {
                if (o1.getKey().y != o2.getKey().y) {
                    return o1.getKey().y - o2.getKey().y;
                }
                return o1.getKey().x - o2.getKey().x;
            }
        }

    }

    @Override
    public Iterator<Map.Entry<Point, E>> iterator() {
        return new InnerIterator<E>(dataMap);
    }
}
