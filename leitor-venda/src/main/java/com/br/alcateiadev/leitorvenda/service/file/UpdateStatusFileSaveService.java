package com.br.alcateiadev.leitorvenda.service.file;

import com.br.alcateiadev.leitorvenda.enums.FileStatusEnum;
import com.br.alcateiadev.leitorvenda.gateway.repository.FileSaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class UpdateStatusFileSaveService {

    @Autowired
    private FileSaveRepository fileSaveRepository;

    @Transactional
    public void execute(UUID uuid) {
        fileSaveRepository.updateStatus(FileStatusEnum.PROCESSADO.toString(), uuid);
    }
}
