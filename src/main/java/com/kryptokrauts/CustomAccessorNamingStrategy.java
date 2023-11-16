/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package com.kryptokrauts;

import java.beans.Introspector;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.type.TypeKind;

import org.mapstruct.ap.spi.AccessorNamingStrategy;
import org.mapstruct.ap.spi.DefaultAccessorNamingStrategy;

/**
 * A custom {@link AccessorNamingStrategy} recognizing getters in the form of {@code property()} and setters in the
 * form of {@code withProperty(value)}.
 *
 * @author Sjaak Derksen
 */
public class CustomAccessorNamingStrategy extends DefaultAccessorNamingStrategy {

		public CustomAccessorNamingStrategy() {
			System.out.println("Initialized-----------------");
		}

		public String inout(String in) {
			return in;
		}

    @Override
    public boolean isGetterMethod(ExecutableElement method) {
			System.out.println("CALLED GETTER FOR "+method.getSimpleName().toString());
        String methodName = method.getSimpleName().toString();
        return !methodName.startsWith( "with" ) && method.getReturnType().getKind() != TypeKind.VOID;
    }

    @Override
    public String getPropertyName(ExecutableElement getterOrSetterMethod) {
			System.out.println("CALLED GETPROPERTY FOR "+getterOrSetterMethod.getSimpleName().toString());
        String methodName = getterOrSetterMethod.getSimpleName().toString();
        return Introspector.decapitalize( methodName.startsWith( "with" ) ? methodName.substring(  4 ) : methodName );
    }
}