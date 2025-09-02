package com.ase.fileservice.controllers;

import java.time.OffsetDateTime;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.ase.fileservice.api.FilesApi;
import com.ase.fileservice.interfaces.FileService;
import com.ase.fileservice.model.AccessRights;
import com.ase.fileservice.model.ErrorSchema;
import com.ase.fileservice.model.FileProperties;
import com.ase.fileservice.model.SearchResult;
import com.ase.fileservice.model.UpdateFilePropertiesRequest;
import com.ase.fileservice.model.UpdateFilePropertiesRequestMetadata;

@RestController
public class FilesController implements FilesApi {

  private static final Logger LOGGER =
      LoggerFactory.getLogger(FilesController.class);

  private final FileService fileService;

  public FilesController(@Autowired FileService fileService) {
    this.fileService = fileService;
  }

  @Override
  public ResponseEntity<Integer> deleteFile(Integer fileId) {
    fileService.deleteFile(fileId);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @Override
  public ResponseEntity<Resource> getFile(Integer fileId) {
    var content = fileService.getFile(fileId);
    var resource = content.toResource();
    return new ResponseEntity<>(resource, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<FileProperties> getFileProperties(Integer fileId) {
    var properties = fileService.getFileProperties(fileId);
    return new ResponseEntity<>(properties, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<SearchResult> searchFiles(
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
    var searchResult = fileService.searchFiles(
        page,
        limit,
        option,
        sort,
        asc,
        fuzziness,
        time,
        start,
        end,
        tag,
        location,
        owner,
        access);
    return new ResponseEntity<>(searchResult, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<FileProperties> updateFile(
      Integer fileId,
      String name,
      String mimeType,
      MultipartFile file,
      Integer ownerId,
      List<String> tags,
      AccessRights access,
      List<Integer> locations) {

    var metadata = new UpdateFilePropertiesRequestMetadata();
    metadata.setName(name);
    metadata.setOwnerId(ownerId);

    var fileProperties = new UpdateFilePropertiesRequest();
    fileProperties.setMetadata(metadata);
    fileProperties.setAccess(access);
    fileProperties.setLocations(locations);
    fileProperties.setTags(tags);

    var modifiedProperties = fileService.updateFile(
        fileId,
        fileProperties,
        mimeType,
        file);
    return new ResponseEntity<>(
        modifiedProperties,
        HttpStatus.OK);
  }

  @Override
  public ResponseEntity<FileProperties> updateFileContent(
      Integer fileId,
      MultipartFile file,
      String mimeType) {
    var fileProperties = fileService.updateFileContent(fileId, file, mimeType);
    return new ResponseEntity<>(fileProperties, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<Void> updateFileProperties(
      Integer fileId,
      UpdateFilePropertiesRequest updateFilePropertiesRequest) {
    fileService.updateFileProperties(fileId, updateFilePropertiesRequest);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @Override
  public ResponseEntity<FileProperties> uploadFile(
      String name,
      String mimeType,
      MultipartFile file,
      Integer ownerId,
      List<String> tags,
      AccessRights access,
      List<Integer> locations) {

    var metadata = new UpdateFilePropertiesRequestMetadata();
    metadata.setName(name);
    metadata.setOwnerId(ownerId);

    var fileProperties = new UpdateFilePropertiesRequest();
    fileProperties.setMetadata(metadata);
    fileProperties.setAccess(access);
    fileProperties.setLocations(locations);
    fileProperties.setTags(tags);

    var created = fileService.uploadFile(fileProperties, mimeType, file);
    return new ResponseEntity<>(created, HttpStatus.OK);
  }

  /**
   * Method for handling the Rest of Exceptions that are thrown during Runtime.
   */
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(Exception.class)
  public ErrorSchema handleExceptions(Exception ex) {

    LOGGER.error("Exception: {}", ex.getMessage(), ex);

    ErrorSchema error = new ErrorSchema();
    error.setTitle("Exception");
    error.setDetail(ex.getMessage());
    error.status(HttpStatus.INTERNAL_SERVER_ERROR.value());
    return error;
  }
}
