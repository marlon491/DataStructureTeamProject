import java.util.*;

/**
 * This class runs functionality and timed
 * tests on the three types of calendar
 * data structures: Linked List, Nested
 * Array, and Improved Nested Array.
 * 
 * Each test is run with the same number
 * of elements and performs the same tests
 * in sequence, using a randomizer.
 * 
 * @author David McLellan
 * @author Trevor Elwell
 * @version 7 APRIL 2016
 */
class TestingApp
{
    // Creates each of the three data structures to be tested.
    private static final ImpNestedArrayCalendar inac = new ImpNestedArrayCalendar();
    private static final NestedArrayCalendar nac = new NestedArrayCalendar();
    private static final LinkedListCalendar llc = new LinkedListCalendar();
    private static final RandomList randoms = new RandomList();
    
    // Creates randomizer object and supporting variables.
    private static final Random rnd = new Random();
    private static final long SEED = 846195035;
    private static final int NUM_LINKS_TO_TEST = 1000000;
    private static int NUM_START = 1000;
    private static int RANGE = 6;

    public static void main(String[] args)
    {
        // Runs calendar tests in order.
        rnd.setSeed(SEED);
        runTest(new CalendarTestData() {
            @Override
            public String getTestName() {
                return "Improved Nested Array Calendar";
            }

            @Override
            public void runRandomizer() {
                inacRandomizer();
            }

            @Override
            public BaseCalendar getCalendar() {
                return inac;
            }
        });
        rnd.setSeed(SEED);
        runTest(new CalendarTestData() {
            @Override
            public String getTestName() {
                return "Nested Array Calendar";
            }

            @Override
            public void runRandomizer() {
                nacRandomizer();
            }

            @Override
            public BaseCalendar getCalendar() {
                return nac;
            }
        });
        rnd.setSeed(SEED);
        runTest(new CalendarTestData() {
            @Override
            public String getTestName() {
                return "Linked List Calendar";
            }

            @Override
            public void runRandomizer() {
                llcRandomizer();
            }

            @Override
            public BaseCalendar getCalendar() {
                return llc;
            }
        });
    }
    
    /*
     * Tests each function of the specific calendar type
     * by calling the randomizer to initially load the 
     * calendar with a set number of events.
     */
    private static void runTest(CalendarTestData testData)
    {
        System.out.println("");
        System.out.println("****** Begining " + testData.getTestName() + " Loading Test **********");
        System.out.println("");
        System.out.println("Running Initial Test on " + NUM_LINKS_TO_TEST + " events.");
        long startTime = System.currentTimeMillis();
        testData.runRandomizer();
        long stopTime = System.currentTimeMillis();
        System.out.println("It took: " + (stopTime - startTime)+ " mS to load " + testData.getCalendar().getTotalEvents()+ " events.");
        System.out.println("");
        System.out.println("****** Begining Improved Nested Array Calendar Timed Tests **********");
        System.out.println("");
        System.out.println("Insert 1_000 Events Test:");
        System.out.println("Inserting 1_000 Test Events");
        startTime = System.currentTimeMillis();
        // Insert an event 1000 times
        for (int i=0; i<randoms.getCount(); i++) {
        	int[] event = {0,randoms.getRandom(i),1,1,1,0};
        	testData.getCalendar().insertEvent(event);
        }
        stopTime = System.currentTimeMillis();

        System.out.println("There are now: " + testData.getCalendar().getTotalEvents() + " events in the calendar.");
        System.out.println("It took: " + ((stopTime - startTime))+ " milliseconds to insert 1000 events");
        System.out.println("");
        System.out.println("Find 1_000 Events Test:");
        System.out.println("Finding 1_000 Random Events");
        startTime = System.nanoTime();
        
        // FIND our 1_000 random events
        for (int i=0; i<randoms.getCount(); i++) {
        	testData.getCalendar().findEvent(randoms.getRandom(i));
        }

        stopTime = System.nanoTime();

        System.out.println("It took: " + ((stopTime - startTime)/1000)+ " microS to find a 1_000 events");
        System.out.println("");
        System.out.println("Delete 1_000 Event Test:");
        System.out.println("Deleting 1_000 Random Events");
        startTime = System.currentTimeMillis();

        for (int i=0; i<randoms.getCount(); i++) {
					testData.getCalendar().deleteEvent(randoms.getRandom(i));        	
        }
        
        stopTime = System.currentTimeMillis();
        System.out.println("There are now: " + testData.getCalendar().getTotalEvents() + " events in the calendar.");
        System.out.println("It took: " + ((stopTime - startTime))+ " milliseconds to delete 1_000 events");
        System.out.println("");
        System.out.println("Traverse and Generate Report of All Events Test:");
        startTime = System.nanoTime();
        testData.getCalendar().printReport();
        stopTime = System.nanoTime();
        System.out.println("It took: " + ((stopTime - startTime)/1000)+ " microS to traverse calendar and generate report");
        System.out.println("");
        System.out.println("****** Ending Improved Nested Array Calendar Loading Test **********");
        System.out.println("");
    }
    
