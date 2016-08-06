package org.vaadin.keen.charts;

import com.vaadin.annotations.JavaScript;
import com.vaadin.ui.AbstractJavaScriptComponent;
import io.keen.client.java.Query;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

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
        this(projectId, readKey, null, query);
    }

    public KeenChart(String projectId,
                     String readKey,
                     KeenChartType chartType,
                     Query query) {
        try {
            setSizeFull();
            Map<String, Object> customArgsMap = new HashMap<>();

            if (chartType != null) {
                customArgsMap.put("chartType", chartType.toString().toLowerCase());
            }

            String args = toString(query.constructQueryArgs());
            String customArgs = toString(customArgsMap);

            String queryType = query.getQueryType().toString();
            callFunction("draw", projectId, readKey, queryType, args, customArgs);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String toString(Map<String, Object> map) throws IOException {
        StringWriter writer = new StringWriter();
        new JacksonJsonHandler().writeJson(writer, map);
        return writer.toString();
    }

}
