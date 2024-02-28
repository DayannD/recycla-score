package com.simplon.recyclascore.services.aws;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.simplon.recyclascore.utils.Utils;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class AwsService {
  private final String bucketName = "recycla-score";
  private final String bucketRegion = "eu-west-3";
  private final AWSCredentials credentials = new BasicAWSCredentials(
    System.getenv("AWS_KEY"),
    System.getenv("AWS_SECRET")
  );
  private final AmazonS3 s3client = AmazonS3ClientBuilder
    .standard()
    .withCredentials(new AWSStaticCredentialsProvider(credentials))
    .withRegion(bucketRegion)
    .build();

  public void uploadFile(MultipartFile multipartFile) throws IOException {
    File file = Utils.convertMultiPartToFile(multipartFile);
    String fileName = multipartFile.getOriginalFilename();
    try {
      s3client.putObject(new PutObjectRequest(bucketName, fileName, file));
    } catch (AmazonServiceException e) {
      throw new RuntimeException("Error in uploading file: " + fileName, e);
    } finally {
      file.delete();
    }
  }

  public void uploadFile(File file, String fileName) {
    try {
      s3client.putObject(new PutObjectRequest(bucketName, fileName, file));
    } catch (AmazonServiceException e) {
      throw new RuntimeException("Error in uploading file: " + fileName, e);
    } finally {
      file.delete();
    }
  }

  public void deleteFile(String fileName) {
    try {
      log.warn("DELETING FILE: " + fileName);
      s3client.deleteObject(bucketName, fileName);
    } catch (AmazonServiceException e) {
      throw new RuntimeException("Error in deleting file: " + fileName, e);
    }
  }

  public byte[] downloadFile(String fileName) {
    log.warn("DOWNLOADING FILE: " + fileName);
    S3Object s3Object = s3client.getObject(bucketName, fileName);
    S3ObjectInputStream inputStream = s3Object.getObjectContent();

    try {
      return inputStream.readAllBytes();
    } catch (IOException e) {
      throw new RuntimeException("Error in downloading file: " + fileName, e);
    }
  }
  public List<Bucket> listBucket() {
    return s3client.listBuckets();
  }
}
