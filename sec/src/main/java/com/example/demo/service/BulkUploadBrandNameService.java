package com.example.demo.service;

import com.example.demo.entity.cars.Brand;
import com.example.demo.repo.car.BrandRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class BulkUploadBrandNameService {

    @Autowired
    private BrandRepository brandRepository;

    public List<Brand> uploadBrands(MultipartFile file) {
        List<Brand> brands = new ArrayList<>();

        try (InputStream inputStream = file.getInputStream();
             Workbook workbook = new XSSFWorkbook(inputStream)) {

            Sheet sheet = workbook.getSheetAt(0); // Read the first sheet
            Iterator<Row> rows = sheet.iterator();
            boolean firstRow = true;

            while (rows.hasNext()) {
                Row currentRow = rows.next();

                if (firstRow) { // Skip header row
                    firstRow = false;
                    continue;
                }

                Cell nameCell = currentRow.getCell(0); // Assuming 'name' is in the first column
                if (nameCell != null && nameCell.getCellType() == CellType.STRING) {
                    Brand brand = new Brand();
                    brand.setName(nameCell.getStringCellValue().trim());
                    brands.add(brand);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to process the file: " + e.getMessage());
        }

        return brandRepository.saveAll(brands);
    }
}
