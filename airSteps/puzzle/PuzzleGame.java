package puzzle;

import hall.GameHall;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import java.io.*;
import javax.swing.filechooser.*;

public class PuzzleGame extends JFrame implements ActionListener{
	
	PuzzlePad puzzlePad;	//ƴͼ��Ϸ��
	JMenuBar bar;			//�˵���
	JMenu gradeMenu, choiceImage;	//�ȼ��˵� ѡ��ͼ��
	JMenuItem oneGrade, twoGrade, newImage, defaultImage;	//�˵���
	JRadioButton digitPlay, imagePlay;	//�����淨��ͼ���淨
	ButtonGroup group = null;
	JButton startButton;
	Image image;
	Toolkit tool;
	String user;
	
	private class InnerWindowCloser extends WindowAdapter{
		public void windowClosing( WindowEvent we ){
			new GameHall( user );
		}
  }
	
	public PuzzleGame( String _user ){
		user = _user;
		tool = this.getToolkit();
		bar = new JMenuBar();
		gradeMenu = new JMenu( "ѡ�񼶱�" );
		choiceImage = new JMenu( "ѡ��ͼ��" );
		oneGrade = new JMenuItem( "����" );
		twoGrade = new JMenuItem( "�߼�" );
		newImage = new JMenuItem( "ѡ��һ����ͼ��" );
		defaultImage = new JMenuItem( "ʹ��Ĭ��ͼ��" );
		gradeMenu.add( oneGrade );
		gradeMenu.add( twoGrade );
		choiceImage.add( newImage );
		choiceImage.add( defaultImage );
		bar.add( gradeMenu );
		bar.add( choiceImage );
		setJMenuBar( bar );
		oneGrade.addActionListener( this );
		twoGrade.addActionListener( this );
		newImage.addActionListener( this );
		defaultImage.addActionListener( this );
		startButton = new JButton( "��ʼ" );
		startButton.addActionListener( this );
		group = new ButtonGroup();
		digitPlay = new JRadioButton( "�����淨", true );
		imagePlay = new JRadioButton( "ͼ���淨", false );
		group.add( digitPlay );
		group.add( imagePlay );
		
		puzzlePad = new PuzzlePad();
		puzzlePad.setGrade( 1 );
		puzzlePad.setIsDigitPlay();
		this.add( puzzlePad, BorderLayout.CENTER );
		JPanel pNorth = new JPanel();
		pNorth.add( digitPlay );
		pNorth.add( imagePlay );
		pNorth.add( startButton );
		pNorth.add( new JLabel( "���ͼ����������ʾ�����ٵ���һ�ΰ�ť" ) );
		this.add( pNorth, BorderLayout.NORTH );
		this.add( puzzlePad.getHandleMove(), BorderLayout.SOUTH );
		this.validate();
		this.setVisible( true );
		this.addWindowListener( new InnerWindowCloser() );
		this.setSize( 550, 380 );
		this.setLocation( ( int )( this.getToolkit().getScreenSize().getWidth() - this.getWidth() ) / 2, 
				( int )( this.getToolkit().getScreenSize().getHeight() - this.getHeight() ) / 2  );
		this.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		this.setTitle( "Puzzle--" + user );
		try{
			image = tool.createImage( new File( "puzzle\\dingman.jpg" ).toURI().toURL() );
			puzzlePad.setImage( image );
		}catch( Exception exp ){}
	}
	
	public void actionPerformed( ActionEvent e ){
		if( e.getSource() == startButton ){
			if( digitPlay.isSelected() ){
				puzzlePad.setIsDigitPlay();
			}else if( imagePlay.isSelected() ){
				puzzlePad.setImage( image );
				puzzlePad.setIsImagePlay();
			}
		}else if( e.getSource() == oneGrade ){
			puzzlePad.setGrade( 1 );
		}else if( e.getSource() == twoGrade ){
			puzzlePad.setGrade( 2 );
		}else if( e.getSource() == newImage ){
			FileNameExtensionFilter filter =  new FileNameExtensionFilter(
			"JPG & GIF Images", "jpg", "gif");
			JFileChooser chooser = new JFileChooser();
			chooser.setFileFilter( filter );
			int state = chooser.showOpenDialog( null );
			File file = chooser.getSelectedFile();
			if( file != null && state == JFileChooser.APPROVE_OPTION ){
				try{
					image = tool.createImage( file.toURI().toURL() );
					puzzlePad.setImage( image );
				}catch( Exception exp ){}
			}
		}else if( e.getSource() == defaultImage ){
			try{
				image = tool.createImage( new File( "puzzle\\dingman.jpg" ).toURI().toURL() );
				puzzlePad.setImage( image );
			}catch( Exception exp ){}
		}
	}
	
/*	public static void main( String args[] ){
		new PuzzleGame();
	}*/

}