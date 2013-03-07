/**
 * Created with IntelliJ IDEA.
 * User: lduarte
 * Date: 3/7/13
 * Time: 12:12 AM
 * To change this template use File | Settings | File Templates.
 */

package pt.isel.adeetc.leic.mpd;

import javafx.util.Pair;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

public class Binder {

    private Map<String, String> matches;

    private Binder() {
        // Doesn't make sense instantiating an Utility Class
    }

    public Map<String, Object> getFields(Object o) {
        Map<String, Object> result = new HashMap<String, Object>();
        Class<?> klass = o.getClass();

        for( Field field : klass.getFields() ) {
            try {
                result.put(field.getName(), field.get(o));
            } catch (IllegalAccessException e) {
                // TODO: Treat exception and rethrow, but lets ignore it for now
                return Collections.EMPTY_MAP;
            }
        }
        return result;
    }

    public <T> T bindTo(Class<T> klass, Iterable<Pair<String, Object>> pairs) {
        T instance = null;
        try {
            instance = klass.newInstance();
        } catch (Exception e) {
            // TODO: Same as the method above
            return null;
        }

        for( Pair<String, Object> pair : pairs) {
            String key = pair.getKey();
            if( matches.containsKey(key) ) {
                key = matches.get(key);
            }
            Method setter = Utils.getSetter(klass, key, pair.getValue());
            if( setter != null ) {
                try {
                    setter.invoke(instance, pair.getValue());
                } catch (Exception e) {
                    // TODO: Throw Exception
                    return null;
                }
            }
            // Lets assume that if it doesn't have a property setter, it has a field
            try {
                Field field = klass.getField(key);
                int modifiers = field.getModifiers();
                if ( Modifier.isPublic(modifiers) ) {
                    field.set(instance, pair.getValue());
                }
                else {
                    // TODO: Throw Exception
                    return null;
                }
            } catch (Exception e) {
                // TODO: Throw Exception
                return null;
            }
        }
        return instance;
    }

    public <TSrc, TDest> TDest map(TSrc objSrc, Class<TDest> klassDest) {
        Map<String, Object> fields = getFields(objSrc);
        Iterable<Pair<String, Object>> fieldsAsPairs = Utils.toPairs(fields);

        TDest instance = bindTo(klassDest, fieldsAsPairs);

        return instance;
    }

    public Binder matches(String source, String destination) {
        matches.put(source, destination);
        return this;
    }
}
