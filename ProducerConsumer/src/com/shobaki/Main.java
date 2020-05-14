
// using ArrayBlockingQueue instead of List

package com.shobaki;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

import static com.shobaki.Main.EOF;

public class Main {
    public static final String EOF = "EOF";

    public static void main(String[] args) {
        ArrayBlockingQueue<String> buffer = new ArrayBlockingQueue<>(6);

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        MyProducer producer = new MyProducer(buffer, ThreadColor.ANSI_YELLOW);
        MyConsumer consumer1 = new MyConsumer(buffer, ThreadColor.ANSI_PURPLE);
        MyConsumer consumer2 = new MyConsumer(buffer, ThreadColor.ANSI_CYAN);

        executorService.execute(producer);
        executorService.execute(consumer1);
        executorService.execute(consumer2);

        Future<String> future = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println(ThreadColor.ANSI_WHITE + "I'm being printed for the Callable class");
                return "This is the callable result";
            }
        });

        try {
            System.out.println(future.get());
        } catch (ExecutionException e) {
            System.out.println("Something went wrong");
        } catch (InterruptedException e) {
            System.out.println("Thread running the task was interrupted");
        }
        executorService.shutdown();

    }
}

class MyProducer implements Runnable {
    private ArrayBlockingQueue<String> buffer;
    private String color;

    public MyProducer(ArrayBlockingQueue<String> buffer, String color) {
        this.buffer = buffer;
        this.color = color;
    }

    public void run() {
        Random random = new Random();
        String[] nums = {"1", "2", "3", "4", "5"};
        for (String num : nums) {
            try {
                System.out.println(color + "Adding..." + num);
                buffer.put(num);

                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                System.out.println("Producer was interrupted");
            }
        }
        System.out.println(color + "Adding EOF and exiting...");
        try {
            buffer.put("EOF");
        } catch (InterruptedException e) {

        }

    }
}

class MyConsumer implements Runnable {
    private ArrayBlockingQueue<String> buffer;
    private String color;

    public MyConsumer(ArrayBlockingQueue<String> buffer, String color) {
        this.buffer = buffer;
        this.color = color;
    }

    public void run() {
        while (true) {
            synchronized (buffer) {
                try {
                    if (buffer.isEmpty()) {
                        continue;
                    }
                    if (buffer.peek().equals(EOF)) {
                        System.out.println(color + "Exiting");
                        break;
                    } else {
                        System.out.println(color + "Removed " + buffer.take());
                    }
                } catch (InterruptedException e) {
                }
            }

        }
    }
}

//**********************************************

