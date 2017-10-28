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


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.lang.Math;

public class myc45 {

	public static void main(String[] args) throws IOException {
		// .csv data sets
		String files[] = {"C:\\Users\\RoopKanth\\Desktop\\C4.5-master\\C4.5-master\\data_sets\\house-votes-84.csv"};
		Scanner scan;
		
		
		scan = new Scanner(new File(files[0]));
		String headerLine = scan.nextLine();
		String headers[]  = headerLine.split(",");
		
		// class index is assumed to be the last column
		int classIndex    = headers.length - 1;
		int numAttributes = headers.length - 1;
		
		// store data set attributes
		Attribute attributes[] = new Attribute[numAttributes];
		for(int x = 0; x < numAttributes; x++) {
			attributes[x] = new Attribute(headers[x]);
		}
		
		// for storing classes and class count
		List<String>  classes      = new ArrayList<String>();
		List<Integer> classesCount = new ArrayList<Integer>();
		
		// store are values into respected attributes
		// along with respected classes
		while(scan.hasNextLine()){
			Val data = null;
			String inLine = scan.nextLine();
			String lineData[] = inLine.split(",");
			
			// insert class into classes List
			if(classes.isEmpty()){
				classes.add(lineData[classIndex]);
				classesCount.add(classes.indexOf(lineData[classIndex]), 1);
			}
			else{
				if(!classes.contains(lineData[classIndex])){
					classes.add(lineData[classIndex]);
					classesCount.add(classes.indexOf(lineData[classIndex]), 1);
				}
				else {
					classesCount.set(classes.indexOf(lineData[classIndex]),
							classesCount.get(classes.indexOf(lineData[classIndex])) + 1);
				}
			}
			
			// insert data into attributes
			for(int x = 0; x < numAttributes; x++){
				data = new Val(lineData[x], lineData[classIndex]);
				attributes[x].insertVal(data);
			}
		}
		int totalNumClasses = 0;
		for(int i : classesCount){
			totalNumClasses += i;
		}
		double IofD = calcIofD(classesCount); // Set information criteria
		
		// TESTING DATA 
		Attribute age = new Attribute("age");
		
		Val inV = new Val("30","yes"); age.insertVal(inV);
		inV = new Val("30","yes"); age.insertVal(inV);
		inV = new Val("30","no"); age.insertVal(inV);
		inV = new Val("30","no"); age.insertVal(inV);
		inV = new Val("30","no"); age.insertVal(inV);
		inV = new Val("35","yes"); age.insertVal(inV);
		inV = new Val("35","yes"); age.insertVal(inV);
		inV = new Val("35","yes"); age.insertVal(inV);
		inV = new Val("35","yes"); age.insertVal(inV);
		inV = new Val("40","yes"); age.insertVal(inV);
		inV = new Val("40","yes"); age.insertVal(inV);
		inV = new Val("40","yes"); age.insertVal(inV);
		inV = new Val("40","no"); age.insertVal(inV);
		inV = new Val("40","no"); age.insertVal(inV);
		
		System.out.println(age.toString());

		List<Integer> testCount = new ArrayList<Integer>();
		testCount.add(9);
		testCount.add(5);

		double testIofD = calcIofD(testCount);
		age.setGain(testIofD,14);

		System.out.println("True positive rate: " + testIofD);
		System.out.println("Falsely Possitive rate: " + age.gain);
		
		/*
		for(Attribute a : attributes){
			System.out.println(a.toString());
		}
		*/
		
	}
	
	public static double calcIofD(List<Integer> classesCount){
		double IofD = 0.0;
		double temp = 0.0;
		
		int totalNumClasses = 0;
		for(int i : classesCount){
			totalNumClasses += i;
		}
		
		for(double d : classesCount){
			temp = (-1 * (d/totalNumClasses)) * (Math.log((d/totalNumClasses)) / Math.log(2));
			IofD += temp;
		}
		return IofD;
	}
}