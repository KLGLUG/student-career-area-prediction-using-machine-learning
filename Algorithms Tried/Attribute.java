/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package machinelearning;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author RoopKanth
 */

   


public class Attribute {
	public String       name   = new String();
	public List<Values> values = new ArrayList<Values>();
	public double       gain   = 0.0;
	
	public Attribute(String name){
		this.name = name;
	}
	
	public void setGain(double IofD, int totalNumClasses){
		int totalValClasses = 0;
		double temp = 0.0;
		for(Values v : values){
			v.setGain();
			for(int i : v.classesCount){
				totalValClasses += i;
			}
			gain += (totalValClasses/(double)totalNumClasses) * v.gain;
			totalValClasses = 0;
		}
		this.gain = IofD - this.gain;
	}
	
	public void insertVal(Val inVal){
		if(this.values.isEmpty()){
			values.add(new Values(inVal.valueName, inVal.itClass));
		}
		else{
			for(Values v : values){
				if(v.valueName.equals(inVal.valueName)){
					v.update(inVal);
					return;
				}
			}
			values.add(new Values(inVal.valueName, inVal.itClass));
		}
	}
	
	public String toString(){
		String out = new String("attribute: " + this.name + "\n");
		for(Values v : values){
			out += "\tvalue: " + v.valueName + ", ";
			out += "\n\t\tclasses: ";
			for(String c : v.classes){
				out += c + ", ";
			}
			out += "\n\t\tcounts: ";
			for(Integer i : v.classesCount){
				out += i + ", ";
			}
			out += "\n";
		}
		
		return out;
	}

}

