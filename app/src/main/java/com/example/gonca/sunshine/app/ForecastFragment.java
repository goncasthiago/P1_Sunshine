package com.example.gonca.sunshine.app;

import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.media.RatingCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.Arrays;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class ForecastFragment extends Fragment {

    public ForecastFragment() {
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
public abstract class FetchWeatherTask extends AsyncTask<Void, Void, Void> {

    private final String LOG_TAG = FetchWeatherTask.class.getSimpleName();
    private final String OPEN_WEATHER_MAP_API_KEY = "de5e5924279b9cfcf3279121ad002d17";
    @Override
        protected Void doInBackground(Void... params) {
            // inicio do script de busca http

            // These two need to be declared outside the try/catch
            // so that they can be closed in the finally block.
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            // Will contain the raw JSON response as a string.
            String forecastJsonStr = null;

            try {
                // Construct the URL for the OpenWeatherMap query
                // Possible parameters are avaiable at OWM's forecast API page, at
                // http://openweathermap.org/API#forecast
                String baseUrl = "http://api.openweathermap.org/data/2.5/forecast/daily?q=94043&mode=json&units=metric&cnt=7";
                String apiKey = "&APPID=" + OPEN_WEATHER_MAP_API_KEY;
                URL url = new URL(baseUrl.concat(apiKey));
                // Create the request to OpenWeatherMap, and open the connection
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                // Read the input stream into a String
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    // Nothing to do.
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                    // But it does make debugging a *lot* easier if you print out the completed
                    // buffer for debugging.
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    // Stream was empty.  No point in parsing.
                    return null;
                }
                forecastJsonStr = buffer.toString();
            } catch (IOException e) {
                Log.e("ForecastFragment", "Error ", e);
                // If the code didn't successfully get the weather data, there's no point in attemping
                // to parse it.
                return null;
            } finally{
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e("ForecastFragment", "Error closing stream", e);
                    }
                }
            }

            return null ;
        }


    }
}
