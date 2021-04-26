package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
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

    @FXML
    Text txtMessage = new Text();

    @FXML
    ImageView imgIcon;


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

    private double getBMIValue()
    {
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

        return bmi;
    }

    public void onClickCalculate(MouseEvent mouseEvent) {
        double bmi = getBMIValue();
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

    public void onClickDiet(MouseEvent mouseEvent) {
        double bmi = getBMIValue();

        String iconFile = "01.jpg";
        if(bmi < 18.5) {
            txtMessage.setText("Make sure to increase calorie intake.");
            iconFile = "01.jpg";
        }
        else if(bmi < 24.9) {
            txtMessage.setText("Keep doing what you're doing!");
            iconFile = "02.jpg";
        }
        else if(bmi < 29.9) {
            txtMessage.setText("Make sure to decrease your calorie intake! Avoid sugars as much as possible.");
            iconFile = "03.jpg";
        }
        else if (bmi>30) {
            txtMessage.setText("Decrease calorie intake and avoid all sugars.");
            iconFile = "04.jpg";
        }

        Image image = new Image(getClass().getResource(iconFile).toExternalForm());
        imgIcon.setImage(image);
    }

    public void onClickExcercise(MouseEvent mouseEvent) {
        double bmi = getBMIValue();

        String iconFile = "11.jpg";

        if (bmi < 18.5) {
            txtMessage.setText("Lift weights: Squats/deadlifts, bicep curls, triceps curls, bench press] Cardio: 30 min run.");
            iconFile = "11.jpg";
        }
        else if(bmi < 24.9) {
            txtMessage.setText("Lift weights: Squats/deadlifts, bicep curls, triceps curls, bench press] Cardio: 30 min run.");
            iconFile = "12.jpg";
        }
        else if(bmi < 29.9) {
            txtMessage.setText("Cardio: 30-60 min elliptical machine, 30-60 min walk, light running.");
            iconFile = "13.jpg";
        }
        else if (bmi>30) {
            txtMessage.setText("30-60 min in the pool, 30-60 min stationary bike.");
            iconFile = "14.jpg";
        }

        Image image = new Image(getClass().getResource(iconFile).toExternalForm());
        imgIcon.setImage(image);
    }
}
