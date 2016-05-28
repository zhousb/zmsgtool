package com.zsb.msg.impl;

import com.zsb.msg.IMsgSender;

/**
 * mds 发送器 Date: 2016年4月27日 <br>
 * 
 * @author zhoushanbin 
 * 
 */
public class MDSSender implements IMsgSender {

	@Override
	public void init(String param) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendMessage(String msg) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}
/****
	private IMessageSender msgSender;

	private int partition;

	private static int count = 0;

	private static int MAX_COUNT = 10000000;

	@Override
	public void sendMessage(String msg) throws Exception {
		synchronized (MDSSender.class) {
			count += 1;
			System.out.println("send msg:" + msg);
			msgSender.send(msg.getBytes(), getPartition());
		}
	}

	private int getPartition() {
		if (count >= MAX_COUNT) {
			count = 0;
		}
		return count % this.partition;
	}

	@Override
	public void init(String param) {
		MDSParam paramObj = (new Gson()).fromJson(param, MDSParam.class);
		AuthDescriptor ad = new AuthDescriptor(paramObj.getAuthAddr(),
				paramObj.getpId(), paramObj.getPassword(), paramObj.getSrvId());
		this.msgSender = MsgSenderFactory.getClient(ad, paramObj.getTopic());
		this.partition = paramObj.getPartition();
	}

	@Override
	public void close() {

	}
***/
}
