package sample;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private Button eButtonPlus;

    @FXML
    private Button eButtonMinus;

    @FXML
    private TextField eTextField;

    @FXML
    private Button eButton3;

    @FXML
    private Button eButton6;

    @FXML
    private Button eButton9;

    @FXML
    private Button eButtonMultiply;

    @FXML
    private Button eButtonDivide;

    @FXML
    private Button eButtonEqually;

    @FXML
    private Button eButtonOpen;

    @FXML
    private Button eButtonClose;

    @FXML
    private Button eButton1;

    @FXML
    private Button eButton2;

    @FXML
    private Button eButton4;

    @FXML
    private Button eButton5;

    @FXML
    private Button eButton7;

    @FXML
    private Button eButton8;

    @FXML
    private Button eButton0;

    @FXML
    private Button eButtonComma;

    @FXML
    private Button eButtonCancel;

    @FXML
    private Button eButtonDelete;

    @FXML
    private TextArea eTextArea;

    @FXML
    void onActionButtonAddNumberToTextField(ActionEvent event) {

        String c = eTextField.getText();
        Button d = (Button)event.getSource();
        String e = d.getId();
        eTextField.setText(c+e.charAt(e.length()-1));
    }

    @FXML
    void onActionButtonBracketFinish(ActionEvent event) {
        String c = eTextField.getText();
        String e = ")";
        eTextField.setText(c+e);
    }

    @FXML
    void onActionButtonBracketStart(ActionEvent event) {
        String c = eTextField.getText();
        String e = "(";
        eTextField.setText(c+e);
    }

    @FXML
    void onActionButtonComma(ActionEvent event) {
        String c = eTextField.getText();
        String e = ",";
        eTextField.setText(c+e);
    }

    @FXML
    void onActionButtonDivide(ActionEvent event) {
        String c = eTextField.getText();
        String e = "/";
        eTextField.setText(c+e);
    }



    @FXML
    void onActionButtonMinus(ActionEvent event) {
        String c = eTextField.getText();
        String e = "-";
        eTextField.setText(c+e);
    }

    @FXML
    void onActionButtonMultiply(ActionEvent event) {
        String c = eTextField.getText();
        String e = "*";
        eTextField.setText(c+e);
    }

    @FXML
    void onActionButtonPlus(ActionEvent event) {
        String c = eTextField.getText();
        String e = "+";
        eTextField.setText(c+e);
    }

    @FXML
    void onActionButtonExpression(ActionEvent event) {

    }
}