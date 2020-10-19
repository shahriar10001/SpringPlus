package Dao.Resource.Sales;

import Dao.Config.AbstractDao;
import DaoEntity.Sales.Provider;
import IRepository.Sales.IProviderRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class ProviderRepository extends AbstractDao<Integer, Provider> implements IProviderRepository{


    @Override
    public Boolean checkIfExists(Integer example) {
        return this.findById(example) != null;
    }
}
