package tk.taroninak.swipepad.services.impl;

import android.os.AsyncTask;
import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

import static tk.taroninak.swipepad.config.AppConstants.*;

import tk.taroninak.swipepad.datatypes.SwipeCommand;
import tk.taroninak.swipepad.services.PacketSenderService;

/**
 * Created by Taron Petrosyan
 * Date: 5/20/18
 */
public class PacketSenderServiceImpl implements PacketSenderService {

    private ObjectMapper mapper;

    public PacketSenderServiceImpl(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void send(SwipeCommand command) {
        ServiceTask serviceTask = new ServiceTask();
        try {
            serviceTask.execute(mapper.writeValueAsString(command));
        } catch (IOException e) {
            throw new IllegalArgumentException("Corrupted command was provided. Cant be deserialized to JSON");
        }
    }

    private static class ServiceTask extends AsyncTask<String, Integer, Integer> {
        @Override
        protected Integer doInBackground(String... strings) {
            try {
                DatagramSocket socket = new DatagramSocket();
                byte[] d = strings[0].getBytes();
                DatagramPacket packet = new DatagramPacket(d, d.length, new InetSocketAddress(SERVER_HOST, SERVER_PORT));
                socket.send(packet);
                Log.i("info", "packet is sent:" + strings);
                return 0;
            } catch (java.io.IOException e) {
                Log.e("perr", e.getMessage());
                e.printStackTrace();
                return 1;
            }
        }
    }
}
