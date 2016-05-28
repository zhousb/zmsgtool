package com.zsb.entry;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.google.gson.Gson;
import com.zsb.utils.CommonUtils;
import com.zsb.utils.KafkaInfoTools;

import kafka.javaapi.PartitionMetadata;
import kafka.javaapi.consumer.SimpleConsumer;

public class ViewMsg implements ActionListener {

	private JFrame frame;
	private JPanel desktop;

	public ViewMsg() {
		frame = new JFrame();
		frame.setBounds(300, 300, 700, 500);
		frame.setTitle("消息查看窗口");
		frame.setVisible(true);
		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		desktop = new JPanel();
		frame.getContentPane().add(desktop, BorderLayout.SOUTH);
	}

	public void init() {
		Map<String,String> map = getPartitionInfo();
		Gson gson = new Gson();
		System.out.println(gson.toJson(map));

	}

	private Map<String,String> getPartitionInfo() {
		Map<String,String> info2 = new TreeMap<String,String>();
		
		TreeMap<Integer, PartitionMetadata> info = null;
		Map<String, String> conf = CommonUtils.loadSetConf();
		String[] brokers = (String[]) conf.get("kafka.broker.list").split(",");
		List<String> bks = new ArrayList<String>();
		for (int i = 0; i < brokers.length; i++) {
			bks.add(brokers[i].substring(0, brokers[i].lastIndexOf(":")));
		}
		String port = brokers[0].substring(brokers[0].indexOf(":") + 1,
				brokers[0].length());
		String topic = conf.get("kafka.topic");
		info = KafkaInfoTools.findLeader(bks, new Integer(port).intValue(),
				topic);

		for (Entry<Integer, PartitionMetadata> entry : info.entrySet()) {
			int partition = entry.getKey();
			String leadBroker = entry.getValue().leader().host();
			String clientName = "Client_" + topic + "_" + partition;
			SimpleConsumer consumer = new SimpleConsumer(leadBroker, new Integer(port).intValue(),
					100000, 64 * 1024, clientName);
			long readOffset = KafkaInfoTools.getLastOffset(consumer, topic, partition,
					kafka.api.OffsetRequest.LatestTime(), clientName);
			if (consumer != null)
				consumer.close();
			info2.put(String.valueOf(entry.getKey()), String.valueOf(readOffset));
		}
		return info2;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

}
