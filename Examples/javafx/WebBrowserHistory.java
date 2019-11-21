package it.unipr.sowide.java.javafx;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebHistory.Entry;
import javafx.scene.web.WebView;

public class WebBrowserHistory extends HBox
{
  public WebBrowserHistory(final WebView webView)
  {
    this.setSpacing(4);
    this.setStyle("-fx-padding: 10;"
        + "-fx-border-style: solid inside;"
        + "-fx-border-width: 2;"
        + "-fx-border-insets: 5;"
        + "-fx-border-radius: 5;"
        + "-fx-border-color: blue;");

    WebHistory history       = webView.getEngine().getHistory();
    Label      label         = new Label("History:");
    Button     backButton    = new Button("Back");
    Button     forwardButton = new Button("Forward");

    backButton.setDisable(true);
    forwardButton.setDisable(true);

    backButton.setOnAction((e) -> history.go(-1));
    forwardButton.setOnAction((e) -> history.go(1));

    history.currentIndexProperty().addListener((i, o, n) -> {
      int currentIndex = n.intValue();

      if (currentIndex <= 0)
      {
        backButton.setDisable(true);
      }
      else
      {
        backButton.setDisable(false);
      }

      if (currentIndex >= history.getEntries().size())
      {
        forwardButton.setDisable(true);
      }
      else
      {
        forwardButton.setDisable(false);
      }
    });

    ComboBox<Entry> historyList = new ComboBox<>();
    historyList.setPrefWidth(150);
    historyList.setItems(history.getEntries());

    historyList.setCellFactory((l) -> {
      ListCell<Entry> cell = new ListCell<Entry>()
      {
        @Override
        public void updateItem(final Entry item, final boolean empty)
        {
          super.updateItem(item, empty);

          if (empty)
          {
            this.setText(null);
            this.setGraphic(null);
          }
          else
          {
            String pageTitle = item.getTitle();
            this.setText(pageTitle);
          }
        }
      };

      return cell;
    });

    historyList.setOnAction((e) -> {
      int currentIndex = history.getCurrentIndex();

      Entry selectedEntry = historyList.getValue();

      int selectedIndex = historyList.getItems().indexOf(selectedEntry);
      int offset        = selectedIndex - currentIndex;
      history.go(offset);
    });

    this.getChildren().addAll(backButton, forwardButton, label, historyList);
  }
}
