package tk.taroninak.swipepad.config.di;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;
import tk.taroninak.swipepad.MainActivity;
import tk.taroninak.swipepad.services.DirectionDetectorService;
import tk.taroninak.swipepad.services.GestureDetectorService;
import tk.taroninak.swipepad.services.PacketSenderService;
import tk.taroninak.swipepad.services.impl.DirectionDetectorServiceImpl;
import tk.taroninak.swipepad.services.impl.GestureDetectorServiceImpl;
import tk.taroninak.swipepad.services.impl.PacketSenderServiceImpl;

/**
 * Created on 28-Jan-19
 *
 * @author Taron Petrosyan
 */
@Module
public abstract class AppModule {

    @ContributesAndroidInjector
    abstract MainActivity contributeActivityInjector();

    static PacketSenderService packetSenderService = new PacketSenderServiceImpl(new ObjectMapper());

    static DirectionDetectorService directionDetectorService = new DirectionDetectorServiceImpl();

    static GestureDetectorService gestureDetectorService = new GestureDetectorServiceImpl(packetSenderService, directionDetectorService);

    @Provides
    @Singleton
    public static PacketSenderService packetSenderService() {
        return packetSenderService;
    }

    @Provides
    @Singleton
    public static GestureDetectorService gestureDetectorService() {
        return gestureDetectorService;
    }

    @Provides
    @Singleton
    public static DirectionDetectorService directionDetectorService() {
        return directionDetectorService;
    }
}
