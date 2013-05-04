package snake;
import javax.swing.*;
import java.awt.*;

public class Block extends JPanel{
	private Toolkit tool;
	private Image image;
	private boolean wallOrRoad;	//ָʾǽ����· 0Ϊ· 1Ϊǽ
	private boolean isOccupy;		//ָʾ�Ƿ�����ռ��  0δռ 1ռ
	private boolean hasFood;			//ָʾ�Ƿ���ʳ�� 0û�� 1��
	
	public Block( String str ){
		tool = this.getToolkit();
		image = tool.getImage( str );
		hasFood = false;	//��ʼ��û��ʳ��
		isOccupy = false;	//��ʼ��δ��������ռ
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
