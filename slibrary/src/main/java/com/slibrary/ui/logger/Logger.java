package com.slibrary.ui.logger;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.PrettyFormatStrategy;

/**
 * Loger日志打印封装(https://github.com/orhanobut/logger)
 *
 * 1. 在Application进行初始化
 * @see #init()
 *
 */

public class Logger {
    /**
     * 1. 在Application进行初始化
     * MyApplication.onCreate() - Logger.init();
     */
    public static void init() {
        // 配置打印策略
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(true)  // (Optional) Whether to show thread info or not. Default true
                .methodCount(20)         // (Optional) How many method line to show. Default 2
                .methodOffset(3)        // (Optional) Hides internal method calls up to offset. Default 5
                // .logStrategy(customLog) // (Optional) Changes the log strategy to print out. Default LogCat
                .tag("silion")   // (Optional) Global tag for every log. Default PRETTY_LOGGER
                .build();

        com.orhanobut.logger.Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy) {
            // 重写该方法控制是否打印
            @Override
            public boolean isLoggable(int priority, String tag) {
                return true;
            }
        });
    }

    /**
     * Debug级别, 输出类名
     *
     * @param object 类实例
     * @param msg 输出信息
     */
    public static void d(Object object, String msg) {
        d(object.getClass().getSimpleName() + ": " + msg);
    }

    /**
     * Debug级别
     * @param object
     */
    public static void d(Object object) {
        com.orhanobut.logger.Logger.d(object);
    }
}
