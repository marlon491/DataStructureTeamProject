/**
 *
 * @author markwalling
 */
abstract class BaseEvent {

    abstract public int getType();

    abstract public BaseEvent getNext();
        
    public void displayEvent()
    {
        String[] CURRENT_TYPE = {"FORCED OUTAGE", "MAINTENANCE",
            "INSTALLATION", "REMOVED", "RANDOM OUTAGE", "OPERATIONAL"};
        String currentType = CURRENT_TYPE[getType()];
        
        String[] CURRENT_STATUS = {"OFFLINE", "RUNNING"};
        String currentStatus = CURRENT_STATUS[getStatus()];
        
        String clock = String.format ("%08d", getClock());
        
        System.out.println("");
        System.out.println("****************************************");
        System.out.println("* Event Data Elements");
        System.out.println("* Field Clock (KEY):  " + clock);
        System.out.println("* Field Type:         " + currentType);
        System.out.println("* Field Unit:         " + getUnit());
        System.out.println("* Field Status:       " + currentStatus);
        System.out.println("****************************************");
        System.out.println("");
    }

    protected abstract int getStatus();

    protected abstract int getClock();

    protected abstract int getUnit();

}
