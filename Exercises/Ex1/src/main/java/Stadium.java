import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.client.WriteApiBlocking;
import com.influxdb.client.domain.WritePrecision;
import com.influxdb.client.write.Point;
import org.iot.raspberry.grovepi.GrovePi;
import org.iot.raspberry.grovepi.pi4j.GrovePi4J;
import org.iot.raspberry.grovepi.sensors.analog.GroveRotarySensor;
import org.iot.raspberry.grovepi.sensors.data.GroveRotaryValue;
import org.iot.raspberry.grovepi.sensors.digital.GroveLed;
import org.iot.raspberry.grovepi.sensors.digital.GroveUltrasonicRanger;
import org.iot.raspberry.grovepi.sensors.i2c.GroveRgbLcd;

import java.time.Instant;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Stadium {
    public static void main(String[] args) throws Exception {
        Logger.getLogger("GrovePi").setLevel(Level.WARNING);
        Logger.getLogger("RaspberryPi").setLevel(Level.WARNING);

        GrovePi grovePi = new GrovePi4J();
        /*
         * Ultrasonic
         * 2 leds
         * display
         * rotary
         * */
        GroveUltrasonicRanger enterSensor = new GroveUltrasonicRanger(grovePi, 3);//D3
        GroveLed redLed = new GroveLed(grovePi, 5);
        GroveLed greenLed = new GroveLed(grovePi, 6);
        GroveRgbLcd lcd = grovePi.getLCD();
        GroveRotarySensor gate = new GroveRotarySensor(grovePi, 1);
        // You can generate an API token from the "API Tokens Tab" in the UI
        String token = "mBXK-AYDE8nun8-3JfSLXRLU5wDLvIjxTNVadTuWwtrrYLLlYpUccNudoRucm_-He127mxRP2Wa7OKNBamQo7Q==";
        String bucket = "stadium";
        String org = "SUPSI";

        InfluxDBClient client = InfluxDBClientFactory.create("http://10.42.0.1:8086", token.toCharArray());

        String data = "mem,host=host1 used_percent=23.43234543";




        int tifosi = 0;
        final int max = 10;
        boolean open = false;
        final int maxDeadZone = 170, minDeadZone = 130;
        while (true) {
            lcd.setText("Tifosi: " + tifosi);
            GroveRotaryValue rotaryValue = gate.get();
            if (rotaryValue.getDegrees() >= minDeadZone && rotaryValue.getDegrees() <= maxDeadZone) {
                redLed.set(0);
                greenLed.set(0);
                open = false;
            }
            if (!open)
                if (rotaryValue.getDegrees() <= minDeadZone || rotaryValue.getDegrees() >= maxDeadZone) {
                    open = true;
                    if (rotaryValue.getDegrees() > maxDeadZone) {
                        //uscita tifoso
                        if (enterSensor.get() <= 20) {
                            if (tifosi > 0) {
                                tifosi--;
                                Point point = Point
                                        .measurement("stadium")
                                        .addTag("tipo","uscita")
                                        .addField("value", tifosi)
                                        .time(Instant.now(), WritePrecision.NS);
                                redLed.set(1);
                                WriteApiBlocking writeApi = client.getWriteApiBlocking();
                                writeApi.writePoint(bucket, org, point);
                            }

                        }
                    } else if (rotaryValue.getDegrees() < minDeadZone) {
                        //entrata tifoso
                        if (enterSensor.get() <= 20) {
                            if (tifosi < max) {
                                tifosi++;
                                Point point = Point
                                        .measurement("stadium")
                                        .addTag("tipo","entrata")
                                        .addField("value", tifosi)
                                        .time(Instant.now(), WritePrecision.NS);
                                greenLed.set(1);
                                WriteApiBlocking writeApi = client.getWriteApiBlocking();
                                writeApi.writePoint(bucket, org, point);
                            }

                        }
                    }
                }

        }

    }
}
