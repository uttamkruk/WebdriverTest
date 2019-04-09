//Zip files from Directory and save it to another folder -- One liner code

package utility;

import java.io.File;

import org.zeroturnaround.zip.ZipUtil;

public class ZipFiles {

	public static void zipReport (File srcDir , File destDir){
		
		//ZipUtil.pack(new File(System.getProperty("user.dir")+"\\target\\surefire-reports"), new File(System.getProperty("user.dir")+"\\outfiles\\TestReport.zip"));
		
		//Zip Files
		ZipUtil.pack(srcDir , destDir);
        System.out.println("File zipped ...");
        
        //Unzip Files
        File outputDir = new File(System.getProperty("user.dir")+"\\outfiles");
        if(!(outputDir.exists())){
        	outputDir.mkdir();
        }
        ZipUtil.unpack(destDir, outputDir);
	
	}

}


//Zip files from Directory and save it to another folder -- Lengthy code

/*import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipFiles
{
 List<String> fileList;
 private static final String OUTPUT_ZIP_FILE = System.getProperty("user.dir")+"\\Misc\\zipReport\\surfire-reports.zip";
 private static final String SOURCE_FOLDER = System.getProperty("user.dir")+"\\target\\surefire-reports";

 ZipFiles(){
	fileList = new ArrayList<String>();
 }

 public static void main( String[] args )
 {
 	ZipFiles ZipFiles = new ZipFiles();
 	ZipFiles.generateFileList(new File(SOURCE_FOLDER));
 	ZipFiles.zipIt(OUTPUT_ZIP_FILE);
 }

 *//**
  * Zip it
  * @param zipFile output ZIP file location
  *//*
 public void zipIt(String zipFile){

  byte[] buffer = new byte[1024];

  try{

 	FileOutputStream fos = new FileOutputStream(zipFile);
 	ZipOutputStream zos = new ZipOutputStream(fos);

 	System.out.println("Output to Zip : " + zipFile);

 	for(String file : this.fileList){

 		System.out.println("File Added : " + file);
 		ZipEntry ze= new ZipEntry(file);
     	zos.putNextEntry(ze);

     	FileInputStream in =
                    new FileInputStream(SOURCE_FOLDER + File.separator + file);

     	int len;
     	while ((len = in.read(buffer)) > 0) {
     		zos.write(buffer, 0, len);
     	}

     	in.close();
 	}

 	zos.closeEntry();
 	//remember close it
 	zos.close();

 	System.out.println("Done");
 }catch(IOException ex){
    ex.printStackTrace();
 }
}

 *//**
  * Traverse a directory and get all files,
  * and add the file into fileList
  * @param node file or directory
  *//*
 public void generateFileList(File node){

 	//add file only
	if(node.isFile()){
		fileList.add(generateZipEntry(node.getAbsoluteFile().toString()));
	}

	if(node.isDirectory()){
		String[] subNote = node.list();
		for(String filename : subNote){
			generateFileList(new File(node, filename));
		}
	}

 }

 *//**
  * Format the file path for zip
  * @param file file path
  * @return Formatted file path
  *//*
 private String generateZipEntry(String file){
 	return file.substring(SOURCE_FOLDER.length()+1, file.length());
 }
}*/
