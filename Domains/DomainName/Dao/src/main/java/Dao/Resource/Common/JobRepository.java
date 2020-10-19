package Dao.Resource.Common;

import Dao.Config.AbstractDao;
import DaoEntity.Common.Job;
import IRepository.Common.IJobRepository;
import RVM.Common.JobRvm;
import RepoUtility.ConvertService;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@SuppressWarnings("unchecked")
@Transactional
@Repository
public class JobRepository extends AbstractDao<Integer, Job>
        implements IJobRepository {

    @Override
    public List<JobRvm> getJobByExample(JobRvm daoViewModel) {

        return ConvertService.convertList(
                this.selectByExample(ConvertService.convertObject(
                        daoViewModel, Job.class)),JobRvm.class);

    }
}
