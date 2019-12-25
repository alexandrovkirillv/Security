package ru.study.alex;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class View {

    private JPanel boxesPanel = new JPanel();
    private JPanel boxesPanel2 = new JPanel();
    private final String title = "Metric";
    private TimeSeries_AWT demo;
    private JPanel prePanel;
    private GridBagConstraints preCenter = new GridBagConstraints();
    private ArrayList<CheckBoxButton> checkList= new ArrayList<>();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager
                            .getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                View mainGUI = new View();
                try {
                    mainGUI.display();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    private void display() throws Exception {
        String appName = "Chart";
        JFrame preFrame = new JFrame(appName);
        preFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        prePanel = new JPanel(new GridBagLayout());

        preCenter.insets = new Insets(4, 0, 4, 0);
        preCenter.fill = GridBagConstraints.NONE;
        preCenter.gridwidth = GridBagConstraints.REMAINDER;

        preFrame.add(prePanel);
        preFrame.setSize(600, 600);

        createCheckBoxButtons();
        prePanel.add(boxesPanel, preCenter);
        prePanel.add(boxesPanel2, preCenter);
        boxesPanel.setVisible(true);
        boxesPanel2.setVisible(true);
        preFrame.setVisible(true);

        demo = new TimeSeries_AWT(title, new ArrayList<>());
        demo.setVisible(true);
        prePanel.add(demo, preCenter);

    }


    private void  createCheckBoxButtons() {

        CheckBoxButton tempBox = new CheckBoxButton("temp");
        CheckBoxButton horWindBox = new CheckBoxButton("horWind");
        CheckBoxButton mounWindBox = new CheckBoxButton("mounWind");
        CheckBoxButton minSpeedMomentHW = new CheckBoxButton("minSpeedMomentHW");
        CheckBoxButton maxSpeedMomentHW = new CheckBoxButton("maxSpeedMomentHW");
        CheckBoxButton avSpeedVW = new CheckBoxButton("avSpeedVW");
        CheckBoxButton atmPressure = new CheckBoxButton("atmPressure");
        CheckBoxButton relHumidity = new CheckBoxButton("relHumidity");
        CheckBoxButton dpTemp = new CheckBoxButton("dpTemp");
        CheckBoxButton waterVapourPres = new CheckBoxButton("waterVapourPres");
        CheckBoxButton absHumidity = new CheckBoxButton("absHumidity");
        CheckBoxButton airDensity = new CheckBoxButton("airDensity");
        CheckBoxButton speedOfSound = new CheckBoxButton("speedOfSound");

        checkList.add(tempBox);
        checkList.add(horWindBox);
        checkList.add(mounWindBox);
        checkList.add(minSpeedMomentHW);
        checkList.add(maxSpeedMomentHW);
        checkList.add(avSpeedVW);
        checkList.add(atmPressure);
        checkList.add(relHumidity);
        checkList.add(dpTemp);
        checkList.add(waterVapourPres);
        checkList.add(absHumidity);
        checkList.add(airDensity);
        checkList.add(speedOfSound);

        for (int i = 0; i < 7; i++) {
            updateListener(checkList.get(i), boxesPanel);
        }
        for (int i = 7; i < 13; i++) {
            updateListener(checkList.get(i), boxesPanel2);

        }
    }

    private void updateListener(CheckBoxButton checkBoxButton, JPanel boxesPanel) {
        checkBoxButton.addItemListener(e -> {
            try {
                updateInfo();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        boxesPanel.add(checkBoxButton);
    }

    private void updateInfo() throws Exception {
        ArrayList<String> paramsList = new ArrayList<>();

        paramsList = getParams(paramsList);

        prePanel.setVisible(false);
        prePanel.remove(demo);
        demo = null;
        demo = new TimeSeries_AWT(title, paramsList);
        prePanel.add(demo);
        prePanel.setVisible(true);
    }

    private ArrayList<String> getParams(ArrayList<String> paramsList) {

        for(CheckBoxButton checkBoxButton : checkList){
            paramsList= checkBoxButton.checkParam(paramsList);
        }

        return paramsList;
    }
}
