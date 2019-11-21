package it.unipr.sowide.java.javafx;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.FontSmoothingType;
import javafx.scene.web.WebView;

public class WebMenu extends MenuButton
{
  public WebMenu(final WebView webView)
  {
    this.setText("Options");
    this.setStyle("-fx-padding: 10;"
        + "-fx-border-style: solid inside;"
        + "-fx-border-width: 2;"
        + "-fx-border-insets: 5;"
        + "-fx-border-radius: 5;"
        + "-fx-border-color: blue;");

    CheckMenuItem ctxMenu = new CheckMenuItem("Enable Context Menu");

    ctxMenu.setSelected(true);

    MenuItem normalFontMenu  = new MenuItem("Normal");
    MenuItem biggerFontMenu  = new MenuItem("10% Bigger");
    MenuItem smallerFontMenu = new MenuItem("10% Smaller");

    MenuItem normalZoomMenu  = new MenuItem("Normal");
    MenuItem biggerZoomMenu  = new MenuItem("10% Bigger");
    MenuItem smallerZoomMenu = new MenuItem("10% Smaller");

    RadioMenuItem grayMenu = new RadioMenuItem("GRAY");
    RadioMenuItem lcdMenu  = new RadioMenuItem("LCD");

    grayMenu.setSelected(true);

    Menu scalingMenu   = new Menu("Font Scale");
    Menu smoothingMenu = new Menu("Font Smoothing");
    Menu zoomMenu      = new Menu("Zoom");

    scalingMenu.textProperty().bind(
        new SimpleStringProperty("Font Scale ").concat(
            webView.fontScaleProperty().multiply(100.0)).concat("%"));

    zoomMenu.textProperty().bind(new SimpleStringProperty("Zoom ").concat(
        webView.zoomProperty().multiply(100.0)).concat("%"));

    scalingMenu.getItems().addAll(normalFontMenu, biggerFontMenu,
        smallerFontMenu);
    smoothingMenu.getItems().addAll(grayMenu, lcdMenu);
    zoomMenu.getItems().addAll(normalZoomMenu, biggerZoomMenu, smallerZoomMenu);

    new ToggleGroup().getToggles().addAll(lcdMenu, grayMenu);

    normalFontMenu.setOnAction((e) -> webView.setFontScale(1.0));

    biggerFontMenu.setOnAction(
        (e) -> webView.setFontScale(webView.getFontScale() + 0.10));

    smallerFontMenu.setOnAction(
        (e) -> webView.setFontScale(webView.getFontScale() - 0.10));

    grayMenu.setOnAction(
        (e) -> webView.setFontSmoothingType(FontSmoothingType.GRAY));

    lcdMenu.setOnAction(
        (e) -> webView.setFontSmoothingType(FontSmoothingType.LCD));

    normalZoomMenu.setOnAction((e) -> webView.setZoom(1.0));

    biggerZoomMenu.setOnAction(
        (e) -> webView.setZoom(webView.getZoom() + 0.10));

    smallerZoomMenu.setOnAction(
        (e) -> webView.setZoom(webView.getZoom() - 0.10));

    webView.contextMenuEnabledProperty().bind(ctxMenu.selectedProperty());

    CheckMenuItem scriptMenu = new CheckMenuItem("Enable JavaScript");
    scriptMenu.setSelected(true);
    webView.getEngine().javaScriptEnabledProperty().bind(
        scriptMenu.selectedProperty());

    this.getItems().addAll(ctxMenu, scalingMenu, smoothingMenu, zoomMenu,
        new SeparatorMenuItem(), scriptMenu);
  }
}

