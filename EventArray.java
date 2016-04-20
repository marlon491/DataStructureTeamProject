
/**
 * This class holds event data for use in the
 * Nested Array and Improved Nested Array calendar.
 *
 * @author David McLellan
 * @version 7 APRIL 2016
 */
public class EventArray extends BaseEvent {

    private final int CLOCK = 1;
    private final int TYPE = 2;
    private final int UNIT = 3;
    private final int STATUS = 4;
    private final int[] event = new int[6];

    /*
     * Keeping compatiblity by initializing from randomizer array.
     * 
     * @param  event   Takes randomize data in array form
     */
    public EventArray(int[] event) {
        this.event[0] = 0;  // positional artifact from original system
        this.event[CLOCK] = event[1];
        this.event[TYPE] = event[2];
        this.event[UNIT] = event[3];
        this.event[STATUS] = event[4];
    }

    public int getKey() {
        return event[CLOCK];
    }

    @Override
    public int getType() {
        return event[TYPE];
    }

    @Override
    public BaseEvent getNext() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected int getStatus() {
        return this.event[STATUS];
    }

    @Override
    protected int getClock() {
        return this.event[CLOCK];
    }

    @Override
    protected int getUnit() {
        return this.event[UNIT];
    }

    int[] getEventArray() {
        return event;
    }

}
