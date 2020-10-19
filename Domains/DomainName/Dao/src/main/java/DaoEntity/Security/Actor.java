package DaoEntity.Security;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Actor", schema = "Common")
public class Actor {
    private Integer id;
    private String userName;
    private String roleName;

    @Id
    @Column(name = "Id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "UserName", nullable = true, length = 255)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "RoleName", nullable = true, length = 255)
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Actor actor = (Actor) o;
        return Objects.equals(id, actor.id) &&
                Objects.equals(userName, actor.userName) &&
                Objects.equals(roleName, actor.roleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, roleName);
    }
}
