package agkz.mods.lazerz.common;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

import agkz.mods.lazerz.Lazerz;

public class DataUtil {
	
	public byte[] convertObjectToByteArray(Object object) {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutput out = null;
			
			byte[] managerBytes = null;
			try {
				out = new ObjectOutputStream(bos);
				out.writeObject(object);
				managerBytes = bos.toByteArray();
				
			} catch (IOException e) {
				Lazerz.logger.warning("Error converting manager to ItemStack: " + e.getMessage());
			} finally {
				try {
					bos.close();
					out.close();
				} catch (IOException e) {
					Lazerz.logger.warning("Error closing ByteArrayOutputStream " + e.getMessage());
				}
			}
			return managerBytes;
	}
	
	public Object convertByteArrayToObject(byte[] objectBytes) {
		ByteArrayInputStream bis = new ByteArrayInputStream(objectBytes);
		ObjectInput in = null;
		
		Object manager = null;
		try {
			in = new ObjectInputStream(bis);
			manager = in.readObject();
		} catch (IOException e) {
			Lazerz.logger.warning("Error converting ByteArrayInputStream to ObjectInput: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			Lazerz.logger.warning("Error reading ObjectInputStream to Object: " + e.getMessage());
		} finally {
			try {
				bis.close();
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return manager;
		
	}
}
