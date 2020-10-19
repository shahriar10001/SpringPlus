package DaoEntity;


import DaoEntity.Common.Job;
import DaoEntity.Rules.Rules;
import DaoEntity.Sales.Product;
import DaoEntity.Sales.Provider;
import DaoEntity.Sales.SaleableItem;
import DaoEntity.Security.Actor;

public class HibernateMappingClass {

    public static Class[] initialize() {
        return
                new Class[]{
                        Job.class
                        , Actor.class
                        , SaleableItem.class
                        , Provider.class
                        , Product.class
                        , Rules.class
                };
    }
}
