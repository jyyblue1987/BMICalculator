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

import javax.xml.soap.Text;
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
    }

    public void onClickCalculate(MouseEvent mouseEvent) {
    }
}
