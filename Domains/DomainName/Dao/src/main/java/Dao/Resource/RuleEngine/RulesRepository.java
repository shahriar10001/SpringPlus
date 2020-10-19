package Dao.Resource.RuleEngine;

import Dao.Config.AbstractDao;
import DaoEntity.Rules.Rules;
import IRepository.RuleEngine.IRulesRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class RulesRepository extends AbstractDao<Integer, Rules> implements IRulesRepository {
    @Override
    public Boolean checkIfExists(Integer id) {
        return this.findById(id) != null;
    }
}
