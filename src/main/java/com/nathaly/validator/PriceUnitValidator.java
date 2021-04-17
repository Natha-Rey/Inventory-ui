package com.nathaly.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("pricePerUnitValidator")
public class PriceUnitValidator implements Validator<Double> {
    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Double aDouble) throws ValidatorException {
        if(aDouble <= 0.0){
            throw new ValidatorException(new FacesMessage("Price per Unit must be greater than 0.0"));
        }
    }
}
