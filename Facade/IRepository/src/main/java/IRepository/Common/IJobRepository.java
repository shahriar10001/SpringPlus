package IRepository.Common;

import RVM.Common.JobRvm;

import java.util.List;

public interface IJobRepository {
    List<JobRvm> getJobByExample(JobRvm entity);
}
