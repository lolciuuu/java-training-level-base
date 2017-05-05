package com.sda.collection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.stream.Stream;


public class ClassClassDemo {
    private Class clazz;

    private StringBuilder stringBuilder = new StringBuilder();


    public ClassClassDemo(Class clazz) {
        this.clazz = clazz;
    }

    private String getInfo() {
        getClassInfo();
        return stringBuilder.toString();

    }

    public void getClassInfo() {
        stringBuilder.append("Class: ")
                .append(clazz.getName())
                .append("\nPackage: ")
                .append(clazz.getPackage())
                .append("\nConstructors: \t");

        appendConstructorsInfo();

        stringBuilder
                .append("\nInterfaces: \t");

        appendInterfacesInfo();

        stringBuilder
                .append("\nBase Class: ")
                .append(clazz.getSuperclass());
        appendMethodInfo();
    }

    private void appendInterfacesInfo() {
        final Class[] interfaces = clazz.getInterfaces();

        for (Class current : interfaces) {
            stringBuilder.append("\n\t -").append(current);
        }
    }

    private void appendConstructorsInfo() {
        final Constructor[] constructors = clazz.getConstructors();

        for (Constructor current : constructors) {
            String toCut = current.toString();
            stringBuilder
                    .append("\n\t -")
                    .append(cuttingPackageInfo(toCut));
        }
    }

    private String cuttingPackageInfo(String toCut) {

        if (toCut.contains("(")) {
            return cuttingPackageInfo(toCut.substring(0, toCut.lastIndexOf("(")))
                    + splitParameter(toCut.substring(toCut.lastIndexOf("(") + 1, toCut.length() - 1));
        }
        else {
            int cutPackageInfo = toCut.lastIndexOf(".");
            return toCut.substring(++cutPackageInfo);
        }
    }

    private String splitParameter(String parameters) {
        String[] splitted = parameters.split(",");
        String result = "";
        for (String currentParameter : splitted) {
            result += cuttingPackageInfo(currentParameter) + ",";


        }
        result = result.substring(0, result.length() - 1);
        return "(" + result + ")";
    }

    private void appendMethodInfo() {
        final Method[] methods = clazz.getDeclaredMethods();

        for (Method current : methods) {
            stringBuilder.append("\t\n")
                    .append(current.toString().substring(0, current.toString().indexOf(" ") + 1))
                    .append(cuttingPackageInfo(current.toString()));
        }

    }

    public static void main(String[] args) {
        ClassClassDemo demo = new ClassClassDemo(ArrayList.class);
        String info = demo.getInfo();
        System.out.println(info);
    }
}

