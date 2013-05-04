package hannoi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import hall.GameHall;

public class HannoiWindow extends JFrame implements ActionListener{
	  Tower tower = null;
	  int amountOfDisc = 4;
	  char []towerName = { 'A', 'B', 'C' };
	  JMenuBar bar;
	  JMenu menuGrade;
	  JMenuItem oneGradeItem,twoGradeItem,threeGradeItem;
	  JButton renew=null;
	  JButton autoButton=null;
	  JPanel center=new JPanel();
	  String user;
	  
	  private class InnerWindowCloser extends WindowAdapter{
			public void windowClosing( WindowEvent we ){
				new GameHall( user );
			}
	  }
	  
	  public HannoiWindow( String _user ){
		user = _user;
		
	    tower=new Tower(towerName);
	    tower.setAmountOfDisc(amountOfDisc);
	    tower.setMaxDiscWidth(120);
	    tower.setMinDiscWidth(50);
	    tower.setDiscHeight(16);
	    tower.putDiscOnTower();
	    add(tower,BorderLayout.CENTER);
	    bar=new JMenuBar();
	    menuGrade=new JMenu("ѡ�񼶱�");
	    oneGradeItem=new JMenuItem("����");
	    twoGradeItem=new JMenuItem("�м�");
	    threeGradeItem=new JMenuItem("�߼�");
	    menuGrade.add(oneGradeItem);
	    menuGrade.add(twoGradeItem);
	    menuGrade.add(threeGradeItem);
	    bar.add(menuGrade);
	    setJMenuBar(bar);
	    oneGradeItem.addActionListener(this);
	    twoGradeItem.addActionListener(this);
	    threeGradeItem.addActionListener(this);
	    renew=new JButton("���¿�ʼ");
	    renew.addActionListener(this);
	    autoButton=new JButton("�Զ���ʾ");
	    autoButton.addActionListener(this);
	    JPanel north=new JPanel();
	    north.add(renew);
	    north.add(autoButton);
	    String mess="��ȫ�����Ӵ�"+towerName[0]+"�����˵�"+towerName[1]+"����"+towerName[2]+"��";
	    JLabel hintMess=new JLabel(mess,JLabel.CENTER);
	    north.add(hintMess);
	    add(north,BorderLayout.NORTH);
	    

		setTitle( "Hannoi--" + user );
		setResizable(false);
	    setVisible(true);
	    setSize( 460, 410 );
	    this.addWindowListener( new InnerWindowCloser() );
	    this.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
	    this.setLocation( ( int )( this.getToolkit().getScreenSize().getWidth() - this.getWidth() ) / 2, 
				( int )( this.getToolkit().getScreenSize().getHeight() - this.getHeight() ) / 2  );
	    validate();
	    
	  }
	  public void actionPerformed(ActionEvent e){
	    if(e.getSource()==oneGradeItem){
	    	amountOfDisc = 4;
	    	tower.setAmountOfDisc(amountOfDisc);
	    	tower.putDiscOnTower();
	    }
	    else if(e.getSource() == twoGradeItem){
	    	amountOfDisc =6;
	    	tower.setAmountOfDisc(amountOfDisc);
	    	tower.putDiscOnTower();
	    }
	    else if(e.getSource() == threeGradeItem){
	    	amountOfDisc =8;
	    	tower.setAmountOfDisc(amountOfDisc);
	    	tower.putDiscOnTower();
	    }
	    else if(e.getSource() == renew){
	    	tower.setAmountOfDisc(amountOfDisc);
	    	tower.putDiscOnTower();
	    }
	    else if(e.getSource() ==autoButton){
	    	tower.setAmountOfDisc(amountOfDisc);
	    	tower.putDiscOnTower();
	    	int x =this.getBounds().x=this.getBounds().width;
	    	int y =this.getBounds().y;
	    	tower.getAutoMoveDisc().setLocation(x,y);
	    	tower.getAutoMoveDisc().setSize(280,this.getBounds().height);
	    	tower.getAutoMoveDisc().setVisible(true);
	    }
	    validate();
	  }
}