    /*
     * The randomizer takes the total number of events to test
     * and creates that number of unique events to load into the
     * appropriate calendar structure for further testing.
     */
    private static void inacRandomizer()
    {
        int[] event = new int[6];
        int YEAR_SECONDS = 31540000;
        int MAX_ADV = (YEAR_SECONDS / TestingApp.NUM_LINKS_TO_TEST); // limits advancement to under total Seconds in Year
        int clock = 0; // event clock time
        for (int i = 0; i < TestingApp.NUM_LINKS_TO_TEST; i++)
        {
            int n = rnd.nextInt(MAX_ADV); // Clock incriment amount
            clock += n; // new event clock time.
            
            event[0] = 0; // Positional artifact from original system
            event[1] = clock; // Sets the seconds since 12am Jan 1 Year XX
            event[2] = (n%6); // Selects last event types
            event[3] = ((n%10)+1); // Selects unit #1 - #10
            event[4] = (event[2] != 5) ? 0 : 1; // Matches running conditional
            event[5] = 0; // Positional artifact from original system
            
            inac.loadEvent(event);
        }
    }
    
    /*
     * The randomizer takes the total number of events to test
     * and creates that number of unique events to load into the
     * appropriate calendar structure for further testing.
     */
    private static void nacRandomizer()
    {
        int[] event = new int[6];
        int YEAR_SECONDS = 31540000;
        int MAX_ADV = (YEAR_SECONDS / TestingApp.NUM_LINKS_TO_TEST); // limits advancement to under total Seconds in Year
        int clock = 0; // event clock time
        for (int i = 0; i < TestingApp.NUM_LINKS_TO_TEST; i++)
        {
            int n = rnd.nextInt(MAX_ADV); // Clock incriment amount
            clock += n; // new event clock time.
            
            event[0] = 0; // Positional artifact from original system
            event[1] = clock; // Sets the seconds since 12am Jan 1 Year XX
            event[2] = (n%6); // Selects last event types
            event[3] = ((n%10)+1); // Selects unit #1 - #10
            event[4] = (event[2] != 5) ? 0 : 1; // Matches running conditional
            event[5] = 0; // Positional artifact from original system
            
            nac.loadEvent(event);
        }
    }
    
    /*
     * The randomizer takes the total number of events to test
     * and creates that number of unique events to load into the
     * appropriate calendar structure for further testing.
     */
    private static void llcRandomizer()
    {
        int[] event = new int[6];
        int YEAR_SECONDS = 31540000;
        int MAX_ADV = (YEAR_SECONDS / TestingApp.NUM_LINKS_TO_TEST); // limits advancement to under total Seconds in Year
        int clock = 0; // event clock time
        for (int i = 0; i < TestingApp.NUM_LINKS_TO_TEST; i++)
        {
            int n = rnd.nextInt(MAX_ADV); // Clock incriment amount
            clock += n; // new event clock time.
            
            event[0] = 0; // Positional artifact from original system
            event[1] = clock; // Sets the seconds since 12am Jan 1 Year XX
            event[2] = (n%6); // Selects last event types
            event[3] = ((n%10)+1); // Selects unit #1 - #10
            event[4] = (event[2] != 5) ? 0 : 1; // Matches running conditional
            event[5] = 0; // Positional artifact from original system
            
            llc.loadEvent(event);
        }
    }

    private interface CalendarTestData {

        String getTestName();

        void runRandomizer();

        BaseCalendar getCalendar();
    }
}
