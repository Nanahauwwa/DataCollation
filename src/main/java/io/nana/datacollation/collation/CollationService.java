package io.nana.datacollation.collation;

import jakarta.mail.Multipart;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

@Service
public class CollationService {
    @Autowired
    private CollationRepository collationRepository;

    public void saveExcelDataToDatabase(MultipartFile multipartfile) throws IOException{
        InputStream inputStream = multipartfile.getInputStream();
        Workbook workbook = WorkbookFactory.create(inputStream);
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.iterator();
        // skip header row
        if (rowIterator.hasNext()){
            rowIterator.next();
        }
        while (rowIterator.hasNext()){
            Row row = rowIterator.next();
            CollationModel artisan = new CollationModel();
            artisan.setName(row.getCell(0).getStringCellValue());
            artisan.setSex(row.getCell(5).getStringCellValue());
            artisan.setPhoneNumber(row.getCell(2).getStringCellValue());
            artisan.setCity(row.getCell(4).getStringCellValue());
            artisan.setState(row.getCell(3).getStringCellValue());
            artisan.setProgram(row.getCell(6).getStringCellValue());
            artisan.setTrade(row.getCell(1).getStringCellValue());
            collationRepository.save(artisan);

        }

    }
}
