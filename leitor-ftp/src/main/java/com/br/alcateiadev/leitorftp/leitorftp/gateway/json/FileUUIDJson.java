package com.br.alcateiadev.leitorftp.leitorftp.gateway.json;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class FileUUIDJson {
    private String uuid;
}
