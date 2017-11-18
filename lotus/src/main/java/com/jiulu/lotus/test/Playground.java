package com.jiulu.lotus.test;

import com.jiulu.lotus.base.PriorityTaskQueue;

/**
 * 测试类
 */

public class Playground {

    public static void main(String[] args){
        PriorityTaskQueue<Student> queue = new PriorityTaskQueue();
        queue.add(new Student("tom",45),45);
        queue.add(new Student("jim",33),33);
        queue.add(new Student("wendy",56),56);
        queue.add(new Student("selinx",99),99);

        while (!queue.isEmpty()){
            System.out.print(queue.poll().toString() + "  ");
        }


    }


}
