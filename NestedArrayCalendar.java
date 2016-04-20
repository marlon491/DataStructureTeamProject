
import java.util.Iterator;

/**
 * This class holds event data for use in the 
 * Nested Array calendar.
 * 
 * This version uses linear search instead
 * of the improved binary search.
 * 
 * @author David McLellan
 * @author Trevor Elwell
 * @version 7 APRIL 2016
 */
public class NestedArrayCalendar extends BaseCalendar
{
    int count;
    final EventArray[] calendar;
    private final int MAX_SIZE = 1002000;
    
    public NestedArrayCalendar()
    {
        count = 0;
        calendar = new EventArray[MAX_SIZE];
    }
    
    @Override
    public boolean isEmpty()
    {
        return(count == 0);
    }
    
    @Override
    public int getTotalEvents()
    {
        return count;
    }
    
    /*
     * Loads events in sequential order from randomizer.
     * 
     * @param  event   Takes randomized data in array form
     */
    @Override
    public void loadEvent(int[] event)
    {
        if (count < MAX_SIZE)
        {
            EventArray newEvent = new EventArray(event);
            calendar[count] = newEvent;
            count++;
        }
        else
        {
            System.out.println("Event Calendar is Full. Cannot add event.");
        }
    }
    
    /*
     * Loads single event into calendar and ensures it is
     * placed in the appropriate sorted calendar order.
     * 
     * @param  event   Takes single event data in array form
     */
    @Override
    public void insertEvent(int[] event)
    {
        if (count < MAX_SIZE)
        {
            int clock = 1;
            int index = 0;
            EventArray newEvent = new EventArray(event);
            
            for ( ; index < count; index++)
            {
                if (calendar[index].getKey() > newEvent.getKey())
                {
                    break;
                }
            }

            System.arraycopy(calendar, index, calendar, index + 1, count - index);
            
            calendar[index] = newEvent;
            count++;
        }
        else
        {
            System.out.println("Event Calendar is Full. Cannot add event.");
        }
    }
    
    /*
     * Delets specific event from calendar, given a
     * specific clock time of an event.
     * 
     * This version uses linear search instead
     * of the improved binary search.
     * 
     * @param  key   Clock time of specific event.
     * @return     Reference to deleted event.
     */
    @Override
    public EventArray deleteEvent(int key)
    {
        int index = 0;

        for ( ; index < count; index++)
        {
            if (calendar[index].getKey() == key)
            {
                break;
            }
        }

        if (index == count)
        {
            return null;
        }
        else
        {
            EventArray event = new EventArray(calendar[index].getEventArray());
            System.arraycopy(calendar, index + 1, calendar, index, count - index);
            count--;
            return event;
        }
    }
    
    /*
     * Finds specific event from calendar, given a
     * specific clock time of an event.
     * 
     * This version uses linear search instead
     * of the improved binary search.
     * 
     * @param  key   Clock time of specific event.
     * @return     Reference to found event.
     */
    @Override
    public EventArray findEvent(int key)
    {
        int index = 0;
        
        for ( ; index < count; index++)
        {
            if (calendar[index].getKey() == key)
            {
                break;
            }
        }
        
        if (index == count)
        {
            return null;
        }
        else
        {
            return calendar[index];
        }
    }
    
    @Override
    public EventArray getFirst()
    {
        return calendar[0];
    }
    
    @Override
    public EventArray getLast()
    {
        return calendar[count - 1];
    }
    
    /*
     * Prints a listing of events between a given start point
     * and the number of events to print in the range.
     * 
     * @param  start   The sequential event number to start with.
     * @param  range   The number of event records to print.
     */
    @Override
    public void printRange(int start, int range)
    {
        int counter = 0;
        
        if ((start + range) < count)
        {
            for (int i = 0; i < range; i++)
            {
                System.out.println("Displaying link " + (start + i) + ".");
                calendar[i + start].displayEvent();
            }
        }
        else
        {
            System.out.println("Your range is not contained in the calendar.");
        }
    }

    @Override
    protected Iterable<EventArray> getIterator() {
        return () -> new Iterator<EventArray>() {
            int idx = 0;

            @Override
            public boolean hasNext() {
                return idx < count;
            }

            @Override
            public EventArray next() {
                return calendar[idx++];
            }
        };
    }
}