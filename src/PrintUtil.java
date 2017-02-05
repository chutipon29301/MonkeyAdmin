/*
Copyright [2017] [Chutipon]

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;
import org.apache.pdfbox.printing.PDFPrintable;
import org.apache.pdfbox.printing.Scaling;

import javax.print.attribute.*;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.Sides;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;


class PrintUtil {
    private String path;

    PrintUtil(String path) {
        this.path = path;
    }

    void print(){
        PDDocument doc = null;
        try {
            doc = PDDocument.load(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(new PDFPrintable(doc, Scaling.SCALE_TO_FIT));
//        job.setPageable(new PDFPageable(doc));
        PrintRequestAttributeSet attr = new HashPrintRequestAttributeSet();
        attr.add(Sides.DUPLEX);
        attr.add(MediaSizeName.ISO_A4);
        try {
            job.print(attr);
        } catch (PrinterException e) {
            e.printStackTrace();
        }
    }
}
