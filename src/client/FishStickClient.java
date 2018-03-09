package client;
/* File: FishStickClient.java
 * Author: Stanley Pieda, based on course example by Todd Kelley
 * Modified Date: Jan 2018
 * Description: Networking client that uses simple protocol to send and receive transfer objects.
 */
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.UUID;

import datatransfer.FishStick;
import datatransfer.Message;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Need programming comments with correct author name throughout this class
 * @author xyz abc
 */
public class FishStickClient {
	private Socket connection;
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private String message = "";
	private String serverName = "localhost";
	private int portNum = 8081;
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	//Added Message object variable to send
	private Message mess = new Message("add");
	
	//Added FishStick Object to be transfererred to server via Message object
	private FishStick fs = new FishStick();
	

	public static void main(String[] args) {
		switch (args.length){
		case 2:
			(new FishStickClient(args[1],Integer.parseInt(args[2]))).runClient();
			break;
		case 1:
			(new FishStickClient("localhost",Integer.parseInt(args[1]))).runClient();
			break;
		default:
			(new FishStickClient("localhost",8081)).runClient();
		}

	}
	public FishStickClient(String serverName, int portNum){
		this.serverName = serverName;
		this.portNum = portNum;
	}
	public void runClient(){
		String myHostName = null;
		try {
			InetAddress myHost = Inet4Address.getLocalHost();
			myHostName = myHost.getHostName();
		} catch (UnknownHostException e1) {
		    	System.out.println("Communications link failure");
			//e1.printStackTrace();
		}
		try {
			connection = new Socket(InetAddress.getByName(serverName), portNum);
			output = new ObjectOutputStream (connection.getOutputStream());
			input = new ObjectInputStream( connection.getInputStream());               
			do {
				
				
				
				
				switch(mess.getCommand()){
				
				case "disconnect":	mess = null;
									break;
				
				case "command_worked":	fs = mess.getFishStick();
										System.out.println();
										System.out.println("Command: success Returned FishStick: "+fs.toString());
										System.out.println("Do you want to insert another fish stick?(y or n):");
										if(br.readLine().equals("y")){
											mess.setCommand("add");
										}else{
											mess.setCommand("disconnect");
											System.out.println("Shutting down connection to server");
											break;
										}
										
				case "add": 	System.out.print("Enter data for new FishStick:\n");
								System.out.print("Please enter record number: ");
								fs.setRecordNumber(Integer.parseInt(br.readLine()));
								System.out.print("Please enter omega: ");
								fs.setOmega(br.readLine());
								System.out.print("Please enter lambda: ");
								fs.setLambda(br.readLine());
								fs.setUUID(UUID.randomUUID().toString());
								mess.setFishStick(fs);
								break;
										
				case "command_failed":	System.out.println("Insert did not succeed or similar");
										mess.setCommand("disconnect");
										break;
				}// end of switch
				output.writeObject(mess);
				output.flush();
				mess = (Message) input.readObject();
				System.out.println(message);
			} while (mess != null);
		} catch (IOException exception) {
			System.out.println("Server failed to preform requested operation");
			exception.printStackTrace();
		} catch (ClassNotFoundException exception) {
			System.out.println(exception.getMessage());
			exception.printStackTrace();
		} finally {
			try{if(input != null){input.close();}}catch(IOException ex){
				System.out.println(ex.getMessage());}
			try{if(output != null){output.flush(); output.close();}}catch(IOException ex){
				System.out.println(ex.getMessage());}
			try{if(connection != null){
			    System.out.println("Shutting down Connection to Server");
			    connection.close();
			}}catch(IOException ex){
				System.out.println(ex.getMessage());}
		}
	}
	
	public UUID generateUUID(){
		return UUID.randomUUID();
	}
	
    /* Your code here */

}
