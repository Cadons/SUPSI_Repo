package ch.supsi;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.iot.raspberry.grovepi.GrovePi;
import org.iot.raspberry.grovepi.pi4j.GrovePi4J;
import org.iot.raspberry.grovepi.sensors.digital.GroveButton;
import org.iot.raspberry.grovepi.sensors.digital.GroveBuzzer;
import org.iot.raspberry.grovepi.sensors.digital.GroveLed;
import org.iot.raspberry.grovepi.sensors.digital.GroveUltrasonicRanger;
import org.iot.raspberry.grovepi.sensors.i2c.GroveRgbLcd;
import org.iot.raspberry.grovepi.sensors.listener.GroveButtonListener;
import org.iot.raspberry.grovepi.sensors.synch.OnOffCommuter;
import org.iot.raspberry.grovepi.sensors.synch.SensorMonitor;

/**
 * @author Giuseppe Landolfi
 * @author Radostin Tsetanov
 * <p>
 * UltraSonic Ranger --> D6 Led --> D3
 */
public class ParkingSensor {

    private static boolean acquisitionOn = true;
    private static boolean soundOn = false;

    public static void main(String[] args) throws Exception {
        Logger.getLogger("GrovePi").setLevel(Level.WARNING);
        Logger.getLogger("RaspberryPi").setLevel(Level.WARNING);
        GrovePi grovePi = new GrovePi4J();
        GroveUltrasonicRanger ranger = new GroveUltrasonicRanger(grovePi, 6);
        GroveLed led = new GroveLed(grovePi, 3);
        GroveButton disableSound = new GroveButton(grovePi, 1);
        final GroveBuzzer sound = new GroveBuzzer(grovePi, 5);
        sound.set(1);
        GroveRgbLcd lcd = grovePi.getLCD();
        OnOffCommuter blinkLed = new OnOffCommuter(led, 100, 200);
        OnOffCommuter buzzerBeep = new OnOffCommuter(sound, 100, 200);

        SensorMonitor<Double> distanceMonitor = new SensorMonitor<>(ranger, 10);
        SensorMonitor<Boolean> onOfMonitorBtn = new SensorMonitor<>(disableSound, 1);
        lcd.setRGB(255, 255, 255);

        boolean alert = false;
        GroveButtonListener btnListenr = new GroveButtonListener() {
            @Override
            public void onRelease() {
            }

            @Override
            public void onPress() {
            }

            @Override
            public void onClick() {
                soundOn = !soundOn;
            }
        };
        disableSound.setButtonListener(btnListenr);
        distanceMonitor.start();
        //alternative
        blinkLed.start();
        buzzerBeep.start();
        //---------
        onOfMonitorBtn.start();


        while (true) {

            if (acquisitionOn) {

                if (distanceMonitor.isValid()) {

                    double distance = distanceMonitor.getValue();
                    lcd.setText("Distance: " + distance);

                    long freq = (int) distance * 10;
                    if (distance < 100) {
                        if (alert == false) {
                            alert = true;
                            blinkLed.start();
                            if (soundOn)
                                buzzerBeep.start();
                            else
                                buzzerBeep.stop();


                        }

                        System.out.println(distance);

                        blinkLed.setResetInterval(freq);
                        buzzerBeep.setResetInterval(freq);
                       /* if (distance <= 4) {
                            blinkLed.stop();
                            buzzerBeep.stop();
                            sound.set(true);
                            led.set(true);

                        } else {
                            blinkLed.start();
                            buzzerBeep.start();
                        }*/
                    } else {
                        alert = false;
                        blinkLed.stop();
                        buzzerBeep.stop();
                    }
                }

            }

            Thread.sleep(100);
        }
    }

}
