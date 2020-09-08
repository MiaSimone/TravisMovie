package facades;

import entities.Movie;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;



//Uncomment the line below, to temporarily disable this test
//@Disabled
public class MovieFacadeTest1 {
    

    private static EntityManagerFactory emf;
    private static MovieFacade facade;
    private static Movie emp1;
    private static Movie emp2;
    private static Movie emp3;

    public MovieFacadeTest1() {
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

    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    // TODO: Delete or change this method 
    
    /*
    getMovieByTitel
    getAllMovies
    getOldestMovie
    */
    
    @Test
    public void getMovieByTitel() {
        String titel = "Månen";
        String exp = "Månen";
        String result = facade.getMovieByTitel(titel).getTitle();
        
        Assert.assertEquals(exp, result);
    }
    
    @Test
    public void getAllMovies() {
        
        
        
    }

}
