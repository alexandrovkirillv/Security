package ru.study.alex;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.Minute;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class TimeSeries_AWT extends JPanel {

    public TimeSeries_AWT(final String title, ArrayList<String> parameters, URLReader urlReader) throws Exception {
        // super(title);
        XYDataset dataset = createDataset(parameters, urlReader);
        String temp = "Climate monitoring system";
        JFreeChart chart = createChart(dataset, temp);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 370));
        chartPanel.setMouseZoomable(true, false);
        add(chartPanel);
    }

    private XYDataset createDataset(ArrayList<String> parameters, URLReader urlReader) {
        ArrayList<TimeSeries> listOfSeries = new ArrayList<>();

        for (String parameter : parameters) {
            TreeMap<Minute, Float> treeMap = urlReader.getResult(parameter);
            TimeSeries series = new TimeSeries(parameter);
            for (Map.Entry e : treeMap.entrySet()) {
                series.add((Minute) e.getKey(), (Float) e.getValue());
            }
            listOfSeries.add(series);
        }
        TimeSeriesCollection timeSeriesCollection = new TimeSeriesCollection();

        for (TimeSeries series : listOfSeries) {
            timeSeriesCollection.addSeries(series);
        }
        return timeSeriesCollection;
    }

    private JFreeChart createChart(final XYDataset dataset, String parameter) {
        return ChartFactory.createTimeSeriesChart(
                parameter,
                "Time",
                "Value",
                dataset,
                false,
                false,
                false);
    }
}