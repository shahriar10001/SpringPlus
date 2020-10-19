package com.Application.API.Parallelism;

import SVM.ObjectValue.ObjectResult;
import com.Application.Help.ForkJoin;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;


@RestController
public class ForkJoinAPI {
    @RequestMapping(value = "/Stream/Parallelism/getForkJoin", method = RequestMethod.GET)
    public ResponseEntity<ObjectResult> getForkJoin(Long n,Long threshold) {

        long[] numbers = LongStream.rangeClosed(1, n).toArray();
        ForkJoinTask<Long> task = new ForkJoin(numbers,threshold);
        Long s= new ForkJoinPool().invoke(task);
        return ResponseEntity.ok(ObjectResult.get(s));
    }

}
