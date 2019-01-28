package tk.taroninak.swipepad.services.impl;

import tk.taroninak.swipepad.datatypes.SwipeDirection;
import tk.taroninak.swipepad.services.DirectionDetectorService;

/**
 * Created on 28-Jan-19
 *
 * @author Taron Petrosyan
 */
public class DirectionDetectorServiceImpl implements DirectionDetectorService {

    private static double QUARTER_PI = Math.PI / 4;
    private static double HALF_PI = Math.PI / 2;
    private static double THREE_QUARTER_PI = 3 * Math.PI / 4;

    @Override
    public SwipeDirection detect(float velocityX, float velocityY) {
        SwipeDirection direction;
        double theta = -Math.atan2(velocityY, velocityX);
        if (theta > -QUARTER_PI && theta < QUARTER_PI) {
            direction = SwipeDirection.RIGHT;
        } else if (theta > QUARTER_PI && theta < THREE_QUARTER_PI) {
            direction = SwipeDirection.UP;
        } else if (theta > THREE_QUARTER_PI || theta < -THREE_QUARTER_PI) {
            direction = SwipeDirection.LEFT;
        } else if (theta < -QUARTER_PI && theta > -THREE_QUARTER_PI) {
            direction = SwipeDirection.DOWN;
        } else {
            throw new IllegalArgumentException("Could not detect direction");
        }
        return rotate(direction, HALF_PI);
    }

    private SwipeDirection rotate(SwipeDirection direction, double angle) {
        return direction;
    }
}
