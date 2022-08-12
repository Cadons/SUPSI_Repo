import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.client.WriteApiBlocking;
import com.influxdb.client.domain.WritePrecision;
import com.influxdb.client.write.Point;
import org.iot.raspberry.grovepi.GrovePi;
import org.iot.raspberry.grovepi.pi4j.GrovePi4J;
import org.iot.raspberry.grovepi.pi4j.lcd.GroveRgbLcdPi4J;
import org.iot.raspberry.grovepi.sensors.analog.GroveRotarySensor;
import org.iot.raspberry.grovepi.sensors.data.GroveRotaryValue;
import org.iot.raspberry.grovepi.sensors.data.GroveTemperatureAndHumidityValue;
import org.iot.raspberry.grovepi.sensors.digital.GroveButton;
import org.iot.raspberry.grovepi.sensors.digital.GroveTemperatureAndHumiditySensor;
import org.iot.raspberry.grovepi.sensors.i2c.GroveRgbLcd;
import org.iot.raspberry.grovepi.sensors.listener.GroveButtonListener;
import org.iot.raspberry.grovepi.sensors.synch.SensorMonitor;
import org.w3c.dom.css.RGBColor;

import java.awt.*;
import java.io.IOException;
import java.time.Instant;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Funivia {
    private static boolean isOpen = true;
    private static boolean tempChange = false;

    public static void main(String[] args) throws Exception {
        Logger.getLogger("GrovePi").setLevel(Level.WARNING);
        Logger.getLogger("RaspberryPi").setLevel(Level.WARNING);
        GrovePi grovePi = new GrovePi4J();
        //Humidity D2
        //BTN D3
        //LCD
        //Rotary A1
        // You can generate an API token from the "API Tokens Tab" in the UI
        String token = "39ZM2P6fQkbkPs1FDUX8I-DmWMLz1K40rbRvRQMQfugXdeu4vWlP3h9WL3KhfUnSWnQDpPra77uzFflLU9NZgg==";
        String bucket = "funivia2";
        String org = "SUPSI";

        InfluxDBClient client = InfluxDBClientFactory.create("http://10.42.0.1:8086", token.toCharArray());
        GroveRotarySensor tornello = new GroveRotarySensor(grovePi, 1);
        GroveButton emergencyBtn = new GroveButton(grovePi, 3);
        GroveRgbLcd display = grovePi.getLCD();
        GroveTemperatureAndHumiditySensor humiditySensor = new GroveTemperatureAndHumiditySensor(grovePi, 2, GroveTemperatureAndHumiditySensor.Type.DHT11);

        SensorMonitor<GroveRotaryValue> monitorTornello = new SensorMonitor<>(tornello, 100);
        SensorMonitor<GroveTemperatureAndHumidityValue> monitorHumidity = new SensorMonitor<>(humiditySensor, 1000);
        SensorMonitor<Boolean> monitorEmergency = new SensorMonitor<>(emergencyBtn, 50);

        WriteApiBlocking writeApi = client.getWriteApiBlocking();
        GroveButtonListener buttonListener = new GroveButtonListener() {
            @Override
            public void onRelease() {

            }

            @Override
            public void onPress() {

            }

            @Override
            public void onClick() {
                System.out.println("button clicked");
                if (isOpen) {
                    isOpen = false;
                    tempChange = false;
                } else {
                    isOpen = true;
                    tempChange = true;
                }
            }
        };
        emergencyBtn.setButtonListener(buttonListener);
        monitorEmergency.start();
        monitorHumidity.start();
        monitorTornello.start();
        int peopleInside = 0;
        boolean newPerson=true;
        while (true) {
            display.setText("Clienti attuali: "+peopleInside);

            double ang = 0;
            if (!isOpen) {
                display.setRGB(255, 0, 0);

                Point point = Point
                        .measurement("stato")
                        .addField("value", 0)
                        .time(Instant.now(), WritePrecision.NS);
                writeApi.writePoint(bucket, org, point);


            } else {
                if(peopleInside<10)
                display.setRGB(0, 255, 0);
                else
                    display.setRGB(0, 0, 255);

                Point point = Point
                        .measurement("stato")
                        .addField("value", 1)
                        .time(Instant.now(), WritePrecision.NS);
                writeApi.writePoint(bucket, org, point);
            }
            if (monitorHumidity.isValid()) {
                double temperature = monitorHumidity.getValue().getTemperature();

                if (temperature >= 30) {
                    if (tempChange)
                        isOpen = false;


                } else {
                    if (tempChange)
                        isOpen = true;

                }
                Point point = Point
                        .measurement("meteo")
                        .addField("temperatura", temperature)
                        .addField("umidità", monitorHumidity.getValue().getHumidity())
                        .time(Instant.now(), WritePrecision.NS);
                writeApi.writePoint(bucket, org, point);
            }

            if (isOpen) {

                if (monitorTornello.isValid()) {
                    ang = monitorTornello.getValue().getDegrees();
                    Point point = null;
                    if(ang>30&&ang<270) {
                        newPerson = true;
                    }
                    if (ang >= 0 && ang <= 30)//uscita
                    {
                        if (peopleInside > 0&&newPerson) {

                            peopleInside--;
                            point = Point
                                    .measurement("accessi")
                                    .addTag("tipo", "uscita")
                                    .addField("utenti", peopleInside)
                                    .time(Instant.now(), WritePrecision.NS);
                            writeApi.writePoint(bucket, org, point);
                            newPerson=false;
                        }


                    } else if (ang >= 270 && ang <= 300&&newPerson)//entrata
                    {
                        if (peopleInside < 10 && peopleInside >= 0) {
                            peopleInside++;
                            point = Point
                                    .measurement("accessi")
                                    .addTag("tipo", "entrata")
                                    .addField("utenti", peopleInside)
                                    .time(Instant.now(), WritePrecision.NS);
                            writeApi.writePoint(bucket, org, point);
                            newPerson=false;
                        }

                    }

                }
            }
            Thread.sleep(100);
        }

    }
}
