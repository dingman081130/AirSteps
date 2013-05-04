package hall;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import java.awt.*;
import java.awt.event.*;
import snake.*;
import mine.*;
import hannoi.*;
import puzzle.*;
import maze.*;

class Logo extends JPanel{
	private Toolkit tool;
	private Image image;
	
	public Logo( String file ){
		this.setSize( 70, 70 );
		tool = this.getToolkit();
		image = tool.getImage( file );
	}
	
	public void paintComponent( Graphics g ){
		g.drawImage( image, 0, 0, this.getWidth(), this.getHeight(), this );
	}
}

public class GameHall extends JFrame implements ActionListener, MouseListener, MouseMotionListener{
	private JMenuBar bar;
	private JMenu menuHelp, menuUser;
	private JPanel gameList;
	private Logo snakeLogo, mineLogo, mazeLogo, puzzleLogo, hannoiLogo;
	private JTextArea userInfo, gameInfo;
	private JScrollPane jsp1, jsp2;
	private String user;
	private int select = 0;	//1-5个游戏的选择
	
	private class InnerWindowCloser extends WindowAdapter{
		public void windowClosing( WindowEvent we ){
			System.exit( 0 );
		}
	}
	
	public GameHall( String _user ){
		user = _user;
		this.setTitle( "GameHall--" + user );
		this.setSize( 400, 280 );
		this.setResizable( false );
		this.setVisible( true );
		this.setLayout( null );
		this.addWindowListener( new InnerWindowCloser() );
		this.setLocation( ( int )( this.getToolkit().getScreenSize().getWidth() - this.getWidth() ) / 2, 
				( int )( this.getToolkit().getScreenSize().getHeight() - this.getHeight() ) / 2  );
		
		bar = new JMenuBar();
		bar.setBounds( 0, 0, 390, 20 );
		this.add( bar );
		
		menuHelp = new JMenu( "help" );
		bar.add( menuHelp );
		
		menuUser = new JMenu( "user" );
		bar.add( menuUser );
		
		gameList = new JPanel();
		gameList.setBorder( new SoftBevelBorder( BevelBorder.LOWERED ) );
		gameList.setBounds( 5, 25, 230, 190 );
		this.add( gameList );
		gameList.setLayout( null );
		
		snakeLogo = new Logo( "hall\\snakeLogo.jpg" );
		snakeLogo.setBorder( new SoftBevelBorder( BevelBorder.RAISED ) );
		snakeLogo.setLocation( 5, 5 );
		snakeLogo.addMouseListener( this );
		snakeLogo.addMouseMotionListener( this );
		gameList.add( snakeLogo );
		JLabel label1 = new JLabel( "     snake" );
		label1.setBounds( 5, 75, 70, 20 );
		gameList.add( label1 );
		
		mineLogo = new Logo( "hall\\mineLogo.jpg" );
		mineLogo.setBorder( new SoftBevelBorder( BevelBorder.RAISED ) );
		mineLogo.setLocation( 80, 5 );
		mineLogo.addMouseListener( this );
		mineLogo.addMouseMotionListener( this );
		gameList.add( mineLogo );
		JLabel label2 = new JLabel( "     mine" );
		label2.setBounds( 80, 75, 70, 20 );
		gameList.add( label2 );
		
		mazeLogo = new Logo( "hall\\mazeLogo.jpg" );
		mazeLogo.setBorder( new SoftBevelBorder( BevelBorder.RAISED ) );
		mazeLogo.setLocation( 155, 5 );
		mazeLogo.addMouseListener( this );
		mazeLogo.addMouseMotionListener( this );
		gameList.add( mazeLogo );
		JLabel label3 = new JLabel( "     maze" );
		label3.setBounds( 155, 75, 70, 20 );
		gameList.add( label3 );
		
		puzzleLogo = new Logo( "hall\\puzzleLogo.jpg" );
		puzzleLogo.setBorder( new SoftBevelBorder( BevelBorder.RAISED ) );
		puzzleLogo.setLocation( 5, 100 );
		puzzleLogo.addMouseListener( this );
		puzzleLogo.addMouseMotionListener( this );
		gameList.add( puzzleLogo );
		JLabel label4 = new JLabel( "     puzzle" );
		label4.setBounds( 5, 170, 70, 20 );
		gameList.add( label4 );
		
		hannoiLogo = new Logo( "hall\\hannoiLogo.jpg" );
		hannoiLogo.setBorder( new SoftBevelBorder( BevelBorder.RAISED ) );
		hannoiLogo.setLocation( 80, 100 );
		hannoiLogo.addMouseListener( this );
		hannoiLogo.addMouseMotionListener( this );
		gameList.add( hannoiLogo );
		JLabel label5 = new JLabel( "     hannoi" );
		label5.setBounds( 80, 170, 70, 20 );
		gameList.add( label5 );
		
		JLabel label6 = new JLabel( "单击游戏Logo，进入所选游戏窗口。" );
		label6.setBounds( 5, 220, 210, 20 );
		this.add( label6 );
		
		userInfo = new JTextArea();
		userInfo.setEditable( false );
		userInfo.setText( "尊敬的" + user + "\n      欢迎进入云中漫步\n游戏大厅!" );
		jsp1 = new JScrollPane( userInfo );
		jsp1.setBounds( 240, 25, 150, 100 );
		this.add( jsp1 );
		jsp1.setBorder( new SoftBevelBorder( BevelBorder.LOWERED ) );
		
		gameInfo = new JTextArea();
		gameInfo.setEditable( false );
		jsp2 = new JScrollPane( gameInfo );
		jsp2.setBounds( 240, 130, 150, 100 );
		this.add( jsp2 );
		jsp2.setBorder( new SoftBevelBorder( BevelBorder.LOWERED ) );
		
		this.validate();
	}
	
	public void actionPerformed( ActionEvent ae ){
		
	}
	public void mouseClicked(MouseEvent e){
		if( e.getSource() == snakeLogo ){
			new SnakeWindow( user );
			this.dispose();
		}else if( e.getSource() == mineLogo ){
			new MineGame( user );
			this.dispose();
		}else if( e.getSource() == hannoiLogo ){
			new HannoiWindow( user );
			this.dispose();
		}else if( e.getSource() == puzzleLogo ){
			new PuzzleGame( user );
			this.dispose();
		}else if( e.getSource() == mazeLogo ){
			new MazeWindow( user );
			this.dispose();
		}
	}
    
	public void mouseEntered(MouseEvent e){
		
	}
 
	public void mouseExited(MouseEvent e){
		
	}
 
	public void mousePressed(MouseEvent e){
		
	}
      
	public void mouseReleased(MouseEvent e){
		
	}
	
	public void mouseDragged( MouseEvent e ){
		
	}
	
	public void mouseMoved( MouseEvent e ){
		if( e.getSource() == snakeLogo ){
			gameInfo.setText( "贪吃蛇：\n    这个游戏好哇！" );
		}else if( e.getSource() == mineLogo ){
			gameInfo.setText( "扫雷：\n    这个游戏棒哇！" );
		}else if( e.getSource() == mazeLogo ){
			gameInfo.setText( "迷宫：\n    这个游戏顶呱呱哇！" );
		}else if( e.getSource() == puzzleLogo ){
			gameInfo.setText( "拼图：\n    呵呵，不好意思，\n这个游戏貌似有点问题，\n见谅见谅啊！" );
		}else if( e.getSource() == hannoiLogo ){
			gameInfo.setText( "汉诺塔:\n    脑残者禁止入内!" );
		}
	}
}
