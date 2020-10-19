package RVM.Sales;

import java.sql.Timestamp;
import java.util.Objects;

public class SaleableItemRvm {
    private Integer id;
    private Integer pricingRuleId;
    private Timestamp fromDate;
    private Timestamp throughDate;
    private Boolean isActive;
    private ProviderRvm provider = new ProviderRvm();
    private ProductRvm product = new ProductRvm();


    public ProviderRvm getProvider() {
        return provider;
    }

    public void setProvider(ProviderRvm provider) {
        this.provider = provider;
    }

    public ProductRvm getProduct() {
        return product;
    }

    public void setProduct(ProductRvm product) {
        this.product = product;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPricingRuleId() {
        return pricingRuleId;
    }

    public void setPricingRuleId(Integer pricingRuleId) {
        this.pricingRuleId = pricingRuleId;
    }

    public Timestamp getFromDate() {
        return fromDate;
    }

    public void setFromDate(Timestamp fromDate) {
        this.fromDate = fromDate;
    }

    public Timestamp getThroughDate() {
        return throughDate;
    }

    public void setThroughDate(Timestamp throughDate) {
        this.throughDate = throughDate;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SaleableItemRvm that = (SaleableItemRvm) o;
        return id == that.id &&
                isActive == that.isActive &&
                Objects.equals(pricingRuleId, that.pricingRuleId) &&
                Objects.equals(fromDate, that.fromDate) &&
                Objects.equals(throughDate, that.throughDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pricingRuleId, fromDate, throughDate, isActive);
    }
}
