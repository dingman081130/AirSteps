package snake;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;


import java.awt.event.*;
import java.io.*;
import java.util.*;

public class ShowPanel extends JLayeredPane{
	private SnakeWindow father;
	private File mapFile;
	private Block[][] blocks = null;
	private Snake snake;
	private Food food;
	private int state;	
	/*	游戏的状态 
	 * 
	 * 0 初始态--地图还未载入的状态
	 * 1 激活态--地图载入后的状态 
	 * 2 就绪态--初始化蛇与食物后的状态
	 * 3 运行态--蛇正在行走干掉食物所处的状态 
	 * 4 暂停态--蛇停止行走的状态
	 * 5 死亡态--蛇已经死亡的状态
	 *
	 */
	public ShowPanel( SnakeWindow _father ){
		this.father = _father;
		initGame();
	}
	
	public void initGame(){ 
		state = 0;
	}
	
	public void activeGame( File _mapFile ){	//激活游戏 包括地图的载入和初始化蛇与食物
		this.mapFile = _mapFile;
		
		if( blocks != null ){
			for( int i = 0; i < 50; i ++ )
				for( int j = 0; j < 50; j ++ ){
					this.remove( blocks[i][j] );
				}
		}
		if( snake != null )
			delSnake();
		if( food != null )
			this.remove( food );
		
		char [][]a;
		a = new char[50][50];
		RandomAccessFile in = null;
		try{
			in = new RandomAccessFile( mapFile, "r" );
			for( int i = 0; i < 50; i ++ ){
				String str = in.readLine();
				a[i] = str.toCharArray();
			}
			in.close();
		}catch( IOException e ){
			JOptionPane.showMessageDialog( father, "mapFile operation error!" );
			e.printStackTrace();
		}
		
		blocks = new Block[50][50];
		for( int i = 0; i < 50; i ++ )
			for( int j = 0; j < 50; j ++ ){
				if( a[i][j] == '0' ){
					blocks[i][j] = new Block( "snake\\road.jpg" );
					blocks[i][j].setBorder( new SoftBevelBorder( BevelBorder.LOWERED ) );
					blocks[i][j].setWallOrRoad( false );
				}else if( a[i][j] == '1' ){
					blocks[i][j] = new Block( "snake\\wall.jpg" );
					blocks[i][j].setBorder( new SoftBevelBorder( BevelBorder.RAISED ) );
					blocks[i][j].setWallOrRoad( true );
				}else {
					JOptionPane.showMessageDialog( this, "mapFile illegal!" );
					return;
				}
				
				blocks[i][j].setIsOccupy( false );	//载入地图时 block都未被蛇身覆盖 因为蛇还没生成
				blocks[i][j].setSize( 10, 10 );
				blocks[i][j].setLocation( j * 10, i * 10 );
				this.setLayer( blocks[i][j], JLayeredPane.DEFAULT_LAYER );
				this.add( blocks[i][j] );
			}		

		state = 1;	//载入地图后 游戏的状态由初始态变为激活态
		readyGame();
	}
	
	public void readyGame(){
		this.repaint();
		snake = new Snake( this );
		snake.initSnake();
		this.addSnake();

		father.getMenuPanel().setCnt( snake.getSnakeBodyNodes().size() );
		
		food = new Food( blocks );
		this.setLayer( food, JLayeredPane.DRAG_LAYER );
		this.add( food );
		food.findLocation();

		state = 2;
	}
	
	public void runGame(){
		snake.move();
		state = 3;
	}
	
	public void pauseGame(){
		snake.pause();
		state = 4;
	}
	
	public void killGame(){
		delSnake();	//删除蛇
		food.getCurrentBlock().setHasFood( false );
		this.remove( food );	//删除食物
		state = 5;
	}
	
	//添加蛇
	public void addSnake(){
		Vector<BodyNode> v = snake.getSnakeBodyNodes();
		for( int i = 0; i < v.size(); i ++ ){
			this.setLayer( v.get( i ), JLayeredPane.DRAG_LAYER );
			this.add( v.get( i ) );
		}
	}
	
	public void delSnake(){
		Vector<BodyNode> v = snake.getSnakeBodyNodes();
		for( int i = 0; i < v.size(); i ++ ){
			v.get( i ).getCurrentBlock().setIsOccupy( false );
			this.remove( v.get( i ) );	//清除在地图中的蛇身
		}
	}
	
	//得到游戏的状态
	public int getState(){
		return state;
	}
	
	public Snake getSnake(){
		return snake;
	}
	
	public void addNewSnakeNode( BodyNode _newNode ){
		this.setLayer( _newNode, JLayeredPane.DRAG_LAYER );
		this.add( _newNode );
	}
	
	public Block[][] getBlocks(){
		return blocks;
	}
	
	public Food getFood(){
		return food;
	}
	
	public SnakeWindow getFather(){
		return father;
	}
}
