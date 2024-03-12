package com.tx.operating.utils;

import com.tx.operating.service.IParseFunction;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tanxiong
 */
public class FindImplementationsUtil {

    public static List<Object> findImplementations(Class<?> interfaceClass) {
        List<Object> parseFunctions = new ArrayList<>();
        try {
            // 获取当前线程的类加载器
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

            // 获取类加载器加载的所有类
            List<Class<?>> allClasses = scanClasses(classLoader);

            // 遍历所有类，判断是否实现了指定接口
            for (Class<?> clazz : allClasses) {
                if (interfaceClass.isAssignableFrom(clazz) && !clazz.isInterface() && !clazz.equals(interfaceClass)) {
                    parseFunctions.add(clazz.newInstance());
                }
            }
        } catch (IOException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return parseFunctions;
    }

    private static List<Class<?>> scanClasses(ClassLoader classLoader) throws IOException, ClassNotFoundException {
        List<Class<?>> classes = new ArrayList<>();
        String[] paths = System.getProperty("java.class.path").split(System.getProperty("path.separator"));
        for (String path : paths) {
            FindImplementationsUtil.scan(new File(path), classLoader, classes);
        }
        return classes;
    }

    private static void scan(File directory, ClassLoader classLoader, List<Class<?>> classes) throws ClassNotFoundException {
        if (directory.isDirectory()) {
            for (File file : directory.listFiles()) {
                if (file.isDirectory()) {
                    scan(file, classLoader, classes);
                } else if (file.getName().endsWith(".class")) {
                    String className = file.getPath()
                            .replace(System.getProperty("file.separator"), ".")
                            .substring(file.getPath().indexOf("classes") + 8, file.getPath().lastIndexOf("."));
                    Class<?> clazz = classLoader.loadClass(className);
                    classes.add(clazz);
                }
            }
        }
    }

}
