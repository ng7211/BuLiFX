package com.example.fxabgabe.MVC;

import com.example.fxabgabe.Model.BuLiModel;
import javafx.fxml.FXML;

public class MainViewController {
    @FXML private TeamsTableController tableIncludeController;
    @FXML private DetailsController detailsIncludeController;
    @FXML private ChartController chartIncludeController;

    @FXML
    public void initialize() {
        BuLiModel model = new BuLiModel();

        tableIncludeController.setViewModel(model);
        detailsIncludeController.setViewModel(model);
        chartIncludeController.setViewModel(model);
    }
}
