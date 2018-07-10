/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tags;

import data.ItemDao;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author olda9
 */
public class Css extends SimpleTagSupport {

    @Override
    public void doTag() throws JspException, IOException {
        super.doTag(); //To change body of generated methods, choose Tools | Templates.
        final JspWriter writer = getJspContext().getOut();
        writer.println("<style>");
        writer.println("table.paleBlueRows {\n"
                + "  font-family: \"Times New Roman\", Times, serif;\n"
                + "  border: 1px solid #FFFFFF;\n"
                + "  width: 350px;\n"
                + "  height: 200px;\n"
                + "  text-align: center;\n"
                + "  border-collapse: collapse;\n"
                + "}\n"
                + "table.paleBlueRows td, table.paleBlueRows th {\n"
                + "  border: 1px solid #FFFFFF;\n"
                + "  padding: 3px 20px;\n"
                + "  margin: 3px 50px;\n"
                + "}\n"
                + "table.paleBlueRows tbody td {\n"
                + "  font-size: 13px;\n"
                + "}\n"
                + "table.paleBlueRows tr:nth-child(even) {\n"
                + "  background: #D0E4F5;\n"
                + "}\n"
                + "table.paleBlueRows thead {\n"
                + "  background: #0B6FA4;\n"
                + "  border-bottom: 5px solid #FFFFFF;\n"
                + "}\n"
                + "table.paleBlueRows thead th {\n"
                + "  font-size: 17px;\n"
                + "  font-weight: bold;\n"
                + "  color: #FFFFFF;\n"
                + "  text-align: center;\n"
                + "  border-left: 2px solid #FFFFFF;\n"
                + "}\n"
                + "table.paleBlueRows thead th:first-child {\n"
                + "  border-left: none;\n"
                + "}\n"
                + "\n"
                + "table.paleBlueRows tfoot {\n"
                + "  font-size: 14px;\n"
                + "  font-weight: bold;\n"
                + "  color: #333333;\n"
                + "  background: #D0E4F5;\n"
                + "  border-top: 3px solid #444444;\n"
                + "}\n"
                + "table.paleBlueRows tfoot td {\n"
                + "  font-size: 14px;\n"
                + "}");

        writer.println("#demo {\n"
                + "-webkit-box-shadow: 5px 5px 15px 5px #000000; \n"
                + "box-shadow: 5px 5px 15px 5px #000000;\n"
                + "padding: 5px 20px;\n"
                + "}");
        writer.println("h2, p {\n"
                + "  display: inline;\n"
                + "}");
        writer.println("a{\n"
                + "  align: right;\n"
                + "margin-right:0px;\n"
                + "padding-right:0px;\n"
                + "float: right;\n"
                + "width: 50;\n"
                + "    padding: 10px;"
                + "}");
        writer.println("</style>");
    }

}
