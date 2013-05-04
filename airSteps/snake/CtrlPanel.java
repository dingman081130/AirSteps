package snake;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class CtrlPanel extends JPanel implements ActionListener, KeyListener{
	private SnakeWindow father;
	private JButton start, pause, renew, ok;
	private JTextField speed;
	
	public CtrlPanel( SnakeWindow _father ){
		this.father = _father;
		
		this.setLayout( null );
		start = new JButton( "start" );
		start.setBounds( 1, 2, 70, 20 );
		this.add( start );
		start.addActionListener( this );
		//因为start是切换到运行态的开关 所以应该侦听键盘
		start.addKeyListener( this );
		
		pause = new JButton( "pause" );
		pause.setBounds( 72, 2, 70, 20 );
		this.add( pause );
		pause.addActionListener( this );
		
		renew = new JButton( "renew" );
		renew.setBounds( 143, 2, 70, 20 );
		this.add( renew );
		renew.addActionListener( this );
		
		speed = new JTextField( "set speed under pause！" );
		speed.setBounds( 295, 3, 150, 20 );
		this.add( speed );
		
		ok = new JButton( "ok" );
		ok.setBounds( 447, 2, 50, 20 );
		this.add( ok );
		ok.addActionListener( this );
	}
	
	public void keyPressed( KeyEvent ke ){
		int direction = father.getShowPanel().getSnake().getDirection();
		if( ke.getKeyCode() == KeyEvent.VK_UP ){
			if( direction == 2 || direction == 3 )
				father.getShowPanel().getSnake().setDirection( 0 );
		}else if( ke.getKeyCode() == KeyEvent.VK_DOWN ){
			if( direction == 2 || direction == 3 )
				father.getShowPanel().getSnake().setDirection( 1 );
		}else if( ke.getKeyCode() == KeyEvent.VK_LEFT ){
			if( direction == 0 || direction == 1 )
				father.getShowPanel().getSnake().setDirection( 2 );
		}else if( ke.getKeyCode() == KeyEvent.VK_RIGHT ){
			if( direction == 0 || direction == 1 )
				father.getShowPanel().getSnake().setDirection( 3 );
		}else ;
	}
	public void keyReleased( KeyEvent ke ){
	}
	public void keyTyped( KeyEvent ke ){
	}
	
	public void actionPerformed( ActionEvent ae ){
		if( ae.getSource() == start ){
			int state = father.getShowPanel().getState();
			if( state == 2 || state == 4 ){		//就绪态或暂停态转运行态
				father.getShowPanel().runGame();
			}
		}else if( ae.getSource() == pause ){
			if( father.getShowPanel().getState() == 3 ){	//运行态转暂停态
				father.getShowPanel().pauseGame();
			}
		}else if( ae.getSource() == renew ){
			int state = father.getShowPanel().getState();
			if(  state == 5 || state == 2 ){	//死亡态或就绪态转就绪态
				father.getShowPanel().readyGame();
			}
		}else if( ae.getSource() == ok ){
			int state = father.getShowPanel().getState();
			if( state == 2 || state == 4 ){	//就绪和暂停状态下可以设置速度
				String str = speed.getText();
				if( !str.equals( "" ) ){	//输入不空
					int _speed = Integer.valueOf( str ).intValue();
					father.getShowPanel().getSnake().setSpeed( _speed );
				}
			}
		}else ;
	}
}
