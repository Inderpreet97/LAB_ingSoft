package it.unipr.sowide.java.javafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class BarChartDemo extends Application
{
  @Override
  public void start(final Stage stage)
  {
    CategoryAxis xAxis = new CategoryAxis();
    NumberAxis yAxis = new NumberAxis();

    yAxis.setLabel("Gold medals");

    BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
    barChart.setTitle("Olympic gold medals in London");

    XYChart.Series<String, Number> data = new XYChart.Series<>();

    data.getData().add(new XYChart.Data<String, Number>("USA", 46));
    data.getData().add(new XYChart.Data<String, Number>("China", 38));
    data.getData().add(new XYChart.Data<String, Number>("UK", 29));
    data.getData().add(new XYChart.Data<String, Number>("Russia", 22));
    data.getData().add(new XYChart.Data<String, Number>("South Korea", 13));
    data.getData().add(new XYChart.Data<String, Number>("Germany", 11));

    barChart.getData().add(data);
    barChart.setLegendVisible(false);

    HBox root = new HBox();

    root.getChildren().add(barChart);

    Scene scene = new Scene(root, 480, 330);

    stage.setTitle("BarChart");
    stage.setScene(scene);
    stage.show();
  }

  public static void main(final String[] args)
  {
    launch(args);
  }
}
