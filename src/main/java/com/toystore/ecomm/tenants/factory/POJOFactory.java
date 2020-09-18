package com.toystore.ecomm.tenants.factory;

import java.util.HashMap;
import java.util.Map;

public class POJOFactory {
	private static final Map<String, Class<?>> instances = new HashMap<>();

    public static void register(String pojoName, Class<?> instance) {
        if (pojoName != null && instance != null) {
            instances.put(pojoName, instance);
        }
    }

    public static Object getInstance(String pojoName) throws IllegalAccessException, InstantiationException {
        if (instances.containsKey(pojoName)) {
            return ((Class<?>)(instances.get(pojoName))).newInstance();
        }
        return null;
    }
}
