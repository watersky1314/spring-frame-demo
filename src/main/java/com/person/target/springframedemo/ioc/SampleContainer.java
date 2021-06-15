package com.person.target.springframedemo.ioc;

import sun.reflect.misc.ReflectUtil;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class SampleContainer implements Container {
    /**
     * 保存所有bean对象，格式为 com.xxx.Person : @52x2xa
     */
    private Map<String, Object> beans;

    /**
     * 存储bean和name的关系
     */
    private Map<String, String> beanKeys;

    public SampleContainer() {
        this.beans = new ConcurrentHashMap<>();
        this.beanKeys = new ConcurrentHashMap<>();
    }

    @Override
    public <T> T getBean(Class<T> clazz) {
        String name = clazz.getName();
        Object object = beans.get(name);
        if (null != object) {
            return (T) object;
        }
        return null;
    }

    @Override
    public <T> T getBean(String name) {
        String className = beanKeys.get(name);
        Object obj = beans.get(className);
        if (null != obj) {
            return (T) obj;
        }
        return null;
    }

    @Override
    public Object registerBean(Object bean) {
        String name = bean.getClass().getName();
        beanKeys.put(name, name);
        beans.put(name, bean);
        return bean;
    }

    @Override
    public Object registerBean(Class<?> clazz) {
        String name = clazz.getName();
        beanKeys.put(name, name);
        Object bean = null;
        try {
            bean = ReflectUtil.newInstance(clazz);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        beans.put(name, bean);
        return bean;
    }

    @Override
    public Object registerBean(String name, Object bean) {
        String className = bean.getClass().getName();
        beanKeys.put(name, className);
        beans.put(className, bean);
        return bean;
    }

    @Override
    public Set<String> getBeanNames() {
        return beanKeys.keySet();
    }

    @Override
    public void remove(Class<?> clazz) {
        String className = clazz.getName();
        if (!className.equals("")) {
            beanKeys.remove(className);
            beans.remove(className);
        }
    }

    @Override
    public void removeByName(String name) {
        String className = beanKeys.get(name);
        if (null != className && !className.equals("")) {
            beanKeys.remove(name);
            beans.remove(className);
        }
    }

    @Override
    public void initwrap() {
        for (Map.Entry<String, Object> entry : beans.entrySet()) {
            Object object = entry.getValue();
            injection(object);
        }
    }

    /**
     * 注入对象
     *
     * @param object
     */
    public void injection(Object object) {
        // 所有字段
        try {
            Field[] fields = object.getClass().getDeclaredFields();
            for (Field field : fields) {
                // 需要注入的字段
                Resource resource = field.getAnnotation(Resource.class);
                if (null != resource) {

                    // 要注入的字段
                    Object resourceField = null;

                    String name = resource.name();
                    if (!name.equals("")) {
                        String className = beanKeys.get(name);
                        if (null != className && !className.equals("")) {
                            resourceField = beans.get(className);
                        }
                        if (null == resourceField) {
                            throw new RuntimeException("Unable to load " + name);
                        }
                    } else {
                        if (resource.type() == Class.class) {
                            resourceField = recursiveAssembly(field.getType());
                        } else {
                            // 指定装配的类
                            resourceField = this.getBean(resource.type());
                            if (null == resourceField) {
                                resourceField = recursiveAssembly(resource.type());
                            }
                        }
                    }

                    if (null == resourceField) {
                        throw new RuntimeException("Unable to load " + field.getType().getCanonicalName());
                    }

                    boolean accessible = field.isAccessible();
                    field.setAccessible(true);
                    field.set(object, resourceField);
                    field.setAccessible(accessible);
                }
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private Object recursiveAssembly(Class<?> clazz) {
        if (null != clazz) {
            return this.registerBean(clazz);
        }
        return null;
    }
}
