package io.nana.datacollation.artisan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class CollationApi {
    @Autowired
    private ArtisanService collationService;

    @PostMapping("/upload-spreadsheet")
    public ResponseEntity<String> uploadExcel(@RequestBody MultipartFile file) {
        try {
            collationService.saveExcelDataToDatabase(file);
            return  ResponseEntity.status(HttpStatus.OK).body("Spreadsheet uploaded successfully.");
        } catch (IOException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/{trade}")
    public ResponseEntity<?> fetchArtisanByTradeName(@PathVariable("trade") String tradeName) {
        if(tradeName == null || tradeName.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid trade name");
        }
        return ResponseEntity.ok().body(collationService.getAllArtisanByTradeName(tradeName));
         }
}
