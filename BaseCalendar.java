/**
 *
 * @author markwalling
 */
public abstract class BaseCalendar {

    /*
     * Delets specific event from calendar, given a
     * specific clock time of an event.
     *
     * Improved version uses binary search instead
     * of linear search.
     *
     * @param  key   Clock time of specific event.
     * @return     Reference to deleted event.
     */
    abstract BaseEvent deleteEvent(int key);

    /*
     * Finds specific event from calendar, given a
     * specific clock time of an event.
     *
     * Improved version uses binary search instead
     * of linear search.
     *
     * @param  key   Clock time of specific event.
     * @return     Reference to found event.
     */
    abstract BaseEvent findEvent(int key);

    abstract BaseEvent getFirst();

    abstract BaseEvent getLast();

    abstract int getTotalEvents();

    /*
     * Loads single event into calendar and ensures it is
     * placed in the appropriate sorted calendar order.
     *
     * @param  event   Takes single event data in array form
     */
    abstract void insertEvent(int[] event);

    abstract boolean isEmpty();

    /*
     * Loads events in sequential order from randomizer.
     *
     * @param  event   Takes randomized data in array form
     */
    abstract void loadEvent(int[] event);

    /*
     * Prints a listing of events between a given start point
     * and the number of events to print in the range.
     *
     * @param  start   The sequential event number to start with.
     * @param  range   The number of event records to print.
     */
    abstract void printRange(int start, int range);

    /*
     * Print Report traverses all the events held in the calendar
     * and talleys each type of the UNIT DOWN events and creates
     * and prints a report of the events by type.
     */
    public final void printReport()
    {
        int forced = 0;
        int maintenance = 0;
        int installation = 0;
        int removed = 0;
        int random = 0;
        
        for(BaseEvent current : getIterator()) {
            switch(current.getType())
            {
                case 1:
                    forced++;
                    break;
                case 2:
                    maintenance++;
                    break;
                case 3:
                    installation++;
                    break;
                case 4:
                    removed++;
                    break;
                case 5:
                    random++;
                    break;
            }
        }
        
        System.out.println("*******************************************");
        System.out.println("* Calendar System Report");
        System.out.println("*******************************************");
        System.out.println("* Total Calendar Events: " + getTotalEvents());
        System.out.println("* Forced Outages:        " + forced);
        System.out.println("* Maintenance Outages:   " + maintenance);
        System.out.println("* Unit Installations:    " + installation);
        System.out.println("* Unit Removals:         " + removed);
        System.out.println("* Random Outages:        " + random);
        System.out.println("*******************************************");
        System.out.println("");
    }    

    protected abstract Iterable<? extends BaseEvent> getIterator();
}
