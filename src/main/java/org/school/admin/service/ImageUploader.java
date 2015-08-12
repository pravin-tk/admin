package org.school.admin.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;

public class ImageUploader {
	
	public void writeToFile(InputStream is, String uploadedFileLocation) {
		try {
			OutputStream out = new FileOutputStream(new File(
					uploadedFileLocation));
			int read = 0;
			byte[] bytes = new byte[102400];
 
			out = new FileOutputStream(new File(uploadedFileLocation));
			while ((read = is.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			out.flush();
			out.close();
			try {
				this.s3Upload(uploadedFileLocation);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
 
			e.printStackTrace();
		}
		
	}
	public String s3Upload(String uploadedFileLocation) throws Exception {

	    String url = "";
	    AmazonS3 s3client = new AmazonS3Client(new BasicAWSCredentials("AKIAICRWCIBOMK46354Q", "8F3NTyy17Vzub1HXIhInDu0HQquH+dkCzI/RvzFQ"));

		try {
		    File file = new File(uploadedFileLocation);
		    String folder = file.getParentFile().getName();
		    PutObjectRequest putObjectRequest = new PutObjectRequest("edbuddy", "images/"+folder+"/"+file.getName(), file);
		    putObjectRequest.withCannedAcl(CannedAccessControlList.PublicRead);
		    s3client.putObject(putObjectRequest);
		} catch (AmazonServiceException ase) {
			ase.printStackTrace();
		} catch (AmazonClientException ace) {
			ace.printStackTrace();
		}
	    return url;
	}
	
}
