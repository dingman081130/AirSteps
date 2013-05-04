package snake;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class MenuPanel extends JMenuBar implements ActionListener{
	private SnakeWindow father;
	private JMenu maps, changeGame, rule;
	private File mapFile;
	private JLabel cnt;
	
	public MenuPanel( SnakeWindow _father ){
		this.father = _father;
		this.setLayout( null );
		
		maps = new JMenu( "load map" );
		maps.setBounds( 0, 0, 70, 20 );
		this.add( maps );
		
		changeGame = new JMenu( "change Game" );
		changeGame.setBounds( 70, 0, 100, 20 );
		this.add( changeGame );
		
		rule = new JMenu( "rule" );
		rule.setBounds( 170, 0, 70, 20 );
		this.add( rule );
		
		
		File dir = new File( "snake\\." );
		File file[] = dir.listFiles(
				new FilenameFilter(){
					public boolean accept( File dir, String name ){
						return name.endsWith( ".map" );
					}
				}
		);
		
		for(int i = 0; i < file.length; i++ ){
			JMenuItem item = new JMenuItem( file[i].getName() );
			item.addActionListener( this );
			maps.add( item );
		}
		
		cnt = new JLabel( "The length of snake is ?" );
		cnt.setBounds( 350, 0, 150, 20 );
		this.add( cnt );
	}
	
	public void actionPerformed( ActionEvent ae ){
		JMenuItem item = ( JMenuItem )ae.getSource();
		mapFile = new File( "snake\\" + item.getText() );
		int state = father.getShowPanel().getState();
		if( state == 0 || state == 2 || state == 5){	
			//�����ʾ���Ϊ��ʼ̬�����̬������̬��ת����̬
			father.getShowPanel().activeGame( mapFile );
		}
	}
	
	public void setCnt( int _cnt ){
		cnt.setText( "The length of snake is " + new Integer( _cnt ).toString() );
	}

}
