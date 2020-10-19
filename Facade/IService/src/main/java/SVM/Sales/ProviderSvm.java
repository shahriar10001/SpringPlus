package SVM.Sales;

import java.util.Objects;

public class ProviderSvm {
    private Integer id;
    private String name;
    private Integer code;
    private String centInsCode;
    private String providerEngineCode;

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

    public String getCentInsCode() {
        return centInsCode;
    }

    public void setCentInsCode(String centInsCode) {
        this.centInsCode = centInsCode;
    }

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
        ProviderSvm provider = (ProviderSvm) o;
        return id == provider.id &&
                code == provider.code &&
                Objects.equals(name, provider.name) &&
                Objects.equals(centInsCode, provider.centInsCode) &&
                Objects.equals(providerEngineCode, provider.providerEngineCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, code, centInsCode, providerEngineCode);
    }
}
