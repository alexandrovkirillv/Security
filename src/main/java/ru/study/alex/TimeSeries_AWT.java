package ru.study.alex;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.Minute;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;

import java.util.Map;
import java.util.TreeMap;

public class TimeSeries_AWT extends ApplicationFrame {

    public TimeSeries_AWT(final String title, String parameter) {
        super(title);
        final XYDataset dataset = createDataset();
        final JFreeChart chart = createChart(dataset, parameter);
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 370));
        chartPanel.setMouseZoomable(true, false);
        setContentPane(chartPanel);
    }

    private XYDataset createDataset() {
        final TimeSeries series = new TimeSeries("Random Data");

        TreeMap<Minute, Float> treeMap = URLReader.getResult();

        for (Map.Entry e : treeMap.entrySet()) {
            series.add((Minute)e.getKey(), (Float)e.getValue());
        }

//        Second current = new Second(11,11, 11, 11, 11, 2011);
//        double value = 100.0;
//
//        series.add(current, 100.1);
//        series.add(current.next(), 100.2);
//        series.add(current.next().next(), 100.3);
//        series.add(current.next().next().next(), 100.4);
//        series.add(current.next().next().next().next(), 100.5);


        return new TimeSeriesCollection(series);
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