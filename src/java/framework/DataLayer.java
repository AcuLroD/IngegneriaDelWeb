package framework;

/**
 *
 * @author Giuseppe Della Penna
 */
public interface DataLayer extends AutoCloseable {

    void init() throws DataLayerException;

    void destroy() throws DataLayerException;
}
