/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tags;

import data.ItemDao;
import data.User;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.SkipPageException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author olda9
 */
public class Number extends SimpleTagSupport {

    private String format;
    private String number;

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    
    @Override
    public void doTag() throws JspException, IOException {
        System.out.println("Number is:" + number);
        System.out.println("Format is:" + format);
        try {
            double amount = Double.parseDouble(number);
            DecimalFormat formatter = new DecimalFormat(format);
            String formattedNumber = formatter.format(amount);
            getJspContext().getOut().write(formattedNumber);
        } catch (Exception e) {
            e.printStackTrace();
            // stop page from loading further by throwing SkipPageException
            throw new SkipPageException("Exception in formatting " + number
                    + " with format " + format);
        }
    }

}


