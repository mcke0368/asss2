/*
 * File: Message.java
 * Author: Joel Schmuland and Jordan Mckenzie, based off of class example by Stanley Pieda.
 * Date: Feb, 2018
 * Description:  Message object that is transferred between client and server whcihc carries Fishstick and message data
 *
 */
package datatransfer;

import java.io.Serializable;


/**
 * The Class Message.
	 * @author Joel Schmuland and Jordan Mckenzie
 */
public class Message implements Serializable{

	/** The serial version UID. */
	private long serialVersionUID;

	/** The command. */
	private String command;

	/** The fish stick. */
	private FishStick fishStick;

	/**
	 * Instantiates a new message.
	 * @author Joel Schmuland and Jordan Mckenzie
	 *
	 * @param command the command
	 */
	public Message(String command){
		this.command = command;
	}

	/**
	 * Instantiates a new message.
	 * @author Joel Schmuland and Jordan Mckenzie
	 *
	 * @param command the command
	 * @param fishStick the fish stick
	 */
	public Message(String command, FishStick fishStick){
		this.command = command;
		this.fishStick = fishStick;
	}

	/**
	 * Gets the command.
	 * @author Joel Schmuland and Jordan Mckenzie
	 *
	 * @return the command
	 */
	public String getCommand(){
		return command;
	}

	/**
	 * Sets the command.
	 * @author Joel Schmuland and Jordan Mckenzie
	 *
	 * @param command the new command
	 */
	public void setCommand(String command){
		this.command = command;
	}

	/**
	 * Gets the fish stick.
	 * @author Joel Schmuland and Jordan Mckenzie
	 *
	 * @return the fish stick
	 */
	public FishStick getFishStick(){
		return fishStick;
	}

	/**
	 * Sets the fish stick.
	 * @author Joel Schmuland and Jordan Mckenzie
	 *
	 * @param fishStick the new fish stick
	 */
	public void setFishStick(FishStick fishStick){
		this.fishStick = fishStick;
	}

}
