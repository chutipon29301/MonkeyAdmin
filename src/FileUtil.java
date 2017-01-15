import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.HashMap;

public class FileUtil {
	
	private String path;
	private File[] folderList;
	private HashMap<String, String> fileFullNameMap;
	private HashMap<String, String> fileNameMap;
	
	
	public FileUtil(String path) {
		this.path = path;
	}
	
	public ArrayList<String> getFileNameList() {
		ArrayList<String> fileNameList = new ArrayList<String>();
		listFile();
		for (int i = 0; i < folderList.length; i++) {
			fileNameList.add(folderList[i].getName());
		}
		return fileNameList;
	}
	
	public HashMap<String, String> getMapFullNameFromFolder() throws FileNotFoundException{
		fileFullNameMap = new HashMap<String, String>();
		ArrayList<String> fileNameList = getFileNameList();
		for (int i = 0; i < fileNameList.size(); i++) {
			try {
				fileFullNameMap.put(fileNameList.get(i).substring(0, fileNameList.get(i).indexOf('_')), fileNameList.get(i));
			} catch (Exception e) {
			}
		}
		return fileFullNameMap;
	}
	
	public HashMap<String, String> getMapNameFromFolder() throws FileNotFoundException {
		fileNameMap = new HashMap<String, String>();
		ArrayList<String> fileNameList = getFileNameList();
		for (int i = 0; i < fileNameList.size(); i++) {
			try {
				System.out.println(fileNameList.get(i).substring(0, fileNameList.get(i).indexOf('-')) + " " + fileNameList.get(i).substring(fileNameList.get(i).indexOf('-') + 1, fileNameList.get(i).indexOf('_')));
				fileNameMap.put(fileNameList.get(i).substring(0, fileNameList.get(i).indexOf('-')), fileNameList.get(i).substring(fileNameList.get(i).indexOf('-') + 1, fileNameList.get(i).indexOf('_')));
			} catch (Exception e) {
			}
		}
		return fileNameMap;
	}
	
	/**
	 * Get most recent rev of sheet
	 * @param levelName 
	 * @return most recent rev in format (REVx_x) e.g. (REV1_0)
	 */
	public String getRev(String levelName) {
		ArrayList<String> commonFolderName = new ArrayList<String>();
		listFile();
		for (int i = 0; i < folderList.length; i++) {
			if(folderList[i].getName().substring(0, folderList[i].getName().indexOf('(')).equals(levelName)) commonFolderName.add(folderList[i].getName().substring(folderList[i].getName().indexOf('(')));
		}
		int index = -1;
		double revVal = 0.0;
		for (int i = 0; i < commonFolderName.size(); i++) {
			double tempVal = Double.parseDouble(commonFolderName.get(i).charAt(commonFolderName.get(i).indexOf('V') + 1) + "." + commonFolderName.get(i).charAt(commonFolderName.get(i).indexOf('_') + 1));
			if (tempVal > revVal) {
				index = i;
				revVal = tempVal;
			}
		}
		return commonFolderName.get(index);
	}
	
	public boolean copy(String destinationPath) {
		try {
			copyFileUsingFileChannels(new File(path), new File(destinationPath));
			return true;
		} catch (IOException e) {
			return false;
		}
	}
	
	private void copyFileUsingFileChannels(File source, File dest) throws IOException {
		FileChannel inputChannel = null;
		FileChannel outputChannel = null;
		try {
			inputChannel = inputExtracted(source).getChannel();
			outputChannel = outputExtracted(dest).getChannel();
			outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
		} finally {
			inputChannel.close();
			outputChannel.close();
		}
	}

	private FileOutputStream outputExtracted(File dest) throws FileNotFoundException {
		return new FileOutputStream(dest);
	}

	private FileInputStream inputExtracted(File source) throws FileNotFoundException {
		return new FileInputStream(source);
	}
	
	private void listFile() {
		folderList = (new File(path)).listFiles();
	}
	
	public static void main(String[] args) {
		FileUtil test = new FileUtil("\\\\192.169.1.150\\database\\MATH_DB\\MJ");
		try {
			test.getMapNameFromFolder();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
