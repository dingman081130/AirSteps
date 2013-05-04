package maze;
class MazePoint 
{
	int x, y;
	boolean haveWallOrRoad;
	WallOrRoad wallOrRoad = null;
	public MazePoint(int x, int y)			//��ʼ������ÿ��mazePoint�ڴ����е�λ��
	{
		this.x = x;
		this.y = y;
	}
	
	public boolean isHaveWallOrRoad()
	{
		return haveWallOrRoad;
	}
	
	public void setIsWallOrRoad(boolean boo)
	{
		haveWallOrRoad = boo;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public boolean equals(MazePoint p)
	{
		if(p.getX() == this.getX() && p.getY() == this.getY())
			return true;
		else return false;
	}
	
	public void setWallOrRoad(WallOrRoad obj)
	{
		wallOrRoad = obj;
	}
	
	public WallOrRoad getWallOrRoad()
	{
		return wallOrRoad;
	}
}
