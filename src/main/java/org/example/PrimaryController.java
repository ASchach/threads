package org.example;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class PrimaryController {

    public TextArea output;
    public TextField max_value;

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    public void startCounting(MouseEvent mouseEvent) {
        output.setText("");
        Counter counter = new Counter(Integer.parseInt(max_value.getText()));
        counter.setOutput(output);
        counter.startIncrement();
    }
}
