/*
 * Copyright © 2023 FinToken Ltd.,  All rights reserved.
 */

package org.example.feettool.util;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 时间顺序UUID
 *
 * @author zhengguangle
 * @version create
 * @since 2023-02-04
 */
public class TimingUuid {
    private static int postfix = 0;
    private static final ReentrantLock lock = new ReentrantLock();
    private static String timestamp = "";

    public static String uuid() {
        lock.lock();
        try {
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS", Locale.US);
            long dateLong = Long.parseLong(sdf.format(date));
            String prefix = Long.toString(dateLong, 36);

            final SecureRandom numberGenerator = new SecureRandom();
            byte[] data = new byte[16];
            numberGenerator.nextBytes(data);
            data[6] &= 0x0f;  /* clear version        */
            data[6] |= 0x40;  /* set to version 4     */
            data[8] &= 0x3f;  /* clear variant        */
            data[8] |= 0x80;  /* set to IETF variant  */

            long msb = 0;
            long lsb = 0;
            for (int i = 0; i < 8; i++)
                msb = (msb << 8) | (data[i] & 0xff);
            for (int i = 8; i < 16; i++)
                lsb = (lsb << 8) | (data[i] & 0xff);

            StringBuilder builder = new StringBuilder(32);
            builder.append(prefix);
            builder.append(getPostfix(prefix));
            builder.append(digits(msb >> 32, 4));
            builder.append(digits(msb, 4));
            builder.append(digits(lsb >> 48, 4));
            builder.append(digits(lsb, 12));
            builder.setLength(32);

            return builder.toString();
        } finally {
            lock.unlock();
        }
    }

    private static String getPostfix(String prefix) {
        if (prefix.equals(timestamp)) {
            postfix++;
        } else {
            timestamp = prefix;
            postfix = 1;
        }
        String postfixStr = Integer.toString(postfix, 36);
        if (postfixStr.length() > 3) {
            postfix = 1;
            postfixStr = "001";
        } else if (postfixStr.length() < 3) {
            String format = "%0" + (3 - postfixStr.length()) + "d%s";
            postfixStr = String.format(format, 0, postfixStr);
        }
        return postfixStr;
    }

    private static String digits(long val, int digits) {
        long hi = 1L << (digits * 4);
        return Long.toString(hi | (val & (hi - 1)), 36).substring(1);
    }
}
