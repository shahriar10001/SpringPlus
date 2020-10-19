package Inbound.Common;

import IService.Business.Common.IJobService;
import SVM.Common.JobSvm;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService implements IJobService {

//    @Autowired
//    IJobRepository repository;



    @Override
    public List<JobSvm> getJobByExample(JobSvm entity) {

        return  null;
//        return ConvertService.convertList
//                (this.repository.getJobByExample(
//                        ConvertService.convertObject(entity, JobRvm.class)),JobSvm.class);
    }

}