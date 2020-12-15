package users.ICONS;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;


public class UserThree extends JFrame implements ActionListener,Runnable {
    JPanel p1;
    JTextField t1;
    JButton b1;
    static JTextArea a1;
   
    boolean typing = false;
    
    BufferedWriter writer;
    BufferedReader reader;
    UserThree(){
    
        p1 = new JPanel();
        p1.setLayout(null);
        p1.setBackground(new Color(7, 94, 84));
        p1.setBounds(0,0,500,50);
        add(p1);
        
    
    ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("users/ICONS/3.png")); 
    Image i2 = i1.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);
    ImageIcon i3 = new ImageIcon(i2);
    JLabel l1 = new JLabel(i3);
    l1.setBounds(5,5,30,30);
    p1.add(l1);
    
    l1.addMouseListener(new MouseAdapter(){
        public void mouseClicked(MouseEvent ae){
            System.exit(0);
        }
    });
    
    ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("users/ICONS/chloe.jpg")); 
    Image i5 = i4.getImage().getScaledInstance(60,60,Image.SCALE_DEFAULT);
    ImageIcon i6 = new ImageIcon(i5);
    JLabel l2 = new JLabel(i6);
    l2.setBounds(40,0,60,60);
    p1.add(l2);
    
    JLabel l3 = new JLabel("Student");
    l3.setFont(new Font("SAN_SERIF",Font.BOLD,20));
    l3.setForeground(Color.white);
    l3.setBounds(110,10,100,20);
    p1.add(l3);
    
    JLabel l4 = new JLabel("Java1,Java2,Java3.....");
    l4.setFont(new Font("SAN_SERIF",Font.ITALIC,14));
    l4.setForeground(Color.white);
    l4.setBounds(110,28,100,20);
    p1.add(l4);
    
    Timer t = new Timer(1, new ActionListener() {
    	public void actionPerformed(ActionEvent ae) {
    		if(!typing) {
    			l4.setText("Java1,Java2,Java3.....");
    		}
    	}
    });
    
    t.setInitialDelay(500);
    
    a1 = new JTextArea();
    a1.setBounds(0, 51,500 , 498);
    a1.setForeground(Color.white);
    a1.setBackground(Color.BLACK);
    a1.setFont(new Font("SANS_SERIF",Font.BOLD,16));
    a1.setEditable(false);
    a1.setLineWrap(true);
    a1.setWrapStyleWord(true);
    add(a1);
    
    t1 = new JTextField();
    t1.setBounds(5,550,400,40);
    t1.setFont(new Font("SANS_SERIF",Font.BOLD,16));
    add(t1);     
    
        t1.addKeyListener(new KeyAdapter() {
        	public void keyPressed(KeyEvent ke) {
        		l4.setText("typing...");
        		
        		t.stop();
        		
        		typing = true;
        	}
        	
        	public void keyReleased(KeyEvent ke) {
        		typing = false;
        		
        		if(!t.isRunning()) {
        			t.start();
        		}
        	}
        });
    
    b1 = new JButton("SEND");
    b1.setBounds(410,550,80,40);
    b1.setBackground(new Color(7, 94, 84));
    b1.setForeground(Color.white);
    b1.addActionListener(this);
    add(b1);
    
    setLayout(null);
    setSize(500,600);
    setLocation(1020,0);    
    setUndecorated(true);
    setVisible(true);
    try {
    	Socket socketClient = new Socket("localhost", 2001);
    	writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
    	reader = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
    }catch(Exception e) {}
    
    }

    public void actionPerformed(ActionEvent ae){
    	String str = "UserThree: "+t1.getText();
    	try {
    		writer.write(str);
    		writer.write("\r\n");
    		writer.flush();
    	}catch(Exception e){
    		
    	}
    	t1.setText(" ");
    }
  
    public void run() {
    	try {
    		String msg = " ";
    		while((msg = reader.readLine()) != null) {
    			a1.append(msg + "\n");
    		}
    	}catch(Exception e) {
    		
    	}
    }
    
    public static void main(String[] args){
    	UserThree one = new UserThree();
         Thread t1 = new Thread(one);
         t1.start();
     
    }
    
    
}
