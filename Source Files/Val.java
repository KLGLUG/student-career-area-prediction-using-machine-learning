/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package machinelearning;

/**
 *
 * @author RoopKanth
 */
public class Val {
	public String valueName = "";
	public String itClass   = "";

	public Val(String name, String inClass){
		this.valueName = new String(name);
		this.itClass   = new String(inClass);
	}

	public boolean isNameEqual(Val inV){
		if(this.valueName.equals(inV.valueName))
			return true;
		return false;
	}
}