package com.ase.fileservice.services.interfaces;

import com.ase.fileservice.model.FileProperties;

public interface FileService {
  default void deleteFile(Integer fileId) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  default FileProperties createFile(FileProperties fileProperties) {
    throw new UnsupportedOperationException("Not supported yet.");
  }
}
