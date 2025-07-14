package com.example.fxabgabe.Model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;


public class BuLiModel {

    private final ObservableList<Team> observableList = javafx.collections.FXCollections.observableArrayList();
    private final ObjectProperty<Team> selectedTeam = new SimpleObjectProperty<>();
    private final SortedList<Team> sortedTeams = new SortedList<>(observableList);


    public BuLiModel() {
        loadTeams();
    }

    void loadTeams() {
        observableList.addAll(
                new Team("FC Bayern München", 32, 892.000000, 94, 26, 28, 3, 3),
                new Team("RB Leipzig", 35, 507.700000, 66, 42, 19, 8, 7),
                new Team("Borussia Dortmund", 30, 435.100000, 70, 37, 25, 6, 3),
                new Team("Eintracht Frankfurt",35, 416.600000, 57, 33, 17, 10, 7),
                new Team("Bayer 04 Leverkusen",24, 391.050000, 80, 30, 22, 10, 2),
                new Team("Vfb Stuttgart",32, 337.630000, 55,45, 18, 5, 11),
                new Team("Vfl Wolfsburg",32, 263.400000, 44, 44, 12, 14, 8),
                new Team("SC Freiburg", 32, 202.500000, 53, 47, 15, 9, 10),
                new Team("TSG 1899 Hoffenheim", 39, 180.350000, 33, 53, 9,10, 15),
                new Team("Borussia Mönchengladbach",29, 160.050000, 50, 45, 11, 8, 15),
                new Team("1.FSV Mainz",29, 145.150000, 38, 40, 12, 12, 10),
                new Team("FC Augsburg",30, 124.080000, 25, 68, 7, 10, 17),
                new Team("SV Werder Bremen",25, 122.300000, 36,37, 13, 6, 15),
                new Team("1.FC Union Berlin",30, 121.500000, 40, 43, 15, 8, 11),
                new Team("1.FC Köln",33, 74.730000, 50, 50, 7, 8, 19),
                new Team("Hamburger SV",31, 55.300000, 35, 44, 13, 7, 14),
                new Team("1.FC Heidenheim 1846",27, 50.400000, 18, 69, 5, 8, 21),
                new Team("FC St. Pauli",28, 45.530000, 38, 33, 12, 9,13));
    }

    public ObservableList<Team> getObservableList() {
        return observableList;
    }

    public SortedList<Team> getSortedTeams() {
        return sortedTeams;
    }

    //setter for ChartController
    public void setSelectedTeam(Team t) { selectedTeam.set(t); }

    public ObjectProperty<Team> selectedTeamProperty() {
        return selectedTeam;
    }

    public Team getSelectedTeamProperty() {
        return selectedTeam.get();
    }
}
