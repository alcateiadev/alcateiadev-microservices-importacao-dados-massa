package com.br.alcateiadev.leitorvendedor.service.file;

import com.br.alcateiadev.leitorvendedor.domain.FileSave;
import com.br.alcateiadev.leitorvendedor.gateway.repository.FileSaveRepository;
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
