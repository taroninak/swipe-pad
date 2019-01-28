package tk.taroninak.swipepad.services;


import tk.taroninak.swipepad.datatypes.SwipeCommand;

/**
 * Created by Taron Petrosyan
 * Date: 5/20/18
 */
public interface PacketSenderService {
    void send(SwipeCommand command);
}
