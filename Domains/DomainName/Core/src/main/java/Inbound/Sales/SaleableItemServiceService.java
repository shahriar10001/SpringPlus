package Inbound.Sales;

import IRepository.Sales.ISaleableItemRepository;
import IService.Business.Sales.ISaleableItemService;
import RVM.Sales.SaleableItemRvm;
import RepoUtility.ConvertService;
import SVM.Sales.SaleableItemSvm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleableItemServiceService implements ISaleableItemService {

    @Autowired
    ISaleableItemRepository saleableItemRepository;

    @Override
    public SaleableItemSvm persistSaleableItem(SaleableItemSvm entity) {

        return ConvertService
                .convertObject(saleableItemRepository
                        .persistSaleableItem(ConvertService
                                .convertObject(entity, SaleableItemRvm.class)), SaleableItemSvm.class);
    }

    @Override
    public List<SaleableItemSvm> retrieveSaleableItem(SaleableItemSvm example) {
        return ConvertService.convertList(
                this.saleableItemRepository.selectSaleableItem(ConvertService
                        .convertObject(example, SaleableItemRvm.class)), SaleableItemSvm.class);
    }
}
