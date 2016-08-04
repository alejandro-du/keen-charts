package org.vaadin.keen.charts;

import com.vaadin.annotations.JavaScript;
import com.vaadin.ui.AbstractJavaScriptComponent;
import io.keen.client.java.Query;

import java.io.IOException;
import java.io.StringWriter;

/**
 * A Vaadin Component that wraps a Keen IO chart.
 *
 * @author alejandro@vaadin.com
 */
@JavaScript({"https://d26b395fwzu5fz.cloudfront.net/3.4.1/keen.min.js", "keen-chart.js"})
public class KeenChart extends AbstractJavaScriptComponent {

    /**
     * Creates a new instance.
     *
     * @param projectId The project ID provided by Keen IO.
     * @param readKey   The read key provided by Keen IO.
     * @param query     The query backing the chart.
     */
    public KeenChart(String projectId,
                     String readKey,
                     Query query) {
        try {
            setSizeFull();
            StringWriter writer = new StringWriter();
            new JacksonJsonHandler().writeJson(writer, query.constructQueryArgs());

            callFunction("draw", projectId, readKey, query.getQueryType().toString(), writer.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
