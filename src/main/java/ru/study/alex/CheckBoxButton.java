package ru.study.alex;

import javax.swing.*;
import java.util.ArrayList;

public class CheckBoxButton extends JCheckBox implements iCheckBox {

    String name;

    CheckBoxButton(String name){
        this.name = name;
        this.setText(name);
    }

    @Override
    public ArrayList<String> checkParam(ArrayList<String> paramsList) {
        if (this.isSelected()) {
            paramsList.add(name);
        }
        return paramsList;
    }
}

