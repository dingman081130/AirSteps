package snake;
import java.awt.*;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;


public class BodyNode extends JPanel {
	private Toolkit tool;
	private Image image;
	private Block currentBlock;
	private int x, y; //currentBlock 在blocks[][]中的行号和列号
	
	public BodyNode(){
		tool = this.getToolkit();
		image = tool.getImage( "snake\\snake.jpg" );
		this.setSize( 10, 10 );
		this.setBorder( new SoftBevelBorder( BevelBorder.LOWERED ) );
	}
	
	public void paintComponent( Graphics g ){
		g.drawImage( image, 0, 0, this.getWidth(), this.getHeight(), this );
	}
	
	public void setPosition( Block b, int _x, int _y ){
		currentBlock = b;
		this.x = _x;
		this.y = _y;
		this.setLocation( currentBlock.getLocation().x, currentBlock.getLocation().y );		//在地图中的像素位置
	}
	public int getXInBlocks(){
		return x;
	}
	public int getYInBlocks(){
		return y;
	}
	public Block getCurrentBlock(){
		return currentBlock;
	}
}
