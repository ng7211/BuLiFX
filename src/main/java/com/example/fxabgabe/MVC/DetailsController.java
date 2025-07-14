package com.example.fxabgabe.MVC;

import com.example.fxabgabe.Model.BuLiModel;
import com.example.fxabgabe.Model.Team;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class DetailsController {

    @FXML private ComboBox<Team> teamChoice;

    @FXML private TextField tfClub, tfPoints, tfGoalsScored, tfGoalsConceded,
            tfWins, tfLosses, tfDraws, tfSquadValue, tfSquadSize;

    @FXML private TextField addtfClub, addtfSquadSize, addtfSquadValue,
            addtfGoalsScored, addtfGoalsConceded, addtfWins,
            addtfDraws, addtfLosses;

    @FXML private Label labClub, labWins, labLosses, labDraws, labGoalsScored,
            labGoalsConceded, labSquadSize, labSquadValue, labPoints;
    @FXML private Label addlabClub, addlabSquadSize, addlabSquadValue, addlabGoalsScored,
            addlabGoalsConceded, addlabWins, addlabDraws, addlabLosses;

    private BuLiModel model = new BuLiModel();

    @FXML
    protected void initialize() {
        labClub.setText("Club:");
        labWins.setText("Wins:");
        labLosses.setText("Losses:");
        labDraws.setText("Draws:");
        labGoalsScored.setText("Goals scored:");
        labGoalsConceded.setText("Goals conceded:");
        labSquadSize.setText("Squad size:");
        labSquadValue.setText("Squad value:");
        labPoints.setText("Points:");

        addlabClub.setText("Club:");
        addlabSquadSize.setText("Squad size:");
        addlabSquadValue.setText("Squad value:");
        addlabGoalsScored.setText("Goals scored:");
        addlabGoalsConceded.setText("Goals conceded:");
        addlabWins.setText("Wins:");
        addlabDraws.setText("Draws:");
        addlabLosses.setText("Losses:");

        //fill dropdown
        teamChoice.setItems(model.getObservableList());

        //listener to show details
        teamChoice.getSelectionModel().selectedItemProperty().addListener((_, _, newT) -> {
            if (newT != null) {
                // Details anzeigen
                tfClub.setText(newT.getName());
                tfSquadValue.setText(newT.getMarktwert() + " Mio.");
                tfGoalsScored.setText(String.valueOf(newT.getTore()));
                tfGoalsConceded.setText(String.valueOf(newT.getGegentore()));
                tfWins.setText(String.valueOf(newT.getSiege()));
                tfLosses.setText(String.valueOf(newT.getNiederl()));
                tfDraws.setText(String.valueOf(newT.getUnents()));
                tfSquadSize.setText(String.valueOf(newT.getKadergroesse()));
                tfPoints.setText(String.valueOf(newT.getPunkte()));
                // ans ViewModel weitergeben
                model.setSelectedTeam(newT);
            } else {
                tfClub.clear(); tfSquadValue.clear(); tfGoalsScored.clear();
                tfGoalsConceded.clear(); tfWins.clear(); tfLosses.clear();
                tfDraws.clear(); tfSquadSize.clear(); tfPoints.clear();
            }
        });
    }

    @FXML
    public void addTeam() {
        try {
            Team t = new Team(
                    addtfClub.getText().trim(),
                    Integer.parseInt(addtfSquadSize.getText()),
                    Double.parseDouble(addtfSquadValue.getText()),
                    Integer.parseInt(addtfGoalsScored.getText()),
                    Integer.parseInt(addtfGoalsConceded.getText()),
                    Integer.parseInt(addtfWins.getText()),
                    Integer.parseInt(addtfDraws.getText()),
                    Integer.parseInt(addtfLosses.getText())
            );
            model.getObservableList().add(t);

            //when team is added textfilds are cleared
            addtfClub.clear(); addtfSquadSize.clear(); addtfSquadValue.clear();
            addtfGoalsScored.clear(); addtfGoalsConceded.clear();
            addtfWins.clear(); addtfDraws.clear(); addtfLosses.clear();

            //new entry
            teamChoice.getSelectionModel().select(t);

        } catch (NumberFormatException ex) {
            new Alert(Alert.AlertType.WARNING,
                    "Bitte alle Felder korrekt ausfüllen.",
                    ButtonType.OK)
                    .showAndWait();
        }
    }


    public void setViewModel(BuLiModel vm) {
        this.model = vm;
        //fill dropdown
        teamChoice.setItems(vm.getObservableList());
    }
}
