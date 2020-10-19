package DaoEntity.Sales;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "Provider", schema = "Sales")
public class Provider {
    private Integer id;
    private String name;
    private Integer code;
    private String centInsCode;
    private String providerEngineCode;
    private Collection<SaleableItem> saleableItemsById;

    @Id
    @Column(name = "Id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "Name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "Code")
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Column(name = "CentInsCode")
    public String getCentInsCode() {
        return centInsCode;
    }

    public void setCentInsCode(String centInsCode) {
        this.centInsCode = centInsCode;
    }

    @Column(name = "ProviderEngineCode")
    public String getProviderEngineCode() {
        return providerEngineCode;
    }

    public void setProviderEngineCode(String providerEngineCode) {
        this.providerEngineCode = providerEngineCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Provider provider = (Provider) o;
        return Objects.equals(id, provider.id) &&
                Objects.equals(name, provider.name) &&
                Objects.equals(code, provider.code) &&
                Objects.equals(centInsCode, provider.centInsCode) &&
                Objects.equals(providerEngineCode, provider.providerEngineCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, code, centInsCode, providerEngineCode);
    }

    @OneToMany(mappedBy = "provider")
    public Collection<SaleableItem> getSaleableItemsById() {
        return saleableItemsById;
    }

    public void setSaleableItemsById(Collection<SaleableItem> saleableItemsById) {
        this.saleableItemsById = saleableItemsById;
    }
}
