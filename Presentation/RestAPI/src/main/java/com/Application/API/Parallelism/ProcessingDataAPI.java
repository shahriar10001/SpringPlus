package com.Application.API.Parallelism;

import SVM.ObjectValue.ObjectResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.LongStream;

@RestController
public class ProcessingDataAPI {

    @RequestMapping(value = "/Stream/parallel/wParallel", method = RequestMethod.GET)
    public   ResponseEntity<ObjectResult> getWithoutParallel(Long n) {

        Object result = LongStream.rangeClosed(1, n)
                .reduce(0L, Long::sum);

        return ResponseEntity.ok(ObjectResult.get(result));
    }
    @RequestMapping(value = "/Stream/parallel/Parallel", method = RequestMethod.GET)
    public   ResponseEntity<ObjectResult> getParallel(Long n) {

        Object result = LongStream.rangeClosed(1, n)
                .parallel()
                .reduce(0L, Long::sum);

        return ResponseEntity.ok(ObjectResult.get(result));
    }

    //avoiding shared mutable state
    @RequestMapping(value = "/Stream/parallel/sharedMutableState", method = RequestMethod.GET)
    public   ResponseEntity<ObjectResult> sharedMutableState(Long n) {
        AtomicLong total = new AtomicLong();
        total.set(0);
         LongStream.rangeClosed(1, n).parallel().forEach(x->{
            total.set(total.get() + x);
        });
        return ResponseEntity.ok(ObjectResult.get(total));
    }

    //a parallel stream isn’t always faster than the corresponding sequential version.



}
