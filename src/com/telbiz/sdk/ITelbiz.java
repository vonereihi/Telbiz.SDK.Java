package com.telbiz.sdk;

import com.telbiz.model.CommonResponse;
import com.telbiz.model.SMSHeader;

public interface ITelbiz {
	CommonResponse SendSMS(SMSHeader header, String phone, String message);
	CommonResponse Topup(String phone, int amount);
}
