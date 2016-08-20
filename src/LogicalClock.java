
public class LogicalClock {
private TimeStamp timeStamp;
    
    public LogicalClock (int processId){
        timeStamp = new TimeStamp (0, processId);
    }
   
    public void update (){
        timeStamp.increment ();
    }

    public void update (int eventTimeStamp){
        if (eventTimeStamp > timeStamp.getTimeValue ()){
            timeStamp.setTimeValue (eventTimeStamp);
        }
        update ();
    }

    /**
     * Send a copy of the timestamp.
     *
     * Must send a copy and not the timestamp itself because we do not
     * want it to be modified later when the clock is updated.
     */ 
    public TimeStamp getTimeStamp (){
        return new TimeStamp(timeStamp);
    }

    public void setTimeStamp (int timeValue){
        this.timeStamp.setTimeValue (timeValue);
    }
}
