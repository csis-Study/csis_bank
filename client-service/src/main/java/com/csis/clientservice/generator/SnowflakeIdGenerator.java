package com.csis.clientservice.generator;

public class SnowflakeIdGenerator {
    private final long prefix = 10001L; // 固定前缀
    private final long sequenceBits = 33; // 剩余13位需调整算法
    private long sequence = 0L;
    private long lastTimestamp = -1L;

    public synchronized String generate() {
        long timestamp = System.currentTimeMillis();
        if (timestamp < lastTimestamp) {
            throw new RuntimeException("时钟回拨");
        }
        if (timestamp == lastTimestamp) {
            sequence = (sequence + 1) & ((1 << sequenceBits) - 1);
            if (sequence == 0) { // 当前毫秒序列用完
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0L;
        }
        lastTimestamp = timestamp;

        // 组合ID：10001(5位) + 时间戳(调整位数) + 机器ID + 序列号
        long id = prefix * 1_000_000_000_000_000L // 前缀占前5位
                | ((timestamp & 0x1FFFFF) << 34)  // 时间戳占21位（支持约69年）
                | (1L << 10)                      // 机器ID占10位（示例值）
                | sequence;                       // 序列号占12位
        return String.valueOf(id);
    }

    private long tilNextMillis(long lastTimestamp) {
        long timestamp = System.currentTimeMillis();
        while (timestamp <= lastTimestamp) {
            timestamp = System.currentTimeMillis();
        }
        return timestamp;
    }
}