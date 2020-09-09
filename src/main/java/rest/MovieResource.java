package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.MovieDTO;
import dto.MoviesDTO;
import facades.MovieFacade;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import entities.Movie;
import javax.ws.rs.PathParam;
import utils.EMF_Creator;

//Todo Remove or change relevant parts before ACTUAL use
@Path("movie")
public class MovieResource {

    
    /*
    /api/movie/all
    /api/movie/titel/{titel}
    /api/movie/oldestMovie
    */
    
     private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    
    //An alternative way to get the EntityManagerFactory, whithout having to type the details all over the code
    //EMF = EMF_Creator.createEntityManagerFactory(DbSelector.DEV, Strategy.CREATE);
    
    private static final MovieFacade FACADE =  MovieFacade.getFacadeExample(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
 
   /*
    private static List<MovieDTO> movies = new ArrayList();

    public MovieResource() {
        if (movies.isEmpty()){
            movies.add(new MovieDTO(
                    new Movie(2014, "Maze Runner", new String[]{"Dylan O'Brien", "Thomas Sangster", "Kaya Scodelario"})));
            movies.add(new MovieDTO(new Movie(1975, "Olsenbanden slår igen", new String[]{"Egon Olsen","Kjeld"})));
            movies.add(new MovieDTO(new Movie(2010, "Burlesque", new String[]{"Christina Aguilera","Cher"})));
        }
    }
  */  
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }
    
    @GET
    @Path("/populate")
    @Produces({MediaType.APPLICATION_JSON})
    public String populate() {
        FACADE.populateDB();
        return "{\"msg\":\"3 rows added\"}";
    }

@Path("all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllMovies() {
        List<Movie> allMovies = FACADE.getAllMovies();
        return GSON.toJson(allMovies);
    }

@GET
    @Path("/title/{title}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getMovieByName(@PathParam("title") String title) {
        List<Movie> movieList = FACADE.getMovieByTitle(title);
        MoviesDTO movieListDTO = new MoviesDTO(movieList);
        return GSON.toJson(movieListDTO);
    }
    
    @Path("oldestMovie")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String findOldestPerson() {
        Movie oldest = null;
        List<Movie> movies = FACADE.getAllMovies();
        
        for (Movie m : movies) {
            if (oldest == null || m.getYear() < oldest.getYear()){
                oldest = m;
            }    
        }
        MovieDTO oldestDTO = new MovieDTO(oldest);
        return GSON.toJson(oldestDTO);
    }
    
}
