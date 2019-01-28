package tk.taroninak.swipepad.services.impl;

import android.util.Log;
import android.view.MotionEvent;

import tk.taroninak.swipepad.datatypes.SwipeAction;
import tk.taroninak.swipepad.datatypes.SwipeCommand;
import tk.taroninak.swipepad.datatypes.SwipeDirection;
import tk.taroninak.swipepad.services.DirectionDetectorService;
import tk.taroninak.swipepad.services.GestureDetectorService;
import tk.taroninak.swipepad.services.PacketSenderService;

/**
 * Created on 28-Jan-19
 *
 * @author Taron Petrosyan
 */
public class GestureDetectorServiceImpl implements GestureDetectorService {

    private static final String DEBUG_TAG = "SWIPE_PAD_DEBUG";
    private static final String INFO_TAG = "SWIPE_PAD_INFO";


    private PacketSenderService packetSenderService;
    private DirectionDetectorService directionDetectorService;
    private int fingerCount;

    public GestureDetectorServiceImpl(PacketSenderService packetSenderService, DirectionDetectorService directionDetectorService) {
        this.packetSenderService = packetSenderService;
        this.directionDetectorService = directionDetectorService;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        Log.d(DEBUG_TAG, "onSingleTapConfirmed called");
        Log.i(INFO_TAG, "Single Tap gesture detected");
        detected(new SwipeCommand(SwipeAction.CLICK, null, 1, 0));
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        Log.d(DEBUG_TAG, "onDoubleTap called");
        Log.i(INFO_TAG, "Double Tap gesture detected");
        detected(new SwipeCommand(SwipeAction.CLICK, null, 2, 0));
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        Log.d(DEBUG_TAG, "onDoubleTapEvent called");
        return true;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        Log.d(DEBUG_TAG, "onDown called");
        fingerCount = 0;
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        Log.d(DEBUG_TAG, "onShowPress called");
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        Log.d(DEBUG_TAG, "onSingleTapUp called");
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        Log.d(DEBUG_TAG, "onScroll called");

        fingerCount = Math.max(Math.max(e2.getPointerCount(), e1.getPointerCount()), fingerCount);
        if (fingerCount < 3) {
            int time = 0;
            for (int i = 0; i < e1.getHistorySize(); i++) {
                time += e1.getHistoricalEventTime(i);
            }
            for (int i = 0; i < e2.getHistorySize(); i++) {
                time += e2.getHistoricalEventTime(i);
            }
            if (time < 1000000) {
                return true;
            }
            Log.i(INFO_TAG, "Scroll gesture detected");
            float velocityX = (e2.getX() - e1.getX()) / (e2.getEventTime() - e1.getEventTime());
            float velocityY = (e2.getY() - e1.getY()) / (e2.getEventTime() - e1.getEventTime());
            SwipeDirection direction = directionDetectorService.detect(distanceX, distanceY);
            detected(new SwipeCommand(fingerCount == 2 ? SwipeAction.SCROLL : SwipeAction.MOVE, direction, fingerCount, Math.max(velocityX, velocityY), distanceX, distanceY));
        }
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        Log.d(DEBUG_TAG, "onLongPress called");
        Log.i(INFO_TAG, "Long Press gesture detected");
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        Log.d(DEBUG_TAG, "onFling called");

        SwipeDirection direction = directionDetectorService.detect(velocityX, velocityY);

        if (fingerCount > 2) {
            Log.i(INFO_TAG, String.format("%s finger swipe %s gesture detected", fingerCount, direction));
            detected(new SwipeCommand(SwipeAction.SWIPE, direction, fingerCount, Math.max(velocityX, velocityY)));
            fingerCount = 0;
        }
        return true;
    }

    private void detected(SwipeCommand command) {
        packetSenderService.send(command);
    }
}
