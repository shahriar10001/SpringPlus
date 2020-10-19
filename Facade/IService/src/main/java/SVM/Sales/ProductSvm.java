package SVM.Sales;

import java.util.Objects;

public class ProductSvm {
    private Integer id;
    private String name;
    private Integer code;
    private String productEngineCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

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
        ProductSvm product = (ProductSvm) o;
        return id == product.id &&
                code == product.code &&
                Objects.equals(name, product.name) &&
                Objects.equals(productEngineCode, product.productEngineCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, code, productEngineCode);
    }
}
