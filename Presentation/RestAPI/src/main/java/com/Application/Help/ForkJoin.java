package com.Application.Help;

import java.util.concurrent.RecursiveTask;

public class ForkJoin  extends RecursiveTask<Long> {

    private final long[] numbers;
    private final int start;
    private final int end;
    //The size of the array under which this task is no longer  split into subtasks
    private final  long threshold ;

    //public  constructor  used to create the main task.
    public ForkJoin(long[] numbers,Long threshold) {
        this(numbers, 0, numbers.length, threshold);
    }
    //Private constructor used to recursively create subtasks of the main task.
    private ForkJoin(long[] numbers, int start, int end, long threshold) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
        this.threshold = threshold;
    }


    @Override
    protected Long compute() {

        //The size of the portion of the array summed by  this task.
        int length = end - start;

        //System.out.println("start:"+start +"=> end :"+end);

        //If the size is less than or equal to the threshold, compute the result sequentially.
        if (length < threshold)
            return computeSequentially();

        //Create a subtask to sum the first half of the array.
        ForkJoin leftTask = new ForkJoin(numbers, start, start + length/2, threshold);
        //Asynchronously "execute" the newly created subtask using another thread of the ForkJoinPool.
        leftTask.fork();
        //Create a subtask to sum the second  half of the array
        ForkJoin rightTask = new ForkJoin(numbers, start + length/2, end, threshold);

        //"Execute" this second subtask synchronously, potentially allowing further recursive splits.
        Long rightResult = rightTask.compute();

        //Read the result of the first subtask or wait for it if it isn’t ready.
        Long leftResult = leftTask.join();
        return leftResult + rightResult;
    }

    private long computeSequentially() {
        long sum = 0;
        for (int i = start; i < end; i++) {
            sum += numbers[i];
        }
        return sum;
    }


}