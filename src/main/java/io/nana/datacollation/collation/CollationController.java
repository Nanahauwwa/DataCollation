package io.nana.datacollation.collation;

import jakarta.mail.Multipart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/api")
public class CollationController {
    @Autowired
    private CollationService collationService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadExcel(@RequestBody MultipartFile file) {
        try {
            collationService.saveExcelDataToDatabase(file);
            return  ResponseEntity.status(HttpStatus.OK).body("Data uploaded successfully.");
        } catch (IOException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }




}
