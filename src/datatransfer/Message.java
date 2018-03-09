package datatransfer;

import java.io.Serializable;

/**
 * Need programming comments with correct author name throughout this class
 * @author xyz abc
 */
public class Message implements Serializable{

	private long serialVersionUID;
	private String command;
	private FishStick fishStick;
	
	public Message(String command){
		this.command = command;
	}
	
	public Message(String command, FishStick fishStick){
		this.command = command;
		this.fishStick = fishStick;
	}
	
	public String getCommand(){
		return command;
	}
	
	public void setCommand(String command){
		this.command = command;
	}
	
	public FishStick getFishStick(){
		return fishStick;
	}
	
	public void setFishStick(FishStick fishStick){
		this.fishStick = fishStick;
	}
	
}
