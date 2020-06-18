package ToDo;

import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import org.junit.*;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;

public class ToDoTest {
    private static final Logger log = Logger.getLogger(ToDoTest.class.getName());

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setup() {
        log.info("Setting up...");

    }

    @After
    public void tearDown() {
        log.info("Tearing down...");

    }

    @BeforeClass
    public static void before() {
        log.info("Running JUnit test cases from class: " + ToDoTest.class);

    }

    @AfterClass
    public static void after() {
        log.info("Testing class " + ToDoTest.class + " has completed.");

    }

    public void reset() {
        tearDown();
        setup();
    }

    /** NOTE: For several test cases, Mockito and Powermock besides JUnit are required */

    @Test
    public void findAllTasksTest() {
        ObservableList<Task> list =  ToDo.findAllTasks();

        assertEquals("Doctor's appointment", list.get(0).toString());
        assertEquals("Purchase computer", list.get(1).toString());
        assertEquals("Catch a flight", list.get(2).toString());
        assertEquals("Attend interview", list.get(3).toString());
        assertEquals("Finish assignment", list.get(4).toString());
        assertEquals("Misc", list.get(5).toString());

        assertEquals(6, list.size());
    }
}
