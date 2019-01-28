package tk.taroninak.swipepad.config.di;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import tk.taroninak.swipepad.SwipePadApp;

/**
 * Created on 28-Jan-19
 *
 * @author Taron Petrosyan
 */
@Singleton
@Component(modules = {AndroidInjectionModule.class, AppModule.class})
public interface AppComponent extends AndroidInjector<SwipePadApp> {
}
