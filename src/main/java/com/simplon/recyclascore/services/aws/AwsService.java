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
import com.simplon.recyclascore.exception.FileStorageException;
import com.simplon.recyclascore.utils.Utils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class AwsService {
  private static final String BUCKET_NAME = "recycla-score";
  private static final String BUCKET_REGION = "eu-west-3";
  private final AWSCredentials credentials = new BasicAWSCredentials(
    System.getenv("AWS_KEY"),
    System.getenv("AWS_SECRET")
  );
  private final AmazonS3 s3client = AmazonS3ClientBuilder
    .standard()
    .withCredentials(new AWSStaticCredentialsProvider(credentials))
    .withRegion(BUCKET_REGION)
    .build();

  public void uploadFile(MultipartFile multipartFile) throws IOException {
    File file = Utils.convertMultiPartToFile(multipartFile);
    String fileName = multipartFile.getOriginalFilename();
    try {
      s3client.putObject(new PutObjectRequest(BUCKET_NAME, fileName, file));
    } catch (AmazonServiceException e) {
      throw new FileStorageException("Error in uploading file: " + fileName, e);
    } finally {
      Files.delete(file.toPath());
    }
  }

  public void uploadFile(File file, String fileName) throws IOException {
    try {
      s3client.putObject(new PutObjectRequest(BUCKET_NAME, fileName, file));
    } catch (AmazonServiceException e) {
      throw new FileStorageException("Error in uploading file: " + fileName, e);
    } finally {
      Files.delete(file.toPath());
    }
  }

  public void deleteFile(String fileName) {
    try {
      log.warn("DELETING FILE: " + fileName);
      s3client.deleteObject(BUCKET_NAME, fileName);
    } catch (AmazonServiceException e) {
      throw new FileStorageException("Error in deleting file: " + fileName, e);
    }
  }

  public byte[] downloadFile(String fileName) {
    log.warn("DOWNLOADING FILE: " + fileName);
    S3Object s3Object = s3client.getObject(BUCKET_NAME, fileName);
    S3ObjectInputStream inputStream = s3Object.getObjectContent();

    try {
      return inputStream.readAllBytes();
    } catch (IOException e) {
      throw new FileStorageException("Error in downloading file: " + fileName, e);
    }
  }
  public List<Bucket> listBucket() {
    return s3client.listBuckets();
  }
}
