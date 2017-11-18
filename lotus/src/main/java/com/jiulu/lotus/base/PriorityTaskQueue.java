package com.jiulu.lotus.base;

import com.jiulu.lotus.tool.Precondition;


/**
 * Created by zhanghongjun on 2017/11/18.
 */

public class PriorityTaskQueue<E> implements TaskQueue<E> {
    //默认的数据的大小
    public static final int DEFAULT_QUEUE_SIZE = 16;

    //默认的优先级大小
    public static final int DEFAULT_PRIORITY_LEVEL = 5; // 0 - 100


    static class Entry<E> {
        int priority;
        E element;

        public Entry(int priority, E element) {
            this.priority = priority;
            this.element = element;
        }

        public Entry(int priority) {
            this(priority, null);
        }


        public Entry() {
            this(DEFAULT_PRIORITY_LEVEL);
        }
    }


    private Entry[] table;
    private int size;           //当前table大小
    private int capacity;       //table总大小

    public PriorityTaskQueue() {
        this(DEFAULT_QUEUE_SIZE);
    }

    public PriorityTaskQueue(int capacity) {
        Precondition.checkArgument(capacity > 0 && capacity < Integer.MAX_VALUE, "capacity is invalid");

        //下标从1开始，所以要多开一个
        this.capacity = capacity;
        table = new Entry[capacity + 1];
        size = 0;
    }

    @Override
    public E poll() {
        Entry entry = table[1];
        swap(1, size);
        size--;
        shiftDown(1);
        return (E) entry.element;
    }

    @Override
    public void add(E element) {
       add(element,makeNextPriority());
    }

    @Override
    public void add(E element, int priority) {
        Precondition.checkNotNull(element, "element must not be null");

        checkCapacity();

        Entry entry = new Entry(priority, element);
        table[size + 1] = entry;
        size++;

        shiftUp(size);
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    //将索引为k的元素向下翻
    private void shiftDown(int k) {
        while (2 * k <= size) {
            int j = 2 * k;
            if(j + 1 <= size && table[j + 1].priority > table[j].priority ){
                j = j + 1;
            }

            if(table[k].priority >= table[j].priority){
                break;
            }

            swap(k,j);
            k = j;
        }
    }

    //将索引为k的元素向上翻
    private void shiftUp(int k) {
        while (k > 1 && table[k].priority > table[k / 2].priority) {
            swap(k / 2, k);
            k = k / 2;
        }
    }

    //交换两个元素的位置
    private void swap(int i, int k) {
        Entry t = table[i];
        table[i] = table[k];
        table[k] = t;
    }

    //检查容量
    private void checkCapacity() {

    }

    //生成默认的优先级
    private int makeNextPriority() {
        return 0;
    }
}
