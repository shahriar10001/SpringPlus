package ServiceUtility;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import jdk.nashorn.api.scripting.ScriptObjectMirror;

import java.sql.Date;
import java.util.*;
import java.util.concurrent.TimeUnit;

public   class Extension {
    public static long getDiffDateWithCurrentDate(Date date2, TimeUnit timeUnit) {
        if (date2 == null) return 0;
        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        long diffInMillies = date.getTime() - date2.getTime();
        return timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }

    public static double getDiffDateWithCurrentDateMilliSeconds(Date date2, TimeUnit timeUnit) {
        if (date2 == null) return 0;
        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        double diffInMillis = date.getTime() - date2.getTime();
        return diffInMillis;
    }

    public static long getDiffDateWithTwoDate(Date from, Date to, TimeUnit timeUnit) {
        long diffInMillies = to.getTime() - from.getTime();
        return timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }

    public static Double getSumDictionaryHashtable(Map<String, Object> discounts) {
        Double db = 0D;
        for (Map.Entry<String, Object> entry : discounts.entrySet()) {
            db = db + Double.valueOf(entry.getValue().toString());
        }
//        Optional<Objectect>  objects= Optional.of(discounts.values().stream().map(u -> (Double) u).reduce(Double::sum));
        return db;
    }

    public static Map<String, Object> getSumDictionaryHashMap(Dictionary discount) {
        Map<String, Object> map = new HashMap<String, Object>();
        Enumeration<String> keys = discount.keys();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            map.put(key, discount.get(key));
        }
        return map;
    }

    public static Object convertIntoJavaObject(Object scriptObj) {
        if (scriptObj instanceof ScriptObjectMirror) {
            ScriptObjectMirror scriptObjectMirror = (ScriptObjectMirror) scriptObj;
            if (scriptObjectMirror.isArray()) {
                List<Object> list = Lists.newArrayList();
                for (Map.Entry<String, Object> entry : scriptObjectMirror.entrySet()) {
                    list.add(convertIntoJavaObject(entry.getValue()));
                }
                return list;
            } else {
                Map<String, Object> map = Maps.newHashMap();
                for (Map.Entry<String, Object> entry : scriptObjectMirror.entrySet()) {
                    map.put(entry.getKey(), convertIntoJavaObject(entry.getValue()));
                }
                return map;
            }
        } else {
            return scriptObj;
        }
    }


    public static class Tuple<T1,T2> {

        public final T1 v1;
        public final T2 v2;

        public Tuple(T1 v1, T2 v2) {
            this.v1 = v1;
            this.v2 = v2;
        }
    }

}
