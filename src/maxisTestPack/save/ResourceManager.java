package maxisTestPack.save;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;

import maxisTestPack.Main;

/**
 * this class contains the methods
 * to load and unload data
 * @author Maxi
 *
 */
public class ResourceManager {
	
	
	/**
	 * saves data on given FileName
	 * the data to save has to import Serializable!
	 * The save format is .save
	 * @param data
	 * @param fileName
	 */
	public static void save(Serializable data, String fileName) {
		try {
			FileOutputStream fileOut = new FileOutputStream(fileName);
			ObjectOutputStream oos = new ObjectOutputStream(fileOut);
			oos.writeObject(data);
			oos.close();
		}
		catch(Exception e){
			System.err.println("Coudn't save data with fileName: " + fileName);
			e.printStackTrace();
		}
	}
	
	/**
	 * loads data on given fileName
	 * @param fileName
	 * @return an Object which you can cast to the 
	 * Object which you need, contains save stuff
	 * 
	 */
	public static Object load(String fileName) {
		try {
			FileInputStream fileIn = new FileInputStream(fileName);
			ObjectInputStream ois = new ObjectInputStream(fileIn);
			Object out = ois.readObject();
			ois.close();
			return out;
		}
		catch(Exception e){
			System.err.println("Coudn't load data with fileName: " + fileName);
		}
		
		return null;
	}
}
