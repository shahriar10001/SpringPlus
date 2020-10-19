package com.Application.API.Stream;

import IService.Business.Common.IJobService;
import SVM.ObjectValue.ObjectResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JoinAPI {

    private  String s;

    @Autowired
    IJobService _jobService;

    @RequestMapping(value = "/Stream/Collect/InnerJoinTwoList", method = RequestMethod.GET)
    public ResponseEntity<ObjectResult> InnerJoinTwoList(String s) {
        this.s = s;
        return ResponseEntity.ok(ObjectResult.get("ok"));
    }
}
