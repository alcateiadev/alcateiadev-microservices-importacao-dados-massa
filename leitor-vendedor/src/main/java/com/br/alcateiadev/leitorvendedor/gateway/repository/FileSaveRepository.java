package com.br.alcateiadev.leitorvendedor.gateway.repository;

import com.br.alcateiadev.leitorvendedor.domain.FileSave;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface FileSaveRepository extends CrudRepository<FileSave, UUID> {

    @Modifying
    @Query("update FileSave set status = ?1 where id = ?2")
    void updateStatus(String status, UUID uuid);
}
