package snake;
import javax.swing.*;


import java.awt.*;
import java.util.*;

//Snake��BodyNode�ļ���
public class Snake implements Runnable{
	private ShowPanel father;
	private Thread thread;
	private Vector<BodyNode> bodyNodes;
	private Block[][] blocks;
	private int direction;	//�����еķ��� 0-top 1-down 2-left 3-right
	private boolean isRunning = true;
	private int speed;
	
	public Snake( ShowPanel _father ){
		this.father = _father;
		this.blocks = father.getBlocks();
		speed = 50;
		bodyNodes = new Vector();
	}
	
	public void initSnake(){
		//��ʼ������һ������Ϊ����node����
		int x, y;
		do{
			x = ( int )( Math.random() * 25 );	//�ڵ�ͼ���ϰ벿������
			y = ( int )( Math.random() * 50 );
		}while( x < 2 || blocks[x][y].getWallOrRoad() || blocks[x-1][y].getWallOrRoad() );
		
		BodyNode first = new BodyNode();
		blocks[x][y].setIsOccupy( true );
		first.setPosition( blocks[x][y], x, y );
		bodyNodes.add( first );
		
		BodyNode last = new BodyNode();
		blocks[x-1][y].setIsOccupy( true );
		last.setPosition( blocks[x-1][y], x-1, y );
		bodyNodes.add( last );
		
		direction = 1;	//��ʼ�����߷���Ϊ��
	}
	
	public Vector<BodyNode> getSnakeBodyNodes(){
		return bodyNodes;
	}
	
	public void setDirection( int d ){
		if( d > -1 && d < 4 )
			direction = d;
		else 
			;
	}
	
