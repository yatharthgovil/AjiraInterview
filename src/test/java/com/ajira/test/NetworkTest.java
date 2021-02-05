package com.ajira.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;

import com.ajira.network.Network;
import com.ajira.node.DeviceType;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class NetworkTest {

	public static Network network;

	@BeforeEach
	public void testInit() {
		network = new Network();
	}

	@Test
	@Order(1)
	public void testAddComputer() {
		network.add("C1", DeviceType.COMPUTER);
		assertEquals("C1", network.getNodes().get(0).getName());// test name
		assertEquals(0, network.getNodes().get(0).getIndex()); // test devicetype
		network.add("C3", DeviceType.COMPUTER);
		assertEquals(1, network.getNodes().get(1).getIndex());// test indexing
	}

	@Test
	@Order(2)
	public void testAddRepeater() {
		network.add("R1", DeviceType.REPEATER);
		assertEquals("R1", network.getNodes().get(0).getName());
		assertEquals(0, network.getNodes().get(0).getIndex());
		assertEquals(DeviceType.REPEATER, network.getNodes().get(0).getdType());
	}

	@Test
	@Order(3)
	public void testConnect() {
		addHelper();
		network.connect("C1", "R1");// c1:index0, R1: index2,c3: index1
		network.connect("R1", "C3");
		assertEquals(true, network.getConnections().get(0).contains(2));
		assertEquals(true, network.getConnections().get(2).contains(1));
	}

	/***
	 * basic test case for inforoute
	 */
	@Test
	@Order(4)
	public void testInfoRouteBasic() {
		
		addHelper();
		connectHelper();
		network.infoRoute("C1", "C3");
		assertEquals("C1->R1->C3", network.infoRoute("C1", "C3"));
	}
	
	public void addHelper() {
		network.add("C1", DeviceType.COMPUTER);
		network.add("C3", DeviceType.COMPUTER);
		network.add("R1", DeviceType.REPEATER);
	}
	public void connectHelper() {
		network.connect("C1", "R1");// c1:index0, R1: index2,c3: index1
		network.connect("R1", "C3");
	}

	@Test
	@Order(5)
	public void testDeviceStrength() {
		addHelper();
		network.setStrength("C1", 3);
		assertEquals(3, network.getNodes().get(0).getStrength());
	}

	/***
	 * When two routes are possible between two node 
	 * but only pne of the  two routes
	 * can be used because of the limit set by strength
	 */
	@Test
	@Order(6)
	public void testInfoRouteAdvanced() {
		addHelper();
		connectHelper();
		network.add("C5", DeviceType.COMPUTER);
		network.add("C4", DeviceType.COMPUTER);
		network.add("C6", DeviceType.COMPUTER);
		network.add("C7", DeviceType.COMPUTER);
		network.connect("C1", "C5");
		network.connect("C5", "C4");
		network.connect("C3", "C4");
		network.connect("C4", "C6");
		network.connect("C6", "C7");
		network.setStrength("C1", 3);

		assertEquals("C1->R1->C3->C4->C6->C7", network.infoRoute("C1", "C7"));
	}

	/*
	 * @Test public void demoTest() { System.out.println("DemoTEst"); }
	 */
}
