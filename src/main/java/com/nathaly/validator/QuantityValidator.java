package com.nathaly.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("quantityValidator")
public class QuantityValidator implements Validator<Integer> {
    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Integer integer) throws ValidatorException {
        if(integer <=0){
            throw new ValidatorException(new FacesMessage("Quantity has to be greater than 0"));
        }
    }
}