	public void run(){
		while( isRunning ){

			BodyNode first = bodyNodes.firstElement();
			BodyNode last = bodyNodes.lastElement();
			
			switch( direction ){
				case 0:
					int x0 =  ( first.getXInBlocks() - 1 + 50 ) % 50;
					int y0 = ( first.getYInBlocks() + 50 ) % 50;
					Block next0 = blocks[x0][y0];	//����һ����м��
					if( next0.getHasFood() ){	//ǰ����ʳ��
						next0.setHasFood( false );	//�Ե�ʳ��
						father.getFood().findLocation();
						next0.setIsOccupy( true );	//�����ÿ鱻ռ��
						BodyNode newNode0 = new BodyNode();	//����һ�����
						newNode0.setPosition( next0, x0, y0 );
						bodyNodes.add( 0, newNode0 );	//���ӵ�������ȥ
						father.addNewSnakeNode( newNode0 );	//���ӵ���ʾ�����ȥ
						father.getFather().getMenuPanel().setCnt( bodyNodes.size() );
					}else if( next0.getWallOrRoad() ){	//ǰ����ʯͷ
						isRunning = false;
						father.killGame();
						JOptionPane.showMessageDialog( father.getFather(), "eat stone" );
					}else if( next0.getIsOccupy() ){	//ǰ��������
						isRunning = false;
						father.killGame();
						JOptionPane.showMessageDialog( father.getFather(), "eat itself" );
					}else if( !next0.getWallOrRoad() ){	//ǰ����·
						next0.setIsOccupy( true );	//ռ��ÿ�
						last.getCurrentBlock().setIsOccupy( false );	//�ͷ�β���ڵ�ռ�õĿ�
						bodyNodes.remove( bodyNodes.size() - 1 );
						last.setPosition( next0, x0, y0 );
						bodyNodes.add( 0, last );
					}else ;
					break;
				case 1:
					int x1 = ( first.getXInBlocks() + 1 + 50 ) % 50;
					int y1 = ( first.getYInBlocks() + 50 ) % 50;
					Block next1 = blocks[x1][y1];	//����һ����м��
					if( next1.getHasFood() ){	//ǰ����ʳ��
						next1.setHasFood( false );	//�Ե�ʳ��
						father.getFood().findLocation();
						next1.setIsOccupy( true );	//�����ÿ鱻ռ��
						BodyNode newNode1 = new BodyNode();	//����һ�����
						newNode1.setPosition( next1, x1, y1 );
						bodyNodes.add( 0, newNode1 );	//���ӵ�������ȥ
						father.addNewSnakeNode( newNode1 );	//���ӵ���ʾ�����ȥ
						father.getFather().getMenuPanel().setCnt( bodyNodes.size() );
					}else if( next1.getWallOrRoad() ){	//ǰ����ʯͷ
						isRunning = false;
						father.killGame();
						JOptionPane.showMessageDialog( father.getFather(), "eat stone" );
					}else if( next1.getIsOccupy() ){	//ǰ��������
						isRunning = false;
						father.killGame();
						JOptionPane.showMessageDialog( father.getFather(), "eat itself" );
					}else if( !next1.getWallOrRoad() ){	//ǰ����·
						next1.setIsOccupy( true );	//ռ��ÿ�
						last.getCurrentBlock().setIsOccupy( false );	//�ͷ�β���ڵ�ռ�õĿ�
						bodyNodes.remove( bodyNodes.size() - 1 );
						last.setPosition( next1, x1, y1 );
						bodyNodes.add( 0, last );
					}else ;
					break;
				case 2:
					int x2 = ( first.getXInBlocks() + 50 ) % 50 ;
					int y2 = ( first.getYInBlocks() - 1 + 50 ) % 50;
					Block next2 = blocks[x2][y2];	//����һ����м��
					if( next2.getHasFood() ){	//ǰ����ʳ��
						next2.setHasFood( false );	//�Ե�ʳ��
						father.getFood().findLocation();
						next2.setIsOccupy( true );	//�����ÿ鱻ռ��
						BodyNode newNode2 = new BodyNode();	//����һ�����
						newNode2.setPosition( next2, x2, y2 );
						bodyNodes.add( 0, newNode2 );	//���ӵ�������ȥ
						father.addNewSnakeNode( newNode2 );	//���ӵ���ʾ�����ȥ
						father.getFather().getMenuPanel().setCnt( bodyNodes.size() );
					}else if( next2.getWallOrRoad() ){	//ǰ����ʯͷ
						isRunning = false;
						father.killGame();
						JOptionPane.showMessageDialog( father.getFather(), "eat stone" );
					}else if( next2.getIsOccupy() ){	//ǰ��������
						isRunning = false;
						father.killGame();
						JOptionPane.showMessageDialog( father.getFather(), "eat itself" );
					}else if( !next2.getWallOrRoad() ){	//ǰ����·
						next2.setIsOccupy( true );	//ռ��ÿ�
						last.getCurrentBlock().setIsOccupy( false );	//�ͷ�β���ڵ�ռ�õĿ�
						bodyNodes.remove( bodyNodes.size() - 1 );
						last.setPosition( next2, x2, y2 );
						bodyNodes.add( 0, last );
					}else ;
					break;
				case 3:
					int x3 = ( first.getXInBlocks() + 50 ) % 50;
					int y3 = ( first.getYInBlocks() + 1 + 50 ) % 50;
					Block next3 = blocks[x3][y3];	//����һ����м��
					if( next3.getHasFood() ){	//ǰ����ʳ��
						next3.setHasFood( false );	//�Ե�ʳ��
						father.getFood().findLocation();
						next3.setIsOccupy( true );	//�����ÿ鱻ռ��
						BodyNode newNode3 = new BodyNode();	//����һ�����
						newNode3.setPosition( next3, x3, y3 );
						bodyNodes.add( 0, newNode3 );	//���ӵ�������ȥ
						father.addNewSnakeNode( newNode3 );	//���ӵ���ʾ�����ȥ
						father.getFather().getMenuPanel().setCnt( bodyNodes.size() );
					}else if( next3.getWallOrRoad() ){	//ǰ����ʯͷ
						isRunning = false;
						father.killGame();
						JOptionPane.showMessageDialog( father.getFather(), "eat stone" );
					}else if( next3.getIsOccupy() ){	//ǰ��������
						isRunning = false;
						father.killGame();
						JOptionPane.showMessageDialog( father.getFather(), "eat itself" );
					}else if( !next3.getWallOrRoad() ){	//ǰ����·
						next3.setIsOccupy( true );	//ռ��ÿ�
						last.getCurrentBlock().setIsOccupy( false );	//�ͷ�β���ڵ�ռ�õĿ�
						bodyNodes.remove( bodyNodes.size() - 1 );
						last.setPosition( next3, x3, y3 );
						bodyNodes.add( 0, last );
					}else ;
					break;
				default:
					;
			}
			
			try{
				thread.sleep( speed );
			}catch( InterruptedException e ){
				e.printStackTrace();
			}
		}
	}
	
	public void move(){
		thread = new Thread( this );
		thread.start();
	}
	
	public void pause(){
		thread.stop();
	}
	
	public int getDirection(){
		return direction;
	}
	
	public void setSpeed( int _speed ){
		this.speed = _speed;
	}
}