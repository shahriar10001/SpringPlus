package DaoEntity.Sales;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "Product", schema = "Sales")
public class Product {
    private Integer id;
    private String name;
    private Integer code;
    private String productEngineCode;
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

    @Column(name = "ProductEngineCode")
    public String getProductEngineCode() {
        return productEngineCode;
    }

    public void setProductEngineCode(String productEngineCode) {
        this.productEngineCode = productEngineCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) &&
                Objects.equals(name, product.name) &&
                Objects.equals(code, product.code) &&
                Objects.equals(productEngineCode, product.productEngineCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, code, productEngineCode);
    }

    @OneToMany(mappedBy = "product")
    public Collection<SaleableItem> getSaleableItemsById() {
        return saleableItemsById;
    }

    public void setSaleableItemsById(Collection<SaleableItem> saleableItemsById) {
        this.saleableItemsById = saleableItemsById;
    }
}
