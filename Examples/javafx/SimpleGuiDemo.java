package it.unipr.sowide.java.javafx;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class SimpleGuiDemo extends Application
{
  @Override
  public void start(final Stage primaryStage)
  {
    BorderPane root  = new BorderPane();
    Scene      scene = new Scene(root, 300, 250, Color.WHITE);

    MenuBar menuBar = new MenuBar();

    menuBar.prefWidthProperty().bind(primaryStage.widthProperty());

    root.setTop(menuBar);

    Menu fileMenu = buildFileMenu();
    Menu webMenu  = buildWebMenu();
    Menu sqlMenu  = buildSqlMenu();

    menuBar.getMenus().addAll(fileMenu, webMenu, sqlMenu);

    primaryStage.setScene(scene);
    primaryStage.show();
  }

  private Menu buildFileMenu()
  {
    Menu     m = new Menu("File");
    MenuItem n = new MenuItem("New");
    MenuItem s = new MenuItem("Save");
    MenuItem e = new MenuItem("Exit");

    e.setOnAction(actionEvent -> Platform.exit());

    m.getItems().addAll(n, s, new SeparatorMenuItem(), e);

    return m;
  }

  private Menu buildWebMenu()
  {
    Menu m = new Menu("Web");

    CheckMenuItem h = new CheckMenuItem("HTML");
    h.setSelected(true);
    m.getItems().add(h);

    CheckMenuItem c = new CheckMenuItem("CSS");
    c.setSelected(true);
    m.getItems().add(c);

    return m;
  }

  private Menu buildSqlMenu()
  {
    Menu m = new Menu("SQL");

    ToggleGroup   g = new ToggleGroup();

    RadioMenuItem r = new RadioMenuItem("MySQL");
    r.setToggleGroup(g);
    RadioMenuItem o = new RadioMenuItem("Oracle");
    o.setToggleGroup(g);
    o.setSelected(true);

    m.getItems().addAll(r, o, new SeparatorMenuItem());

    Menu t = new Menu("Tutorial");

    t.getItems().addAll(new CheckMenuItem("Java"),
        new CheckMenuItem("JavaFX"), new CheckMenuItem("Swing"));

    m.getItems().add(t);

    return m;
  }

  public static void main(final String[] args)
  {
    launch(args);
  }
}
