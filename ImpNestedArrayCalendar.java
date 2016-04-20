/**
 * This class holds event data for use in the 
 * Improved Nested Array calendar.
 * 
 * Improved version uses binary search instead
 * of linear search.
 * 
 * @author David McLellan
 * @author Trevor Elwell
 * @version 7 APRIL 2016
 */
public class ImpNestedArrayCalendar extends NestedArrayCalendar
{

   
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
    @Override
    public EventArray deleteEvent(int key)
    {
        int lower = 0;
        int upper = (count - 1);
        int current;
        
        while (true)
        {
            current = ((lower + upper) / 2);
            
            if (calendar[current].getKey() == key)
            {
                break;
            }
            else if (lower > upper)
            {
                System.out.println("Could not find event: " + key);
                return null;
            }
            else
            {
                if (calendar[current].getKey() < key)
                {
                    lower = (current + 1);
                }
                else
                {
                    upper = (current - 1);
                }
            }
        }

        EventArray event = new EventArray(calendar[current].getEventArray());
        System.arraycopy(calendar, current + 1, calendar, current, count - current);
        count--;
        return event;
    }
    
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
    @Override
    public EventArray findEvent(int key)
    {
        int lower = 0;
        int upper = (count - 1);
        int current;
        
        while (true)
        {
            current = ((lower + upper) / 2);
            
            if (calendar[current].getKey() == key)
            {
                return calendar[current];
            }
            else if (lower > upper)
            {
                System.out.println("Could not find event: " + key);
                return null;
            }
            else
            {
                if (calendar[current].getKey() < key)
                {
                    lower = (current + 1);
                }
                else
                {
                    upper = (current - 1);
                }
            }
        }
    }    
}