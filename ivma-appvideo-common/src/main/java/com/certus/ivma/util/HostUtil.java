package com.certus.ivma.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by 123 on 2019/3/7.
 */
public class HostUtil {

    private HostUtil(){

    }
    public static String getLocalHostIp(){
        InetAddress address = null;
        try {
            address = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return address.getHostAddress();
    }


}
