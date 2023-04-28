package annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.CONSTRUCTOR})
public @interface RunMe {
// or: public @ interface RunMe {
  // types of the access methods / attributes are very restricted
  // primitives (not wrappers!)
  // String
  // Annotation
  // enum
  // Class values
  // arrays of the above (note, that single dimensional)
  String value() default "Unknown";
  int count() default 10;
}