//package com.shobaki;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;
//import java.util.concurrent.*;
//import java.util.concurrent.locks.ReentrantLock;
//
//import static com.shobaki.Main.EOF;
//
//public class Main {
//    public static final String EOF = "EOF";
//
//    public static void main(String[] args) {
//        List<String> buffer = new ArrayList<>();
//        ReentrantLock bufferLock = new ReentrantLock();
//        //we replaced synchronized methods by bufferLock because by using synchronized methods will hold all the thread,
//        //but bufferLock will hold the threads who share the same task
//
//        //update using a fix thread pool. Its vital for applications that use a large number of threads because
//        //using them allow to jvm to optimize thread management
//        ExecutorService executorService = Executors.newFixedThreadPool(5);
//
//        MyProducer producer = new MyProducer(buffer, ThreadColor.ANSI_YELLOW, bufferLock);
//        MyConsumer consumer1 = new MyConsumer(buffer, ThreadColor.ANSI_PURPLE, bufferLock);
//        MyConsumer consumer2 = new MyConsumer(buffer, ThreadColor.ANSI_CYAN, bufferLock);
//
//        executorService.execute(producer);
//        executorService.execute(consumer1);
//        executorService.execute(consumer2);
//
//        Future<String> future = executorService.submit(new Callable<String>() {
//            @Override
//            public String call() throws Exception {
//                System.out.println(ThreadColor.ANSI_WHITE  + "I'm being printed for the Callable class");
//                return "This is the callable result";
//            }
//        });
//
//        try{
//            System.out.println(future.get());
//        }catch(ExecutionException e){
//            System.out.println("Something went wrong");
//        }catch(InterruptedException e){
//            System.out.println("Thread running the task was interrupted");
//        }
//        executorService.shutdown();
//
////        new Thread(producer).start();
////        new Thread(consumer1).start();
////        new Thread(consumer2).start();
//
//
//    }
//}
//
//
//
//
//
////update the following to use try finally block
//
//class MyProducer implements Runnable {
//    private List<String> buffer;
//    private String color;
//    private ReentrantLock bufferLock;
//
//    public MyProducer(List<String> buffer, String color, ReentrantLock bufferLock) {
//        this.buffer = buffer;
//        this.color = color;
//        this.bufferLock = bufferLock;
//    }
//
//    public void run() {
//        Random random = new Random();
//        String[] nums = {"1", "2", "3", "4", "5"};
//        for (String num : nums) {
//            try {
//                System.out.println(color + "Adding..." + num);
//                bufferLock.lock();
//                try {
//                    buffer.add(num);
//                } finally {
//                    bufferLock.unlock();
//                }
//
//                Thread.sleep(random.nextInt(1000));
//            } catch (InterruptedException e) {
//                System.out.println("Producer was interrupted");
//            }
//        }
//        System.out.println(color + "Adding EOF and exiting...");
//        bufferLock.lock();
//        try {
//            buffer.add("EOF");
//        } finally {
//            bufferLock.unlock();
//        }
//
//
//    }
//}
//
//class MyConsumer implements Runnable {
//    private List<String> buffer;
//    private String color;
//    private ReentrantLock bufferLock;
//
//    public MyConsumer(List<String> buffer, String color, ReentrantLock bufferLock) {
//        this.buffer = buffer;
//        this.color = color;
//        this.bufferLock = bufferLock;
//    }
//
//    public void run() {
//        int counter =0;
//        while (true) {
////            bufferLock.lock();
//            //trying if statement with tryLock() instead of bufferLock.lock()
//            if (bufferLock.tryLock()) {
//                try {
//                    if (buffer.isEmpty()) {
//                        continue;
//                    }
//                    System.out.println(color + "The counter = " + counter);
//                    counter =0;
//                    if (buffer.get(0).equals(EOF)) {
//                        System.out.println(color + "Exiting");
//                        break;
//                    } else {
//                        System.out.println(color + "Removed " + buffer.remove(0));
//                    }
//                } finally {
//                    bufferLock.unlock();
//                }
//            }else{
//                counter++;
//            }
//        }
//    }
//}

//******************************************************************

//we mis used buferrLock and we need to update the Producer and Consumer to use try finally block

//class MyProducer implements Runnable {
//    private List<String> buffer;
//    private String color;
//    private ReentrantLock bufferLock;
//
//    public MyProducer(List<String> buffer, String color, ReentrantLock bufferLock) {
//        this.buffer = buffer;
//        this.color = color;
//        this.bufferLock = bufferLock;
//    }
//
//    public void run() {
//        Random random = new Random();
//        String[] nums = {"1", "2", "3", "4", "5"};
//        for (String num : nums) {
//            try {
//                System.out.println(color + "Adding..." + num);
//                bufferLock.lock();
//                buffer.add(num);
//                bufferLock.unlock();
////                synchronized (buffer) { //we removed this block when we added bufferLock
////                    buffer.add(num);
////                }
//
//
//                Thread.sleep(random.nextInt(1000));
//            } catch (InterruptedException e) {
//                System.out.println("Producer was interrupted");
//            }
//        }
//        System.out.println(color + "Adding EOF and exiting...");
//        bufferLock.lock();
//        buffer.add("EOF");
//        bufferLock.unlock();
////        synchronized (buffer) { //we removed this block when we added bufferLock
////            buffer.add("EOF");
////        }
//
//
//    }
//}
//
//class MyConsumer implements Runnable {
//    private List<String> buffer;
//    private String color;
//    private ReentrantLock bufferLock;
//
//    public MyConsumer(List<String> buffer, String color, ReentrantLock bufferLock) {
//        this.buffer = buffer;
//        this.color = color;
//        this.bufferLock = bufferLock;
//    }
//
//    public void run() {
//        while (true) {
////            synchronized (buffer) {we removed this block when we added bufferLock
//            bufferLock.lock();
//            if (buffer.isEmpty()) {
//                bufferLock.unlock();
//                continue;
//            }
//            if (buffer.get(0).equals(EOF)) {
//                System.out.println(color + "Exiting");
//                bufferLock.unlock();
//                break;
//            } else {
//                System.out.println(color + "Removed " + buffer.remove(0));
//            }
//            bufferLock.unlock();
////            }
//
//        }
//    }
//}