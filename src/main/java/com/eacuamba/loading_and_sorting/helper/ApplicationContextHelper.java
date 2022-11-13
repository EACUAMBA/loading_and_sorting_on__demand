package com.eacuamba.loading_and_sorting.helper;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.ResolvableType;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.util.ArrayUtils;

@Component
public class ApplicationContextHelper implements ApplicationContextAware {
    private static ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextHelper.applicationContext = applicationContext;
    }

    public static <T> T getBean(Class<T> tClass, Class<?> type){
        System.out.printf("Trying to find a bean of class %s and type %s.%n", tClass.getName(), type.getName());
        String[] applicationContextBeanNamesForType = ApplicationContextHelper.applicationContext.getBeanNamesForType(ResolvableType.forClassWithGenerics(tClass, type));
        return (T)applicationContext.getBean(applicationContextBeanNamesForType[0], tClass);
    }

    public static <T> T getBean(Class<T> tClass){
        System.out.printf("Trying to find a bean named %s.%n", tClass.getName());
        return applicationContext.getBean(tClass);
    }
}
