package omg.pub.mylist;

public class ListItem
{
	private String pid,pn,type,dt,t;

	public void setPid(String pid)
	{
		this.pid = pid;
	}

	public String getPid()
	{
		return pid;
	}

	public void setPn(String pn)
	{
		this.pn = pn;
	}

	public String getPn()
	{
		return pn;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public String getType()
	{
		return type;
	}

	public void setDt(String dt)
	{
		this.dt = dt;
	}

	public String getDt()
	{
		return dt;
	}

	public void setT(String t)
	{
		this.t = t;
	}

	public String getT()
	{
		return t;
	}
	ListItem(){
	}
	ListItem(String pid,String pn,String type, String dateTime,String text){
		this.pid=pid;
		this.pn=pn;
		this.type=type;
		this.dt=dateTime;
		this.t=text;
	}
}
