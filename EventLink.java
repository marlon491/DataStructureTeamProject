/**
 * This class holds event data for use in the 
 * linked list calendar.
 * 
 * @author David McLellan
 * @version 7 APRIL 2016
 */
public class EventLink extends BaseEvent
{
    /*
     * Instance variables - Same as original but changing to support
     * links instead of array indices from original coding.
     */
    private EventLink previous;
    private EventLink next;
    private final int field_clock;  // Using the field_clock as link key
    private final int field_type;
    private final int field_unit;
    private final int field_status;


    /*
     * Keeping compatiblity by initializing from randomizer array.
     * 
     * @param  event   Takes randomize data in array form
     */
    public EventLink(int[] event)
    {
        field_clock = event[1];
        field_type = event[2];
        field_unit = event[3];
        field_status = event[4];
    }
        
    public int getKey()
    {
        return field_clock;
    }
    
    @Override
    public int getType()
    {
        return field_type;
    }
    
    public EventLink getPrevious()
    {
        return previous;
    }
    
    public void setPrevious(EventLink previous)
    {
        this.previous = previous;
    }
    
    @Override
    public EventLink getNext()
    {
        return next;
    }
    
    public void setNext(EventLink next)
    {
        this.next = next;
    }

    @Override
    protected int getStatus() {
        return field_status;
    }

    @Override
    protected int getClock() {
        return field_clock;
    }

    @Override
    protected int getUnit() {
        return field_unit;
    }
}
