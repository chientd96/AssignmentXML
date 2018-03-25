package controller;

import com.google.gson.Gson;
import connection.ConnectXML;
import java.util.ArrayList;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import model.Film;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author TDC
 */
@Path("/film")
public class FilmController {
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String sayOke(){
        ConnectXML con = new ConnectXML();
        Gson gson = new Gson();
        String json = gson.toJson(con.getAllFilm());
        return json;
    }
    
    @GET
    @Path("/detail")
    @Produces(MediaType.APPLICATION_JSON)
    public String getDetail(@QueryParam("url1") String url1, @QueryParam("url2") String url2){
        ConnectXML con = new ConnectXML();
        Gson gson = new Gson();
        String json = gson.toJson(con.getDetail(url1, url2));
        return json;
    }
    @GET
    @Path("/{type}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getType(@PathParam("type") String type){
        ConnectXML con = new ConnectXML();
        Gson gson = new Gson();
        ArrayList<Film> list = new ArrayList<>();
        for(Film film : con.getAllFilm()){
            if(film.getType().equals(type))
                list.add(film);
        }
        String json = gson.toJson(list);
        return json;
    }
    
    @GET
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    public String searchByName(@QueryParam("name") String name){
        ConnectXML con = new ConnectXML();
        Gson gson = new Gson();
        ArrayList<Film> list = new ArrayList<>();
        for(Film film : con.getAllFilm()){
            if(film.getName().contains(name))
                list.add(film);
        }
        String json = gson.toJson(list);
        return json;
    }
    
}
