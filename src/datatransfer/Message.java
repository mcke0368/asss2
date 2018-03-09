/*
 * File: Message.java
 * Author: Joel Schmuland and Jordan Mckenzie, based off of class example by Stanley Pieda.
 * Date: Feb, 2018
 * Description:  
 *
*/
package datatransfer;

import java.io.Serializable;


/**
 * The Class Message.
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
	 *
	 * @param command the command
	 */
	public Message(String command){
		this.command = command;
	}
	
	/**
	 * Instantiates a new message.
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
	 *
	 * @return the command
	 */
	public String getCommand(){
		return command;
	}
	
	/**
	 * Sets the command.
	 *
	 * @param command the new command
	 */
	public void setCommand(String command){
		this.command = command;
	}
	
	/**
	 * Gets the fish stick.
	 *
	 * @return the fish stick
	 */
	public FishStick getFishStick(){
		return fishStick;
	}
	
	/**
	 * Sets the fish stick.
	 *
	 * @param fishStick the new fish stick
	 */
	public void setFishStick(FishStick fishStick){
		this.fishStick = fishStick;
	}
	
}
