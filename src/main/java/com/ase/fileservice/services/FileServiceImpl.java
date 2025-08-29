package com.ase.fileservice.services;

import org.springframework.stereotype.Service;
import com.ase.fileservice.interfaces.FileContent;
import com.ase.fileservice.interfaces.FileService;

@Service
class FileServiceImpl implements FileService {

  public FileContent<?> getFile(Integer fileId) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

}
