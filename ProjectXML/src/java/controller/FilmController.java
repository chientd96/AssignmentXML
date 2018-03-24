package controller;

import com.google.gson.Gson;
import connection.ConnectXML;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author TDC
 */
@Path("/getAll")
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
    public String getDetail(@QueryParam("") String url1, @QueryParam("") String url2){
        ConnectXML con = new ConnectXML();
        Gson gson = new Gson();
        String json = gson.toJson(con.getDetail(url1, url2));
        return json;
    }
}
