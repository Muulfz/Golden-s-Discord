package bot.discordGolden.commands;

import com.github.prominence.openweathermap.api.OpenWeatherMapManager;
import com.github.prominence.openweathermap.api.WeatherRequester;
import com.github.prominence.openweathermap.api.constants.Accuracy;
import com.github.prominence.openweathermap.api.constants.Language;
import com.github.prominence.openweathermap.api.constants.Unit;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.awt.*;


public class Weather implements Command {
    private static final String Cities = "city.list.json";

    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) throws Exception {
        OpenWeatherMapManager openWeatherManager = new OpenWeatherMapManager("c331c81f333e7a6e00a2ef6fcdd0d706");
        WeatherRequester weatherRequester = openWeatherManager.getWeatherRequester();

        String location = event.getMessage().getContentRaw().replace("!clima ", "").toUpperCase();


        if (Cities.toUpperCase().contains(location.toUpperCase())) {
            event.getChannel().sendMessage("Coloque nome de uma cidade.").queue();
        } else {
            try {
                com.github.prominence.openweathermap.api.model.response.Weather weatherResponse = weatherRequester
                        .setLanguage(Language.ENGLISH)
                        .setUnitSystem(Unit.METRIC_SYSTEM)
                        .setAccuracy(Accuracy.ACCURATE)
                        .getByCityName(location);

                EmbedBuilder clima = new EmbedBuilder();
                clima.setColor(Color.CYAN);
                clima.setAuthor(weatherResponse.getCityName() + ", " + weatherResponse.getCountry(), event.getAuthor().getAvatarUrl(), event.getAuthor().getAvatarUrl());
                clima.setDescription("**Unidade:** " + weatherResponse.getTemperatureUnit() + "\n");
                clima.appendDescription("**Latitude:** " + weatherResponse.getCoordinates().getLatitude() + "\n");
                clima.appendDescription("**Longitude:** " + weatherResponse.getCoordinates().getLongitude() + "\n");
                clima.appendDescription("\n");
                clima.appendDescription("**Temp:** " + weatherResponse.getTemperature() + "\n");
                if (weatherResponse.getRain() == null) {
                    clima.appendDescription("**Chuva:** " + "Não está chovendo." + "\n");
                } else {
                    clima.appendDescription("**Chuva:** " + weatherResponse.getRain() + "\n");
                }
                clima.appendDescription("**Vento:** " + weatherResponse.getWind() + "\n");
                clima.appendDescription("\n");
                clima.appendDescription("**Humidade:** " + weatherResponse.getHumidityPercentage() + " %" + "\n");
                clima.appendDescription("****" + weatherResponse.getDataCalculationDate() + "\n");
                event.getChannel().sendMessage(clima.build()).queue();
            } catch (NullPointerException | com.github.prominence.openweathermap.api.exception.DataNotFoundException e) {
                event.getChannel().sendMessage("Coloque nome de uma cidade valida.").queue();
            }

        }




    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }

    @Override
    public String help() {
        return null;
    }
}
