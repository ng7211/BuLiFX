package com.example.fxabgabe.MVC;

import com.example.fxabgabe.Model.BuLiModel;
import com.example.fxabgabe.Model.Team;
import javafx.beans.binding.Bindings;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class TeamsTableController {
    @FXML
    private TableView<Team> table;
    @FXML
    private TableColumn<Team, String> colClub;
    @FXML
    private TableColumn<Team, Number> colGamesPlayed, colWin, colDraw, colLosses, colGD, colPoints, colRank, colScored, colConceded;

    @FXML
    ContextMenu ctx = new ContextMenu();

    private BuLiModel buLiModel;

    @FXML
    protected void initialize() {

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

        MenuItem sortByPoints = new MenuItem("Sort by most points");
        MenuItem sortByGoals   = new MenuItem("Sort by least goals");

        sortByPoints.setOnAction(_ -> {
            table.getSortOrder().setAll(colPoints);
            table.sort();
        });
        sortByGoals.setOnAction(_ -> {
            table.getSortOrder().setAll(colScored);
            table.sort();
        });

        ctx.getItems().addAll(sortByPoints, sortByGoals);

        table.setContextMenu(ctx);
    }

    public void setViewModel(BuLiModel model) {
        SortedList<Team> sorted = model.getSortedTeams();
        sorted.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sorted);
    }
}    