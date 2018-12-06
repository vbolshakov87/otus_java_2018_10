package ru.otus.l041.framework;

import ru.otus.l041.framework.Exceptions.TestRunException;
import ru.otus.l041.framework.Exceptions.TestValidationException;
import ru.otus.l041.framework.annotations.After;
import ru.otus.l041.framework.annotations.Before;
import ru.otus.l041.framework.annotations.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.HashMap;

public class TestClassRunner {
    private HashMap<Class, ArrayList<Method>> functions;
    private HashMap<Method, String> methodMessages;
    private Class clazz;
    private Object object;

    public TestClassRunner(Class clazz) throws TestValidationException {
        this.clazz = clazz;

        functions = new HashMap<Class, ArrayList<Method>>();
        functions.put(Before.class, new ArrayList<>());
        functions.put(After.class, new ArrayList<>());
        functions.put(Test.class, new ArrayList<>());

        methodMessages = new HashMap<Method, String>();

        setMethods();
    }

    public void run() throws TestRunException {
        for (Method testFunction: functions.get(Test.class)) {
            object = ReflectionHelper.instantiate(clazz);
            if (object == null) {
                throw new TestRunException("Cannot run tests as object is nor created");
            }

            Output.title(String.format("Execute test: %s", testFunction.getName()));
            for (Method function: functions.get(Before.class)) {
                runTestMethod(function);
            }

            Output.info(String.format("Run test %s", testFunction.getName()));
            runTestMethod(testFunction);

            for (Method function: functions.get(After.class)) {
                runTestMethod(function);
            }

            Output.positive("passed");
            Output.finish();
        }
    }

    private void runTestMethod(Method function) {
        if (methodMessages.containsKey(function)) {
            Output.info(methodMessages.get(function));
        }
        ReflectionHelper.callMethod(object, function.getName());
    }

    private void setMethods() throws TestValidationException {
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            setMethodByAnnotation(method);
        }
    }

    private void setMethodByAnnotation(Method method) throws TestValidationException {
        for (Annotation annotation : method.getDeclaredAnnotations()) {
            Class annotationType = annotation.annotationType();
            if (!functions.containsKey(annotationType)) {
                continue;
            }

            validateTestAnnotations(method, annotationType);
            functions.get(annotationType).add(method);

            String message = getMessage(method, annotationType);
            if (message.length() > 0) {
                methodMessages.put(method, message);
            }
        }
    }

    private void validateTestAnnotations(Method method, Class allowedClazz) throws TestValidationException {
        for (Annotation annotation : method.getDeclaredAnnotations()) {
            Class annotationType = annotation.annotationType();
            if (annotationType == allowedClazz) {
                continue;
            }
            if (functions.containsKey(annotationType)) {
                throw new TestValidationException(String.format(
                        "method with annotation %s is not allowed to have %s annotation",
                        allowedClazz.getSimpleName(),
                        annotationType.getSimpleName()
                ));
            }
        }
    }

    private String getMessage(Method method, Class annotationType) {
        if (annotationType == Before.class) {
            return method.getAnnotation(Before.class).message();
        }
        if (annotationType == After.class) {
            return method.getAnnotation(After.class).message();
        }
        if (annotationType == Test.class) {
            return method.getAnnotation(Test.class).message();
        }

        return "";
    }
}
