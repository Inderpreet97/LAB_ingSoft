package it.unipr.sowide.java.javafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class LineChartDemo extends Application
{
  @Override
  public void start(final Stage stage)
  {
    HBox root = new HBox();

    Scene scene = new Scene(root, 450, 330);

    NumberAxis xAxis = new NumberAxis();
    xAxis.setLabel("Age");

    NumberAxis yAxis = new NumberAxis();
    yAxis.setLabel("Salary (€)");

    LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
    lineChart.setTitle("Average salary per age");

    XYChart.Series<Number, Number> data = new XYChart.Series<>();
    data.setName("2016");

    data.getData().add(new XYChart.Data<Number, Number>(18, 567));
    data.getData().add(new XYChart.Data<Number, Number>(20, 612));
    data.getData().add(new XYChart.Data<Number, Number>(25, 800));
    data.getData().add(new XYChart.Data<Number, Number>(30, 980));
    data.getData().add(new XYChart.Data<Number, Number>(40, 1410));
    data.getData().add(new XYChart.Data<Number, Number>(50, 2350));

    lineChart.getData().add(data);

    root.getChildren().add(lineChart);

    stage.setTitle("LineChart");
    stage.setScene(scene);
    stage.show();
  }

  public static void main(final String[] args)
  {
    launch(args);
  }
}
