package com.ase.fileservice.controllers;

import java.time.OffsetDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.ase.fileservice.api.FilesApi;
import com.ase.fileservice.model.AccessRights;
import com.ase.fileservice.model.FileProperties;
import com.ase.fileservice.model.SearchResult;
import com.ase.fileservice.model.UpdateFilePropertiesRequest;
import com.ase.fileservice.services.interfaces.FileService;

@RestController
public class FilesController implements FilesApi {

  private final FileService fileService;

  public FilesController(@Autowired FileService fileService) {
    this.fileService = fileService;
  }

  @Override
  public ResponseEntity<Integer> deleteFile(Integer fileId) {
    // TODO: Global Exception Handling
    fileService.deleteFile(fileId);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @Override
  public ResponseEntity<Void> getFile(Integer fileId) {
    return FilesApi.super.getFile(fileId);
  }

  @Override
  public ResponseEntity<FileProperties> getFileProperties(Integer fileId) {
    return FilesApi.super.getFileProperties(fileId);
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
      List<List<Integer>> location,
      List<String> owner,
      List<Object> access) {
    return FilesApi.super.searchFiles(
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
    return FilesApi.super.updateFile(
        fileId,
        name,
        mimeType,
        file,
        ownerId,
        tags,
        access,
        locations);
  }

  @Override
  public ResponseEntity<FileProperties> updateFileContent(
      Integer fileId,
      MultipartFile file,
      String mimeType) {
    return FilesApi.super.updateFileContent(
        fileId,
        file,
        mimeType);
  }

  @Override
  public ResponseEntity<Void> updateFileProperties(
      Integer fileId,
      UpdateFilePropertiesRequest updateFilePropertiesRequest) {
    return FilesApi.super.updateFileProperties(
        fileId,
        updateFilePropertiesRequest);
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
    return FilesApi.super.uploadFile(
        name,
        mimeType,
        file,
        ownerId,
        tags,
        access,
        locations);
  }
}
