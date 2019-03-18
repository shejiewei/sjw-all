package com.youfan.service.weixin;

import lombok.Data;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Data
@Root(name = "xml", strict = false)
public class WxPaySyncResponse {
    @Element(name = "return_code")
    private String returnCode;

    @Element(name = "return_msg", required = false)
    private String returnMsg;

    /** 以下字段在return_code为SUCCESS的时候有返回. */
    @Element(name = "appid", required = false)
    private String appid;

    @Element(name = "mch_id", required = false)
    private String mchId;

    @Element(name = "device_info", required = false)
    private String deviceInfo;

    @Element(name = "nonce_str", required = false)
    private String nonceStr;

    @Element(name = "sign", required = false)
    private String sign;

    @Element(name = "result_code", required = false)
    private String resultCode;

    @Element(name = "err_code", required = false)
    private String errCode;

    @Element(name = "err_code_des", required = false)
    private String errCodeDes;

    /** 以下字段在return_code 和result_code都为SUCCESS的时候有返回. */
    @Element(name = "trade_type", required = false)
    private String tradeType;

    @Element(name = "prepay_id", required = false)
    private String prepayId;

    @Element(name = "code_url", required = false)
    private String codeUrl;

    /** 微信支付异步返回的数据. */
}