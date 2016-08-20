import java.util.Comparator;
import java.util.PriorityQueue;


public class Node {
	int nodeID;
	Node[] nodes;
	LogicalClock myclock;
	Pair<Integer,TimeStamp> flag1;
	Pair<Integer,TimeStamp> flag2;
	Pair<Integer,TimeStamp> flag3;
	PriorityQueue<Pair<Integer,TimeStamp>> requestQueue;
	boolean reply[];
	boolean recvallreplies;

	public Node(int i, int n, Node[] node) {
		reply = new boolean[n];
		nodeID = i;
		nodes = node;
		flag1= flag2 = flag3 = null;
		requestQueue = new PriorityQueue<Pair <Integer,TimeStamp>>(2*nodes.length, new Comparator<Pair<Integer, TimeStamp>>() {
			@Override
            public int compare(Pair<Integer, TimeStamp> first, Pair<Integer, TimeStamp> last) {
                if (first.getRight().same(last.getRight()))
                    return last.getLeft() - first.getLeft();
                else return last.getRight().getTimeValue() - first.getRight().getTimeValue();
            }
        });
	}
	
	private void sendrequest(int nodeID, int timeValue) {
		for(int i=0;i<nodes.length;i++)
		{
			System.out.println("Sent request to :"+ i);
			if(i+1!=nodeID)
				nodes[i].flag1 = new Pair<Integer,TimeStamp>(nodeID,myclock.getTimeStamp());
		}
	}
	
	
	
	private void recvrequest(TimeStamp timeStamp, int nodeid) {
		if(myclock.getTimeStamp().getTimeValue() < timeStamp.getTimeValue() )
		{
			myclock.setTimeStamp(timeStamp.getTimeValue());
			requestQueue.add(new Pair<Integer,TimeStamp>(nodeid,timeStamp));
			flag1 = null;
			myclock.update();
		}
		
	}

	private void sendreply(int nodeID, int timeValue) {
		
	}
	
	public void begin() {
		myclock = new LogicalClock(nodeID);
		Thread requestthread = new Thread( new Runnable(){
		    public void run(){
		    	requestQueue.add(new Pair<Integer,TimeStamp>(nodeID,myclock.getTimeStamp()));
		    	myclock.update();
		    	sendrequest(nodeID,myclock.getTimeStamp().getTimeValue());
		    	 while(recvallreplies!=true)
				 {
					 
				 }
		    }
		
		  });
		  requestthread.start();
		  if(flag1 !=null)
		  {
			  recvrequest(flag1.getRight(),flag1.getLeft());
			  myclock.update();
		  }
	}

}
