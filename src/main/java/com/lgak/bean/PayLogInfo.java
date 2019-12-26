package com.lgak.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "pay_log_info")
public class PayLogInfo {
    /**
     * 订单ID
     */
    @Id
    @Column(name = "order_id")
    @NotNull(groups = {PayLogInsert.class, PayLogUpdate.class, PayLogSelectByOrderId.class})
    private String orderId;

    /**
     * 商家ID
     */
    @Column(name = "store_id")
    @NotNull(groups = {PayLogInsert.class})
    private Integer storeId;

    /**
     * 金额
     */
    @NotNull(groups = PayLogInsert.class)
    private Double money;

    /**
     * 1:付款 2:退款
     */
    @NotNull(groups = PayLogInsert.class)
    private Integer type;

    /**
     * 日期(YYYY/MM/DD)
     */
    @JsonFormat(pattern = "yyyy/MM/dd")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate date;

    /**
     * 时间(HH:MM:SS)
     */
//    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime time;

    /**
     * 1:支付宝 2:微信 3:农行
     */
    @NotNull(groups = PayLogInsert.class)
    private Integer source;

    /**
     * 1:支付中 2:支付完成 3:退款中 4:退款完成
     */
    @NotNull(groups = {PayLogInsert.class, PayLogUpdate.class})
    private Integer state;

    public interface PayLogInsert {
    }

    public interface PayLogUpdate {
    }

    public interface PayLogSelectByOrderId {
    }
}