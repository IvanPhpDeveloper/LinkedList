package TheBestLinkedList;

import java.util.*;

public class TheBestLinkedList<T> implements List<T> {
    private static class Node<T> {
        public T getInfo() {
            return info;
        }

        public void setInfo(T newInfo) {
            this.info = newInfo;
        }

        public Node<T> getNext() {
            return this.next;
        }

        public void setInfo(Node<T> newNext) {
            this.next = newNext;
        }

        T info;
        Node<T> next;
        Node<T> prev;

        public Node(T info) {
            this.info = info;
            next = null;
        }
    }

    private int size = 0;
    private Node<T> head;
    private Node<T> tail;


    @Override
    public int size() {

        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean contains(Object o) {
        for (T elements : this) {
            if (o == null ? elements == null : o.equals(elements))
                return true;

        }
        return false;
    }

    class LinkedListIterator implements Iterator {

        private Node<T> currentNode;
        private Node<T> previous;
        private Node<T> previous2;
        private boolean removeCalled;

        public LinkedListIterator() {
            currentNode = head;
            previous = null;
            previous2 = null;
            removeCalled = false;
        }

        public boolean hasNext() {
            return currentNode != null;

        }

        public T next() {
            if (currentNode == null) {
                throw new NoSuchElementException();
            }
            T temp = currentNode.getInfo();
            previous2 = previous;
            previous = currentNode;
            currentNode = currentNode.getNext();
            removeCalled = false;
            return temp;
        }

        public void remove() {
            if (previous == null || removeCalled) {
                throw new IllegalStateException();
            }
            if (previous2 == null) {
                head = currentNode;
            } else {
                previous2.setInfo(currentNode);
            }
            size--;
            removeCalled = true;
        }

    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size()];
        int i = 0;
        for (T t : this) {
            array[i] = t;
            i++;

        }
        return array;
    }


    @Override
    public <T1> T1[] toArray(T1[] a) {
        Object[] result = a;
        if (size() > result.length) {
            result = new Object[size()];

        }
        int i = 0;
        for (T t : this) {
            result[i] = (T1) t;
            i++;

        }
        if (result.length > i) {
            result[i] = null;

        }

        return (T1[]) result;

    }

    @Override
    public boolean add(T t) {

//add(size-1,t);
        Node<T> newNode = new Node<>(t);
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }

        size++;
        return true;
    }


    @Override
    public boolean remove(Object o) throws NullPointerException {

        Node<T> currentNode = head;
        if (currentNode == null) {
            return false;
        }

        if (currentNode.info.equals(o)) {
            currentNode = currentNode.next;
            return true;
        } else {

            while (currentNode.next != null) {
                if (currentNode.next.info.equals(o)) {
                    currentNode.next = currentNode.next.next;
                    return true;
                }
                currentNode = currentNode.next;
            }
            return false;
        }
    }


    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!contains(o)) {
                return false;
            }
        }
        return true;
    }


    @Override
    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean modified = false;
        Iterator<T> iterator = iterator();
        while (iterator.hasNext()) {
            if (c.contains(iterator.next())) {
                iterator.remove();
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean modified = false;
        Iterator<T> iterator = iterator();
        while (iterator.hasNext()) {
            if (c.contains(iterator.next()) == false) {
                iterator.remove();
                modified = true;
            }
        }
        return modified;
    }


    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;

    }

    @Override
    public T get(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> currentNode = head;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.next;
        }
        return currentNode.info;
    }


    @Override
    public T set(int index, T element) {
        if (index < 0 || index >= size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        Node<T> currentNode = head;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.next;

        }
        currentNode.info = element;

        return element;
    }

    @Override
    public void add(int index, T element) {
        Node<T> currentNode = head;
        if (index > size) {
            throw new NullPointerException();
        }
        for (int i = 0; i < size; i++) {
            currentNode = currentNode.next;
        }
        currentNode.next = new Node<T>(element);
        currentNode.next.prev = currentNode;
    }

    @Override
    public T remove(int index) {
        Node<T> currentNode = head;
        if (index > size) {
            throw new NullPointerException();
        }
        if (index == 0) {
            T element = currentNode.info;
            currentNode = currentNode.next;
            return element;
        } else {
            for (int i = 0; i < size; i++) {
                currentNode = currentNode.next;
            }
            T element = currentNode.next.info;
            currentNode.next = currentNode.next.next;
            return element;

        }
    }


    @Override
    public int indexOf(Object o) {
        int index = 0;
        Node<T> currentNode = head;
        while (currentNode != null) {
            if (currentNode.info.equals(o)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        int index = size - 1;
        Node<T> currentNode = head;
        while (currentNode == null) {
            if (currentNode.info.equals(o)) {
                return index;
            }
            index--;
        }
        return -1;
    }


    @Override
    public ListIterator<T> listIterator() {

            return (ListIterator<T>) new TheBestLinkedList<>();
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("");
        }
        return (ListIterator<T>) new TheBestLinkedList();
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        if (fromIndex < 0 || fromIndex > size() || toIndex < fromIndex || toIndex > size()) {
            throw new IndexOutOfBoundsException();
        }
        List<T> list = new TheBestLinkedList<T>();
        Node<T> currentNode = head;
        int count = 0;
        while (currentNode != null) {
            if (count > fromIndex && count < toIndex) {
                list.add((T) currentNode);
            }
            currentNode = currentNode.next;
            count++;
        }

       return list;
    }
}

