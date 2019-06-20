/**
 *
 */


import com.trendrr.beanstalk.BeanstalkClient;
import com.trendrr.beanstalk.BeanstalkException;
import com.trendrr.beanstalk.BeanstalkJob;
import com.trendrr.beanstalk.BeanstalkPool;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * @author Dustin Norlander
 * @created Nov 15, 2010
 */
public class Example {

    protected static Log log = LogFactory.getLog(Example.class);

    /**
     * @param args
     */
    public static void main(String[] args) {
        //Example usage for a

        try {
//            clientExample();
            pooledExample();
        } catch (BeanstalkException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }

    /**
     * Example for using an unpooled client
     *
     * @throws BeanstalkException
     */
    public static void clientExample() throws BeanstalkException {
        BeanstalkClient client = new BeanstalkClient("192.168.16.10", 11300, "example");
        log.info("Putting a job");
        client.put(1L, 6, 5000, "this is some data1".getBytes());
        client.put(1L, 6, 5000, "this is some data2".getBytes());
        for (int i = 0; i < 3; i++) {
            BeanstalkJob job = client.reserve(5);
            if (job != null) {
                log.info("GOt job: " + new String(job.getData()));
                client.deleteJob(job);
            }
        }
        client.tubeStats();
//        BeanstalkJob job1 = client.reserve(5);
//        log.info("GOt job1: " + new String(job1.getData()));
//        client.release(job, 11, 0);
//        BeanstalkJob reserve = client.reserve(null);
//        log.info("GOt job1: " + new String(job.getData()));
        client.close(); //closes the connection
    }


    public static void pooledExample() throws BeanstalkException {
        BeanstalkPool pool = new BeanstalkPool("test.ehoo100.com", 8010,
                30, //poolsize
                "example" //tube to use
        );

        BeanstalkClient client = pool.getClient();

        log.info("Putting a job");
        client.put(1l, 0, 5000, "this is some data".getBytes());
        BeanstalkJob job = client.reserve(60);
        log.info("GOt job: " + job);
        client.deleteJob(job);
//        client.close();  //returns the connection to the pool
        client = pool.getClient();
        client.put(1l, 0, 5000, "this is some data1".getBytes());
        BeanstalkJob job1 = client.reserve(60);
        log.info("GOt job: " + job1);
        client.deleteJob(job1);
        client.close();  //returns the connection to the pool
    }
}
