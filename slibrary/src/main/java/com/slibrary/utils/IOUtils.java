package com.slibrary.utils;

import com.slibrary.logger.Logger;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by silion on 2018/1/2.
 */

public class IOUtils {
    /** 关闭流 */
    public static boolean close(Closeable io) {
        if (io != null) {
            try {
                io.close();
            } catch (IOException e) {
                Logger.e(e, e.getMessage(), e);
            }
        }
        return true;
    }
}
