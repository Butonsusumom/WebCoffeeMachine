package com.tsybulko.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.DynamicAttributes;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PagesTag extends TagSupport implements DynamicAttributes {
    private Map<String, Object> map = new HashMap<>();
    private int activated;
    private int length;
    private static int countInOnePage = 30;
    private static int maxButtonCount = 10;

    @Override
    public int doStartTag() throws JspException {
        activated = (int) map.get("activated");
        length = (int) map.get("length");
        StringBuffer buffer = new StringBuffer();
        List<String> buttons = new ArrayList<>();
        List<String> resultButtons = new ArrayList<>();
        if (!(length <= countInOnePage)) {
            int count = length % countInOnePage == 0 ? length / countInOnePage : (length / countInOnePage) + 1;
            buffer.append("<form action=\"\" id=\"pagingForm\">");
            for (int i = 1; i <= count; i++) {
                String input = "<input type=\"submit\" name=\"currentPage\" class=\"paging\" value=\"" + i + "\"";
                if (i == activated)
                    input += "id=\"currentPage\" disabled=\"disabled\"";
                input += "/>";
                buttons.add(input);
            }
            if (buttons.size() > maxButtonCount) {
                if (activated > 3) {
                    if (activated > 4)
                        buffer.append(buttons.get(0));
                    buffer.append('\u2026');
                }
                for (int i = activated - 1; i >= activated - 3; i--) {
                    resultButtons.add(0, buttons.get(i));
                    if (i == 0)
                        break;
                }
                for (int i = activated; i < buttons.size(); i++) {
                    if (resultButtons.size() == 10)
                        break;
                    resultButtons.add(buttons.get(i));
                }
                if (resultButtons.size() < maxButtonCount) {
                    for (int i = activated - 4; i >= 0; i--) {
                        if (resultButtons.size() == 10)
                            break;
                        resultButtons.add(0, buttons.get(i));
                    }
                }
                for (String button : resultButtons) {
                    buffer.append(button);
                }
                if (!(resultButtons.get(9).equals(buttons.get(buttons.size() - 1)))) {
                    buffer.append('\u2026');
                    if (!resultButtons.get(9).equals(buttons.get(buttons.size() - 2)))
                        buffer.append(buttons.get(buttons.size() - 1));
                }
            } else {
                for (String button : buttons)
                    buffer.append(button);
            }
            buffer.append("</form>");
            buffer.append("<br/>");
            try {
                pageContext.getOut().print(buffer.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return SKIP_BODY;
    }

    @Override
    public void setDynamicAttribute(String uri, String name, Object value) throws JspException {
        map.put(name, value);
    }
}
