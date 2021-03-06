package com.trendrr.beanstalk;

/**
 * @author dustin
 */
public class BeanstalkJob {

    private byte[] data;
    private long id;
    private BeanstalkClient client = null;

    public BeanstalkClient getClient() {
        return this.client;
    }

    public void setClient(BeanstalkClient client) {
        this.client = client;
    }

    public byte[] getData() {
        return this.data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
