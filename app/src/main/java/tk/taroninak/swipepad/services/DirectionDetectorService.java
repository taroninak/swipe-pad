package tk.taroninak.swipepad.services;

import tk.taroninak.swipepad.datatypes.SwipeDirection;

/**
 * Created on 28-Jan-19
 *
 * @author Taron Petrosyan
 */
public interface DirectionDetectorService {

    SwipeDirection detect(float velocityX, float velocityY);

}
