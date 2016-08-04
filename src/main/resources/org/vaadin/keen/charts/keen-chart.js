window.org_vaadin_keen_charts_KeenChart = function() {
    var connector = this;
    var element = connector.getElement();
    var chartId = "keen-chart-" + connector.getConnectorId();
    element.innerHTML='<div class="keen-chart"></div>';
    element.setAttribute("id", chartId);

    connector.draw = function(projectId, readKey, queryType, args) {
        var client = new Keen({
            projectId: projectId,
            readKey: readKey
        });

        Keen.ready(function() {
            var query = new Keen.Query(queryType, JSON.parse(args));
            client.draw(query, element, {});
        });
    }
}