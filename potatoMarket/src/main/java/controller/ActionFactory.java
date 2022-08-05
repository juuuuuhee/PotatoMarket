package controller;

import controller.action.AddfavoriteAction;
import controller.action.IntoChatRoom;
import controller.action.JoinAction;
import controller.action.LoginAction;
import controller.action.LogoutAction;
import controller.action.UpdatedataAction;
import controller.action.delfavoriteAction;

public class ActionFactory {
	private ActionFactory() {} ; 
	private static ActionFactory instance = new ActionFactory();
	public static ActionFactory getInstance() {
		return instance;
	}
	
	public Action getAction(String command) {
		Action action = null;
		
		if(command.equals("join")) action = new JoinAction();
		else if(command.equals("login")) action = new LoginAction();
		else if(command.equals("logout"))action = new LogoutAction();
		else if(command.equals("intoChatRoom"))action = new IntoChatRoom();
		else if(command.equals("update"))action = new UpdatedataAction();
		else if(command.equals("addFavo"))action = new AddfavoriteAction();
		else if(command.equals("delfavo"))action = new delfavoriteAction();
		return action;
	}
	
	
}
