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
 * 
 * @author Joel Schmuland and Jordan Mckenzie
 */
public class FishStickServer {

	private ServerSocket server;
	private Socket connection;
	private int messagenum;
	private int portNum = 8081;
	public static ExecutorService threadExecutor = Executors.newCachedThreadPool();

	// Added DAOIMPL to insert fishstick
	FishStickDaoImpl daoImpl = new FishStickDaoImpl();

	public static void main(String[] args) {
		
		// Checks for argument that sets the port # for server to run at
		if (args.length > 0) {
			(new FishStickServer(Integer.parseInt(args[0]))).runServer();
		} else {
			(new FishStickServer(8081)).runServer();
		}
	}

	/**
	 * Constructor that sets the port number on which the server will run
	 * @param portNum The port number on which the server will run
	 */
	public FishStickServer(int portNum) {
		this.portNum = portNum;
	}

	/**
	 * Sets up the server to receive FishStick data and insert it into the database
	 * as well as send back message objects to client
	 * @param connection The socket connection of the server
	 */
	public void talkToClient(final Socket connection) {
		
		// Run new thread for each client that connects
		threadExecutor.execute(new Runnable() {
			public void run() {
				ObjectOutputStream output = null; // The ouptut stream for the server
				ObjectInputStream input = null; // The input stream for the server
				Message mess; // Message object to recieve and transfer the data between client and server
				
				try {
					// receive connection from client and print out details
					SocketAddress remoteAddress = connection.getRemoteSocketAddress();
					String remote = remoteAddress.toString();
					System.out.println("Got a connection to: " + remote);
					
					// Set up streams to and from client
					output = new ObjectOutputStream(connection.getOutputStream());
					input = new ObjectInputStream(connection.getInputStream());
					
					do {
						// Set message object to data from client
						mess = (Message) input.readObject();
						System.out.print("From: " + remote + " Command: ");

						// Based on command from message object, execute code
						switch (mess.getCommand()) {

						// Insert FishStick object into the database and set new message command
						case "add":
							FishStick temp = new FishStick();
							daoImpl.insertFishStick(mess.getFishStick()); // Insert FishStick into Database
							temp = daoImpl.findByRecordNumber(mess.getFishStick().getRecordNumber() + ""); // Retreive FishStick from database based on record number
							System.out.println("add FishStick: " + temp.getId() + ", " + temp.getRecordNumber() + ", "
									+ temp.getOmega() + ", " + temp.getLambda() + ", " + temp.getUUID()); // Print out FishStick data to console
							temp = daoImpl.findByUUID(temp.getUUID()); // Get UUID from FishStick
							mess.setFishStick(temp); // Set Fishstick with the UUID to message object
							mess.setCommand("command_worked"); // successful command
							break; // break from loop

							// Send back disconnect message to client for disconnection
						case "disconnect":
							System.out.println("disconnect FishStick: null");
							mess = null;
							break;
						}

						// Write the message object to ouput stream and flush it
						output.writeObject(mess);
						output.flush();
					} while (mess != null); // End loop if message object is null
					System.out.println(remote + " disconnected via request");
					
					// Catch errors and print out corresponding messages
				} catch (IOException exception) {
					System.out.println("Communications link failure");
				} catch (ClassNotFoundException exception) {
					System.out.println(exception.getMessage());
					exception.printStackTrace();
				} catch (SQLException e) {
					System.out.println("Communications link failure");
					
					// Close all resources
				} finally {
					try {
						if (input != null) {
							input.close(); // Close input stream
						}
					} catch (IOException ex) {
						System.out.println(ex.getMessage());
					}
					try {
						if (output != null) { // Flush and close output stream
							output.flush();
							output.close();
						}
					} catch (IOException ex) {
						System.out.println(ex.getMessage());
					}
					try {
						if (connection != null) { // Close the connection to client
							connection.close();
						}
					} catch (IOException ex) {
						System.out.println(ex.getMessage());
					}// end try catch
				}//end finally
			}// end run
		});
	}// talk to client

	/**
	 * Set up a server socket that listens for connection on specified port
	 */
	public void runServer() {
		try {
			server = new ServerSocket(portNum);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Listenting for connections...");
		while (true) { // Loop while listening for connections
			try {
				connection = server.accept();
				talkToClient(connection); // When connection comes in, call talktoclient method
			} catch (IOException exception) {
				exception.printStackTrace();
			}
		}// end while
	}// end runServer
}// end of FishSickServer Class
