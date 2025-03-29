package com.csis.notificationservice.utils;

public class UserContext {
    // 静态私有化 ThreadLocal 实例，防止外部修改
    private static final ThreadLocal<String> userIdHolder = new ThreadLocal<>();

    /**
     * 获取当前线程的用户ID
     * @return 用户ID，如果未设置则返回 null
     */
    public static String getUserId() {
        return userIdHolder.get();
    }

    /**
     * 设置当前线程的用户ID（仅供过滤器内部调用）
     * @param userId 用户ID
     */
    static void setUserId(String userId) {
        userIdHolder.set(userId);
    }

    /**
     * 清理当前线程的用户ID（防止内存泄漏）
     */
    static void clear() {
        userIdHolder.remove();
    }
}