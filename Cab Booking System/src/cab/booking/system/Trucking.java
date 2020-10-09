/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cab.booking.system;


import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class Trucking extends JFrame{ 

    JTable table;
    JLabel ReservationDetails,Source,Destination;
    JButton show, b1, b2;
    Choice c1, c2, c3;
    
    public static void main(String[] args){
        new Trucking("");
    }
    
    public Trucking(String username){
        
        setTitle("Transport Goods");
	getContentPane().setBackground(Color.WHITE);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        ReservationDetails = new JLabel("Transport Goods");
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
        
        JLabel l9 = new JLabel("Name : ");
	l9.setFont(new Font("Tahoma", Font.PLAIN, 19));
	l9.setBounds(50, 150, 250, 27);
	add(l9);
        
        JLabel l10 = new JLabel("");
	l10.setFont(new Font("Tahoma", Font.PLAIN, 19));
	l10.setBounds(200, 150, 150, 27);
	add(l10);
        
	
	c1 = new Choice();
        c1.setBounds(150, 100, 150, 27);
        add(c1);
      	
	c2 = new Choice();
        c2.setBounds(500, 100, 150, 27);
        add(c2);
        
        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from intracity");
            while(rs.next()){
                c1.add(rs.getString("source"));
                c2.add(rs.getString("destination"));
            }
            
            rs = c.s.executeQuery("select * from customer where username = '"+username+"'");
            while(rs.next()){
                l10.setText(rs.getString("name"));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        
       
        JLabel l11 = new JLabel("Weight : ");
	l11.setFont(new Font("Tahoma", Font.PLAIN, 19));
	l11.setBounds(50, 200, 150, 27);
	add(l11);
        
        c3 = new Choice();
        c3.add("0 - 10 KG");
        c3.add("10 - 50 KG");
        c3.add("50 - 100 KG");
        c3.add("100 - 500 KG");
        c3.add("500 - 1000 KG");
        c3.add(" > 1000 KG");
        c3.setBounds(200, 200, 150, 27);
        add(c3);
        
	show = new JButton("SHOW DETAILS");
        show.setBackground(Color.BLACK);
        show.setForeground(Color.WHITE);
	show.setBounds(400, 200, 130, 25);
	add(show);
	
        
        JLabel l1 = new JLabel("Driver Name : ");
	l1.setFont(new Font("Tahoma", Font.PLAIN, 19));
	l1.setBounds(50, 250, 150, 27);
	add(l1);
        
        JLabel l2 = new JLabel("");
	l2.setFont(new Font("Tahoma", Font.PLAIN, 19));
	l2.setBounds(200, 250, 150, 27);
	add(l2);
        
        JLabel l5 = new JLabel("Truck : ");
	l5.setFont(new Font("Tahoma", Font.PLAIN, 19));
	l5.setBounds(50, 300, 250, 27);
	add(l5);
        
        JLabel l6 = new JLabel("");
	l6.setFont(new Font("Tahoma", Font.PLAIN, 19));
	l6.setBounds(200, 300, 350, 27);
	add(l6);
        
        JLabel l7 = new JLabel("Distance : ");
	l7.setFont(new Font("Tahoma", Font.PLAIN, 19));
	l7.setBounds(50, 350, 150, 27);
	add(l7);
        
        JLabel l8 = new JLabel("");
	l8.setFont(new Font("Tahoma", Font.PLAIN, 19));
	l8.setBounds(200, 350, 350, 27);
	add(l8);
        
        JLabel l3 = new JLabel("Price : ");
	l3.setFont(new Font("Tahoma", Font.PLAIN, 19));
	l3.setBounds(50, 400, 150, 27);
	add(l3);
        
        JLabel l4 = new JLabel("");
	l4.setFont(new Font("Tahoma", Font.PLAIN, 19));
	l4.setBounds(200, 400, 150, 27);
	add(l4);
        
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("cab/booking/System/icons/transport.jpeg"));
        Image i2 = i1.getImage().getScaledInstance(500, 300,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        
        JLabel l16 = new JLabel(i3);
        l16.setBounds(460, 170, 500, 300);
        add(l16);
			
		
	show.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
		try{
                    String dst  = c2.getSelectedItem();
                    String weight  = c3.getSelectedItem();
                    
                    Conn c = new Conn();
                    
                    String str = "select * from transport where destination = '"+dst+"'";
                    ResultSet rs = c.s.executeQuery(str);
                    int price = 0;
                    while(rs.next()){
                        l2.setText(rs.getString("dname"));
                        price = Integer.parseInt(rs.getString("price"));
                        l6.setText(rs.getString("truck"));
                        l8.setText(rs.getString("distance"));
                    }
                    switch(weight){
                        case "0 - 10 KG" : price += 100;
                        break;
                        case "10 - 50 KG" : price += 200;
                        break;
                        case "50 - 100 KG" : price += 300;
                        break;
                        case "100 - 500 KG" : price += 400;
                        break;
                        case "500 - 1000 KG" : price += 500;
                        break;
                        case "> 1000 KG" : price += 600;
                        break;
                    }
                    
                    l4.setText("Rs: " + price);
                    
                   			
		}catch(Exception e){
                    e.printStackTrace();
                }
            }
	});
        
        b1 = new JButton("Transport");
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        b1.setBounds(50, 500, 150, 30);
        add(b1);
        
        b1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
		try{
                    String src  = c1.getSelectedItem();
                    String dst  = c2.getSelectedItem();
                    String weight  = c3.getSelectedItem();
                    String dname = l2.getText();
                    String price = l4.getText();
                    String truck = l6.getText();
                    String distance = l8.getText();
                    String name = l10.getText();
                    
                    Conn c = new Conn();
                    
                    c.s.executeUpdate("delete from intransport");
                    
                    String str = "insert into intransport values('"+src+"','"+dst+"','"+name+"','"+weight+"','"+dname+"','"+truck+"','"+distance+"','"+price+"')";
                    c.s.executeUpdate(str);
                    
                    JOptionPane.showMessageDialog(null, "Booked Successfully");
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
        
        
	setSize(1000,600);
        setLocation(450,220);
	setVisible(true);
		
    }
}
