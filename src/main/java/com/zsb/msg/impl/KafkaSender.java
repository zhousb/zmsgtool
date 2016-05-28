package com.zsb.msg.impl;

import com.zsb.msg.IMsgSender;
import com.zsb.msg.KafkaProducer;


/**
 * kafka 发送器 Date: 2016年4月27日 <br>
 * 
 * @author zhoushanbin
 */
public class KafkaSender implements IMsgSender {

	private KafkaProducer produce = null;;

	@Override
	public void sendMessage(String msg) throws Exception {
		synchronized (KafkaSender.class) {

			if (null == produce) {
				throw new Exception("StatKafkaSender is not init");
			}
			produce.sendMessage(msg);
		}

	}

	@Override
	public void init(String param) {
		produce = new KafkaProducer(param);
	}

	@Override
	public void close() {
		if (null != produce) {
			produce.close();
		}
	}

}
