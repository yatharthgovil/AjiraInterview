package com.ajira.network.operations;

import com.ajira.network.Network;
import com.ajira.node.DeviceType;

public interface NetworkOperations {

	public int add(String name, DeviceType dType);

	public String infoRoute(String n1, String n2);

	public int connect(String n1, String n2);

}
