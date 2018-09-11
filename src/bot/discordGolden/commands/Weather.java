package bot.discordGolden.commands;

import com.github.prominence.openweathermap.api.OpenWeatherMapManager;
import com.github.prominence.openweathermap.api.WeatherRequester;
import com.github.prominence.openweathermap.api.constants.Accuracy;
import com.github.prominence.openweathermap.api.constants.Language;
import com.github.prominence.openweathermap.api.constants.Unit;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.awt.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


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
                clima.setAuthor(weatherResponse.getCityName() + ", " + weatherResponse.getCountry(), "https://emojipedia-us.s3.dualstack.us-west-1.amazonaws.com/thumbs/120/emojione/151/world-map_1f5fa.png", "https://emojipedia-us.s3.dualstack.us-west-1.amazonaws.com/thumbs/120/emojione/151/world-map_1f5fa.png");
                clima.setDescription("**Unidade:** " + weatherResponse.getTemperatureUnit() + "\n");
                clima.appendDescription("**Latitude:** " + weatherResponse.getCoordinates().getLatitude() + "\n");
                clima.appendDescription("**Longitude:** " + weatherResponse.getCoordinates().getLongitude() + "\n");
                clima.appendDescription("\n");
                clima.appendDescription("**Temperatura:** " + weatherResponse.getTemperature() + "\n");
                clima.appendDescription("**Temp Max:** " + weatherResponse.getWeatherInfo().getMaximumTemperature() + "\n");
                clima.appendDescription("**Temp Min:** " + weatherResponse.getWeatherInfo().getMinimumTemperature() + "\n");
                if (weatherResponse.getRain() == null) {
                    clima.appendDescription("**Chuva:** " + "Não está chovendo." + "\n");
                    clima.appendDescription("**Neve:** " + "Não está nevando." + "\n");
                } else {
                    clima.appendDescription("**Chuva:** " + weatherResponse.getRain() + "\n");
                    clima.appendDescription("**Volume de chuva nas ultimas 3 horas:** " + weatherResponse.getRain().getRainVolumeLast3Hrs() + "\n");
                    clima.appendDescription("**Neve:** " + weatherResponse.getSnow() + "\n");
                    clima.appendDescription("**Volume de neve nas ultimas 3 horas:** " + weatherResponse.getSnow().getSnowVolumeLast3Hrs() + "\n");
                }
                double vento = weatherResponse.getWind().getSpeed() * 3.6;
                NumberFormat formatter = new DecimalFormat("#0.00");
                clima.appendDescription("**Vento:** " + formatter.format(vento) + " km/h" + "\n");
                clima.appendDescription("\n");
                clima.appendDescription("**Umidade:** " + weatherResponse.getHumidityPercentage() + " %" + "\n");
                clima.appendDescription("**Pressão atmosférica** " + weatherResponse.getWeatherInfo().getPressure() + " mb" + "\n");
                clima.appendDescription("**Calculado em: **" + weatherResponse.getDataCalculationDate().toLocaleString() + "\n");
                clima.setFooter("Pedido por: " + event.getAuthor().getName() + " • " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("EEEE" + " hh:mm a ")), event.getAuthor().getAvatarUrl());
                event.getChannel().sendMessage(clima.build()).queue();
            } catch (NullPointerException | com.github.prominence.openweathermap.api.exception.DataNotFoundException e) {
                EmbedBuilder descriptionError = new EmbedBuilder();
                descriptionError.setColor(Color.RED);
                descriptionError.setDescription("Coloque nome de uma cidade valida.");
                descriptionError.setFooter("Pedido por: " + event.getAuthor().getName() + " • " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("EEEE" + " hh:mm a ")), event.getAuthor().getAvatarUrl());
                event.getChannel().sendMessage(descriptionError.build()).queue();
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
