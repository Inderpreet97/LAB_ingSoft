package it.unipr.sowide.java.javafx;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;

public class PieChartDemo extends Application
{
  @Override
  public void start(final Stage stage)
  {
    Scene scene = new Scene(new Group());
    stage.setTitle("Imported Fruits");
    stage.setWidth(500);
    stage.setHeight(500);

    PieChart chart = new PieChart(FXCollections.observableArrayList(
        new PieChart.Data("Grapefruit", 13), new PieChart.Data("Oranges", 25),
        new PieChart.Data("Plums", 10), new PieChart.Data("Pears", 22),
        new PieChart.Data("Apples", 30)));

    chart.setTitle("Imported Fruits");

    ((Group) scene.getRoot()).getChildren().add(chart);
    stage.setScene(scene);
    stage.show();
  }

  public static void main(final String[] args)
  {
    launch(args);
  }
}
