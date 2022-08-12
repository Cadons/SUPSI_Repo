import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.client.WriteApiBlocking;
import com.influxdb.client.domain.WritePrecision;
import org.iot.raspberry.grovepi.GrovePi;
import org.iot.raspberry.grovepi.pi4j.GrovePi4J;
import org.iot.raspberry.grovepi.sensors.analog.GroveLightSensor;
import org.iot.raspberry.grovepi.sensors.digital.GroveUltrasonicRanger;
import org.iot.raspberry.grovepi.sensors.synch.SensorMonitor;

import java.awt.*;
import java.time.Instant;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InfluxDistance {
    public static void main(String[] args) throws Exception {
        Logger.getLogger("GrovePi").setLevel(Level.WARNING);
        Logger.getLogger("RaspberryPi").setLevel(Level.WARNING);
        GrovePi grovePi = new GrovePi4J();
        GroveLightSensor ultrasonicRanger=new GroveLightSensor(grovePi,1);
        SensorMonitor<Double> sensorMonitor=new SensorMonitor<>(ultrasonicRanger,200);

        sensorMonitor.start();


        String token = "lDb6YYj-ebMXE5KDS8NYgV6dLcjWabAw8DsJJLwr5rlxQAVLCaUOrDo_2wPnxs3OmjgOXjwyl3s50FEL7D-yKw==";
        String bucket = "LightSensor";
        String org = "SUPSI";

        InfluxDBClient client = InfluxDBClientFactory.create("http://10.42.0.1:8086", token.toCharArray());
        com.influxdb.client.write.Point measurePoint=new  com.influxdb.client.write.Point("LightSensor");
        WriteApiBlocking writeApi = client.getWriteApiBlocking();

        while (true)
        {
            if(sensorMonitor.isValid())
            {
                double distance=sensorMonitor.getValue();
                System.out.println(distance);
                String data = "mem,host=host1 used_percent=23.43234543";
                if(distance<100)
                    measurePoint.addTag("type","dangerous");
                else
                    measurePoint.addTag("type","normal");

                measurePoint.addField("value",distance).time(Instant.now(),WritePrecision.NS);

                writeApi.writePoint(bucket, org, measurePoint);
            }
            Thread.sleep(200);
        }
    }
}
