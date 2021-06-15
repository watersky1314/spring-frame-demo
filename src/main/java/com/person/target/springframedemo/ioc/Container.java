package com.person.target.springframedemo.ioc;

import java.util.Set;

public interface Container {

    /**
     *  注册一个普通的bean到容器中
     * @param bean 普通的bean
     * @return object
     */
    Object registerBean(Object bean);

    /**
     *  注册一个代理对象的bean到容器中
     * @param clazz 代理bean
     * @return Object
     */
    Object registerBean(Class<?> clazz);

    Object registerBean(String name, Object bean);
    /**
     * 根据class获取bean
     * @param clazz no desc
     * @param <T> no desc
     * @return no desc
     */
    <T>T getBean(Class<T> clazz);

    /**
     * 根据名称获取bean
     * @param name 名称
     * @param <T> no desc
     * @return no desc
     */
    <T>T getBean(String name);

    /**
     * 根据名称移除bean
     * @param name no desc
     */
    void removeByName(String name);

    /**
     * 根据类移除bean
     * @param clazz no desc
     */
    void remove(Class<?> clazz);

    /**
     * 获取所有bean的名称
     * @return no desc
     */
    Set<String> getBeanNames();

    /**
     * 初始化装配
     */
    void initwrap();
}
