package picard.util;

import java.util.Collection;

/**
 * Created by Aleksandr_Kolesov on 11/3/2015.
 */
public interface BatchConsumer<T> {
    public void consume(Collection<T> batch);
}
