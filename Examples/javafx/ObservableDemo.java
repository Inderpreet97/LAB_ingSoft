package it.unipr.sowide.java.javafx;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener.Change;
import javafx.collections.ObservableList;

public class ObservableDemo
{
  public static void main(final String[] args)
  {
    List<String> list = new ArrayList<String>();

    ObservableList<String> observableList = FXCollections.observableList(list);

    observableList.addListener((Change<? extends String> c) ->
        System.out.println("change!"));

    observableList.add("item one");
    list.add("item two");
    System.out.println("Size: " + observableList.size());
  }
}
