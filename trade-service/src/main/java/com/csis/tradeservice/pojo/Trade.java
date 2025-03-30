package com.csis.tradeservice.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@Table(name = "Trade")  // 对应数据库中的 Trade 表
public class Trade {

    @Id
    @Column(name = "id",nullable = false)//主键
    private int id;

    @Column(name = "trade_id", length = 20, nullable = false)  // 交易流水号，长度为20，非空
    private String tradeId;

    @Column(name = "client_id", length = 18, nullable = false)  // 客户ID：长度为18，非空
    private String clientId;

    @Column(name = "product_item", length = 20, nullable = false)  // 产品编号：长度为20，非空
    private String productItem;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", length = 6, nullable = false)  // 交易类型：枚举值（申购 / 赎回 / 调仓），非空
    private TradeType type;

    @Column(name = "amount", precision = 20, scale = 2, nullable = false)  // 金额或数量：精度为20，小数位数为2，非空
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 6, nullable = false)  // 状态：枚举值（待审批 / 审批中 / 已拒绝 / 已执行），非空
    private TradeStatus status;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "create_time", nullable = false)  // 创建时间：不可为空
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "update_time", nullable = false)  // 最新状态变更时间：不可为空
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    // 交易类型枚举
    public enum TradeType {
        申购, 赎回,调仓
    }

    // 交易状态枚举
    public enum TradeStatus {
        待审批, 审批中, 已拒绝, 已执行
    }
}
