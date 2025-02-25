package view;

import model.User;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class UserPdf {

    public void exportPdf(List<User> users) {
        System.out.println(users.size());
        Document document = new Document(PageSize.A4);

        try {
            PdfWriter writer = PdfWriter.getInstance(document,
                new FileOutputStream(System.getProperty("user.dir") + File.separator + "users.pdf"));
            
            document.open();

            // Definisi kolom
            float[] columnWidths = {33.33F, 33.33F, 33.33F};
            PdfPTable table = new PdfPTable(columnWidths);
            table.setWidthPercentage(100);

            // Header tabel
            table.addCell(new Phrase("No"));
            table.addCell(new Phrase("Name"));
            table.addCell(new Phrase("Email"));

            // Isi tabel
            int no = 1;
            for (User user : users) {
                table.addCell(new Phrase(String.valueOf(no++)));
                table.addCell(new Phrase(user.getName()));
                table.addCell(new Phrase(user.getEmail()));
            }

            // Menambahkan tabel ke dokumen
            document.add(table);

        } catch (DocumentException | IOException ex) {
            System.err.println(ex.getMessage());
        } finally {
            document.close();
        }
    }
}
