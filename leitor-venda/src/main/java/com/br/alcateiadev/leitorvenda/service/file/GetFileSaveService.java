package com.br.alcateiadev.leitorvenda.service.file;

import com.br.alcateiadev.leitorvenda.domain.FileSave;
import com.br.alcateiadev.leitorvenda.gateway.repository.FileSaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GetFileSaveService {

    @Autowired
    private FileSaveRepository fileSaveRepository;

    public FileSave execute(UUID uuid) {
        return fileSaveRepository.findById(uuid).get();
    }
}
