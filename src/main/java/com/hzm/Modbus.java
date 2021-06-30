package com.hzm;

import java.math.BigInteger;

/**
 * @author Hezeming
 * @version 1.0
 * @date 2021年03月16日
 */
public class Modbus {

    public static void main(String[] args) {
        System.out.println(getCRC("01 03 00 00 00 10".replace(" ", "")));

        System.out.println(Integer.parseInt("2C", 16));
        System.out.println(Integer.parseInt("0A", 16));
    }

    public static byte[] toBytes(String str) {
        byte[] bytes = new BigInteger(str, 16).toByteArray();
        return bytes;
    }

    public static String getCRC(String str) {
        byte[] bytes = toBytes(str);
        int CRC = 0x0000ffff;
        int POLYNOMIAL = 0x0000a001;

        int i, j;
        for (i = 0; i < bytes.length; i++) {
            CRC ^= ((int) bytes[i] & 0x000000ff);
            for (j = 0; j < 8; j++) {
                if ((CRC & 0x00000001) != 0) {
                    CRC >>= 1;
                    CRC ^= POLYNOMIAL;
                } else {
                    CRC >>= 1;
                }
            }
        }
        String crc = Integer.toHexString(CRC);
        if (crc.length() == 2) {
            crc = "00" + crc;
        } else if (crc.length() == 3) {
            crc = "0" + crc;
        }
        crc = crc.substring(2, 4) + crc.substring(0, 2);
        return crc.toUpperCase();
    }
}
