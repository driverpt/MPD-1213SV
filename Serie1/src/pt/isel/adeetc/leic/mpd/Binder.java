/**
 * Created with IntelliJ IDEA.
 * User: lduarte
 * Date: 3/7/13
 * Time: 12:12 AM
 * To change this template use File | Settings | File Templates.
 */

package pt.isel.adeetc.leic.mpd;

import javafx.util.Pair;

import java.util.Map;

public class Binder {
    public Map<String, Object> getFields(Object o) {
        return null;
    }

    public <T> T bindTo(Class<T> klass, Iterable<Pair<String, Object>> pairs) {
        return null;
    }

    public <TSrc, TDest> TDest map(TSrc objSrc, Class<TDest> klassDest) {
        return null;
    }
}
