package facades;

import entities.Movie;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
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
    
    public void populateDB(){
            EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(new Movie(1986, "Topgun", new String[]{"Tom Cruise", "Val Kilmer","Kelly McGills"}));
            em.persist(new Movie(2003, "Kill Bill", new String[]{"Uma Thurman", "Daryl Hannah"}));
            em.persist(new Movie(1991, "Point Break", new String[]{"Patrick Swayze", "Keanu Reeves"}));
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    
     public List<Movie> getMovieByTitle(String title){
        EntityManager em = emf.createEntityManager();
        try {
              Query query = em.createNamedQuery("Movie.getByTitle");
              query.setParameter("title", title);
              List<Movie> movieList = query.getResultList();
              return movieList;
        }         
        finally {
            em.close();
        }  
    }
    
    /*
    public Movie getMovieByTitel(String titel){
         EntityManager em = emf.createEntityManager();
        try{
            Movie temp = em.find(Movie.class,titel);
            return temp;
        }finally {
            em.close();
        }
    }
    */
    
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
        try {
              Query query = em.createNamedQuery("Movie.getOldestMovie");
              query.setParameter("year", year);
              Movie movie = (Movie) query.getSingleResult();
              return movie;
        }         
        finally {
            em.close();
        }
    }
    
    /*
    public Movie getOldestMovie(){
         EntityManager em = emf.createEntityManager();
        try{
            Query query2 = em.createQuery("Select MIN(m.year) FROM Movie m");
            int year = (int) query2.getSingleResult();
            
            TypedQuery<Movie> query = 
                       em.createQuery("SELECT m FROM Movie m WHERE m.year :year",Movie.class);
            
            query.setParameter("year", year);
            Movie m = (Movie)query.getSingleResult();
            
            return m;
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
