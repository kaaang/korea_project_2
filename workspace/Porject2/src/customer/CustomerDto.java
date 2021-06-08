package customer;

public class CustomerDto {
    private String pk_customerservice, pk_user,title,content,regdate,answered;

    public String getPk_customerservice() {
        return pk_customerservice;
    }

    public void setPk_customerservice(String pk_customerservice) {
        this.pk_customerservice = pk_customerservice;
    }

    public String getPk_user() {
        return pk_user;
    }

    public void setPk_user(String pk_user) {
        this.pk_user = pk_user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRegdate() {
        return regdate;
    }

    public void setRegdate(String regdate) {
        this.regdate = regdate;
    }

    public String getAnswered() {
        return answered;
    }

    public void setAnswered(String answered) {
        this.answered = answered;
    }
}
