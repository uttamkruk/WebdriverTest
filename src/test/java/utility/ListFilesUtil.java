package utility;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;

import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.apache.commons.io.filefilter.WildcardFileFilter;

public class ListFilesUtil {
	
		    /**
		     * List all the files and folders from a directory
		     * @param directoryName to be listed
		     */
			
		    public void listFilesAndFolders(String directoryName){
		        File directory = new File(directoryName);
		        //get all the files from a directory
		        File[] fList = directory.listFiles();
		        for (File file : fList){
		            System.out.println(file.getName());
		        }
		    }
		    
		    /**
		     * List all the files under a directory
		     * @param directoryName to be listed
		     */
		    
		    public void listFiles(String directoryName){
		        File directory = new File(directoryName);
		        //get all the files from a directory
		        File[] fList = directory.listFiles();
		        for (File file : fList){
		            if (file.isFile()){
		                System.out.println(file.getName());
		            }
		        }
		    }
		    
		    /**
		     * List all the folder under a directory
		     * @param directoryName to be listed
		     */
		    
		    public void listFolders(String directoryName){
		        File directory = new File(directoryName);
		        //get all the files from a directory
		        File[] fList = directory.listFiles();
		        for (File file : fList){
		            if (file.isDirectory()){
		                System.out.println(file.getName());
		            }
		        }
		    }
		    
		    /**
		     * List all files from a directory and its subdirectories
		     * @param directoryName to be listed
		     */
		    
		    public void listFilesAndFilesSubDirectories(String directoryName){
		        File directory = new File(directoryName);
		        //get all the files from a directory
		        File[] fList = directory.listFiles();
		        for (File file : fList){
		            if (file.isFile()){
		                System.out.println(file.getAbsolutePath());
		            } else if (file.isDirectory()){
		                listFilesAndFilesSubDirectories(file.getAbsolutePath());
		            }
		        }
		    }
		    
		    public File getTheNewestFile(String directoryName, String ext) {
			    File theNewestFile = null;
			    File dir = new File(directoryName);
			    FileFilter fileFilter = new WildcardFileFilter("*." + ext);
			    File[] files = dir.listFiles(fileFilter);

			    if (files.length > 0) {
			        /** The newest file comes first **/
			    	Arrays.sort(files, LastModifiedFileComparator.LASTMODIFIED_REVERSE);
			        theNewestFile = files[0];
			    }

			    return theNewestFile;
			}
		    
		    /*public static void main (String[] args){
		        ListFilesUtil listFilesUtil = new ListFilesUtil();
		        //Windows directory example
		        final String directoryPath ="C:\\Testing\\WebdriverTest";
		        listFilesUtil.listFiles(directoryPath);
		        listFilesUtil.listFilesAndFolders(directoryPath);
		        listFilesUtil.listFolders(directoryPath);
		        listFilesUtil.listFilesAndFilesSubDirectories(directoryPath);
		    }*/
}