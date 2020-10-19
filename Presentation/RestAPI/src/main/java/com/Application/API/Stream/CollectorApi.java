package com.Application.API.Stream;

import IService.Business.Common.IJobService;
import SVM.ObjectValue.ObjectResult;
import com.Application.PVM.Common.CreateDataSet;
import com.Application.PVM.Common.DishPvm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingInt;


@RestController
public class CollectorApi {

    @Autowired
    IJobService service;

    @RequestMapping(value = "/Stream/Collect/GroupBy", method = RequestMethod.GET)
    public ResponseEntity<ObjectResult> GroupBy() {
        List<DishPvm> dishPvms = CreateDataSet.getMenu();
        Map<DishPvm.Type, List<DishPvm>> menu =
                dishPvms.stream().collect(Collectors.groupingBy(x -> {
                    return x.getType();
                }));

        return ResponseEntity.ok(
                ObjectResult.get(menu)
        );
    }

    @RequestMapping(value = "/Stream/Collect/GroupByTwoLeverl", method = RequestMethod.GET)
    public ResponseEntity<ObjectResult> GroupByTwoLeverl() {
        List<DishPvm> dishPvms = CreateDataSet.getMenu();
        Map<DishPvm.Type, Map<String, List<DishPvm>>> menu =
                dishPvms.stream().collect(
                        Collectors.groupingBy(x -> x.getType(),
                                Collectors.groupingBy(x -> {
                                            if (x.getPrice() > 450) return "expeincive";
                                            return "cheap";
                                        }
                                )));

        return ResponseEntity.ok(ObjectResult.get(menu));
    }

    @RequestMapping(value = "/Stream/Collect/getCount", method = RequestMethod.GET)
    public ResponseEntity<ObjectResult> getCount() {
        List<DishPvm> dishPvms = CreateDataSet.getMenu();
        Map<DishPvm.Type, Long> menu =
                dishPvms.stream().collect(
                        Collectors.groupingBy(
                                x -> x.getType(),
                                Collectors.counting()
                        ));

        return ResponseEntity.ok(ObjectResult.get(menu));
    }

    @RequestMapping(value = "/Stream/Collect/collectingAndThenForMaxBy", method = RequestMethod.GET)
    public ResponseEntity<ObjectResult> collectingAndThenForMaxBy() {
        List<DishPvm> dishPvms = CreateDataSet.getMenu();
        Map<DishPvm.Type, DishPvm> menu =
                dishPvms.stream().collect(
                        Collectors.groupingBy(
                                x -> x.getType(),
                                Collectors.collectingAndThen(
                                        Collectors.maxBy(comparingInt(DishPvm::getPrice)),
                                        x -> x.get()
                                )
                        ));

        return ResponseEntity.ok(ObjectResult.get(menu));
    }

    @RequestMapping(value = "/Stream/Collect/collectingAndThenForSummaryStatistics1", method = RequestMethod.GET)
    public ResponseEntity<ObjectResult> collectingAndThenForSummaryStatistics1() {
        List<DishPvm> dishPvms = CreateDataSet.getMenu();
        Map<DishPvm.Type, IntSummaryStatistics> menu =
                dishPvms.stream().collect(
                        Collectors.groupingBy(
                                x -> x.getType(),
                                Collectors.collectingAndThen(
                                        Collectors.summarizingInt(x -> x.getPrice()),
                                        x -> x
                                )
                        ));

        return ResponseEntity.ok(ObjectResult.get(menu));
    }

    @RequestMapping(value = "/Stream/Collect/partitioningBy", method = RequestMethod.GET)
    public ResponseEntity<ObjectResult> partitioningBy() {
        List<DishPvm> dishPvms = CreateDataSet.getMenu();
        //partitioningBy ==> Map<Boolean,List<T>>
        Map<Boolean, List<DishPvm>> menu =
                dishPvms.stream().collect(Collectors.partitioningBy(x -> x.getPrice() > 400));
        return ResponseEntity.ok(ObjectResult.get(menu));
    }


}
