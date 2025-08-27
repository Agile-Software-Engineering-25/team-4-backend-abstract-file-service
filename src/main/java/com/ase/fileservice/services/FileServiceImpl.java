package com.ase.fileservice.services;

import com.ase.fileservice.services.interfaces.FileService;
import org.springframework.stereotype.Service;

@Service
class FileServiceImpl implements FileService {
  @Override
  public void deleteFile(Integer fileId) {
    FileService.super.deleteFile(fileId);
  }

}
