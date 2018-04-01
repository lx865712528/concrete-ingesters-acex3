package edu.bit.nlp.concrete.ingesters.acex3;

import edu.jhu.hlt.concrete.AnnotationMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConcreteUtils {

    private static final Logger log = LoggerFactory.getLogger(ConcreteUtils.class);

    public static final long annotationTime = System.currentTimeMillis();

    public static AnnotationMetadata metadata(String toolName) {
        return new AnnotationMetadata().setTool(toolName).setTimestamp(annotationTime);
    }

}
