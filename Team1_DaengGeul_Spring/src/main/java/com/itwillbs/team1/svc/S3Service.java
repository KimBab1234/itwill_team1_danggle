package com.itwillbs.team1.svc;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Service
@PropertySource("classpath:awsS3.properties")
public class S3Service {

	private AmazonS3 s3Client;
	
	@Value("${cloud.aws.credentials.accessKey}")
	private String accessKey;

	@Value("${cloud.aws.credentials.secretKey}")
	private String secretKey;

	@Value("${cloud.aws.s3.bucket}")
	private String bucket;

	@Value("${cloud.aws.region.static}")
	private String region;

	public void setS3Client() {
		AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);

		s3Client = AmazonS3ClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(credentials))
				.withRegion(this.region)
				.build();
	}

	public void upload(MultipartFile file, String fileName) throws IOException {
		
		if(s3Client==null) {
			setS3Client();
		}
		s3Client.putObject(new PutObjectRequest(bucket, "profileImg/"+fileName, file.getInputStream(), null)
				.withCannedAcl(CannedAccessControlList.PublicRead));
		
	}
	
	public void delete(String deleteFileName) {
		if(s3Client==null) {
			setS3Client();
		}
		s3Client.deleteObject(bucket, deleteFileName);
	}
	
}
