package controllers.subsection.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;

import java.net.URL;
import java.util.ResourceBundle;

public class SubSectionSettingsController implements Initializable {

    @FXML private Slider sensitivitySlider;
    @FXML private Slider ageRangeSlider;

    @Override
    public void initialize(URL url, ResourceBundle rb){

        setSliders();
        sensitivitySlider.setOnMouseDragReleased(e ->{
            snapSlider(sensitivitySlider.getValue());
        });
    }

    private void setSliders(){

        sensitivitySlider.setMin(1);
        sensitivitySlider.setMax(7);
        sensitivitySlider.setShowTickLabels(true);
        sensitivitySlider.setBlockIncrement(1);
        sensitivitySlider.setMinorTickCount(0);
        sensitivitySlider.setMajorTickUnit(1);
        sensitivitySlider.setSnapToTicks(true);
        sensitivitySlider.setValue(7);

        ageRangeSlider.setMin(1);
        ageRangeSlider.setMax(7);
        ageRangeSlider.setBlockIncrement(1);
        ageRangeSlider.setMinorTickCount(0);
        ageRangeSlider.setMajorTickUnit(1);
        ageRangeSlider.setSnapToTicks(true);
        ageRangeSlider.setValue(7);
    }

    private void snapSlider(double sliderValue){

        if(sliderValue < 1.5){
            sensitivitySlider.setValue(1);
        }
        else if(sliderValue > 1.5 && sliderValue < 2.5){
            sensitivitySlider.setValue(2);
        }
        else if(sliderValue > 2.5 && sliderValue < 3.5){
            sensitivitySlider.setValue(3);
        }
        else if(sliderValue > 3.5 && sliderValue < 4.5){
            sensitivitySlider.setValue(4);
        }
        else if(sliderValue > 4.5 && sliderValue < 5.5){
            sensitivitySlider.setValue(5);
        }
        else if(sliderValue > 5.5 && sliderValue < 6.5){
            sensitivitySlider.setValue(6);
        }
        else if(sliderValue > 6.5){
            sensitivitySlider.setValue(7);
        }
    }

}
