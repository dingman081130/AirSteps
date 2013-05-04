package hall;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.io.*;

public class RegisterDialog extends JDialog implements ActionListener{
	private JLabel nameLabel, pwdLabel, rePwdLabel, icon;
	private JTextField name;
	private JPasswordField pwd, rePwd;
	private JButton checkName, submit;
	
	private class InnerWindowCloser extends WindowAdapter{
		public void windowClosing( WindowEvent we ){
			new LoginWindow();
		}
	}
	
	public RegisterDialog(){
		this.setTitle( "register" );
		this.setSize( 200, 150 );
		this.setVisible( true );
		this.setResizable( false );
		this.setLayout( null );
		this.addWindowListener( new InnerWindowCloser() );
		this.setDefaultCloseOperation( JDialog.DISPOSE_ON_CLOSE );
		this.setLocation( ( int )( this.getToolkit().getScreenSize().getWidth() - this.getWidth() ) / 2, 
				( int )( this.getToolkit().getScreenSize().getHeight() - this.getHeight() ) / 2  );
		
		nameLabel = new JLabel( "�û���" );
		nameLabel.setBounds( 15, 20, 40, 20 );
		this.add( nameLabel );
		
		name = new JTextField( "������ע����" );
		name.setBounds( 60, 20, 100, 20 );
		this.add( name );
		
/*		checkName = new JButton( "���" );
		checkName.setBounds( 165, 20, 60, 20 );
		this.add( checkName );
		checkName.addActionListener( this );
*/		
		pwdLabel = new JLabel( "����" );
		pwdLabel.setBounds( 15, 45, 40, 20 );
		this.add( pwdLabel );
		
		pwd = new JPasswordField();
		pwd.setBounds( 60, 45, 100, 20 );
		this.add( pwd );
/*		
		rePwdLabel = new JLabel( "��֤" );
		rePwdLabel.setBounds( 15, 70, 40, 20 );
		this.add( rePwdLabel );
		
		rePwd = new JPasswordField();
		rePwd.setBounds( 60, 70, 100, 20 );
		this.add( rePwd );
		
		icon = new JLabel( "��" );
		icon.setBounds( 165, 70, 60, 20 );
		this.add( icon );
*/	
		submit = new JButton( "�ύ" );
		submit.setBounds( 60, 80, 60, 20 );
		this.add( submit );
		submit.addActionListener( this );
		
		this.validate();
	}
	
	public void actionPerformed( ActionEvent ae ){
		if( ae.getSource() == submit ){
			String nameS = name.getText();
			if( nameS.equals( "" ) ){
				JOptionPane.showMessageDialog( this, "�û�������Ϊ��" );
				return ;
			}
			File file = new File( "database\\user.txt" );
			try{
				FileReader fr = new FileReader( file );
				BufferedReader br = new BufferedReader( fr );
				String s;
				s = br.readLine();
				while( s != null ){	//�ļ�û�н���
					if( s.equals( nameS ) ){	//�ж���ע�����Ƿ���ͬ
						JOptionPane.showMessageDialog( this, "���û����Ѿ�����" );
						fr.close(); br.close();
						return ;
					}
					s = br.readLine();	//����������
					s = br.readLine();	//��ȡ�û�����
				}
				fr.close(); br.close();
				FileWriter fw = new FileWriter( file, true );
				fw.write( nameS + "\n" + pwd.getText() + "\n" );
				fw.close();
				this.dispose();
				new LoginWindow();
				
			}catch( FileNotFoundException fnfe ){
				JOptionPane.showMessageDialog( this, "û�пɶ�����û��ļ����û������" );
				return ;
			}catch( IOException ioe ){
				JOptionPane.showMessageDialog( this, "��ȡ�û��ļ�����" );
				return ;
			}
		}
	}
}