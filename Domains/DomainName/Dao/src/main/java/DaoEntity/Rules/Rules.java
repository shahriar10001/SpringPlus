package DaoEntity.Rules;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "Rules", schema = "RuleEngine")
public class Rules {
    private Integer id;

    @Id
    @GeneratedValue
    @Column(name = "Id", nullable = false)
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    private Integer tenantId;
    private String name;
    private Integer ruleTypeId;
    private Short version;
    private Boolean canOverride;
    private Timestamp modificationDate;
    private Timestamp creationDate;


    @Column(name = "TenantId")
    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    @Column(name = "Name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "RuleTypeId")
    public Integer getRuleTypeId() {
        return ruleTypeId;
    }

    public void setRuleTypeId(Integer ruleTypeId) {
        this.ruleTypeId = ruleTypeId;
    }

    @Column(name = "Version")
    public Short getVersion() {
        return version;
    }

    public void setVersion(Short version) {
        this.version = version;
    }

    @Column(name = "CanOverride")
    public Boolean isCanOverride() {
        return canOverride;
    }

    public void setCanOverride(Boolean canOverride) {
        this.canOverride = canOverride;
    }

    @Column(name = "ModificationDate")
    public Timestamp getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(Timestamp modificationDate) {
        this.modificationDate = modificationDate;
    }

    @Column(name = "CreationDate")
    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rules rules = (Rules) o;
        return id == rules.id &&
                tenantId == rules.tenantId &&
                ruleTypeId == rules.ruleTypeId &&
                version == rules.version &&
                canOverride == rules.canOverride &&
                Objects.equals(name, rules.name) &&
                Objects.equals(modificationDate, rules.modificationDate) &&
                Objects.equals(creationDate, rules.creationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tenantId, name, ruleTypeId, version, canOverride, modificationDate, creationDate);
    }
}
