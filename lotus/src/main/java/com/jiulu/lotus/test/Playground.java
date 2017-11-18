package com.jiulu.lotus.test;

import com.jiulu.lotus.base.FixedThreadPool;
import com.jiulu.lotus.base.ThreadPool;

/**
 * 测试类
 */

public class Playground {

    public static void main(String[] args) throws InterruptedException {
//        PriorityTaskQueue<Student> queue = new PriorityTaskQueue();
//        queue.add(new Student("tom",45),45);
//        queue.add(new Student("jim",33),33);
//        queue.add(new Student("wendy",56),56);
//        queue.add(new Student("selinx",99),99);
//
//        while (!queue.isEmpty()){
//            System.out.print(queue.poll().toString() + "  ");
//        }


        ThreadPool pool = new FixedThreadPool(3);
        pool.start();

        for (int i = 0; i < 7; i++) {
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    String name = Thread.currentThread().getName();
                    try {
                        Thread.currentThread().sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(name + "   做完了");
                }
            });

        }



        Thread.currentThread().sleep(10 * 1000);
        pool.shutdown();

    }


}
