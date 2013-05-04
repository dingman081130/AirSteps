package hall;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.IOException;

public class LoginWindow extends JFrame implements ActionListener{
	private Toolkit tool;
	private Image image;
	
	private JLabel userLabel;
	private JTextField user;
	private JLabel pwdLabel;
	private JPasswordField pwd;
	
	private JButton login;
	private JButton register;
	
	public LoginWindow(){
		this.setTitle( "欢迎光临云中漫步游戏厅" );
		this.setSize( 300, 180 );
		this.setResizable( false );
		this.setVisible( true );
		this.setLayout( null );
		this.setLocation( ( int )( this.getToolkit().getScreenSize().getWidth() - this.getWidth() ) / 2, 
				( int )( this.getToolkit().getScreenSize().getHeight() - this.getHeight() ) / 2  ); 
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		
		tool = this.getToolkit();
		image = tool.getImage( "hall\\cloud.jpg" );
		
		userLabel = new JLabel( "user" );
		userLabel.setBounds( 60, 30, 30, 20 );
		this.add( userLabel );
		
		user = new JTextField( "请输入用户名" );
		user.setBounds( 95, 30, 120, 20 );
		this.add( user );
		
		pwdLabel = new JLabel( "pwd" );
		pwdLabel.setBounds( 60, 60, 30, 20 );
		this.add( pwdLabel );
		
		pwd = new JPasswordField();
		pwd.setBounds( 95, 60, 120, 20 );
		this.add( pwd );
		
		login = new JButton( "login" );
		login.setBounds( 60, 90, 80, 20 );
		this.add( login );
		login.addActionListener( this );
		
		register = new JButton( "register" );
		register.setBounds( 145, 90, 80, 20 );
		this.add( register );
		register.addActionListener( this );

		this.validate();
		this.repaint();
	}
	
	public void paintComponent( Graphics g ){
		g.drawImage( image, 0, 0, this.getWidth(), this.getHeight(), this );
	}

	public void actionPerformed( ActionEvent ae ){
		if( ae.getSource() == login ){
			if( user.getText().equals( "ad" ) && pwd.getText().equals( "123" ) ){
				new GameHall( "ad" );
				this.dispose();
			}else{
				String nameGet = user.getText();
				String pwdGet = pwd.getText();
				try{
					FileReader fr = new FileReader( "database\\user.txt" );
					BufferedReader br = new BufferedReader( fr );
					String nameRead = br.readLine();
					String pwdRead = br.readLine();
					while( nameRead != null && pwdRead != null ){
						if( nameRead.equals( nameGet ) && pwdRead.equals( pwdGet ) ){
							fr.close();
							br.close();
							new GameHall( nameGet );
							this.dispose();
						}
						nameRead = br.readLine();	//读取用户名行
						pwdRead = br.readLine();	//读取密码行
					}
					br.close(); fr.close();
					JOptionPane.showMessageDialog( this, "用户" + nameGet + "不存在" );
					return ;
				}catch( FileNotFoundException fnfe ){
					JOptionPane.showMessageDialog( this, "没有可读入的文件" );
					return ;
				}catch( IOException ioe ){
			//		JOptionPane.showMessageDialog( this, "读入文件错误" );	?bug
					return ;
				}
			}
		}else if( ae.getSource() == register ){
			new RegisterDialog();
			this.dispose();
		}
	}
	
	public static void main( String [] args ){
		new LoginWindow();
	}
}
