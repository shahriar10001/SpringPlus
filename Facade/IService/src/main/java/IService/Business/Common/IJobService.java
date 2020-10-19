package IService.Business.Common;

import SVM.Common.JobSvm;

import java.util.List;

public interface IJobService {
    List<JobSvm> getJobByExample(JobSvm entity);
}
