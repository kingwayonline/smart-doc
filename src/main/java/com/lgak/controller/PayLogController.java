package com.lgak.controller;

import com.lgak.bean.PayLogInfo;
import com.lgak.mapper.PayLogInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * 支付记录
 *
 * @author liubo
 * @date 2019/11/18
 */
@RestController
public class PayLogController {

    @Autowired
    private PayLogInfoMapper payLogInfoMapper;

    /**
     * 支付记录插入
     *
     * @param payLogInfo
     */
    @PostMapping("/addPayLog")
    public Object addPayLog(@RequestBody @Validated(PayLogInfo.PayLogInsert.class) PayLogInfo payLogInfo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "参数异常!";
        }
        payLogInfoMapper.insert(payLogInfo);
        return payLogInfo.getOrderId();
    }

    /**
     * 支付记录更新
     *
     * @param payLogInfo
     */
    @PostMapping("/updatePayLog")
    public Object updatePayLog(@RequestBody @Validated(PayLogInfo.PayLogUpdate.class) PayLogInfo payLogInfo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "参数异常!";
        }

        PayLogInfo build = PayLogInfo.builder().orderId(payLogInfo.getOrderId()).state(payLogInfo.getState()).build();
        payLogInfoMapper.updateByPrimaryKeySelective(build);
        return payLogInfo.getOrderId();
    }

    /**
     * 支付记录查询
     *
     * @param payLogInfo
     */
    @PostMapping("/selectByOrderIdPayLog")
    public Object selectByOrderIdPayLog(@RequestBody @Validated(PayLogInfo.PayLogSelectByOrderId.class) PayLogInfo payLogInfo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "参数异常!";
        }

        PayLogInfo result = payLogInfoMapper.selectByPrimaryKey(payLogInfo);
        return result;
    }

}
