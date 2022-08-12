import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.client.WriteApiBlocking;
import com.influxdb.client.domain.WritePrecision;
import com.influxdb.client.write.Point;

import java.time.Instant;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class InfluxWriteTest {
    public static void main(String[] args) {
        // You can generate an API token from the "API Tokens Tab" in the UI
        String token = "3-0I2KvwpcZXqLSTbok8J6YK9ByfNFk9_QFxODSLLX6kozd7EodPBEROrXO8b0ek_wF2EfEOWSYkXyzhPiAgmA==";

        String bucket = "Industry4_test";
        String org = "SUPSI";

        InfluxDBClient client = InfluxDBClientFactory.create("http://localhost:8086", token.toCharArray());
        WriteApiBlocking writeApi = client.getWriteApiBlocking();
        for(int i=0;i<100000;i++) {
            Point temperature = Point
                    .measurement("temperature")
                    .addTag("mode", "auto")
                    .addField("value", ThreadLocalRandom.current().nextDouble(15.,40.))
                    .time(Instant.now(), WritePrecision.NS);
            Point cl = Point
                    .measurement("cl")
                    .addTag("mode", "auto")
                    .addField("value", ThreadLocalRandom.current().nextDouble(10.,100.))
                    .time(Instant.now(), WritePrecision.NS);

            writeApi.writePoint(bucket, org, temperature);
            writeApi.writePoint(bucket, org, cl);
        }
        client.close();
    }
}

