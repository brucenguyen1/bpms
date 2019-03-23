package support;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import oracle.binding.AttributeBinding;
import oracle.binding.BindingContainer;
import oracle.adf.model.BindingContext;

 

public class DownloadFileBean {

    public DownloadFileBean() {

    }

    private static String FILE = "c:/temp/RepaymentAgreement.pdf";

    private static Font catFont =

        new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);

    private static Font redFont =

        new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.RED);

    private static Font subFont =

        new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);

    private static Font smallBold =

        new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);

 

    private BindingContainer bindings;

 

    public void generatePDF(FacesContext facesContext,

                            java.io.OutputStream outputStream) {

 

        this.generatePDFFile(facesContext, outputStream); // Generate PDF File

        this.downloadPDF(facesContext, outputStream); // Download PDF File

 

    }

 

    private void downloadPDF(FacesContext facesContext,

                             java.io.OutputStream outputStream) {

 

        try {

            facesContext = facesContext.getCurrentInstance();

            ServletContext context =

                (ServletContext)facesContext.getExternalContext().getContext();

            ExternalContext ctx = facesContext.getExternalContext();

            HttpServletResponse res = (HttpServletResponse)ctx.getResponse();

 
            res.setContentType("application/pdf");

            System.out.println(context.getRealPath("/"));

            File file = new File(FILE);

            FileInputStream fdownload;

            byte[] b;

            fdownload = new FileInputStream(file);

            int n;

            while ((n = fdownload.available()) > 0) {

                b = new byte[n];

                int result = fdownload.read(b);

                outputStream.write(b, 0, b.length);

                if (result == -1)

                    break;

            }

            outputStream.flush();

        } catch (Exception e) {

            e.printStackTrace();

        }

 

    }

 

    private void generatePDFFile(FacesContext facesContext,

                                 java.io.OutputStream outputStream) {

 

        try {

            System.out.println("In Generate PDF................");

            Document document = new Document();

            PdfWriter.getInstance(document, new FileOutputStream(FILE));

            document.open();

            addMetaData(document);

            addTitlePage(document);

            addContent(document);

            document.close();

            System.out.println("End of PDF......................");

 

 

            facesContext = facesContext.getCurrentInstance();

            ServletContext context =

                (ServletContext)facesContext.getExternalContext().getContext();

 

 

            System.out.println(context.getRealPath("/"));

 

            File file = new File(FILE);

 

            FileInputStream fdownload;

            byte[] b;

 

            System.out.println(file.getCanonicalPath());

            System.out.println(file.getAbsolutePath());

            fdownload = new FileInputStream(file);

 

            int n;

            while ((n = fdownload.available()) > 0) {

                b = new byte[n];

                int result = fdownload.read(b);

                outputStream.write(b, 0, b.length);

                if (result == -1)

                    break;

            }

            outputStream.flush();

 

        } catch (Exception e) {

            e.printStackTrace();

        }

        //     /   return null;

        // Add event code here...

    }

 

 

    // iText allows to add metadata to the PDF which can be viewed in your Adobe

    // Reader

    // under File -> Properties

 

    private static void addMetaData(Document document) {

        document.addTitle("My first PDF");

        document.addSubject("Using iText");

        document.addKeywords("Java, PDF, iText");

        document.addAuthor("Lars Vogel");

        document.addCreator("Lars Vogel");

    }

 

    private static void addTitlePage(Document document) throws DocumentException {

        Date month = new Date();

        SimpleDateFormat currentMonth = new SimpleDateFormat("MMMM");

        Paragraph preface = new Paragraph();

        // We add one empty line

        addEmptyLine(preface, 1);

        // Lets write a big header

        preface.add(new Paragraph("Loan Application Pack", catFont));

 

        document.add(preface);

        // Start a new page

        // document.newPage();

    }

    private static void addContent(Document document)   throws DocumentException {

        BindingContext bctx = BindingContext.getCurrent(); 
        BindingContainer bindings = bctx.getCurrentBindingsEntry();
        AttributeBinding monthlyRepaymentAmountBinding = 
                      (AttributeBinding) bindings.get("monthlyRepaymentAmount"); 
        String monthlyRepaymentAmount = monthlyRepaymentAmountBinding.getInputValue().toString(); 
        AttributeBinding numberOfRepaymentBinding = 
                      (AttributeBinding) bindings.get("numberOfRepayment"); 
        String numberOfRepayment = numberOfRepaymentBinding.getInputValue().toString();
        
        document.add(new Paragraph("Monthly Repayment Amount: " + monthlyRepaymentAmount, catFont));
        document.add(new Paragraph("Number of Repayment: " + numberOfRepayment, catFont));
              
        System.out.println("monthlyRepaymentMount is "+monthlyRepaymentAmount); 
        
    }
 

 

    private static void addEmptyLine(Paragraph paragraph, int number) {

        for (int i = 0; i < number; i++) {

            paragraph.add(new Paragraph(" "));

        }

    }

 

    public static oracle.binding.BindingContainer getBindings() {

        return BindingContext.getCurrent().getCurrentBindingsEntry();

    }

}