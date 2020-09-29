package com.br.alcateiadev.leitorftp.leitorftp.service.ftp;

import lombok.Builder;
import lombok.Data;

import java.io.File;

@Builder
@Data
public class FileTransfer {
    private String uuid;
    private String path;
    private File pathLocal;
}
