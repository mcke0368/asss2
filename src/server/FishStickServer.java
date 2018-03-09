package server;
/* File: FishStickServer.java
 * Author: Stanley Pieda, based on course materials by Todd Kelley
 * Modified Date:Jan 2018
 * Description: Networking server that uses simple protocol to send and receive transfer objects.
 */
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.ServerSocket;
import java.net.SocketAddress;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import datatransfer.FishStick;
import datatransfer.Message;

import dataaccesslayer.FishStickDao;
import dataaccesslayer.FishStickDaoImpl;

/**
 * Need programming comments with correct author name throughout this class
 * @author xyz abc
 */
public class FishStickServer {

	private ServerSocket server;
	private Socket connection;
	private int messagenum;
	private int portNum = 8081;
	public static ExecutorService threadExecutor = Executors.newCachedThreadPool();
	
	//Added DAOIMPL to insert fishstick
	FishStickDaoImpl daoImpl = new FishStickDaoImpl();

	public static void main(String[] args) {
		if(args.length > 0){
			(new FishStickServer(Integer.parseInt(args[0]))).runServer();
		}else{
			(new FishStickServer(8081)).runServer();
		}
	}
	public FishStickServer(int portNum){
		this.portNum = portNum;
	}
	public void talkToClient(final Socket connection){
		threadExecutor.execute( new Runnable () {
			public void run(){	
				ObjectOutputStream output = null;
				ObjectInputStream input = null;
				Message mess;
				try {
					SocketAddress remoteAddress = connection.getRemoteSocketAddress();
					String remote = remoteAddress.toString();
					System.out.println("Got a connection to: " + remote);
					output = new ObjectOutputStream (connection.getOutputStream());
					input = new ObjectInputStream( connection.getInputStream());               
					do {
						mess = (Message) input.readObject();
						System.out.print("From: " + remote + " Command: ");
						
						switch(mess.getCommand()){
						case "add": 	FishStick temp = new FishStick();
										/*
										 * insert Fish stick into DB and send back comand_worked with
										 * Fish Stick to client
										 */
										daoImpl.insertFishStick(mess.getFishStick()); //Insert
										temp = daoImpl.findByRecordNumber(mess.getFishStick().getRecordNumber()+"");
										System.out.println("add FishStick: " + temp.getId() + ", " + temp.getRecordNumber() + ", " + temp.getOmega() + ", " + temp.getLambda() + ", " + temp.getUUID());
										temp = daoImpl.findByUUID(temp.getUUID());
										mess.setFishStick(temp);
										mess.setCommand("command_worked");
										break;
										
						case "disconnect":	System.out.println("disconnect FishStick: null");
            								mess = null;
									break;
						}

						//message = (String) input.readObject();
						
						/**
						 * Unneeded code
						 */
						//System.out.println("From:" + remote + ">"+message);
//						if(message == null || message.isEmpty()) {
//							message = null;
//						}
//						else {
//							message = messagenum++ + " Output> " + message;
//						}
						output.writeObject(mess);
						output.flush();
					} while (mess != null);
					System.out.println(remote + " disconnected via request");
		        } catch (IOException exception) {
		            System.out.println("Communications link failure");
//		            System.err.println(exception.getMessage());
//		            exception.printStackTrace();
		        }catch (ClassNotFoundException exception) {
		            System.out.println(exception.getMessage());
		            exception.printStackTrace();
		        } catch (SQLException e) {
		            System.out.println("Communications link failure");
					// TODO Auto-generated catch block
					//e.printStackTrace();
				} 
			finally {
				try{if(input != null){input.close();}}catch(IOException ex){
					System.out.println(ex.getMessage());}
				try{if(output != null){output.flush(); output.close();}}catch(IOException ex){
					System.out.println(ex.getMessage());}
				try{if(connection != null){connection.close();}}catch(IOException ex){
					System.out.println(ex.getMessage());}
		        }
			}
		});
	}
	public void runServer(){
		try {
			server = new ServerSocket(portNum);
		}catch (IOException e){
			e.printStackTrace();
		}
		System.out.println("Listenting for connections...");
		while(true){
			try{
				connection = server.accept();
				talkToClient(connection);
			} catch (IOException exception) {
				exception.printStackTrace();
			}
		}
	}
	
}
