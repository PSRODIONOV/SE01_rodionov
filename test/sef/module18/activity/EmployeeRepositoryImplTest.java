package sef.module18.activity;

import junit.framework.TestCase;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;


class EmployeeRepositoryImplTest extends TestCase {


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

    public void testFindEmployeeByID() {
        EmployeeRepository rep = new EmployeeRepositoryImpl(conn);
        try{
            Employee result = rep.findEmployeeByID(3);
            assertEquals(result.getFirstName(), "Scott");
            assertEquals(result.getLastName(), "Feist");
            assertEquals(result.getProfLevel(), 1);

            result = rep.findEmployeeByID(0);
            assertNull(result);
        }
        catch(HRSSystemException ex){
            fail();
        }
    }


    public void testFindEmployeeByName() {

        EmployeeRepository rep = new EmployeeRepositoryImpl(conn);
        try {
            List<Employee> results = rep.findEmployeeByName("J", "DOE");
            assertEquals(2, results.size());

            assertEquals(results.get(0).getFirstName().toUpperCase(), "JOHN");
            assertEquals(results.get(0).getLastName().toUpperCase(), "DOE");
            assertEquals(results.get(0).getProfLevel(), 1);


            assertEquals(results.get(1).getFirstName().toUpperCase(), "JANE");
            assertEquals(results.get(1).getLastName().toUpperCase(), "DOE");
            assertEquals(results.get(1).getProfLevel(), 2);

            results = rep.findEmployeeByName("A", "B");
            assertEquals(0, results.size());

        } catch (HRSSystemException e) {
            // TODO Auto-generated catch block
            fail();
        }
    }



    public void testFindEmployeeByProfLevel() {
        EmployeeRepository rep = new EmployeeRepositoryImpl(conn);
        try{
            List<Employee> result = rep.findEmployeeByProfLevel(3);
            assertEquals(1, result.size());
            assertEquals(result.get(0).getFirstName(), "James");
            assertEquals(result.get(0).getLastName(), "Donnell");

            result = rep.findEmployeeByProfLevel(4);
            assertEquals(0, result.size());
        }
        catch(HRSSystemException ex){
            fail();
        }
    }

    public void testFindEmployeeByProject() {
        EmployeeRepository rep = new EmployeeRepositoryImpl(conn);
        try{
            List<Employee> result = rep.findEmployeeByProject(3);
            assertEquals(2 , result.size());
            assertEquals(result.get(0).getEmployeeID(), 2);
            assertEquals(result.get(1).getEmployeeID(), 4);

            result = rep.findEmployeeByProject(4);
            assertEquals(0, result.size());
        }
        catch(HRSSystemException ex){
            fail();
        }

    }

    public void testInsertEmployee() {
        EmployeeRepository rep = new EmployeeRepositoryImpl(conn);
        try{
            boolean insCorrect = false;
            int res = rep.insertEmployee(new EmployeeImpl(0, "PAVEL", "ROD", 1));
            if(res > 0)
                insCorrect = true;
            assertTrue(insCorrect);

        }
        catch (HRSSystemException ex){
            fail();
        }
    }

    public void testUpdateEmployee() {
        EmployeeRepository rep = new EmployeeRepositoryImpl(conn);
        try{
            boolean res = rep.updateEmployee(new EmployeeImpl(6, "PAVEL", "RODIONOV", 1));
            assertTrue(res);
        }
        catch(HRSSystemException ex){
            fail();
        }
    }
}