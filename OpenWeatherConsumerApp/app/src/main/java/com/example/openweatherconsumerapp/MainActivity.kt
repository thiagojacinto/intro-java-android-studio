package com.example.openweatherconsumerapp

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import org.json.JSONObject
import org.w3c.dom.Text
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    var city: String? = "Maceio,br"
    val API_KEY: String = "6032890f8cf91f0b91d5a21c8c0ab0b2"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Runs the created method
        weatherTask().execute()

    }

    inner class weatherTask() : AsyncTask<String, Void, String>() {

        override fun onPreExecute() {
            super.onPreExecute()

            /*
            Shows the ProgressBar:

            Making the MAIN & ERROR design fade while LOADER GETS VISIBLE
             */
            findViewById<ProgressBar>(R.id.loader).visibility = View.VISIBLE
            findViewById<RelativeLayout>(R.id.main_container).visibility = View.GONE
            findViewById<TextView>(R.id.error_text).visibility = View.GONE

        }

        override fun doInBackground(vararg params: String?): String? {

            var response : String?

            try{
                response = URL("https://api.openweathermap.org/data/2.5/weather?q=$city&units=metric&lang=pt_br&appid=$API_KEY").readText(
                        Charsets.UTF_8
                    )
            }catch (error : Exception){
                response = null
            }
            return response
        }

        override fun onPostExecute(result: String?) {

            super.onPostExecute(result)

            try {
                /* Extracting JSON returns from the API */
                val responseObjFromJSON = JSONObject(result)
                val main = responseObjFromJSON.getJSONObject("main")
                val sys = responseObjFromJSON.getJSONObject("sys")
                val wind = responseObjFromJSON.getJSONObject("wind")
                val weather = responseObjFromJSON.getJSONArray("weather").getJSONObject(0)

                val updatedAt: Long = responseObjFromJSON.getLong("dt")
                val updatedAtText =
                    "Atualizado em: " + SimpleDateFormat("dd/MM/yyyy hh:mm a", Locale.ENGLISH).format(
                        Date(updatedAt * 1000)
                    )
                val temp = main.getString("temp") + "°C"
                val tempMin = "Min Temp: " + main.getString("temp_min") + "°C"
                val tempMax = "Max Temp: " + main.getString("temp_max") + "°C"
                val pressure = main.getString("pressure") + " mmHg"
                val humidity = main.getString("humidity") + " %"

                val sunrise: Long = sys.getLong("sunrise")
                val sunset: Long = sys.getLong("sunset")
                val windSpeed = wind.getString("speed")
                val windDirection = wind.getString("deg")
                    val windInfo : String = "$windSpeed @ $windDirection°"
                val weatherDescription = weather.getString("description")

                val address = responseObjFromJSON.getString("name") + ", " + sys.getString("country")

                /* Populating extracted data into our views */
                findViewById<TextView>(R.id.address_text_view).text = address
                findViewById<TextView>(R.id.updated_at).text = updatedAtText
                findViewById<TextView>(R.id.status).text = weatherDescription.capitalize()
                findViewById<TextView>(R.id.temperature).text = temp
                findViewById<TextView>(R.id.temp_min).text = tempMin
                findViewById<TextView>(R.id.temp_max).text = tempMax
                findViewById<TextView>(R.id.sunrise_text_view).text =
                    SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(Date(sunrise * 1000))
                findViewById<TextView>(R.id.sunset).text =
                    SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(Date(sunset * 1000))
                // findViewById<TextView>(R.id.wind).text = windSpeed
                findViewById<TextView>(R.id.wind).text = windInfo
                findViewById<TextView>(R.id.pressure).text = pressure
                findViewById<TextView>(R.id.humidity).text = humidity

                /* Views populated, Hiding the loader, Showing the main design */
                findViewById<ProgressBar>(R.id.loader).visibility = View.GONE
                findViewById<RelativeLayout>(R.id.main_container).visibility = View.VISIBLE

            } catch (e: Exception) {
                findViewById<ProgressBar>(R.id.loader).visibility = View.GONE
                findViewById<TextView>(R.id.error_text).visibility = View.VISIBLE
            }
        }
    }
}
