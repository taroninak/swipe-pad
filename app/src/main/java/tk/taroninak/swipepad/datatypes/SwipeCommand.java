package tk.taroninak.swipepad.datatypes;

/**
 * Created on 28-Jan-19
 *
 * @author Taron Petrosyan
 */
public class SwipeCommand {
    private SwipeAction action;

    private SwipeDirection direction;

    private int fingers;

    private float velocity;

    private float distanceX;

    private float distanceY;

    public SwipeCommand(SwipeAction action, SwipeDirection direction, int fingers, float velocity) {
        this.action = action;
        this.direction = direction;
        this.fingers = fingers;
        this.velocity = velocity;
    }

    public SwipeCommand(SwipeAction action, SwipeDirection direction, int fingers, float velocity, float distanceX, float distanceY) {
        this.action = action;
        this.direction = direction;
        this.fingers = fingers;
        this.velocity = velocity;
        this.distanceX = distanceX;
        this.distanceY = distanceY;
    }

    public SwipeAction getAction() {
        return action;
    }

    public void setAction(SwipeAction action) {
        this.action = action;
    }

    public SwipeDirection getDirection() {
        return direction;
    }

    public void setDirection(SwipeDirection direction) {
        this.direction = direction;
    }

    public int getFingers() {
        return fingers;
    }

    public void setFingers(int fingers) {
        this.fingers = fingers;
    }

    public float getVelocity() {
        return velocity;
    }

    public void setVelocity(float velocity) {
        this.velocity = velocity;
    }

    public float getDistanceX() {
        return distanceX;
    }

    public void setDistanceX(float distanceX) {
        this.distanceX = distanceX;
    }

    public float getDistanceY() {
        return distanceY;
    }

    public void setDistanceY(float distanceY) {
        this.distanceY = distanceY;
    }
}
