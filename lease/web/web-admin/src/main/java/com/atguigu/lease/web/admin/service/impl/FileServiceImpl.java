package com.atguigu.lease.web.admin.service.impl;

import com.atguigu.lease.api.apartment.FileClient;
import com.atguigu.lease.web.admin.service.FileService;
import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileClient fileClient;

    @Override
    public String upload(MultipartFile file) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        return fileClient.upload(file).getData();
    }
}
