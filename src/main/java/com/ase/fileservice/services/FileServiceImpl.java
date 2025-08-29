package com.ase.fileservice.services;

import org.springframework.stereotype.Service;
import com.ase.fileservice.services.interfaces.FileService;

@Service
class FileServiceImpl implements FileService {
  @Override
  public void deleteFile(Integer fileId) {
    FileService.super.deleteFile(fileId);
  }

}
