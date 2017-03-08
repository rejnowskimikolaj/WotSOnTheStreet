package pl.dweb.zulicywiesci.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "news")
public class NewsItem {

    @Id
    @GeneratedValue
    @Column
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private Integer priority;

    @Column
    private String author;

    @Column
    private Date date;

//    @ManyToMany
//    @JoinTable(name = "news_category", joinColumns = {@JoinColumn(name = "news_id")}, inverseJoinColumns = {@JoinColumn(name = "category_id")})
//    private List<Category> categories;

    public NewsItem() {}

    public NewsItem(String title, String content, Integer priority, String author, Date date) {
        this.title = title;
        this.content = content;
        this.priority = priority;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }



//    public List<Category> getCategories() {
//        return categories;
//    }
//
//    public void setCategories(List<Category> categories) {
//        this.categories = categories;
//    }

    @Override
    public String toString() {
        return "NewsItem{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", priority=" + priority +
                ", author='" + author + '\'' +
                '}';
    }
}
