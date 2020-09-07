package facades;

import entities.Movie;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class MovieFacade {

    private static MovieFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private MovieFacade() {}
    
    
    public Movie getMovieByTitel(String titel){
         EntityManager em = emf.createEntityManager();
        try{
            Movie temp = em.find(Movie.class,titel);
            return temp;
        }finally {
            em.close();
        }
    }
    
    public List<Movie> getAllMovies(){
         EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<Movie> query = 
                       em.createQuery("SELECT m FROM Movie m",Movie.class);
            return query.getResultList();
        }finally {
            em.close();
        }
    }
    
    public Movie getOldestMovie(int year){
         EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<Movie> query = 
                       em.createQuery("SELECT m FROM Movie m WHERE min(m.year)",Movie.class);
            return query.getSingleResult();
        }finally {
            em.close();
        }
    }
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static MovieFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new MovieFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

}
