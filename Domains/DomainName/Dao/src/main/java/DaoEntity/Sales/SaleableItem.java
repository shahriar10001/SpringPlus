package DaoEntity.Sales;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "SalesableItem", schema = "Sales")
public class SaleableItem {
    private Integer id;
    private Integer pricingRuleId;
    private Timestamp fromDate;
    private Timestamp throughDate;
    private Boolean isActive;
    private Provider provider;
    private Product product;

    @Id
    @Column(name = "Id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "PricingRuleId")
    public Integer getPricingRuleId() {
        return pricingRuleId;
    }

    public void setPricingRuleId(Integer pricingRuleId) {
        this.pricingRuleId = pricingRuleId;
    }


    @Column(name = "FromDate")
    public Timestamp getFromDate() {
        return fromDate;
    }

    public void setFromDate(Timestamp fromDate) {
        this.fromDate = fromDate;
    }

    @Column(name = "ThroughDate")
    public Timestamp getThroughDate() {
        return throughDate;
    }

    public void setThroughDate(Timestamp throughDate) {
        this.throughDate = throughDate;
    }

    @Column(name = "IsActive")
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
        SaleableItem that = (SaleableItem) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(pricingRuleId, that.pricingRuleId) &&
                Objects.equals(fromDate, that.fromDate) &&
                Objects.equals(throughDate, that.throughDate) &&
                Objects.equals(isActive, that.isActive);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pricingRuleId, fromDate, throughDate, isActive);
    }

    @ManyToOne
    @JoinColumn(name = "ProviderId", referencedColumnName = "Id")
    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider providerByProviderId) {
        this.provider = providerByProviderId;
    }

    @ManyToOne
    @JoinColumn(name = "ProductId", referencedColumnName = "Id")
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product productByProductId) {
        this.product = productByProductId;
    }
}
