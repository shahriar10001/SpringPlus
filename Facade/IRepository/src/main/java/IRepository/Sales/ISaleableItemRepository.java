package IRepository.Sales;

import RVM.Sales.SaleableItemRvm;

import java.util.List;

public interface ISaleableItemRepository {
    SaleableItemRvm persistSaleableItem(SaleableItemRvm entity);
    List<SaleableItemRvm> selectSaleableItem(SaleableItemRvm example);
}
