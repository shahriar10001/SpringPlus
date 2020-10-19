package SVM.Sales;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

public class SaleableItemSvm {
    private Integer id;

    private Integer pricingRuleId;
    private Boolean isActive;

    private ProviderSvm provider = new ProviderSvm();
    private ProductSvm product = new ProductSvm();

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Tehran")
    private Timestamp fromDate;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Tehran")
    private Timestamp throughDate;

    public Date getFromDate() {
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

    public ProviderSvm getProvider() {
        return provider;
    }

    public void setProvider(ProviderSvm provider) {
        this.provider = provider;
    }

    public ProductSvm getProduct() {
        return product;
    }

    public void setProduct(ProductSvm product) {
        this.product = product;
    }


    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isactive) {
        isActive = isactive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SaleableItemSvm that = (SaleableItemSvm) o;
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
