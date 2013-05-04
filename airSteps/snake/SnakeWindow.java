package snake;
import hall.GameHall;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;

public class SnakeWindow extends JFrame{

	private MenuPanel menuPanel = new MenuPanel( this );
	private ShowPanel showPanel = new ShowPanel( this );
	private CtrlPanel ctrlPanel = new CtrlPanel( this );
	private String user;
	
	private class InnerWindowCloser extends WindowAdapter{
		public void windowClosing( WindowEvent we ){
			new GameHall( user );
		}
  }
	
	public SnakeWindow( String _user ){
		user = _user;
		this.setTitle( "Snake--" + user );
		this.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		this.setVisible( true );
		this.setSize( 510, 580 );
		this.addWindowListener( new InnerWindowCloser() );
		this.setLocation( ( int )( this.getToolkit().getScreenSize().getWidth() - this.getWidth() ) / 2, 
				( int )( this.getToolkit().getScreenSize().getHeight() - this.getHeight() ) / 2  );
		this.setResizable( false );
		this.setLayout( null );
		
		menuPanel.setBorder( new SoftBevelBorder( BevelBorder.RAISED ) );
		menuPanel.setBounds( 1, 1, 500, 20 );
		this.add( menuPanel );
		
		showPanel.setBorder( new SoftBevelBorder( BevelBorder.LOWERED ) );
		showPanel.setBounds( 1, 22, 500, 500 );
		this.add( showPanel );
		
		ctrlPanel.setBorder( new SoftBevelBorder( BevelBorder.LOWERED ) );
		ctrlPanel.setBounds( 1, 523, 500, 24 );
		this.add( ctrlPanel );
		
		this.validate();
	}
	
	public MenuPanel getMenuPanel(){
		return menuPanel;
	}
	public ShowPanel getShowPanel(){
		return showPanel;
	}
	public CtrlPanel getCtrlPanel(){
		return ctrlPanel;
	}
}
