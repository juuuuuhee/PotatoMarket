package controller;

import controller.action.JoinAction;

public class ActionFactory {
	private ActionFactory() {} ; 
	private static ActionFactory instance = new ActionFactory();
	public static ActionFactory getInstance() {
		return instance;
	}
	
	public Action getAction(String command) {
		Action action = null;
		
		if(command.equals("join")) action = new JoinAction();
		
		return action;
	}
	
	
}
