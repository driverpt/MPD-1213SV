package pt.isel.adeetc.leic.mpd;

import javafx.util.Pair;

import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: lduarte
 * Date: 3/7/13
 * Time: 4:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class Utils {
    private Utils() {

    }

    static Method getSetter(Class<?> klass, String propertyName, Object parameterObject) {
        String setterName = concatWithCamelCase("set", propertyName);
        try {
            return klass.getMethod(setterName, parameterObject.getClass());
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    static String concatWithCamelCase(String string1, String string2) {
        StringBuilder builder = new StringBuilder();
        builder.append(string1);
        builder.append(MessageFormat.format("{0}{1}", Character.toUpperCase(string2.charAt(0)), string2.substring(1)));
        return builder.toString();
    }

    static <K, V> Iterable<Pair<K, V>> toPairs(Map<K, V> map) {
        // According to official Javadoc, LinkedList is the most efficient List
        List<Pair<K, V>> result = new LinkedList<Pair<K, V>>();
        for( K key : map.keySet() ) {
            V value = map.get(key);
            Pair<K, V> pair = new Pair<K, V>(key, value);
            result.add(pair);
        }
        return result;
    }
}
