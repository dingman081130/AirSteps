package snake;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;


import java.awt.*;

public class Food extends JPanel{
	private Toolkit tool;
	private Image image;
	private Block[][] blocks;
	private Block currentBlock;	//ʳ�ﵱǰռ�ݵĿ�
	
	public Food( Block[][] b ){
		tool = this.getToolkit();
		image = tool.getImage( "snake\\food.jpg" );
		blocks = b;	//�õ���ͼ
		this.setSize( 10, 10 );	//ʳ��Ĵ�СҲΪ10
		this.setBorder( new SoftBevelBorder( BevelBorder.LOWERED ) );
	}
	public void paintComponent( Graphics g ){
		g.drawImage( image, 0, 0, this.getWidth(), this.getHeight(), this );
	}
	//��ʳ�ﶨλ��û�б������ǵ�·��
	public void findLocation(){
		int x, y;	//x���к� y���к�
		do{
			x = ( int )( Math.random() * 50 );
			y = ( int )( Math.random() * 50 );
		}while( blocks[x][y].getWallOrRoad() || blocks[x][y].getIsOccupy() );	//ǽ��ռ�����ܷ�ʳ��
		currentBlock = blocks[x][y];	//ָ��ʳ�ﵱǰռ�еĿ�
		currentBlock.setHasFood( true );	//��ǰ����ʳ��
		this.setLocation( blocks[x][y].getLocation().x, blocks[x][y].getLocation().y );
	}
	public Block getCurrentBlock(){
		return currentBlock;
	}
}
