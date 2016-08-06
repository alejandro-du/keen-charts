package org.vaadin.keen.charts;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;
import io.keen.client.java.AbsoluteTimeframe;
import io.keen.client.java.Query;
import io.keen.client.java.QueryType;

/**
 * A simple UI containing some KeenCharts.
 *
 * @author alejandro@vaadin.com
 */
@Theme(ValoTheme.THEME_NAME)
public class DemoUI extends UI {

    private final String projectId = "579dc2a53831444785e06dd4";

    private final String readKey = "7d46c7a5e73064eeb23a5899cb631a8acefd48b748e6e95db16f8d4b241a334f7406c52ab3cdb47f0ce8bf53d8e0a82b3f317d1f82a81ef4771c5d8874441e666b47940536702d8bab076c9c36f11c059919df88a799999845ad009e5511ff1c";

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        KeenChart chart1 = new KeenChart(projectId, readKey, new Query.Builder(QueryType.SUM)
                .withEventCollection("gifts")
                .withTargetProperty("value")
                .withTimeframe(new AbsoluteTimeframe("2016-08-01T00:00", "2016-08-31T23:59"))
                .build());

        KeenChart chart2 = new KeenChart(projectId, readKey, new Query.Builder(QueryType.SUM)
                .withEventCollection("gifts")
                .withTargetProperty("value")
                .withTimeframe(new AbsoluteTimeframe("2016-08-01T00:00", "2016-08-31T23:59"))
                .withGroupBy("name")
                .build());

        KeenChart chart3 = new KeenChart(projectId, readKey, new Query.Builder(QueryType.COUNT)
                .withEventCollection("gifts")
                .withTimeframe(new AbsoluteTimeframe("2016-08-01", "2016-08-05"))
                .withInterval("daily")
                .build());

        KeenChart chart4 = new KeenChart(projectId, readKey, KeenChartType.BARCHART, new Query.Builder(QueryType.COUNT)
                .withEventCollection("gifts")
                .withTimeframe(new AbsoluteTimeframe("2016-08-01", "2016-08-05"))
                .withInterval("daily")
                .build());

        GridLayout layout = new GridLayout(2, 2);
        layout.addComponents(chart1, chart2, chart3, chart4);
        layout.setSizeFull();
        layout.setMargin(true);
        layout.setSpacing(true);
        setContent(layout);
    }

}
