package com.example.gonca.sunshine.app;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.media.RatingCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.*;
import java.util.Arrays;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);



        String[ ]forecastArray = new String[ ]{
                "Today  - Sunny - 88/63",
                "Tomorrow - Foggy - 70/40",
                "Weds - Cloudy - 72/63",
                "Thurs - Asteroids - 75/65",
                "Fri - Heavy Rain - 55/56",
                "Sat - HELP TRAPPED IN WEATHERSTATION - 50/51",
                "Sun - Sunny - 80/65"
        };
        List<String> weekForecast = new ArrayList<String>(Arrays.asList(forecastArray));

        ArrayAdapter<String> mForecastArray = new ArrayAdapter<String>( getActivity(),R.layout.list_item_forecast,R.id.list_item_forecast_textview, weekForecast);

        ListView list = (ListView) rootView.findViewById(R.id.listview_forecast);
        list.setAdapter(mForecastArray);


        /*

        // Outra maneira de popular a lista

        String[] forecastArray = new String[]{
                "Today  - Sunny - 88/63",
                "Tomorrow - Foggy - 70/40",
                "Weds - Cloudy - 72/63",
                "Thurs - Asteroids - 75/65",
                "Fri - Heavy Rain - 55/56",
                "Sat - HELP TRAPPED IN WEATHERSTATION - 50/51",
                "Sun - Sunny - 80/65"
        };

        final ArrayList<String> listArray = new ArrayList<String>();

        for (int i = 0; i < forecastArray.length; ++i) {
            listArray.add(forecastArray[i]);
        }

        ArrayAdapter<String> mForecastArray = new ArrayAdapter<String>(getActivity(), R.layout.list_item_forecast, R.id.list_item_forecast_textview, listArray);

        ListView list = (ListView) rootView.findViewById(R.id.listview_forecast);

        list.setAdapter(mForecastArray);
*/

        return rootView;

    }

}
