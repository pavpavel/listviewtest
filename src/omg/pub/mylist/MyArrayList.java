package omg.pub.mylist;
import java.util.*;

public class MyArrayList
{
	private ArrayList<String> al;
	private ArrayList<String> pid;
	private ArrayList<String> t;
	private ArrayList<String> pname;
	
	
	public MyArrayList(){
		al = new ArrayList<String>();
		pid = new ArrayList<String>();
		t = new ArrayList<String>();
		pname = new ArrayList<String>();
	}
	public void add(String lines,String processID,String type, String processName){
		al.add(lines);
		pid.add(processID);
		t.add(type);
		pname.add(processName);
	}
	public String getPID(Integer p){
		return pid.get(p);
	}
	public ArrayList<String> getPIDs(){
		return pid;
	}
	public String getLine(Integer p){
		return al.get(p);
	}
	public ArrayList<String> getLines(){
		return al;
	}
	public String getProcessName(Integer p){
		return pname.get(p);
	}
	public ArrayList<String> getProcessNames(){
		return pname;
	}
	public String getType(Integer p){
		return t.get(p);
	}
	public ArrayList<String> getTypes(){
		return t;
	}
	
}
