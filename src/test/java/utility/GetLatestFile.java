package utility;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;

import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.apache.commons.io.filefilter.WildcardFileFilter;

public class GetLatestFile {
	
	public static File getTheNewestFile(String filePath, String ext) {
	    File theNewestFile = null;
	    File dir = new File(filePath);
	    FileFilter fileFilter = new WildcardFileFilter("*." + ext);
	    File[] files = dir.listFiles(fileFilter);

	    if (files.length > 0) {
	        /** The newest file comes first **/
	    	Arrays.sort(files, LastModifiedFileComparator.LASTMODIFIED_REVERSE);
	        theNewestFile = files[0];
	    }
	    System.out.println("The last modified latest file is : " +theNewestFile);
	    return theNewestFile;
	}
	
	
	 /**
     * List all files from a directory and its subdirectories
     * @param directoryName to be listed
     */
	
	 public static File listFilesAndFilesSubDirectories(String directoryName, String ext){
		    File theNewestFile = null;
	        File directory = new File(directoryName);
	        FileFilter fileFilter = new WildcardFileFilter("*." + ext);
	        //get all the files from a directory
	        File[] fList = directory.listFiles();
	        for (File file : fList){
	            if (file.isFile()){
	                System.out.println(file.getAbsolutePath());
	                Arrays.sort(fList, LastModifiedFileComparator.LASTMODIFIED_REVERSE);
	    	        theNewestFile = fList[0];
	                
	            } else if (file.isDirectory()){
	                listFilesAndFilesSubDirectories(file.getAbsolutePath(),"html");
	            }
	        }
	        return theNewestFile;
	    }
	
	

}
