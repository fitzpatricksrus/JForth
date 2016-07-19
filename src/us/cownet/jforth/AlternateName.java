package us.cownet.jforth;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@interface AlternateName {
	String name();
};

