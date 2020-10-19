package Dao.Resource.Sales;


import Dao.Config.AbstractDao;
import DaoEntity.Sales.SaleableItem;
import IRepository.RuleEngine.IRulesRepository;
import IRepository.Sales.IProductRepository;
import IRepository.Sales.IProviderRepository;
import IRepository.Sales.ISaleableItemRepository;
import RVM.Sales.SaleableItemRvm;
import RepoUtility.ConvertService;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Transactional
@Repository
public class SaleableItemRepository extends AbstractDao<Integer, SaleableItem> implements ISaleableItemRepository {


    @Autowired
    IProductRepository productRepository;

    @Autowired
    IProviderRepository providerRepository;

    @Autowired
    IRulesRepository rulesRepository;

    private Throwable inputCheck(SaleableItemRvm model) {

//region Null values validation
        if (model.getIsActive() == null
                || model.getPricingRuleId() == null
                || model.getProduct() == null
                || model.getProvider() == null
                || model.getProduct().getId() == null
                || model.getProvider().getId() == null
                || (model.getFromDate() != null && model.getThroughDate() == null))
            return new IllegalArgumentException("200");
//endregion

//region Foreign key validation
        if (!productRepository.checkIfExists(model.getProduct().getId()))
            return new IllegalArgumentException("300");
//endregion

        return null;
    }

    @Override
    public SaleableItemRvm persistSaleableItem(SaleableItemRvm entity) {

//region check data validity
        Throwable e = inputCheck(entity);

        if (e != null)
            throw new IllegalArgumentException(e.getMessage());
//endregion

        //Get similar entity by constraint(on update use it)
        SaleableItemRvm saleableItemRvm = new SaleableItemRvm();
        saleableItemRvm.setProduct(entity.getProduct());
        saleableItemRvm.setProvider(entity.getProvider());
        List<SaleableItemRvm> saleableItemRvmList = this.selectSaleableItem(saleableItemRvm);
        if (saleableItemRvmList.size() > 0)
            saleableItemRvm = saleableItemRvmList.get(0);
        else
            saleableItemRvm = null;

        //Unique-Index(Product+Provider) constraint check
        if (saleableItemRvm != null
                && entity.getId() != null
                && !saleableItemRvm.getId().equals(entity.getId()))
            throw new IllegalArgumentException("100");

        //On insert making up ID/On update use constraint id
        if (entity.getId() == null || entity.getId() < 1) {
            if (saleableItemRvm == null) {
                entity.setId(this.selectMaxId().getId() + 1);
                this.doTransaction(session -> {
                            session.save(ConvertService.convertObject(entity, SaleableItem.class));
                        }
                );

            } else {
                entity.setId(saleableItemRvm.getId());
                entity.setProvider(saleableItemRvm.getProvider());
                entity.setProduct(saleableItemRvm.getProduct());
                this.doTransaction(session -> {
                            session.update(ConvertService.convertObject(entity, SaleableItem.class));
                        }
                );

            }
        }
        return entity;
        //TODO Publish-Event for clients
    }

    @Override
    public List<SaleableItemRvm> selectSaleableItem(SaleableItemRvm example) {

        SaleableItem model = ConvertService.convertObject(example, SaleableItem.class);

        List<SaleableItem> saleableItems = createQuery(model).list();

        return ConvertService.convertList(saleableItems, SaleableItemRvm.class);
    }

    private Query createQuery(SaleableItem model) {

        Map<String, Object> params = new HashMap();

        //Make prepared query statement and put parameters in map
        String hqlQuery = " from SaleableItem s join fetch s.provider join fetch s.product where 1=1 ";


        if (model.getId() != null) {
            hqlQuery += " and  s.id => :id ";
            params.put("id", model.getId());
        } else {

            if (model.getFromDate() != null) {
                hqlQuery += " and  s.fromDate >= :fromDate ";
                params.put("fromDate", model.getFromDate());
            }

            if (model.getThroughDate() != null) {
                hqlQuery += " and  s.throughDate <= :throughDate ";
                params.put("throughDate", model.getThroughDate());
            }

            if (model.getIsActive() != null) {
                hqlQuery += " and  s.isActive = :isActive ";
                params.put("isActive", model.getIsActive());
            }

            if (model.getPricingRuleId() != null) {
                hqlQuery += " and  s.pricingRuleId = :pricingRuleId ";
                params.put("pricingRuleId", model.getPricingRuleId());
            }

            if (model.getProvider() != null
                    && model.getProvider().getId() != null) {
                hqlQuery += " and  s.provider.id = :providerId ";
                params.put("providerId", model.getProvider().getId());
            }

            if (model.getProduct() != null
                    && model.getProduct().getId() != null) {
                hqlQuery += " and  s.product.id = :productId ";
                params.put("productId", model.getProduct().getId());
            }

        }

        //Value-injection to query-parameters(Stateless Query)
        Query Q = currentSession().createQuery(hqlQuery);
        for (String str : Q.getNamedParameters()) {
            Q.setParameter(str, params.get(str));
        }

        return Q;
    }
}