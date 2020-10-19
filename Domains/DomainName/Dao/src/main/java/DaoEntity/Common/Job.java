package DaoEntity.Common;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Job", schema = "Common")
public class Job {
    private Integer id;
    private Short jobLevel;
    private String title;

    @Id
    @GeneratedValue
    @Column(name = "Id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "JobLevel", nullable = false)
    public Short getJobLevel() {
        return jobLevel;
    }

    public void setJobLevel(Short jobLevel) {
        this.jobLevel = jobLevel;
    }

    @Basic
    @Column(name = "Title", nullable = false, length = 100)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Job job = (Job) o;
        return id == job.id &&
                jobLevel == job.jobLevel &&
                Objects.equals(title, job.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, jobLevel, title);
    }
}
