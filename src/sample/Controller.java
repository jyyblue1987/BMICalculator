package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

import java.awt.event.ActionEvent;

public class Controller {
    @FXML
    TextField txtWeight = new TextField();

    @FXML
    TextField txtHeight = new TextField();

    @FXML
    ChoiceBox<String> cmbWeight = new ChoiceBox<>();

    @FXML
    ChoiceBox<String> cmbHeight = new ChoiceBox<>();

    @FXML
    Text txtBMI = new Text();

    @FXML
    Text txtStatus = new Text();



    // when loading the page, this method is called automatically, so this initializes the event handler.
    public void initialize() {
        cmbWeight.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if(oldValue != newValue)
                {
                    String value = cmbWeight.getItems().get((Integer) newValue);
                    txtWeight.setPromptText("Weight(" + value + ")");
                }
            }
        });

        cmbHeight.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if(oldValue != newValue)
                {
                    String value = cmbHeight.getItems().get((Integer) newValue);
                    txtHeight.setPromptText("Height(" + value + ")");
                }
            }
        });

        //This is to ensure only numerical values can be inputted into the boxes (no letters or special characters).
        txtWeight.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
                    txtWeight.setText(oldValue);
                }
            }
        });

        //This is to ensure only numerical values can be inputted into the boxes (no letters or special characters).
        txtHeight.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
                    txtHeight.setText(oldValue);
                }
            }
        });
    }
    //this calculates the Body mass index.
    protected double calculateBMI(double mass, double height) {
        return mass / Math.pow(height/100.0, 2.0);
    }

    public void onClickCalculate(MouseEvent mouseEvent) {
        double weight = Double.parseDouble(txtWeight.getText());
        double height = Double.parseDouble(txtHeight.getText());
        if(cmbWeight.getValue().equals("pounds") )
        {
            // convert pounds to kilos;
            weight *= 0.45359237;
        }

        if(cmbHeight.getValue().equals("inches") )
        {
            height *= 2.54;
        }

        double bmi = calculateBMI(weight, height);
        txtBMI.setText(String.format("%.2f", bmi));

        String status = "";
        if(bmi < 18.5)
            status = "Underweight";
        else if(bmi < 24.9)
            status = "Healthy Weight";
        else if(bmi < 29.9)
            status = "Overweight";
        else if (bmi>30)
            status = "Obese";

        txtStatus.setText(status);

    }
}
