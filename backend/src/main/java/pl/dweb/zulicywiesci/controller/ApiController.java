package pl.dweb.zulicywiesci.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.dweb.zulicywiesci.model.NewsItem;
import pl.dweb.zulicywiesci.model.NewsItemList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

@Controller
public class ApiController {

    @RequestMapping(value = "/api/one", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody String getNews(
                          @RequestParam(value = "id") Long id) {


        EntityManagerFactory emf = Persistence.createEntityManagerFactory("NewsPU");
        EntityManager em = emf.createEntityManager();

        NewsItem newsItem = em.find(NewsItem.class, id);

        Gson gson=  new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();
        String json = gson.toJson(newsItem);

        System.out.println(json);
        em.close();
        emf.close();

        return json;
    }

    @RequestMapping(value = "/api/list", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody String getNewsList() {


        EntityManagerFactory emf = Persistence.createEntityManagerFactory("NewsPU");
        EntityManager em = emf.createEntityManager();

        List<NewsItem> newsItemList = em.createQuery("SELECT n FROM NewsItem n ORDER BY n.date DESC").getResultList();

        Gson gson=  new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();
        String json = gson.toJson(new NewsItemList(newsItemList));

        System.out.println(json);
        em.close();
        emf.close();

        return json;
    }
}
