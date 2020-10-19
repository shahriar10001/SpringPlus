package RVM.Common;


import java.util.Objects;

public class JobRvm {
    private Integer id;
    private String title;
    private Short jobLevel;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Short getJobLevel() {
        return jobLevel;
    }

    public void setJobLevel(Short jobLevel) {
        this.jobLevel = jobLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobRvm rules = (JobRvm) o;
        return id == rules.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
