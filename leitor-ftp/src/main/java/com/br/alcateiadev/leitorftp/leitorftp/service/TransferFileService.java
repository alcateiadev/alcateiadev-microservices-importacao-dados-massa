package com.br.alcateiadev.leitorftp.leitorftp.service;

import com.br.alcateiadev.leitorftp.leitorftp.domain.FileSave;
import com.br.alcateiadev.leitorftp.leitorftp.service.file.CreateFileSaveService;
import com.br.alcateiadev.leitorftp.leitorftp.service.file.SendFileKakfaService;
import com.br.alcateiadev.leitorftp.leitorftp.service.ftp.FileTransfer;
import com.br.alcateiadev.leitorftp.leitorftp.service.ftp.TransferFTPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.List;

@Service
public class TransferFileService {

    @Autowired
    private TransferFTPService transferFTPService;

    @Autowired
    private CreateFileSaveService createFileSaveService;

    @Autowired
    private SendFileKakfaService sendFileKakfaService;

    @Scheduled(fixedRate = 100 * 60)
    public void execute() throws FileNotFoundException {
        List<FileTransfer> files = transferFTPService.execute();

        for (FileTransfer fileTransfer : files) {
            String uuid = createFileSaveService.execute(
                    FileSave
                            .builder()
                            .path(fileTransfer.getPath())
                            .build()
            );

            fileTransfer.setUuid(uuid);
            sendFileKakfaService.execute(fileTransfer);
        }


    }

}
