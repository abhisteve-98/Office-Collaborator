package Office.Collaborator;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class AdminLogin extends JFrame implements ActionListener {
    JLabel nameLbl,passLbl;
    JTextField name;
    JPasswordField pass;
    JButton loginButton;
    JPanel panel;

    public AdminLogin() {

        setSize(250,250);

        nameLbl=new JLabel("User Name: ");
        name=new JTextField();
        name.setColumns(20);
        passLbl=new JLabel("Password: ");
        pass=new JPasswordField(20);
        loginButton=new JButton("Login");
        panel=new JPanel(new FlowLayout());
        panel.add(nameLbl);
        panel.add(name);
        panel.add(passLbl);
        panel.add(pass);
        panel.add(loginButton);
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setBorder(new LineBorder(Color.BLACK));
        panel.setBounds(10,10,10,10);
        add(panel,BorderLayout.CENTER);
        setTitle("Login Form");

        loginButton.addActionListener(this);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AdminLogin().setVisible(true);
            }
        });


    }
    public void actionPerformed(ActionEvent login){
        String userName=name.getText();
        String password=pass.getText();
        String url="jdbc:mysql://127.0.0.1:3307/officedatabase";
        if(userName.equals("root") && password.equals("steve@98")) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection(url,userName,password);
                JLabel optionsLbl=new JLabel("Select Any One Option: ");
                JLabel insertLbl=new JLabel("Select this to Insert employee ");
                JButton insertButton =new JButton("Insert Employee");
                JLabel updateLbl=new JLabel("Select this to Update employee ");
                JButton updateButton=new JButton("Update Employee");
                JLabel deleteLbl=new JLabel("Select this to Delete employee ");
                JButton deleteButton=new JButton("Delete Employee");
                JPanel optionPanel=new JPanel(new GridLayout(8,1));
                optionPanel.add(optionsLbl);
                optionPanel.add(updateLbl);
                optionPanel.add(updateButton);
                optionPanel.add(insertLbl);
                optionPanel.add(insertButton);
                optionPanel.add(deleteLbl);
                optionPanel.add(deleteButton);
                JFrame optionFrame=new JFrame();
                optionFrame.setSize(350,350);
                optionFrame.add(optionPanel,BorderLayout.CENTER);
                optionFrame.setTitle("Options");
                optionFrame.setVisible(true);

                insertButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        JLabel detailDialog=new JLabel("Enter the details of the employee");
                        detailDialog.setFont(new Font("Serif",Font.BOLD,18));
                        JLabel namelbl= new JLabel("Name: ");
                        JTextField name= new JTextField(20);
                        JLabel emaillbl=new JLabel("Email: ");
                        JTextField email=new JTextField(20);
                        JLabel passlbl=new JLabel("Password: ");
                        JTextField password=new JTextField(20);
                        JLabel projectlbl=new JLabel("Allocated Project: ");
                        JTextField project=new JTextField(20);
                        JLabel addresslbl=new JLabel("Address: ");
                        JTextField address=new JTextField(30);
                        JLabel skillslbl=new JLabel("Skills: ");
                        JTextField skills=new JTextField(20);
                        JLabel phonelbl=new JLabel("Phone Number: ");
                        JTextField phone=new JTextField(10);
                        JLabel reportslbl=new JLabel("Reporting Manager: ");
                        JTextField reportsTo=new JTextField(20);
                        JLabel bandlbl=new JLabel("Band: ");
                        JTextField band=new JTextField(10);
                        JButton insert=new JButton("Insert");
                        JPanel insertPanel=new JPanel(new GridLayout(23,1));
                        insertPanel.add(detailDialog);
                        insertPanel.add(namelbl);
                        insertPanel.add(name);
                        insertPanel.add(emaillbl);
                        insertPanel.add(email);
                        insertPanel.add(passlbl);
                        insertPanel.add(password);
                        insertPanel.add(projectlbl);
                        insertPanel.add(project);
                        insertPanel.add(addresslbl);
                        insertPanel.add(address);
                        insertPanel.add(skillslbl);
                        insertPanel.add(skills);
                        insertPanel.add(phonelbl);
                        insertPanel.add(phone);
                        insertPanel.add(reportslbl);
                        insertPanel.add(reportsTo);
                        insertPanel.add(bandlbl);
                        insertPanel.add(band);
                        insertPanel.add(insert);
                        insertPanel.setVisible(true);
                        JFrame insertFrame=new JFrame();
                        insertFrame.setSize(350,500);
                        insertFrame.add(insertPanel,BorderLayout.CENTER);
                        insertFrame.setTitle("Insert Menu");
                        insertFrame.setVisible(true);

                        insert.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                String Name=name.getText();
                                String Email=email.getText();
                                String Password=password.getText();
                                String Project=project.getText();
                                String Address=address.getText();
                                String Skill= skills.getText();
                                String PhoneNo=phone.getText();
                                String reportingManager=reportsTo.getText();
                                String Band=band.getText();
                                String insertQuery="INSERT INTO employees VALUES(?,?,?,?,?,?,?,?,?)";

                                try {
                                    PreparedStatement insStatement = con.prepareStatement(insertQuery);
                                    insStatement.setString(1,Name);
                                    insStatement.setString(2,Password);
                                    insStatement.setString(3,Email);
                                    insStatement.setString(4,Project);
                                    insStatement.setString(5,Address);
                                    insStatement.setString(6,Skill);
                                    insStatement.setString(7,PhoneNo);
                                    insStatement.setString(8,reportingManager);
                                    insStatement.setString(9,Band);
                                    int count=insStatement.executeUpdate();
                                    if(count>0) {
                                        JFrame success = new JFrame();
                                        success.setSize(200, 200);
                                        JOptionPane.showMessageDialog(success, "Successfully added" + count + "rows"
                                                , "Insert Successful", JOptionPane.INFORMATION_MESSAGE);
                                        success.setTitle("Successful");
                                    }
                                    else{
                                        JFrame insertfailureFrame=new JFrame();
                                        JOptionPane.showMessageDialog(insertfailureFrame,"There Was An Error In Insertion! ","Failure",JOptionPane.WARNING_MESSAGE);
                                        insertfailureFrame.setTitle("Failure");
                                    }
                                    insStatement.close();
                                    con.close();


                                }
                                catch (Exception es){
                                    JFrame failureFrame=new JFrame();
                                    JOptionPane.showMessageDialog(failureFrame,es,"Error",JOptionPane.WARNING_MESSAGE);
                                    failureFrame.setVisible(true);
                                }
                            }
                        });
                    }
                });
                updateButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JLabel heading=new JLabel("Enter the details of the employee you want to update ");
                        JLabel namelbl=new JLabel("Enter Name of Employee");
                        JTextField Name = new JTextField(20);
                        JLabel newPasslbl=new JLabel("Enter new Password");
                        JTextField newPass=new JTextField(20);
                        JLabel newProjectlbl=new JLabel("Enter new Allocated Project");
                        JTextField newProject=new JTextField(20);
                        JLabel newAddresslbl=new JLabel("Enter new Address");
                        JTextField newAddress=new JTextField(20);
                        JLabel newReportingManagerlbl=new JLabel("Enter new Reporting Manager Name");
                        JTextField newReportingManager=new JTextField(20);
                        JLabel newBandlbl=new JLabel("Enter new Band");
                        JTextField newBand=new JTextField(20);
                        JButton Update=new JButton("Update");
                        JLabel notelbl=new JLabel("Note: Enter previous entry if no update in any attribute!");
                        JPanel updatePanel=new JPanel(new GridLayout(15,1));
                        updatePanel.add(heading);
                        updatePanel.add(namelbl);
                        updatePanel.add(Name);
                        updatePanel.add(newPasslbl);
                        updatePanel.add(newPass);
                        updatePanel.add(newProjectlbl);
                        updatePanel.add(newProject);
                        updatePanel.add(newAddresslbl);
                        updatePanel.add(newAddress);
                        updatePanel.add(newReportingManagerlbl);
                        updatePanel.add(newReportingManager);
                        updatePanel.add(newBandlbl);
                        updatePanel.add(newBand);
                        updatePanel.add(Update);
                        updatePanel.add(notelbl);
                        JFrame updateFrame=new JFrame();
                        updateFrame.setSize(350,500);
                        updateFrame.add(updatePanel,BorderLayout.CENTER);
                        updateFrame.setTitle("Update Menu");
                        updateFrame.setVisible(true);

                        Update.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                String empName=Name.getText();
                                String pass=newPass.getText();
                                String project=newProject.getText();
                                String address=newAddress.getText();
                                String reportingManager=newReportingManager.getText();
                                String band=newBand.getText();
                                String updateQuery="UPDATE employees SET Password=?,Allocated_Project=?,Address=?,Reporting_Manager=?,Band=? WHERE Name REGEXP ? ;";
                                try{
                                    PreparedStatement updateStatement=con.prepareStatement(updateQuery);
                                    updateStatement.setString(1,pass);
                                    updateStatement.setString(2,project);
                                    updateStatement.setString(3,address);
                                    updateStatement.setString(4,reportingManager);
                                    updateStatement.setString(5,band);
                                    updateStatement.setString(6,empName);
                                    int successCount=updateStatement.executeUpdate();
                                    if(successCount>0) {
                                        JFrame successFrame = new JFrame();
                                        JOptionPane.showMessageDialog(successFrame, "Successfully updated " + successCount + "row/s", "Successfully Updated",
                                                JOptionPane.INFORMATION_MESSAGE);
                                        successFrame.setTitle("Successful");
                                    }
                                    else{
                                        JFrame failureFrame=new JFrame();
                                        JOptionPane.showMessageDialog(failureFrame,"There Was An Error In Updation! ","Failure",JOptionPane.WARNING_MESSAGE);
                                        failureFrame.setTitle("Failure");

                                    }
                                    updateStatement.close();
                                    con.close();
                                }
                                catch (Exception exc){
                                    exc.printStackTrace();
                                }
                            }
                        });

                    }
                });
                deleteButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JLabel delEmplbl=new JLabel("Enter the Name of the employee you want to delete");
                        JTextField delEmp=new JTextField(20);
                        JButton del=new JButton("Delete");
                        JPanel deletePanel=new JPanel(new GridLayout(4,1));
                        deletePanel.add(delEmplbl);
                        deletePanel.add(delEmp);
                        deletePanel.add(del);
                        JFrame deleteFrame=new JFrame();
                        deleteFrame.setSize(250,250);
                        deleteFrame.add(deletePanel,BorderLayout.CENTER);
                        deleteFrame.setTitle("Delete Menu");
                        deleteFrame.setVisible(true);
                        del.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                String empName=delEmp.getText();
                                String deleteQuery="DELETE FROM employees WHERE Name REGEXP ?";
                                try{
                                    PreparedStatement deleteStatement=con.prepareStatement(deleteQuery);
                                    deleteStatement.setString(1,empName);
                                    int deleteCount=deleteStatement.executeUpdate();
                                    if(deleteCount>0){
                                        JFrame deleteSuccess=new JFrame();
                                        JOptionPane.showMessageDialog(deleteSuccess,"Deletion Successful","Delete Successful",JOptionPane.INFORMATION_MESSAGE);
                                        deleteSuccess.setTitle("Successful");
                                    }
                                    else{
                                        JFrame deleteFailure=new JFrame();
                                        JOptionPane.showMessageDialog(deleteFailure,"Deletion Failed","Deletion Failure",JOptionPane.WARNING_MESSAGE);
                                        deleteFailure.setTitle("Failure");
                                    }
                                    deleteStatement.close();
                                    con.close();

                                }
                                catch (Exception delExc){
                                    delExc.printStackTrace();
                                }
                            }
                        });
                    }
                });

            }
            catch (Exception e){
                JFrame exceptionFrame=new JFrame();
                JOptionPane.showMessageDialog(exceptionFrame,e,"Error",JOptionPane.WARNING_MESSAGE);
                exceptionFrame.setTitle("Error");
            }
        }
        else{
            JFrame error_window= new JFrame();
            JOptionPane.showMessageDialog(error_window,"Incorrect User Name or Password",
                    "Warning",JOptionPane.WARNING_MESSAGE);
            error_window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
    }
}
