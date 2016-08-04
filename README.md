# Keen Charts Add-on for Vaadin

Keen Charts is a Vaadin integration for Keen IO, an analytics service to easily collect, explore, and visualize data.

# Usage

You don't need to recompile the widgetset in order to use this add-on. You need the project ID and the read key provided by Keen IO when you create an account at https://keen.io.

Creating a chart is straightforward:

    KeenChart chart = new KeenChart("PROJECT_ID", "READ_KEY", new Query.Builder(QueryType.SUM)
            .withEventCollection("gifts")
            .withTargetProperty("value")
            .withTimeframe(new AbsoluteTimeframe("2016-08-01T00:00", "2016-08-31T23:59"))
            .build());

Use your own values for PROJECT_ID and READ_KEY.

For more information about the Keen IO Java API, consult the documentation at https://github.com/keenlabs/KeenClient-Java

For more information about Keen IO, consult the documentation at https://keen.io/docs/

## License

Add-on is distributed under Apache License 2.0. For license terms, see LICENSE.txt.