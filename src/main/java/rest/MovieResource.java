package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.MovieDTO;
import utils.EMF_Creator;
import facades.MovieFacade;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import entities.Movie;
import java.util.Collections;
import javax.persistence.Persistence;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

//Todo Remove or change relevant parts before ACTUAL use
@Path("movie")
public class MovieResource {

    
    /*
    /api/movie/all
    /api/movie/titel/{titel}
    /api/movie/oldestMovie
    */
    
    @Context
    private UriInfo context;
    
    private static Gson GSON = new GsonBuilder().setPrettyPrinting().create();
//NOTE: Change Persistence unit name according to your setup
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu"); 
    MovieFacade facade =  MovieFacade.getFacadeExample(emf);
    
    private static List<MovieDTO> movies = new ArrayList();

    public MovieResource() {
        if (movies.isEmpty()){
            movies.add(new MovieDTO(
                    new Movie(2014, "Maze Runner", new String[]{"Dylan O'Brien", "Thomas Sangster", "Kaya Scodelario"})));
            movies.add(new MovieDTO(new Movie(1975, "Olsenbanden slår igen", new String[]{"Egon Olsen","Kjeld"})));
            movies.add(new MovieDTO(new Movie(2010, "Burlesque", new String[]{"Christina Aguilera","Cher"})));
        }
    }
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }
    
    @GET
    @Path("/populate")
    @Produces({MediaType.APPLICATION_JSON})
    public String populate() {
        facade.populateDB();
        return "{\"msg\":\"3 rows added\"}";
    }

    @Path("all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        String jsonString = GSON.toJson(movies);
        return jsonString;
    }
    
    @Path("/titel/{titel}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String movieByTitel(@PathParam("titel") String titel){
        try {
            Movie movie = findMovieDTOTitel(titel).getMovie();
            String jsonString = GSON.toJson(movie);
            return jsonString;
        } catch (Exception e){
            String err = "{\"error\":404}";
            return err;
        }
    }
    
        public MovieDTO findMovieDTOTitel(String titel) {
            for(MovieDTO movieDTO : movies) {
                if(movieDTO.getMovie().getTitle().equals(titel)) {
                    return movieDTO;
                }
            }
            return null;
        }
    
    
    @Path("oldestMovie")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String findOldestPerson() {
        MovieDTO oldest = null;
        for (MovieDTO m : movies) {
            if (oldest == null || m.getYear() < oldest.getYear()){
                oldest = m;
            }    
        }
        String jsonString = GSON.toJson(oldest.getMovie());
        return jsonString;
    }
    
}
