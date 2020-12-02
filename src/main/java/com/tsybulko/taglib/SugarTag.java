package com.tsybulko.taglib;

import com.tsybulko.entity.drinks.Drink;
import com.tsybulko.entity.ingredients.Ingredient;
import com.tsybulko.services.factory.impl.ServiceFactoryImpl;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class SugarTag extends TagSupport {

    @Override
    public int doStartTag() throws JspException {
        StringBuffer tag = new StringBuffer();
        Ingredient sugar = ServiceFactoryImpl.getServiceFactoryInstance().getIngredientService().getIngredientByName("sugar");
        try {
            if (sugar.getQuantity() > 4) {
                int maxCount = sugar.getQuantity() > 4 * Drink.GRAM_OF_SUGAR_IN_SPOON ? 5
                        : sugar.getQuantity() / Drink.GRAM_OF_SUGAR_IN_SPOON;
                int defaultSugarCount = sugar.getQuantity() > (Drink.GRAM_OF_SUGAR_IN_SPOON * 2) - 1 ? 2
                        : sugar.getQuantity() / Drink.GRAM_OF_SUGAR_IN_SPOON;
                tag.append("<input type=\"range\" oninput=\"sugarCount.value=(sugar.value)\" id=\"sugar\" name=\"sugar\"" +
                        "min=\"0\" max=\"" + maxCount + "\" step=\"1\" value=\"" + defaultSugarCount + "\"/>");
                tag.append("<output id=\"sugarCount\">" + defaultSugarCount + "</output>");
                pageContext.getOut().print(tag.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }
}
