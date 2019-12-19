package ru.study.alex;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View {
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
                mainGUI.preDisplay();
            }
        });
    }


    private void preDisplay() {
        String appName = "Chart";
        JFrame preFrame = new JFrame(appName);
        preFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton temperature = new JButton("Temperature");
        JButton horWind = new JButton("horWind");
        JButton mounWind = new JButton("mounWind");
        temperature.addActionListener(new enterServerButtonListener("temp"));
        horWind.addActionListener(new enterServerButtonListener("horWind"));
        mounWind.addActionListener(new enterServerButtonListener("mounWind"));
        JPanel prePanel = new JPanel(new GridBagLayout());

        GridBagConstraints preCenter = new GridBagConstraints();
        preCenter.insets = new Insets(4, 0, 4, 0);
        preCenter.fill = GridBagConstraints.NONE;
        preCenter.gridwidth = GridBagConstraints.REMAINDER;

        prePanel.add(temperature, preCenter);
        prePanel.add(horWind, preCenter);
        prePanel.add(mounWind, preCenter);

        preFrame.add(prePanel);
        preFrame.setSize(600, 600);
        preFrame.setVisible(true);
    }

    static class enterServerButtonListener implements ActionListener {
        String type;
        public enterServerButtonListener(String type){
            this.type = type;
        }
        public void actionPerformed(ActionEvent event) {
            try {
                new URLReader(type).start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
