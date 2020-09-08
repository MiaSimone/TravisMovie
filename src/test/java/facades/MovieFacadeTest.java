package facades;

import utils.EMF_Creator;
import entities.Movie;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



//Uncomment the line below, to temporarily disable this test
//@Disabled

public class MovieFacadeTest {
    

    private static EntityManagerFactory emf;
    private static MovieFacade facade;
    private final Movie m1 = new Movie(2014, "Maze Runner", new String[]{"Dylan O'Brien", "Thomas Sangster", "Kaya Scodelario"});
    private final Movie m2 = new Movie(1975, "Olsenbanden slår igen", new String[]{"Egon Olsen","Kjeld"});
    private final Movie m3 = new Movie(2010, "Burlesque", new String[]{"Christina Aguilera","Cher"});
     
    public MovieFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
       emf = EMF_Creator.createEntityManagerFactoryForTest();
       facade = MovieFacade.getFacadeExample(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the script below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
                em.createNamedQuery("Movie.deleteAllRows").executeUpdate();
                em.persist(m1);
                em.persist(m2);
                em.persist(m3);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    /*
    getMovieByTitel
    getAllMovies
    getOldestMovie
    
    */
    @Test
    public void getMovieByTitel() {
        String titel = "Maze Runner";
        String exp = "Maze Runner";
        String result = facade.getMovieByTitle(titel).getTitle();
        
        assertEquals(exp, result);
    }
    
    @Test
    public void getAllMovies() {
        int exp = 3;
        int result = facade.getAllMovies().size();
        
        assertEquals(exp, result);
    }
    
    @Test
    public void getOldestMovie() {
        String exp = "Olsenbanden slår igen";
        String result = facade.getOldestMovie(1975).getTitle();
        
        assertEquals(exp, result);
    }
    

}
