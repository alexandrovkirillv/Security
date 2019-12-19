package ru.study.alex;

import org.jfree.data.time.Minute;
import org.jfree.ui.RefineryUtilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;

public class URLReader {

    private static TreeMap<Minute, Float> result = new TreeMap<>();
    private String parameter;

     static TreeMap<Minute, Float> getResult() {
        return result;
    }

    public URLReader(String parameter) {
        this.parameter = parameter;
    }

    public void start() throws Exception {

        readURL("http://mon.imces.ru/tl/48/idd_amkf/*/tm/2019-12-05T15:05:00+07/2019-12-06T15:05:00+07?ids_group=10&sn=12430AMK-03", parameter);

        final String title = "Time Series Management";
        final TimeSeries_AWT demo = new TimeSeries_AWT(title, parameter);
        demo.pack();
        RefineryUtilities.positionFrameRandomly(demo);
        demo.setVisible(true);
    }

    private void readURL(String URL, String parameter) throws IOException {
        URL oracle = new URL(URL);
        BufferedReader in = new BufferedReader(new InputStreamReader(oracle.openStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            splitter(inputLine, parameter);
        }
        in.close();
    }

    private void splitter(String inLine, String type) {

        int numberOfType = 0;

        switch (type) {
            case ("temp"):
                numberOfType = 4;
                break;
            case ("horWind"):
                numberOfType = 5;
                break;
            case ("mounWind"):
                numberOfType = 6;
                break;
        }

        ArrayList<String> tmpString = new ArrayList<>(Arrays.asList(inLine.split(";")));
        if (tmpString.size() > 1) {
            int year = Integer.parseInt(tmpString.get(0).substring(0,4));
            int month =  Integer.parseInt(tmpString.get(0).substring(5,7));
            int day =  Integer.parseInt(tmpString.get(0).substring(8,10));
            int hour =  Integer.parseInt(tmpString.get(0).substring(11,13));
            int minute =  Integer.parseInt(tmpString.get(0).substring(14,16));

            Minute time = new Minute(minute,hour,day,month,year);

            result.put(time, Float.parseFloat(tmpString.get(numberOfType)));
        }
    }
}


