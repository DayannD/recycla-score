package com.simplon.recyclascore.web.controller.aws;

import com.amazonaws.services.s3.model.Bucket;
import com.simplon.recyclascore.services.aws.AwsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/aws")
public class AwsController {

  private final AwsService awsService;

  @GetMapping("/download/{fileName}")
  public ResponseEntity<byte[]> downloadFile(@PathVariable String fileName) {
    byte[] data = this.awsService.downloadFile(fileName);
    return ResponseEntity.ok().body(data);
  }


  @PostMapping("/upload")
  @ResponseStatus(HttpStatus.OK)
  public void uploadFile(@RequestParam("file") MultipartFile file) {
    try {
      this.awsService.uploadFile(file);
    } catch (Exception e) {
      throw new RuntimeException("Error in uploading file: " + file.getOriginalFilename(), e);
    }
  }

  @GetMapping("/list")
  public ResponseEntity<List<Bucket>> listFiles() {
    return ResponseEntity.ok().body(this.awsService.listBucket());
  }
}
