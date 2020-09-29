package com.br.alcateiadev.leitorftp.leitorftp.service.file;

import com.br.alcateiadev.leitorftp.leitorftp.domain.FileSave;
import com.br.alcateiadev.leitorftp.leitorftp.enums.FileStatusEnum;
import com.br.alcateiadev.leitorftp.leitorftp.gateway.repository.FileSaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateFileSaveService {

    @Autowired
    private FileSaveRepository fileSaveRepository;

    @Transactional
    public String execute(FileSave fileSave) {
        fileSave.setStatus(FileStatusEnum.RECEBIDO.toString());
        fileSaveRepository.save(fileSave);
        return fileSave.getId().toString();
    }

}
