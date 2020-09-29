package com.br.alcateiadev.leitorftp.leitorftp.service.ftp;


import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.S3ClientOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class UploadS3Service {

    @Value("${s3.access.key}")
    private String accessKey;

    @Value("${s3.secret.key}")
    private String secretKey;

    @Value("${s3.host}")
    private String s3Host;

    @Value("${s3.bucket}")
    private String s3Bucket;

    public FileTransfer execute(String nameFile, File file) throws IOException {

        AWSCredentials credentials = new BasicAWSCredentials(
                accessKey,
                secretKey
        );

        AmazonS3Client newClient = new AmazonS3Client(credentials,
                new ClientConfiguration().withSignerOverride("S3SignerType"));

        newClient.setS3ClientOptions(S3ClientOptions.builder().setPathStyleAccess(true).build());
        newClient.setEndpoint(s3Host);

        String newNameFile = createName(nameFile);

        newClient.putObject(s3Bucket, newNameFile, file);

       return FileTransfer
               .builder()
               .path(newNameFile)
               .build();
    }

    private String createName(String nameFile) {
        String date = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        return date.concat("-").concat(nameFile);
    }
}