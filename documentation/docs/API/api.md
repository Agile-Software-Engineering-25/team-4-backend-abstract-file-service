# API

## General Structure

### `/files`

#### GET
Search the files with given query parameters.
To use negation, add `!` as the first character.

**Parameters for filtering:**

- `tag`
- `name`
- `owner`
- `access`
- `location`: Folder to search in.
- `option`: For the above parameters, the filters can be combined with `and` / `or`
- `time`: What timestamp to use (`createdAt` / `updatedAt`)
  - `start`: Beginning of the time range
  - `end`: End of the time range
- `sort`: The datatype to sort on
- `asc`: Boolean for ascending / descending order
- `fuzziness`: How strict the input must match for searching file names.(0–5)

**Pagination Parameters:**

- `page`: Page number to return
- `limit`: Number of entries per page

#### POST
Upload a file.
To create a directory, omit the `file` parameter.

---

### `/files/{file-id}`

#### GET
Get the properties of a file by ID.

#### PATCH
Update selected properties of the file.

#### POST
Update the **content** of the file.

#### PUT
Completely update the file.
Current properties and content will be replaced with the provided data.

#### DELETE
Delete the file.

---

### `/files/{file-id}/content`

#### GET
Retrieve the binary content of the file.

---

## Design Decisions

- **Splitting content and properties**
  You don't need to download the whole file just to get some information about it. So a separation between the binary data (which could be large) and the properties is desired.

- **Don't split the resource**
  Files are considered a single resource. Therefore, the API should not be split into multiple endpoints (e.g: /tags, /files, /properties). To update the resource, use a single payload in your request depending on your needs.
  You have access to all the capabilities, but by not providing extra data, you can use basic functionality like upload, download, and delete with minimal setup.

---

## Working with the API in Java

The files for the API are generated in with the openapigenerator plugin and the generator for `spring`:


To import:

- Use `com.ase.fileservice.api` for the API
- Use `com.ase.fileservice.model` for the models used by the API

---

## TODO

Coming changes to the API:

- Setting minimum and maximum values
  *(Depends on the implementation — research is ongoing)*

---

### Definition:
The API is defined in [team-4-backend-abstract-file-service/src/main/resources/META-INF/fileService.openapi.yaml](https://github.com/Agile-Software-Engineering-25/team-4-backend-abstract-file-service/blob/main/src/main/resources/META-INF/fileService.openapi.yaml)

## Update Log
- fixed return type of `files/{file-id}/content` to return the Resource