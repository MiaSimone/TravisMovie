/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Movie;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author miade
 */
public class MovieFacadeTest {
    
    public MovieFacadeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testGetMovieByTitel() {
        System.out.println("getMovieByTitel");
        String titel = "";
        MovieFacade instance = null;
        Movie expResult = null;
        Movie result = instance.getMovieByTitel(titel);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetAllMovies() {
        System.out.println("getAllMovies");
        MovieFacade instance = null;
        List<Movie> expResult = null;
        List<Movie> result = instance.getAllMovies();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetOldestMovie() {
        System.out.println("getOldestMovie");
        int year = 0;
        MovieFacade instance = null;
        Movie expResult = null;
        Movie result = instance.getOldestMovie(year);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetFacadeExample() {
        System.out.println("getFacadeExample");
        EntityManagerFactory _emf = null;
        MovieFacade expResult = null;
        MovieFacade result = MovieFacade.getFacadeExample(_emf);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }
    
}
