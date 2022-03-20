package com.example.easytc.domain;

import java.time.Instant;
import java.util.Random;
import java.util.UUID;

public class Vehicle {

    public static enum Status {
        UNKNOWN, CHARGED, CHARGING, UNCHARGED
    }


    private static final String PLATE_UNRECOGNIZED = "中OPLATE";

    private static String randomVehicleId() {
        char[] provinceAbbr = { // 省份简称 4+22+5+3
                '京', '津', '沪', '渝',
                '冀', '豫', '云', '辽', '黑', '湘', '皖', '鲁', '苏', '浙', '赣',
                '鄂', '甘', '晋', '陕', '吉', '闽', '贵', '粤', '青', '川', '琼',
                '宁', '新', '藏', '桂', '蒙',
                '港', '澳', '台'
        };
        String alphas = "QWERTYUIOPASDFGHJKLZXCVBNM1234567890"; // 26个字母 + 10个数字

        Random random = new Random(); // 随机数生成器

        String vid = "";

        // 省份+地区代码+·  如 湘A· 这个点其实是个传感器，不过加上美观一些
        vid += provinceAbbr[random.nextInt(34)]; // 注意：分开加，因为加的是2个char
        vid += alphas.charAt(random.nextInt(26)) + "·";

        // 5位数字/字母
        for (int i = 0; i < 5; i++) {
            vid += alphas.charAt(random.nextInt(36));
        }

        return vid;
    }

    private String plate = null;
    private String obu = null;
    private String color = "W";
    private Status states = Status.UNKNOWN;
    private Instant mtime = Instant.now();

    public Vehicle(String plate, String obu) {
        this.plate = plate;
        this.obu = obu;
    }

    public boolean noOBU() {
        return (obu == null);
    }


    public Instant getLastUpdate() {
        return this.mtime;
    }

    public void update() {
        this.mtime = Instant.now();
    }

    public void setStatus(Status states) {
        this.states = states;
        this.update();
    }

    public static Vehicle noOBUVehicle() {
        Vehicle vehicle = new Vehicle(PLATE_UNRECOGNIZED, null);
        vehicle.setStatus(Status.UNCHARGED);
        return vehicle;
    }

    public static Vehicle randomVehicle() {
        return new Vehicle(randomVehicleId(), UUID.randomUUID().toString());
    }

}
