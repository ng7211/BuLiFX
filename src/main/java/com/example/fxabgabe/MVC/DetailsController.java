package com.example.fxabgabe.MVC;

import com.example.fxabgabe.Model.BuLiModel;
import com.example.fxabgabe.Model.Team;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;

public class DetailsController {
    @FXML private Button saveButton = new Button();
    @FXML private TextField tfClub, tfPoints, tfGoalsScored, tfGoalsConceded,
            tfWins, tfLosses, tfDraws, tfSquadValue, tfSquadSize;
    @FXML private TextField addtfClub, addtfSquadSize, addtfSquadValue,
            addtfGoalsScored, addtfGoalsConceded, addtfWins,
            addtfDraws, addtfLosses;
    @FXML private Label labClub, labWins, labLosses, labDraws, labGoalsScored,
            labGoalsConceded, labSquadSize, labSquadValue, labPoints, labMio;
    @FXML private Label addlabClub, addlabSquadSize, addlabSquadValue, addlabGoalsScored,
            addlabGoalsConceded, addlabWins, addlabDraws, addlabLosses;

    private BuLiModel model;

    public void setViewModel(BuLiModel vm) {
        this.model = vm;

        model.selectedTeamProperty().addListener((_, _, newT) -> {
            if (newT != null) {
                tfClub.setText(newT.getName());
                tfSquadValue.setText(String.valueOf(newT.getMarktwert()));
                tfGoalsScored.setText(String.valueOf(newT.getTore()));
                tfGoalsConceded.setText(String.valueOf(newT.getGegentore()));
                tfWins.setText(String.valueOf(newT.getSiege()));
                tfLosses.setText(String.valueOf(newT.getNiederl()));
                tfDraws.setText(String.valueOf(newT.getUnents()));
                tfSquadSize.setText(String.valueOf(newT.getKadergroesse()));
                tfPoints.setText(String.valueOf(newT.getPunkte()));
            } else {
                tfClub.clear(); tfSquadValue.clear(); tfGoalsScored.clear();
                tfGoalsConceded.clear(); tfWins.clear(); tfLosses.clear();
                tfDraws.clear(); tfSquadSize.clear(); tfPoints.clear();
            }});

    }

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
        labMio.setText("Mio");

        addlabClub.setText("Club:");
        addlabSquadSize.setText("Squad size:");
        addlabSquadValue.setText("Squad value:");
        addlabGoalsScored.setText("Goals scored:");
        addlabGoalsConceded.setText("Goals conceded:");
        addlabWins.setText("Wins:");
        addlabDraws.setText("Draws:");
        addlabLosses.setText("Losses:");

        tfFormatter(addtfSquadSize, addtfSquadValue, addtfGoalsScored, addtfGoalsConceded, addtfWins, addtfDraws, addtfLosses);

        tfFormatter(tfSquadSize, tfSquadValue, tfGoalsScored, tfGoalsConceded, tfWins, tfDraws, tfLosses);
    }

    private void tfFormatter(TextField tfSquadSize, TextField tfSquadValue, TextField tfGoalsScored, TextField tfGoalsConceded, TextField tfWins, TextField tfDraws, TextField tfLosses) {
        tfSquadSize.setTextFormatter(
                new TextFormatter<>(new IntegerStringConverter())
        );
        tfSquadValue.setTextFormatter(
                new TextFormatter<>(new DoubleStringConverter())
        );
        tfGoalsScored.setTextFormatter(
                new TextFormatter<>(new IntegerStringConverter())
        );
        tfGoalsConceded.setTextFormatter(
                new TextFormatter<>(new IntegerStringConverter())
        );
        tfWins.setTextFormatter(
                new TextFormatter<>(new IntegerStringConverter())
        );
        tfDraws.setTextFormatter(
                new TextFormatter<>(new IntegerStringConverter())
        );
        tfLosses.setTextFormatter(
                new TextFormatter<>(new IntegerStringConverter())
        );
    }

    @FXML
    public void addTeam() {
        try {

            Team t = new Team(
                    addtfClub.getText(),
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

        } catch (NumberFormatException ex) {
            new Alert(Alert.AlertType.WARNING,
                    "Bitte alle Felder korrekt ausfüllen.",
                    ButtonType.OK)
                    .showAndWait();
        }
    }

    @FXML
    void updateTeam() {
        if(model.getSelectedTeamProperty() == null) {
            new Alert(Alert.AlertType.WARNING, "Kein Team ausgewählt!", ButtonType.OK).showAndWait();
            return;
        }

        Team selected = model.getSelectedTeamProperty();

        if(!tfClub.getText().trim().isEmpty()) {
            selected.nameProperty().set(tfClub.getText());
        }
        selected.marktwertProperty().set(Double.parseDouble(tfSquadValue.getText()));
        selected.kadergroesseProperty().set(Integer.parseInt(tfSquadSize.getText()));
        selected.toreProperty().set(Integer.parseInt(tfGoalsScored.getText()));
        selected.gegentoreProperty().set(Integer.parseInt(tfGoalsConceded.getText()));
        selected.siegeProperty().set(Integer.parseInt(tfWins.getText()));
        selected.niederlProperty().set(Integer.parseInt(tfLosses.getText()));
        selected.unentsProperty().set(Integer.parseInt(tfDraws.getText()));

        selected.punkteProperty().set(selected.getSiege() * 3 + selected.getUnents());
        selected.spieleGespieltProperty().set(selected.getSiege() + selected.getUnents() + selected.getNiederl());
    }

}
