package crud;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;

import javax.swing.JLabel;

public class LoginPanel{
	
	Panel jp;
	TextField user,pass;
	Button login;
	Label userlabel,passlabel,header;
	
	public LoginPanel(){
		jp=new Panel();
		jp.setLayout(null);
		jp.setBounds(0,0,1024,600);
		
		
		header=new Label("Admin Login!!");
		header.setFont(new Font("Helvetica",Font.BOLD,17));
		header.setBounds(450,10,200,30);
		userlabel=new Label("Username:");
		userlabel.setFont(new Font("Helvetica",Font.BOLD,15));
		userlabel.setBounds(390,40,100,30);
		
		passlabel=new Label("Password:");
		passlabel.setBounds(390,80,100,30);
		passlabel.setFont(new Font("Helvetica",Font.BOLD,15));
		
		
		user=new TextField(10);
		pass=new TextField(10);
		login=new Button("Login");
		
		user.setBounds(500,45,120,20);
		pass.setBounds(500,85,120,20);
		login.setBounds(390,115,230,25);
		
		pass.setEchoChar('*');
		
		jp.add(header);
		jp.add(userlabel);
		jp.add(user);
		jp.add(passlabel);
		jp.add(pass);
		jp.add(login);
	}

}
