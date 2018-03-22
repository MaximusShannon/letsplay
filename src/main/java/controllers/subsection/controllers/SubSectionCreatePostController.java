package controllers.subsection.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SubSectionCreatePostController implements Initializable {

    ArrayList<MenuItem> menuItems;

    @FXML
    private MenuButton languageSpoken;
    @FXML
    private MenuButton gamePlayed;
    @FXML
    private MenuButton timeZone;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        languageSpoken.getItems().addAll(generateLanguages());
        gamePlayed.getItems().addAll(generateGames());
    }

    /**
     *
     * returns a list to add it to the language spoken menubutton on the view.
     * @return
     */
    private ArrayList<MenuItem> generateLanguages(){

        MenuItem menuItem;
        String languages[]
                = {"Manderin", "English",
                "Spanish", "French",
                "Norwegian", "Portuguese",
                "Bengali", "Russian",
                "Dutch", "Irish"};

        menuItems = new ArrayList<>();

        for (String language : languages) {

            menuItem = new MenuItem(language);
            menuItems.add(menuItem);
        }

        return menuItems;
    }

    private ArrayList<MenuItem> generateGames(){


        return menuItems;
    }
}
