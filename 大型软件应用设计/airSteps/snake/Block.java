package snake;
import javax.swing.*;
import java.awt.*;

public class Block extends JPanel{
	private Toolkit tool;
	private Image image;
	private boolean wallOrRoad;	//指示墙还是路 0为路 1为墙
	private boolean isOccupy;		//指示是否被蛇身占据  0未占 1占
	private boolean hasFood;			//指示是否有食物 0没有 1有
	
	public Block( String str ){
		tool = this.getToolkit();
		image = tool.getImage( str );
		hasFood = false;	//初始化没有食物
		isOccupy = false;	//初始化未被蛇身所占
	}
	public void paintComponent( Graphics g ){
		g.drawImage( image, 0, 0, this.getWidth(), this.getHeight(), this );
	}
	public void setWallOrRoad( boolean boo ){
		wallOrRoad = boo;
	}
	public void setIsOccupy( boolean boo ){
		isOccupy = boo;
	}
	public void setHasFood( boolean boo ){
		hasFood = boo;
	}
	public boolean getWallOrRoad(){
		return wallOrRoad;
	}
	public boolean getIsOccupy(){
		return isOccupy;
	}
	public boolean getHasFood(){
		return hasFood;
	}
}
