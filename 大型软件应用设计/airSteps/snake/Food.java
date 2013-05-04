package snake;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;


import java.awt.*;

public class Food extends JPanel{
	private Toolkit tool;
	private Image image;
	private Block[][] blocks;
	private Block currentBlock;	//食物当前占据的块
	
	public Food( Block[][] b ){
		tool = this.getToolkit();
		image = tool.getImage( "snake\\food.jpg" );
		blocks = b;	//得到地图
		this.setSize( 10, 10 );	//食物的大小也为10
		this.setBorder( new SoftBevelBorder( BevelBorder.LOWERED ) );
	}
	public void paintComponent( Graphics g ){
		g.drawImage( image, 0, 0, this.getWidth(), this.getHeight(), this );
	}
	//将食物定位到没有被蛇身覆盖的路上
	public void findLocation(){
		int x, y;	//x是行号 y是列号
		do{
			x = ( int )( Math.random() * 50 );
			y = ( int )( Math.random() * 50 );
		}while( blocks[x][y].getWallOrRoad() || blocks[x][y].getIsOccupy() );	//墙或占领则不能放食物
		currentBlock = blocks[x][y];	//指向食物当前占有的块
		currentBlock.setHasFood( true );	//当前块有食物
		this.setLocation( blocks[x][y].getLocation().x, blocks[x][y].getLocation().y );
	}
	public Block getCurrentBlock(){
		return currentBlock;
	}
}
