package org.agora.server;

import java.io.IOException;

import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;

public class AgoraServer {

	public static void main(String[] args) {
		System.out.println("Starting Server...");
		
		// Getting serial ports list into the array
		System.out.println("Checking Serial Ports...");
		String[] portNames = SerialPortList.getPortNames();
		        
		if (portNames.length == 0) {
		    System.out.println("There are no serial-ports.");
		    return;
		}

		for (int i = 0; i < portNames.length; i++){
		    System.out.println(portNames[i]);
		}
		
		byte[] inbuffer = new byte[20];
		
		try {
	        System.in.read(inbuffer);
	    } catch (IOException e) {
	         e.printStackTrace();
	    }
		
		String SPort = (new String(inbuffer)).trim();
		
		boolean found = false;
		for(int i=0;i<portNames.length;i++){
			if(portNames[i].matches(SPort)) found = true;
		}
		
		if(!found){
			System.out.println("Invalid Port...");
			return;
		}
		
		// Writing to port
		System.out.println("Writing to Port "+SPort);
		SerialPort serial = new SerialPort(SPort);
		try {
		    serial.openPort();

		    serial.setParams(SerialPort.BAUDRATE_9600,
		                         SerialPort.DATABITS_8,
		                         SerialPort.STOPBITS_1,
		                         SerialPort.PARITY_NONE);

		    serial.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN | 
		                                  SerialPort.FLOWCONTROL_RTSCTS_OUT);

		    serial.writeByte((byte)0xFF);
		    
		    while(true){}
		}
		catch (SerialPortException ex) {
		    System.out.println("There was an error on writing string to port т: " + ex);
		}

	}

}
