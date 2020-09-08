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
import javax.persistence.Persistence;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

//Todo Remove or change relevant parts before ACTUAL use
@Path("movie")
public class MovieResource {

    
    @Context
    private UriInfo context;
    
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    
    //An alternative way to get the EntityManagerFactory, whithout having to type the details all over the code
    //EMF = EMF_Creator.createEntityManagerFactory(DbSelector.DEV, Strategy.CREATE);
    
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu"); 
    MovieFacade facade =  MovieFacade.getFacadeExample(emf);
    
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    
    private static List<MovieDTO> movies = new ArrayList();
            
    
    public MovieResource() {
        if (movies.isEmpty()){
            movies.add(new MovieDTO(new Movie(1979, "Månen", new String[]{"Johnny Depp", "Carol"})));
            movies.add(new MovieDTO(new Movie(2016, "Maze Runner", new String[]{"Dylan O'brien", "Eyebrow Guy"})));
            movies.add(new MovieDTO(new Movie(1999, "Kaffe", new String[]{"Inge", "Bertil"})));
        }
    }
    
    @Path("allMovies")
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
            if (oldest == null || m.getYear() > oldest.getYear()){
                oldest = m;
            }    
        }
        String jsonString = GSON.toJson(oldest.getMovie());
        return jsonString;
    }
    
    
}
