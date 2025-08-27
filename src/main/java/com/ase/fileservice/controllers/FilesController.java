package com.ase.fileservice.controllers;

import com.ase.fileservice.model.AccessRights;
import com.ase.fileservice.model.FileProperties;
import com.ase.fileservice.model.SearchResult;
import com.ase.fileservice.model.UpdateFilePropertiesRequest;
import com.ase.fileservice.services.interfaces.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import com.ase.fileservice.api.FilesApi;
import org.springframework.web.multipart.MultipartFile;
import java.time.OffsetDateTime;
import java.util.List;

@RestController
public class FilesController implements FilesApi {

  private final FileService fileService;

  public FilesController(@Autowired FileService fileService) {
    this.fileService = fileService;
  }

  /**
   * DELETE /files/{file-id}
   * Deletes a file.
   *
   * @param fileId ID to identify the file via a query parameter. (required)
   * @return Id of a file. (status code 200)
   * or bad request (status code 400)
   * or unauthorized (status code 401)
   * or Forbidden (status code 403)
   * or Not Found (status code 404)
   * or internal server error (status code 500)
   */
  @Override
  public ResponseEntity<Integer> deleteFile(Integer fileId) {
    // TODO: Global Exception Handling
    fileService.deleteFile(fileId);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  /**
   * GET /files/{file-id}/content
   * Returns the file content e.g: The binary data.
   *
   * @param fileId ID to identify the file via a query parameter. (required)
   * @return Returns the file. (status code 200)
   * or bad request (status code 400)
   * or unauthorized (status code 401)
   * or Forbidden (status code 403)
   * or Not Found (status code 404)
   * or unsupported media type (status code 415)
   * or internal server error (status code 500)
   */
  @Override
  public ResponseEntity<Void> getFile(Integer fileId) {
    return FilesApi.super.getFile(fileId);
  }

  /**
   * GET /files/{file-id}
   * Returns Properties of the file.
   *
   * @param fileId ID to identify the file via a query parameter. (required)
   * @return Properties of the file (status code 200)
   * or bad request (status code 400)
   * or unauthorized (status code 401)
   * or Forbidden (status code 403)
   * or Not Found (status code 404)
   * or unsupported media type (status code 415)
   * or internal server error (status code 500)
   */
  @Override
  public ResponseEntity<FileProperties> getFileProperties(Integer fileId) {
    return FilesApi.super.getFileProperties(fileId);
  }

  /**
   * GET /files
   * Search with given query parameters.
   *
   * @param page      What page should be shown. (optional, default to 1)
   * @param limit     Number of files in one page. (optional, default to 20)
   * @param option    How the given data should be combined to get the search results. (optional)
   * @param sort      The datatype used for sorting. (optional)
   * @param asc       If the data should be sorted in ascending order. (optional, default to true)
   * @param fuzziness Degree of fuzziness for the search of filenames. 0 &#x3D; exact match, 1 &#x3D; low, 2 &#x3D; medium, high &#x3D; 3+  (optional, default to 1)
   * @param time      What timestamp to search in. (optional)
   * @param start     Start time for a time range filter. (optional)
   * @param end       End time for a time range filter. (optional)
   * @param tag       Filter by tag. Prefix with \&quot;!\&quot; to exclude a tag. Example: tag&#x3D;cats&amp;tag&#x3D;!dogs  (optional)
   * @param location  Filter by location.  (optional)
   * @param owner     Filter by ownerId. Prefix with \&quot;!\&quot; to exclude a ownerId. Example: tag&#x3D;1234&amp;tag&#x3D;!2344  (optional)
   * @param access    (optional)
   * @return Gets all the file-ids, where the tag is included. (status code 200)
   * or bad request (status code 400)
   * or unauthorized (status code 401)
   * or Forbidden (status code 403)
   * or unsupported media type (status code 415)
   * or internal server error (status code 500)
   */
  @Override
  public ResponseEntity<SearchResult> searchFiles(Integer page, Integer limit, String option, String sort, Boolean asc, Integer fuzziness, String time, OffsetDateTime start, OffsetDateTime end, List<String> tag, List<List<Integer>> location, List<String> owner, List<Object> access) {
    return FilesApi.super.searchFiles(page, limit, option, sort, asc, fuzziness, time, start, end, tag, location, owner, access);
  }

  /**
   * PUT /files/{file-id}
   * Update a file.
   *
   * @param fileId    ID to identify the file via a query parameter. (required)
   * @param name      The name of the file. (required)
   * @param mimeType  MIME type of the file. For Reference: https://www.iana.org/assignments/media-types/media-types.xhtml (required)
   * @param file      The file to upload. Leave empty to create directory. (optional)
   * @param ownerId   Id to identify the Owner (optional)
   * @param tags      Tags associated with that file (optional)
   * @param access    (optional)
   * @param locations (optional)
   * @return File uploaded successfully (status code 201)
   * or bad request (status code 400)
   * or unauthorized (status code 401)
   * or Forbidden (status code 403)
   * or unsupported media type (status code 415)
   * or internal server error (status code 500)
   */
  @Override
  public ResponseEntity<FileProperties> updateFile(Integer fileId, String name, String mimeType, MultipartFile file, Integer ownerId, List<String> tags, AccessRights access, List<Integer> locations) {
    return FilesApi.super.updateFile(fileId, name, mimeType, file, ownerId, tags, access, locations);
  }

  /**
   * POST /files/{file-id}
   * Replaces the file content while preserving metadata.
   *
   * @param fileId   ID to identify the file via a query parameter. (required)
   * @param file     The new file content (required)
   * @param mimeType Update MIME type if content type changes (optional)
   * @return File content updated successfully (status code 200)
   * or bad request (status code 400)
   * or unauthorized (status code 401)
   * or Forbidden (status code 403)
   * or Not Found (status code 404)
   * or unsupported media type (status code 415)
   * or internal server error (status code 500)
   */
  @Override
  public ResponseEntity<FileProperties> updateFileContent(Integer fileId, MultipartFile file, String mimeType) {
    return FilesApi.super.updateFileContent(fileId, file, mimeType);
  }

  /**
   * PATCH /files/{file-id}
   * Updates the properties of a file.
   *
   * @param fileId                      ID to identify the file via a query parameter. (required)
   * @param updateFilePropertiesRequest (optional)
   * @return successfully processed the request. (status code 200)
   * or bad request (status code 400)
   * or unauthorized (status code 401)
   * or Forbidden (status code 403)
   * or unsupported media type (status code 415)
   * or internal server error (status code 500)
   */
  @Override
  public ResponseEntity<Void> updateFileProperties(Integer fileId, UpdateFilePropertiesRequest updateFilePropertiesRequest) {
    return FilesApi.super.updateFileProperties(fileId, updateFilePropertiesRequest);
  }

  /**
   * POST /files
   * Upload a file.
   *
   * @param name      The name of the file. (required)
   * @param mimeType  MIME type of the file. For Reference: https://www.iana.org/assignments/media-types/media-types.xhtml (required)
   * @param file      The file to upload. Leave empty to create directory. (optional)
   * @param ownerId   Id to identify the Owner (optional)
   * @param tags      Tags associated with that file (optional)
   * @param access    (optional)
   * @param locations (optional)
   * @return File uploaded successfully (status code 201)
   * or bad request (status code 400)
   * or unauthorized (status code 401)
   * or Forbidden (status code 403)
   * or unsupported media type (status code 415)
   * or internal server error (status code 500)
   */
  @Override
  public ResponseEntity<FileProperties> uploadFile(String name, String mimeType, MultipartFile file, Integer ownerId, List<String> tags, AccessRights access, List<Integer> locations) {
    return FilesApi.super.uploadFile(name, mimeType, file, ownerId, tags, access, locations);
  }
}
