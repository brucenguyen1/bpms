package support;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.layout.RichPanelBox;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;

 

public class PDFGeneratorBean {

    private RichPanelStretchLayout psl1;

    private RichForm f1;

    private RichDocument d1;

    private RichCommandButton cb1;

    private RichPanelGroupLayout pgl1;

    private RichPanelBox pb1;

 

    public void setPsl1(RichPanelStretchLayout psl1) {

        this.psl1 = psl1;

    }

 

    public RichPanelStretchLayout getPsl1() {

        return psl1;

    }

 

    public void setF1(RichForm f1) {

        this.f1 = f1;

    }

 

    public RichForm getF1() {

        return f1;

    }

 

    public void setD1(RichDocument d1) {

        this.d1 = d1;

    }

 

    public RichDocument getD1() {

        return d1;

    }

 

    public void setCb1(RichCommandButton cb1) {

        this.cb1 = cb1;

    }

 

    public RichCommandButton getCb1() {

        return cb1;

    }

 

    public void setPgl1(RichPanelGroupLayout pgl1) {

        this.pgl1 = pgl1;

    }

 

    public RichPanelGroupLayout getPgl1() {

        return pgl1;

    }

 

    public void setPb1(RichPanelBox pb1) {

        this.pb1 = pb1;

    }

 

    public RichPanelBox getPb1() {

        return pb1;

    }

 

    public void addMessage(FacesMessage.Severity type, String message) {

        FacesContext fctx = FacesContext.getCurrentInstance();

        FacesMessage fm = new FacesMessage(type, message, null);

        fctx.addMessage(null, fm);

    }
   

}
