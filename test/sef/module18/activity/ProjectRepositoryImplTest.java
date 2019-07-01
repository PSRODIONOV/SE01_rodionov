package sef.module18.activity;


import junit.framework.TestCase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

public class ProjectRepositoryImplTest extends TestCase {

    private Connection conn;
    private String url ;
    private String username;
    private String password;

    protected void setUp() throws Exception{
        super.setUp();
        username = "sa";
        password = "";
        Class.forName("org.h2.Driver");
        url = "jdbc:h2:~/test";
        conn = DriverManager.getConnection(url, username, password);
        conn.setAutoCommit(false);
        System.out.println("Connection successfully established!");
    }

    public void testFindProjectByID() {
        ProjectRepository rep = new ProjectRepositoryImpl(conn);
        try{
            Project result = rep.findProjectByID(1);
            assertEquals("Online Insurance System", result.getProjectName());

            result = rep.findProjectByID(0);
            assertNull(result);
        }
        catch(HRSSystemException ex){
            fail();
        }
    }


    public void testFindProjectByName() {
        ProjectRepository rep = new ProjectRepositoryImpl(conn);
        try{
            List<Project> results = rep.findProjectByName("Online");
            assertEquals(2, results.size());

            results = rep.findProjectByName("Case");
            assertEquals(0, results.size());
        }
        catch(HRSSystemException ex){
            fail();
        }
    }


    public void testFindProjectByEmployee() {
        ProjectRepository rep = new ProjectRepositoryImpl(conn);
        try{
            List<Project> results = rep.findProjectByEmployee(1);
            assertEquals(1, results.size());
            assertEquals(1, results.get(0).getProjectID());

            results = rep.findProjectByEmployee(5);
            assertEquals(0, results.size());
        }
        catch(HRSSystemException ex){
            fail();
        }
    }


    public void testInsertProject() {
        ProjectRepository rep = new ProjectRepositoryImpl(conn);
        try{
            boolean insCorrect = false;
            int res = rep.insertProject(new ProjectImpl(0, "Bank", "everything with bank..."));
            if(res > 0)
                insCorrect = true;
            assertTrue(insCorrect);

        }
        catch(HRSSystemException ex){
            fail();
        }
    }


    public void testUpdateProject() {
        ProjectRepository rep = new ProjectRepositoryImpl(conn);
        try{
            boolean res = rep.updateProject(new ProjectImpl(1, "Online Insurance System", "update complete"));
            assertTrue(res);
        }
        catch(HRSSystemException ex){
            fail();
        }
    }
}