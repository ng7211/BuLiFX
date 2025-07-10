package com.example.fxabgabe.MVC;

import com.example.fxabgabe.Model.BuLiModel;
import com.example.fxabgabe.Model.Team;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class TeamsTableController {
    @FXML
    private TableView<Team> table;
    @FXML
    private TableColumn<Team, String> colClub;
    @FXML
    private TableColumn<Team, Number> colGamesPlayed, colWin, colDraw, colLosses, colGD, colPoints, colRank, colScored, colConceded;

    private final BuLiModel buLiModel = new BuLiModel();

    @FXML
    protected void initialize() {
        table.setItems(buLiModel.getSortedTeams());

        colRank.setCellValueFactory(cell ->
                Bindings.createIntegerBinding(
                        //for every cell get value in list and add one
                        () -> table.getItems().indexOf(cell.getValue()) + 1,
                        //if items are changed (new calculation)
                        table.itemsProperty()
                )
        );

        colClub.setCellValueFactory(club -> club.getValue().nameProperty());
        colGamesPlayed.setCellValueFactory(gp -> gp.getValue().spieleGespieltProperty());
        colPoints.setCellValueFactory(points -> points.getValue().punkteProperty());
        colDraw.setCellValueFactory(draw -> draw.getValue().unentsProperty());
        colLosses.setCellValueFactory(losses -> losses.getValue().niederlProperty());
        colWin.setCellValueFactory(win -> win.getValue().siegeProperty());
        colGD.setCellValueFactory(gd -> gd.getValue().torDiffProperty());
        colScored.setCellValueFactory(scored -> scored.getValue().toreProperty());
        colConceded.setCellValueFactory(conceded -> conceded.getValue().gegentoreProperty());

    }
}    