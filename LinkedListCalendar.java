
import java.util.Iterator;

/**
 * This class holds event data for use in the 
 * linked list calendar.
 * 
 * @author David McLellan
 * @author Trevor Elwell
 * @version 7 APRIL 2016
 */
public class LinkedListCalendar extends BaseCalendar
{
    private EventLink first;
    private EventLink last;
    private int numberOfLinks;

    public LinkedListCalendar()
    {
        first = null;
        last = null;
        numberOfLinks = 0;
    }
    
    @Override
    boolean isEmpty()
    {
        return (first == null);
    }
    
    @Override
    public int getTotalEvents()
    {
        return numberOfLinks;
    }
    
    @Override
    EventLink getFirst()
    {
        return first;
    }
    
    @Override
    public EventLink getLast()
    {
        return last;
    }
    
    /*
     * Loads events in sequential order from randomizer.
     * 
     * @param  event   Takes randomized data in array form
     */
    @Override
    public void loadEvent(int[] event)
    {
        EventLink newLink = new EventLink(event);
        numberOfLinks++;
        
        if (isEmpty())
        {
            first = newLink;
        }
        else
        {
            last.setNext(newLink);
            newLink.setPrevious(last);
        }
        last = newLink;
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
        EventLink newLink = new EventLink(event);
        EventLink current = first;
        numberOfLinks++;
        
        while (current.getNext().getKey() <= newLink.getKey() && 
                current.getNext() != null)
        {
            current = current.getNext();
        }
        
        if (current == last)
        {
            newLink.setNext(null);
            last = newLink;
        }
        else
        {
            newLink.setNext(current.getNext());
            current.getNext().setPrevious(newLink);
        }
        newLink.setPrevious(current);
        current.setNext(newLink);
    }
    
    /*
     * Delets specific event from calendar, given a
     * specific clock time of an event.
     * 
     * @param  key   Clock time of specific event.
     * @return     Reference to deleted event.
     */
    @Override
    public EventLink deleteEvent(int key)
    {
        EventLink current = first;
        
        while (current.getKey() != key)
        {
            current = current.getNext();
            if (current == null)
            {
                return null;
            }
        }
        if (current == first)
        {
            first = current.getNext();
        }
        else
        {
            current.getPrevious().setNext(current.getNext());
        }
        if (current == last)
        {
            last = current.getPrevious();
        }
        else
        {
            current.getNext().setPrevious(current.getPrevious());
        }
        numberOfLinks--;
        return current;
    }
    
    /*
     * Finds specific event from calendar, given a
     * specific clock time of an event.
     * 
     * @param  key   Clock time of specific event.
     * @return     Reference to found event.
     */
    @Override
    public EventLink findEvent(int key)
    {
        if (isEmpty())
        {
            System.out.println("The current list is empty.");
            return null;
        }
        
        int counter = 0;
        EventLink current = first;
        
        while (current.getKey() != key)
        {
            counter++;
            current = current.getNext();
            if (current == null)
            {
                System.out.println("The Event " + key + " was not found.");
                return null;
            }
        }
        return current;
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
        
        if ((start + range) < numberOfLinks)
        {
            EventLink current = first;
            
            while (counter < start)
            {
                counter++;
                current = current.getNext();
                if (current == null)
                {
                    System.out.println("Out of Range Error");
                    return;
                }
            }
            for (int i = 0; i < range; i++)
            {
                System.out.println("Displaying link " + (start + i) + ".");
                current.displayEvent();
                current = current.getNext();
            }
        }
        else
        {
            System.out.println("Your range is not contained in the calendar list.");
        }
    }

    @Override
    protected Iterable<EventLink> getIterator() {
        return () -> new Iterator<EventLink>() {
            EventLink current =getFirst();
            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public EventLink next() {
                EventLink ret = current;
                current = current.getNext();
                return ret;
            }
        };

    }
    
}