package mine;

import hall.GameHall;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.*;
import java.io.*;
public class MineGame extends JFrame implements ActionListener{
       JMenuBar bar;
       JMenu fileMenu;
       JMenuItem ����, �м�, �߼�, ɨ��Ӣ�۰�;
       MineArea mineArea =null;
       File Ӣ�۰�=new File("Ӣ�۰�.txt");
       Hashtable hashtable=null;
       ShowRecord showHeroRecord=null;
       String user;
       
       private class InnerWindowCloser extends WindowAdapter{
			public void windowClosing( WindowEvent we ){
				new GameHall( user );
			}
	  }
       
       public MineGame( String _user ){
    	  user = _user;
    	  
          mineArea=new MineArea(16,16,40,1);
          add(mineArea,BorderLayout.CENTER);
          bar=new JMenuBar();
          fileMenu=new JMenu("��Ϸ");
          ����=new JMenuItem("����");
          �м�=new JMenuItem("�м�");
          �߼�=new JMenuItem("�߼�");
          ɨ��Ӣ�۰�=new JMenuItem("ɨ��Ӣ�۰�");
          fileMenu.add(����);
          fileMenu.add(�м�);
          fileMenu.add(�߼�);
          fileMenu.add(ɨ��Ӣ�۰�);
          bar.add(fileMenu);
          setJMenuBar(bar);
          ����.addActionListener(this);
          �м�.addActionListener(this);
          �߼�.addActionListener(this);
          ɨ��Ӣ�۰�.addActionListener(this);
          hashtable=new Hashtable();
          hashtable.put("����","����#"+999+"#����");
          hashtable.put("�м�","�м�#"+999+"#����");
          hashtable.put("�߼�","�߼�#"+999+"#����");
          if(!Ӣ�۰�.exists()){
              try{FileOutputStream out=new FileOutputStream(Ӣ�۰�);
                    ObjectOutputStream objectOut=new ObjectOutputStream(out);
                    objectOut.writeObject(hashtable);
                    objectOut.close();
                    out.close();
                 }
                 catch(IOException e){}
          }
          showHeroRecord = new ShowRecord( this, hashtable );
          setSize( 280, 380 );
          this.addWindowListener( new InnerWindowCloser() );
          this.setLocation( ( int )( this.getToolkit().getScreenSize().getWidth() - this.getWidth() ) / 2, 
  				( int )( this.getToolkit().getScreenSize().getHeight() - this.getHeight() ) / 2  );
          setVisible(true);
          setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
          setTitle( "Mine--" + user );
          validate();
       }
       public void actionPerformed(ActionEvent e){
          if(e.getSource()==����){
                  mineArea.initMineArea(8,8,10,1);
                  setSize(200,280);
                  this.setLocation( ( int )( this.getToolkit().getScreenSize().getWidth() - this.getWidth() ) / 2, 
            				( int )( this.getToolkit().getScreenSize().getHeight() - this.getHeight() ) / 2  );
          }
          if(e.getSource()==�м�){
                  mineArea.initMineArea(16,16,40,2);
                  setSize(280,380);
                  this.setLocation( ( int )( this.getToolkit().getScreenSize().getWidth() - this.getWidth() ) / 2, 
            				( int )( this.getToolkit().getScreenSize().getHeight() - this.getHeight() ) / 2  );
          }
          if(e.getSource()==�߼�){
                  mineArea.initMineArea(22,22,99,3);
                  setSize(350,390);
                  this.setLocation( ( int )( this.getToolkit().getScreenSize().getWidth() - this.getWidth() ) / 2, 
            				( int )( this.getToolkit().getScreenSize().getHeight() - this.getHeight() ) / 2  );
          }
          if(e.getSource()==ɨ��Ӣ�۰�){
             if(showHeroRecord!=null)
              showHeroRecord.setVisible(true);  
          }
          validate();
       }
    }
                    