package com.example.fxabgabe.MVC;

import com.example.fxabgabe.Model.Team;
import com.example.fxabgabe.Model.BuLiModel;
import javafx.fxml.FXML;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;


public class ChartController {
    @FXML private ComboBox<String> chartChoice;
    @FXML private ScatterChart<Number, Number> scatterChart;
    @FXML private NumberAxis xAxis;
    @FXML private NumberAxis yAxis;

    private BuLiModel model = new BuLiModel();

    @FXML
    private void initialize() {
        xAxis.setLabel("Market value (Mio €)");
        yAxis.setLabel("Points");

        chartChoice.getItems().addAll(
                "Points vs Market value",
                "Points vs Squad size",
                "Goals scored vs Goals conceded"
        );

        //Listener to switch choice
        chartChoice.getSelectionModel().selectedItemProperty()
                .addListener((_, _, newOpt) -> updateChart(newOpt));

        //Ensuring chart is not null
        if (!chartChoice.getItems().isEmpty()) {
            chartChoice.getSelectionModel().selectFirst();
        }
    }

    private void updateChart(String option) {
        scatterChart.getData().clear();
        XYChart.Series<Number,Number> series = new XYChart.Series<>();

        for (Team t : model.getSortedTeams()) {
            if (option == null) return;
            XYChart.Data<Number,Number> d;
            switch(option) {
                case "Points vs Market value":
                    d = new XYChart.Data<>(t.getMarktwert(), t.getPunkte());
                    xAxis.setLabel("Team value");
                    yAxis.setLabel("Points");
                    break;
                case "Points vs Squad size":
                    d = new XYChart.Data<>(t.getKadergroesse(), t.getPunkte());
                    xAxis.setLabel("Squad size");
                    yAxis.setLabel("Points");
                    break;
                case "Goals scored vs Goals conceded":
                    d = new XYChart.Data<>(t.getTore(), t.getGegentore());
                    xAxis.setLabel("Scored");
                    yAxis.setLabel("Conceded");
                    break;
                default:
                    return;
            }
            d.setExtraValue(t);
            series.getData().add(d);

        }

        scatterChart.getData().add(series);
    }

    public void setViewModel(BuLiModel model) {
        this.model = model;
        updateChart(chartChoice.getSelectionModel().getSelectedItem());
    }
}
