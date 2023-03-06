package io.nana.datacollation.artisan;

import io.nana.datacollation.programme.ProgrammeModel;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

@Service
public class ArtisanService {
    @Autowired
    private CollationRepository collationRepository;


//    ArtisanModel artisan = new ArtisanModel();
    // set other fields...
//    ProgrammeModel programme1 = new ProgrammeModel();
//programme1.setTitle("Programme 1");
//programme1.setDescription("Description for Programme 1");
//programme1.setArtisan(artisan); // set the artisan for this programme
//artisan.getProgrammes().add(programme1); // add the programme to the artisan's list
//// add more programmes if needed...
//artisanRepository.save(artisan); // save the artisan to the database

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
            ArtisanModel artisan = new ArtisanModel();
            artisan.setFullName(row.getCell(0).getStringCellValue());

            if (row.getCell(5)!= null && row.getCell(5).getStringCellValue().equalsIgnoreCase("male")){
                artisan.setSex(Constants.Genders.MALE);
            } else if (row.getCell(5)!= null && row.getCell(5).getStringCellValue().equalsIgnoreCase("female")){
                artisan.setSex(Constants.Genders.FEMALE);
            } else {
                artisan.setSex(Constants.Genders.OTHER);
            }

            artisan.setPhoneNumber(row.getCell(2).getStringCellValue());
            artisan.setCity(row.getCell(4).getStringCellValue());
            artisan.setState(row.getCell(3).getStringCellValue());

            if(row.getCell(6).getStringCellValue().split(",").length > 0) {
                ProgrammeModel programme = null;
                for (String s : row.getCell(6).getStringCellValue().split(",")) {
                    programme = new ProgrammeModel();
                    programme.setTitle(s);
                    programme.setArtisan(artisan);
                }
                artisan.getProgrammes().add(programme);
            }

            artisan.setTrade(row.getCell(1).getStringCellValue());

                collationRepository.save(artisan);

        }

    }

    public List<ArtisanModel> getAllArtisanByTradeName(String trade){
        return collationRepository.findByTrade(trade);
    }
}
