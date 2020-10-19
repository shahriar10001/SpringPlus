package Dao.Resource.Sales;

import Dao.Config.AbstractDao;
import DaoEntity.Sales.Product;
import IRepository.Sales.IProductRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class ProductRepository extends AbstractDao<Integer, Product> implements IProductRepository {


    @Override
    public Boolean checkIfExists(Integer id) {
        return this.findById(id) != null;
    }
}
