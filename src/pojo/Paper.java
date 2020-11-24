package pojo;

public class Paper {
    private String paperId;
    private String paperTitle;
    private String paperContext;
    private String paperDate;
    private String paperAuthor;

    public Paper() {
    }

    public Paper(String paperTitle, String paperDate, String paperAuthor) {
        this.paperTitle = paperTitle;
        this.paperDate = paperDate;
        this.paperAuthor = paperAuthor;
    }

    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId;
    }

    public String getPaperTitle() {
        return paperTitle;
    }

    public void setPaperTitle(String paperTitle) {
        this.paperTitle = paperTitle;
    }

    public String getPaperContext() {
        return paperContext;
    }

    public void setPaperContext(String paperContext) {
        this.paperContext = paperContext;
    }

    public String getPaperDate() {
        return paperDate;
    }

    public void setPaperDate(String paperDate) {
        this.paperDate = paperDate;
    }

    public String getPaperAuthor() {
        return paperAuthor;
    }

    public void setPaperAuthor(String paperAuthor) {
        this.paperAuthor = paperAuthor;
    }
}
