package crud;

import java.applet.Applet;
import java.applet.AppletStub;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.List;
import java.awt.Panel;
import java.awt.Scrollbar;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.sun.org.apache.xerces.internal.impl.RevalidationHandler;



public class CrudApplication extends Applet implements ActionListener{
	
	TextField user,pass;
	Button login;
	Label userlabel,passlabel,header,head;
	Panel jp;
	CRUD h;
	LoginPanel lp;
	
	public void init(){
		
		setSize(1024,600);
		setLayout(new BorderLayout());
		jp=new Panel();
		
		FlowLayout fl=new FlowLayout(FlowLayout.CENTER);
		
		jp.setLayout(new FlowLayout(FlowLayout.CENTER));
		jp.setLayout(null);
		jp.setBounds(0,0,1024,600);
		
		header=new Label("WELCOME TO CRUD APPLICATION");
		header.setAlignment(Label.CENTER);
		header.setBackground(new Color(0xFFCC66));
		
		header.setFont(new Font("Helvetica",Font.BOLD,20));
		lp=new LoginPanel();
		
		
		head=lp.header;
		userlabel=lp.userlabel;
		user=lp.user;
		passlabel=lp.passlabel;
		pass=lp.pass;
		login=lp.login;
		
		login.addActionListener(this);
		
		
		jp.add(head);
		jp.add(userlabel);
		jp.add(user);
		jp.add(passlabel);
		jp.add(pass);
		jp.add(login);
		
		add("North",header);
		add("Center",jp);
		
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getActionCommand().equals("Login")){
			
			System.out.println("dfsdf");
			if(user.getText().equals("root") && pass.getText().equals("root")){
				header.setText("WELCOME TO CRUD APPLICATION");
				jp.removeAll();
				jp.setLayout(new BorderLayout());
				h=new CRUD();
				h.logoutButton.addActionListener(this);
				jp.add(h.topPanel,BorderLayout.NORTH);
				jp.add(h.centerPanel,BorderLayout.CENTER);
				revalidate();
			}
			else{
				JOptionPane.showMessageDialog(null, "Invalid Login");
				user.setText("");
				pass.setText("");
			}
			
			
		}
		else if(arg0.getActionCommand().equals("Logout")){
			jp.removeAll();
			jp.setLayout(null);
			jp.setBounds(0,0,1024,600);
			user.setText("");
			pass.setText("");
			jp.add(head);
			jp.add(userlabel);
			jp.add(user);
			jp.add(passlabel);
			jp.add(pass);
			jp.add(login);
			revalidate();
		}
		
		
		
	}

}