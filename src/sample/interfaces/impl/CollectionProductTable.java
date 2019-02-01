package sample.interfaces.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import sample.Constants;
import sample.interfaces.ProductTable;
import sample.objects.Product;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.text.ParseException;

public class CollectionProductTable implements ProductTable {
    private ObservableList<Product> productList = FXCollections.observableArrayList();

    @Override
    public void add(Product product) {
        productList.add(product);
    }

    @Override
    public void update(Product product) {

    }

    @Override
    public void delete(Product product) {
        productList.remove(product);
    }

    public ObservableList<Product> getProductList() {
        return productList;
    }

    public void excelRead() {
        Workbook wb = null;
        String fileName = "cmpl.xls";
        OPCPackage pkg = null;
        if (fileName.endsWith("xlsx") || fileName.endsWith("xls")) {
            boolean isXLSX = (fileName.endsWith("xlsx"));

            try {
                InputStream inp = new FileInputStream(fileName);
                if (isXLSX) {
                    pkg = OPCPackage.open(inp);
                    wb = new XSSFWorkbook(pkg);
                } else {
                    wb = new HSSFWorkbook(new POIFSFileSystem(inp));
                }
                Product product = new Product();
                Cell cell;
                Cell cell1;
                int id, num1 = 0, num2 = 0, num3 = 0;
                String title;
                String type = "";
                String manufacturer = "";
                double retailPrice;
                double smallWholesale;
                double largeWholesale;
                //               int quantity;

                for (Sheet sheet1 : wb) {
                    for (Row row : sheet1) {
                        for (int i = 0; i <= 6; i++) {
                            for (String str : Constants.RetailPRICE) {
                                if (row.getCell(i).toString().contains(str)) {
                                    num1 = i;
                                    break;
                                }
                            }
                            for (String str : Constants.SmallWHOLESALE) {
                                if (row.getCell(i).toString().contains(str)) {
                                    num2 = i;
                                    break;
                                }
                            }
                            for (String str : Constants.LargeWHOLESALE) {
                                if (row.getCell(i).toString().contains(str)) {
                                    num3 = i;
                                    break;
                                }

                            }

                        }
                    }
                }

                for (Sheet sheet : wb) {
                    for (Row row : sheet) {
                        cell = row.getCell(0);
                        cell1 = row.getCell(1);

                        if (cell.getCellType() != CellType.NUMERIC & !(cell1.toString().equals(""))) {
                            type = row.getCell(1).toString();
                        } else if (cell != null && cell.getCellType() == CellType.NUMERIC) {
                            id = (int) cell.getNumericCellValue();
                            title = row.getCell(1).getStringCellValue();
                            for (String str : Constants.MANUFACTURER) {
                                if (title.contains(str)) {
                                    manufacturer = str;
                                }
                            }

                            NumberFormat nf = NumberFormat.getInstance();

                            retailPrice = nf.parse(String.format("%.2f", row.getCell(num1).getNumericCellValue())).doubleValue();
                            smallWholesale = nf.parse(String.format("%.2f", row.getCell(num2).getNumericCellValue())).doubleValue();
                            largeWholesale = nf.parse(String.format("%.2f", row.getCell(num3).getNumericCellValue())).doubleValue();
//                            quantity =(int) row.getCell(6).getNumericCellValue();
                            product = new Product(id, type, manufacturer, title, retailPrice, smallWholesale, largeWholesale);
                            productList.add(product);

                        }
                    }
                }
                wb.close();
                if (pkg != null) {
                    pkg.close();
                }

            } catch (FileNotFoundException | InvalidFormatException | NumberFormatException e) {
                System.out.println(e.getLocalizedMessage());
            } catch (IOException e) {
                System.out.println(e.getLocalizedMessage());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else System.out.println("Given file is NOT Microsoft Excel file!");
    }

    public void tablePrint() {
        int number = 0;
        for (Product product : productList) {
            number++;
            System.out.println(number + ") " + product);
        }
    }

}
