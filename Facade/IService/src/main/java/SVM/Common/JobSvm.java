package SVM.Common;

public class JobSvm{
    private Integer id;
    private Short jobLevel;
    private String title;

    public JobSvm() {
    }

    public JobSvm(String title) {
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Short getJobLevel() {
        return jobLevel;
    }

    public void setJobLevel(Short jobLevel) {
        this.jobLevel = jobLevel;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
