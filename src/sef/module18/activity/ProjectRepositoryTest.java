package sef.module18.activity;

import junit.framework.TestCase;

public class ProjectRepositoryTest extends TestCase {

    ProjectRepositoryImplTest runTest = new ProjectRepositoryImplTest();

    protected void setUp() throws Exception{
        runTest.setUp();
    }

    public void testFindProjectByID() {
        runTest.testFindProjectByID();
    }

    public void testFindProjectByName() {
        runTest.testFindProjectByName();
    }

    public void testFindProjectByEmployee() {
        runTest.testFindProjectByEmployee();
    }

    public void testInsertProject() {
        runTest.testInsertProject();
    }

    public void testUpdateProject() {
        runTest.testUpdateProject();
    }
}