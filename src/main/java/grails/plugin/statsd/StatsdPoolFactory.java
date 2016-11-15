package grails.plugin.statsd;

import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;

public class StatsdPoolFactory implements PooledObjectFactory {
    final private String host;
    final private int port;
    final private String prefix;

    public StatsdPoolFactory(String host, int port, String prefix) {
        this.host = host;
        this.port = port;
        this.prefix = prefix;
    }

    @Override
    public PooledObject makeObject() throws Exception {
        return new DefaultPooledObject(new StatsdClient(host, port, prefix));
    }

    @Override
    public void destroyObject(PooledObject pooledObject) throws Exception {
        ((StatsdClient) pooledObject.getObject()).close();
    }

    @Override
    public boolean validateObject(PooledObject pooledObject) {
        return ((StatsdClient) pooledObject.getObject()).isOpen();
    }

    @Override
    public void activateObject(PooledObject pooledObject) throws Exception {
        // Objects don't require setup when sent out
    }

    @Override
    public void passivateObject(PooledObject pooledObject) throws Exception {
        // Objects don't require changes to be returned to pool
    }
}
