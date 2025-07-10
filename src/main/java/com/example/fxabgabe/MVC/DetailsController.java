package com.example.fxabgabe.MVC;

import com.example.fxabgabe.BuLiModel;
import com.example.fxabgabe.Team;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class DetailsController {

    @FXML
    private ComboBox<Team> teamChoice;
    @FXML
    private TextField tfClub;
    @FXML
    private TextField tfPoints;
    @FXML
    private TextField tfGoalsScored;
    @FXML
    private TextField tfGoalsConceded;
    @FXML
    private TextField tfWins;
    @FXML
    private TextField tfLosses;
    @FXML
    private TextField tfDraws;
    @FXML
    private TextField tfSquadValue;
    @FXML
    private TextField tfSquadSize;

    @FXML
    private Label labClub;
    @FXML
    private Label labWins;
    @FXML
    private Label labLosses;
    @FXML
    private Label labDraws;
    @FXML
    private Label labGoalsScored;
    @FXML
    private Label labGoalsConceded;
    @FXML
    private Label labSquadSize;
    @FXML
    private Label labSquadValue;
    @FXML
    private Label labPoints;

    private final BuLiModel model = new BuLiModel();


    @FXML
    protected void initialize() {
        //Set text of labels
        teamChoice
                .getSelectionModel()
                .selectedItemProperty()
                .addListener((_, _, newTeam) -> {
                    if (newTeam != null) {
                        labClub.setText("Club:");
                        labWins.setText("Wins:");
                        labLosses.setText("Losses:");
                        labGoalsConceded.setText("Goals conceded:");
                        labSquadSize.setText("Squad size:");
                        labSquadValue.setText("Squad value:");
                        labDraws.setText("Draws:");
                        labGoalsScored.setText("Goals scored:");
                        labPoints.setText("Points:");
                        }
                    });

        //fill dropdown with teams
        teamChoice.setItems(model.getObservableList().sorted());

        //if dropdown clicked fill text fields with team statistics
        teamChoice.getSelectionModel()
                .selectedItemProperty()
                .addListener((_, _, newTeam) -> {
                    if (newTeam != null) {
                        tfClub.setText(newTeam.getName());
                        tfSquadValue.setText(newTeam.getMarktwert() + " Mio.");
                        tfGoalsScored.setText(String.valueOf(newTeam.getTore()));
                        tfGoalsConceded.setText(String.valueOf(newTeam.getGegentore()));
                        tfWins.setText(String.valueOf(newTeam.getSiege()));
                        tfLosses.setText(String.valueOf(newTeam.getNiederl()));
                        tfDraws.setText(String.valueOf(newTeam.getUnents()));
                        tfSquadSize.setText(String.valueOf(newTeam.getKadergroesse()));
                        tfPoints.setText(String.valueOf(newTeam.getPunkte()));
                    } else {
                        //clear all text fields
                        tfClub.clear();
                        tfSquadValue.clear();
                        tfGoalsScored.clear();
                        tfGoalsConceded.clear();
                        tfWins.clear();
                        tfLosses.clear();
                        tfDraws.clear();
                        tfSquadSize.clear();
                        tfPoints.clear();
                    }
                });
    }
}
