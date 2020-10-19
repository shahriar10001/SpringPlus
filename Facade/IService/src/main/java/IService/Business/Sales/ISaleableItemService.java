package IService.Business.Sales;

import SVM.Sales.SaleableItemSvm;

import java.util.List;

public interface ISaleableItemService {

    SaleableItemSvm persistSaleableItem(SaleableItemSvm entity);
    List<SaleableItemSvm> retrieveSaleableItem(SaleableItemSvm example);
}
