import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MainClass {
	static int n;
	
	public static void main(String[] args) throws InterruptedException
	{
		 System.out.println("Enter number of nodes : ");
		 try{
		        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
		        String s = bufferRead.readLine();
		        n = Integer.valueOf(s);
		    }
		    catch(IOException e)
		    {
		        e.printStackTrace();
		    }
		 
		 
		 Node node[] = new Node[n];
		 
		// create a pool of threads, 10 max jobs will execute in parallel
		 ExecutorService threadPool = Executors.newFixedThreadPool(10);
		 // submit jobs to be executing by the pool
		 for (int i = 0; i < n; i++) {
			 final Node newnode= new Node(i,n, node);
			 node[i] = newnode;
		    threadPool.submit(new Runnable() {
		         public void run() {
		             // some code to run in parallel
		        	 newnode.begin();
		         }
		     });
		 }
		 // once you've submitted your last job to the service it should be shut down
		 threadPool.shutdown();
		 // wait for the threads to finish if necessary
		 threadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
	}
}