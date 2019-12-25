package ru.study.alex;

import org.jfree.data.time.Minute;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;

public class URLReader {

    private static final ArrayList<String> tmpResult = new ArrayList<>();
    private static final String URL = "http://mon.imces.ru/tl/48/idd_amkf/*/tm/2019-12-05T15:05:00+07/2019-12-06T15:05:00+07?ids_group=10&sn=12430AMK-03";

    URLReader() throws IOException {
        readURL();
    }

    private void readURL() throws IOException {
        URL urlClass = new URL(URL);

        BufferedReader in = new BufferedReader(new InputStreamReader(urlClass.openStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            tmpResult.add(inputLine);
        }
        in.close();
    }

    TreeMap<Minute, Float> getResult(String parameter) {

        TreeMap<Minute, Float> result = new TreeMap<>();
        int numberOfType = 0;

        switch (parameter) {
            case ("temp"):
                numberOfType = 4;
                break;
            case ("horWind"):
                numberOfType = 5;
                break;
            case ("mounWind"):
                numberOfType = 6;
                break;
            case ("minSpeedMomentHW"):
                numberOfType = 7;
                break;
            case ("maxSpeedMomentHW"):
                numberOfType = 8;
                break;
            case ("avSpeedVW"):
                numberOfType = 9;
                break;
            case ("atmPressure"):
                numberOfType = 10;
                break;
            case ("relHumidity"):
                numberOfType = 11;
                break;
            case ("dpTemp"):
                numberOfType = 12;
                break;
            case ("waterVapourPres"):
                numberOfType = 13;
                break;
            case ("absHumidity"):
                numberOfType = 14;
                break;
            case ("airDensity"):
                numberOfType = 15;
                break;
            case ("speedOfSound"):
                numberOfType = 16;
                break;
        }

        for (String tmpResultString : tmpResult) {
            ArrayList<String> tmpString = new ArrayList<>(Arrays.asList(tmpResultString.split(";")));
            if (tmpString.size() > 1) {
                int year = Integer.parseInt(tmpString.get(0).substring(0, 4));
                int month = Integer.parseInt(tmpString.get(0).substring(5, 7));
                int day = Integer.parseInt(tmpString.get(0).substring(8, 10));
                int hour = Integer.parseInt(tmpString.get(0).substring(11, 13));
                int minute = Integer.parseInt(tmpString.get(0).substring(14, 16));

                Minute time = new Minute(minute, hour, day, month, year);

                result.put(time, Float.parseFloat(tmpString.get(numberOfType)));
            }
        }
        return result;
    }
}


