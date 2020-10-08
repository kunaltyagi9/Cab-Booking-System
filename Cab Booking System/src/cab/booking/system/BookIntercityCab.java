/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cab.booking.system;


import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Random;
import javax.swing.*;

public class BookIntercityCab extends JFrame{  

    JTable table;
    JLabel ReservationDetails,Source,Destination;
    JButton show, b1, b2;
    Choice c1, c2;
    
    public static void main(String[] args){
        new BookIntercityCab("");
    }
    
    public BookIntercityCab(String username){
        
        setTitle("Book Cab");
	getContentPane().setBackground(Color.WHITE);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        ReservationDetails = new JLabel("BOOK INTERCITY CAB");
	ReservationDetails.setForeground(Color.BLUE);
	ReservationDetails.setFont(new Font("Tahoma", Font.PLAIN, 31));
	ReservationDetails.setBounds(280, 27, 359, 31);
	add(ReservationDetails);

	
	setLayout(null);
		
	Source = new JLabel("SOURCE");
	Source.setFont(new Font("Tahoma", Font.PLAIN, 19));
	Source.setBounds(60, 100, 70, 27);
	add(Source);
	
        Destination = new JLabel("DESTINATION");
	Destination.setFont(new Font("Tahoma", Font.PLAIN, 19));
	Destination.setBounds(350, 100, 150, 27);
	add(Destination);
		
	
	c1 = new Choice();
        c1.setBounds(150, 100, 150, 27);
        add(c1);
      	
	c2 = new Choice();
        c2.setBounds(500, 100, 150, 27);
        add(c2);
        
        JLabel l9 = new JLabel("Name : ");
	l9.setFont(new Font("Tahoma", Font.PLAIN, 19));
	l9.setBounds(50, 150, 250, 27);
	add(l9);
        
        JLabel l10 = new JLabel("");
	l10.setFont(new Font("Tahoma", Font.PLAIN, 19));
	l10.setBounds(200, 150, 150, 27);
	add(l10);
        
        JLabel l11 = new JLabel("Username : ");
	l11.setFont(new Font("Tahoma", Font.PLAIN, 19));
	l11.setBounds(50, 200, 150, 27);
	add(l11);
        
        JLabel l12 = new JLabel("");
	l12.setFont(new Font("Tahoma", Font.PLAIN, 19));
	l12.setBounds(200, 200, 350, 27);
	add(l12);
	
        
        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select DISTINCT source, destination from intercity");
            while(rs.next()){
                c1.add(rs.getString("source"));
                c2.add(rs.getString("destination"));
            }
            
            rs = c.s.executeQuery("select * from account where username = '"+username+"'");
            while(rs.next()){
                l10.setText(rs.getString("name"));
                l12.setText(username);
            }

            rs.close();
        }catch(SQLException e){}
        
        
        JLabel l1 = new JLabel("Type : ");
	l1.setFont(new Font("Tahoma", Font.PLAIN, 19));
	l1.setBounds(50, 250, 150, 27);
	add(l1);
        
        Choice c3 = new Choice();
        c3.add("Micro");
        c3.add("Mini");
        c3.add("SUV");
        c3.setBounds(200, 250, 150, 27);
        add(c3);
        
        
	show = new JButton("DETAILS");
        show.setBackground(Color.BLACK);
        show.setForeground(Color.WHITE);
	show.setBounds(370, 250, 90, 22);
	add(show);
            
        JLabel l3 = new JLabel("Car : ");
	l3.setFont(new Font("Tahoma", Font.PLAIN, 19));
	l3.setBounds(50, 300, 150, 27);
	add(l3);
        
        JLabel l4 = new JLabel("");
	l4.setFont(new Font("Tahoma", Font.PLAIN, 19));
	l4.setBounds(200, 300, 450, 27);
	add(l4);
        
        JLabel l5 = new JLabel("Driver : ");
	l5.setFont(new Font("Tahoma", Font.PLAIN, 19));
	l5.setBounds(50, 350, 250, 27);
	add(l5);
        
        JLabel l6 = new JLabel("");
	l6.setFont(new Font("Tahoma", Font.PLAIN, 19));
	l6.setBounds(200, 350, 450, 27);
	add(l6);
        
        JLabel l7 = new JLabel("Price : ");
	l7.setFont(new Font("Tahoma", Font.PLAIN, 19));
	l7.setBounds(50, 400, 150, 27);
	add(l7);
        
        JLabel l8 = new JLabel("");
	l8.setFont(new Font("Tahoma", Font.PLAIN, 19));
	l8.setBounds(200, 400, 450, 27);
	add(l8);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("cab/booking/System/icons/intracab.png"));
        Image i2 = i1.getImage().getScaledInstance(300, 300,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        
        JLabel l16 = new JLabel(i3);
        l16.setBounds(480, 150, 300, 300);
        add(l16);
        
        JLabel l13 = new JLabel("Reference Number : ");
	l13.setFont(new Font("Tahoma", Font.PLAIN, 19));
	l13.setBounds(50, 450, 250, 27);
	add(l13);
        
        JLabel l14 = new JLabel();
	l14.setFont(new Font("Tahoma", Font.PLAIN, 19));
	l14.setBounds(250, 450, 250, 27);
	add(l14);
		
        Random r = new Random();
        l14.setText(""+Math.abs(r.nextInt() % 100000));
		
	show.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
		try{
                    String src  = c1.getSelectedItem();
                    String dst  = c2.getSelectedItem();
                    
                    Conn c = new Conn();
                    
                    String str = "select * from customer where username = '"+username+"'";
                    String str2 = "select * from intercity where destination = '"+dst+"' AND type = '"+c3.getSelectedItem()+"'";
                    ResultSet rs = c.s.executeQuery(str2);
                    if(rs.next()){
                        l6.setText(rs.getString("driver"));
                        l8.setText("Rs " + rs.getString("price"));
                        l4.setText(rs.getString("car"));
                    }
                    
                    rs = c.s.executeQuery(str);
                    if(rs.next()){
                        l10.setText(rs.getString("name"));
                    }
                    l12.setText(username);
                    
					
		}catch(Exception e){
                    e.printStackTrace();
                }
            }
	});
        
        b1 = new JButton("Book CAB");
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        b1.setBounds(50, 500, 150, 30);
        add(b1);
        
        b1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
		try{
                    String src  = c1.getSelectedItem();
                    String dst  = c2.getSelectedItem();
                    String type = c3.getSelectedItem();
                    String price = l8.getText();
                    String car = l4.getText();
                    String driver = l6.getText();
                    String name = l10.getText();
                    String ref = l14.getText();
                    
                    Conn c = new Conn();
                    
                    c.s.executeUpdate("delete from interCab");
                    
                    String str = "insert into interCab values('"+username+"','"+name+"','"+src+"','"+dst+"','"+type+"', '"+driver+"' , '"+car+"','"+price+"', '"+ref+"')";
                    c.s.executeUpdate(str);
                    
                    JOptionPane.showMessageDialog(null, "Cab Booked Successfully");
                    setVisible(false);
                    
		}catch(Exception e){
                    e.printStackTrace();
                }
            }
	});
        
        
        b2 = new JButton("Cancel");
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        b2.setBounds(250, 500, 150, 30);
        add(b2);
		
        b2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
		setVisible(false);
            }
	});
        
        
	setSize(860,600);
        setLocation(500,220);
	setVisible(true);
		
    }
}
