package com.simbirsoft.modeles;

import org.apache.commons.io.FilenameUtils;
import org.primefaces.model.UploadedFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class PrimeFacesFile {
    private UploadedFile uploadedFile;

    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    public void transferFile(String filepath) throws IOException {
        InputStream inputStream = uploadedFile.getInputstream();
        OutputStream outputStream = new FileOutputStream(new java.io.File(filepath));
        Integer reader = 0;
        byte[] bytes = new byte[(int)getUploadedFile().getSize()];
        while ((reader = inputStream.read(bytes))!=-1){
            outputStream.write(bytes, 0, reader);
        }
        inputStream.close();
        outputStream.flush();
        outputStream.close();
    }
}
