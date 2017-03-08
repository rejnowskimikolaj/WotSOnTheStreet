package pl.dweb.zulicywiesci.model;

import javax.persistence.*;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue
    @Column
    private Integer id;

    @Column
    private String name;

//    @ManyToMany(mappedBy = "categories")
//    private List<NewsItem> news;

    public Category() {}

    public Category(String name) {
        this.name = name;
    }

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

//    public List<NewsItem> getNews() {
//        return news;
//    }
//
//    public void setNews(List<NewsItem> news) {
//        this.news = news;
//    }

    @Override
    public String toString() {
        return name;
    }
}
