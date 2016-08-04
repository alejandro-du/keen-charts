package org.vaadin.keen.charts;

import com.vaadin.server.DefaultUIProvider;
import com.vaadin.server.VaadinServlet;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * A simple Java Application that starts a Jetty Server and initializes a Vaadin application.
 *
 * @author alejandro@vaadin.com
 */
public class TestApp {

    public static void main(String[] args) throws Exception {
        Server server = new Server(9090);
        server.setHandler(buildHandler(TestUI.class.getName()));
        server.start();
    }

    private static Handler buildHandler(String uiClassName) throws IOException {
        Path webappPath = Paths.get("test-app-webapp");
        Files.deleteIfExists(webappPath);
        String war = Files.createDirectory(webappPath).toString();

        ServletHolder servletHolder = new ServletHolder(new VaadinServlet());
        servletHolder.setInitParameter(VaadinServlet.SERVLET_PARAMETER_UI_PROVIDER, DefaultUIProvider.class.getName());
        servletHolder.setInitParameter("UI", uiClassName);

        WebAppContext context = new WebAppContext(war, "/");
        context.addServlet(servletHolder, "/*");
        return context;
    }

}
