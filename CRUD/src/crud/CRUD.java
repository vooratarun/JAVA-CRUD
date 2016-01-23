package crud;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class CRUD extends Panel implements ActionListener{
	boolean loginStatus=false;
	String idno, name, gen,year, branch, ph,email;
	Button homeButton,viewButton,registerButton,logoutButton,searchButton, deleteRowButton,editRowButton,regSubmitButton,regClearButton;
	Label appNameLabel,idLable,nameLable,genLable,yearLable,branchLable,phLable,emailLable;
	TextField idField=null,nameField=null,phField=null,emailField=null,searchField=null;
	Choice genField,yearField,branchField;
	
	Panel topPanel,centerPanel,subCenterPanel1,subCenterPanel2, editPanel,buttonPanel,
			regLabelsPanel,regFieldsPanel,regButtonsPanel;
	
	Statement stmt;
	ResultSet rs=null;
	
	Object data[][]=null;
	
	private final JScrollPane scrollPane = new JScrollPane();
	private JTable table = new JTable()
	{
	    public boolean isCellEditable(int rowIndex, int colIndex) 
	    {
	        return false;
	    }
	};
	final Class[] columnClass = new Class[] {
           Integer.class, String.class, String.class,String.class,String.class,String.class,String.class,String.class,Button.class
        };
 
	DefaultTableModel model = new DefaultTableModel() {
		 
        @Override
        public boolean isCellEditable(int row, int column)
        {
            return false;
        }

        @Override
        public Class<?> getColumnClass(int columnIndex)
        {
            return columnClass[columnIndex];
        }
    };
    
	public CRUD(){
		setSize(900,600);		
		CreateJDBCConnection cd=new CreateJDBCConnection();
		stmt = (Statement) cd.getStatement();
		
		setLayout(new BorderLayout());
		topPanel = new Panel(new FlowLayout(FlowLayout.TRAILING));
		centerPanel = new Panel();
		//centerPanel.setBackground(new Color(0xFFFF99))
		
		topPanel.setBackground(new Color(0xFFCC66));
		centerPanel.setBackground(new Color(0xFFFF99));
		topPanel.setFont(new Font("Serif", Font.BOLD, 15));
		centerPanel.setFont(new Font("Serif", Font.BOLD, 15));
		
		add(topPanel, BorderLayout.NORTH);
		add(centerPanel, BorderLayout.CENTER);
		
		homeButton=new Button("Home");
		registerButton=new Button("Register");
		viewButton=new Button("View");
		logoutButton =new Button("Logout");
		
		appNameLabel=new Label("",Label.LEFT);
		appNameLabel.setFont(new Font("Serif", Font.BOLD, 18));
		
		
		
		
		//topPanel.add(homeButton);
		topPanel.add(registerButton);
		topPanel.add(viewButton);
		topPanel.add(logoutButton);
		logoutButton.addActionListener(this);
		//topPanel.add(new Label("CRUD"));
		
		homeButton.addActionListener(this);
		registerButton.addActionListener(this);
		viewButton.addActionListener(this);
		homeGUI();
		
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub				
		String str=ae.getActionCommand();
		if(ae.getSource()==homeButton){
			centerPanel.removeAll();
			homeGUI();
			revalidate();
		}
		else if(ae.getSource()==registerButton){
			
			centerPanel.removeAll();
			registerGUI();
			centerPanel.revalidate();
			
		}
		else if(ae.getSource()==viewButton)
		{
			centerPanel.removeAll();
			viewGUI();	
			centerPanel.revalidate();
		}
		else if(ae.getSource()==regSubmitButton){
			newRegistration();			
		}
		else if(ae.getSource()==regClearButton){
			clearAllFields();
		}
		else if(ae.getSource()==searchButton){			
			search(searchField.getText());
		}
		else if(str.equals("Delete Selected Row")){
			editPanel.removeAll();
			int index=table.getSelectedRow();
			if(index !=-1){
				
				
				String selectedIdNo=(String) data[index][1];
			    System.out.print("selected row "+table.getSelectedRow()+" "+selectedIdNo);
			    deleteSelectedRow(index,selectedIdNo);
			}
			else{
				editPanel.add(new Label("Please select a Row to Delete"));
				JOptionPane.showMessageDialog(null, "Please select a Row to Delete");
				editPanel.validate();
			}
		}
		else if(str.equals("Edit Selected Row")){
			editPanel.removeAll();
			int index=table.getSelectedRow();
			regLabelsFieldsGUI();
			editPanelGUI(index);
			editPanel.revalidate();
		}
		else if(str.equals("Update Selected Row")){
			updateSelectedRow();
		}
		revalidate();
		repaint();
	}
	

	private void registerGUI() {
		// TODO Auto-generated method stub
		centerPanel.setLayout(new FlowLayout());
		regLabelsPanel = new Panel(new GridLayout(0,1,15,15));
        regFieldsPanel = new Panel(new GridLayout(0,1,15,15));
        regButtonsPanel = new Panel(new GridLayout(1,1,1,1));
               
        regLabelsFieldsGUI();
        regSubmitButton=new Button("Submit");
		regClearButton=new Button("Clear");
		regLabelsPanel.add(idLable);regFieldsPanel.add(idField );
		regLabelsPanel.add(nameLable );regFieldsPanel.add(nameField );
		regLabelsPanel.add(genLable );regFieldsPanel.add(genField );
		regLabelsPanel.add(yearLable );regFieldsPanel.add(yearField );
		regLabelsPanel.add(branchLable );regFieldsPanel.add(branchField );
		regLabelsPanel.add(phLable );regFieldsPanel.add(phField );
		regLabelsPanel.add(emailLable );regFieldsPanel.add(emailField );
		regLabelsPanel.add(regClearButton);regFieldsPanel.add(regSubmitButton);
		centerPanel.add(regLabelsPanel, BorderLayout.CENTER);
        centerPanel.add(regFieldsPanel, BorderLayout.CENTER);
		centerPanel.add(regButtonsPanel);
		System.out.print("hello"+idField.getText());
		
		regSubmitButton.addActionListener(this);
		regClearButton.addActionListener(this);
		
	}

	private void regLabelsFieldsGUI() {
		// TODO Auto-generated method stub
		idLable=new Label("ID NO :");
		 idField = new TextField(20);
		 nameLable=new Label("NAME :");
		 nameField = new TextField(20);
		 genLable=new Label("GENDER :");
		 genField = new Choice();
		 genField.add("<select>");
		 genField.add("Male");
		 genField.add("Female");
		 yearLable=new Label("YEAR :");
		 yearField = new Choice();
		 yearField.add("<select>");
		 yearField.add("PUC 1"); yearField.add("PUC 2");yearField.add("ENGG 1");
		 yearField.add("ENGG 2");yearField.add("ENGG 3");yearField.add("ENGG 4");
		 branchLable=new Label("BRANCH :");
		 branchField = new Choice();
		 branchField.add("<select>");
		 branchField.add("CSE");branchField.add("ECE");
		 branchField.add("MECH");branchField.add("CIVIL");branchField.add("MME");
		 branchField.add("CHEMICAL");branchField.add("Not Applicable");
		 phLable=new Label("PH NO :");
		 phField = new TextField(20);
		 emailLable=new Label("EMAIL :");
		 emailField = new TextField(20);
		
	}

	private void viewGUI() {
		// TODO Auto-generated method stub
		search("none");
		
		
		
		regLabelsFieldsGUI();
		centerPanel.setLayout(new BorderLayout());
		//subCenterPanel1=new Panel(new FlowLayout(FlowLayout.LEFT));
		subCenterPanel1=new Panel(new FlowLayout(FlowLayout.TRAILING));
		subCenterPanel2=new Panel();
		
		
		
		searchField=new TextField("Id/Name/Year/Branch/Gen",20);
		searchButton=new Button("Search");
		
		
		//subCenterPanel1.add(homeButton);
		//subCenterPanel1.add(registerButton);
		//subCenterPanel1.add(viewButton);
		
		subCenterPanel1.add(searchField);
		subCenterPanel1.add(searchButton);
		//subCenterPanel1.add(new Button("fdgd"));
		
		searchButton.addActionListener(this);
		centerPanel.add(subCenterPanel1, BorderLayout.NORTH);
		centerPanel.add(subCenterPanel2, BorderLayout.CENTER);
		tableViewGUI();
		
		subCenterPanel2.setLayout(new BorderLayout());		
		buttonPanel=new Panel();
		editPanel=new Panel();
		editPanel.setBackground(new Color(0xFFCC66));
		buttonPanel.setBackground(Color.lightGray);
		
		subCenterPanel2.add(scrollPane,BorderLayout.PAGE_START);
		subCenterPanel2.add(editPanel,BorderLayout.CENTER);
		
		
		
		subCenterPanel2.add(buttonPanel,BorderLayout.SOUTH);
	    				 
	    buttonPanelGUI();		
	}

	private void buttonPanelGUI() {
		// TODO Auto-generated method stub
		deleteRowButton=new Button("Delete Selected Row");
	    editRowButton=new Button("Edit Selected Row");
	    buttonPanel.add(editRowButton);
	    buttonPanel.add(deleteRowButton);
	    buttonPanel.setBackground(new Color(0xFFFF99));
	    
	    deleteRowButton.addActionListener(this);
	    editRowButton.addActionListener(this);
	    revalidate();
	}

	private void tableViewGUI() {
		// TODO Auto-generated method stub
		Object columnNames[]={"S No","Unversity ID","Name","Gender","Year","Branch","Phone Number","Email"};
		//table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    //table.setShowGrid(true);	    
	    //model.setColumnIdentifiers(columnNames);
	    //table.setModel(model);
	    table=new JTable(data,columnNames);
	    table.setBackground(new Color(0xFFCC66));
	    //scrollPane.setBackground(new Color(0xFFCC66));
	    scrollPane.setViewportView(table);
	    //JTableHeader header = table.getTableHeader();
	    //table.getTableHeader().setReorderingAllowed(false);
		//this.setVisible(true);	    
		
	}

	private void homeGUI() {
		// TODO Auto-generated method stub
		centerPanel.add(appNameLabel,BorderLayout.CENTER);
	}

	public void newRegistration(){
		idno=idField.getText();name=nameField.getText();gen=genField.getSelectedItem();branch=branchField.getSelectedItem();
		year=yearField.getSelectedItem();ph=phField.getText();email=emailField.getText();
		System.out.print("his "+idno+name+gen);
		if(idno!=" " && name!="" && gen!="" && branch!="" && year!="" && ph!="" && email!=""){
			try {
				stmt.executeUpdate("insert into student values('"+idno+"','"+name+"','"+gen+"','"+year+"','"+branch+"','"+ph+"','"+email+"')");
				clearAllFields();
				//JOptionPane.showMessageDialog(this, name+", You Successfully Registered. THANQ");
				JOptionPane.showMessageDialog(null, name+" successfully registrered");
				centerPanel.removeAll();
				
			} catch (SQLException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(this, e.getLocalizedMessage());
			}			
		}
		else{
			JOptionPane.showMessageDialog(this, "Please fill all fields");
		}
	}
	public void clearAllFields(){
		idField.setText("");nameField.setText("");yearField.setName("");branchField.setName("");genField.setName("Select");
		phField.setText("");emailField.setText("");
	}
	public void search(String key){				
		try {
			if(!key.equals("none"))
				rs=stmt.executeQuery("select * from student where idno='"+ key+"' or name='"+key+"' or gen='"+key+"' or year='"+key+"' or branch='"+key+"'");
			else
				rs=stmt.executeQuery("select * from student");
			int j=0;
			boolean b = rs.last();
            int numberOfRecords = 0;
            if(b){
                numberOfRecords = rs.getRow();
                rs.beforeFirst();
            }
            ResultSetMetaData md = rs.getMetaData();
            int columns = md.getColumnCount();
			data=new Object[numberOfRecords][columns+1];
			while(rs.next()){
				idno=rs.getString(1);name=rs.getString(2);gen=rs.getString(3);
				year=rs.getString(4);branch=rs.getString(5);ph=rs.getString(6);
				email=rs.getString(7);
				Object d[]={j+1,idno,name,gen,year,branch,ph,email};
				data[j++]=d;			
			}
			tableViewGUI();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//revalidate();	    
	}
	
	public void deleteSelectedRow(int rowIndex,String key){
		try {
			stmt.executeUpdate("delete from student where idno='"+key+"'");
			editPanel.add(new Label("With Id \" "+data[rowIndex][1]+" \" Row is deleted"));
			search("none");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				
	}
	
	private void updateSelectedRow() {
		try {
			String id=idField.getText();
		
			stmt.executeUpdate("update student set name='"+nameField.getText()+"',gen='"+genField.getSelectedItem()+
					"',year='"+yearField.getSelectedItem()+"',branch='"+branchField.getSelectedItem()+"',ph='"+phField.getText()+
					"',email='"+emailField.getText()+"' where idno='"+id+"'");
			search("none");
			editPanel.removeAll();
			editPanel.add(new Label("With id \" "+id+" \" row updated"));
			buttonPanel.removeAll();
			buttonPanelGUI();
			editPanel.revalidate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//editPanel.validate();
	}
	
	public void editPanelGUI(int editRowIndex){
		editPanel.removeAll();
		if(editRowIndex !=-1){
			String item=null;
			editPanel.add(idField);editPanel.add(nameField);editPanel.add(genField);editPanel.add(yearField);
			editPanel.add(branchField);editPanel.add(phField);editPanel.add(emailField);
			item=(String) data[editRowIndex][1];
			idField.setText(item); idField.setEnabled(false);
			item=(String) data[editRowIndex][2];
			nameField.setText(item);
			item=(String) data[editRowIndex][3];
			//genField.setText(item);
			item=(String) data[editRowIndex][4];
			//yearField.setText(item);
			item=(String) data[editRowIndex][5];
			//branchField.setText(item);
			item=(String) data[editRowIndex][6];
			phField.setText(item);
			item=(String) data[editRowIndex][7];
			emailField.setText(item);
			
			editRowButton.setLabel("Update Selected Row");
			editRowButton.setBackground(Color.orange);
			deleteRowButton.disable();
			editPanel.revalidate();
		}
		else{
			editPanel.add(new Label("Please select a Row to Edit"));
			JOptionPane.showMessageDialog(null, "Please select a Row to Edit");
		}
		return ;
	}
}
