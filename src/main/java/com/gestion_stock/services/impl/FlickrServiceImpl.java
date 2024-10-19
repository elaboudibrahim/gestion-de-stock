package com.gestion_stock.services.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutionException;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.uploader.UploadMetaData;
import com.gestion_stock.services.FlickrService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FlickrServiceImpl implements FlickrService {
  @Autowired
  private Flickr flickr;
  @Override
  @SneakyThrows
  public String savePhoto(InputStream photo, String title) {
    UploadMetaData uploadMetaData = new UploadMetaData();
    uploadMetaData.setTitle(title);

    String photoId = flickr.getUploader().upload(photo, uploadMetaData);
    return flickr.getPhotosInterface().getPhoto(photoId).getMedium640Url();
  }

}
