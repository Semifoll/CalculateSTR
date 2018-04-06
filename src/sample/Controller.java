package sample;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.ArrayList;

public class Controller {

    static private ArrayList<Integer> ARRAY_OF_BRACKET;
    static private String PREVIOUS;

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

    static{
        ARRAY_OF_BRACKET = new ArrayList<>();
        PREVIOUS = "";
    }

    @FXML
    void onActionButtonAddNumberToTextField(ActionEvent event) {
        if(PREVIOUS.equals(")")){
            //ошибка перед числом закрывающая скобка
            message("Number add with no expression before bracket!");
        }else{
            String c = eTextField.getText();
            Button d = (Button)event.getSource();
            String e = d.getId();
            eTextField.setText(c+e.charAt(e.length()-1));
            PREVIOUS = e;
        }
    }

    @FXML
    void onActionButtonBracketFinish(ActionEvent event) {

        if(ARRAY_OF_BRACKET.size()!=0 ){
            if((isNumber(PREVIOUS)||PREVIOUS.equals(")"))){
                String c = eTextField.getText();
                String e = ")";
                eTextField.setText(c+e);
                ARRAY_OF_BRACKET.remove(ARRAY_OF_BRACKET.size()-1);
                PREVIOUS = e;
                return;
            }
        }
        //ошибка закрывающая скобка без открывающей
        message("BRACKET does not opened!");
    }

    @FXML
    void onActionButtonBracketStart(ActionEvent event) {
        //если перед скобкой число
        if(!(PREVIOUS.equals(")")|| PREVIOUS.equals(".")||isNumber(PREVIOUS))){
            String c = eTextField.getText();
            String e = "(";
            eTextField.setText(c+e);
            ARRAY_OF_BRACKET.add(c.length());
            PREVIOUS = e;
            return;
        }
        message("Invalid character before previous symbol.");
    }

    @FXML
    void onActionButtonComma(ActionEvent event) {
        if(isNumber(PREVIOUS)){
            //если перед запятой не число
            String c = eTextField.getText();
            String e = ".";
            eTextField.setText(c+e);
            PREVIOUS = e;
        }else{
            message("Invalid character previous symbol not number.");
        }
    }

    @FXML
    void onActionButtonDivide(ActionEvent event) {
        //если перед делением не число
        if(isNumber(PREVIOUS)|| PREVIOUS.equals(")")){
            String c = eTextField.getText();
            String e = "/";
            eTextField.setText(c+e);
            PREVIOUS = e;
        }else{
            message("Invalid character. Before the divide must be number or ) .");
        }

    }

    @FXML
    void onActionButtonMinus(ActionEvent event) {
        //если перед минусом не умножение или деление
        if(PREVIOUS.equals(")")||isNumber(PREVIOUS)){
            String c = eTextField.getText();
            String e = "-";
            eTextField.setText(c+e);
            PREVIOUS =e;
        }else{
            message("Invalid character. Before the minus must be number or ) .");
        }

    }

    @FXML
    void onActionButtonMultiply(ActionEvent event) {
        //если перед умножением минус/плюс или открытая скобка
        if(PREVIOUS.equals(")")||isNumber(PREVIOUS)){
            String c = eTextField.getText();
            String e = "*";
            eTextField.setText(c+e);
            PREVIOUS = e;
        }else{
            message("Invalid character. Before multiply must be number or ).");
        }

    }

    @FXML
    void onActionButtonPlus(ActionEvent event) {
        //если перед плюсом умножение/деление
        if(PREVIOUS.equals(")")||isNumber(PREVIOUS)){
            String c = eTextField.getText();
            String e = "+";
            eTextField.setText(c+e);
            PREVIOUS = e;
        }else{
            message("Invalid character. Before plus must be number or ).");
        }
    }

    @FXML
    void onActionButtonExpression(ActionEvent event) {
        //если есть еще открывающие скобки.
        if(ARRAY_OF_BRACKET.size()!=0){
            String dontClose = "";
            for(int i = 0; i!= ARRAY_OF_BRACKET.size(); i++){
                dontClose+=" "+ (i+1) + " on position: " + ARRAY_OF_BRACKET.get(i) + ", ";
            }
            message("BRACKET does not closed! Last barket is " + dontClose);
        }else{
            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine engine = manager.getEngineByName("js");
            try {
                String c = eTextField.getText();
                Object result = engine.eval(c);
                eTextArea.setText(eTextArea.getText() + "\n" + c + " = " + result.toString());
                eTextField.setText(result.toString());
            } catch (ScriptException e) {
                String d = e.getMessage();
                eTextArea.setText("Error can't find close or open barket " + d.charAt(d.length()-1));
            }
        }

    }

    @FXML
    void onActionButtonCancel(ActionEvent event) {
        eTextField.clear();
        PREVIOUS = "";
        ARRAY_OF_BRACKET.clear();
    }

    @FXML
    void onActionButtonDelete(ActionEvent event) {
        String prevStr = eTextField.getText();
        if(prevStr!=null || !(prevStr.trim().isEmpty())){
            if(')' == prevStr.charAt(prevStr.length()-1)){
                ARRAY_OF_BRACKET.add(0);
            } else if('(' == prevStr.charAt(prevStr.length()-1)){
                ARRAY_OF_BRACKET.remove(ARRAY_OF_BRACKET.size()-1);
            }
            if(prevStr.length()>=2){
                eTextField.setText(prevStr.substring(0,prevStr.length()-1));
                PREVIOUS = prevStr.charAt(prevStr.length()-2)+"";
            }else{
                eTextField.clear();
                PREVIOUS="";
            }

        }
    }

    boolean isNumber(String prev){
        if(PREVIOUS.equals("")||
                PREVIOUS.equals("-")||
                PREVIOUS.equals("+")||
                PREVIOUS.equals("/")||
                PREVIOUS.equals("*")||
                PREVIOUS.equals(".")||
                PREVIOUS.equals("(")||
                PREVIOUS.equals(")")){

            return false;
        }else{
            return true;
        }
    }
    void message(String strMessage){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Error Expression");
        alert.setHeaderText("Error!");
        alert.setContentText(strMessage);
        alert.showAndWait();
    }
}