package controllers.innerviewcontrollers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;

import java.net.URL;
import java.util.ResourceBundle;

public class SubSectionSettingsController implements Initializable {

    @FXML private Slider ageRangeSlider;

    @Override
    public void initialize(URL url, ResourceBundle rb){

        setSliders();

    }

    private void setSliders(){

        ageRangeSlider.setMin(1);
        ageRangeSlider.setMax(7);
        ageRangeSlider.setBlockIncrement(1);
        ageRangeSlider.setMinorTickCount(0);
        ageRangeSlider.setMajorTickUnit(1);
        ageRangeSlider.setSnapToTicks(true);
        ageRangeSlider.setValue(7);
    }


}
