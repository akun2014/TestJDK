package com.ownerkaka.testjdk;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author akun
 * @since 2019-07-29
 */
@WebServlet("/testclassloader")
public class TestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        ClassLoader classLoader = this.getClass().getClassLoader();

        while (classLoader != null) {
            System.out.println("classLoader:" + classLoader.getClass().getCanonicalName());
            classLoader = classLoader.getParent();
        }
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        System.out.println("thread classloader:" + loader.getClass().getCanonicalName());
    }
}
