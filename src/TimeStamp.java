
public class TimeStamp {

	    private int timeValue;
	    private int processId;
	        
	    public TimeStamp (int timeValue, int processId){
	        this.timeValue = timeValue;
	        this.processId = processId;
	    }
	    
	    public TimeStamp (TimeStamp timeStamp){
	        this.timeValue = timeStamp.getTimeValue ();
	        this.processId = timeStamp.getProcessId ();
	    }


	    public int compareTo (TimeStamp otherTimeStamp){
	        if (otherTimeStamp == null){
	            return 1;
	        }
	        if (this == otherTimeStamp){
	            return 0;
	        }

	        int otherTimeValue = otherTimeStamp.getTimeValue ();
	        int otherProcessId = otherTimeStamp.getProcessId ();

	        if (timeValue < otherTimeValue){
	            return -1;
	        }
	        else if (timeValue == otherTimeValue) {
	            if (processId < otherProcessId){
	                return -1;
	            } else if (processId == otherProcessId) {
	                return 0;
	            } else {
	                return 1;
	            }
	        } else {
	            return 1;
	        }
	    }

	    public boolean equals (Object o){
	        if (!(o instanceof TimeStamp)){
	            return false;
	        }
	        TimeStamp otherTimeStamp = (TimeStamp) o;
	        return timeValue == otherTimeStamp.getTimeValue ()
	                && processId == otherTimeStamp.getProcessId ();
	    }
	    
	    public boolean same(TimeStamp t) {
	    	return t.timeValue == this.timeValue;
	    }
	    
	    public void increment (){
	        timeValue++;
	    }

	    public void setTimeValue (int timeValue){
	        this.timeValue = timeValue;
	    }

	    public int getTimeValue (){
	        return timeValue;
	    }

	    public int getProcessId (){
	        return processId;
	    }
}
