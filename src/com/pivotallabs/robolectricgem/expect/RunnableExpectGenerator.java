package com.pivotallabs.robolectricgem.expect;

import com.pivotallabs.greatexpectations.BaseMatcher;
import com.pivotallabs.greatexpectations.ExpectGenerator;
import com.pivotallabs.robolectricgem.matchers.TextViewMatcher;
import com.pivotallabs.robolectricgem.matchers.ViewMatcher;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

/**
 * See https://github.com/xian/great-expectations
 */
public class RunnableExpectGenerator extends ExpectGenerator {
    public RunnableExpectGenerator(String packageName) {
        super(packageName);
    }

    public static void main(String args[]) throws java.io.IOException {
        generateCustomExpect();
    }

    /**
     * @throws FileNotFoundException
     *
     * Regenerate Expect.java to make you custom Matcher methods available in tests:
     *
     * 1. Create your new Matcher class
     * 2. Add it to the list in matcherClasses()
     * 3. Execute this method to regenerate Expect.java
     * 
     */
    public static void generateCustomExpect() throws FileNotFoundException {
        String packageName = Expect.class.getPackage().getName();
        String path = "src/" + packageName.replace(".", "/") + "/Expect.java";
        System.out.println("path = " + path);
        System.out.println("packagename = " + packageName);
        RunnableExpectGenerator expectGenerator = new RunnableExpectGenerator(packageName);
        expectGenerator.setOut(new PrintStream(new File(path)));
        expectGenerator.generate();
    }
    
    @Override
    @SuppressWarnings({"unchecked"})
    public List<Class<? extends BaseMatcher>> matcherClasses() {
        List<Class<? extends BaseMatcher>> classes = super.matcherClasses();

        Class<? extends BaseMatcher>[] customMatcherClasses = new Class[] {
                ViewMatcher.class,
                TextViewMatcher.class
        };

        classes.addAll(Arrays.asList(customMatcherClasses));
        return classes;
    }
}
