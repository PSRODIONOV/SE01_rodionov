package sef.module18.activity;

import junit.framework.TestCase;

public class EmployeeRepositoryTest extends TestCase {

    EmployeeRepositoryImplTest runTest = new EmployeeRepositoryImplTest();

    protected void setUp() throws Exception{
        runTest.setUp();
    }

    public void testFindEmployeeByID() {
        runTest.testFindEmployeeByID();
    }

    public void testFindEmployeeByName() {
        runTest.testFindEmployeeByName();
    }

    public void testFindEmployeeByProfLevel() {
        runTest.testFindEmployeeByProfLevel();
    }

    public void testFindEmployeeByProject() {
        runTest.testFindEmployeeByProject();
    }

    public void testInsertEmployee() {
        runTest.testInsertEmployee();
    }

    public void testUpdateEmployee() {
        runTest.testUpdateEmployee();
    }

}