package com.br.alcateiadev.leitorvendedor.service.file;

import com.br.alcateiadev.leitorvendedor.enums.FileStatusEnum;
import com.br.alcateiadev.leitorvendedor.gateway.repository.FileSaveRepository;
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
