package com.br.alcateiadev.leitorftp.leitorftp.gateway.repository;

import com.br.alcateiadev.leitorftp.leitorftp.domain.FileSave;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface FileSaveRepository extends CrudRepository<FileSave, UUID> {
}
