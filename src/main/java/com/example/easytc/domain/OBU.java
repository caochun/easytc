package com.example.easytc.domain;

import java.util.Random;

public class OBU {


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

    private String plate;

    public OBU(String plate) {
        this.plate = plate;
    }

    public static final OBU UNRECOGNIZED = new OBU(PLATE_UNRECOGNIZED);

    public static OBU getRandomOBU() {
        return new OBU(randomVehicleId());
    }

}
