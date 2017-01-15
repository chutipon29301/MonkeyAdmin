import java.io.FileNotFoundException;

public class DecodeSubjectName {
	private String path = "";
	
	public DecodeSubjectName(String levelName) {
		path += Index.DB_LOCATION;
		switch (levelName.charAt(0)) {
		case 'M':
			path += "MATH_DB";
			break;
		case 'P':
			path += "PHYSICS_DB";
			break;
		default:
			break;
		}
		path += "\\" + levelName.substring(0, levelName.indexOf('-')) + "\\";
		FileUtil tempFolder = new FileUtil(path);
		try {
			path += tempFolder.getMapFromFolder().get(levelName.substring(0, levelName.indexOf('0') - 1)) + "\\";
		} catch (FileNotFoundException e) {
		}
		FileUtil revGetter = new FileUtil(path);
		path += levelName + revGetter.getRev(levelName) + "\\" + levelName + "VDO.mp4";
		
	}
	
	@Override
	public String toString() {
		return path;
	}
}
