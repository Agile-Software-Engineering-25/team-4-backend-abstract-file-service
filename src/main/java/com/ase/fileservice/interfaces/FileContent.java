package com.ase.fileservice.interfaces;

import org.springframework.core.io.Resource;

public interface FileContent<T> {
  T getData();

  Resource toResource();
}
