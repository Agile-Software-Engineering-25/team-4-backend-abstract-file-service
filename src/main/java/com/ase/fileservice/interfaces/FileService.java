package com.ase.fileservice.interfaces;

import java.time.OffsetDateTime;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import com.ase.fileservice.model.FileProperties;
import com.ase.fileservice.model.SearchResult;
import com.ase.fileservice.model.UpdateFilePropertiesRequest;

public interface FileService {

  FileContent<?> getFile(Integer fileId);

  default void deleteFile(Integer fileId) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  default FileProperties getFileProperties(Integer fileId) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  default FileProperties uploadFile(
      UpdateFilePropertiesRequest updateFilePropertiesRequest,
      String mimeType,
      MultipartFile file) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  default FileProperties updateFile(
      Integer fileId,
      UpdateFilePropertiesRequest updateFilePropertiesRequest,
      String mimeType,
      MultipartFile file) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  default void updateFileProperties(
      Integer fileId,
      UpdateFilePropertiesRequest updateFilePropertiesRequest) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  default FileProperties updateFileContent(
      Integer fileId,
      MultipartFile file,
      String mimeType) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  default SearchResult searchFiles(
      Integer page,
      Integer limit,
      String option,
      String sort,
      Boolean asc,
      Integer fuzziness,
      String time,
      OffsetDateTime start,
      OffsetDateTime end,
      List<String> tag,
      List<Integer> location,
      List<String> owner,
      List<Object> access) {
    throw new UnsupportedOperationException("Not supported yet.");
  }
}
