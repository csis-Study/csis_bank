package com.csis.clientservice.generator;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.sql.*;
import java.util.concurrent.atomic.AtomicLong;

public class ClientIdGenerator implements IdentifierGenerator {
    private static final String PREFIX = "10001";
    private static final AtomicLong sequence = new AtomicLong(0);

    // 在类加载时初始化序列
    static {
        sequence.set(loadMaxSequenceFromDB() + 1); // 避免重复
    }

    /**
     * 从数据库查询当前最大序列号
     */
    private static long loadMaxSequenceFromDB() {
        // 实际项目中需通过DataSource或Hibernate Session查询
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/client-service", "root", "123456")) {
            String sql = "SELECT MAX(usr_id) AS max_id FROM client";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    String maxId = rs.getString("max_id");
                    if (maxId != null && maxId.startsWith(PREFIX)) {
                        // 提取序列部分：10001 + 13位数字 → 取后13位
                        String seqPart = maxId.substring(PREFIX.length());
                        return Long.parseLong(seqPart);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("初始化序列失败", e);
        }
        return 0; // 无数据时从0开始
    }

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object obj) {
        long currentSeq = sequence.getAndIncrement();
        return String.format("%s%013d", PREFIX, currentSeq);
    }
}