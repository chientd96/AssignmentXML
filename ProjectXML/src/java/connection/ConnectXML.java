/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Film;
import model.FilmDetail;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author TDC
 */
public class ConnectXML {
    private Document document = null;
    private static Elements element = null;
    private static Elements imageItem = null;
    private static Elements nameItem = null;
    private static Elements urlItem = null;
    private static Elements description = null;
    
    public ArrayList<Film> getAllFilm(){
        ArrayList<Film> list = new ArrayList<>();
        try {
            document = (Document) Jsoup.connect("https://www.imdb.com/trailers?pf_rd_m=A2FGELUUNOQJNL&pf_rd_p=0d1d650c-4448-4f36-a6c6-c8fd8c9a31df&pf_rd_r=6XBFWVJVM3GDB6RGQMQG&pf_rd_s=hero&pf_rd_t=15061&pf_rd_i=homepage&ref_=hm_hp_sm").get();
            element = document.select("div[class=gridlist] div.gridlist-item");
            for (Element item : element) {
                imageItem = item.select("div.trailer-image a img[loadlate]");
                description = item.select("div.trailer-image a img");
                nameItem = item.select("div.trailer-caption a");
                urlItem = item.select("div.trailer-caption a");
                Document document1 = (Document) Jsoup.connect("https://www.imdb.com/title/"+ urlItem.attr("href").substring(7, 16) + "?ref_="+ urlItem.attr("href").substring(22)).get();
                Elements type = document.select("div.title-overview div.title_wrapper div.subtext span[class=itemprop]");
                list.add(new Film(nameItem.text(),urlItem.attr("href").substring(7, 16),urlItem.attr("href").substring(22), imageItem.attr("loadlate"), description.attr("title"),type.text()));
            }
        } catch (IOException ex) {
            Logger.getLogger(ConnectXML.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }
    
    public FilmDetail getDetail(String url1, String url2){
        FilmDetail film = new FilmDetail();
        try {
            document = (Document) Jsoup.connect("https://www.imdb.com/title/"+ url1 + "?ref_="+ url2).get();
            Elements date = document.select("div.title-overview div.title_wrapper div.subtext a:eq(2)"); //date
            Elements year = document.select("div.title-overview div.title_wrapper h1 span");//year
            Elements director = document.select("div.title-overview div.plot_summary_wrapper div.credit_summary_item span[itemprop=director]");
            Elements actors = document.select("div.title-overview div.plot_summary_wrapper div.credit_summary_item span[itemprop=actors] a span");
            Elements creator = document.select("div.title-overview div.plot_summary_wrapper div.credit_summary_item span[itemprop=creator]");
            Elements rate = document.select("div.title-overview div.titleReviewBar span[class=subtext]");//rate
            Elements videoUrl = document.select("div.title-overview div.slate a");
            Elements title = document.select("div.title-overview div.title_wrapper div.subtext span[class=itemprop]");
            Elements image = document.select("div.title-overview div.slate_wrapper div.poster a img");
            film.setActor(actors.text());
            film.setDate(date.text());
            film.setDirector(director.text());
            film.setTitle(title.text());
            film.setVideoUrl("https://www.imdb.com/"+videoUrl.attr("href"));
            film.setRate(director.text());
            film.setCreator(creator.text());
            film.setYear(director.text());
            film.setName(director.text());
            film.setImage(image.attr("src"));
            
        } catch (IOException ex) {
            Logger.getLogger(ConnectXML.class.getName()).log(Level.SEVERE, null, ex);
        }
        return film;
    }
}
