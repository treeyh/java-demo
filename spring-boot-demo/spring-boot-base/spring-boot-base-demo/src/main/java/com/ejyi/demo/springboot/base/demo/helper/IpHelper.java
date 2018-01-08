package com.ejyi.demo.springboot.base.demo.helper;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class IpHelper {

    public static String getIpAddr(HttpServletRequest request) {
        if (request == null) return "";
        String ip = request.getHeader("X-Forwarded-For");

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        String p[] = ip.split("[,]", 10);
        if (p.length == 0) {
            return ip;
        }
        if (p.length == 1) {
            return p[0];
        }
        if ("unknown".equalsIgnoreCase(p[0])) {
            return p[1];
        }
        return p[0];
    }

    /**
     * 使用 {@code java.net.InetAddress.isSiteLocalAddress()} 获取内网IP
     *
     * @return 内网IP
     */
    public static String getLocalAddress() {
        List<String> ipList = new ArrayList<String>();
        try {
            Enumeration<NetworkInterface> nis = NetworkInterface.getNetworkInterfaces();
            while (nis.hasMoreElements()) {
                NetworkInterface ni = nis.nextElement();
                Enumeration<InetAddress> addrs = ni.getInetAddresses();
                while (addrs.hasMoreElements()) {
                    InetAddress inetAddress = addrs.nextElement();
                    if (inetAddress.isSiteLocalAddress()) {
                        ipList.add(inetAddress.getHostAddress());
                    }
                }
            }

        } catch (SocketException e) {
            throw new RuntimeException("Can't get local ip.", e);
        }
        if (ipList.isEmpty()) {
            throw new RuntimeException("No available local ip.");
        }
        return ipList.get(0);// 选择第一个网卡的ip，因为后面的可能有虚拟的
    }


    public static  String getIpStr(String ip){
        if(StringUtils.isEmpty(ip)){
            return null;
        }
        String[] ips = StringUtils.split(ip, ".");

        if(4 != ips.length){
            return null;
        }
        String ipVal = StringHelper.addPreForNum(ips[0], 3, "0")
                + StringHelper.addPreForNum(ips[1], 3, "0")
                + StringHelper.addPreForNum(ips[2], 3, "0")
                + StringHelper.addPreForNum(ips[3], 3, "0");

        return ipVal;
    }

    public static void main(String[] args) {
        System.out.println(getIpStr("127.0.0.1"));
    }
}



